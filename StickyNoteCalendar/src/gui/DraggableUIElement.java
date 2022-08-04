package gui;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public abstract class DraggableUIElement extends DrawableUIElement implements MouseInputListener {
    
    public DraggableUIElement() {
        addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
    
    }

    public void mouseExited(MouseEvent e) {
      
    }

    public void mouseClicked(MouseEvent e) {
   
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    
    }
} 
