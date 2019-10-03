import java.util.Random;

public class Region {

    private int x;
    private int y;
    private TechLevel techLevel;
    private String name;
    private static Region[] regions = new Region[12];

    public Region(String name, Region[] regions) {
        this.regions = regions;
        this.x = getRandomCoordinateX(regions);
        this.y = getRandomCoordinateY(regions);
        this.name = name;
        this.techLevel = constructTechLevel();
    }

    public Region(String name) {
        this(name, regions);
        //this.regions = new Region[12];
        this.regions[0] = this;
        int range = 200 - (-200) + 1;
        this.x = (int) (Math.random() * range) + (-200);
        this.y = (int) (Math.random() * range) + (-200);
        this.techLevel = constructTechLevel();
    }

    private TechLevel constructTechLevel() {
        Random r = new Random();
        int c = r.nextInt(10);
        if (c == 0) {
            return TechLevel.NEOLITHIC;
        } else if (c == 1) {
            return TechLevel.AGRICULTURAL;
        } else if (c == 2) {
            return TechLevel.MEDIEVAL;
        } else if (c == 3) {
            return TechLevel.RENAISSANCE;
        } else if (c == 4) {
            return TechLevel.INDUSTRIAL;
        } else if (c == 5) {
            return TechLevel.MODERN;
        } else {
            return TechLevel.FUTURISTIC;
        }
    }

    public int getRandomCoordinateX(Region[] regions) {
        Random r = new Random();
        int c = r.nextInt(401) - 200;
        while (coordinateCheckX(regions, c)) {
            c = r.nextInt(401) - 200;
        }
        return c;
    }

    public boolean coordinateCheckX(Region[] regions, int c) {
        for (Region r: regions) {
            if (r != null) {
                if ((r.getX() < c + 5) && (r.getX() > c - 5)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getRandomCoordinateY(Region[] regions) {
        Random r = new Random();
        int c = r.nextInt(401) - 200;
        while (coordinateCheckY(regions, c)) {
            c = r.nextInt(401) - 200;
        }
        return c;
    }

    public boolean coordinateCheckY(Region[] regions, int c) {
        for (Region r: regions) {
            if (r != null) {
                if ((r.getY() < c + 5) && (r.getY() > c - 5)) {
                    return true;
                }
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

    /* public String toString() {
        return "Region name: " + this.getName() + "\nCoordinates[x, y]: " + this.getX() + ", "
                + this.getY() + "\nTech Level: " + this.getTechLevel();
    } */
}