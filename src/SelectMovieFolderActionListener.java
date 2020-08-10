import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SelectMovieFolderActionListener extends Component implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("/"));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            SettingManager settingManager = new SettingManager();
            try {
                settingManager.saveMoviePath(chooser.getSelectedFile().toString());
            } catch (IOException | ParseException ioException) {
                ioException.printStackTrace();
            }
        }
        else {
            System.out.println("No Selection ");
        }


    }

}
