package tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ErrorHandler;
import utils.Utils;
import java.util.Map;


import static io.restassured.RestAssured.given;

public class Test_APIScenarios extends BaseTest {



@Test
@Story("Retrieve Emails From Users Data")
    public void GetUsersData()
    {
        Allure.step("Add {Params} and {endpoint} to the request");
        // Fetch all query parameters dynamically
        Map<String, String> Params = Utils.getParamsFrom("queryParams");
        // Fetch the endpoint from the json file
        String endpoint = Utils.getData("endpoint");

        Allure.step("Send GET request to retrieve the users details");
        // Send GET request to retrieve the user details using ErrorHandler to execute and validate
        Response getResponse = ErrorHandler.executeWithValidation(() ->
                        given().queryParams(Params)
                                .when()
                                .get(endpoint)
                                .then()
                                .extract()
                                .response(),
                200, "Error during retrieving User Data");

         // Extract and print the Emails from the response
         Allure.step("Extract and print {Data} from the response");
         Utils.extractSpecificData(getResponse, "data.email");


        }
    }

