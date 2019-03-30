import java.util.HashMap;

/**
 *
 *
 */
public class FastZombie extends Zombie {

    public FastZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.FAST_ZOMBIE_SPEED, ZombieType.FAST,
                Constants.FAST_ZOMBIE_COLLISION_RANGE, Constants.FAST_ZOMBIE_DETECTION_RANGE);
    }


    @Override
    public void handleWandering(SimulationController controller) {
        if(canKillSoldier(controller))
            return;

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

    @Override
    public void handleFollowing(SimulationController controller) {
        if(canKillSoldier(controller))
            return;

        // go next position if not going out of borders
        // change direction otherwise
        goNextOrChangeDirection(controller);

        setState(ZombieState.WANDERING);

    }
}
