package fi.maakaapeli.day2Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import org.junit.Test;

import fi.maakaapeli.day2.Dive;

public class DiveTest {

    @Test
    public void shouldNotBeNull_CreateDay2Object() {
        assertNotNull(new Dive());
    }

    @Test
    public void shouldBeTrue_dataFileExist() {
        Path resourceDirectory = Paths.get("src", "test", "resources", "day2", "data.json");
        Boolean fileExists = resourceDirectory.toFile().exists();
        assertTrue(fileExists);
    }

    @Test
    public void shouldBeTrue_dataFileHasCommandData() {
        JsonObject commandsObject = getCommandsObject();
        assertTrue(commandsObject.has("commands"));
    }

    @Test
    public void shoulBeTrue_returnValidJsonObject() {
        Dive dive = new Dive(getCommandsObject());
        assertTrue(dive.getCommandsObject().has("commands"));
    }

    @Test
    public void shouldNotBeNull_commandsAndValuesMap() {

        Dive dive = new Dive(getCommandsObject());
        Map<Integer, Map<String, Integer>> commandsAndValuesMap = dive.getCommandsAndValuesMap();

        assertNotNull(commandsAndValuesMap);
    }

    @Test
    public void shouldBeTrue_commandsAndValuesMapHasSixEntries() {
        Dive dive = new Dive(getCommandsObject());
        assertTrue(dive.getCommandsAndValuesMap().size() == 6);
    }

    @Test
    public void shouldBeTrue_commandTypesAreStringAndInteger() {
        Dive dive = new Dive(getCommandsObject());
        Map<Integer, Map<String, Integer>> commandsAndValuesMap = dive.getCommandsAndValuesMap();

        for (Map.Entry<Integer, Map<String, Integer>> entry : commandsAndValuesMap.entrySet()) {
            assertTrue(entry.getKey() instanceof Integer);
            assertTrue(entry.getValue() instanceof HashMap);

            for (Map.Entry<String, Integer> valueEntry : entry.getValue().entrySet()) {
                assertTrue(valueEntry.getKey() instanceof String);
                assertTrue(valueEntry.getValue() instanceof Integer);
            }
        }
    }

    @Test
    public void shouldBeTrue_finalDepthIsCorrect() {
        final int wantedDepth = 150;
        Dive dive = new Dive(getCommandsObject());

        assertTrue(dive.getFinalDepth() == wantedDepth);

    }

    @Test
    public void shouldBeTrue_finalDepthWithAimIsCorrect() {
        final int wantedDepth = 900;
        Dive dive = new Dive(getCommandsObject());

        assertTrue(dive.getFinalDepthWithAim() == wantedDepth);

    }

    private JsonObject getCommandsObject() {
        Path resourceDirectory = Paths.get("src", "test", "resources", "day2", "data.json");
        File file = resourceDirectory.toFile();

        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            return new Gson().fromJson(reader, JsonObject.class);
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
        }
        return null;
    }
}
