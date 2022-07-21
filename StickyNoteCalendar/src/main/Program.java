package main;

import gui.Window;
import util.Vector2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import gui.Text;

public class Program {
    private static final String PROGRAM_NAME = "Sticky Note Calendar";
    private static Window window;
    private static Executor executor = Executors.newSingleThreadExecutor();

    public static void Init() {
        window = new Window(PROGRAM_NAME, 1280, 720);
    }

    public static void Start() {
        Text text = new Text("Hello World", new Vector2(0, 0), 10);
        text.setPosition(new Vector2(100, 100));

        executor.execute(window::Draw);
    }
}
