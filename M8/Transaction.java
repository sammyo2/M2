public class Transaction {

    public static boolean buyQuantityCheckBoolean(int index, int quantity, Game g, Region r) {
        return (quantity <= r.getMarket(index).getQuantity());
    }

    public static String buyQuantityCheckString(int index, int quantity, Game g, Region r) {
        if (quantity <= r.getMarket(index).getQuantity()) {
            return "Quantity chosen is available in market.";
        } else {
            return "Quantity chosen is greater than what is available in market.";
        }
    }

    public static String buyQuantityCheckString(boolean b) {
        if (b) {
            return "Quantity chosen is available in market.";
        } else {
            return "Quantity chosen is greater than what is available in market.";
        }
    }

    public static boolean sellQuantityCheckBoolean(int index, int quantity, Game g, Region r) {
        return (quantity <= g.getPlayer().getInventory(index).getQuantity());
    }

    public static String sellQuantityCheckString(int index, int quantity, Game g, Region r) {
        if (quantity <= g.getPlayer().getInventory(index).getQuantity()) {
            return "Quantity chosen is in inventory.";
        } else {
            return "Quantity chosen is greater than what is available in inventory.";
        }
    }

    public static String sellQuantityCheckString(boolean b) {
        if (b) {
            return "Quantity chosen is in inventory.";
        } else {
            return "Quantity chosen is greater than what is available in inventory.";
        }
    }

    public static boolean buyCargoCheckBoolean(int quantity, Game g) {
        return (g.getPlayer().getShip().getcurrentCargoSpace() + quantity
                <= g.getPlayer().getShip().getMaxCargoSpace());
    }

    public static String buyCargoCheckString(int quantity, Game g) {
        if (g.getPlayer().getShip().getcurrentCargoSpace() + quantity
                <= g.getPlayer().getShip().getMaxCargoSpace()) {
            return "Quantity chosen will fit in cargo.";
        } else {
            return "Quantity chosen is too large for cargo to hold.";
        }
    }

    public static String buyCargoCheckString(boolean b) {
        if (b) {
            return "Quantity chosen will fit in cargo.";
        } else {
            return "Quantity chosen is too large for cargo to hold.";
        }
    }

    public static int buyPriceCalculator(int index, int quantity, Region r, Game g) {
        if (g.getPlayer().getMerchantField() == 0) {
            return (int) ((r.getMarket(index).getPrice() * quantity) / .8);
        } else {
            return (r.getMarket(index).getPrice() * quantity) / g.getPlayer().getMerchantField();
        }
    }

    public static int exchangePriceCalculator(int index, int quantity, Region r, Game g) {
        if (g.getPlayer().getMerchantField() == 0) {
            return (int) ((r.getMarket(index).getPrice() *  quantity) / .9);
        } else {
            return (r.getMarket(index).getPrice() * g.getPlayer().getMerchantField()) * quantity;
        }
    }

    public static boolean buyPriceCheckerBoolean(Game g, int price) {
        return (price <= g.getPlayer().getCredits());
    }

    public static String buyPriceCheckerString(Game g, int price) {
        if (price <= g.getPlayer().getCredits()) {
            return "You can afford this purchase";
        } else {
            return "You cannot afford this purchase";
        }
    }

    public static String buyPriceCheckerString(boolean b) {
        if (b) {
            return "You can afford this purchase";
        } else {
            return "You cannot afford this purchase";
        }
    }

    public static void buyTransaction(int price, Game g, Region r, int index, int quantity) {
        g.getPlayer().setCredits(g.getPlayer().getCredits() - price);
        g.getPlayer().getInventory(index).setQuantity(g.getPlayer().getInventory(index).
                getQuantity() + quantity);
        r.getMarket(index).setQuantity(r.getMarket(index).getQuantity() - quantity);
        g.getPlayer().getShip().setCurrentCargoSpace(g.getPlayer().getShip().
                getcurrentCargoSpace() + quantity);
    }

    public static void sellTransaction(int price, Game g, Region r, int index, int quantity) {
        g.getPlayer().setCredits(g.getPlayer().getCredits() + price);
        g.getPlayer().getInventory(index).setQuantity(g.getPlayer().getInventory(index).
                getQuantity() - quantity);
        r.getMarket(index).setQuantity(r.getMarket(index).getQuantity() + quantity);
        g.getPlayer().getShip().setCurrentCargoSpace(g.getPlayer().getShip().
                getcurrentCargoSpace() - quantity);
    }
}
