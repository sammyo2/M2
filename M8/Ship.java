public class Ship {
   
    private String type;
    private String name;
    private int maxCargoSpace;
    private int currentCargoSpace;
    private int maxFuel;
    private int currentFuel;
    private int maxHealth;
    private int currentHealth;

    public Ship(String type, String name, int maxCargoSpace, int maxFuel, int maxHealth) {
        this.type = type;
        this.name = name;
        this.maxCargoSpace = maxCargoSpace;
        this.currentCargoSpace = 0;
        this.maxFuel = maxFuel;
        this.currentFuel = maxFuel;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMaxCargoSpace(int maxCargoSpace) {
        this.maxCargoSpace = maxCargoSpace;
    }

    public int getMaxCargoSpace() {
        return this.maxCargoSpace;
    }
   
    public void setCurrentCargoSpace(int currentCargoSpace) {
        this.currentCargoSpace = currentCargoSpace;
    }

    public int getcurrentCargoSpace() {
        return this.currentCargoSpace;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public int getMaxFuel() {
        return this.maxFuel;
    }

    public void setCurrentFuel(int currentFuel) {
        this.currentFuel = currentFuel;
    }

    public int getCurrentFuel() {
        return this.currentFuel;
    }

    public void setmaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getmaxHealth() {
        return maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

}