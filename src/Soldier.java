
public abstract class Soldier extends SimulationObject {

    private SoldierState state;
    private final SoldierType type;

    public Soldier(String name, Position position, double speed, SoldierType type) {
        super(name, position, speed);
        this.state = SoldierState.SEARCHING;
        this.type = type;
    }

    @Override
    public void step(SimulationController controller) {
        if ( state == SoldierState.SEARCHING){
            handleSearching();
        } else if ( state == SoldierState.AIMING){
            handleAiming();
        } else if ( state == SoldierState.SHOOTING){
            handleShooting();
        } else {
            System.out.println("Illegal Soldier State");
            throw new IllegalStateException();
        }
    }

    public abstract void handleSearching();

    public abstract void handleAiming();

    public abstract void handleShooting();

    public SoldierState getState() {
        return state;
    }

    public void setState(SoldierState state) {
        this.state = state;
    }

    public SoldierType getType() {
        return type;
    }

}
