/**
 *
 *
 */
public class SlowZombie extends Zombie {

    public SlowZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.SLOW_ZOMBIE_SPEED, ZombieType.SLOW);
    }

    @Override
    public void handleWandering() {

    }

    @Override
    public void handleFollowing() {

    }
}
