package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 
import java.util.List;

import gui.stickynote.StickyNote;
import main.calendar.Calendar;
import main.calendar.Month;
import main.calendar.day.Day;

public class SaveManager {
    private static final String USER_DIRECTORY = System.getProperty("user.home");
    private static final String SAVE_FOLDER = "StickyNoteCalendar";
    private static final String FILE_NAME = "SNC.save";

    private static final String MONTH_TYPE = "MONTH:";
    private static final String DAY_TYPE = "DAY:";
    private static final String STICKY_NOTE_TYPE = "STICKY_NOTE:";

    private static FileWriter writer;

    public static void SaveData() {
        try {
            File saveFile = GetFile();
            WriteData(saveFile);
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }

    private static File GetFile() throws IOException, SecurityException {
        File saveFile = new File(USER_DIRECTORY + File.separator + SAVE_FOLDER + File.separator + FILE_NAME);
        System.out.println(saveFile.getAbsolutePath());

        if (!saveFile.exists()) {
            File directory = new File(USER_DIRECTORY + File.separator + SAVE_FOLDER);
            directory.mkdir();
            saveFile.createNewFile();
        }

        return saveFile;
    }

    public static void WriteData(File file) throws IOException {
        writer = new FileWriter(file);
        
        for (Month month : Calendar.getInstance().getMonths()) {
            WriteMonth(writer, month);
            System.out.println("Wrote " + month.getName());
        }

        writer.close();
    }

    public static void WriteMonth(FileWriter writer, Month month) throws IOException {
        String monthName = month.getName();
        int numberOfDays = month.getDays().size();

        writer.write(MONTH_TYPE);
        writer.write(monthName);
        writer.write("\n");

        for (int i = 0; i < numberOfDays; i++) {
            WriteDay(writer, month.getDays().get(i));
        }
    }

    public static void WriteDay(FileWriter writer, Day day) throws IOException {
        List<StickyNote> notes = day.stickyNotes;

        writer.write(DAY_TYPE);
        writer.write(Integer.toString(day.day));
        writer.write("\n");

        for (StickyNote stickyNote : notes) {
            WriteStickyNote(writer, stickyNote);
        }
    }

    public static void WriteStickyNote(FileWriter writer, StickyNote stickyNote) throws IOException {
        writer.write(STICKY_NOTE_TYPE);
        writer.write(stickyNote.getStickyNoteText().getText());
        writer.write("\n");
    }
}
