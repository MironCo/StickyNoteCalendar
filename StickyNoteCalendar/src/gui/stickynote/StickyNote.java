package gui.stickynote;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import gui.DrawableUIElement;

import java.awt.Color;
import java.awt.Dimension;

import util.Vector2;


public class StickyNote extends DrawableUIElement {

    public Color noteColor = NoteColor.PURPLE.getColor();
    private Vector2 dimensions = new Vector2(150, 150);

    public StickyNote() {
        position = new Vector2(100, 10);
        setPreferredSize(new Dimension((int)dimensions.x, (int)dimensions.y));

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        Rectangle2D rect = new Rectangle2D.Double(position.x, position.y, dimensions.x, dimensions.y);
        g2d.setColor(noteColor);
        g2d.fill(rect);
    }
}
