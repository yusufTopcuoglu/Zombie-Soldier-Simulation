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

    }

    @Override
    public void handleFollowing(SimulationController controller) {

    }
}
