import java.util.HashMap;

/**
 * A type of zombie. It has no additional attributes that
 * are MAX_FOLLOW_COUNT and followCount.
 *
 *
 * The speed, detection range, and collision range are specific
 * and values are present in Constants.java
 *
 * @see SimulationObject
 * @see Zombie
 * @see Constants
 *
 */
public class RegularZombie extends Zombie{
    /**
     * Indicates the number of step that this zombie will be in
     * following state. It is 4 for the all regular zombies and will
     * not change.
     */
    private static final int MAX_FOLLOW_COUNT = 4;

    private int followCount;

    /**
     * The only constructor of the regular zombie.
     * Calls super constructor with given name, given position, regular zombie speed,
     * regular zombie collision range, regular zombie detection range.
     * Initialize the follow count as 0.
     *
     * @param name is the name of the zombie
     * @param position is the position of the zombie
     */
    RegularZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.REGULAR_ZOMBIE_SPEED, Constants.REGULAR_ZOMBIE_COLLISION_RANGE,
                Constants.REGULAR_ZOMBIE_DETECTION_RANGE);
        followCount = 0;
    }

    /**
     * Handles the one simulation step of the regular zombie in wandering state
     *
     * Behaves like this :
     *
     * – Calculate the next position of the zombie .
     * – If the position is out of bounds, change direction to random value.
     * – If the position is not out of bounds, change zombie position to the new_position.
     * – Calculate the euclidean distance to the closest soldier.
     * – If the distance is shorter than or equal to the detection range of the zombie, change state to
     * FOLLOWING.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleWandering(SimulationController controller) {
        // go next position if no going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        // calculate distance and index of closest zombie
        HashMap<String, Double> closestSoldierValues = controller.getClosestSoldierValues(getPosition());
        double distance = closestSoldierValues.get("distance");
        double index = closestSoldierValues.get("index");
        if(canDetect(distance)){
            // zombie detected a soldier
            // change direction to the soldier and change state to following
            turnDirectionToPosition(controller.getSoldier((int) index).getPosition());
            setState(ZombieState.FOLLOWING);
        }
    }

    /**
     * Handles the one simulation step of the regular zombie in following state.
     *
     * Behaves like this:
     *
     * – Calculate the next position of the zombie .
     * – If the position is out of bounds, change direction to random value.
     * – If the position is not out of bounds, change zombie position to the new_position.
     * – Count the number of step zombie has been in FOLLOWING state.
     * – If the count is 4, change state to WANDERING.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleFollowing(SimulationController controller) {
        // go next position if no going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        incrementFollowCount();
        if(getFollowCount() == MAX_FOLLOW_COUNT){
            resetFollowCount();
            setState(ZombieState.WANDERING);
        }
    }


    /**
     *
     * @return the follow count
     */
    private int getFollowCount() {
        return followCount;
    }

    /**
     * Sets follow count to 0
     */
    private void resetFollowCount() {
        this.followCount = 0;
    }

    /**
     * Increments the follow count by one
     */
    private void incrementFollowCount(){
        this.followCount++;
    }
}
