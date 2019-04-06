import java.util.HashMap;

/**
 * A type of zombie. It has no additional attributes.
 * Only behavior is different.
 *
 * The speed, detection range, and collision range are specific
 * and values are present in Constants.java
 *
 * @see SimulationObject
 * @see Zombie
 * @see Constants
 *
 */
public class FastZombie extends Zombie {

    /**
     * The only constructor of the fast zombie.
     * Calls super constructor with given name, given position, fast zombie speed,
     * fast zombie collision range, fast zombie detection range.
     *
     * @param name is the name of the zombie
     * @param position is the position of the zombie
     */
    public FastZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.FAST_ZOMBIE_SPEED, Constants.FAST_ZOMBIE_COLLISION_RANGE,
                Constants.FAST_ZOMBIE_DETECTION_RANGE);
    }


    /**
     * Handles the one simulation step of the fast zombie in wandering sate
     *
     * Behaves like this :
     *
     * – Calculate the euclidean distance to the closest soldier.
     * – If the distance is shorter than or equal to the detection range of the zombie, change direction
     * to soldier, change state to FOLLOWING and return.
     * – If not calculate the next position of the zombie.
     * – If the position is out of bounds, change direction to random value.
     * – If the position is not out of bounds, change zombie position to the new_position.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleWandering(SimulationController controller) {
        // calculate distance and index of closest soldier
        HashMap<String, Double> closestSoldierValues = controller.getClosestSoldierValues(getPosition());
        double distance = closestSoldierValues.get("distance");
        double index = closestSoldierValues.get("index");
        if(canDetect(distance)){
            // zombie detected the soldier
            turnDirectionToPosition(controller.getSoldier((int) index).getPosition());
            setState(ZombieState.FOLLOWING);
        } else {
            // go next position if not going out of borders
            // change direction otherwise
            goNextOrChangeDirection(controller);
        }
    }

    /**
     * Handles the one simulation step of the fast zombie in following sate
     *
     * Behaves like this :
     *
     * – Calculate the next position of the zombie .
     * – If the position is out of bounds, change direction to random value.
     * – If the position is not out of bounds, change zombie position to the new_position.
     * – Change state to WANDERING.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleFollowing(SimulationController controller) {
        // go next position if not going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        setState(ZombieState.WANDERING);

    }
}
