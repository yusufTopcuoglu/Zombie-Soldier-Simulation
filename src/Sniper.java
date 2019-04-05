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
        Bullet bullet = Bullet.factorySniperBullet(getPosition().clone(), getDirection().clone());
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
        // go next position if no going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        setState(SoldierState.AIMING);
    }


}
