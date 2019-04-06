/**
 * A type of soldier. It has no additional attributes.
 * Only behavior is different.
 *
 * The bullets that this class creates are the sniper bullets.
 *
 * The speed, shooting range, and collision range are specific
 * and values are present in Constants.java
 *
 * @see SimulationObject
 * @see Soldier
 * @see Constants
 */
public class Sniper extends Soldier{

    public Sniper(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.SNIPER_SPEED, Constants.SNIPER_COLLISION_RANGE,
                Constants.SNIPER_SHOOTING_RANGE);
    }

    /**
     * Creates a sniper bullet with the same position and direction of the sniper.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void createBullet(SimulationController controller) {
        Bullet bullet = Bullet.factorySniperBullet(getPosition().clone(), getDirection().clone());
        controller.addBullet(bullet);
        printFiringBullet(bullet.getName());
    }

    /**
     * Handles the searching state of the sniper.
     *
     * This function behaves like this :
     *
     * – Calculate the next position of the soldier .
     * – If the position is out of bounds, change direction to random value
     * – If the position is not out of bounds, change soldier position to the new_position.
     * – Change state to AIMING.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleSearching(SimulationController controller) {
        // go next position if no going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);
        setState(SoldierState.AIMING);
    }


}
