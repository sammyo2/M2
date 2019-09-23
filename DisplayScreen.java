import javax.swing.*;
import java.awt.*;

public class DisplayScreen {

    public DisplayScreen(String name, String difficulty, int pilotField, int fighterField,
        int merchantField, int engineerField) {
               JFrame frame = new JFrame("Confirmation Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Name: " + name);
        gbc.gridx=0;
        gbc.gridy=0;
        centerPanel.add(nameLabel,gbc);

        JLabel difficultyLabel = new JLabel("Difficulty: " + difficulty);
        gbc.gridy++;
        centerPanel.add(difficultyLabel,gbc);

        JLabel pilotLabel = new JLabel("Pilot: " + pilotField);
        gbc.gridy++;
        centerPanel.add(pilotLabel,gbc);

        JLabel fighterLabel = new JLabel("Fighter: " + fighterField);
        gbc.gridy++;
        centerPanel.add(fighterLabel,gbc);

        JLabel merchantLabel = new JLabel("Merchant: " + merchantField);
        gbc.gridy++;
        centerPanel.add(merchantLabel,gbc);

        JLabel engineerLabel = new JLabel("Engineer: " + engineerField);
        gbc.gridy++;
        centerPanel.add(engineerLabel,gbc);

        int credits;
        if(difficulty.equals("Easy")) {
            credits = 1000;
        } else if(difficulty.equals("Medium")) {
            credits = 500;
        } else if(difficulty.equals("Hard")) {
            credits = 100;
        } else {
            credits = 0;
        }
        JLabel creditLabel = new JLabel("Credits: " + credits);
        gbc.gridy++;
        centerPanel.add(creditLabel,gbc);

        frame.setSize(500,500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DisplayScreen app = new DisplayScreen("Joe","Easy",5,5,5,5);
            }
        });
    }
}