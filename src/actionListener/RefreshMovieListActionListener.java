package actionListener;

import functionality.SettingManager;
import searchApi.BingImageSearchv7Quickstart;
import searchApi.URLDownloader;
import org.json.simple.JSONArray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RefreshMovieListActionListener implements ActionListener {

    public RefreshMovieListActionListener() {
    }

    
    public void actionPerformed(ActionEvent e) {
        reloadMovieList();

        try {
            URLDownloader.saveImage();
            runGoogleSearchApi();
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }
        
        // BingImageSearchApi();
    }
    

    private void BingImageSearchApi() {
        try {
            BingImageSearchv7Quickstart.URLSearch();
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }


    private void reloadMovieList() {
        SettingManager settingManager = new SettingManager();

        try {
            Runtime.getRuntime().exec(String.format("bash -c \"%s\"", "> movieList.txt"));  // resets the preciously movieList
            JSONArray pathList = settingManager.getMoviePath();
            for (Object o : pathList) {
                String movieFolder = (String) o;

                // Convert the movieFolder to the right format
                String newMovieFolder = movieFolder.replace("\\", "/");
                String disc = newMovieFolder.split(":")[0].toLowerCase();
                String path = newMovieFolder.split(":")[1];

                String command = "find /mnt/" + disc + path + " -type f -name '*.mkv' -o -name '*.mp4' -o -name '*.wmv' -o -name '*.flv' -o -name '*.webm' -o -name '*.mov' | awk -F/ '{print $NF}' >> movieList.txt";
                //System.out.println(command);
                Runtime.getRuntime().exec(String.format("bash -c \"%s\"", command));
            }

            //Runtime.getRuntime().exec(String.format("bash -c \"%s\"", "sort -k4 -n movieList.txt"));    // dont work3

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    

    public void runGoogleSearchApi() throws IOException {
        String command1 = "cd /mnt";
        String command2 = "source ./c/Users/Josef/PycharmProjects/Movie-Collector/venv/Scripts/activate";
        String command3 = "python3 c/Users/Josef/PycharmProjects/Movie-Collector/googleSearchApi.py";
        String finalCommand = command1 + " && " + command2 + " && " + command3;
        Process process = Runtime.getRuntime().exec(String.format("bash -c \"%s\"", finalCommand));

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));

        // Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }
}
