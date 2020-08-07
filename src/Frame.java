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
    private static final int MOVIE_ICONE_WIDTH = 250;
    private static final int MOVIE_ICONE_HEIGHT = 400;

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Movie collecter");
        frame.setSize(1500, 3000);

        Image applicationIcon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Josef\\IdeaProjects\\Movie-Collector\\src\\appIcon.png");

        frame.setIconImage(applicationIcon);

        JPanel panel = new JPanel(new GridLayout(0, 8, 25, 25));
        panel.setBackground(Color.DARK_GRAY);

        String fileName = "E://Movies/movies.txt";
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!(line.equals(""))) {
                ImageIcon icon = new ImageIcon("C:\\Users\\Josef\\PycharmProjects\\Movie-Collector\\images\\" + line.split("\\.")[0] + ".JPG");

                // Rescale the image to fit the button
                Image img = icon.getImage() ;
                Image newimg = img.getScaledInstance(MOVIE_ICONE_WIDTH, MOVIE_ICONE_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;
                icon = new ImageIcon( newimg );

                JButton button = new JButton(line, icon);

                // Makes the button bordesrs "invisable"
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.DARK_GRAY);
                button.setBorder(new LineBorder(Color.DARK_GRAY));
                button.setPreferredSize(new Dimension(MOVIE_ICONE_WIDTH, MOVIE_ICONE_HEIGHT));
                button.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        File file = new File("E:/Movies/Movies/" + line.split("\\.")[0] + "/" + line);
                        Desktop desktop = Desktop.getDesktop();
                        if(!(file.exists())) {
                            file = new File("F:/Film/" + line.split("\\.")[0] + "/" + line);
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

