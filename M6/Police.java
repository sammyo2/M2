public class Police {

    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;
    private int quantity;
    private int inventoryIndex;

    public int findMultiplier(Game g) {
        if (g.getDifficulty().toLowerCase().equals("easy")) {
            return EASY;
        } else if (g.getDifficulty().toLowerCase().equals("medium")) {
            return MEDIUM;
        } else {
            return HARD;
        }
    }

    public Police(Game g) {
        this.inventoryIndex = (int) (Math.random() * 10);
        while (g.getPlayer().getInventory(inventoryIndex).getQuantity() == 0) {
            this.inventoryIndex = (int) (Math.random() * 10);
        }
        this.quantity = ((int) (Math.random() * (g.getPlayer().getInventory(inventoryIndex).
                getQuantity() / 2))) + (g.getPlayer().getInventory(inventoryIndex).
                getQuantity() / 2);
        if (this.quantity == 0) {
            this.quantity = 1;
        }
    }


    public String forfeit(Game g) {
        g.getPlayer().getInventory(inventoryIndex).setQuantity(g.getPlayer().
                getInventory(inventoryIndex).getQuantity() - quantity);
        g.getPlayer().getShip().setCurrentCargoSpace(g.getPlayer().getShip().
                getcurrentCargoSpace() - quantity);
        return "<html>Illegal inventory given to police."
                + "<br/>" + g.getPlayer().
                getInventory(inventoryIndex).getName() + " decreased by " + quantity + ".</html>";
    }

    public String flee(Game g) {
        int r = (int) (Math.random() * 101);
        if (g.getPlayer().getPilotField() * 5 > r) {
            return "<html>Flee successful."
                    + "<br/>" + "Returning to starting region."
                    + "<br/>" + "Lost fuel equal to cost of original travel.</html>";
        } else {
            g.getPlayer().getInventory(inventoryIndex).setQuantity(g.getPlayer().
                    getInventory(inventoryIndex).getQuantity() - quantity);
            g.getPlayer().getShip().setCurrentCargoSpace(g.getPlayer().getShip().
                    getcurrentCargoSpace() - quantity);
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().getShip().
                    getCurrentHealth() - (100 * findMultiplier(g)));
            int payment;
            if (g.getPlayer().getCredits() >= (1000 * findMultiplier(g))) {
                payment = 1000 * findMultiplier(g);
                g.getPlayer().setCredits(g.getPlayer().getCredits() - payment);
            } else {
                payment = g.getPlayer().getCredits();
                g.getPlayer().setCredits(0);
            }
            return "<html>Flee unsuccessful."
                    + "<br/>" + g.getPlayer().getInventory(inventoryIndex).
                    getName() + " current quantity decreased by " + quantity + "."
                    + "<br/>" + "Ship health decreased by " + (100 * findMultiplier(g)) + "."
                    + "<br/>" + "Credits decreased by " + payment + ".</html>";
        }
    }

    public String fight(Game g) {
        int r = (int) (Math.random() * 101);
        if (g.getPlayer().getFighterField() * 5 > r) {
            return "<html>Fight successful."
                    + "<br/>" + "Continuing travel.</html>";
        } else {
            g.getPlayer().getInventory(inventoryIndex).setQuantity(g.getPlayer().
                    getInventory(inventoryIndex).getQuantity() - quantity);
            g.getPlayer().getShip().setCurrentCargoSpace(g.getPlayer().getShip().
                    getcurrentCargoSpace() - quantity);
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().getShip().
                    getCurrentHealth() - (100 * findMultiplier(g)));
            int payment;
            if (g.getPlayer().getCredits() >= (1000 * findMultiplier(g))) {
                payment = 1000 * findMultiplier(g);
                g.getPlayer().setCredits(g.getPlayer().getCredits() - payment);
            } else {
                payment = g.getPlayer().getCredits();
                g.getPlayer().setCredits(0);
            }
            return "<html>Fight unsuccessful."
                    + "<br/>" + g.getPlayer().getInventory(inventoryIndex).getName()
                    + " current quantity decreased by " + quantity + "."
                    + "<br/>" + "Ship health decreased by " + (100 * findMultiplier(g)) + "."
                    + "<br/>" + "Credits decreased by " + payment + ".</html>";
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

    public int getQuantity() {
        return quantity;
    }

    public int getInventoryIndex() {
        return inventoryIndex;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setInventoryIndex(int inventoryIndex) {
        this.inventoryIndex = inventoryIndex;
    }
}