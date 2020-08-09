/*import com.google.gson.Gson;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 */

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Frame {
    private static final int MOVIE_ICONE_WIDTH = 250;
    private static final int MOVIE_ICONE_HEIGHT = 400;

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException, InterruptedException {
        try {

            File myObj = new File("settings.json");
            if (myObj.createNewFile()) { // Creates new file
                Map<String, Integer> columns = new HashMap<>();
                Columns numOfColumns = new Columns();
                numOfColumns.setColumns(9);
                columns.put("columns", numOfColumns.getColumns());

                Gson gson = new Gson();
                String output = gson.toJson(columns);

                Writer writer = Files.newBufferedWriter(Paths.get("settings.json"));
                gson.toJson(output, writer);
                writer.close();

            } else { // File already exists.
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Window window = new Window();
        window.setTitle("Movie collecter");
        window.setSize(1500, 3000);
        window.addLoadScreen("gifs/Dual Ring-1.5s-800px (1).gif", 100, 100);
        window.createMenu();
        window.setIconImage("images/appIcon.png");

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("settings.json"));
        JSONObject json = (JSONObject) parser.parse((String) obj);
        Long COLUMS = (Long) json.get("columns");

        JPanel panel = new JPanel(new GridLayout(0, Math.toIntExact(COLUMS), 25, 25));
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
                Image newimg = img.getScaledInstance(MOVIE_ICONE_WIDTH, MOVIE_ICONE_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;
                icon = new ImageIcon( newimg );

                JButton button = new JButton(movieFileName, icon);

                // Makes the button bordesrs "invisable"
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.DARK_GRAY);
                button.setBorder(new LineBorder(Color.DARK_GRAY));
                button.setPreferredSize(new Dimension(MOVIE_ICONE_WIDTH, MOVIE_ICONE_HEIGHT));
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

