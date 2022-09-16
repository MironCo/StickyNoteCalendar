package gui.colors;

import javafx.scene.paint.Color;

public class DarkColorTheme extends ColorTheme {
    public DarkColorTheme() {
        backgroundColor = Color.rgb(37, 35, 40);
        textColor = Color.WHITESMOKE;
        borderColor = Color.WHITESMOKE;
        toolbarColor = Color.rgb(45, 42, 54);
        buttonColor = Color.rgb((int)(45*1.5), (int)(42*1.5), (int)(54*1.5));
        buttonHighlightColor = Color.rgb(106, 111, 153);
    }
} 

