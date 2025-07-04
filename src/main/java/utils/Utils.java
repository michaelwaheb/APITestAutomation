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
    public static JsonNode loadJsonData(String JsonFilepath) {
        queryParamsNode = null;
        try {
            queryParamsNode = objectMapper.readTree(new File(JsonFilepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryParamsNode;
    }

    // Method to get value using dot notation (e.g., "queryParams.animal_type")
    public static String getData(String keyPath,String JsonFilepath) {
        try {
            JsonNode node = objectMapper.readTree(new File(JsonFilepath));
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
    public static Map<String, String> getParamsFrom (String Params,String JsonFilepath)
    {

            try {
        Map<String, String> queryParams = new LinkedHashMap<>();
            queryParamsNode = objectMapper.readTree(new File(JsonFilepath)).get(Params);

            if (queryParamsNode == null) return queryParams; // Return empty map if no queryParams

            queryParamsNode.fields().forEachRemaining(entry ->
                    queryParams.put(entry.getKey(), entry.getValue().asText())
            );

            return queryParams;
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON file: " , e);
            }
        }

    public static Map<String, Object> getBodyParams(String key,String JsonFilepath) {
        try {
            Map<String, Object> map = new LinkedHashMap<>();
            JsonNode node = objectMapper.readTree(new File(JsonFilepath)).get(key);

            if (node == null || !node.isObject()) return map;

            node.fields().forEachRemaining(entry -> {
                JsonNode value = entry.getValue();
                if (value.isInt()) map.put(entry.getKey(), value.asInt());
                else if (value.isBoolean()) map.put(entry.getKey(), value.asBoolean());
                else if (value.isDouble()) map.put(entry.getKey(), value.asDouble());
                else map.put(entry.getKey(), value.asText());
            });

            return map;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Params.json", e);
        }
    }


    // Method to extract and print data from the response
    public static void extractSpecificData(Response response,String Data)
    {
        JsonPath jsonPath = response.jsonPath();

        Object extracted = jsonPath.get(Data);

        if (extracted instanceof List) {
            List<?> dataList = (List<?>) extracted;
            System.out.println("Extracted Data: " + dataList);
            Allure.step("List of '" + Data + "' extracted successfully");
            Allure.addAttachment("Extracted List", dataList.toString());
        } else {
            String data = String.valueOf(extracted);
            System.out.println("Extracted Data: " + data);
            Allure.step("Single '" + Data + "' extracted successfully");
            Allure.addAttachment("Extracted Value", data);
        }
    }
    // Method to extract and print all data from the response
    public static void extractAllData(Response response) {
        try {
            JsonPath jsonPath = response.jsonPath();
            Object fullData = jsonPath.get();

            // Get HTTP status information
            int statusCode = response.getStatusCode();
            String statusLine = response.getStatusLine();

            // Print to console
            System.out.println("✅ HTTP Status: " + statusCode + " - " + statusLine);
            System.out.println("🔍 Full JSON Response:\n" + fullData);

            // Attach to Allure
            Allure.step("✅ Status and response body extracted successfully");
            Allure.addAttachment("📨 HTTP Status", statusCode + " - " + statusLine);
            Allure.addAttachment("📦 Full JSON Response", "application/json", fullData.toString());

        } catch (Exception e) {
            System.err.println("❌ Failed to extract full data: " + e.getMessage());
            Allure.step("❌ Failed to extract full data");
            Allure.addAttachment("Extraction Error", e.getMessage());
        }
//        try {
//            JsonPath jsonPath = response.jsonPath();
//            Object fullData = jsonPath.get();
//
//            // Print to console
//            System.out.println("🔍 Full JSON Response:\n" + fullData);
//
//            // Attach to Allure
//            Allure.step("Full response extracted successfully");
//            Allure.addAttachment("Full JSON Response", "application/json", fullData.toString());
//        } catch (Exception e) {
//            System.err.println("❌ Failed to extract full data: " + e.getMessage());
//            Allure.step("Failed to extract full data");
//            Allure.addAttachment("Extraction Error", e.getMessage());
//        }
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




