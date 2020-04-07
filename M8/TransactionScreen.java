import javax.swing.*;
import java.awt.*;

public class TransactionScreen {
    public TransactionScreen(Game g, int index, String label) {
        JFrame frame = new JFrame();
        if (label.startsWith("B")) {
            frame.setTitle("Buy Transaction Screen");
        } else {
            frame.setTitle("Sell Transaction Screen");
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton exitButton = new JButton("Return to Region Details Screen");
        exitButton.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    RegionDetailsScreen rS = new RegionDetailsScreen(g, index);
                    frame.dispose();
                }
            });
        });
        centerPanel.add(exitButton, gbc);

        int itemIndex = Integer.parseInt(label.substring(label.length() - 1));

        gbc.gridy++;
        centerPanel.add(new JLabel("Item: "
                + g.getPlayer().getInventory(itemIndex).getName()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Price: " + g.getUniverse().
                getRegion(index).getMarket(itemIndex).getPrice()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Market Quantity of Item: " + g.getUniverse().
                getRegion(index).getMarket(itemIndex).getQuantity()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Current Inventory of Item: " + g.getPlayer().
                getInventory(itemIndex).getQuantity()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Current Cargo: " + g.getPlayer().getShip().
                getcurrentCargoSpace()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Max Cargo Space: " + g.getPlayer().getShip().
                getMaxCargoSpace()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Current Credits: " + g.getPlayer().getCredits()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Merchant level: " + g.getPlayer().getMerchantField()), gbc);

        gbc.gridy++;
        JLabel transaction = new JLabel();
        if (label.startsWith("B")) {
            transaction.setText("Buy Quantity");
        } else {
            transaction.setText("Sell Quantity");
        }
        centerPanel.add(transaction, gbc);

        gbc.gridy++;
        JTextField quantity = new JTextField(10);
        centerPanel.add(quantity, gbc);

        JPanel bottomPanel = new JPanel();
        GridBagConstraints gbc2 = new GridBagConstraints();
        bottomPanel.setLayout(new GridBagLayout());

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        JButton transactionButton = new JButton();
        if (label.startsWith("B")) {
            transactionButton.setText("Attempt Purchase");
        } else {
            transactionButton.setText("Attempt Exchange");
        }
        bottomPanel.add(transactionButton, gbc2);

        gbc2.gridy++;
        JLabel error = new JLabel();
        bottomPanel.add(error, gbc2);

        gbc2.gridy++;
        JLabel error2 = new JLabel();
        bottomPanel.add(error2, gbc2);

        gbc2.gridy++;
        JLabel error3 = new JLabel();
        bottomPanel.add(error3, gbc2);

        gbc2.gridy++;
        JLabel error4 = new JLabel();
        bottomPanel.add(error4, gbc2);

        transactionButton.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    int q = Integer.parseInt(quantity.getText());
                    if (q < 0) {
                        error.setText("Quantity cannot be negative.");
                    } else {
                        if (label.startsWith("B")) {
                            boolean e2 = Transaction.buyQuantityCheckBoolean(itemIndex,
                                    q, g, g.getUniverse().getRegion(index));
                            error2.setText(Transaction.buyQuantityCheckString(e2));
                            boolean e3 = Transaction.buyCargoCheckBoolean(q, g);
                            error3.setText(Transaction.buyCargoCheckString(e3));
                            int price = Transaction.buyPriceCalculator(itemIndex,
                                    q, g.getUniverse().getRegion(index), g);
                            boolean e4 = Transaction.buyPriceCheckerBoolean(g, price);
                            error4.setText(Transaction.buyPriceCheckerString(e4));
                            if (e2 && e3 && e4) {
                                TransactionConfirmationScreen tCS =
                                        new TransactionConfirmationScreen(itemIndex,
                                                q, g, index, label, price);
                                frame.dispose();
                            }
                        } else {
                            boolean e2 = Transaction.sellQuantityCheckBoolean(itemIndex,
                                    q, g, g.getUniverse().getRegion(index));
                            error2.setText(Transaction.sellQuantityCheckString(e2));
                            if (e2) {
                                int price = Transaction.exchangePriceCalculator(itemIndex,
                                        q, g.getUniverse().getRegion(index), g);
                                TransactionConfirmationScreen tCS =
                                        new TransactionConfirmationScreen(itemIndex,
                                                q, g, index, label, price);
                                frame.dispose();
                            }
                        }
                    }
                }
            });
        });

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}