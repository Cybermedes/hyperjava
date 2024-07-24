package com.connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConnectFour extends JFrame implements ActionListener {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char[] players = new char[]{'X', 'O'};
    private final JButton[][] board;
    private int turn;
    private int counter;
    private boolean isGameOver;

    ConnectFour() {
        // Game variables
        this.board = new JButton[ROWS][COLUMNS];
        this.turn = 0;
        this.counter = 1;
        this.isGameOver = false;

        // Game app dimensions
        setTitle("Connect Four");
        setSize(new Dimension(600,600));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel with the 42 buttons on top
        JPanel panel = getButtonsPanel();
        add(panel, BorderLayout.CENTER);

        // Panel with the 'Reset' button at the bottom
        JPanel resetPanel = getResetPanel();
        add(resetPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // Create a panel with the 'Reset' button
    private JPanel getResetPanel() {
        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton resetButton = new JButton("Reset");
        resetButton.setBackground(ComponentColors.YELLOW.getColor());
        resetButton.setFont(new Font("Liberation Mono", Font.BOLD, 40));
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(actionEvent -> {
            resetBoard();
            setGameOver(false);
        });
        resetPanel.add(resetButton);
        return resetPanel;
    }

    // Create a panel with all 42 buttons
    private JPanel getButtonsPanel() {
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
                    button.setBackground(ComponentColors.DARK_GREEN.getColor());
                } else {
                    button.setBackground(ComponentColors.LIGHT_GREEN.getColor());
                }
                counter++;
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                button.setFont(new Font("Liberation Mono", Font.BOLD, 40));
                button.setFocusPainted(false);
                button.addActionListener(this);
                panel.add(button);
                board[ROWS - 1 - i][j] = button;
            }
        }
        return panel;
    }

    // Set the button text to 'X' or 'O' when pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        char label = players[this.turn % players.length];
        int column = ((JButton) e.getSource()).getName().charAt("Button".length()) - 'A';
        for (int i = 0; i < ROWS; i++) {
            if (this.board[i][column].getText().isEmpty()) {
                this.board[i][column].setText(String.valueOf(label));
                break;
            }
        }
        this.turn++;
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
        this.turn = 0;
    }

    private void resetBoard() {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                this.board[i][j].setText("");
            }
        }
    }
}
