public class Item {

    private String name;
    private int price;
    private int quantity;
    private static String[] itemNames = new String[]{"Fuel", "Medicine", "Computers",
                                                        "Gold", "Wood", "Fruit",
                                                        "Fast Food", "Titanium",
                                                        "Clean Water", "AK-47"};
    private static int[] itemBasePrice = new int[]{10, 40, 200, 400, 30,
                                                      20, 30, 300, 30, 100};
    private static int[] itemBaseQuantity = new int[]{1000, 100, 50, 200, 800,
                                                      500, 300, 50, 400, 150};
    private static double[] neolithicMult = new double[]{5, 0, 0, 2, 2, 2, 0, 0, .5, 0};
    private static double[] agriculturalMult = new double[]{4, .5, 0, 3, 5, 5, 0, 0, 1, 0};
    private static double[] medievalMult = new double[]{3, .7, 0, 4, 3, 4, 0, 0, 2, 0};
    private static double[] renaissanceMult = new double[]{2, .8, 0, 5, 1, 3, .5, .5, 3, .5};
    private static double[] industrialMult = new double[]{1, .9, .5, 1, .8, 1, 1, 1, 4, .7};
    private static double[] modernMult = new double[]{.5, 1, 1, .5, .5, .5, 2, 2, 5, 1};
    private static double[] futuristicMult = new double[]{0, 2, 2, 0, 0, 0, 3, 3, 0, 2};
    private static double easyPriceMult = .5;
    private static double medPriceMult = 1;
    private static double hardPriceMult = 1.5;
    private static double easyQuantMult = 1.5;
    private static double medQuantMult = 1;
    private static double hardQuantMult = .5;

    public Item(TechLevel techlevel, int i, String difficulty) {
        this.name = itemNames[i];
        this.quantity = determineQuantity(techlevel, i, difficulty);
        this.price = determinePrice(techlevel, i, difficulty);
    }

    public Item(int i) {
        this.name = itemNames[i];
        this.quantity = 0;
        this.price = itemBasePrice[i];
    }

    private int determineQuantity(TechLevel techlevel, int i, String difficulty) {
        double mult = findQuantMultiplier(difficulty);
        int quantity = (int) (itemBaseQuantity[i] * mult);
        if (techlevel.equals(techlevel.NEOLITHIC)) {
            return (int) (quantity * neolithicMult[i]);
        } else if (techlevel.equals(techlevel.AGRICULTURAL)) {
            return (int) (quantity * agriculturalMult[i]);
        } else if (techlevel.equals(techlevel.MEDIEVAL)) {
            return (int) (quantity * medievalMult[i]);
        } else if (techlevel.equals(techlevel.RENAISSANCE)) {
            return (int) (quantity * renaissanceMult[i]);
        } else if (techlevel.equals(techlevel.INDUSTRIAL)) {
            return (int) (quantity * industrialMult[i]);
        } else if (techlevel.equals(techlevel.MODERN)) {
            return (int) (quantity * modernMult[i]);
        } else {
            return (int) (quantity * futuristicMult[i]);
        }
    }

    private int determinePrice(TechLevel techlevel, int i, String difficulty) {
        double mult = findBuyMultiplier(difficulty);
        int price = (int) (itemBasePrice[i] * mult);
        if (techlevel.equals(techlevel.NEOLITHIC)) {
            if (neolithicMult[i] == 0) {
                return (int) (price * 2.5);
            } else {
                if ((int) (price * (1.0 / neolithicMult[i])) < 1) {
                    return 1;
                } else {
                    return (int) (price * (1.0 / neolithicMult[i]));
                }
            }
        } else if (techlevel.equals(techlevel.AGRICULTURAL)) {
            if (agriculturalMult[i] == 0) {
                return (int) (price * 2.5);
            } else {
                if ((int) (price * (1.0 / agriculturalMult[i])) < 1) {
                    return 1;
                } else {
                    return (int) (price * (1.0 / agriculturalMult[i]));
                }
            }
        } else if (techlevel.equals(techlevel.MEDIEVAL)) {
            if (medievalMult[i] == 0) {
                return (int) (price * 2.5);
            } else {
                if ((int) (price * (1.0 / medievalMult[i])) < 1) {
                    return 1;
                } else {
                    return (int) (price * (1.0 / medievalMult[i]));
                }
            }
        } else if (techlevel.equals(techlevel.RENAISSANCE)) {
            if (renaissanceMult[i] == 0) {
                return (int) (price * 2.5);
            } else {
                if ((int) (price * (1.0 / renaissanceMult[i])) < 1) {
                    return 1;
                } else {
                    return (int) (price * (1.0 / renaissanceMult[i]));
                }
            }
        } else if (techlevel.equals(techlevel.INDUSTRIAL)) {
            if (industrialMult[i] == 0) {
                return (int) (price * 2.5);
            } else {
                if ((int) (price * (1.0 / industrialMult[i])) < 1) {
                    return 1;
                } else {
                    return (int) (price * (1.0 / industrialMult[i]));
                }
            }
        } else if (techlevel.equals(techlevel.MODERN)) {
            if (modernMult[i] == 0) {
                return (int) (price * 2.5);
            } else {
                if ((int) (price * (1.0 / modernMult[i])) < 1) {
                    return 1;
                } else {
                    return (int) (price * (1.0 / modernMult[i]));
                }
            }
        } else {
            if (futuristicMult[i] == 0) {
                return (int) (price * 2.5);
            } else {
                if ((int) (price * (1.0 / futuristicMult[i])) < 1) {
                    return 1;
                } else {
                    return (int) (price * (1.0 / futuristicMult[i]));
                }
            }
        }
    }

    private double findBuyMultiplier(String difficulty) {
        if (difficulty.equals("Easy")) {
            return easyPriceMult;
        } else if (difficulty.equals("Medium")) {
            return medPriceMult;
        } else {
            return hardPriceMult;
        }
    }

    private double findQuantMultiplier(String difficulty) {
        if (difficulty.equals("Easy")) {
            return easyQuantMult;
        } else if (difficulty.equals("Medium")) {
            return medQuantMult;
        } else {
            return hardQuantMult;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static int getItemBasePrice(int index) {
        return itemBasePrice[index];
    }

    public static int getItemBaseQuantity(int index) {
        return itemBaseQuantity[index];
    }
}
