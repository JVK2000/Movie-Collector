package actionListener;


import searchApi.URLDownloader;
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
    final String movieFileName;

    public changeImageURLActionListener(String movieFileName) {
        this.movieFileName = movieFileName;
    }

    public void actionPerformed(ActionEvent e) {

        JTextField url = new JTextField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Enter URL for " + movieFileName.split("\\(")[0] + ""),
                url,

        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.DEFAULT_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                URLDownloader.downloadImageFromUrl(movieFileName, url.getText());
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("imageURL.json"));
                jsonObject.put(movieFileName, url.getText());
                Files.write(Paths.get("imageURL.json"), jsonObject.toJSONString().getBytes());

            } catch (IOException | ParseException ioException) {
                final JComponent[] errorDialog = new JComponent[] {
                        new JLabel("Bad URL")};
                JOptionPane.showConfirmDialog(null, errorDialog, "My custom dialog", JOptionPane.DEFAULT_OPTION);
                ioException.printStackTrace();
            }
        } else {
            System.out.println("User canceled / closed the dialog, result = " + result);
        }

    }



}
