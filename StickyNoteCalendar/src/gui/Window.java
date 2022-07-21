package gui;


import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import gui.stickynote.StickyNote;

public class Window {
    private final JFrame frame;
    private final JPanel panel;
    
    public Window(String title, int width, int height) {
        frame = new JFrame();
        panel = new JPanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Sticky Note Calendar");

        frame.add(panel);
        frame.getContentPane().add(panel);
        frame.setSize(width, height);
        frame.setPreferredSize(frame.getSize());
        panel.setSize(frame.getSize());
        panel.setPreferredSize(frame.getPreferredSize());
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        StickyNote test = new StickyNote();
        panel.add(test);

        frame.pack();
        frame.setVisible(true);
    }

    public void add(JComponent element) {
        frame.getContentPane().add(element);
    } 

    public void Draw() {
        frame.paint(frame.getGraphics());
    }

    public final JPanel getPanel() {
        return panel;
    }
}
