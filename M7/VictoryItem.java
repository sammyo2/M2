public class VictoryItem extends Item {

    public VictoryItem(TechLevel techlevel, int i, Game g) {
        super(techlevel, i, g.getDifficulty());
        this.setName(g.getPlayer().getName() + "'s Universe");
        this.setQuantity(1);
        this.setPrice(determinePrice(g));
    }

    public int determinePrice(Game g) {
        if (g.getDifficulty().toLowerCase().equals("easy")) {
            return 40000;
        } else if (g.getDifficulty().toLowerCase().equals("medium")) {
            return 45000;
        } else {
            return 50000;
        }
    }
}
