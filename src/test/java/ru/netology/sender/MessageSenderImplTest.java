package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    private final String RUSSIANS_TEXT_RE = "[а-яА-Я]+.*";
    private final String OTHER_TEXT_RE = "[a-zA-Z]+.*";

    @Test
    void messageRus() {
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        GeoServiceImpl gs = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(gs.byIp(GeoServiceImpl.MOSCOW_IP)).thenReturn(location);

        LocalizationServiceImpl ls = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(ls.locale(location.getCountry())).thenCallRealMethod();

        MessageSender resultMessage = new MessageSenderImpl(gs, ls);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);

        String result = resultMessage.send(resultMap);

        Assertions.assertTrue(result.matches(RUSSIANS_TEXT_RE));
    }

    @Test
    void messageOther() {
        Location location = new Location("New York", Country.USA, null,  0);

        GeoServiceImpl gs = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(gs.byIp(GeoServiceImpl.NEW_YORK_IP)).thenReturn(location);

        LocalizationServiceImpl ls = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(ls.locale(location.getCountry())).thenCallRealMethod();

        MessageSender resultMessage = new MessageSenderImpl(gs, ls);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);

        String result = resultMessage.send(resultMap);

        Assertions.assertTrue(result.matches(OTHER_TEXT_RE));
    }
}
