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

    }

    @Override
    public void handleFollowing(SimulationController controller) {

    }
}
