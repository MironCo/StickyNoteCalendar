package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import gui.colors.ColorThemeManager;
import gui.colors.LightColorTheme;
import gui.stickynote.NoteColor;
import gui.stickynote.StickyNote;
import gui.stickynote.StickyNoteManager;
import main.calendar.Calendar;
import main.calendar.CalendarData;
import main.calendar.day.Day;
import main.calendar.month.Month;
import main.calendar.month.MonthFactory;

public class LoadManager {    
    private static List<Month> loadedMonths = new ArrayList<>();

    public static void LoadSave() {
        try {
            File saveFile = GetFile();
            if (saveFile != null) {
                ParseSaveData(saveFile);
            }
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }

    private static File GetFile() throws IOException, SecurityException {
        File saveFile = new File(SaveData.USER_DIRECTORY + File.separator + SaveData.SAVE_FOLDER + File.separator + SaveData.FILE_NAME);
        System.out.println(saveFile.getAbsolutePath());

        if (!saveFile.exists()) {
            return null;
        }

        return saveFile;
    }

    private static void ParseSaveData(File inFile) throws FileNotFoundException, IOException {
        if (inFile.exists()) {
            FileReader fileReader = new FileReader(inFile);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line;

            Month newMonth = null;
            Day newDay = null;

            while ((line = buffReader.readLine()) != null) {
                if (line.contains(SaveData.MONTH_TYPE)) {
                    int year = Integer.parseInt(line.substring(line.indexOf(" ") + 1).replace("\n", ""));
                    int month = CalendarData.findMonthIndex(line.substring(line.indexOf(":") + 1, line.indexOf(" ")));
                    YearMonth mYearMonth = YearMonth.of(year, month);
                    newMonth = MonthFactory.buildMonth(mYearMonth);
                    loadedMonths.add(newMonth);
                } else if (line.contains(SaveData.DAY_TYPE)) {
                    int dayNumber = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                    newDay = newMonth.getDays().get(dayNumber-1);
                } else if (line.contains(SaveData.STICKY_NOTE_TYPE)) {
                    String stickyNoteContents = line.substring(line.indexOf(":") + 1, line.indexOf(",")).replace("\\n", "\n");
                    NoteColor noteColor = StickyNoteManager.getNoteColorByName(line.substring(line.indexOf(",") + 1));
                    StickyNote newNote = new StickyNote(stickyNoteContents);
                    newNote.setColor(noteColor);
                    newDay.AddStickyNote(newNote);
                } else if (line.contains(SaveData.COLOR_THEME_TYPE)) {
                    String colorThemeName = line.substring(line.indexOf(":") + 1);
                    ColorThemeManager.setCurrentColorTheme(ColorThemeManager.getColorThemeFromName(colorThemeName));
                }
            }

            for (Month month : loadedMonths) {
                Calendar.getInstance().addNewMonth(month);
            }
            fileReader.close();
            buffReader.close();
        }
    }
}
