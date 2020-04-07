import javax.swing.*;
import java.awt.*;

public class TraderScreen {

    public TraderScreen(Game g, Trader t, ShipScreen sS, int index) {
        JFrame frame = new JFrame("Trader Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JLabel tp = new JLabel();
        tp.setIcon(new ImageIcon(new ImageIcon("merchant.png").getImage().
                getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        topPanel.add(tp);

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("You have encountered a trader!"), gbc);

        gbc.gridy = 5;
        centerPanel.add(new JLabel("Item trader is offering: "
                + t.getItem().getName()), gbc);

        gbc.gridy = 10;
        centerPanel.add(new JLabel("Quantity: "
                + t.getQuantity()), gbc);

        gbc.gridy = 15;
        centerPanel.add(new JLabel("Price: "
                + t.getPrice()), gbc);

        JPanel bottomPanel = new JPanel();
        GridBagConstraints gbc2 = new GridBagConstraints();
        bottomPanel.setLayout(new GridBagLayout());

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JButton confirmButton = new JButton("Buy");
        confirmButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    TraderBuyScreen tBS = new TraderBuyScreen(g, t, sS, index);
                    frame.dispose();
                }
            });
        });
        bottomPanel.add(confirmButton, gbc2);

        gbc2.gridy = 5;
        JButton negotiateButton = new JButton("Negotiate Trade");
        negotiateButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    TradeNegotiationScreen tNS = new TradeNegotiationScreen(g, t,
                            t.negotiate(g), sS, index);
                    frame.dispose();
                }
            });
        });
        bottomPanel.add(negotiateButton, gbc2);

        gbc2.gridy = 10;
        JButton ignoreButton = new JButton("Ignore Trader");
        ignoreButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    NPCResultScreen n = new NPCResultScreen(t.ignore(), false, sS, index, g);
                    frame.dispose();
                }
            });
        });
        bottomPanel.add(ignoreButton, gbc2);

        gbc2.gridy = 15;
        JButton robButton = new JButton("Rob Trader");
        robButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    NPCResultScreen n = new NPCResultScreen(t.rob(g), false, sS, index, g);
                    frame.dispose();
                }
            });
        });
        bottomPanel.add(robButton, gbc2);

        frame.setSize(500, 500);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
