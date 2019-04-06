import java.util.HashMap;

/**
 * A type of simulation object. Abilities of a soldier are
 * moving, changing direction and firing a bullet.
 *
 * Additionally, it has 3 attributes which are state, collision range, and
 * shooting range.
 *
 * Provides print methods for the certain actions.
 *
 * @see SimulationObject
 * @see RegularSoldier
 * @see Commando
 * @see Sniper
 *
 */
public abstract class Soldier extends SimulationObject {

    private SoldierState state;
    private final double collisionRange;
    private final double shootingRange;

    /**
     * This is the only constructor of this class.
     *
     * Calls the constructor of the super class with name, position, and
     * speed. It sets the collision range and shooting range to the given parameters.
     * Initializes the state as Searching for all instances.
     *
     * @param name is the name of the soldier
     * @param position is the position of the soldier
     * @param speed is the speed of the soldier
     * @param collisionRange is the collisionRange of the soldier
     * @param shootingRange is the shootingRange of the soldier
     */
    public Soldier(String name, Position position, double speed,
                   double collisionRange, double shootingRange) {
        super(name, position, speed);
        this.state = SoldierState.SEARCHING;
        this.collisionRange = collisionRange;
        this.shootingRange = shootingRange;
    }

    /**
     * Handles the one simulation step of the Soldier.
     * It decides the action by checking the state of the soldier.
     *
     * @param controller is the SimulationController object that simulation plays in
     */
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
     * Calls the addSoldier method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     *
     * @param controller is the SimulationController object that we call its addSoldier method
     */
    @Override
    public void addItself(SimulationController controller){
        controller.addSoldier(this);
    }

    /**
     * Calls the removeSoldier method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     *
     * @param controller is the SimulationController object that we call its removeSoldier method
     */
    @Override
    public void removeItself(SimulationController controller){
        controller.removeSoldier(this);
    }

    /**
     * It calls the same function of the super class and prints
     * the changes.
     * @param position is the position that object will turn
     */
    @Override
    public void turnDirectionToPosition(Position position){
        super.turnDirectionToPosition(position);
        printDirectionChange();
    }

    /**
     * Calls the same function of the super class and prints
     * the changes.
     * @param position is the position that object will turn
     */
    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        printPositionChange();
    }

    /**
     * Calls the same function of the super class and prints
     * the changes.
     * @param direction is the direction that object will turn
     */
    @Override
    public void setDirection(Position direction) {
        super.setDirection(direction);
        printDirectionChange();
    }

    /**
     * This is an abstract class and must be implemented
     * Creates and adds a bullets to the controller.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    public abstract void createBullet(SimulationController controller);

    /**
     * This function decides whether soldier can shoot to
     * that distance by checking the shooting range
     *
     * @param distance is the double value that we check if we can shoot
     * @return <tt>true</tt> if shooting range is bigger than or equal to the distance
     */
    boolean canShoot(double distance){
        return shootingRange >= distance;
    }

    /**
     * This is an abstract function and must be implemented
     * Handles the actions of the soldier in searching state
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    public abstract void handleSearching(SimulationController controller);

    /**
     * Handles the actions of the soldier in aiming state.
     *
     * The behaviour is the following ;
     * – Calculate the euclidean distance to the closest zombie.
     * – If the distance is shorter than or equal to the shooting range of the soldier, change soldier
     * direction to zombie and change state to SHOOTING.
     * – If not, change state to SEARCHING
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
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

    /**
     * Handles the actions of the soldier in shooting state.
     *
     * The behaviour is the following ;
     * – Create a bullet. Bullet’s position and direction is same as soldier’s.
     * – Calculate the euclidean distance to the closest zombie.
     * – If the distance is shorter than or equal to the shooting range of the soldier, change state to
     * AIMING.
     * – If not, randomly change soldier direction and change state to SEARCHING.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
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
            setDirection(Position.generateRandomDirection());
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
    private void printStateChange(){
        System.out.println(getName() + " changed state to " + getState() + ".");
    }

    /**
     * This function prints in the following format ;
     * "soldier_name" moved to "position"."newline"
     * For example:
     * Soldier1 moved to (12.37, 34.43).
     */
    private void printPositionChange(){
        System.out.println(getName() + " moved to " + getPosition() + ".");
    }

    /**
     * This function prints in the following format ;
     * soldier_name changed direction to direction.<newline>
     * For example:
     * Soldier1 changed direction to (0.33, -0.94).
     */
    private void printDirectionChange(){
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
     void printFiringBullet(String bulletName){
        System.out.println(getName() + " fired " + bulletName + " to direction " + getDirection() + ".");
    }

    /// BELOWS ARE GETTER AND SETTER ///

    /**
     *
     * @return the state of the soldier.
     */
    public SoldierState getState() {
        return state;
    }

    /**
     *  Sets the state to the given state and prints the action.
     *
     * @param state is the Zombie state that object's state will be set
     */
    public void setState(SoldierState state) {
        this.state = state;
        printStateChange();
    }

    /**
     *
     * @return the collision range
     */
    double getCollisionRange() {
        return collisionRange;
    }

}
