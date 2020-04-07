import javax.swing.*;
import java.awt.*;

public class NPCResultScreen {

    public NPCResultScreen(String s, boolean b, int index, Game g) {
        JFrame frame = new JFrame("NPC Result Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel(s), gbc);

        gbc.gridy = 5;
        JButton confirmButton = new JButton();
        if (b) {
            confirmButton.setText("Return to Starting Region");
        } else {
            confirmButton.setText("Continue Travel");
        }
        confirmButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    g.getPlayer().getShip().setCurrentFuel(g.
                            getPlayer().getShip().getCurrentFuel() - Travel.fuel(g,
                            Travel.distance(g.getPlayer().
                                    getCurrentRegion(), g.getUniverse().getRegion(index))));
                    if (!b) {
                        g.getPlayer().setCurrentRegionIndex(index);
                        g.getPlayer().setCurrentRegion(g.getUniverse().getRegion(index));
                    }
                    g.getSS().getJFrame().dispose();
                    ShipScreen newSS = new ShipScreen(g);
                    g.setSS(newSS);
                    if (g.getPlayer().getShip().getCurrentHealth() < 1) {
                        EndScreen eS = new EndScreen(g);
                    } else if (b) {
                        RegionDetailsScreen rDS = new RegionDetailsScreen(g, g.getPlayer().
                                getCurrentRegionIndex());
                    } else {
                        RegionDetailsScreen rDS = new RegionDetailsScreen(g, index);
                    }
                    frame.dispose();
                }
            });
        });
        centerPanel.add(confirmButton, gbc);

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
