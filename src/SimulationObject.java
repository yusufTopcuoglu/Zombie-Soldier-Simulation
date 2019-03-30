/**
 *
 *
 */
public abstract class SimulationObject {
    private final String name;
    private Position position;
    private Position direction;
    private final double speed;
    private boolean active;

    public SimulationObject(String name, Position position, double speed) {
        this.name = name;
        this.position = position;
        this.speed = speed;
        this.direction = Position.generateRandomDirection(true);
        this.active = true;
    }

    public Position calculateNextPosition(){
        return position.getSummedPosition(direction.getMultipliedPosition(speed));
    }

    public void turnDirectionToPosition(Position position){
        direction = position.getSubtractedPosition(direction);
        direction.normalize();
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getDirection() {
        return direction;
    }

    public void setDirection(Position direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void goNextOrChangeDirection(SimulationController controller){
        // calculate the next position
        Position nextPosition = calculateNextPosition();
        if ( controller.isPositionInside(nextPosition) ){
            // the nextPosition is inside the borders of controller
            setPosition(nextPosition);
        } else {
            // the nextPosition is out of borders of controller
            // change direction randomly
            setDirection(Position.generateRandomDirection(true));
        }

    }

    public abstract void step(SimulationController controller);

    public abstract void addItself(SimulationController controller);

    public abstract void removeItself(SimulationController controller);
}
