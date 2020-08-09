import org.json.simple.parser.ParseException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MovieIconSizeSettingActionListener implements ActionListener {
    final double numbOfColumn;

    public MovieIconSizeSettingActionListener(double i) {
        this.numbOfColumn = i;
    }

    public void actionPerformed(ActionEvent e) {
        SettingManager settingManager = new SettingManager();
        try {
            settingManager.saveMovieIconSize(numbOfColumn);
        } catch (IOException | ParseException ioException) {
            ioException.printStackTrace();
        }
    }

}
