package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MessageSenderImplTest {

    @Test
    public void testSendRussianTextForRussianIp() {
        GeoService geoService = mock(GeoServiceImpl.class);
        LocalizationService localizationService = mock(LocalizationServiceImpl.class);
        
        when(geoService.byIp("172.16.58.3"))
            .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        when(localizationService.locale(Country.RUSSIA))
            .thenReturn("Добро пожаловать");
        
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send("172.16.58.3");
        
        assertEquals("Добро пожаловать", result);
        verify(geoService, times(1)).byIp("172.16.58.3");
        verify(localizationService, times(1)).locale(Country.RUSSIA);
    }

    @Test
    public void testSendEnglishTextForAmericanIp() {
        GeoService geoService = mock(GeoServiceImpl.class);
        LocalizationService localizationService = mock(LocalizationServiceImpl.class);
        
        when(geoService.byIp("96.123.45.67"))
            .thenReturn(new Location("New York", Country.USA, "5th Avenue", 10));
        when(localizationService.locale(Country.USA))
            .thenReturn("Welcome");
        
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send("96.123.45.67");
        
        assertEquals("Welcome", result);
        verify(geoService, times(1)).byIp("96.123.45.67");
        verify(localizationService, times(1)).locale(Country.USA);
    }

    @Test
    public void testSendEnglishTextForOtherIp() {
        GeoService geoService = mock(GeoServiceImpl.class);
        LocalizationService localizationService = mock(LocalizationServiceImpl.class);
        
        when(geoService.byIp("192.168.1.1"))
            .thenReturn(new Location("Berlin", Country.GERMANY, "Hauptstrasse", 20));
        when(localizationService.locale(Country.GERMANY))
            .thenReturn("Welcome");
        
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send("192.168.1.1");
        
        assertEquals("Welcome", result);
        verify(geoService, times(1)).byIp("192.168.1.1");
        verify(localizationService, times(1)).locale(Country.GERMANY);
    }

    @Test
    public void testSendEnglishTextForLocalhost() {
        GeoService geoService = mock(GeoServiceImpl.class);
        LocalizationService localizationService = mock(LocalizationServiceImpl.class);
        
        when(geoService.byIp("127.0.0.1"))
            .thenReturn(new Location(null, null, null, 0));
        when(localizationService.locale(null))
            .thenReturn("Welcome");
        
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send("127.0.0.1");
        
        assertEquals("Welcome", result);
        verify(geoService, times(1)).byIp("127.0.0.1");
        verify(localizationService, times(1)).locale(null);
    }
}
