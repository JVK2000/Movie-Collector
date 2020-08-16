package actionListener;

import functionality.SettingManager;
import functionality.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieIconSizeSettingActionListener implements ActionListener {
    final Window window;
    final double numbOfColumn;

    public MovieIconSizeSettingActionListener(double i, Window window) {
        this.numbOfColumn = i;
        this.window = window;
    }


    public void actionPerformed(ActionEvent e) {
        SettingManager settingManager = new SettingManager();
        try {
            settingManager.saveMovieIconSize(numbOfColumn);
            restartApplication();
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }
    }

    public void restartApplication() throws Exception {
        window.restartApplication();
    }

}
