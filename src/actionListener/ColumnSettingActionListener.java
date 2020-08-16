package actionListener;

import functionality.SettingManager;
import functionality.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColumnSettingActionListener implements ActionListener {
   final Window window;
   final int numbOfColumn;

   public ColumnSettingActionListener(int i, Window window) {
      this.numbOfColumn = i;
      this.window = window;
   }

   public void actionPerformed(ActionEvent e) {
      SettingManager settingManager = new SettingManager();
      try {
         settingManager.saveColumns(numbOfColumn);
         restartApplication(); // Not Working
      } catch (Exception ioException) {
         ioException.printStackTrace();
      }
   }

   public void restartApplication() throws Exception {
      window.restartApplication();
   }
}
