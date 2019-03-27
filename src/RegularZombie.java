/**
 *
 * 
 */
public class RegularZombie extends Zombie{

    public RegularZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.REGULAR_ZOMBIE_SPEED, ZombieType.REGULAR,
                Constants.REGULAR_ZOMBIE_COLLISION_RANGE, Constants.REGULAR_ZOMBIE_DETECTION_RANGE);
    }

    @Override
    public void handleWandering() {

    }

    @Override
    public void handleFollowing() {

    }
}
