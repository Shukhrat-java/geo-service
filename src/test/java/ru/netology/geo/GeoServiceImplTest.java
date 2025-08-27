package ru.netology.geo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {

    @Test
    public void testByIpRussianSegment() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.123.45.67");
        
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    public void testByIpAmericanSegment() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.123.45.67");
        
        assertEquals(Country.USA, location.getCountry());
    }

    @Test
    public void testByIpOtherSegment() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("192.168.1.1");
        
        assertEquals(Country.USA, location.getCountry());
    }

    @Test
    public void testByIpLocalhost() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("127.0.0.1");
        
        assertEquals(null, location.getCountry());
    }
}
