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
                    g.getPlayer().setCurrentRegionIndex(index);
                    g.getPlayer().setCurrentRegion(g.getUniverse().getRegion(index));

                    g.getPlayer().getShip().setCurrentFuel(g.
                            getPlayer().getShip().getCurrentFuel() - fuelCost);
                    sS.getJFrame().dispose();
                    ShipScreen newSS = new ShipScreen(g);
                    RegionDetailsScreen rDS = new RegionDetailsScreen(g, index, newSS);
                    frame.dispose();
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
