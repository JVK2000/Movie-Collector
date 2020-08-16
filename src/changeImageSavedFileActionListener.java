import org.python.google.common.io.Files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class changeImageSavedFileActionListener extends Component implements ActionListener {
    private String movieFileName;

    public changeImageSavedFileActionListener(String movieFileName) {
        this.movieFileName = movieFileName;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("/"));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            //SettingManager settingManager = new SettingManager();
            //settingManager.saveMoviePath(chooser.getSelectedFile().toString());
            String fileToCopyPath = chooser.getSelectedFile().toString();
            try {
                copyFile(fileToCopyPath, "C:\\Users\\Josef\\IdeaProjects\\Movie-Collector\\movieCovers\\" + movieFileName.split("\\.")[0] + ".jpg");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }
        else {
            System.out.println("No Selection ");
        }


    }

    /**
     * Code from: https://www.java67.com/2016/09/how-to-copy-file-from-one-location-to-another-in-java.html
     * @param from the original path for the file
     * @param to the new destination for the file
     * @throws IOException
     */
    private static void copyFile(String from, String to) throws IOException {
        Path src = Paths.get(from);
        Path dest = Paths.get(to);
        Files.copy(src.toFile(), dest.toFile());
    }

    private static void ranameFile(String from, String to) throws IOException {
        File f1 = new File("oldname.txt");
        File f2 = new File("newname.txt");
        f1.renameTo(f2);
    }




    }


