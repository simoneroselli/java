public class U1 extends Rocket {

    U1(){super(0);}

    public boolean launch(String cargoName, int cargoCarried, int percChanceExplosion, int cargoLimit) {

        double chanceExplosion = (double) percChanceExplosion / 10;

        double launchExplodeChances = (chanceExplosion * cargoCarried) / cargoLimit;
        double randomEvent = Math.random();

        System.out.println("Launching cargo: " + cargoName);
        System.out.println("Random event: " + randomEvent);
        System.out.println("Launch explosion chances: " + launchExplodeChances);

        if (randomEvent <= launchExplodeChances) {
            System.out.println("Cargo " + cargoName + " exploded on launching! -- Relaunching --");
            System.out.println("");
            return false;
        } else {
            System.out.println("Cargo " + cargoName + " Successfully launched");
            System.out.println("");
            return true;
        }
    }

    public boolean land(String cargoName, int cargoCarried, int percChanceCrash, int cargoLimit) {

        double chanceCrash = (double) percChanceCrash / 10;

        double landCrashChances = (chanceCrash * cargoCarried) / cargoLimit;
        double randomEvent = Math.random();

        System.out.println("Landing cargo: " + cargoName);
        System.out.println("Random Event: " + randomEvent);
        System.out.println("Land crash chances: " + landCrashChances);

        if (randomEvent <= landCrashChances) {
            System.out.println("Cargo " + cargoName + " crashed on landing! -- Relaunching --");
            System.out.println("");
            return false;
        } else {
            System.out.println("Cargo " + cargoName + " successfully landed");
            System.out.println("");
            return true;
        }
    }
}