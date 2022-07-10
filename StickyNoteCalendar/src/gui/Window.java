package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Window {
    private JFrame frame;
    private JPanel panel;
    
    public Window(String title, int width, int height) {
        frame = new JFrame();
        panel = new JPanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setWindowTitle(title);
        frame.add(panel);
        frame.setSize(width, height);
    }

    public void setWindowTitle(String title) {
        frame.setTitle(title);
    }

    public void Open() {
        frame.setVisible(true);
    }
}
