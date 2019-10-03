import javax.swing.*;
import java.awt.*;

public class RegionScreen {

    private final String[] regionNames =
            new String[]{"Z5", "X7", "K9", "P2", "D4", "H1", "Q0", "R3", "Y8", "A6", "K1", "P8", };

    public RegionScreen() {
        JFrame frame = new JFrame("Region Selection Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        JLabel label = new JLabel(
                "Test of Region Screen", SwingConstants.CENTER);
        frame.getContentPane().add(label, BorderLayout.NORTH);
        JButton selectButton = new JButton("Select Region");
        JButton leftButton = new JButton("<<<<");
        JButton rightButton = new JButton(">>>>");
        JPanel centerPanel = new JPanel();

        Region newRegion = new Region("name");
        Region[] regionsToDisplay = new Region[regionNames.length];
        for (int i = 0; i < regionNames.length; i++) {
            regionsToDisplay[i] = new Region(regionNames[i]);
        }

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(leftButton);
        bottomPanel.add(rightButton);

        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Region Name: " + regionsToDisplay[4].getName(), SwingConstants.RIGHT), gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        centerPanel.add(new JLabel("Coordinates [x, y]: [" + regionsToDisplay[4].getX() + ", " + regionsToDisplay[4].getY() + "]", SwingConstants.RIGHT), gbc);

        gbc.gridx = 0;
        gbc.gridy = 20;
        centerPanel.add(new JLabel("Tech Level: " + regionsToDisplay[4].getTechLevel(), SwingConstants.RIGHT), gbc);

        //int j = 0;
        /* while (j > -2) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            centerPanel.add(new JLabel("Region Name: " + regionsToDisplay[j].getName(), SwingConstants.RIGHT), gbc);

            gbc.gridx = 0;
            gbc.gridy = 10;
            centerPanel.add(new JLabel("Coordinates [x, y]: [" + regionsToDisplay[j].getX() + ", " + regionsToDisplay[j].getY() + "]", SwingConstants.RIGHT), gbc);

            gbc.gridx = 0;
            gbc.gridy = 20;
            centerPanel.add(new JLabel("Tech Level: " + regionsToDisplay[j].getTechLevel(), SwingConstants.RIGHT), gbc);

            rightButton.addActionListener(e -> {
                j++;
            });

        } */

        rightButton.addActionListener(e -> {
            for (int j = 1; j < regionsToDisplay.length; j++) {
                centerPanel.add(new JLabel("Region Name: " + regionsToDisplay[j].getName(), SwingConstants.RIGHT), gbc);
                centerPanel.add(new JLabel("Coordinates [x, y]: [" + regionsToDisplay[j].getX() + ", " + regionsToDisplay[j].getY() + "]", SwingConstants.RIGHT), gbc);
                centerPanel.add(new JLabel("Tech Level: " + regionsToDisplay[j].getTechLevel(), SwingConstants.RIGHT), gbc);

                j++;
            }
        });

        /* void displayRegion(Region r) {

            gbc.gridx = 0;
            gbc.gridy = 0;
            centerPanel.add(new JLabel("Region Name: " + r.getName(), SwingConstants.RIGHT), gbc);

            gbc.gridx = 0;
            gbc.gridy = 10;
            centerPanel.add(new JLabel("Coordinates [x, y]: [" + r.getX() + ", " + regionsToDisplay[0].getY() + "]", SwingConstants.RIGHT), gbc);

            gbc.gridx = 0;
            gbc.gridy = 20;
            centerPanel.add(new JLabel("Tech Level: " + r.getTechLevel(), SwingConstants.RIGHT), gbc);

        } */

        /* gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Region Name: " + regionsToDisplay[0].getName(), SwingConstants.RIGHT), gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        centerPanel.add(new JLabel("Coordinates [x, y]: [" + regionsToDisplay[0].getX() + ", " + regionsToDisplay[0].getY() + "]", SwingConstants.RIGHT), gbc);

        gbc.gridx = 0;
        gbc.gridy = 20;
        centerPanel.add(new JLabel("Tech Level: " + regionsToDisplay[0].getTechLevel(), SwingConstants.RIGHT), gbc); */

        /* rightButton.addActionListener(e -> {
            for (int i = 0; i < regionsToDisplay.length; i++) {
                displayRegion(regionsToDisplay[i]);
                i++;
            }
        }); */




        frame.setSize(500, 500);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(selectButton, BorderLayout.SOUTH);
        frame.getContentPane().add(leftButton, BorderLayout.WEST);
        frame.getContentPane().add(rightButton, BorderLayout.EAST);
        frame.setVisible(true);
    }

    /* private void displayRegion(Region r) {

        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        centerPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Region Name: " + r.getName(), SwingConstants.RIGHT), gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        centerPanel.add(new JLabel("Coordinates [x, y]: [" + r.getX() + ", " + r.getY() + "]", SwingConstants.RIGHT), gbc);

        gbc.gridx = 0;
        gbc.gridy = 20;
        centerPanel.add(new JLabel("Tech Level: " + r.getTechLevel(), SwingConstants.RIGHT), gbc);

    } */
}