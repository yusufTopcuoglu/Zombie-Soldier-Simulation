import javafx.geometry.Pos;

import java.util.HashMap;

/**
 *
 *
 */
public class Commando extends Soldier {

    public Commando(String name, Position position) { // DO NOT CHANGE PARAMETERS
        super(name, position, Constants.COMMANDO_SPEED, SoldierType.COMMANDO,
                Constants.COMMANDO_COLLISION_RANGE, Constants.COMMANDO_SHOOTING_RANGE);

    }

    /**
     * This function behaves like this ;
     *
     * – Calculate the euclidean distance to the closest zombie.
     * – If the distance is shorter than or equal to the shooting range of the soldier; change soldier
     * direction to zombie, change state to SHOOTING and return.
     * – Calculate the next position of the soldier .
     * – If the position is out of bounds, change direction to random value.
     * – If the position is not out of bounds, change soldier position to the new_position.
     * – Calculate the euclidean distance to the closest zombie.
     * – If the distance is shorter than or equal to the shooting range of the soldier; change soldier
     * direction to zombie, change state to SHOOTING.
     *
     * @param controller is the SimulationController object that the simulation plays in
     */
    @Override
    public void handleSearching(SimulationController controller) {
        // calculate distance and index of closest zombie
        HashMap<String, Double> closestZombieValues = getClosestZombieValues(controller);
        double distance = closestZombieValues.get("distance");
        double index = closestZombieValues.get("index");

        // if soldier can shoot to this distance
        if(canShoot(distance)){
            turnDirectionToPosition(controller.getZombie( (int) index).getPosition());
            setState(SoldierState.SHOOTING);
            return;
        }

        // soldier could not shoot to the closest zombie
        // move to the next position
        Position nextPosition = calculateNextPosition();
        if (nextPosition.isInsideBounds(controller)){
            // next position inside borders, change position
            setPosition(nextPosition);
            // re-calculate the distance and index of closest zombie
            closestZombieValues = getClosestZombieValues(controller);
            distance = closestZombieValues.get("distance");
            index = closestZombieValues.get("index");
        } else {
            // nex position out of borders, change direction
            setDirection(Position.generateRandomDirection(true));
        }

        // if soldier can shoot to this distance
        if(canShoot(distance)){
            turnDirectionToPosition(controller.getZombie( (int) index).getPosition());
            setState(SoldierState.SHOOTING);
        }

    }

    @Override
    public void handleAiming(SimulationController controller) {
        System.out.println("Illegal Commando State");
        throw new IllegalStateException();
    }

    @Override
    public void handleShooting(SimulationController controller) {

    }
}
