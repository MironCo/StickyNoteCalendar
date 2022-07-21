package main.calendar;

import gui.UIElement;
import gui.Window;

public class Calendar extends UIElement {
   public static Date today;
   public static final Calendar instance = new Calendar();

   private Calendar() {
    //singleton class - do nothing
   }

   public static final Calendar getInstance() {
    return instance;
   }

   @Override
   public void addToWindow(Window window) {
    
   }

   public static void Init() {

   }

   public static void Draw() {

   }
}
