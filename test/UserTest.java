
import models.Portfolio;
import models.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("testUser", 10000.0);
        user.setPassWord("password123");
    }

    @Test
    public void testGetUserName() {
        assertEquals("testUser", user.getUserName());
    }

    @Test
    public void testSetAndGetPassWord() {
        user.setPassWord("newPassword");
        assertEquals("newPassword", user.getPassWord());
    }

    @Test
    public void testGetAndSetBuyingPower() {
        user.setBuyingPower(20000.0);
        assertEquals(20000.0, user.getBuyingPower(), 0.001);
    }

    @Test
    public void testAddAndGetPortfolio() {
        Portfolio portfolio = new Portfolio("Name"); // Assuming a default constructor or relevant constructor
        user.addPortfolio(portfolio);
        assertEquals(portfolio, user.getPortfolio(0));
    }

    @Test
    public void testGetPortfolioList() {
        Portfolio portfolio1 = new Portfolio("Name"); // Assuming a default constructor
        Portfolio portfolio2 = new Portfolio("Name"); // Assuming a default constructor
        user.addPortfolio(portfolio1);
        user.addPortfolio(portfolio2);
        assertEquals(2, user.getPortfolioList().size());
    }

    // Implement additional tests for edge cases and error conditions
}