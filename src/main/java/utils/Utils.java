package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import io.restassured.path.json.JsonPath;
import java.util.List;

public class Utils {
    private static JsonNode queryParamsNode;
    private static final ObjectMapper objectMapper = new ObjectMapper();



    // Method to load data from Json file
    public static JsonNode loadJsonData() {
        queryParamsNode = null;
        try {
            queryParamsNode = objectMapper.readTree(new File("src/test/java/Data/Params.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryParamsNode;
    }

    // Method to get value using dot notation (e.g., "queryParams.animal_type")
    public static String getData(String keyPath) {
        try {
            JsonNode node = objectMapper.readTree(new File("src/test/java/Data/Params.json"));
            String[] keys = keyPath.split("\\.");
            for (String key : keys) {
                if (node != null) {
                    node = node.get(key);
                }
            }
            return node != null ? node.asText() : null;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " , e);
        }
    }

    // Method to get all query parameters as a Map
    public static Map<String, String> getParamsFrom (String Params){
            try {
        Map<String, String> queryParams = new LinkedHashMap<>();
            queryParamsNode = objectMapper.readTree(new File("src/test/java/Data/Params.json")).get(Params);

            if (queryParamsNode == null) return queryParams; // Return empty map if no queryParams

            queryParamsNode.fields().forEachRemaining(entry ->
                    queryParams.put(entry.getKey(), entry.getValue().asText())
            );

            return queryParams;
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON file: " , e);
            }
        }

    // Method to extract and print data from the response
    public static void extractSpecificData(Response response,String Data)
    {
        // Extract data using JsonPath
        JsonPath jsonPath = response.jsonPath();
        List<String> TargetData = jsonPath.getList(Data);

        // Print the extracted data
        System.out.println("Extracted Data: " + TargetData);
        Allure.step("Check Success Result");
        Allure.addAttachment("The'" +Data+ "'Extracted Successfully", "The target Data:( "+ TargetData + " ).");
    }

    // Method to start and stop the Allure server
    public static void startAndStopAllureServe() throws IOException, InterruptedException {
        // Construct the Maven command for running Allure serve
        String mavenCommand = "allure serve \"target/allure-results\"";
        String projectpath = Paths.get("").toAbsolutePath().toString();
        // Start the Allure server process using ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", mavenCommand);
        processBuilder.directory(new java.io.File(projectpath)); // Set project directory

        // Start the Allure server
        Process allureProcess = processBuilder.start();

        // Capture the output from the server process
        BufferedReader reader = new BufferedReader(new InputStreamReader(allureProcess.getInputStream()));

        // Start a thread to read and display the output from the Allure server
        Thread outputThread = new Thread(() -> {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);  // Print the output from the server
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        outputThread.start();

        // Simulate some work or delay (e.g., let the server run for 10 seconds)
        Thread.sleep(10000); // You can adjust the time duration here

        // Stop the Allure server
        if (allureProcess.isAlive()) {
            allureProcess.destroy();  // Gracefully stop the process
            System.out.println("Allure server stopped gracefully.");
        } else {
            System.out.println("No Allure server process to stop.");
        }


    }
}




