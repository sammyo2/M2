import javax.swing.*;
import java.awt.*;

public class ConfirmationScreen {

    public ConfirmationScreen(String name, String difficulty, int pilotField,
                              int fighterField, int merchantField, int engineerField) {
        JFrame frame = new JFrame("Confirmation Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        JLabel label = new JLabel(
                "Are these the settings you want to play with?", SwingConstants.CENTER);
        frame.getContentPane().add(label, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Name: " + name);
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(nameLabel, gbc);

        JLabel difficultyLabel = new JLabel("Difficulty: " + difficulty);
        gbc.gridy++;
        centerPanel.add(difficultyLabel, gbc);

        JLabel pilotLabel = new JLabel("Pilot: " + pilotField);
        gbc.gridy++;
        centerPanel.add(pilotLabel, gbc);

        JLabel fighterLabel = new JLabel("Fighter: " + fighterField);
        gbc.gridy++;
        centerPanel.add(fighterLabel, gbc);

        JLabel merchantLabel = new JLabel("Merchant: " + merchantField);
        gbc.gridy++;
        centerPanel.add(merchantLabel, gbc);

        JLabel engineerLabel = new JLabel("Engineer: " + engineerField);
        gbc.gridy++;
        centerPanel.add(engineerLabel, gbc);

        JLabel creditsLabel = new JLabel();
        gbc.gridy++;
        centerPanel.add(creditsLabel, gbc);
        if (difficulty.equals("Easy")) {
            creditsLabel.setText("Credits: 2000");
        } else if (difficulty.equals("Medium")) {
            creditsLabel.setText("Credits: 1500");
        } else {
            creditsLabel.setText("Credits: 1000");
        }

        JButton startButton = new JButton("Click to Confirm");
        startButton.setPreferredSize(new Dimension(100, 20));
        startButton.addActionListener(e ->
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        /* Game g = new Game(name, difficulty, pilotField, fighterField,
                                merchantField, engineerField); */
                        RegionScreen rS = new RegionScreen();
                        frame.dispose();
                    }
                }));

        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(startButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}