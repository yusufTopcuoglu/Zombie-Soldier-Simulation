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

    @Override
    public void turnDirectionToPosition(Position position){
        super.turnDirectionToPosition(position);
        printDirectionChange();
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        printPositionChange();
    }

    @Override
    public void setDirection(Position direction) {
        super.setDirection(direction);
        printDirectionChange();
    }

    public abstract void createBullet(SimulationController controller);

    public boolean canShoot(double distance){
        return shootingRange >= distance;
    }

    public abstract void handleSearching(SimulationController controller);

    public void handleAiming(SimulationController controller) {
        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = controller.getClosestZombieValues(getPosition());
        double distance = closestZombieValues.get("distance");
        double index = closestZombieValues.get("index");

        if (canShoot(distance)){
            // soldier can shoot to that distance
            turnDirectionToPosition(controller.getZombie((int) index).getPosition());
            setState(SoldierState.SHOOTING);
        } else {
            setState(SoldierState.SEARCHING);
        }
    }

    public void handleShooting(SimulationController controller) {
        createBullet(controller);

        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = controller.getClosestZombieValues(getPosition());
        double distance = closestZombieValues.get("distance");
        if (canShoot(distance)){
            // soldier can shoot to that distance, change state to aiming
            setState(SoldierState.AIMING);
        } else {
            // soldier can not shoot to that distance
            setDirection(Position.generateRandomDirection(true));
            setState(SoldierState.SEARCHING);
        }

    }

//    'StringBuilder stringBuilder' can be replaced with 'String' less... (Ctrl+F1)
//    Inspection info: Reports any usages of StringBuffer, StringBuilder or StringJoiner
//    which can be replaced with a single java.lang.String concatenation.
//    Using a String concatenation makes the code shorter and simpler.
//    This inspection only reports when the resulting concatenation is at least
//    as efficient or more efficient than the original code.

    /**
     * This function prints in the following format ;
     * "soldier_name" changed state to "state_name"."newline"
     * For example:
     * Soldier1 changed state to AIMING.
     */
    public void printStateChange(){
        System.out.println(getName() + " changed state to " + getState() + ".");
    }

    /**
     * This function prints in the following format ;
     * "soldier_name" moved to "position"."newline"
     * For example:
     * Soldier1 moved to (12.37, 34.43).
     */
    public void printPositionChange(){
        System.out.println(getName() + " moved to " + getPosition() + ".");
    }

    /**
     * This function prints in the following format ;
     * soldier_name changed direction to direction.<newline>
     * For example:
     * Soldier1 changed direction to (0.33, -0.94).
     */
    public void printDirectionChange(){
        System.out.println(getName() + " changed direction to " + getDirection() + ".");
    }

    /**
     * This function prints in the following format ;
     * "soldier_name fired "bullet_name to direction "direction"."newline"
     * For example:
     * Soldier1 fired Bullet0 to direction (0.51, 0.86).
     *
     * @param bulletName is the name of fired bullet
     */
    public void printFiringBullet(String bulletName){
        System.out.println(getName() + " fired " + bulletName + " to direction " + getDirection() + ".");
    }


    public SoldierState getState() {
        return state;
    }

    public void setState(SoldierState state) {
        this.state = state;
        printStateChange();
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
