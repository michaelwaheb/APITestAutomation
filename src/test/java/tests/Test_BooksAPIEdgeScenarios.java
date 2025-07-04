package tests;

import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class Test_BooksAPIEdgeScenarios extends BaseTest {

    String JsonFilePath = "src/test/java/Data/BooksData.json";


    @Test
    @Story("Retrieve details of a specific book by not exist ID.")
    public void GetBooksDetailsbyIDnotexist() {
        utils.APIActions.GetDatabyID("endpoint", "BookParamsEdge.id",JsonFilePath,404);

    }
    @Test
    @Story("Retrieve details of a specific book by ID is a string or special character.")
    public void GetBooksDetailsbystringorspecialID() {
        utils.APIActions.GetDatabyID("endpoint", "BookParamsEdge.id",JsonFilePath,404);

    }

    @Test
    @Story("Retrieve details of a specific book by negative ID ")
    public void GetBooksDetailsbynegativeID() {
        utils.APIActions.GetDatabyID("endpoint", "BookParamsEdge3.id",JsonFilePath,404);

    }

    @Test
    @Story("Add a new book to the system with Empty required fields.")
    public void AddnewBookwithemptyfields() {
        utils.APIActions.AddNewData("endpoint", "BookParamsEdge4",JsonFilePath,400);

    }
    @Test
    @Story("Add a new book to the system with additional unknown fields")
    public void AddnewBookwithadditinalfields() {
        utils.APIActions.AddNewData("endpoint", "BookParamsEdge",JsonFilePath,400);

    }
    @Test
    @Story("Add a new book to the system with same fields values")
    public void AddnewBookwithsamefieldsvalues() {
        utils.APIActions.AddNewData("endpoint", "BookParamsEdge5",JsonFilePath,409);

    }

    @Test
    @Story("Update an existing book by a non-existing ID.")
    public void UpdateExistingBookbyanonexistingID() {
        utils.APIActions.UpdateExistingDatabyID("endpoint", "BookParamsEdge.id", "updateExistBookParams",JsonFilePath,404);

    }
    @Test
    @Story("Update an existing book by a negative ID.")
    public void UpdateExistingBookbynegativeID() {
        utils.APIActions.UpdateExistingDatabyID("endpoint", "BookParamsEdge3.id", "updateExistBookParams",JsonFilePath,404);

    }
    @Test
    @Story("Update an existing book by ID with extra field.")
    public void UpdateExistingBookbyIDwithextrafield() {
        utils.APIActions.UpdateExistingDatabyID("endpoint", "BookParamsEdge.id", "updateExistBookParams",JsonFilePath,400);

    }

    @Test
    @Story("Delete an existing book by non-existing ID.")
    public void DeleteExistingBookbynonexistingID() {
        utils.APIActions.DeleteDatabyID("endpoint", "BookParamsEdge.id",JsonFilePath,404);

    }
    @Test
    @Story("Delete an existing book by ID is a string or special character..")
    public void DeleteExistingBookbystringorspecialID() {
        utils.APIActions.DeleteDatabyID("endpoint", "BookParamsEdge2.id",JsonFilePath,400);

    }
    @Test
    @Story("Delete an existing book by negative ID ")
    public void DeleteExistingBookbynegativeID() {
        utils.APIActions.DeleteDatabyID("endpoint", "BookParamsEdge2.id",JsonFilePath,400);

    }

}

