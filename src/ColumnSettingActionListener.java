import org.json.simple.parser.ParseException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ColumnSettingActionListener implements ActionListener {
   final int numbOfColumn;

   public ColumnSettingActionListener(int i) {
      this.numbOfColumn = i;
   }

   public void actionPerformed(ActionEvent e) {
      SettingManager settingManager = new SettingManager();
      try {
         settingManager.saveColumns(numbOfColumn);
         restartApplication(); // Not Working
      } catch (IOException | URISyntaxException | ParseException ioException) {
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
