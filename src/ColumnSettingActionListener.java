import com.google.gson.Gson;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

         restartApplication();

      } catch (IOException | URISyntaxException ioException) {
         ioException.printStackTrace();
      }
   }


   public void restartApplication() throws URISyntaxException, IOException {
      final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
      final File currentJar = new File(Frame.class.getProtectionDomain().getCodeSource().getLocation().toURI());

      /* is it a jar file? */
      if(!currentJar.getName().endsWith(".jar"))
         return;

      /* Build command: java -jar application.jar */
      final var command = new ArrayList<String>();
      command.add(javaBin);
      command.add("-jar");
      command.add(currentJar.getPath());

      final ProcessBuilder builder = new ProcessBuilder(command);
      builder.start();
      System.exit(0);
   }
}
