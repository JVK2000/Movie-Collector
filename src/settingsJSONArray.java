import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class settingsJSONArray {
    static String output;
    public static void main(String[] args) {

        Map<String, Integer> columns = new HashMap<>();
        Columns numOfColumns = new Columns();
        numOfColumns.setColumns(10);
        columns.put("columns", numOfColumns.getColumns());
        //colours.put(2, "yellow");
        //colours.put(3, "green");

        Gson gson = new Gson();

        output = gson.toJson(columns);

        System.out.println(output);
    }

    @Override
    public String toString() {
        return output;

    }

    /*Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print JSON
        Columns student = new Columns("Adithya");
        String jsonStr = gson.toJson(student, Columns.class);
        System.out.println("JSON String: \n" + jsonStr);
        JsonElement jsonElement = gson.toJsonTree(student);
        jsonElement.getAsJsonObject().addProperty("id", "115");
        jsonStr = gson.toJson(jsonElement);
        System.out.println("JSON String after inserting additional property: \n" + jsonStr);

         */

}

class Columns {
    private int numOfColumns;

    public Columns(int numOfColumns) {
        this.numOfColumns= numOfColumns;
    }

    public Columns() {}

    public int getColumns() {
        return numOfColumns;
    }
    public void setColumns(int numOfColumns) {
        this.numOfColumns = numOfColumns;
    }
}
