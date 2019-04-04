import java.util.HashMap;

public class Bullet extends SimulationObject{

    Bullet(Position position, Position direction, double speed) {
        super("Bullet" + SimulationController.getNextBulletNo(), position, speed);
        setDirection(direction);
    }

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
                    controller.getZombie((int) index).setActive(false);
                    printCollisionZombie(controller.getZombie((int) index).getName());
                    setActive(false);
                    return;
                }
                // bullet did not hit, going on its way
                // go 1 unit further along the direction
                getPosition().add(getDirection());

                if (controller.isPositionInside(getPosition())){
                    // moved out of the bounds, stop loop
                    printGoingOut();
                    setActive(false);
                    return;
                }

            }
            printCompletingStepWithoutAction();
            setActive(false);
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

    static Bullet factoryCommandoBullet(Position position, Position direction){
        return new Bullet(position, direction, Constants.COMMANDO_BULLET_SPEED);
    }

    static Bullet factorySniperBullet(Position position, Position direction){
        return new Bullet(position, direction, Constants.SNIPER_BULLET_SPEED);
    }

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
    public void printCollisionZombie(String zombieName){
        System.out.println(getName() + " hit " + zombieName + ".");
    }

    /**
     * This function prints in the following format ;
     * "bullet_name" moved out of bounds."newline"
     * For example:
     * Bullet0 moved out of bounds.
     */
    public void printGoingOut(){
        System.out.println(getName() + " moved out of bounds.");
    }

    /**
     * This function prints in the following format ;
     * "bullet_name" dropped to the ground at "bullet_position"."newline"
     * For example:
     * Bullet0 dropped to the ground at (12.37, 34.43).
     */
    public void printCompletingStepWithoutAction(){
        System.out.println(getName() + " dropped to the ground at " + getPosition() + ".");
    }
}
