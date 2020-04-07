public class Bandit implements NPC {

    public String pay(Game g) {
        g.getPlayer().raiseKarma();
        g.getPlayer().raiseKarma();
        if (g.getPlayer().getCredits() >= NPC.findMultiplier(g) * 1000) {
            g.getPlayer().setCredits(g.getPlayer().getCredits() - (NPC.findMultiplier(g) * 1000));
            return "<html>Paid " + (NPC.findMultiplier(g) * 1000) + " credits."
                + "<br/>Continuing travel.</html>";
        } else if (g.getPlayer().getShip().getcurrentCargoSpace() > 0) {
            g.getPlayer().createInventory();
            return "<html>All Inventory lost."
                + "<br/>Continuing travel.</html>";
        } else {
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().
                    getShip().getCurrentHealth() - (100 * NPC.findMultiplier(g)));
            return "<html>Ship lost " + g.getPlayer().getShip().getCurrentHealth() + " health."
                + "<br/>Continuing travel.</html>";
        }
    }

    public String flee(Game g) {
        if (5 * g.getPlayer().getPilotField() >= ((int) (Math.random() * 101))) {
            return "<html>Flee successful!"
                + "<br/>Returning to starting region.</html>";
        } else {
            g.getPlayer().setCredits(0);
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().
                    getShip().getCurrentHealth() - (100 * NPC.findMultiplier(g)));
            return "<html>Flee unsuccessful!"
                + "<br/>Returning to starting region."
                + "<br/>Ship lost " + (100 * NPC.findMultiplier(g)) + " health."
                + "<br/>All credits lost.</html>";
        }
    }

    public String fight(Game g) {
        if (5 * g.getPlayer().getFighterField() >= ((int) (Math.random() * 101))) {
            g.getPlayer().setCredits(g.getPlayer().getCredits() + (3000 / NPC.findMultiplier(g)));
            return "<html>Fight successful!"
                + "<br/>Continuing travel."
                + "<br/>Gained " + (3000 / NPC.findMultiplier(g)) + " credits from bandit.</html>";
        } else {
            g.getPlayer().setCredits(0);
            g.getPlayer().getShip().setCurrentHealth(g.getPlayer().
                    getShip().getCurrentHealth() - (100 * NPC.findMultiplier(g)));
            return "<html>Fight unsuccessful!"
                + "<br/>Continuing travel."
                + "<br/>Ship lost " + (100 * NPC.findMultiplier(g)) + " health."
                + "<br/>All credits lost.</html>";
        }
    }
}