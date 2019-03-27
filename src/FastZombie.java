/**
 *
 *
 */
public class FastZombie extends Zombie {

    public FastZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.FAST_ZOMBIE_SPEED, ZombieType.FAST);
    }

    @Override
    public void step(SimulationController controller) {

    }

    @Override
    public void handleWandering() {

    }

    @Override
    public void handleFollowing() {

    }
}
