package fi.maakaapeli.day2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dive {

    private JsonObject commandsObject;
    private static Logger log = LoggerFactory.getLogger(Dive.class);

    public Dive() {
        Path resourceDirectory = Paths.get("src", "main", "resources", "day2", "data.json");
        File file = resourceDirectory.toFile();

        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            this.commandsObject = new Gson().fromJson(reader, JsonObject.class);
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            log.error("Error while creating jsonObject", e.getMessage());
        }
    }

    public Dive(JsonObject commandJsonObject) {
        this.commandsObject = commandJsonObject;
    }

    public JsonObject getCommandsObject() {
        return this.commandsObject;
    }

    public Map<Integer, Map<String, Integer>> getCommandsAndValuesMap() {

        Map<Integer, Map<String, Integer>> commandsAndValuesMap = new HashMap<>();

        List<String> commandsList = getCommandsAsList();
        String[] commandsArray = commandsList.toArray(new String[commandsList.size()]);

        for (int i = 0; i < commandsArray.length; i++) {
            String[] commandSplit = commandsArray[i].split(" ");
            String heading = commandSplit[0];
            int value = Integer.parseInt(commandSplit[1]);

            Map<String, Integer> commandMap = new HashMap<>();
            commandMap.put(heading, value);
            commandsAndValuesMap.put(i, commandMap);

        }
        return commandsAndValuesMap;
    }

    private List<String> getCommandsAsList() {
        JsonArray dataArray = getCommandsObject().get("commands").getAsJsonArray();
        return new Gson().fromJson(dataArray, new TypeToken<List<String>>() {
        }.getType());
    }

    public int getFinalDepth() {
        Map<Integer, Map<String, Integer>> commandsMap = getCommandsAndValuesMap();
        int horizontalPos = 0;
        int depth = 0;

        for (Map.Entry<Integer, Map<String, Integer>> entry : commandsMap.entrySet()) {
            for (Map.Entry<String, Integer> valueEntry : entry.getValue().entrySet()) {
                if (valueEntry.getKey().equalsIgnoreCase("forward")) {
                    horizontalPos += valueEntry.getValue();
                } else if (valueEntry.getKey().equalsIgnoreCase("down")) {
                    depth += valueEntry.getValue();
                } else if (valueEntry.getKey().equalsIgnoreCase("up")) {
                    depth -= valueEntry.getValue();
                }
            }
        }

        return horizontalPos * depth;
    }

    public int getFinalDepthWithAim() {

        Map<Integer, Map<String, Integer>> commandsMap = getCommandsAndValuesMap();
        int horizontalPos = 0;
        int depth = 0;
        int aim = 0;

        for (Map.Entry<Integer, Map<String, Integer>> entry : commandsMap.entrySet()) {
            for (Map.Entry<String, Integer> valueEntry : entry.getValue().entrySet()) {
                if (valueEntry.getKey().equalsIgnoreCase("forward")) {
                    horizontalPos += valueEntry.getValue();
                    if (aim != 0) {
                        depth += (aim * valueEntry.getValue());
                    }
                } else if (valueEntry.getKey().equalsIgnoreCase("down")) {
                    aim += valueEntry.getValue();
                } else if (valueEntry.getKey().equalsIgnoreCase("up")) {
                    aim -= valueEntry.getValue();
                }
            }
        }

        return horizontalPos * depth;
    }

}
