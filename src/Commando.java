/**
 *
 *
 */
public class Commando extends Soldier {

    public Commando(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.COMMANDO_SPEED, SoldierType.COMMANDO);

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
