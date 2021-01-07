package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {

    private static GeoService geoService;
    private static Location nullLoc;
    private static Location moscowStreetLoc;
    private static Location newYorkStreetLoc;
    private static Location moscowAnyLoc;
    private static Location newYorkAnyLoc;

    @BeforeAll
    public static void setUp() {
        geoService = new GeoServiceImpl();

        nullLoc = new Location(null, null, null, 0);
        moscowStreetLoc = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        newYorkStreetLoc = new Location("New York", Country.USA, " 10th Avenue", 32);
        moscowAnyLoc = new Location("Moscow", Country.RUSSIA, null, 0);
        newYorkAnyLoc = new Location("New York", Country.USA, null, 0);
    }

    @Test
    public void byIpNull() {
        Assertions.assertNull(geoService.byIp("0.0.0.0"));
    }

    @Test
    public void byIpLocalhost() {
        Location calculatedLocation = geoService.byIp(GeoServiceImpl.LOCALHOST);
        Assertions.assertEquals(nullLoc, calculatedLocation);
    }

    @Test
    public void byIpMoscow() {
        Location calculatedLocation = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        Assertions.assertEquals(moscowStreetLoc, calculatedLocation);
    }

    @Test
    public void byIpMoscowAny() {
        Location calculatedLocation = geoService.byIp("172.123.123.123");
        Assertions.assertEquals(moscowAnyLoc, calculatedLocation);
    }

    @Test
    public void byIpNewYork() {
        Location calculatedLocation = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Assertions.assertEquals(newYorkStreetLoc, calculatedLocation);
    }


    @Test
    public void byIpNewYorkAny() {
        Location calculatedLocation = geoService.byIp("96.123.123.123");
        Assertions.assertEquals(newYorkAnyLoc, calculatedLocation);
    }
}