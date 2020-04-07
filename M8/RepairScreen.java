import javax.swing.*;
import java.awt.*;

public class RepairScreen {

    public RepairScreen(Game g, int index) {
        JFrame frame = new JFrame("Repair Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel txt = new JLabel();
        txt.setText("Repair your Ship!");
        centerPanel.add(txt, gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Current Health: "
                + g.getPlayer().getShip().getCurrentHealth(), SwingConstants.RIGHT), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Max Health Allowed: "
                + g.getPlayer().getShip().getmaxHealth(), SwingConstants.RIGHT), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Engineer Stat: "
                + g.getPlayer().getEngineerField(), SwingConstants.RIGHT), gbc);

        int q = g.getPlayer().getShip().getmaxHealth() - g.getPlayer().getShip().getCurrentHealth();
        if (g.getPlayer().getEngineerField() == 0) {
            q = (int) (q * 1.1);
        } else {
            q = q / g.getPlayer().getEngineerField();
        }
        q = q * 15;

        gbc.gridy++;
        centerPanel.add(new JLabel("Cost of full repair: "
                + q, SwingConstants.RIGHT), gbc);

        gbc.gridy++;
        JButton repairButton = new JButton("Repair");
        centerPanel.add(repairButton, gbc);
        repairButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    int q = g.getPlayer().getShip().getmaxHealth()
                            - g.getPlayer().getShip().getCurrentHealth();
                    if (g.getPlayer().getEngineerField() == 0) {
                        q = (int) (q * 1.1);
                    } else {
                        q = q / g.getPlayer().getEngineerField();
                    }
                    q = q * 15;
                    if (q > g.getPlayer().getCredits()) {
                        txt.setText("Not enough credits to repair.");
                    } else {
                        g.getPlayer().setCredits(g.getPlayer().getCredits() - q);
                        g.getPlayer().getShip().setCurrentHealth(g.
                                getPlayer().getShip().getmaxHealth());
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
