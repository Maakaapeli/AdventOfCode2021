package fi.maakaapeli.day1Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.junit.Test;

import fi.maakaapeli.day1.SonarSweep;

public class SonarSweepTest {

    @Test
    public void should_CreateDay1Object() {
        assertNotNull(new SonarSweep());
    }

    @Test
    public void should_filedNotBeNull() throws FileNotFoundException {
        Path resourceDirectory = Paths.get("src", "test", "resources", "day1", "data.json");
        File file = resourceDirectory.toFile();
        assertNotNull(file);
    }

    @Test
    public void should_filedHaveData() throws FileNotFoundException {

        JsonObject jsonObject = getDataObject();

        assertTrue(jsonObject.has("data"));
    }

    @Test
    public void should_ReturnValidJsonObject() {
        JsonObject dataObject = getDataObject();
        SonarSweep sonarSweep = new SonarSweep(dataObject);
        assertTrue(sonarSweep.getDataObject().has("data"));
    }

    @Test
    public void should_DataObjectLengthBe10() {
        JsonObject dataObject = getDataObject();
        SonarSweep sonarSweep = new SonarSweep(dataObject);

        assertTrue(sonarSweep.getDataObjectDataValuesAsIntegerList().size() == 10);
    }

    @Test
    public void should_sevenMeasurementsLargerThanPrevious() {

        SonarSweep sonarSweep = new SonarSweep(getDataObject());
        int largerCount = sonarSweep.getLargerCount();
        assertTrue(largerCount == 7);
    }

    @Test
    public void should_fiveMeasurementsLargerWithThreeMeasurementWindow() {
        SonarSweep sonarSweep = new SonarSweep(getDataObject());
        int largerCountThreeMeasurementWindow = sonarSweep.getLargerCountThreeMeasurementWindow();

        assertTrue(largerCountThreeMeasurementWindow == 5);
    }

    private JsonObject getDataObject() {
        Path resourceDirectory = Paths.get("src", "test", "resources", "day1", "data.json");
        File file = resourceDirectory.toFile();

        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            return new Gson().fromJson(reader, JsonObject.class);
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
        }
        return null;
    }
}
