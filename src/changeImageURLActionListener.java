import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class changeImageURLActionListener implements ActionListener {
    String movieFileName;

    public changeImageURLActionListener(String movieFileName) {
        this.movieFileName = movieFileName;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("såsååsåws");

        /*
        JDialog jDialog = new JDialog();

        JFrame jFrame = new JFrame();
        jFrame.setSize(250, 150);
        JPanel panel = new JPanel(new GridLayout(0, 2, 20, 20));

        JLabel welcome = new JLabel("Please image url:");
        panel.add(welcome);
        JTextArea jTextField = new JTextArea();
        panel.add(jTextField);

        jFrame.add(panel);
        jFrame.setVisible(true);

         */

        JTextField url = new JTextField();
        JTextField lastName = new JTextField();
        JPasswordField password = new JPasswordField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Enter URL for " + movieFileName.split("\\(")[0] + ""),
                url,

        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                URLDownlowder.downlowdImageFromUrl(movieFileName, url.getText());

                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("imageURL.json"));
                jsonObject.put(movieFileName, url.getText());
                Files.write(Paths.get("imageURL.json"), jsonObject.toJSONString().getBytes());

            } catch (IOException | ParseException ioException) {
                final JComponent[] errorDialog = new JComponent[] {
                        new JLabel("Bad URL")};
                JOptionPane.showConfirmDialog(null, errorDialog, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
                ioException.printStackTrace();
            }
        } else {
            System.out.println("User canceled / closed the dialog, result = " + result);
        }

    }



}
