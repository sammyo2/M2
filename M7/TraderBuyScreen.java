import javax.swing.*;
import java.awt.*;

public class TraderBuyScreen {

    public TraderBuyScreen(Game g, Trader t, int index) {
        JFrame frame = new JFrame("Confirm your Trade");
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
        centerPanel.add(new JLabel("Item: " + t.getItem().getName()), gbc);

        gbc.gridy = 5;
        centerPanel.add(new JLabel("Quantity: "
                + t.getQuantity()), gbc);

        gbc.gridy = 10;
        centerPanel.add(new JLabel("Price: "
                + t.getPrice()), gbc);

        JPanel bottomPanel = new JPanel();
        GridBagConstraints gbc2 = new GridBagConstraints();
        bottomPanel.setLayout(new GridBagLayout());

        gbc.gridy = 15;
        centerPanel.add(new JLabel("Quantity to Purchase:", SwingConstants.RIGHT), gbc);

        gbc.gridy++;
        JTextField qField = new JTextField(5);
        centerPanel.add(qField, gbc);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JButton confirmButton = new JButton("Confirm Trade");
        bottomPanel.add(confirmButton, gbc2);

        gbc2.gridy = 5;
        JButton negotiateButton = new JButton("Return to Trader Interactions");
        bottomPanel.add(negotiateButton, gbc2);

        gbc2.gridy++;
        JLabel error = new JLabel();
        bottomPanel.add(error, gbc2);

        confirmButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    int q = Integer.parseInt(qField.getText());
                    if (q < 1) {
                        error.setText("Quantity to purchase must be 1 or greater.");
                    } else {
                        if (t.buy(q, g)) {
                            NPCResultScreen n = new NPCResultScreen(t.buy(g, q),
                                    false, index, g);
                            frame.dispose();
                        } else {
                            error.setText(t.buy(g, q));
                        }
                    }
                }
            });
        });

        negotiateButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    TraderScreen tS = new TraderScreen(g, t, index);
                    frame.dispose();
                }
            });
        });

        frame.setSize(500, 500);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
