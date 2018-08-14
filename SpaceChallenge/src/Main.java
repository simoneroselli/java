import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws java.io.IOException {

        ArrayList<String> msgTotalCosts = new ArrayList<>();
        List<String> phases = Arrays.asList("phase-1.txt", "phase-2.txt");

        Simulation simulation = new Simulation();

        int u1simulationCost = 0;
        int u2simulationCost = 0;

        for (String phase : phases) {
            System.out.println("-- Loading U1 items for " + phase + " ..");
            ArrayList<Rocket> loadU1 = simulation.load(
                    phase,
                    10000,
                    18000);

            u1simulationCost = simulation.runSimulation(loadU1,
                    18000,
                    100,
                    5,
                    1
            );
        }

        msgTotalCosts.add("TOTAL COST U1 SIMULATION = $" + u1simulationCost + " 000 000");

        for (String phase : phases) {
            System.out.println("-- Loading U2 items for " + phase + " ..");
            ArrayList<Rocket> loadU2 = simulation.load(
                    phase,
                    18000,
                    29000);

            u2simulationCost = simulation.runSimulation(loadU2,
                    11000,
                    120,
                    4,
                    8
            );
        }

        msgTotalCosts.add("TOTAL COST U2 SIMULATION = $" + u2simulationCost + " 000 000");

        System.out.println("-----------------------------------");

        for (String s: msgTotalCosts) {
                System.out.println(s);
        }


    }
}
