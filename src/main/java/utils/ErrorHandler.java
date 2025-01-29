package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class ErrorHandler {

    // Functional interface for API call
    @FunctionalInterface
    public interface ApiCall {
        Response call() throws Exception; // Abstract method
    }

    // Method to execute the API call and validate the response
    public static Response executeWithValidation(ApiCall apiCall, int expectedStatusCode, String errorMessage) {
        Response response = null;

        try {
            // Enable logging of request and response if validation fails
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

            // Execute the API call and get the response
            response = apiCall.call();

            // Assertion: Check if the response is null
            Assert.assertNotNull(response, errorMessage + " - Response is null.");

            // Assertion: Validate the status code
            Assert.assertEquals(response.statusCode(), expectedStatusCode,
                    errorMessage + " - Expected status: " + expectedStatusCode + ", but got: " + response.statusCode());

            // Assertion: Ensure that the response body is not empty
            Assert.assertFalse(response.getBody().asString().isEmpty(),
                    errorMessage + " - Response body is empty.");

        } catch (Exception e) {
            System.err.println(errorMessage + " - Exception: " + e.getMessage());
            // Optionally fail the test here in case of an exception
            Assert.fail(errorMessage + " - Exception: " + e.getMessage());
        }

        return response;
        }
}

