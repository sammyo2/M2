public class Universe {

    private static Universe u = null;
    private Region[] regions = new Region[10];
    private int regionSize;

    private Universe(String[] regionNames, String difficulty) {
        int c = 0;
        for (String name: regionNames) {
            regions[c] = new Region(name, regions, difficulty);
            c++;
        }
        this.regionSize = c;
    }

    public static Universe getInstance(String[] regionNames, String difficulty) {
        if (u == null) {
            u = new Universe(regionNames, difficulty);
        }
        return u;
    }

    public Region getRegion(int i) {
        return regions[i];
    }

    public void setRegion(Region region, int i) {
        regions[i] = region;
    }

    public static Universe getUniverse() {
        return u;
    }

    public int getRegionSize() {
        return regionSize;
    }

    public Region[] getAllRegions() {
        return regions;
    }
}