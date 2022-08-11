package main;

import main.calendar.Calendar;
import util.FontManager;
import gui.Toolbar;
import gui.stickynote.StickyNote;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application{
    public static double screenWidth = 1600;
    public static double screenHeight = 900;

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sticky Note Calendar");

        Pane layout = new Pane();
        ObservableList<Node> objectList = layout.getChildren();

        Scene scene = new Scene(layout, screenWidth, screenHeight);

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
    }
}
