import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import static java.lang.Integer.parseInt;

public class Simulation {

    List<Integer> fleetCosts = new ArrayList<Integer>();

    public List<Item> loadItems(String rocketPhase) throws IOException {

        FileInputStream phaseFile = new FileInputStream(rocketPhase);
        BufferedReader item_list = new BufferedReader(new InputStreamReader(phaseFile));

        List<Item> items = new ArrayList<>();

        try {
            while (true) {
                String line = item_list.readLine();
                if (line == null) {
                    break;
                }
                // Parse the item list and create Item objects
                String[] split = line.split("=");
                String itemName = split[0];
                int itemWeight = parseInt(split[1]);
                Item item = new Item(itemName, itemWeight);

                items.add(item);

            }
        } finally {
            item_list.close();
        }

        return items;
    }

    Rocket rocket = new Rocket(0);

    // Create rockets_list; fill it up with rockets
    public ArrayList<Rocket> load(
            String rocketPhase,
            int rocketWeight,
            int rocketMaxWeight) throws IOException {

        ArrayList<Rocket> rocketsFleet = new ArrayList<Rocket>();
        List<Item> itemsToLoad = loadItems(rocketPhase);

        //itemsToLoad.sort(Comparator.comparingDouble(Item::getWeight));
        ListIterator<Item> item = itemsToLoad.listIterator();

        int rocketCurrentWeight = 0;
        int rocketMaxLoad = rocketMaxWeight - rocketWeight;

        while (item.hasNext()) {

            Item itemObject = item.next();

            if (rocket.canCarry(itemObject, rocketMaxLoad, rocketCurrentWeight)) {

                rocketCurrentWeight = rocket.carry(itemObject, rocketCurrentWeight);
                item.remove();
            } else {
                String rocketName = UUID.randomUUID().toString().substring(0,6);
                Rocket rocket = new Rocket(0);

                rocket.name = "rocket-" + rocketName;
                rocket.carried = rocketCurrentWeight;
                rocketsFleet.add(rocket);
                item.remove();

                rocketCurrentWeight = itemObject.getWeight();
            }
        }
        System.out.println("-- " + rocketsFleet.size() + " Rockets ready for launch.");
        System.out.println("");

        return rocketsFleet;
    }

    public int runSimulation(ArrayList<Rocket> fleet,
                             int rocketMaxLoad,
                             int rocketCost,
                             int percChancesExplosion,
                             int percChancesCrash) {

        int currentFleetCost = 0;
        for (Rocket R : fleet) {
            U1 unit = new U1();
            while (!unit.launch(R.name, R.carried, percChancesExplosion, rocketMaxLoad) ||
                    ! unit.land(R.name, R.carried, percChancesCrash, rocketMaxLoad)) {

                currentFleetCost = currentFleetCost + rocketCost;
                System.out.println("-- Current simulation cost: $" + currentFleetCost);
                System.out.println("");
            }
            currentFleetCost = currentFleetCost + rocketCost;
            System.out.println("-- Current simulation cost: $" + currentFleetCost);
            System.out.println("");
        }

        fleetCosts.add(currentFleetCost);

        Integer totalCost = fleetCosts.stream().mapToInt(Integer::intValue).sum();

        return totalCost;
    }
}


