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

    public void addSimulationObject(SimulationObject obj) {
        obj.addItself(this);
    }

    public void addSoldier(Soldier soldier){
        soldiers.add(soldier);
    }

    public boolean isSoldiersEmpty(){
        return soldiers.isEmpty();
    }

    public void addZombie(Zombie zombie){
        zombies.add(zombie);
    }

    public boolean isZombiesEmpty(){
        return zombies.isEmpty();
    }

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public boolean isBulletsEmpty(){
        return bullets.isEmpty();
    }

    public void removeSimulationObject(SimulationObject obj) {

    }

    public boolean isFinished() {
        return true;
    }
}
