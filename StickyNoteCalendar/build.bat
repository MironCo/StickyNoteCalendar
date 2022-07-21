javac -d output -sourcepath src/ src/gui/Window.java
javac -d output -sourcepath src/ src/gui/Text.java
javac -d output -sourcepath src/ src/gui/UIElement.java

javac -d output -sourcepath src/ src/gui/stickynote/StickyNote.java

javac -d output -sourcepath src/ src/util/Vector2.java

javac -d output -sourcepath src/ src/main/Program.java
javac -d output -sourcepath src/ src/main/calendar/Calendar.java
javac -d output -sourcepath src/ src/main/calendar/Date.java

javac -d output -sourcepath src/ src/main/Main.java

java -cp output/ main/Main
PAUSE