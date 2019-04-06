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
public class SlowZombie extends Zombie {

    /**
     * The only constructor of the slow zombie.
     * Calls super constructor with given name, given position, slow zombie speed,
     * slow zombie collision range, slow zombie detection range.
     *
     * @param name is the name of the zombie
     * @param position is the position of the zombie
     */
    public SlowZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS

        super(name, position, Constants.SLOW_ZOMBIE_SPEED, Constants.SLOW_ZOMBIE_COLLISION_RANGE,
                Constants.SLOW_ZOMBIE_DETECTION_RANGE);

    }

    /**
     * Handles the one simulation step of the slow zombie in wandering state.
     *
     * Behaves like this :
     * – Calculate the euclidean distance to the closest soldier.
     * – If the distance is shorter than or equal to the detection range of the zombie, change state to
     * FOLLOWING and return.
     * – If not calculate the next position of the zombie .
     * – If the position is out of bounds, change direction to random value.
     * – If the position is not out of bounds, change zombie position to the new_position.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleWandering(SimulationController controller) {
        // calculate distance and index of closest zombie
        HashMap<String, Double> closestSoldierValues = controller.getClosestSoldierValues(getPosition());
        double distance = closestSoldierValues.get("distance");
        if(canDetect(distance)){
            setState(ZombieState.FOLLOWING);
        } else {
            // go next position if not going out of borders
            // change direction otherwise
            goNextOrChangeDirection(controller);
        }
    }

    /**
     * Handles the one simulation step of the slow zombie in Following state
     *
     * Behaves like this :
     *
     * – Calculate the euclidean distance to the closest soldier.
     * – If the distance is shorter than or equal to the detection range of the zombie, change direction
     * to soldier.
     * – Calculate the next position of the zombie.
     * – If the position is out of bounds, change direction to random value.
     * – If the position is not out of bounds, change zombie position to the new_position.
     * – Use the calculated distance to the closest soldier in the first step. If the distance is shorter
     * than or equal to the detection range of the zombie, change state to WANDERING.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleFollowing(SimulationController controller) {
        // calculate distance and index of closest zombie
        HashMap<String, Double> closestSoldierValues = controller.getClosestSoldierValues(getPosition());
        double distance = closestSoldierValues.get("distance");
        double index = closestSoldierValues.get("index");

        if(canDetect(distance)){
            // zombie detected the soldier
            turnDirectionToPosition(controller.getSoldier((int) index).getPosition());
        }

        // go next position if not going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        if(canDetect(distance)){
            setState(ZombieState.WANDERING);
        }
    }
}
