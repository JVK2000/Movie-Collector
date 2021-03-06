package searchApi;/*Copyright (c) Microsoft Corporation. All rights reserved.
Licensed under the MIT License.*/

import com.google.gson.*;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
 * Gson: https://github.com/google/gson
 * Maven info:
 *     groupId: com.google.code.gson
 *     artifactId: gson
 *     version: 2.8.1
 *
 * Once you have compiled or downloaded gson-2.8.1.jar, assuming you have placed it in the
 * same folder as this file (BingImageSearch.java), you can compile and run this program with the following steps:
 * 1. create three directories, named "bin", "src", and "lib"
 * 2. place this .java file in "src"
 * 3. download the latest gson .jar file online, and place it in the "lib" folder
 * 4. replace the subscriptionKey value with your valid Cognitive services subscription key
 * 5. run the following commands, replacing "lib/gson-2.8.5.jar" with your .jar file:
 * javac -d bin -sourcepath src -cp lib/gson-2.8.5.jar src/SearchApi.BingImageSearchv7Quickstart.java
 * java -cp bin;lib/gson-2.8.5.jar SearchApi.BingImageSearchv7Quickstart
 */

public class BingImageSearchv7Quickstart {

// ***********************************************
// *** Update or verify the following values. ***
// **********************************************

    public static JSONObject object = new JSONObject();

    // Bing Search V7 subscription key.
    static String subscriptionKey = "675f17090916467cbdb6e24daa409f8e";
    //static String subscriptionKey = "2ae739d57fe04f68bf6bbdea81cb8e7c";

    // Bing Search V7 endpoint.
    static String host = "https://moviecollector.cognitiveservices.azure.com/";
    //static String host = "https://westus.api.cognitive.microsoft.com/";


    static String path = "/bing/v7.0/images/search";


    public static SearchResults SearchImages(String searchQuery) throws Exception {

        // construct URL of search request (endpoint + query string)
        URL url = new URL(host + path + "?q=" + URLEncoder.encode(searchQuery, "UTF-8"));
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

        // receive JSON body
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();

        // construct result object for return
        SearchResults results = new SearchResults(new HashMap<>(), response);

        // extract Bing-related HTTP headers
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String header : headers.keySet()) {
            if (header == null) continue;      // may have null key
            if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
                results.relevantHeaders.put(header, headers.get(header).get(0));
            }
        }

        stream.close();
        return results;
    }

    // pretty-printer for JSON; uses GSON parser to parse and re-serialize
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    public static void main(String[] args) throws Exception {
        URLSearch();
    }


    public static void URLSearch() throws IOException, InterruptedException {
        String fileName = "movieList.txt";
        Path moviePath = Paths.get(fileName);
        Scanner scanner = new Scanner(moviePath);

/*
        FileWriter fw = new FileWriter("imageURLs.txt", false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

 */

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int movieFilePositionInPath = line.split("/").length - 1;
            String movieFileName = line.split("/")[movieFilePositionInPath];

            String searchTerm = movieFileName.split("\\.")[0].split("\\(")[0];
            System.out.println("Searching the Web for: " + searchTerm + "movie poster");

            try {

                SearchResults result = SearchImages(searchTerm + "movie poster");
                JsonParser parser = new JsonParser();

                Object obj = parser.parse(new FileReader("imageURL.json"));
                // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
                JsonObject jsonObject = (JsonObject) obj;
                if (!jsonObject.has(movieFileName)) {
                    System.out.println("----------------------");
                }

                /*
                JsonObject json = parser.parse(result.jsonResponse).getAsJsonObject();
                JsonArray results = json.getAsJsonArray("value");
                JsonObject first_result = (JsonObject) results.get(0);
                String resultURL = first_result.get("thumbnailUrl").getAsString();
                //out.println(resultURL);
                System.out.println(resultURL);
                object.put(movieFileName, resultURL);

                 */

            } catch (Exception e) {
                System.out.println(movieFileName + ": " + "URL missing");
               //out.println(movieFileName + ": " + "URL missing");
                object.put(movieFileName, "URL missing");
                System.out.println(e);

            }
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        //out.close();
        scanner.close();
        Files.write(Paths.get("imageURL.json"), object.toJSONString().getBytes());

    }
}


// Container class for search results encapsulates relevant headers and JSON data
class SearchResults {
    final HashMap<String, String> relevantHeaders;
    final String jsonResponse;

    SearchResults(HashMap<String, String> headers, String json) {
        relevantHeaders = headers;
        jsonResponse = json;
    }
}
