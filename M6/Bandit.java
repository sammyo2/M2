public class Bandit {

    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;

    public static int findMultiplier(Game g) {
        if (g.getDifficulty().toLowerCase().equals("easy")) {
            return EASY;
        } else if (g.getDifficulty().toLowerCase().equals("medium")) {
            return MEDIUM;
        } else {
            return HARD;
        }
    }

    public static String pay(Game g) {
        if (g.getPlayer().getCredits() >= findMultiplier(g) * 1000) {
            g.getPlayer().setCredits(g.getPlayer().getCredits() - (findMultiplier(g) * 1000));
            return "<html>Paid " + (findMultiplier(g) * 1000) + " credits."
                + "<br/>Continuing travel.</html>";
        } else if (g.getPlayer().getShip().getcurrentCargoSpace() > 0) {
            g.getPlayer().createInventory();
            return "<html>All Inventory lost."
                + "<br/>Continuing travel.</html>";
        } else {
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().
                    getShip().getCurrentHealth() - (100 * findMultiplier(g)));
            return "<html>Ship lost " + g.getPlayer().getShip().getCurrentHealth() + " health."
                + "<br/>Continuing travel.</html>";
        }
    }

    public static String flee(Game g) {
        if (5 * g.getPlayer().getPilotField() >= ((int) (Math.random() * 101))) {
            return "<html>Flee successful!"
                + "<br/>Returning to starting region.</html>";
        } else {
            g.getPlayer().setCredits(0);
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().
                    getShip().getCurrentHealth() - (100 * findMultiplier(g)));
            return "<html>Flee unsuccessful!"
                + "<br/>Returning to starting region."
                + "<br/>Ship lost " + (100 * findMultiplier(g)) + " health."
                + "<br/>All credits lost.</html>";
        }
    }

    public static String fight(Game g) {
        if (5 * g.getPlayer().getFighterField() >= ((int) (Math.random() * 101))) {
            g.getPlayer().setCredits(g.getPlayer().getCredits() + (3000 / findMultiplier(g)));
            return "<html>Fight successful!"
                + "<br/>Continuing travel."
                + "<br/>Gained " + (3000 / findMultiplier(g)) + " credits from bandit.</html>";
        } else {
            g.getPlayer().setCredits(0);
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().
                    getShip().getCurrentHealth() - (100 * findMultiplier(g)));
            return "<html>Fight unsuccessful!"
                + "<br/>Continuing travel."
                + "<br/>Ship lost " + (100 * findMultiplier(g)) + " health."
                + "<br/>All credits lost.</html>";
        }
    }

    public int getEasy() {
        return EASY;
    }

    public int getMedium() {
        return MEDIUM;
    }

    public int getHard() {
        return HARD;
    }
}