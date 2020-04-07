import javax.swing.*;
import java.awt.*;

public class RegionScreen {

    public RegionScreen(Game g, int index) {
        JFrame frame = new JFrame("Region Selection Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JButton leftButton = new JButton("<<<<");
        leftButton.addActionListener(e -> {
            if (index - 1 < 0) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        RegionScreen rS = new RegionScreen(g,
                                g.getUniverse().getRegionSize() - 1);
                        frame.dispose();
                    }
                });
            } else {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        RegionScreen rS = new RegionScreen(g, index - 1);
                        frame.dispose();
                    }
                });
            }
        });

        JButton rightButton = new JButton(">>>>");
        rightButton.addActionListener(e -> {
            if (index + 1 == g.getUniverse().getRegionSize()) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        RegionScreen rS = new RegionScreen(g, 0);
                        frame.dispose();
                    }
                });
            } else {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        RegionScreen rS = new RegionScreen(g, index + 1);
                        frame.dispose();
                    }
                });
            }
        });

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Current Region: "
                + g.getPlayer().getCurrentRegion().getName(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 5;
        centerPanel.add(new JLabel("Region Name: "
                + g.getUniverse().getRegion(index).getName(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 10;
        centerPanel.add(new JLabel("Coordinates [x, y]: ["
                + g.getUniverse().getRegion(index).getX() + ", "
                + g.getUniverse().getRegion(index).getY()
                + "]", SwingConstants.RIGHT), gbc);

        gbc.gridy = 20;
        centerPanel.add(new JLabel("Tech Level: "
                + g.getUniverse().getRegion(index).getTechLevel(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 30;
        JLabel travelStatus = new JLabel();
        centerPanel.add(travelStatus, gbc);

        JPanel bottomPanel = new JPanel();
        GridBagConstraints gbc2 = new GridBagConstraints();
        bottomPanel.setLayout(new GridBagLayout());

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JButton travelButton = new JButton();
        if (index == g.getPlayer().getCurrentRegionIndex()) {
            travelButton.setText("Go to Market");
        } else {
            travelButton.setText("Travel");
        }
        travelButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (travelButton.getText().equals("Go to Market")) {
                        RegionDetailsScreen rDS = new RegionDetailsScreen(g, index);
                        frame.dispose();
                    } else {
                        if (Travel.canTravel(Travel.fuel(g, Travel.distance(
                                g.getPlayer().getCurrentRegion(),
                                g.getUniverse().getRegion(index))), g)) {
                            travelStatus.setText(Travel.toString(true));
                            TravelConfirmationScreen rDS = new TravelConfirmationScreen(g,
                                    index);
                            frame.dispose();
                        } else {
                            travelStatus.setText(Travel.toString(false));
                        }
                    }
                }
            });
        });
        bottomPanel.add(travelButton, gbc2);

        gbc2.gridy++;
        JButton refuelButton = new JButton("Refuel");
        refuelButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (g.getPlayer().getInventory(0).getQuantity() > 0) {
                        RefuelScreen rS = new RefuelScreen(g, index);
                        frame.dispose();
                    } else {
                        travelStatus.setText("You have no fuel in inventory.");
                    }
                }
            });
        });
        bottomPanel.add(refuelButton, gbc2);

        gbc2.gridy++;
        JButton repairButton = new JButton("Repair");
        repairButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (g.getPlayer().getShip().getCurrentHealth() < g.
                            getPlayer().getShip().getmaxHealth()) {
                        RepairScreen rS = new RepairScreen(g, index);
                        frame.dispose();
                    } else {
                        travelStatus.setText("Your ship has sustained no damage.");
                    }
                }
            });
        });
        bottomPanel.add(repairButton, gbc2);

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(travelButton, BorderLayout.SOUTH);
        frame.getContentPane().add(leftButton, BorderLayout.WEST);
        frame.getContentPane().add(rightButton, BorderLayout.EAST);
        frame.setVisible(true);
    }
}