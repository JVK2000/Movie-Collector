package functionality;

import actionListener.PopClickListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Frame {

    static JPanel panel = new JPanel();;
    static Window window;

    public static void main(String[] args) throws Exception {
        // Creates settings file if no one exists
        SettingManager settingManager = new SettingManager();
        settingManager.createSaveFile();
        double movieIconWidth = settingManager.getMovieDimensionWidth();
        double movieIconHeight = settingManager.getMovieDimensionHeight();

        window = new Window("Movie collector");
        window.setFullScreen();
        window.addLoadScreen("GIFs/Dual Ring-1.5s-800px (1).gif", 200, 200);
        window.setIconImage("images/appIcon.png");

        Menu menu = new Menu(window);
        menu.createMenuItems();
        window.setJMenuBar(menu.getMenuBar());

        JScrollPane scrollPane = getJScrollPane();
        window.add(scrollPane);

        String fileName = "movieList.txt";
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!(line.equals(""))) {
                // the movie files name. Splits the string into parts, where / is the divider. Uses the last index to get the movie name.
                int movieFilePositionInPath = line.split("/").length - 1;
                String movieFileName = line.split("/")[movieFilePositionInPath];

                MButton button = getMButton((int) movieIconWidth, (int) movieIconHeight, movieFileName, line);
                panel.add(button);
            }
        }
        scanner.close();
        window.removeLoadScreen();
        window.setVisible(true);
    }

    private static JScrollPane getJScrollPane() {
        JPanel mainPanel = new JPanel();
        mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.DARK_GRAY);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(25, 0,0,25);  //top padding

        SearchComponent searchComponent = new SearchComponent();
        JPanel searchPanel = searchComponent.createSearchComponent();
        searchPanel.setBackground(Color.DARK_GRAY);

        mainPanel.add(searchPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.insets = new Insets(0, 0,0,0);  //top padding

        panel.setLayout(new WrapLayout(1, 25, 25));
        panel.setBackground(Color.DARK_GRAY);

        mainPanel.add(panel, c);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.setBackground(Color.DARK_GRAY);
        return scrollPane;
    }

    private static MButton getMButton(int movieIconWidth, int movieIconHeight, String movieFileName, String line) {
        MButton button;
        String imageFile = "movieCovers\\" + movieFileName.split("\\.")[0] + ".JPG";
        ImageIcon icon = new ImageIcon(imageFile);

        // Rescale the image to fit the button
        Image img = icon.getImage();

        Image newImg = img.getScaledInstance(movieIconWidth, movieIconHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);

        File imgFile = new File(imageFile);
        if (imgFile.exists()) {
            button = new MButton(movieFileName.split("\\(")[0], icon);
            button.setToolTipText(movieFileName.split("\\(")[0]);
            button.setBackground(Color.DARK_GRAY);
            System.out.println(imgFile);

        } else {
            button = new MButton(movieFileName.split("\\(")[0]);
            button.setForeground(Color.white);
            int min = 0;
            int max = 200;
            int redIndex = ThreadLocalRandom.current().nextInt(min, max + 1);
            int greenIndex = ThreadLocalRandom.current().nextInt(min, max + 1);
            int blueIndex = ThreadLocalRandom.current().nextInt(min, max + 1);

            Color myWhite = new Color(redIndex, greenIndex, blueIndex);
            button.setBackground(myWhite);
            button.setFont(new Font("Arial", Font.BOLD, 20));
        }

        // Add the movie path to the MButton object
        button.setMoviePath(line);


        // Makes the button borders "invisible"
        button.setBorder(new LineBorder(Color.DARK_GRAY));
        button.setPreferredSize(new Dimension((int) movieIconWidth, (int) movieIconHeight));

        button.addMouseListener(new PopClickListener(movieFileName));

        System.out.println(line);
        // Fetches the disc the movie is stored on
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
        return button;
    }


    public static void updateButton(String movieFileName) throws Exception {
        SettingManager settingManager = new SettingManager();
        double movieIconWidth = settingManager.getMovieDimensionWidth();
        double movieIconHeight = settingManager.getMovieDimensionHeight();

        for (int i = 0; i < panel.getComponentCount(); i++) {
            MButton button = (MButton) panel.getComponent(i);
            if (movieFileName.split("\\(")[0].equals(button.getText())) {
                System.out.println(movieFileName.split("\\(")[0] + "    " + button.getText());
                panel.remove(i);
                button = getMButton((int) movieIconWidth, (int) movieIconHeight, movieFileName, button.getMoviePath());
                panel.add(button, i);
            }
        }
    }


    public static void hideNonMatchingComponents(String searchTerm) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            MButton MButton = (MButton) panel.getComponent(i);
            double similarityIndex = StringSimilarity.similarity(MButton.getText(), searchTerm);
            MButton.setVisible(!(similarityIndex < 0.75));
            if (searchTerm.equals("")) showAllComponents();
        }
    }

    public static void showAllComponents() {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            MButton MButton = (MButton) panel.getComponent(i);
            MButton.setVisible(true);
        }
    }


    public static void restartApplication() throws IOException, URISyntaxException {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar = new File(Frame.class.getProtectionDomain().getCodeSource().getLocation().toURI());

        /* is it a jar file? */
        if(!currentJar.getName().endsWith(".jar"))
            return;

        /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();
        System.exit(0);
    }
}