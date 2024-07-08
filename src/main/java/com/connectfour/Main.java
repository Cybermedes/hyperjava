package com.connectfour;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Runnable game = ConnectFour::new;
        SwingUtilities.invokeLater(game);
    }
}
