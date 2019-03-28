import java.util.ArrayList;
import java.util.Iterator;

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
        for (Bullet bullet : bullets){
            bullet.step(this);
        }
        for (Soldier soldier : soldiers){
            soldier.step(this);
        }
        for (Zombie zombie : zombies){
            zombie.step(this);
        }
        removeInActiveObjects();

    }


    /**
     * This function removes inActive simulation objects from their containers
     */
    private void removeInActiveObjects(){
        Bullet bullet;
        Zombie zombie;
        Soldier soldier;

        Iterator<Bullet> iteratorBullet = bullets.iterator();
        while (iteratorBullet.hasNext()){
            bullet = iteratorBullet.next();
            if (! bullet.isActive())
                iteratorBullet.remove();
        }

        Iterator<Zombie> iteratorZombie = zombies.iterator();
        while (iteratorZombie.hasNext()){
            zombie =  iteratorZombie.next();
            if (! zombie.isActive())
                iteratorZombie.remove();
        }

        Iterator<Soldier> iteratorSoldier = soldiers.iterator();
        while (iteratorSoldier.hasNext()){
            soldier = iteratorSoldier.next();
            if (! soldier.isActive())
                iteratorSoldier.remove();
        }
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

    /**
     * This function calls the removeItself method of the given Simulation object with itself as a parameter.
     * May throw NullPointerException if the obj is null
     * @param obj is the SimulationObject object that we want to remove
     */
    public void removeSimulationObject(SimulationObject obj) {
        obj.removeItself(this);
    }

    /**
     * This function removes the given soldier from the soldiers ArrayList
     * May throw NullPointerException if the soldiers is null
     * @param soldier is the Soldier object that we remove from the soldiers ArrayList
     */
    public void removeSoldier(Soldier soldier){
        soldiers.remove(soldier);
    }

    /**
     * This function removes the given zombie from the zombies ArrayList
     * May throw NullPointerException if the zombies is null
     * @param zombie is the Zombie object that we remove from the zombies ArrayList
     */
    public void removeZombie(Zombie zombie){
        zombies.remove(zombie);
    }

    /**
     * This function removes the given bullet from the bullets ArrayList
     * May throw NullPointerException if the bullets is null
     * @param bullet is the Bullet object that we remove from the bullets ArrayList
     */
    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }

    /**
     * This method checks if the game is finished or not.
     * It decides it by looking if zombies or soldiers are empty
     * @return true if any of the soldiers or zombies are not present in the game, false other wise.
     */
    public boolean isFinished() {
        return isZombiesEmpty() || isSoldiersEmpty();
    }

    public Zombie getZombie(int index){
        return zombies.get(index);
    }

    public Soldier getSoldier(int index){
        return soldiers.get(index);
    }

    public Bullet getBullet(int index){
        return bullets.get(index);
    }

    public int zombieCount(){
        return zombies.size();
    }

    public int soldierCount(){
        return soldiers.size();
    }

    public int bulletCount(){
        return bullets.size();
    }
}
