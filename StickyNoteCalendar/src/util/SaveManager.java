/*
 * Program: StickyNoteCalendar
 * File: SaveManager.java
 * Usage: Utility class that saves the program state into custom data files.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import gui.button.presets.Preset;
import gui.button.presets.PresetManager;
import gui.button.presets.PresetStickyNoteBean;
import gui.colors.ColorThemeManager;
import gui.stickynote.StickyNote;
import main.calendar.Calendar;
import main.calendar.day.Day;
import main.calendar.month.Month;

public class SaveManager {
    private static FileWriter writer;

    public static void SaveData() {
        try {
            File saveFile = GetFile(SaveData.SAVE_FILE_NAME);
            File presetFile = GetFile(SaveData.PRESET_FILE_NAME);
            WriteData(saveFile);
            WritePresetData(presetFile);
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }

    private static File GetFile(String fileName) throws IOException, SecurityException {
        File saveFile = new File(
                SaveData.USER_DIRECTORY + File.separator + SaveData.SAVE_FOLDER + File.separator + fileName);
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
        WriteColorTheme();

        for (Month month : Calendar.getInstance().getMonths()) {
            WriteMonth(month);
        }

        writer.close();
    }

    public static void WriteColorTheme() throws IOException {
        writer.write(SaveData.COLOR_THEME_TYPE);
        writer.write(ColorThemeManager.getCurrentColorTheme().themeName);
        writer.write("\n");
    }

    public static void WriteMonth(Month month) throws IOException {
        String monthName = month.getName();
        int numberOfDays = month.getDays().size();
        int actuallyWritten = 0;

        //check beforehand if all the days are empty
        for (int i = 0; i < numberOfDays; i++) {
            if (!month.getDays().get(i).getStickyNotes().isEmpty()) {
                actuallyWritten ++;
            }
        }

        //if there is at least one day with a sticky note, write the month.
        if (actuallyWritten > 0) {
            writer.write(SaveData.MONTH_TYPE);
            writer.write(monthName);
            writer.write("\n");

            for (int i = 0; i < numberOfDays; i++) {
                if (!month.getDays().get(i).getStickyNotes().isEmpty()) {
                    WriteDay(month.getDays().get(i));
                }
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
        String strippedString = stickyNote.getStickyNoteText();
        if (strippedString.contains("\r")) strippedString = strippedString.replace("\n", "").replace("\r", "\\n");
        else strippedString = strippedString.replace("\n", "\\n");
        writer.write(strippedString);
        writer.write(",");
        writer.write(stickyNote.getColor().getName());
        writer.write("\n");
    }

    
    public static void WritePresetData(File file) throws IOException {
        writer = new FileWriter(file);
        
        for (Preset preset : PresetManager.getInstance().getPresets()) {
            WritePreset(preset);
        }
        
        writer.close();
    }

    public static void WritePreset(Preset preset) throws IOException {
        writer.write(SaveData.PRESET_TYPE);
        writer.write(preset.getName());
        writer.write("\n");

        for (PresetStickyNoteBean bean : preset.presetStickyNoteData) {
            WritePresetStickyNote(bean);
        }
    }

    public static void WritePresetStickyNote(PresetStickyNoteBean bean) throws IOException {
        writer.write(SaveData.PRESET_STICKY_NOTE_TYPE);
        writer.write(bean.getPresetStickyNoteText());
        writer.write(",");
        writer.write(bean.getPresetStickyNoteColor().getName());
        writer.write("\n");
    }
}