public interface SpaceShip {
    boolean launch();

    boolean land();

    boolean canCarry(Item item, int rocketMaxLoad, int rocketCurrentWeight);

    int carry(Item item, int rocketCurrentWeight);

}
