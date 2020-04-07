import javax.swing.*;
import java.awt.*;

public class TravelConfirmationScreen {

    public TravelConfirmationScreen(Game g, int index, ShipScreen sS) {
        JFrame frame = new JFrame("Travel Confirmation Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Current Region: "
                + g.getPlayer().getCurrentRegion().getName(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 5;
        centerPanel.add(new JLabel("Region being traveled to: "
                + g.getUniverse().getRegion(index).getName(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 20;
        int distance = Travel.distance(g.getPlayer().
                getCurrentRegion(), g.getUniverse().getRegion(index));
        centerPanel.add(new JLabel("Distance: "
                + distance, SwingConstants.RIGHT), gbc);

        gbc.gridy = 21;
        centerPanel.add(new JLabel("Pilot Stat: "
                + g.getPlayer().getPilotField(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 22;
        centerPanel.add(new JLabel("Current Fuel: "
                + g.getPlayer().getShip().getCurrentFuel(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 23;
        int fuelCost = Travel.fuel(g, distance);
        centerPanel.add(new JLabel("Fuel Cost: "
                + fuelCost, SwingConstants.RIGHT), gbc);

        gbc.gridy = 24;
        centerPanel.add(new JLabel("Fuel after Travel: "
                + (g.getPlayer().getShip().getCurrentFuel()
                - fuelCost), SwingConstants.RIGHT), gbc);

        JPanel bottomPanel = new JPanel();
        GridBagConstraints gbc2 = new GridBagConstraints();
        bottomPanel.setLayout(new GridBagLayout());

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    int chance;
                    if (g.getDifficulty().toLowerCase().equals("easy")) {
                        chance = 1;
                    } else if (g.getDifficulty().toLowerCase().equals("medium")) {
                        chance = 2;
                    } else {
                        chance = 3;
                    }
                    int r = (int) (Math.random() * 101);
                    if ((r < 5) && (distance > 0) && (g.getPlayer().getShip().
                            getCurrentFuel() != 0)) {
                        Trader t = new Trader(g);
                        TraderScreen tS = new TraderScreen(g, t, sS, index);
                        frame.dispose();
                    } else if ((r < (5 + (5 * chance))) && (distance > 0) && (g.
                            getPlayer().getShip().getCurrentFuel() != 0)) {
                        BanditScreen bS = new BanditScreen(g, sS, index);
                        frame.dispose();
                    } else if ((r < (5 + (5 * chance) + (5 * chance))) && (distance > 0)
                            && (g.getPlayer().getShip().getcurrentCargoSpace() > 0)
                            && (g.getPlayer().getShip().getCurrentFuel() != 0)) {
                        Police p = new Police(g);
                        PoliceScreen pS = new PoliceScreen(g, p, sS, index);
                        frame.dispose();
                    } else {
                        g.getPlayer().setCurrentRegionIndex(index);
                        g.getPlayer().setCurrentRegion(g.getUniverse().getRegion(index));
                        g.getPlayer().getShip().setCurrentFuel(g.
                                getPlayer().getShip().getCurrentFuel() - fuelCost);
                        sS.getJFrame().dispose();
                        ShipScreen newSS = new ShipScreen(g);
                        RegionDetailsScreen rDS = new RegionDetailsScreen(g, index, newSS);
                        frame.dispose();
                    }
                }
            });
        });
        bottomPanel.add(confirmButton, gbc2);

        gbc2.gridy = 5;
        JButton denyButton = new JButton("Deny");
        denyButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    RegionScreen rS = new RegionScreen(g,
                            g.getPlayer().getCurrentRegionIndex(), sS);
                    frame.dispose();
                }
            });
        });
        bottomPanel.add(denyButton, gbc2);

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
