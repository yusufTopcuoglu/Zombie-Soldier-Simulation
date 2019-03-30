import java.util.HashMap;

/**
 *
 *
 */
public class SlowZombie extends Zombie {

    public SlowZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS

        super(name, position, Constants.SLOW_ZOMBIE_SPEED, ZombieType.SLOW,
                Constants.SLOW_ZOMBIE_COLLISION_RANGE, Constants.SLOW_ZOMBIE_DETECTION_RANGE);

    }

    @Override
    public void handleWandering(SimulationController controller) {
        if(canKillSoldier(controller))
            return;

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
