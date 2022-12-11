package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import gui.stickynote.StickyNote;
import main.calendar.Calendar;
import main.calendar.day.Day;
import main.calendar.month.Month;

public class SaveManager {
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
        File saveFile = new File(
                SaveData.USER_DIRECTORY + File.separator + SaveData.SAVE_FOLDER + File.separator + SaveData.FILE_NAME);
        System.out.println(saveFile.getAbsolutePath());

        if (!saveFile.exists()) {
            File directory = new File(SaveData.USER_DIRECTORY + File.separator + SaveData.SAVE_FOLDER);
            directory.mkdir();
            saveFile.createNewFile();
        }

        return saveFile;
    }

    public static void WriteData(File file) throws IOException {
        writer = new FileWriter(file);

        for (Month month : Calendar.getInstance().getMonths()) {
            WriteMonth(month);
        }

        writer.close();
    }

    public static void WriteMonth(Month month) throws IOException {
        String monthName = month.getName();
        int numberOfDays = month.getDays().size();

        writer.write(SaveData.MONTH_TYPE);
        writer.write(monthName);
        writer.write("\n");

        for (int i = 0; i < numberOfDays; i++) {
            if (!month.getDays().get(i).getStickyNotes().isEmpty()) {
                WriteDay(month.getDays().get(i));
            }
        }
    }

    public static void WriteDay(Day day) throws IOException {
        List<StickyNote> notes = day.stickyNotes;

        writer.write(SaveData.DAY_TYPE);
        writer.write(Integer.toString(day.day));
        writer.write("\n");

        for (StickyNote stickyNote : notes) {
            WriteStickyNote(stickyNote);
        }
    }

    public static void WriteStickyNote(StickyNote stickyNote) throws IOException {
        writer.write(SaveData.STICKY_NOTE_TYPE);
        String strippedString = stickyNote.getStickyNoteText().getText();
        if (strippedString.contains("\r")) strippedString = strippedString.replace("\n", "").replace("\r", "\\n");
        else strippedString = strippedString.replace("\n", "\\n");
        writer.write(strippedString);
        writer.write(",");
        writer.write(stickyNote.getColor().getName());
        writer.write("\n");
    }
}