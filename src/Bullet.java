public class Bullet extends SimulationObject{

    public Bullet(String name, Position position, double speed) {
        super(name, position, speed);
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
}
