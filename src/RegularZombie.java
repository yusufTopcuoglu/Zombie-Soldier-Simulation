/**
 *
 * 
 */
public class RegularZombie extends Zombie{

    public RegularZombie(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.REGULAR_ZOMBIE_SPEED, ZombieType.REGULAR);
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
