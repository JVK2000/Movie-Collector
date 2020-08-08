import com.google.gson.Gson;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ColumnSettingActionListener implements ActionListener {
   int numOfColm;

   public ColumnSettingActionListener(int i) {
      this.numOfColm = i;
      System.out.println(numOfColm);
   }

   public void actionPerformed(ActionEvent e) {
      try {
         Map<String, Integer> columns = new HashMap<>();
         Columns numOfColumns = new Columns();
         numOfColumns.setColumns(numOfColm);
         columns.put("columns", numOfColumns.getColumns());

         Gson gson = new Gson();
         String output = gson.toJson(columns);

         Writer writer = Files.newBufferedWriter(Paths.get("settings.json"));
         gson.toJson(output, writer);
         writer.close();

      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }
}
