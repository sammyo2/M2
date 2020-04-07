import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegionDetailsScreen {

    public RegionDetailsScreen(Game g, int index, ShipScreen sS) {
        JFrame frame = new JFrame("Region Details Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        topPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(new JLabel("Region Name: "
                + g.getUniverse().getRegion(index).getName(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 10;
        topPanel.add(new JLabel("Coordinates [x, y]: ["
                + g.getUniverse().getRegion(index).getX() + ", "
                + g.getUniverse().getRegion(index).getY()
                + "]", SwingConstants.RIGHT), gbc);

        gbc.gridy = 20;
        topPanel.add(new JLabel("Tech Level: "
                + g.getUniverse().getRegion(index).getTechLevel(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 30;
        topPanel.add(new JLabel("Item : Market Quantity : "
                + "Price : Buy Button : Inventory : Sell Button",
                SwingConstants.RIGHT), gbc);

        JPanel centerPanel = new JPanel();
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(new Object[][] {
                {g.getPlayer().getInventory(0).getName(), g.getUniverse().getRegion(index).
                        getMarket(0).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(0).getPrice(), "Buy Index 0", g.getPlayer().getInventory(0).
                        getQuantity(), "Sell Index 0"},
                {g.getPlayer().getInventory(1).getName(), g.getUniverse().getRegion(index).
                        getMarket(1).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(1).getPrice(), "Buy Index 1", g.getPlayer().getInventory(1).
                        getQuantity(), "Sell Index 1"},
                {g.getPlayer().getInventory(2).getName(), g.getUniverse().getRegion(index).
                        getMarket(2).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(2).getPrice(), "Buy Index 2", g.getPlayer().getInventory(2).
                        getQuantity(), "Sell Index 2"},
                {g.getPlayer().getInventory(3).getName(), g.getUniverse().getRegion(index).
                        getMarket(3).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(3).getPrice(), "Buy Index 3", g.getPlayer().getInventory(3).
                        getQuantity(), "Sell Index 3"},
                {g.getPlayer().getInventory(4).getName(), g.getUniverse().getRegion(index).
                        getMarket(4).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(4).getPrice(), "Buy Index 4", g.getPlayer().getInventory(4).
                        getQuantity(), "Sell Index 4"},
                {g.getPlayer().getInventory(5).getName(), g.getUniverse().getRegion(index).
                        getMarket(5).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(5).getPrice(), "Buy Index 5", g.getPlayer().getInventory(5).
                        getQuantity(), "Sell Index 5"},
                {g.getPlayer().getInventory(6).getName(), g.getUniverse().getRegion(index).
                        getMarket(6).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(6).getPrice(), "Buy Index 6", g.getPlayer().getInventory(6).
                        getQuantity(), "Sell Index 6"},
                {g.getPlayer().getInventory(7).getName(), g.getUniverse().getRegion(index).
                        getMarket(7).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(7).getPrice(), "Buy Index 7", g.getPlayer().getInventory(7).
                        getQuantity(), "Sell Index 7"},
                {g.getPlayer().getInventory(8).getName(), g.getUniverse().getRegion(index).
                        getMarket(8).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(8).getPrice(), "Buy Index 8", g.getPlayer().getInventory(8).
                        getQuantity(), "Sell Index 8"},
                {g.getPlayer().getInventory(9).getName(), g.getUniverse().getRegion(index).
                        getMarket(9).getQuantity(), g.getUniverse().getRegion(index).
                        getMarket(9).getPrice(), "Buy Index 9", g.getPlayer().getInventory(9).
                        getQuantity(), "Sell Index 9"}},
                new Object[] {"Item", "Market Quantity", "Price", "Buy Button",
                              "Inventory", "Sell Button"});
        JTable table = new JTable(dm);
        table.getColumn("Buy Button").setCellRenderer(new ButtonRenderer());
        table.getColumn("Buy Button").setCellEditor(
                new ButtonEditor(new JCheckBox(), g, index, frame, sS));
        table.getColumn("Sell Button").setCellRenderer(new ButtonRenderer());
        table.getColumn("Sell Button").setCellEditor(
                new ButtonEditor(new JCheckBox(), g, index, frame, sS));
        centerPanel.add(table);

        JButton exitButton = new JButton("Back to Region Selection");
        exitButton.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    RegionScreen rS = new RegionScreen(g, index, sS);
                    frame.dispose();
                }
            });
        });

        frame.setSize(500, 500);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(exitButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}