import java.util.HashMap;

/**
 * A type of soldier. It has no additional attributes.
 * Only behavior is different.
 *
 * The bullets that this class creates are the regular soldier bullets.
 *
 * The speed, shooting range, and collision range are specific
 * and values are present in Constants.java
 *
 * @see SimulationObject
 * @see Soldier
 * @see Constants
 */
public class RegularSoldier extends Soldier {

    /**
     * The only constructor of this class. Calls the super class constructor with
     * given name, given position, regular soldier speed,
     * regular soldier collision range, regular soldier shooting range.
     *
     * @param name is the name of the commando.
     * @param position is the position of the position.
     */
    RegularSoldier(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.REGULAR_SOLDIER_SPEED, Constants.REGULAR_SOLDIER_COLLISION_RANGE,
                Constants.REGULAR_SOLDIER_SHOOTING_RANGE);
    }

    /**
     * Creates a regular soldier bullet with the same position and direction of the regular soldier.
     *
     * @param controller is the SimulationController object that simulation plays in
     */
    @Override
    public void createBullet(SimulationController controller) {
        Bullet bullet = Bullet.factoryRegularSoldierBullet(getPosition().clone(), getDirection().clone());
        controller.addBullet(bullet);
        printFiringBullet(bullet.getName());
    }

    /**
     * Handles one simulation step of the regular soldier in searching state
     *
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
        // go next position if no going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = controller.getClosestZombieValues(getPosition());
        double distance = closestZombieValues.get("distance");
        if (canShoot(distance)){
            // soldier can shoot to that distance, change state to aiming
            setState(SoldierState.AIMING);
        }
    }

}
