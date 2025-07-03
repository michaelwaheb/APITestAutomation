package tests;

import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class Test_BooksAPIScenarios extends BaseTest {

    String JsonFilePath = "src/test/java/Data/BooksData.json";

    @Test
    @Story("Retrieve a list of all books")
    public void GetAllBooksList() {
        utils.APIActions.GetAllData("endpoint",JsonFilePath);

    }

    @Test
    @Story("Retrieve details of a specific book by its ID.")
    public void GetBooksDetailsbyID() {
        utils.APIActions.GetDatabyID("endpoint", "BookQueryParams.id",JsonFilePath);

    }

    @Test
    @Story("Add a new book to the system.")
    public void AddNewBook() {
        utils.APIActions.AddNewData("endpoint", "AddNewBookParams",JsonFilePath);

    }

    @Test
    @Story("Update an existing book by its ID.")
    public void UpdateExistingBookbyID() {
        utils.APIActions.UpdateExistingDatabyID("endpoint", "updateExistBookParams.id", "updateExistBookParams",JsonFilePath);

    }

    @Test
    @Story("Delete an existing book by its ID.")
    public void DeleteExistingBookbyID() {
        utils.APIActions.DeleteDatabyID("endpoint", "BookQueryParams.id",JsonFilePath);

    }

}

