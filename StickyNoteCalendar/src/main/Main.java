package main;

import gui.Window;

public class Main {
    static Window window;

    public static void main(String[] args) {
        window = new Window("Sticky Note Calendar", 1280, 720);
        window.Open();
    }
}
