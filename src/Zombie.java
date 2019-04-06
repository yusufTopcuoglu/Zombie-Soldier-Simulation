import java.util.HashMap;

/**
 * A type of simulation object. Abilities of a zombie are
 * moving, changing direction, and killing a soldier.
 *
 * Additionally, it has 3 attributes which are state, collision range, and
 * detection range.
 *
 * Provides print methods for the certain actions.
 *
 * @see SimulationObject
 * @see FastZombie
 * @see SlowZombie
 * @see RegularZombie
 */
public abstract class Zombie extends SimulationObject {

    private ZombieState state;
    private final double collisionRange;
    private final double detectionRange;

    /**
     * This is the only constructor of this class.
     *
     * Calls super class constructor with given name,, position, and speed.
     * Sets state, collision range, and detection range to given value.
     *
     * @param name is the name of the zombie.
     * @param position is the position of the zombie.
     * @param speed is the speed of the zombie.
     * @param collisionRange is the collision range of the zombie.
     * @param detectionRange is the detection range of the zombie.
     */
    public Zombie(String name, Position position, double speed, double collisionRange, double detectionRange) {
        super(name, position, speed);
        this.state = ZombieState.WANDERING;
        this.collisionRange = collisionRange;
        this.detectionRange = detectionRange;
    }

    /**
     * Handles the one simulation step of the zombie.
     * It kills a soldier if it can. If it kills a soldier, it does not
     * perform any further action. Else, decides the action
     * by checking the state of the zombie.
     *
     * @param controller is the SimulationController object that simulation plays in
     */
    @Override
    public void step(SimulationController controller) {
        if (isActive()){
            if(canKillSoldier(controller))
                return;

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

    /**
     * This is an abstract function and must be implemented
     * Handles the actions of the zombie in wandering state
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    public abstract void handleWandering(SimulationController controller);

    /**
     * This is an abstract function and must be implemented
     * Handles the actions of the zombie in following state
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    public abstract void handleFollowing(SimulationController controller);

    /**
     * Checks if the zombie can detect the given distance by controlling the
     * detection range.
     *
     * @param distance is tje double value that we check if the zombie can detect
     * @return <tt>true</tt> if the detection range of the zombie is bigger then or equal to the distane
     */
    boolean canDetect(double distance){
        return getDetectionRange() >= distance;
    }

    /**
     * Calls the addZombie method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     * @param controller is the SimulationController object that we call its addZombie method
     */
    @Override
    public void addItself(SimulationController controller) {
        controller.addZombie(this);
    }

    /**
     * Calls the removeZombie method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     * @param controller is the SimulationController object that we call its removeZombie method
     */
    @Override
    public void removeItself(SimulationController controller){
        controller.removeZombie(this);
    }

    /**
     * Prints in the following format ;
     * "zombie_name" changed state to "state_name"."newline"
     * For example:
     * Zombie1 changed state to WANDERING.
     */
    private void printStateChange(){
        System.out.println(getName() + " changed state to " + getState() + ".");
    }

    /**
     * Prints in the following format ;
     * "zombie_name" moved to "position"."newline"
     * For example:
     * Zombie1 moved to (12.37, 34.43).
     */
    private void printPositionChange(){
        System.out.println(getName() + " moved to " + getPosition() + ".");
    }

    /**
     * Prints in the following format ;
     * "zombie_name" changed direction to "direction"."newline"
     * For example:
     * Zombie1 changed direction to (0.33, -0.94).
     */
    private void printDirectionChange(){
        System.out.println(getName() + " changed direction to " + getDirection() + ".");
    }

    /**
     * Prints in the following format ;
     * "zombie_name" killed "soldier_name"."newline"
     * For example:
     * Zombie1 killed Soldier1.
     *
     * @param soldierName is the name of the killed soldier
     */
    private void printKillingSoldier(String soldierName){
        System.out.println(getName() + " killed " + soldierName + ".");
    }

    /**
     * Calculates the closest soldier's distance and position.
     * if the soldier is closer then the sum of collision ranges of soldier and zombie,
     * it kills the soldier by setting the soldier as inactive and prints the action.
     *
     * @param controller is the Simulation controller object that simulation plays in
     * @return <tt>true</tt> if zombie killed a soldier.
     */
    private boolean canKillSoldier(SimulationController controller){
        // calculate distance and index of closest soldier
        HashMap<String, Double> closestSoldierValues = controller.getClosestSoldierValues(getPosition());
        double distance = closestSoldierValues.get("distance");
        double index = closestSoldierValues.get("index");
        if (distance <= getCollisionRange() + controller.getSoldier((int) index).getCollisionRange()){
            // can kill the soldier
            controller.getSoldier((int) index).setInactive();
            printKillingSoldier(controller.getSoldier((int) index).getName());
            return true;
        }
        return false;
    }

    /**
     *
     * @return the state of the zombie
     */
    public ZombieState getState() {
        return state;
    }

    /**
     * Sets the state to the given state and prints the action.
     *
     * @param state is the Zombie state that object's state will be set
     */
    public void setState(ZombieState state) {
        this.state = state;
        printStateChange();
    }

    /**
     * Sets the direction to the given direction and prints the action
     *
     * @param direction is the direction that object's direction will be set
     */
    @Override
    public void setDirection(Position direction) {
        super.setDirection(direction);
        printDirectionChange();
    }

    /**
     * Sets the position to the given position and prints the action
     *
     * @param position is the position that object's position will be set
     */
    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        printPositionChange();
    }

    /**
     *
     * @return the collision range of the zombie
     */
    double getCollisionRange() {
        return collisionRange;
    }

    /**
     *
     * @return the detection range of the zombie
     */
    private double getDetectionRange() {
        return detectionRange;
    }
}
