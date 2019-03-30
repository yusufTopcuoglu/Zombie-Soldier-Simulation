import java.util.HashMap;

/**
 *
 *
 */
public class RegularSoldier extends Soldier {

    public RegularSoldier(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.REGULAR_SOLDIER_SPEED, SoldierType.REGULAR,
                Constants.REGULAR_SOLDIER_COLLISION_RANGE, Constants.REGULAR_SOLDIER_SHOOTING_RANGE);
    }


    @Override
    public void createBullet(SimulationController controller) {
        Bullet bullet = Bullet.factoryRegularSoldierBullet(getPosition(), getDirection());
        controller.addBullet(bullet);
        printFiringBullet(bullet.getName());
    }

    /**
     * This function behaves like this ;
     * – Calculate the next position of the soldier
     * – If the position is out of bounds, change direction to random value
     * – If the position is not out of bounds, change soldier position to the new_position.
     * – Calculate the euclidean distance to the closest zombie.
     * – If the distance is shorter than or equal to the shooting range of the soldier, change state to
     * AIMING.
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleSearching(SimulationController controller) {
        // calculate the next position
        Position nextPosition = calculateNextPosition();
        if ( controller.isPositionInside(nextPosition) ){
            // the nextPosition is inside the borders of controller
            setPosition(nextPosition);
        } else {
            // the nextPosition is out of borders of controller
            // change direction randomly
            setDirection(Position.generateRandomDirection(true));
        }
        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = controller.getClosestEnemyValues(this);
        double distance = closestZombieValues.get("distance");
        if (canShoot(distance)){
            // soldier can shoot to that distance, change state to aiming
            setState(SoldierState.AIMING);
        }
    }

    @Override
    public void handleShooting(SimulationController controller) {
        createBullet(controller);

        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = controller.getClosestEnemyValues(this);
        double distance = closestZombieValues.get("distance");
        if (canShoot(distance)){
            // soldier can shoot to that distance, change state to aiming
            setState(SoldierState.AIMING);
        } else {
            // soldier can not shoot to that distance
            setDirection(Position.generateRandomDirection(true));
            setState(SoldierState.SEARCHING);
        }

    }
}
