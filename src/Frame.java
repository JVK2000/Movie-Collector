/*import com.google.gson.Gson;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Frame {

    public static final int HGAP = 25;
    public static final int VGAP = 25;

    public static void main(String[] args) throws Exception {
        createWindow2();

    }

    public static void createWindow2() throws Exception {
        // Creates settings file if no one exists
        SettingManager settingManager = new SettingManager();
        settingManager.createSaveFile();
        Long columns = settingManager.getColumns();
        Long movieIconWidth = settingManager.getMovieDimensionWidth();
        Long movieIconHeight = settingManager.getMovieDimensionHeight();

        Window window = new Window();
        window.setTitle("Movie collector");
        window.setSize(1500, 3000);
        window.addLoadScreen("gifs/Dual Ring-1.5s-800px (1).gif", 200, 200);
        window.createMenu(window);
        window.setIconImage("images/appIcon.png");

        JPanel panel = new JPanel(new GridLayout(0, Math.toIntExact(columns), HGAP, VGAP));
        panel.setBackground(Color.DARK_GRAY);

        String fileName = "movieList.txt";

        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!(line.equals(""))) {

                // the movie files name. Splits the string into parts, where / is the devider. Uses the last index to get the movie name.
                int movieFilePositionInPath = line.split("/").length - 1;
                String movieFileName = line.split("/")[movieFilePositionInPath];

                ImageIcon icon = new ImageIcon("C:\\Users\\Josef\\PycharmProjects\\Movie-Collector\\images\\" + movieFileName.split("\\.")[0] + ".JPG");

                // Rescale the image to fit the button
                Image img = icon.getImage() ;
                Image newimg = img.getScaledInstance(Math.toIntExact(movieIconWidth), Math.toIntExact(movieIconHeight),  java.awt.Image.SCALE_SMOOTH ) ;
                icon = new ImageIcon( newimg );

                JButton button = new JButton(movieFileName, icon);

                // Makes the button bordesrs "invisable"
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.DARK_GRAY);
                button.setBorder(new LineBorder(Color.DARK_GRAY));
                button.setPreferredSize(new Dimension(Math.toIntExact(movieIconWidth), Math.toIntExact(movieIconHeight)));
                button.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        // Fatches the disc the movie is stored on
                        String fileDisc = line.split("/")[2];

                        // Removes the mnt/ part of the path.
                        String filePath = line.substring(6);

                        File file = new File(fileDisc + ":" + filePath);

                        //File file = new File("E:/Movies/Movies/" + movieFileName.split("\\.")[0] + "/" + movieFileName);
                        Desktop desktop = Desktop.getDesktop();
                        if(!(file.exists())) {
                            file = new File("F:/Film/" + movieFileName.split("\\.")[0] + "/" + movieFileName);
                        }
                        if(file.exists()) {
                            try {
                                desktop.open(file);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                });
                panel.add(button);
            }

        }
        scanner.close();

        window.removeLoadScreen();
        window.makeScrolable(panel);

    }
}

