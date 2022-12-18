package gui.stickynote;

import javafx.scene.paint.Color;

public enum NoteColor {
    PURPLE(Color.rgb(255,101,163), "PURPLE"),
    BLUE(Color.rgb(122,252,255), "BLUE"),
    YELLOW(Color.rgb(255,247,64), "YELLOW"),
    GREEN(Color.rgb(116, 237, 75), "GREEN"),
    BEIGE(Color.rgb(245,255,156), "BEIGE");

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