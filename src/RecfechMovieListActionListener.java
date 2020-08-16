import org.json.simple.JSONArray;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RecfechMovieListActionListener implements ActionListener {
    private static PythonInterpreter pythonInterpreter;

    public RecfechMovieListActionListener() throws IOException {
    }

    private static void test1() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("import C:\\Users\\Josef\\PycharmProjects\\Movie-Collector\\hello_world1");
        PyObject func = interpreter.get("C:\\Users\\Josef\\PycharmProjects\\Movie-Collector\\hello_world1").__getattr__("get_name");
        System.out.println(func.__call__().__tojava__(String.class));
    }

    public void actionPerformed(ActionEvent e) {
        SettingManager settingManager = new SettingManager();

        try {
            Runtime.getRuntime().exec(String.format("bash -c \"%s\"", "> movieList.txt"));  // resets the preciously movieList
            JSONArray pathList = settingManager.getMoviePath();
            for (int i = 0; i < pathList.size(); i++) {
                String movieFolder = (String) pathList.get(i);

                // Convert the movieFolder to the right format
                String newMovieFolder = movieFolder.replace("\\", "/");
                String disc = newMovieFolder.split(":")[0].toLowerCase();
                String path = newMovieFolder.split(":")[1];

                String command = "find /mnt/" + disc + path + " -type f -name '*.mkv' -o -name '*.mp4' -o -name '*.wmv' -o -name '*.flv' -o -name '*.webm' -o -name '*.mov' | awk -F/ '{print $NF}' >> movieList.txt";
                //System.out.println(command);
                Runtime.getRuntime().exec(String.format("bash -c \"%s\"", command));
            }

            //Runtime.getRuntime().exec(String.format("bash -c \"%s\"", "sort -k4 -n movieList.txt"));    // dont work3
            runGoogleSeachApi();


        } catch (Exception exception) {
            exception.printStackTrace();
        }



        /*
        BingImageSearchv7Quickstart bingImageSearchv7Quickstart = new BingImageSearchv7Quickstart();
        try {
            BingImageSearchv7Quickstart.URLSearch();
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }

         */

/*
        try{
            String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
            BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/Josef/PycharmProjects/Movie-Collector/googleSeachApi.py"));
            out.write(prg);
            out.close();
            int number1 = 10;
            int number2 = 32;
            String command = "python C:/Users/Josef/PycharmProjects/Movie-Collector/googleSeachApi.py";
            Process p = Runtime.getRuntime().exec(String.format("bash -c \"%s\"", command));
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int ret = Integer.parseInt(in.readLine());
            System.out.println("value is : "+ret);
        }catch(Exception exception){
            System.out.println(exception);
        }

 */


    }

    public void runGoogleSeachApi() throws IOException {
        String command1 = "cd /mnt";
        String command2 = "source ./c/Users/Josef/PycharmProjects/Movie-Collector/venv/Scripts/activate";
        String command3 = "python3 c/Users/Josef/PycharmProjects/Movie-Collector/googleSeachApi.py";
        String finalcommand = command1 + " && " + command2 + " && " + command3;
        Process process = Runtime.getRuntime().exec(String.format("bash -c \"%s\"", finalcommand));

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
