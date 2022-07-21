package util;

public class Vector2 {
    public float x;
    public float y;

    public Vector2(float _x, float _y) {
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
}
