package utils;

import io.qameta.allure.Allure;
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
    public static Response executeWithValidation(ApiCall apiCall, int expectedStatusCode, String errorMessage, boolean checkBody) {
        Response response = null;
        try {
            // Enable logging of request and response if validation fails
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

            // Execute the API call
            response = apiCall.call();

            // Always print and attach response body (even if it fails)
            String responseBody = response.getBody().asString().trim();
            System.out.println("📩 Response Body:\n" + responseBody);
            Allure.addAttachment("📩 Raw Response Body", "application/json", responseBody);

            // Check if response is null
            Assert.assertNotNull(response, errorMessage + " - Response is null.");

            // Validate status code
            Assert.assertEquals(response.statusCode(), expectedStatusCode,
                    errorMessage + " - Expected status: " + expectedStatusCode + ", but got: " + response.statusCode());

            // Optionally check that body is not empty
            if (checkBody) {
                Assert.assertFalse(responseBody.isEmpty(), errorMessage + " - Response body is empty.");
            }

        } catch (AssertionError ae) {
            // Still attach response in case of failed assertion
            if (response != null && response.getBody() != null) {
                String body = response.getBody().asString().trim();
                Allure.addAttachment("❌ Failed Response Body", "application/json", body);
            }
            throw ae;  // Rethrow to preserve TestNG failure
        } catch (Exception e) {
            System.err.println(errorMessage + " - Exception: " + e.getMessage());
            Assert.fail(errorMessage + " - Exception: " + e.getMessage());
        }

        return response;
    }
}
//        try {
//            // Enable logging of request and response if validation fails
//            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//
//            // Execute the API call and get the response
//            response = apiCall.call();
//
//            // Assertion: Check if the response is null
//            Assert.assertNotNull(response, errorMessage + " - Response is null.");
//
//            // Assertion: Validate the status code
//            Assert.assertEquals(response.statusCode(), expectedStatusCode,
//                    errorMessage + " - Expected status: " + expectedStatusCode + ", but got: " + response.statusCode());
//
//
//            if (checkBody)
//            {
//                String body = response.getBody().asString().trim();
//                Assert.assertFalse(body.isEmpty(),
//                        errorMessage + " - Response body is empty.");
//            }
//
//        } catch (Exception e) {
//            System.err.println(errorMessage + " - Exception: " + e.getMessage());
//            // Optionally fail the test here in case of an exception
//            Assert.fail(errorMessage + " - Exception: " + e.getMessage());
//        }
//
//        return response;
//        }

