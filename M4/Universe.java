public class Universe {

    private static Universe u = null;
    private Region[] regions = new Region[10];

    private Universe(String[] regionNames) {
        int c = 0;
        for (String name: regionNames) {
            regions[c] = new Region(name, regions);
            c++;
        }
    }

    public static Universe getInstance(String[] regionNames) {
        if (u == null) {
            u = new Universe(regionNames);
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
}