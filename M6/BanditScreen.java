import javax.swing.*;
import java.awt.*;

public class BanditScreen {

    public BanditScreen(Game g, ShipScreen sS, int index) {
        JFrame frame = new JFrame("Bandit Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JLabel tp = new JLabel();
        tp.setIcon(new ImageIcon(new ImageIcon("bandit.png").getImage().
                getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        topPanel.add(tp);

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("You have encountered a bandit!"), gbc);

        JPanel bottomPanel = new JPanel();
        GridBagConstraints gbc2 = new GridBagConstraints();
        bottomPanel.setLayout(new GridBagLayout());

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JButton payButton = new JButton("Pay Demands");
        payButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    NPCResultScreen n = new NPCResultScreen(Bandit.pay(g), false, sS, index, g);
                    frame.dispose();
                }
            });
        });
        bottomPanel.add(payButton, gbc2);

        gbc2.gridy = 5;
        JButton fleeButton = new JButton("Flee");
        fleeButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    NPCResultScreen n = new NPCResultScreen(Bandit.flee(g), true, sS, index, g);
                    frame.dispose();
                }
            });
        });
        bottomPanel.add(fleeButton, gbc2);

        gbc2.gridy = 10;
        JButton fightButton = new JButton("Fight the bandit");
        fightButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    NPCResultScreen n = new NPCResultScreen(Bandit.fight(g), false, sS, index, g);
                    frame.dispose();
                }
            });
        });
        bottomPanel.add(fightButton, gbc2);

        frame.setSize(500, 500);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
