package tests;

import config.ConfigLoader;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.Utils;

import java.io.IOException;

public class BaseTest
{

    @BeforeSuite
    public void setup() {
        // Set the base URL
        //RestAssured.baseURI = ConfigLoader.getBaseUrl();
    }

    @AfterSuite
    public void TearDown() {

//Start then stop Allure serve after test finish
        try {
            Utils.startAndStopAllureServe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
