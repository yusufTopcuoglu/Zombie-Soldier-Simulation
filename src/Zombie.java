
public abstract class Zombie extends SimulationObject {

    private ZombieState state;
    private final ZombieType type;

    public Zombie(String name, Position position, double speed, ZombieType type) {
        super(name, position, speed);
        this.state = ZombieState.WANDERING;
        this.type = type;
    }

    @Override
    public void step(SimulationController controller) {
        if ( state == ZombieState.WANDERING){
            handleWandering();
        } else if ( state == ZombieState.FOLLOWING){
            handleFollowing();
        } else {
            System.out.println("Illegal Zombie State");
            throw new IllegalStateException();
        }
    }

    public abstract void handleWandering();

    public abstract void handleFollowing();

    public ZombieState getState() {
        return state;
    }

    public void setState(ZombieState state) {
        this.state = state;
    }

    public ZombieType getType() {
        return type;
    }


}
