public class Trader {

    private static final int EASY = 3;
    private static final int MEDIUM = 2;
    private static final int HARD = 1;
    private Item item;
    private int quantity;
    private int inventoryIndex;
    private int price;

    private static int findMultiplier(Game g) {
        if (g.getDifficulty().toLowerCase().equals("easy")) {
            return EASY;
        } else if (g.getDifficulty().toLowerCase().equals("medium")) {
            return MEDIUM;
        } else {
            return HARD;
        }
    }

    public Trader(Game g) {
        this.inventoryIndex = (int) (Math.random() * 10);
        this.item = g.getPlayer().getInventory(inventoryIndex);
        this.price = Item.getItemBasePrice(inventoryIndex) * 3 / 5;
        this.quantity = Item.getItemBaseQuantity(inventoryIndex) / 10;
    }

    public String buy(Game g, int quantity) {
        boolean check = true;
        String s1 = "The trader has enough " + item.getName() + " to sell to you.";
        if (this.quantity < quantity) {
            check = false;
            s1 = "The trader does not have enough " + item.getName() + " to sell to you.";
        }
        String s2 = Transaction.buyCargoCheckString(Transaction.buyCargoCheckBoolean(quantity, g));
        if (!Transaction.buyCargoCheckBoolean(quantity, g)) {
            check = false;
        }
        String s3 = "You can afford this purchase.";
        if (quantity * this.price > g.getPlayer().getCredits()) {
            check = false;
            s3 = "You cannot afford this purchase.";
        }
        if (check) {
            g.getPlayer().getInventory(inventoryIndex).setQuantity(g.getPlayer().
                    getInventory(inventoryIndex).getQuantity() + quantity);
            g.getPlayer().setCredits(g.getPlayer().getCredits() - (price * quantity));
            g.getPlayer().getShip().setCurrentCargoSpace(g.getPlayer().getShip().
                    getcurrentCargoSpace() + quantity);
            return "Successful purchase of " + quantity + " " + item.getName();
        } else {
            return "<html>" + s1
                    + "<br/>" + s2
                    + "<br/>" + s3 + "</html>";
        }
    }

    public boolean buy(int quantity, Game g) {
        boolean b = true;
        if (this.quantity < quantity) {
            b = false;
        }
        if (!Transaction.buyCargoCheckBoolean(quantity, g)) {
            b = false;
        }
        if (quantity * this.price > g.getPlayer().getCredits()) {
            b = false;
        }
        return b;
    }

    public String ignore() {
        return "<html>No trades made."
                + "<br/>" + "Continuing travel.</html>";
    }

    public String rob(Game g) {
        int r = (int) (Math.random() * 101);
        if (g.getPlayer().getFighterField() * 5 >= r) {
            g.getPlayer().getInventory(inventoryIndex).setQuantity(g.getPlayer().
                    getInventory(inventoryIndex).getQuantity() + (quantity / 2));
            g.getPlayer().getShip().setCurrentCargoSpace(g.getPlayer().getShip().
                    getcurrentCargoSpace() + (quantity / 2));
            return "<html>Robbery successful."
                    + "<br/>" + item.getName() + " is now at a quantity of "
                    + g.getPlayer().getInventory(inventoryIndex).getQuantity() + ".</html>";
        } else {
            int lostHealth = 300 / findMultiplier(g);
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().
                    getShip().getCurrentHealth() - lostHealth);
            return "<html>Robbery unsuccessful."
                    + "<br/>" + "Lost " + lostHealth + " health.</html>";
        }
    }

    public String negotiate(Game g) {
        int r = (int) (Math.random() * 101);
        if (g.getPlayer().getMerchantField() * 5 >= r) {
            this.price = price / 5;
            return "Negotiation successful.";
        } else {
            this.price = price * 5;
            return "Negotiation unsuccessful.";
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

    public int getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setInventoryIndex(int inventoryIndex) {
        this.inventoryIndex = inventoryIndex;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item getItem() {
        return this.item;
    }
}