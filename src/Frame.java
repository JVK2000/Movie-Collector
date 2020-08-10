/*import com.google.gson.Gson;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Frame {

    public static final int HGAP = 25;
    public static final int VGAP = 25;

    public static void main(String[] args) throws Exception {
        // Creates settings file if no one exists
        SettingManager settingManager = new SettingManager();
        settingManager.createSaveFile();
        Long columns = settingManager.getColumns();
        double movieIconWidth = settingManager.getMovieDimensionWidth();
        double movieIconHeight = settingManager.getMovieDimensionHeight();

        Window window = new Window();
        window.setTitle("Movie collector");
        window.setFullScrean();
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

                String imageFile = "C:\\Users\\Josef\\PycharmProjects\\Movie-Collector\\images\\" + movieFileName.split("\\.")[0] + ".JPG";
                ImageIcon icon = new ImageIcon(imageFile);

                // Rescale the image to fit the button
                Image img = icon.getImage() ;

                Image newimg = img.getScaledInstance((int) movieIconWidth, (int) movieIconHeight,  java.awt.Image.SCALE_SMOOTH ) ;
                icon = new ImageIcon(newimg);

                JButton button;

                File imfile = new File(imageFile);
                if (imfile.exists()) {
                    button = new JButton(icon);
                    button.setBackground(Color.DARK_GRAY);

                } else {
                    button = new JButton(movieFileName.split("\\(")[0]);
                    button.setForeground(Color.white);
                    int min = 0;
                    int max = 200;
                    int redIndex = ThreadLocalRandom.current().nextInt(min, max + 1);
                    int greenIndex = ThreadLocalRandom.current().nextInt(min, max + 1);
                    int blueIndex = ThreadLocalRandom.current().nextInt(min, max + 1);

                    System.out.println(redIndex + " " + greenIndex + " " + blueIndex);

                    Color myWhite = new Color(redIndex, greenIndex, blueIndex);
                    button.setBackground(myWhite);
                    button.setFont(new Font("Arial", Font.BOLD, 20));

                }

                // Makes the button bordesrs "invisable"
                button.setBorder(new LineBorder(Color.DARK_GRAY));
                button.setPreferredSize(new Dimension((int) movieIconWidth, (int) movieIconHeight));

                // Fatches the disc the movie is stored on
                String fileDisc = line.split("/")[2];

                // Removes the mnt/ part of the path.
                String filePath = line.substring(6);

                File file = new File(fileDisc + ":" + filePath);
                Desktop desktop = Desktop.getDesktop();

                button.addActionListener(e -> {
                    try {
                        desktop.open(file);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
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

