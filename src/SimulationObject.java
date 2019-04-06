/**
 * The root abstract class for the whole simulation objects.
 * A simulation object represent an object that can be present in the simulation.
 *
 * The class has only one constructor with 3 parameter. The direction of all
 * kind of objects is randomly generated at the constructor. All objects are
 * created as active.
 *
 * @see Soldier
 * @see Commando
 * @see RegularSoldier
 * @see Sniper
 * @see Zombie
 * @see FastZombie
 * @see SlowZombie
 * @see RegularZombie
 * @see Bullet
 *
 */

public abstract class SimulationObject {
    private final String name;
    private Position position;
    private Position direction;
    private final double speed;
    private boolean active;

    /**
     * This is the only constructor of this class. It initialize
     * the name, position and the speed of the simulation object with given
     * parameters. Sets the direction of the object to a random value and
     * sets the object as active.
     *
     * @param name is the name of the SimulationObject
     * @param position is the position of the SimulationObject
     * @param speed is the speed of the SimulationObject
     */
    public SimulationObject(String name, Position position, double speed) {
        this.name = name;
        this.position = position;
        this.speed = speed;
        this.direction = Position.generateRandomDirection();
        this.active = true;
    }

    /**
     * This function calculates the next position of the object
     * by moving the object towards its direction with the amount of
     * its speed.
     *
     * The math behind the logic is the following ;
     * new_position = current_position + direction * speed
     *
     * Note that this function does not modify the current values of
     * the position or direction. It returns a newly created Position.
     *
     * @return the calculated newPosition
     */
    private Position calculateNextPosition(){
        return position.getSummedPosition(direction.getMultipliedPosition(speed));
    }

    /**
     * This functions turns the object to a given position
     *
     * @param position is the position that object will turn
     */
    public void turnDirectionToPosition(Position position){
        direction = position.getSubtractedPosition(getPosition());
        direction.normalize();
    }

    /**
     * This function changes either the position or the direction of the object.
     * To decide that, it first calculates the next position. Then if the next position
     * inside the borders, then changes object's position to new position. However,
     * if the next position is out of orders, then changes object's direction to a
     * randomly generated direction.
     *
     * @param controller is the SimulationController object that simulation plays in
     */
    void goNextOrChangeDirection(SimulationController controller){
        // calculate the next position
        Position nextPosition = calculateNextPosition();
        if ( controller.isPositionInside(nextPosition) ){
            // the nextPosition is inside the borders of controller
            setPosition(nextPosition);
        } else {
            // the nextPosition is out of borders of controller
            // change direction randomly
            setDirection(Position.generateRandomDirection());
        }

    }

    /**
     * This is an abstract function and should be implemented in sub classes.
     * Handles the actions of the object in one simulation step.
     *
     * @param controller is the SimulationController object that simulation plays in
     */
    public abstract void step(SimulationController controller);

    /**
     * This is an abstract function and should be implemented in sub classes.
     * Handles the addition of the object to the simulation
     *
     * @param controller is the SimulationController object that simulation plays in
     *
     */
    public abstract void addItself(SimulationController controller);

    /**
     * This is an abstract function and should be implemented in sub classes.
     * Handles the remove operation of the object from the simulation
     *
     * @param controller is the SimulationController object that simulation plays in.
     *
     */
    public abstract void removeItself(SimulationController controller);

    /// BELOWS ARE GETTER AND SETTER ///

    /**
     *
     * @return name of the simulation object
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return position of the simulation object
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position to a given position
     *
     * @param position is position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     *
     * @return the direction of the simulation object
     */
    public Position getDirection() {
        return direction;
    }

    /**
     * Sets the direction to a given direction
     *
     * @param direction to direction to set
     */
    public void setDirection(Position direction) {
        this.direction = direction;
    }

    /**
     *
     * @return speed of the simulation object
     */
    public double getSpeed() {
        return speed;
    }

    /**
     *
     * @return active of the simulation object
     */
    boolean isActive() {
        return active;
    }

    /**
     * Sets inactive the object
     *     */
    void setInactive() {
        this.active = false;
    }

}
