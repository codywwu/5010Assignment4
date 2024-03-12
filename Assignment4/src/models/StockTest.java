package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StockTest {

    private Stock stock;

    @Before
    public void setUp() {
        stock = new Stock("Company Name", 1000);
        stock.setVolume(5000);
        stock.setLocalHigh(200);
        stock.setLocalLow(150);
    }

    @Test
    public void testGetCompanyName() {
        assertEquals("Company Name", stock.getCompanyName());
    }

    @Test
    public void testGetUserShared() {
        assertEquals(1000, stock.getUserShared());
    }

    @Test
    public void testGetVolume() {
        assertEquals(5000, stock.getVolume());
    }

    @Test
    public void testGetLocalHigh() {
        assertEquals(200, stock.getLocalHigh(), 0.001);
    }

    @Test
    public void testGetLocalLow() {
        assertEquals(150, stock.getLocalLow(), 0.001);
    }

    // Add more tests for remaining methods and edge cases
}