import javax.swing.*;
import java.awt.*;

public class ConfigurationScreen {
    public ConfigurationScreen() {
        JFrame frame = new JFrame("Configuration Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        JLabel mainlabel = new JLabel("Fill out all the following", SwingConstants.CENTER);
        frame.getContentPane().add(mainlabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());
        JLabel difficultyLabel = new JLabel();
        JLabel pointsLabel = new JLabel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Name:", SwingConstants.RIGHT), gbc);

        gbc.gridx = 2;
        JTextField nameField = new JTextField(5);
        centerPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(new JLabel("Difficulty:", SwingConstants.RIGHT), gbc);

        gbc.gridx = 2;
        JButton easyButton = new JButton("Easy");
        centerPanel.add(easyButton, gbc);
        easyButton.addActionListener(e -> {
            difficultyLabel.setText("Easy");
            pointsLabel.setText("20");
        });

        gbc.gridx = 4;
        JButton mediumButton = new JButton("Medium");
        centerPanel.add(mediumButton, gbc);
        mediumButton.addActionListener(e -> {
            difficultyLabel.setText("Medium");
            pointsLabel.setText("15");
        });

        gbc.gridx = 6;
        JButton hardButton = new JButton("Hard");
        centerPanel.add(hardButton, gbc);
        hardButton.addActionListener(e -> {
            difficultyLabel.setText("Hard");
            pointsLabel.setText("10");
        });

        gbc.gridx = 4;
        gbc.gridy++;
        centerPanel.add(difficultyLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(new JLabel("Total # of points:", SwingConstants.RIGHT), gbc);

        gbc.gridx = 2;
        centerPanel.add(pointsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = gbc.gridy + 2;
        centerPanel.add(new JLabel("Skill", SwingConstants.RIGHT), gbc);

        gbc.gridx = 2;
        centerPanel.add(new JLabel("Points"), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(new JLabel("Pilot", SwingConstants.RIGHT), gbc);

        gbc.gridx = 2;
        JTextField pilotField = new JTextField(5);
        centerPanel.add(pilotField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(new JLabel("Fighter", SwingConstants.RIGHT), gbc);

        gbc.gridx = 2;
        JTextField fighterField = new JTextField(5);
        centerPanel.add(fighterField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(new JLabel("Merchant", SwingConstants.RIGHT), gbc);

        gbc.gridx = 2;
        JTextField merchantField = new JTextField(5);
        centerPanel.add(merchantField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(new JLabel("Engineer", SwingConstants.RIGHT), gbc);

        gbc.gridx = 2;
        JTextField engineerField = new JTextField(5);
        centerPanel.add(engineerField, gbc);

        JLabel doneLabel = new JLabel();

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> {
                int sum = Integer.parseInt(pilotField.getText()) + Integer.parseInt(fighterField.getText()) +
                        Integer.parseInt(merchantField.getText()) + Integer.parseInt(engineerField.getText());
                if ((difficultyLabel.getText().equals("Easy") && sum > 20) ||
                        (difficultyLabel.getText().equals("Medium") && sum > 15) ||
                        (difficultyLabel.getText().equals("Hard") && sum > 10)) {
                    doneLabel.setText("Too many points have been allocated. Please reduce to specified amount.");
                } else {
                    doneLabel.setText("Points allocated correctly.");
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            ConfirmationScreen app = new ConfirmationScreen();
                        }
                }
        );}});

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(doneButton);
        bottomPanel.add(doneLabel);

        frame.setSize(1000,1000);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ConfigurationScreen app = new ConfigurationScreen();
            }
        });
    }
}
