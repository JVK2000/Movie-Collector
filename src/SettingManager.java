import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SettingManager {
    private static final int MOVIE_ICON_ORGINAL_WIDTH = 250;
    private static final int MOVIE_ICON_ORGINAL_HEIGHT = 400;

    File FilePath = new File("settings.json");
    Gson gson = new Gson();

    public void createSaveFile() throws IOException {
        if (FilePath.createNewFile()) {
            double movieIconWidth = 250;
            double movieIconHeight = 400;

            JSONObject sampleObject = new JSONObject();
            sampleObject.put("columns", 9);

            JSONObject dimensionsObject = new JSONObject();
            dimensionsObject.put("movieIconWidth", movieIconWidth);
            dimensionsObject.put("movieIconHeight", movieIconHeight);
            sampleObject.put("dimensions", dimensionsObject);

            Files.write(Paths.get("settings.json"), sampleObject.toJSONString().getBytes());
       }
    }

    public void saveMovieIconSize(double scale) throws IOException, ParseException {
        int movieIconWidth = (int) (MOVIE_ICON_ORGINAL_WIDTH * scale);
        int movieIconHeight = (int) (MOVIE_ICON_ORGINAL_HEIGHT * scale);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("settings.json"));

        JSONObject dimensions = (JSONObject) jsonObject.get("dimensions");
        dimensions.put("movieIconWidth", (int) movieIconWidth);
        dimensions.put("movieIconHeight", (int) movieIconHeight);

        Files.write(Paths.get("settings.json"), jsonObject.toJSONString().getBytes());
    }

    public void saveColumns(int numbOfColumn) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("settings.json"));
        jsonObject.put("columns", numbOfColumn);
        Files.write(Paths.get("settings.json"), jsonObject.toJSONString().getBytes());
    }

    public Long getColumns() throws Exception {
        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("settings.json");
        return (long) jsonObject.get("columns");
    }

    public Long getMovieDimensionWidth() throws Exception {
        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("settings.json");
        JSONObject dimensions = (JSONObject) jsonObject.get("dimensions");
        return (Long) dimensions.get("movieIconWidth");
    }

    public Long getMovieDimensionHeight() throws Exception {
        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("settings.json");
        JSONObject dimensions = (JSONObject) jsonObject.get("dimensions");
        return (Long) dimensions.get("movieIconHeight");
    }

    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }


}
