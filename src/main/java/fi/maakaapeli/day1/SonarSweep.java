package fi.maakaapeli.day1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SonarSweep {

    private static Logger log = LoggerFactory.getLogger(SonarSweep.class);
    private JsonObject dataObject;

    public SonarSweep() {
        Path resourceDirectory = Paths.get("src", "main", "resources", "day1", "data.json");
        File file = resourceDirectory.toFile();

        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            this.dataObject = new Gson().fromJson(reader, JsonObject.class);
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            log.error("Error while creating jsonObject", e.getMessage());
        }
    }

    public SonarSweep(JsonObject dataObject) {
        this.dataObject = dataObject;
    }

    public void setDataObject(JsonObject dataObject) {
        this.dataObject = dataObject;
    }

    public JsonObject getDataObject() {
        return dataObject;
    }

    public List<Integer> getDataObjectDataValuesAsIntegerList() {
        JsonArray dataArray = getDataObject().get("data").getAsJsonArray();
        return new Gson().fromJson(dataArray, new TypeToken<List<Integer>>() {
        }.getType());
    }

    public Integer[] getDataObjectDataValuesAsIntegerArray() {
        List<Integer> dataValues = getDataObjectDataValuesAsIntegerList();
        return dataValues.toArray(new Integer[dataValues.size()]);
    }

    public int getLargerCount() {
        int count = 0;
        Integer[] dataValuesArray = getDataObjectDataValuesAsIntegerArray();

        for (int i = 1; i < dataValuesArray.length; i++) {

            int current = dataValuesArray[i];
            int earlier = dataValuesArray[i - 1];

            if (current > earlier) {
                count++;
            }
        }

        return count;
    }

    public int getLargerCountThreeMeasurementWindow() {
        int count = 0;

        Integer[] dataValuesArray = getDataObjectDataValuesAsIntegerArray();

        for (int i = 3; i < dataValuesArray.length; i++) {
            int current = dataValuesArray[i] + dataValuesArray[i - 1] + dataValuesArray[i - 2];
            int earlier = dataValuesArray[i - 1] + dataValuesArray[i - 2] + dataValuesArray[i - 3];
            if (current > earlier) {
                count++;
            }
        }

        return count;
    }
}
