import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RecfechMovieListActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {

        SettingManager settingManager = new SettingManager();

        /*
        try {
            Runtime.getRuntime().exec(String.format("bash -c \"%s\"", "> movieList.txt"));
            JSONArray pathList = settingManager.getMoviePath();
            for (int i = 0; i < pathList.size(); i++) {
                String movieFolder = (String) pathList.get(i);

                // Convert the movieFolder to the right format
                String newMovieFolder = movieFolder.replace("\\", "/");
                System.out.println(newMovieFolder);
                String disc = newMovieFolder.split(":")[0].toLowerCase();
                String path = newMovieFolder.split(":")[1];

                String command = "find /mnt/" + disc + path + " -type f -name '*.mkv' -o -name '*.mp4' -o -name '*.wmv' -o -name '*.flv' -o -name '*.webm' -o -name '*.mov' | awk -F/ '{print $NF}' >> movieList.txt";
                System.out.println(command);
                Runtime.getRuntime().exec(String.format("bash -c \"%s\"", command));
            }

            Runtime.getRuntime().exec(String.format("bash -c \"%s\"", "sort -k4 -n movieList.txt"));

        } catch (Exception exception) {
            exception.printStackTrace();
        }

         */


        /*
        String command = "find " + MovieFolder + " -type f -name '*.mkv' -o -name '*.mp4' -o -name '*.wmv' -o -name '*.flv' -o -name '*.webm' -o -name '*.mov' | awk -F/ '{print $NF}' > movieList.txt";


        try {
            //Runtime.getRuntime().exec(String.format("bash -c \"%s\"", command1));
            Runtime.getRuntime().exec(String.format("bash -c \"%s\"", command2));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

*/

        BingImageSearchv7Quickstart bingImageSearchv7Quickstart = new BingImageSearchv7Quickstart();
        try {
            BingImageSearchv7Quickstart.URLSearch();
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }



    }




}
