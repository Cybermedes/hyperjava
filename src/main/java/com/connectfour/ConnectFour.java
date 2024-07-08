package com.connectfour;

import javax.swing.*;
import java.awt.*;

class ConnectFour extends JFrame {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    ConnectFour() {
        setTitle("Connect Four");
        setSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = getjPanel();
        add(panel);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private JPanel getjPanel() {
        JPanel panel = new JPanel();
        panel.setName("Buttons");
        panel.setLayout(new GridLayout(ROWS, COLUMNS));

        for (int i = 0; i < ROWS; i++) {
            char rowLabel = 'A';
            for (int j = 0; j < COLUMNS; j++) {
                String label = String.format("%c%d", rowLabel + j, ROWS - i);
                JButton button = new JButton();
                button.setText(label);
                button.setName("Button" + label);
                button.setFocusPainted(false);
                panel.add(button);
            }
        }
        return panel;
    }

}
