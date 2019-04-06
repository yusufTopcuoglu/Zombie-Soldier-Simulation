import java.util.HashMap;

/**
 * A type of Simulation object.
 *
 * Does not have a public constructor. Has to be instantiated
 * with the static factory methods
 *
 * Survives only for one simulation step
 *
 * @see SimulationObject
 * @see Constants
 */
public class Bullet extends SimulationObject{

    /**
     * The only constructor of the bullet.
     *
     * Calls super constructor with custom name, given position, and given speed.
     *
     * Name is the "Bullet" + the next_bullet number
     * Sets direction to a given direction
     *
     * @param position is the position to set
     * @param direction is the direction to set
     * @param speed is the speed to set
     */
    private Bullet(Position position, Position direction, double speed) {
        super("Bullet" + SimulationController.getNextBulletNo(), position, speed);
        setDirection(direction);
    }

    /**
     * Handles one simulation step actions of the bullet.
     *
     * Behaves like this :
     *
     * Divides the speed into 1.0 to calculate the N small steps
     * • For every small steps between [0, N ):
     * – Calculates the euclidean distance to the closest zombie using bullet’s and zombie’s position.
     * – If the distance is smaller than or equal to the collision distance of the zombie, sets the
     * zombie and the bullet as inactive, prints the action, and exits loop
     * – Calculates the next position of the bullet
     * – If the bullet moved out of bound, prints action and  exits loop
     *
     * Since a bullet survives only one simulation step, If the bullet does not kill a
     * zombie or does not go out of the borders, it drops to ground,
     * prints the action and sets bullet inactive.
     *
     * @param controller is the SimulationController object that simulation plays in
     */
    @Override
    public void step(SimulationController controller) {
        if (isActive()){
            for (int i = 0; i < getSpeed(); i++){
                // calculate distance and index of closest zombie
                HashMap<String, Double> closestZombieValues = controller.getClosestZombieValues(getPosition());
                double distance = closestZombieValues.get("distance");
                double index = closestZombieValues.get("index");
                if (distance <= controller.getZombie((int) index).getCollisionRange()){
                    // bullet hit the zombie
                    controller.getZombie((int) index).setInactive();
                    printCollisionZombie(controller.getZombie((int) index).getName());
                    setInactive();
                    return;
                }
                // bullet did not hit, going on its way
                // go 1 unit further along the direction
                getPosition().add(getDirection());

                if (!controller.isPositionInside(getPosition())){
                    // moved out of the bounds, stop loop
                    printGoingOut();
                    setInactive();
                    return;
                }

            }
            printCompletingStepWithoutAction();
            setInactive();
        }

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
     * Calls Bullet's constructor with given position, given direction,
     * and commando bullet speed.
     *
     * @param position is the position of the bullet
     * @param direction is the direction of the bullet
     * @return a new Bullet instance with given parameters and commando bullet speed
     */
    static Bullet factoryCommandoBullet(Position position, Position direction){
        return new Bullet(position, direction, Constants.COMMANDO_BULLET_SPEED);
    }

    /**
     * Calls Bullet's constructor with given position, given direction,
     * and sniper bullet speed.
     *
     * @param position is the position of the bullet
     * @param direction is the direction of the bullet
     * @return a new Bullet instance with given parameters and sniper bullet speed
     */
    static Bullet factorySniperBullet(Position position, Position direction){
        return new Bullet(position, direction, Constants.SNIPER_BULLET_SPEED);
    }

    /**
     * Calls Bullet's constructor with given position, given direction,
     * and regular soldier bullet speed.
     *
     * @param position is the position of the bullet
     * @param direction is the direction of the bullet
     * @return a new Bullet instance with given parameters and regular soldier bullet speed
     */
    static Bullet factoryRegularSoldierBullet(Position position, Position direction){
        return new Bullet(position, direction, Constants.REGULAR_SOLDIER_BULLET_SPEED);
    }

    /**
     * This function prints in the following format ;
     * "bullet_name" hit "zombie_name"."newline"
     * For example:
     * Bullet0 hit Zombie1.
     *
     * @param zombieName is the name of the zombie that bullet hit
     */
    private void printCollisionZombie(String zombieName){
        System.out.println(getName() + " hit " + zombieName + ".");
    }

    /**
     * This function prints in the following format ;
     * "bullet_name" moved out of bounds."newline"
     * For example:
     * Bullet0 moved out of bounds.
     */
    private void printGoingOut(){
        System.out.println(getName() + " moved out of bounds.");
    }

    /**
     * This function prints in the following format ;
     * "bullet_name" dropped to the ground at "bullet_position"."newline"
     * For example:
     * Bullet0 dropped to the ground at (12.37, 34.43).
     */
    private void printCompletingStepWithoutAction(){
        System.out.println(getName() + " dropped to the ground at " + getPosition() + ".");
    }
}
