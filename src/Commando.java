/**
 *
 *
 */
public class Commando extends Soldier {

    public Commando(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.COMMANDO_SPEED, SoldierType.COMMANDO,
                Constants.COMMANDO_COLLISION_RANGE, Constants.COMMANDO_SHOOTING_RANGE);

    }

    @Override
    public void handleSearching() {

    }

    @Override
    public void handleAiming() {
        System.out.println("Illegal Commando State");
        throw new IllegalStateException();
    }

    @Override
    public void handleShooting() {

    }
}
