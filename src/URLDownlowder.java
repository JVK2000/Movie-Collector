import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class URLDownlowder {
    /*
    public static void saveImage() throws Exception {
        String movieList = "movieList.txt";
        Path path = Paths.get(movieList);
        Scanner scanner = new Scanner(path);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!(line.equals(""))) {
                int movieFilePositionInPath = line.split("/").length - 1;
                String movieFileName = line.split("/")[movieFilePositionInPath];

                JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("imageURL.json");
                String imageUrl = (String) jsonObject.get(movieFileName);
                System.out.println("imageUrl  " + imageUrl);

                if (!(imageUrl.equals("URL missing"))) {
                    URL url = new URL(imageUrl);
                    String fileName = url.getFile();
                    String destName = "./figures" + fileName.substring(fileName.lastIndexOf("/"));
                    System.out.println(destName);

                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream("movieCovers/" + movieFileName.split("\\.")[0] + ".jpg");

                    byte[] b = new byte[2048];
                    int length;

                    while ((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }
                    is.close();
                    os.close();
                }
            }
        }
    }*/

    public static void saveImage() throws Exception {
        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("imageURL.json");

        for (Object o : jsonObject.keySet()) {
            String movieFileName = (String) o;  // key
            System.out.println(movieFileName);
            String movie_url = (String) jsonObject.get(movieFileName);

            final Path path = Paths.get("movieCovers/" + movieFileName);

            if (!(movie_url.equals("URL missing")) && !Files.exists(path)) {
                System.out.println(movieFileName);
                downlowdImageFromUrl(movieFileName, movie_url);
            }
        }
    }

    public static void downlowdImageFromUrl(String movieFileName, String image_url) throws IOException {
        URL url = new URL(image_url);
        String fileName = url.getFile();
        String destName = "./figures" + fileName.substring(fileName.lastIndexOf("/"));
        System.out.println(destName);

        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream("movieCovers/" + movieFileName.split("\\.")[0] + ".jpg");

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();


    }



    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

        public static void main (String[]args) throws Exception {
            saveImage();
        }
    }



