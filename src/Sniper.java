/**
 *
 *
 */
public class Sniper extends Soldier{

    public Sniper(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.SNIPER_SPEED, SoldierType.SNIPER,
                Constants.SNIPER_COLLISION_RANGE, Constants.SNIPER_SHOOTING_RANGE);
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
        if (nextPosition.isInsideBounds(controller)){
            // next position is inside the bounds, change position to hte next position
            setPosition(nextPosition);
        } else {
            // next position is out of bounds, set direction randomly
            setDirection(Position.generateRandomDirection(true));
        }
        setState(SoldierState.AIMING);
    }

    @Override
    public void handleAiming(SimulationController controller) {

    }

    @Override
    public void handleShooting(SimulationController controller) {

    }
}
