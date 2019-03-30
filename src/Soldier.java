import java.util.HashMap;

public abstract class Soldier extends SimulationObject {

    private SoldierState state;
    private final SoldierType type;
    private final double collisionRange;
    private final double shootingRange;

    public Soldier(String name, Position position, double speed, SoldierType type,
                   double collisionRange, double shootingRange) {
        super(name, position, speed);
        this.state = SoldierState.SEARCHING;
        this.type = type;
        this.collisionRange = collisionRange;
        this.shootingRange = shootingRange;
    }

    @Override
    public void step(SimulationController controller) {
        if (isActive()){
            if ( state == SoldierState.SEARCHING){
                handleSearching(controller);
            } else if ( state == SoldierState.AIMING){
                handleAiming(controller);
            } else if ( state == SoldierState.SHOOTING){
                handleShooting(controller);
            } else {
                System.out.println("Illegal Soldier State");
                throw new IllegalStateException();
            }
        }
    }

    /**
     * This function calls the addSoldier method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     * @param controller is the SimulationController object that we call its addSoldier method
     */
    @Override
    public void addItself(SimulationController controller){
        controller.addSoldier(this);
    }

    /**
     * This function calls the removeSoldier method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     * @param controller is the SimulationController object that we call its removeSoldier method
     */
    @Override
    public void removeItself(SimulationController controller){
        controller.removeSoldier(this);
    }


    public boolean canShoot(double distance){
        return shootingRange >= distance;
    }

    public abstract void handleSearching(SimulationController controller);

    public abstract void handleAiming(SimulationController controller);

    public abstract void handleShooting(SimulationController controller);

    public SoldierState getState() {
        return state;
    }

    public void setState(SoldierState state) {
        this.state = state;
    }

    public SoldierType getType() {
        return type;
    }

    public double getCollisionRange() {
        return collisionRange;
    }

    public double getShootingRange() {
        return shootingRange;
    }
}
