public class Bullet extends SimulationObject{

    public Bullet(String name, Position position, double speed) {
        super(name, position, speed);
    }

    @Override
    public void step(SimulationController controller) {

    }

    @Override
    public void addItself(SimulationController controller) {
        controller.addBullet(this);
    }
}
