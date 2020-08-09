import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RecfechMovieListActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {

        String command1 = "cd /mnt/e/Movies/";
        String command2 = "find /mnt/e/Movies/Movies -type f -name '*.mkv' -o -name '*.mp4' -o -name '*.wmv' -o -name '*.flv' -o -name '*.webm' -o -name '*.mov' | awk -F/ '{print $NF}' > movieList.txt";

        try {
            Runtime.getRuntime().exec(String.format("bash -c \"%s\"", command1));
            Runtime.getRuntime().exec(String.format("bash -c \"%s\"", command2));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        BingImageSearchv7Quickstart bingImageSearchv7Quickstart = new BingImageSearchv7Quickstart();
        try {
            BingImageSearchv7Quickstart.URLSearch();
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }

    }



}
