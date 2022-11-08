package gui.stickynote;

import java.awt.Color;

public enum NoteColor {
    PURPLE(new Color(255,101,163), "PURPLE"),
    BLUE(new Color(122,252,255), "BLUE"),
    YELLOW(new Color(255,247,64), "YELLOW"),
    BEIGE(new Color(245,255,156), "BEIGE");

    private final Color COLOR;
    private final String NAME;

    private NoteColor(final Color color, String name) {
        COLOR = color;
        NAME = name;
    }  

    public Color getColor() {
        return COLOR;
    }

    public String getName() {
        return NAME;
    }
}