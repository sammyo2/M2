public interface NPC {

    int EASY = 1;
    int MEDIUM = 2;
    int HARD = 3;

    static int findMultiplier(Game g) {
        if (g.getDifficulty().toLowerCase().equals("easy")) {
            return EASY;
        } else if (g.getDifficulty().toLowerCase().equals("medium")) {
            return MEDIUM;
        } else {
            return HARD;
        }
    }

    String flee(Game g);

    String fight(Game g);
}
