package com.connectfour;

import java.awt.*;

public enum ComponentColors {

    // Colors for the GUI components
    LIGHT_GREEN(new Color(156, 204, 101)),
    DARK_GREEN(new Color(175, 213, 130)),
    YELLOW(new Color(255, 213, 80)),
    MATCH_GREEN(new Color(0, 230, 118));

    private final Color color;

    ComponentColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
