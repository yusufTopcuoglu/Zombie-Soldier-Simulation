import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Controls the whole simulation objects, simulation's start and end.
 *
 * Holds the all simulation object (e.g. soldiers, zombies, bullets)
 *
 * Counts the fired bullets count and tells the next one.
 *
 */
public class SimulationController {
    private final double height;
    private final double width;

    private static int nextBulletNo = 0;

    private ArrayList<Soldier> soldiers;
    private ArrayList<Zombie> zombies;
    private ArrayList<Bullet> bullets;

    /**
     * The only constructor of this class.
     * Initialize the width and height with given values.
     * Sets soldiers, zombies, and bullets to empty a ArrayList
     *
     * @param width is the width of the simulation
     * @param height is the height of the simulation
     */
    public SimulationController(double width, double height) {
        this.width = width;
        this.height = height;
        soldiers = new ArrayList<>();
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    /**
     * Checks if the given position is inside the bounds.
     *
     * @param position is the position that we check
     * @return <<tt>>true<</tt>> if the given position is inside the bounds
     */
    boolean isPositionInside(Position position){
        return height >= position.getY() && width >= position.getX()
                && position.getX() >= 0 && position.getY() >= 0;
    }

    //Make sure to fill these methods for grading.

    /**
     * Calls the step function of all simulation object
     * that are present in the simulation
     */
    void stepAll() {
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
     * Finds and returns the distance and index of the closest zombie,
     * according to the given position
     *
     * @param position the base position that find closest zombie to that.
     * @return HashMap that contains index and distance of the closest zombie
     */
    HashMap<String, Double> getClosestZombieValues(Position position){
        double distance = Double.MAX_VALUE;
        double index = 0, tempDistance;

        for (int i = 0; i < zombieCount(); i++){
            if (!getZombie(i).isActive())
                continue;
            tempDistance = position.distance(getZombie(i).getPosition());
            if (tempDistance < distance){
                distance = tempDistance;
                index = i;
            }
        }
        HashMap<String, Double> values = new HashMap<>();
        values.put("distance", distance);
        values.put("index", index);
        return values;
    }

    /**
     * Finds and returns the distance and index of the closest soldier,
     * according to the given position
     *
     * @param position the base position that find closest soldier to that.
     * @return HashMap that contains index and distance of the closest soldier
     */
    HashMap<String, Double> getClosestSoldierValues(Position position){
        double distance = Double.MAX_VALUE;
        double index = 0, tempDistance;

        for (int i = 0; i < soldierCount(); i++){
            if (!getSoldier(i).isActive())
                continue;
            tempDistance = position.distance(getSoldier(i).getPosition());
            if (tempDistance < distance){
                distance = tempDistance;
                index = i;
            }
        }
        HashMap<String, Double> values = new HashMap<>();
        values.put("distance", distance);
        values.put("index", index);
        return values;
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
    void addSimulationObject(SimulationObject obj) {
        obj.addItself(this);
    }

    /**
     * This function adds the given soldier to the soldiers ArrayList
     * May throw NullPointerException if the soldiers is null
     * @param soldier is the Soldier object that we add to the soldiers ArrayList
     */
    void addSoldier(Soldier soldier){
        soldiers.add(soldier);
    }

    /**
     * This functions controls the soldier ArrayList
     * May throw NullPointerException if the soldiers is null
     * @return true if the soldiers is empty, false otherwise
     */
    private boolean isSoldiersEmpty(){
        return soldiers.isEmpty();
    }

    /**
     * This function adds the given zombie to the zombies ArrayList
     * May throw NullPointerException if the zombies is null
     * @param zombie is the Zombie object that we add to the zombies ArrayList
     */
    void addZombie(Zombie zombie){
        zombies.add(zombie);
    }

    /**
     * This functions controls the zombie ArrayList
     * May throw NullPointerException if the zombies is null
     * @return true if the zombies is empty, false otherwise
     */
    private boolean isZombiesEmpty(){
        return zombies.isEmpty();
    }

    /**
     * This function adds the given bullet to the bullets ArrayList
     * May throw NullPointerException if the bullets is null
     * @param bullet is the Bullet object that we add to the bullets ArrayList
     */
    void addBullet(Bullet bullet){
        bullets.add(bullet);
        nextBulletNo++;
    }

    /**
     * This function removes the given soldier from the soldiers ArrayList
     * May throw NullPointerException if the soldiers is null
     * @param soldier is the Soldier object that we remove from the soldiers ArrayList
     */
    void removeSoldier(Soldier soldier){
        soldiers.remove(soldier);
    }

    /**
     * This function removes the given zombie from the zombies ArrayList
     * May throw NullPointerException if the zombies is null
     * @param zombie is the Zombie object that we remove from the zombies ArrayList
     */
    void removeZombie(Zombie zombie){
        zombies.remove(zombie);
    }

    /**
     * This method checks if the game is finished or not.
     * It decides it by looking if zombies or soldiers are empty
     * @return true if any of the soldiers or zombies are not present in the game, false other wise.
     */
    boolean isFinished() {
        return isZombiesEmpty() || isSoldiersEmpty();
    }

    /**
     *
     * @param index the wanted zombie index
     * @return the zombie with the given index
     */
    Zombie getZombie(int index){
        return zombies.get(index);
    }

    /**
     *
     * @param index the wanted soldier index
     * @return the soldier with given index
     */
    Soldier getSoldier(int index){
        return soldiers.get(index);
    }

    /**
     *
     * @return the next bullet number
     */
    static int getNextBulletNo() {
        return nextBulletNo;
    }

    /**
     *
     * @return how many zombie present in simulation
     */
    private int zombieCount(){
        return zombies.size();
    }

    /**
     *
     * @return how many soldier present in simulation
     */
    private int soldierCount(){
        return soldiers.size();
    }

}
