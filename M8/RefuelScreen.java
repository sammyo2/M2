import javax.swing.*;
import java.awt.*;

public class RefuelScreen {

    public RefuelScreen(Game g, int index) {
        JFrame frame = new JFrame("Refuel Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel txt = new JLabel();
        txt.setText("Refuel your Ship!");
        centerPanel.add(txt, gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Current Fuel: "
                + g.getPlayer().getShip().getCurrentFuel(), SwingConstants.RIGHT), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Max Fuel Allowed: "
                + g.getPlayer().getShip().getMaxFuel(), SwingConstants.RIGHT), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Fuel needed to have full tank: "
                + (g.getPlayer().getShip().getMaxFuel() - g.getPlayer().getShip().getCurrentFuel()),
                SwingConstants.RIGHT), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Fuel in Inventory: "
                + g.getPlayer().getInventory(0).getQuantity(), SwingConstants.RIGHT), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Quantity fuel to use: "), gbc);

        gbc.gridx++;
        JTextField qField = new JTextField(10);
        centerPanel.add(qField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton refuelButton = new JButton("Refuel");
        centerPanel.add(refuelButton, gbc);
        refuelButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    int q = Integer.parseInt(qField.getText());
                    if (q < 1) {
                        txt.setText("Invalid refuel amount input.");
                    } else if (q > g.getPlayer().getInventory(0).getQuantity()) {
                        txt.setText("Not enough fuel in inventory.");
                    } else if (q + g.getPlayer().getShip().getCurrentFuel() > g.getPlayer().
                            getShip().getMaxFuel()) {
                        txt.setText("Tank will overflow: select lower refill quantity.");
                    } else {
                        g.getPlayer().getInventory(0).setQuantity(g.getPlayer().
                                getInventory(0).getQuantity() - q);
                        g.getPlayer().getShip().setCurrentFuel(g.getPlayer().
                                getShip().getCurrentFuel() + q);
                        g.getSS().getJFrame().dispose();
                        ShipScreen newSS = new ShipScreen(g);
                        g.setSS(newSS);
                        RegionScreen rS = new RegionScreen(g, index);
                        frame.dispose();
                    }
                }
            });
        });

        gbc.gridy++;
        JButton returnButton = new JButton("Return to Region Screen");
        centerPanel.add(returnButton, gbc);
        returnButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    RegionScreen rS = new RegionScreen(g, index);
                }
            });
        });

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
