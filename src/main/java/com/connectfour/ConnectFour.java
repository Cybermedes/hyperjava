package com.connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConnectFour extends JFrame implements ActionListener {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final Color lightGreen = new Color(156, 204, 101);
    private static final Color darkGreen = new Color(175, 213, 130);
    private static final char[] players = new char[]{'X', 'O'};
    private int turn;
    private int counter;

    ConnectFour() {
        this.turn = 0;
        this.counter = 1;

        setTitle("Connect Four");
        setSize(new Dimension(500,500));
        setResizable(false);
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
                button.setName("Button" + label);
                if (counter % 2 == 0) {
                    button.setBackground(darkGreen);
                } else {
                    button.setBackground(lightGreen);
                }
                counter++;
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                button.setFont(new Font("Liberation Mono", Font.BOLD, 40));
                button.setFocusPainted(false);
                button.addActionListener(this);
                panel.add(button);
            }
        }
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char label = players[this.turn % players.length];
        ((JButton) e.getSource()).setText(String.valueOf(label));
        this.turn++;
    }
}
