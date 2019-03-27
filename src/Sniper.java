/**
 *
 *
 */
public class Sniper extends Soldier{

    public Sniper(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.SNIPER_SPEED, SoldierType.SNIPER,
                Constants.SNIPER_COLLISION_RANGE, Constants.SNIPER_SHOOTING_RANGE);
    }

    @Override
    public void handleSearching(SimulationController controller) {

    }

    @Override
    public void handleAiming(SimulationController controller) {

    }

    @Override
    public void handleShooting(SimulationController controller) {

    }
}
