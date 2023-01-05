/*
 * Program: StickyNoteCalendar
 * File: LoadManager.java
 * Usage: Utility class for loading save data.
 * Author: Miron Sulicz
 * Copyright: 2022-2023 Miron Sulicz, All Rights Reserved
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import gui.button.presets.Preset;
import gui.button.presets.PresetManager;
import gui.button.presets.PresetStickyNoteBean;
import gui.colors.ColorThemeManager;
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
            File saveFile = GetFile(SaveData.SAVE_FILE_NAME);
            File presetFile = GetFile(SaveData.PRESET_FILE_NAME);
            if (saveFile != null) {
                ParseSaveData(saveFile);
            }
            if (presetFile != null) {
                ParsePresetData(presetFile);
            }
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }

    private static File GetFile(String fileName) throws IOException, SecurityException {
        File file = new File(SaveData.USER_DIRECTORY + File.separator + SaveData.SAVE_FOLDER + File.separator + fileName);
        System.out.println(file.getAbsolutePath());

        if (!file.exists()) {
            return null;
        }

        return file;
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
                    StickyNoteManager.getInstance().addStickyNote(newNote);
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

    private static void ParsePresetData(File inFile) throws FileNotFoundException, IOException {
        if (inFile.exists()) {
            FileReader fileReader = new FileReader(inFile);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line;

            Preset newPreset = null;

            while ((line = buffReader.readLine()) != null) {
                if (line.contains(SaveData.PRESET_TYPE)) {
                    String presetName = line.substring(line.indexOf(":") + 1);
                    newPreset = new Preset(presetName);
                    
                    if (newPreset != null) {
                        PresetManager.getInstance().addPreset(newPreset);
                    }
                } else if (line.contains(SaveData.PRESET_STICKY_NOTE_TYPE)) {
                    if (newPreset != null) {
                        PresetStickyNoteBean newBean = new PresetStickyNoteBean();
                        newBean.setPresetStickyNoteText(line.substring(line.indexOf(":") + 1, line.indexOf(",")));
                        newBean.setPresetStickyNoteColor(StickyNoteManager.getNoteColorByName(line.substring(line.indexOf(",") + 1)));
                        newPreset.addPresetStickyNote(newBean);
                    }
                }
            }

            fileReader.close();
            buffReader.close();
        }
    }
}