import java.util.ArrayList;
import java.util.Random;

public class Game {

    private String difficulty;
    private Universe universe;
    private final String[] regionNames =
            new String[]{"Z5", "X7", "K9", "P2", "D4", "H1", "Q0", "R3", "Y8", "A6"};
    private Player player;
    private VictoryItem victoryItem;
    private ShipScreen sS;
    private UniverseMap uM;

    public Game(String name, String difficulty, int pilotField, int fighterField,
                int merchantField, int engineerField) {
        startGame(name, difficulty, pilotField, fighterField, merchantField, engineerField);
        Random r = new Random();
        int c = r.nextInt(10);
        player.setCurrentRegion(universe.getRegion(c));
        player.setCurrentRegionIndex(c);
    }

    private void startGame(String name, String difficulty, int pilotField, int fighterField,
                           int merchantField, int engineerField) {
        this.difficulty = difficulty;
        this.player = new Player(name, difficulty, pilotField,
                fighterField, merchantField, engineerField);
        ArrayList<String> tempList = new ArrayList<String>(10);
        tempList.ensureCapacity(10);
        Random r = new Random();
        int c = r.nextInt(10);
        tempList.add(regionNames[c]);
        while (tempList.size() < 10) {
            c = r.nextInt(10);
            listAdder(tempList, regionNames[c]);
        }
        String[] regionNames = new String[10];
        for (int i = 0; i < tempList.size(); i++) {
            regionNames[i] = tempList.get(i);
        }
        universe = universe.getInstance(regionNames, difficulty);
        c = r.nextInt(10);
        this.universe.getRegion(c).setVItem(true);
        this.victoryItem = new VictoryItem(TechLevel.NEOLITHIC, 0, this);
    }

    private void listAdder(ArrayList<String> tempList, String c) {
        if (!tempList.contains(c)) {
            tempList.add(c);
        }
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public VictoryItem getVictoryItem() {
        return this.victoryItem;
    }

    public void setSS(ShipScreen sS) {
        this.sS = sS;
    }

    public ShipScreen getSS() {
        return this.sS;
    }

    public void setUM(UniverseMap uM) {
        this.uM = uM;
    }

    public UniverseMap getUM() {
        return this.uM;
    }
}