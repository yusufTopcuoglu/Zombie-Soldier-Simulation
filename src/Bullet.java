public class Bullet extends SimulationObject{

    public Bullet(String name, Position position, double speed) {
        super(name, position, speed);
        // dont forget setDirection();
    }

    @Override
    public void step(SimulationController controller) {

    }

    /**
     * This function calls the addBullet method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     * @param controller is the SimulationController object that we call its addBullet method
     */
    @Override
    public void addItself(SimulationController controller) {
        controller.addBullet(this);
    }

    /**
     * This function calls the removeBullet method of the given SimulationController object with itself as a parameter
     * May throw NullPointerException if the controller is null
     * @param controller is the SimulationController object that we call its removeBullet method
     */
    @Override
    public void removeItself(SimulationController controller) {
        controller.removeBullet(this);
    }

    /**
     * This function prints in the following format ;
     * <bullet_name> hit <zombie_name>.<newline>
     * For example:
     * Bullet0 hit Zombie1.
     *
     * @param zombieName is the name of the zombie that bullet hit
     */
    public void printCollisionZombie(String zombieName){
        System.out.println(getName() + " hit " + zombieName + ".");
    }

    /**
     * This function prints in the following format ;
     * <bullet_name> moved out of bounds.<newline>
     * For example:
     * Bullet0 moved out of bounds.
     */
    public void printGoingOut(){
        System.out.println(getName() + " moved out of bounds.");
    }

    /**
     * This function prints in the following format ;
     * <bullet_name> dropped to the ground at <bullet_position>.<newline>
     * For example:
     * Bullet0 dropped to the ground at (12.37, 34.43).
     */
    public void printCompletingStepWithoutAction(){
        System.out.println(getName() + " dropped to the ground at " + getPosition() + ".");
    }
}
