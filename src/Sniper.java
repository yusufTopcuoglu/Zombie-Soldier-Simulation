import java.util.HashMap;

/**
 *
 *
 */
public class Sniper extends Soldier{

    public Sniper(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.SNIPER_SPEED, SoldierType.SNIPER,
                Constants.SNIPER_COLLISION_RANGE, Constants.SNIPER_SHOOTING_RANGE);
    }

    @Override
    public void createBullet(SimulationController controller) {
        Bullet bullet = new SniperBullet(getPosition(), getDirection());
        controller.addBullet(bullet);
        printFiringBullet(bullet.getName());
    }

    /**
     * This function behaves like this ;
     * – Calculate the next position of the soldier .
     * – If the position is out of bounds, change direction to random value
     * – If the position is not out of bounds, change soldier position to the new_position.
     * – Change state to AIMING.
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleSearching(SimulationController controller) {
        // calculate next position of the soldier
        Position nextPosition = calculateNextPosition();
        if ( controller.isPositionInside(nextPosition)){
            // next position is inside the bounds, change position to hte next position
            setPosition(nextPosition);
        } else {
            // next position is out of bounds, set direction randomly
            setDirection(Position.generateRandomDirection(true));
        }
        setState(SoldierState.AIMING);
    }


    @Override
    public void handleShooting(SimulationController controller) {
        createBullet(controller);

        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = controller.getClosestEnemyValues(this);
        double distance = closestZombieValues.get("distance");
        double index = closestZombieValues.get("index");

        // if soldier can shoot to this distance
        if(canShoot(distance)){
            setState(SoldierState.AIMING);
        } else {
            // soldier can not shoot to that distance
            setDirection(Position.generateRandomDirection(true));
            setState(SoldierState.SEARCHING);
        }
    }
}
