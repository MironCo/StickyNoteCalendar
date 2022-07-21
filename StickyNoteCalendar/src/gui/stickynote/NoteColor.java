package gui.stickynote;

import java.awt.Color;

public enum NoteColor {
    PURPLE(new Color(255,101,163)),
    BLUE(new Color(122,252,255)),
    YELLOW(new Color(255,247,64)),
    BEIGE(new Color(245,255,156));

    private final Color color;

    private NoteColor(final Color color) {
        this.color = color;
    }  

    public Color getColor() {
        return color;
    }
}