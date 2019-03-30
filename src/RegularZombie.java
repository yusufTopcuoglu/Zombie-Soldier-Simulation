import java.util.HashMap;

/**
 *
 * 
 */
public class RegularZombie extends Zombie{
    private int followCount;

    public RegularZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.REGULAR_ZOMBIE_SPEED, ZombieType.REGULAR,
                Constants.REGULAR_ZOMBIE_COLLISION_RANGE, Constants.REGULAR_ZOMBIE_DETECTION_RANGE);
        followCount = 0;
    }

    @Override
    public void handleWandering(SimulationController controller) {
        if(canKillSoldier(controller))
            return;
        // go next position if no going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        // calculate distance and index of closest zombie
        HashMap<String, Double> closestSoldierValues = controller.getClosestSoldierValues(getPosition());
        double distance = closestSoldierValues.get("distance");
        if(canDetect(distance)){
            setState(ZombieState.FOLLOWING);
        }
    }

    @Override
    public void handleFollowing(SimulationController controller) {
        if(canKillSoldier(controller))
            return;
        // go next position if no going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        incrementFollowCount();
        if(getFollowCount() == 4){
            resetFollowCount();
            setState(ZombieState.WANDERING);
        }
    }


    private int getFollowCount() {
        return followCount;
    }

    private void resetFollowCount() {
        this.followCount = 0;
    }

    private void incrementFollowCount(){
        this.followCount++;
    }
}
