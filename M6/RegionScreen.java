import javax.swing.*;
import java.awt.*;

public class RegionScreen {

    public RegionScreen(Game g, int index, ShipScreen sS) {
        JFrame frame = new JFrame("Region Selection Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JButton leftButton = new JButton("<<<<");
        leftButton.addActionListener(e -> {
            if (index - 1 < 0) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        RegionScreen rS = new RegionScreen(g,
                                g.getUniverse().getRegionSize() - 1, sS);
                        frame.dispose();
                    }
                });
            } else {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        RegionScreen rS = new RegionScreen(g, index - 1, sS);
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
                        RegionScreen rS = new RegionScreen(g, 0, sS);
                        frame.dispose();
                    }
                });
            } else {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        RegionScreen rS = new RegionScreen(g, index + 1, sS);
                        frame.dispose();
                    }
                });
            }
        });

        JPanel centerPanel = new JPanel();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(leftButton);
        bottomPanel.add(rightButton);

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

        JButton travelButton = new JButton("Travel");
        travelButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (Travel.canTravel(Travel.fuel(g, Travel.distance(
                            g.getPlayer().getCurrentRegion(),
                            g.getUniverse().getRegion(index))), g)) {
                        travelStatus.setText(Travel.toString(true));
                        TravelConfirmationScreen rDS = new TravelConfirmationScreen(g, index, sS);
                        frame.dispose();
                    } else {
                        travelStatus.setText(Travel.toString(false));
                    }

                }
            });
        });

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(travelButton, BorderLayout.SOUTH);
        frame.getContentPane().add(leftButton, BorderLayout.WEST);
        frame.getContentPane().add(rightButton, BorderLayout.EAST);
        frame.setVisible(true);
    }
}