package models;

import models.XMLDatabase;
import models.Portfolio;
import models.Stock;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

//import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class XMLDatabaseTest {

    private XMLDatabase xmlDatabase;

    @Before
    public void setUp() {
        xmlDatabase = new XMLDatabase(); // Assuming a default constructor or mock setup
    }

    @Test
    public void testReadImportedFile() {
        // Assuming you have a test file in your test resources folder
        Portfolio portfolio = xmlDatabase.readImportedFile("TestPortfolio");
        assertNotNull("Portfolio should not be null", portfolio);
        // Perform more assertions based on expected content of TestPortfolio.xml
    }

    @Test
    public void testAddUser() {
        // This test might be more challenging to implement without modifying XMLDatabase to be more test-friendly.
        // As an example, you could verify the file content after adding a user, but this requires reading the file again.
        String username = "testUser";
        xmlDatabase.addUser(username);
        // Assert that the user has been added successfully.
        // This could involve reading back the XML content to verify the new user exists.
        // Alternatively, use Mockito to mock the document and verify `appendChild` is called.
    }

    @Test
    public void testGetPortfoliosByUsername() {
        // This method relies on the structure of your XML and the presence of specific users and portfolios.
        String username = "existingUser";
        List<Portfolio> portfolios = xmlDatabase.getPortfoliosByUsername(username);
        assertNotNull("Portfolios list should not be null", portfolios);
        assertFalse("Portfolios list should not be empty", portfolios.isEmpty());
        // Perform more assertions based on expected portfolios for 'existingUser'
    }

    @Test
    public void testIsDateExistInXML() {
        // Assuming "TestStockData.xml" contains date "2021-01-01"
        boolean exists = xmlDatabase.isDateExistInXML("TestStockData", "2021-01-01");
        assertTrue("Date should exist in XML", exists);
    }

    // Additional tests can be implemented similarly for other methods.

}
