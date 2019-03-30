import java.util.HashMap;

public abstract class Zombie extends SimulationObject {

    private ZombieState state;
    private final ZombieType type;
    private final double collisionRange;
    private final double detectionRange;

    public Zombie(String name, Position position, double speed, ZombieType type,
                  double collisionRange, double detectionRange) {
        super(name, position, speed);
        this.state = ZombieState.WANDERING;
        this.type = type;
        this.collisionRange = collisionRange;
        this.detectionRange = detectionRange;
    }

    @Override
    public void step(SimulationController controller) {
        if (isActive()){
            if ( state == ZombieState.WANDERING){
                handleWandering(controller);
            } else if ( state == ZombieState.FOLLOWING){
                handleFollowing(controller);
            } else {
                System.out.println("Illegal Zombie State");
                throw new IllegalStateException();
            }
        }
    }

    public abstract void handleWandering(SimulationController controller);

    public abstract void handleFollowing(SimulationController controller);

    public boolean canDetect(double distance){
        return getDetectionRange() <= distance;
    }

    /**
     * This function calls the addZombie method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     * @param controller is the SimulationController object that we call its addZombie method
     */
    @Override
    public void addItself(SimulationController controller) {
        controller.addZombie(this);
    }

    /**
     * This function calls the removeZombie method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     * @param controller is the SimulationController object that we call its removeZombie method
     */
    @Override
    public void removeItself(SimulationController controller){
        controller.removeZombie(this);
    }

    /**
     * This function prints in the following format ;
     * <zombie_name> changed state to <state_name>.<newline>
     * For example:
     * Zombie1 changed state to WANDERING.
     */
    public void printStateChange(){
        System.out.println(getName() + " changed state to " + getState() + ".");
    }

    /**
     * This function prints in the following format ;
     * <zombie_name> moved to <position>.<newline>
     * For example:
     * Zombie1 moved to (12.37, 34.43).
     */
    public void printPositionChange(){
        System.out.print(getName() + " moved to " + getPosition() + ".");
    }

    /**
     * This function prints in the following format ;
     * <zombie_name> changed direction to <direction>.<newline>
     * For example:
     * Zombie1 changed direction to (0.33, -0.94).
     */
    public void printDirectionChange(){
        System.out.print(getName() + " changed direction to " + getDirection() + ".");
    }

    /**
     * This function prints in the following format ;
     * <zombie_name> killed <soldier_name>.<newline>
     * For example:
     * Zombie1 killed Soldier1.
     *
     * @param soldierName is the name of the killed soldier
     */
    public void printKillingSoldier(String soldierName){
        System.out.print(getName() + " killed " + soldierName + ".");
    }

    public boolean canKillSoldier(SimulationController controller){
        // calculate distance and index of closest soldier
        HashMap<String, Double> closestSoldierValues = controller.getClosestSoldierValues(getPosition());
        double distance = closestSoldierValues.get("distance");
        double index = closestSoldierValues.get("index");
        if (distance <= getCollisionRange() + controller.getSoldier((int) index).getCollisionRange()){
            // can kill the soldier
            controller.getSoldier((int) index).setActive(false);
            printKillingSoldier(controller.getSoldier((int) index).getName());
            return true;
        }
        return false;
    }

    public ZombieState getState() {
        return state;
    }

    public void setState(ZombieState state) {
        this.state = state;
        printStateChange();
    }

    @Override
    public void setDirection(Position direction) {
        super.setDirection(direction);
        printDirectionChange();
    }

    @Override
    public void setPosition(Position position) {
        super.setDirection(position);
        printPositionChange();
    }

    public ZombieType getType() {
        return type;
    }

    public double getCollisionRange() {
        return collisionRange;
    }

    public double getDetectionRange() {
        return detectionRange;
    }
}
