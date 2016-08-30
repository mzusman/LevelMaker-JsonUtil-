package mzusman;

import com.google.gson.stream.JsonWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by mzeus-arch on 8/30/16.
 */
public class JsonCreator implements PageCreator {
    JsonWriter jsonWriter;
    private int level;

    public JsonCreator(Writer writer, int begin_of_levels) throws IOException {
        jsonWriter = new JsonWriter(writer);
        jsonWriter.beginArray();
        level = begin_of_levels;
    }

    public JsonCreator(Writer writer) throws IOException {
        jsonWriter = new JsonWriter(writer);
        jsonWriter.beginArray();
        level = 1;
    }

    public void addLevel() throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("level").value(level);
    }

    public void setBeginOfLevels(int level) {
        this.level = level;
    }

    public void addCriteria(String name, String v) throws IOException {
        jsonWriter.name(name).value(v);
    }

    public void endLevel() throws IOException {
        jsonWriter.endObject();
        level++;
    }

    public void endLevelsArray() throws IOException {
        jsonWriter.endArray();
        jsonWriter.close();
    }

    public int getCurrentLevel() {
        return level;
    }

}
