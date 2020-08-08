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
import java.awt.event.KeyEvent;
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

/*
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 */

public class Frame {
    private static final int MOVIE_ICONE_WIDTH = 250;
    private static final int MOVIE_ICONE_HEIGHT = 400;

    public static void main(String[] args) throws IOException, ParseException {
        try {
            File myObj = new File("settings.json");
            if (myObj.createNewFile()) { // Creates new file
                Map<String, Integer> columns = new HashMap<>();
                Columns numOfColumns = new Columns();
                numOfColumns.setColumns(10);
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



            JFrame frame = new JFrame("Movie collecter");
        frame.setSize(1500, 3000);

        //Create the menu bar.
        JMenuBar menuBar = new JMenuBar();

        //Build the first menu.
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Refrech movie list",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menuItem.addActionListener(new RecfechMovieListActionListener());
        menu.add(menuItem);

        //a submenu
        menu.addSeparator();
        JMenu submenu = new JMenu("Settings");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        //menuItem.addActionListener(new ColumnSettingActionListener());
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);

        menu.add(submenu);
        frame.setJMenuBar(menuBar);

        Image applicationIcon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Josef\\IdeaProjects\\Movie-Collector\\src\\appIcon.png");
        frame.setIconImage(applicationIcon);


        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("settings.json"));

            JSONParser parser2 = new JSONParser();
            JSONObject json = (JSONObject) parser2.parse((String) obj);

            Long loudScreaming = (Long) json.get("columns");

            System.out.println(loudScreaming);


        } catch(Exception e) {
            e.printStackTrace();
        }



        JPanel panel = new JPanel(new GridLayout(0, 10, 25, 25));
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

                System.out.println("C:\\Users\\Josef\\PycharmProjects\\Movie-Collector\\images\\" + movieFileName.split("\\.")[0] + ".JPG");
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

        // Makes the frame scrolleble
        JScrollPane scrPane = new JScrollPane(panel);
        scrPane.getVerticalScrollBar().setUnitIncrement(20);

        // And JPanel needs to be added to the JFrame itself!
        frame.add(scrPane);
        frame.setVisible(true);
    }
}

