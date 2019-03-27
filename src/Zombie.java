
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
        if ( state == ZombieState.WANDERING){
            handleWandering(controller);
        } else if ( state == ZombieState.FOLLOWING){
            handleFollowing(controller);
        } else {
            System.out.println("Illegal Zombie State");
            throw new IllegalStateException();
        }
    }

    public abstract void handleWandering(SimulationController controller);

    public abstract void handleFollowing(SimulationController controller);

    public ZombieState getState() {
        return state;
    }

    public void setState(ZombieState state) {
        this.state = state;
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
