import java.util.ArrayList;

/**
 *
 *
 */
public class SimulationController {
    private final double height;
    private final double width;

    private ArrayList<Soldier> soldiers;
    private ArrayList<Zombie> zombies;
    private ArrayList<Bullet> bullets;

    public SimulationController(double width, double height) {
        this.width = width;
        this.height = height;
        soldiers = new ArrayList<>();
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    //Make sure to fill these methods for grading.
    public void stepAll() {

    }

    /**
     * This function calls the addItself method of the given Simulation object with itself as a parameter.
     * May throw NullPointerException if the obj is null
     * @param obj is the SimulationObject object that we want to add
     */
    public void addSimulationObject(SimulationObject obj) {
        obj.addItself(this);
    }

    /**
     * This function adds the given soldier to the soldiers ArrayList
     * May throw NullPointerException if the soldiers is null
     * @param soldier is the Soldier object that we add to the soldiers ArrayList
     */
    public void addSoldier(Soldier soldier){
        soldiers.add(soldier);
    }

    /**
     * This functions controls the soldier ArrayList
     * May throw NullPointerException if the soldiers is null
     * @return true if the soldiers is empty, false otherwise
     */
    public boolean isSoldiersEmpty(){
        return soldiers.isEmpty();
    }

    /**
     * This function adds the given zombie to the zombies ArrayList
     * May throw NullPointerException if the zombies is null
     * @param zombie is the Zombie object that we add to the zombies ArrayList
     */
    public void addZombie(Zombie zombie){
        zombies.add(zombie);
    }

    /**
     * This functions controls the zombie ArrayList
     * May throw NullPointerException if the zombies is null
     * @return true if the zombies is empty, false otherwise
     */
    public boolean isZombiesEmpty(){
        return zombies.isEmpty();
    }

    /**
     * This function adds the given bullet to the bullets ArrayList
     * May throw NullPointerException if the bullets is null
     * @param bullet is the Bullet object that we add to the bullets ArrayList
     */
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    /**
     * This functions controls the bullet ArrayList
     * May throw NullPointerException if the bullets is null
     * @return true if the bullets is empty, false otherwise
     */
    public boolean isBulletsEmpty(){
        return bullets.isEmpty();
    }

    public void removeSimulationObject(SimulationObject obj) {

    }

    public boolean isFinished() {
        return true;
    }
}
