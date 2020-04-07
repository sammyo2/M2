public class Travel {

    private static final int EASYFUEL = 1;
    private static final int MEDFUEL = 2;
    private static final int HARDFUEL = 3;

    public static int distance(Region r1, Region r2) {
        int xDistance = r1.getX() - r2.getX();
        int yDistance = r1.getY() - r2.getY();

        return (int) (Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2)));
    }

    public static int fuel(Game g, int distance) {
        int fuel = 0;
        if (g.getDifficulty().equals("Easy")) {
            fuel = EASYFUEL * distance;
        } else if (g.getDifficulty().equals("Medium")) {
            fuel = MEDFUEL * distance;
        } else {
            fuel = HARDFUEL * distance;
        }
        if (g.getPlayer().getPilotField() == 0) {
            return (int) (fuel * 0.8);
        } else {
            return fuel / g.getPlayer().getPilotField();
        }
    }

    public static boolean canTravel(int fuel, Game g) {
        return (fuel <= g.getPlayer().getShip().getCurrentFuel());
    }

    public static String toString(boolean b) {
        if (b) {
            return "Travel is possible; sufficient fuel.";
        } else {
            return "Travel not possible; insufficient fuel.";
        }
    }

    public static void changeFuel(Game g, int fuel) {
        g.getPlayer().getShip().setCurrentFuel(g.getPlayer().getShip().getCurrentFuel() - fuel);
    }
}