import models.Stock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StockTest {

    private Stock stock;

    @Before
    public void setUp() {
        stock = new Stock("Test Company", 500);
        stock.setVolume(1000);
        stock.setLocalHigh(50);
        stock.setLocalLow(30);
    }

    @Test
    public void testGetCompanyName() {
        assertEquals("Test Company", stock.getCompanyName());
    }

    @Test
    public void testGetUserShared() {
        assertEquals(500, stock.getUserShared());
    }

    @Test
    public void testGetVolume() {
        assertEquals(1000, stock.getVolume());
    }

    @Test
    public void testGetLocalHigh() {
        assertEquals(50, stock.getLocalHigh(), 0.001);
    }

    @Test
    public void testGetLocalLow() {
        assertEquals(30, stock.getLocalLow(), 0.001);
    }


    // Test for setters
    @Test
    public void testSetCompanyName() {
        stock.setCompanyName("New Test Company");
        assertEquals("New Test Company", stock.getCompanyName());
    }

    @Test
    public void testSetVolume() {
        stock.setVolume(2000);
        assertEquals(2000, stock.getVolume());
    }

    @Test
    public void testSetLocalHigh() {
        stock.setLocalHigh(60);
        assertEquals(60, stock.getLocalHigh(), 0.001);
    }

    @Test
    public void testSetLocalLow() {
        stock.setLocalLow(20);
        assertEquals(20, stock.getLocalLow(), 0.001);
    }
}
