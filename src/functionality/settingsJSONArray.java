package functionality;

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

        Gson gson = new Gson();
        output = gson.toJson(columns);
        System.out.println(output);
    }

    @Override
    public String toString() {
        return output;
    }
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
