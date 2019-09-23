public class Player {

    private String difficulty;
    private String name;
    private int pilotField;
    private int fighterField;
    private int merchantField;
    private int engineerField;
    private int credits;
    private Region currentRegion;

    public Player(String difficulty, String name, int pilotField, int fighterField, int merchantField, int engineerField) {
        this.difficulty = difficulty;
        this.name = name;
        this.pilotField = pilotField;
        this.fighterField = fighterField;
        this.merchantField = merchantField;
        this.engineerField = engineerField;
        if (difficulty.equals("Easy")) {
            credits = 2000;
        } else if (difficulty.equals("Medium")) {
            credits = 1500;
        } else {
            credits = 1000;
        }
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPilotField() {
        return pilotField;
    }

    public void setPilotField(int pilotField) {
        this.pilotField = pilotField;
    }

    public int getFighterField() {
        return fighterField;
    }

    public void setFighterField(int fighterField) {
        this.fighterField = fighterField;
    }

    public int getMerchantField() {
        return merchantField;
    }

    public void setMerchantField(int merchantField) {
        this.merchantField = merchantField;
    }

    public int getEngineerField() {
        return engineerField;
    }

    public void setEngineerField(int engineerField) {
        this.engineerField = engineerField;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Region getCurrentRegion() {
        return currentRegion;
    }

    public void setCurrentRegion(Region currentRegion) {
        this.currentRegion = currentRegion;
    }
}
