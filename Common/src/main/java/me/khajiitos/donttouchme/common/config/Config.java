package me.khajiitos.donttouchme.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import me.khajiitos.donttouchme.common.DontTouchMe;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static final File file = new File("config/donttouchme.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final Values DEFAULT = new Values();
    public static final Values cfg = new Values();

    public static class Values {
        public boolean playersOnly = false;
    }

    public static void load() {
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file)) {
                JsonObject object = GSON.fromJson(fileReader, JsonObject.class);

                if (object.has("playersOnly")) {
                    cfg.playersOnly = object.get("playersOnly").getAsBoolean();
                }
            } catch (JsonSyntaxException e) {
                DontTouchMe.LOGGER.error("Config is not valid JSON");
            } catch (IOException e) {
                DontTouchMe.LOGGER.error("Failed to load config", e);
            }
        } else if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
            save();
        } else {
            DontTouchMe.LOGGER.error("Failed to load or create config");
        }
    }

    public static void save() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            JsonObject object = new JsonObject();
            object.addProperty("playersOnly", cfg.playersOnly);
            fileWriter.write(GSON.toJson(object));
        } catch (IOException e) {
            DontTouchMe.LOGGER.error("Failed to save config", e);
        }
    }
}