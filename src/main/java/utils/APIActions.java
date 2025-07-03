package utils;

import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIActions

{


        public static void GetAllData(String url,String JsonFilePath)
        {
            Allure.step("Add {Params} and {endpoint} to the request");
            // Fetch the endpoint from the json file
            String endpoint = Utils.getData(url,JsonFilePath);

            Allure.step("Send GET request to retrieve all Data list");
            // Send GET request to retrieve the user details using ErrorHandler to execute and validate
            Response getResponse = ErrorHandler.executeWithValidation(() ->
                            given()
                                    .when()
                                    .get(endpoint)
                                    .then()
                                    .extract()
                                    .response(),
                    200, "Error during retrieving List Data",true);

            // Extract and print the Books from the response
            Allure.step("Extract and print {Data} from the response");
            Utils.extractAllData(getResponse);


        }


        public static void GetDatabyID(String url, String id,String JsonFilePath)
        {
            Allure.step("Add {Params} and {endpoint} to the request");
            // Fetch the endpoint from the json file
            String endpoint = Utils.getData(url,JsonFilePath);
            // Fetch the Book id from the json file
            String Bookid = Utils.getData(id,JsonFilePath);
            // Construct full endpoint
            String fullendpoint = endpoint + Bookid;

            Allure.step("Send GET request to retrieve the Data details by ID");
            // Send GET request to retrieve the user details using ErrorHandler to execute and validate
            Response getResponse = ErrorHandler.executeWithValidation(() ->
                            given()
                                    .when()
                                    .get(fullendpoint)
                                    .then()
                                    .extract()
                                    .response(),
                    200, "Error during retrieving Data Details Data by id", true);

            // Extract and print the Books from the response
            Allure.step("Extract and print {Data} from the response");
            Utils.extractAllData(getResponse);

        }


        public static void AddNewData(String url, String Requestbody,String JsonFilePath)
        {
            Allure.step("Add {Params} and {endpoint} to the request");
            // Fetch all query parameters dynamically
            Map<String, Object> body = Utils.getBodyParams(Requestbody,JsonFilePath);
            // Fetch the endpoint from the json file
            String endpoint = Utils.getData("endpoint",JsonFilePath);

            Allure.step("Send Post request to Add new Data");
            // Send GET request to retrieve the user details using ErrorHandler to execute and validate
            Response getResponse = ErrorHandler.executeWithValidation(() ->
                            given().header("Content-Type", "application/json")
                                    .body(body)
                                    .when()
                                    .post(endpoint)
                                    .then()
                                    .extract()
                                    .response(),
                    200, "Error during retrieving Details Data by id", true);

            // Extract and print the Books from the response
            Allure.step("Extract and print {Data} from the response");
            Utils.extractAllData(getResponse);

        }


        public static void UpdateExistingDatabyID(String url,String id, String Requestbody,String JsonFilePath)
        {
            Allure.step("Add {Params} and {endpoint} to the request");
            // Fetch all query parameters dynamically
            Map<String, Object> body = Utils.getBodyParams(Requestbody,JsonFilePath);
            // Fetch the endpoint from the json file
            String endpoint = Utils.getData(url,JsonFilePath);
            // Fetch the Book id from the json file
            String Bookid = Utils.getData(id,JsonFilePath);
            // Construct full endpoint
            String fullendpoint = endpoint + Bookid;

            Allure.step("Send Put request to update existing Data by id");
            // Send GET request to retrieve the user details using ErrorHandler to execute and validate
            Response getResponse = ErrorHandler.executeWithValidation(() ->
                            given().header("Content-Type", "application/json")
                                    .body(body)
                                    .when()
                                    .put(fullendpoint)
                                    .then()
                                    .extract()
                                    .response(),
                    200, "Error during retrieving Books Details Data by id", true);

            // Extract and print the Books from the response
            Allure.step("Extract and print {Data} from the response");
            Utils.extractAllData(getResponse);

        }

    public static void DeleteDatabyID(String url, String id,String JsonFilePath)

    {
        Allure.step("Add {Params} and {endpoint} to the request");
        // Fetch the endpoint from the json file
        String endpoint = Utils.getData(url,JsonFilePath);
        // Fetch the Book id from the json file
        String Bookid = Utils.getData(id,JsonFilePath);
        // Construct full endpoint
        String fullendpoint = endpoint + Bookid;

        Allure.step("Send Delete request to delete Data by ID");
        // Send GET request to retrieve the user details using ErrorHandler to execute and validate
        Response getResponse = ErrorHandler.executeWithValidation(() ->
                        given()
                                .when()
                                .delete(fullendpoint)
                                .then()
                                .extract()
                                .response(),
                200, "Error during Deleting Details Data by id", false);

        // Extract and print the Books from the response
        Allure.step("Extract and print {Data} from the response");
        Utils.extractAllData(getResponse);


    }


    }

