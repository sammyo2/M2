public class TechLevel {

    private TechLevel techLevel;

    public enum TechLevel {
        NEOLITHIC, AGRICULTURAL, MEDIEVAL, RENAISSANCE, INDUSTRIAL, MODERN, FUTURISTIC;
    }

    public TechLevel() {
        int r = Random.nextInt(7);
        if (r = 0) {
            this.techLevel = TechLevel.NEOLITHIC;
        } else if (r = 1) {
            this.techLevel = TechLevel.AGRICULTURAL;
        } else if (r = 2) {
            this.techLevel = TechLevel.MEDIEVAL;
        } else if (r = 3) {
            this.techLevel = TechLevel.RENAISSANCE;
        } else if (r = 4) {
            this.techLevel = TechLevel.INDUSTRIAL;
        } else if (r = 5) {
            this.techLevel = TechLevel.MODERN;
        } else {
            this.techLevel = TechLevel.FUTURISTIC;
        }
    }

    public TechLevel.TechLevel getTechLevel() {
        return techLevel;
    }
}