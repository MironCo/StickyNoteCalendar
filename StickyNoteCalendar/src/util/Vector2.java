/*
 * Program: StickyNoteCalendar
 * File: Vector2.java
 * Usage: Utility class that contains two values, X and Y.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package util;

public class Vector2 {
    public double x;
    public double y;

    public static double Distance(Vector2 a, Vector2 b) {
        double distance = Math.sqrt(Math.pow((b.x - a.x), 2) + Math.pow((b.y - a.y), 2));
        return distance;
    }

    public Vector2(float _x, float _y) {
        x = _x;
        y = _y;
    }

    public Vector2(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public Vector2(double _x, double _y) {
        x = _x;
        y = _y;
    }

    /**
     * Function to set x and y in a Vector2 
     * @param newX - new x position as a float
     * @param newY - new y position as a float 
     */
    public void set(float newX, float newY) {
        x = newX;
        y = newY;
    }

    /**
     * Function to set x and y in a Vector2 
     * @param newX - new x position as an int
     * @param newY - new y position as an int
     */
    public void set(int newX, int newY) {
        x = (float)newX;
        y = (float)newY;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
