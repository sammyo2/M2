import javax.swing.*;
import java.awt.*;

public class EndScreen {

    public EndScreen(Game g) {

        String name;
        if (g.getPlayer().getShip().getCurrentHealth() < 1) {
            name = "Loss Screen";
        } else {
            name = "Victory Screen";
        }
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel txt = new JLabel();
        if (name.equals("Loss Screen")) {
            txt.setText("You have lost the game.");
        } else {
            txt.setText("You have won the game!");
        }
        centerPanel.add(txt, gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("Would you like to start a new game?"), gbc);

        gbc.gridy++;
        centerPanel.add(new JLabel("All contents of this game will be lost."), gbc);

        gbc.gridy++;
        JButton newGame = new JButton("Yes");
        newGame.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    g.getSS().getJFrame().dispose();
                    g.getUM().getJFrame().dispose();
                    WelcomeScreen wS = new WelcomeScreen();
                    frame.dispose();
                }
            });
        });
        centerPanel.add(newGame, gbc);

        gbc.gridy++;
        JButton no = new JButton("No");
        no.addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    g.getSS().getJFrame().dispose();
                    g.getUM().getJFrame().dispose();
                    frame.dispose();
                    System.exit(0);
                }
            });
        });
        centerPanel.add(no, gbc);

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
