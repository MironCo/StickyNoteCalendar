package gui.button.presets;

import gui.button.GUIButton;
import gui.popupmenu.PopuppableUIElement;
import gui.popupmenu.PresetStickyNotePopupMenu;
import gui.stickynote.NoteColor;
import gui.stickynote.StickyNote;
import gui.stickynote.StickyNoteManager;
import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import main.App;
import util.FontManager;

public class AddPresetStickyNote extends GUIButton implements PopuppableUIElement {
    private String textContents = "";
    private NoteColor buttonColor;
    private StickyNote createdStickyNote;
    private TextField textField;
    private PresetStickyNoteBean bean;

    public AddPresetStickyNote(PresetStickyNoteBean bean, double x, double y, int width) {
        super("", x, y, width);
        this.bean = bean;
        this.textContents = bean.getPresetStickyNoteText();
        buttonColor = bean.getPresetStickyNoteColor();

        buttonText.setVisible(false);

        textField = new TextField(textContents);
        textField.setFont(FontManager.loadFont("Nunito-Regular.ttf", 20));
        textField.setLayoutX(graphic.getLayoutX());
        textField.setMaxWidth(graphic.getWidth());
        textField.setLayoutY(graphic.getLayoutY());

        stopEditingText();

        textField.textProperty().addListener((o, oldValue, newValue) -> {
            this.textContents = newValue;
            bean.setPresetStickyNoteText(this.textContents);
        });

        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                stopEditingText();
            }
        });

        setPopupMenu();
        getButtonPane().getChildren().add(textField);
        setOriginalColor(buttonColor.getColor());
        setMouseActions();
    }

    public void setMouseActions() {
        getButtonPane().setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() > 1) {
                    PresetManager.getInstance().setCurrentlyEditingStickyNote(this);
                }
            }
        });

        getButtonPane().setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (!App.getMainToolbar().getToolbarGraphic().contains(new Point2D(App.getMousePosition().x, App.getMousePosition().y))) {
                    double centerOffset = 0;
                    
                    if (createdStickyNote == null) {
                        createdStickyNote = new StickyNote(textContents);
                        if (buttonColor != null) createdStickyNote.setColor(buttonColor);
                        StickyNoteManager.getInstance().addStickyNote(createdStickyNote);
                        StickyNoteManager.getInstance().setDraggedStickyNote(createdStickyNote);
                    }

                    if (createdStickyNote != null) {
                        centerOffset = createdStickyNote.getRectangle().getBoundsInLocal().getHeight() / 2;
                        createdStickyNote.getNodes().get(0).setTranslateX(e.getSceneX() - centerOffset);
                        createdStickyNote.getNodes().get(0).setTranslateY(e.getSceneY() - centerOffset);
                    }
                } else {
                    if (createdStickyNote != null) {
                        StickyNoteManager.getInstance().deleteStickyNote(createdStickyNote);
                        createdStickyNote = null;
                    }
                }
            }
        });

        getButtonPane().setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (createdStickyNote != null) {
                    createdStickyNote.ReleaseStickyNote();
                    createdStickyNote = null;
                }
            }
        });
    }

    public void startEditingText() {
        App.getMainToolbar().stopEditingText();
        textField.setEditable(true);
        textField.setMouseTransparent(false);
        textField.selectEnd();
    }

    public void stopEditingText() {
        textField.setEditable(false);
        textField.setMouseTransparent(true);
        textField.deselect();
    }

    @Override
    public void performAction() {
        StickyNote note = new StickyNote(textContents);
        note.setColor(buttonColor);
        StickyNoteManager.getInstance().addStickyNote(note);    
    }

    public void setColor(NoteColor color) {
        buttonColor = color;
        bean.setPresetStickyNoteColor(buttonColor);
        setOriginalColor(buttonColor.getColor());
        graphic.setFill(color.getColor());
    }

    @Override
    public void updateColors() {

    }

    @Override
    public void setPopupMenu() {        
        popupMenu = PresetStickyNotePopupMenu.getInstance().getContextMenu();

        getButtonPane().setOnContextMenuRequested(e -> {
            PresetManager.getInstance().setRightClickedPresetStickyNote(this);
            popupMenu.show(getButtonPane(), e.getScreenX(), e.getScreenY());
        });
    }

    public PresetStickyNoteBean getBean() {
        return bean;
    }
}
