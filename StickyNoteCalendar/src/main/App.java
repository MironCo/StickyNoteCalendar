package main;

import main.calendar.Calendar;

import gui.Toolbar;
import gui.colors.ColorThemeManager;
import gui.stickynote.StickyNote;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application{
    public static double screenWidth = 1280;
    public static double screenHeight = 720;
    private static StickyNote currentlyEditingStickyNote = null;

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sticky Note Calendar");

        Pane layout = new Pane();
        ObservableList<Node> objectList = layout.getChildren();

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        scene.setFill(ColorThemeManager.getInstance().getCurrentColorTheme().backgroundColor);

        Calendar.getInstance().Init();
        Toolbar toolbar = new Toolbar();
        StickyNote note = new StickyNote();

        Calendar.getInstance().addNodes(objectList);
        toolbar.addTo(objectList);
        note.addTo(objectList);

        stage.setScene(scene);
        stage.show();

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            screenWidth = (int)stage.getWidth();
            screenHeight = (int)stage.getHeight();
            Calendar.getInstance().resizeCalendar();
        };

        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener); 

        stage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (currentlyEditingStickyNote != null && currentlyEditingStickyNote.isEditing()) {
                Text currentText = currentlyEditingStickyNote.getStickyNoteText();
                if (key.getCode() == KeyCode.BACK_SPACE && currentText.getText().length() > 0) {
                    currentText.setText(currentText.getText().substring(0, currentText.getText().length()-1));
                } else {
                    currentText.setText(currentText.getText() + key.getText());
                    if (currentlyEditingStickyNote.isTextOutsideBounds()) {
                        currentText.setText(currentText.getText().substring(0, currentText.getText().length()-1));
                        currentText.setText(currentText.getText() + "\n");
                        currentText.setText(currentText.getText() + key.getText());
                    }
                }
            }
        });
    }

    public static KeyEvent broadcastKeyPressed(KeyEvent key) {
        return key;
    }

    public static void setCurrentStickyNote(StickyNote note) {
        currentlyEditingStickyNote = note;
    }
}
