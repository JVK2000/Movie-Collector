/*Copyright (c) Microsoft Corporation. All rights reserved.
Licensed under the MIT License.*/

import com.google.gson.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
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
 * javac -d bin -sourcepath src -cp lib/gson-2.8.5.jar src/BingImageSearchv7Quickstart.java
 * java -cp bin;lib/gson-2.8.5.jar BingImageSearchv7Quickstart
 */

class BingImageSearchv7Quickstart {

// ***********************************************
// *** Update or verify the following values. ***
// **********************************************

    // Bing Search V7 subscription key.
    static String subscriptionKey = "675f17090916467cbdb6e24daa409f8e";

    // Bing Search V7 endpoint.
    static String host = "https://moviecollector.cognitiveservices.azure.com/";

    static String path = "/bing/v7.0/images/search";


    public static SearchResults SearchImages (String searchQuery) throws Exception {

        // construct URL of search request (endpoint + query string)
        URL url = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8"));
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

        // receive JSON body
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();

        // construct result object for return
        SearchResults results = new SearchResults(new HashMap<String, String>(), response);

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

    public static void main (String[] args) {
       /* if (subscriptionKey.length() != 32) {
            System.out.println("Invalid Bing Search API subscription key!");
            System.out.println("Please paste yours into the source code.");
            System.exit(1);
        }*/

        try {
            String fileName = "E://Movies/movies.txt";
            Path moviePath = Paths.get(fileName);
            Scanner scanner = new Scanner(moviePath);

            try(FileWriter fw = new FileWriter("E:\\Movies/movieCoversURLs.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                while (scanner.hasNextLine()) {
                    String searchTerm = scanner.nextLine().split("\\.")[0];
                    System.out.println("Searching the Web for: " + searchTerm + " poster");
                    SearchResults result = SearchImages(searchTerm + " poster");
                    JsonParser parser = new JsonParser();
                    JsonObject json = parser.parse(result.jsonResponse).getAsJsonObject();
                    JsonArray results = json.getAsJsonArray("value");
                    JsonObject first_result = (JsonObject)results.get(0);
                    String resultURL = first_result.get("thumbnailUrl").getAsString();
                    out.println(resultURL);
                    System.out.println(resultURL);
                    TimeUnit.MILLISECONDS.sleep(500);

                } scanner.close();

            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(1);
        }
    }
}

// Container class for search results encapsulates relevant headers and JSON data
class SearchResults{
    HashMap<String, String> relevantHeaders;
    String jsonResponse;
    SearchResults(HashMap<String, String> headers, String json) {
        relevantHeaders = headers;
        jsonResponse = json;
    }
}