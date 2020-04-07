import javax.swing.*;
import java.awt.*;

public class ShipScreen {

    private JFrame frame;

    public ShipScreen(Game g) {
        JFrame frame = new JFrame("Ship Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        this.frame = frame;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frame.getWidth()) / 2,
                0);

        JPanel centerPanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Ship Type: "
                + g.getPlayer().getShip().getType(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 5;
        centerPanel.add(new JLabel("Ship Name: "
                + g.getPlayer().getShip().getName(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 10;
        centerPanel.add(new JLabel("Current Cargo: "
                + g.getPlayer().getShip().getcurrentCargoSpace(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 15;
        centerPanel.add(new JLabel("Max Cargo: "
                + g.getPlayer().getShip().getMaxCargoSpace(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 20;
        centerPanel.add(new JLabel("Current Fuel: "
                + g.getPlayer().getShip().getCurrentFuel(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 25;
        centerPanel.add(new JLabel("Max Fuel: "
                + g.getPlayer().getShip().getMaxFuel(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 30;
        centerPanel.add(new JLabel("Current Health: "
                + g.getPlayer().getShip().getCurrentHealth(), SwingConstants.RIGHT), gbc);

        gbc.gridy = 35;
        centerPanel.add(new JLabel("Max Health: "
                + g.getPlayer().getShip().getmaxHealth(), SwingConstants.RIGHT), gbc);

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public JFrame getJFrame() {
        return frame;
    }
}