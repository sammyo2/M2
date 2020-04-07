import javax.swing.*;
import java.awt.*;

public class TransactionConfirmationScreen {

    public TransactionConfirmationScreen(int itemIndex, int q, Game g, int index,
                                         String label, int price) {
        JFrame frame = new JFrame();
        if (label.startsWith("B")) {
            frame.setTitle("Buy Confirmation Screen");
        } else {
            frame.setTitle("Sell Confirmation Screen");
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton exitButton = new JButton("Return to Transaction Screen");
        exitButton.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    TransactionScreen tS = new TransactionScreen(g, index, label);
                    frame.dispose();
                }
            });
        });
        centerPanel.add(exitButton, gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Item: " + g.getPlayer().getInventory(itemIndex).
                getName()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Price: " + g.getUniverse().getRegion(index).
                getMarket(itemIndex).getPrice()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Merchant level: " + g.getPlayer().getMerchantField()), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Quantity being bought/sold: " + q), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Market Quantity: " + g.getUniverse().getRegion(index).
                getMarket(itemIndex).getQuantity()), gbc);

        gbc.gridy++;
        JLabel marketQ = new JLabel();
        centerPanel.add(marketQ, gbc);
        if (label.startsWith("B")) {
            marketQ.setText("Market Quantity (After buying): " + (g.getUniverse().getRegion(index).
                    getMarket(itemIndex).getQuantity() - q));
        } else {
            marketQ.setText("Market Quantity (After selling inventory): " + (g.getUniverse().
                    getRegion(index).getMarket(itemIndex).getQuantity() + q));
        }

        gbc.gridy++;
        centerPanel.add(new JLabel("Current Inventory of Item: " + g.getPlayer().
                getInventory(itemIndex).getQuantity()), gbc);

        gbc.gridy++;
        JLabel inventoryQ = new JLabel();
        centerPanel.add(inventoryQ, gbc);
        if (label.startsWith("B")) {
            inventoryQ.setText("Inventory Quantity (After buying): "
                    + (g.getPlayer().getInventory(itemIndex).getQuantity() + q));
        } else {
            inventoryQ.setText("Inventory Quantity (After selling inventory): "
                    + (g.getPlayer().getInventory(itemIndex).getQuantity() - q));
        }

        gbc.gridy++;
        centerPanel.add(new JLabel("Current Cargo: "
                + g.getPlayer().getShip().getcurrentCargoSpace()), gbc);

        gbc.gridy++;
        JLabel newCargo = new JLabel();
        centerPanel.add(newCargo, gbc);
        if (label.startsWith("B")) {
            newCargo.setText("Cargo (After buying): "
                    + (g.getPlayer().getShip().getcurrentCargoSpace() + q));
        } else {
            newCargo.setText("Cargo (After selling inventory): "
                    + (g.getPlayer().getShip().getcurrentCargoSpace() - q));
        }

        gbc.gridy++;
        centerPanel.add(new JLabel("Current Credits: " + g.getPlayer().getCredits()), gbc);

        gbc.gridy++;
        JLabel totalCredits = new JLabel();
        centerPanel.add(totalCredits, gbc);
        if (label.startsWith("B")) {
            totalCredits.setText("Total Purchase Price: " + price);
        } else {
            totalCredits.setText("Total Exchange Price: " + price);
        }

        gbc.gridy++;
        JLabel newCredits = new JLabel();
        centerPanel.add(newCredits, gbc);
        if (label.startsWith("B")) {
            newCredits.setText("Credits (After buying): "
                    + (g.getPlayer().getCredits() - price));
        } else {
            newCredits.setText("Credits (After selling inventory): "
                    + (g.getPlayer().getCredits() + price));
        }

        gbc.gridy++;
        JButton confirmButton = new JButton("Confirm Transaction");
        confirmButton.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (label.startsWith("B")) {
                        Transaction.buyTransaction(price, g, g.getUniverse().
                                getRegion(index), itemIndex, q);
                        g.getSS().getJFrame().dispose();
                        ShipScreen newSS = new ShipScreen(g);
                        g.setSS(newSS);
                        TransactionScreen tS = new TransactionScreen(g, index, label);
                        frame.dispose();
                    } else {
                        Transaction.sellTransaction(price, g, g.getUniverse().
                                getRegion(index), itemIndex, q);
                        g.getSS().getJFrame().dispose();
                        ShipScreen newSS = new ShipScreen(g);
                        g.setSS(newSS);
                        TransactionScreen tS = new TransactionScreen(g, index, label);
                        frame.dispose();
                    }
                }
            });
        });
        centerPanel.add(confirmButton, gbc);

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
