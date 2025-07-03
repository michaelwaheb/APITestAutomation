package tests;

import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class Test_AuthorsAPIScenarios extends BaseTest {

    String JsonFilePath = "src/test/java/Data/AuthorsData.json";

@Test
@Story("Retrieve a list of all authors")
    public void GetAllAuthorList()
    {
        utils.APIActions.GetAllData("endpoint",JsonFilePath);

    }

    @Test
    @Story("Retrieve details of a specific author by their ID.")
    public void GetAuthorDetailsbyID()
    {
        utils.APIActions.GetDatabyID("endpoint","AuthorQueryParams.id",JsonFilePath);

    }

    @Test
    @Story("Add a new author to the system.")
    public void AddNewAuthor()
    {
        utils.APIActions.AddNewData("endpoint","AddNewAuthorParams",JsonFilePath);

    }

    @Test
    @Story("Update an existing author’s details.")
    public void UpdateExistingAuthorbyID()
    {
        utils.APIActions.UpdateExistingDatabyID("endpoint","updateExistAuthorParams.id","updateExistAuthorParams",JsonFilePath);

    }

    @Test
    @Story("Delete an author by their ID.")
    public void DeleteExistingAuthorbyID()
    {
        utils.APIActions.DeleteDatabyID("endpoint","AuthorQueryParams.id",JsonFilePath);

    }


    }

