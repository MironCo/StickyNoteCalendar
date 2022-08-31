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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application{
    public static double screenWidth = 1280;
    public static double screenHeight = 720;
    private static StickyNote currentlyEditingStickyNote = null;

    private static ObservableList<Node> objectList = null;

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sticky Note Calendar");

        Pane layout = new Pane();
        objectList = layout.getChildren();

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        scene.setFill(ColorThemeManager.getInstance().getCurrentColorTheme().backgroundColor);

        Calendar.getInstance().Init();
        Calendar.getInstance().addNodes(objectList);    

        Toolbar toolbar = new Toolbar();
        StickyNote note = new StickyNote();
            
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
                currentlyEditingStickyNote.ChangeStickNoteText(key);
            }
        });
       
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            for (Node node : objectList) {
                node.setScaleY((double)newVal / (double)screenHeight);
            }
        });
    }

    public static KeyEvent broadcastKeyPressed(KeyEvent key) {
        return key;
    }

    public static void setCurrentStickyNote(StickyNote note) {
        currentlyEditingStickyNote = note;
    }

    public static void AddToScene(List<Node> nodes) {
        objectList.addAll(nodes);
    }
}
