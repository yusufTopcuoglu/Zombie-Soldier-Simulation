
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 *
 */
public class SimulationRunner {

    public static void main(String[] args) {
        SimulationController simulation = new SimulationController(50, 50);

        
        simulation.addSimulationObject(new RegularSoldier("Soldier1", new Position(0, 0)));
        simulation.addSimulationObject(new RegularZombie("Zombie1", new Position(50, 50)));


        while (!simulation.isFinished()) {
            simulation.stepAll();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulationRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
