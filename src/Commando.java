import java.util.HashMap;

/**
 *
 *
 */
public class Commando extends Soldier {

    public Commando(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.COMMANDO_SPEED, SoldierType.COMMANDO,
                Constants.COMMANDO_COLLISION_RANGE, Constants.COMMANDO_SHOOTING_RANGE);

    }

    @Override
    public void createBullet(SimulationController controller) {
        Bullet bullet = Bullet.factoryCommandoBullet(getPosition().clone(), getDirection().clone());
        controller.addBullet(bullet);
        printFiringBullet(bullet.getName());
    }

    /**
     * This function behaves like this ;
     *
     * – Calculate the euclidean distance to the closest zombie.
     * – If the distance is shorter than or equal to the shooting range of the soldier; change soldier
     * direction to zombie, change state to SHOOTING and return.
     * – Calculate the next position of the soldier .
     * – If the position is out of bounds, change direction to random value.
     * – If the position is not out of bounds, change soldier position to the new_position.
     * – Calculate the euclidean distance to the closest zombie.
     * – If the distance is shorter than or equal to the shooting range of the soldier; change soldier
     * direction to zombie, change state to SHOOTING.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleSearching(SimulationController controller) {
        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = controller.getClosestZombieValues(getPosition());
        double distance = closestZombieValues.get("distance");
        double index = closestZombieValues.get("index");

        // if soldier can shoot to this distance
        if(canShoot(distance)){
            turnDirectionToPosition(controller.getZombie( (int) index).getPosition());
            setState(SoldierState.SHOOTING);
            return;
        }

        // go next position if no going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        // re-calculate the distance and index of closest zombie
        closestZombieValues = controller.getClosestZombieValues(getPosition());
        distance = closestZombieValues.get("distance");
        index = closestZombieValues.get("index");

        if(canShoot(distance)){
            // soldier can shoot to this distance
            turnDirectionToPosition(controller.getZombie( (int) index).getPosition());
            setState(SoldierState.SHOOTING);
        }

    }

    /**
     * Throws illegal statte exception since commando have no aiming state
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleAiming(SimulationController controller) {
        System.out.println("Illegal Commando State");
        throw new IllegalStateException();
    }

    @Override
    public void handleShooting(SimulationController controller) {
        createBullet(controller);

        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = controller.getClosestZombieValues(getPosition());
        double distance = closestZombieValues.get("distance");
        double index = closestZombieValues.get("index");

        // if soldier can shoot to this distance
        if(canShoot(distance)){
            turnDirectionToPosition(controller.getZombie((int) index).getPosition());
        } else {
            // soldier can not shoot to that distance
            setDirection(Position.generateRandomDirection(true));
            setState(SoldierState.SEARCHING);
        }
    }
}
