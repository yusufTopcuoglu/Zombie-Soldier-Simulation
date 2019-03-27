/**
 *
 *
 */
public class RegularSoldier extends Soldier {

    public RegularSoldier(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.REGULAR_SOLDIER_SPEED, SoldierType.REGULAR,
                Constants.REGULAR_SOLDIER_COLLISION_RANGE, Constants.REGULAR_SOLDIER_SHOOTING_RANGE);
    }


    @Override
    public void handleSearching() {

    }

    @Override
    public void handleAiming() {

    }

    @Override
    public void handleShooting() {

    }
}
