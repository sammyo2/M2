public class Region {

    private int x;
    private int y;
    private TechLevel techLevel;
    private String name;

    public Region(String name) {
        this.x = getRandomCoordinateX(regions[]);
        this.y = getRandomCoordinateY(regions[]);
        this.name = name;
        this.techLevel = TechLevel();
    }

    public int getRandomCoordinateX(Region[] regions) {
        int c = Random.nextInt(601) - 300;
        while (coordinateCheckX(regions, c)) {
            c = Random.nextInt(601) - 300;
        }
        return c;
    }

    public boolean coordinateCheckX(Region[] regions, int c) {
        for (Region r: regions) {
            if ((r.getX() < c + 5) && (r.getX() > c - 5)) {
                return true;
            }
        }
        return false;
    }

    public int getRandomCoordinateY(Region[] regions) {
        int c = Random.nextInt(601) - 300;
        while (coordinateCheckY(regions, c)) {
            c = Random.nextInt(601) - 300;
        }
        return c;
    }

    public boolean coordinateCheckY(Region[] regions, int c) {
        for (Region r: regions) {
            if ((r.getY() < c + 5) && (r.getY() > c - 5)) {
                return true;
            }
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}