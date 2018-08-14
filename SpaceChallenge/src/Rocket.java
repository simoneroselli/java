public class Rocket implements SpaceShip {

    String name;
    int carried;

    public Rocket(int cargoCarried) {

        carried = cargoCarried;
    }

    @Override
    public boolean launch() {

        return true;
    }

    @Override
    public boolean land() {

        return true;
    }

    @Override
    public boolean canCarry(Item item, int rocketMaxLoad, int rocketCurrentWeight) {
        int itemWeight = item.getWeight();

        if (rocketCurrentWeight + itemWeight <= rocketMaxLoad) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public int carry(Item item, int rocketCurrentWeight) {
        int itemWeight = item.getWeight();

        int newRocketCurrentWeight = itemWeight + rocketCurrentWeight;

        return newRocketCurrentWeight;
    }

}