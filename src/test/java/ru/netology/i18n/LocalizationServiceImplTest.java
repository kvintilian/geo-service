package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

    private static LocalizationService service;
    private final String RUSSIANS_TEXT_RE = "[а-яА-Я]+.*";
    private final String OTHER_TEXT_RE = "[a-zA-Z]+.*";

    @BeforeAll
    public static void setUp() {
        service = new LocalizationServiceImpl();
    }

    @Test
    public void localeRus() {
        Assertions.assertTrue(service.locale(Country.RUSSIA).matches(RUSSIANS_TEXT_RE));
    }

    @Test
    public void localeBrazil() {
        Assertions.assertTrue(service.locale(Country.BRAZIL).matches(OTHER_TEXT_RE));
    }

    @Test
    public void localeGermany() {
        Assertions.assertTrue(service.locale(Country.GERMANY).matches(OTHER_TEXT_RE));
    }
    @Test
    public void localeOtherUSA() {
        Assertions.assertTrue(service.locale(Country.USA).matches(OTHER_TEXT_RE));
    }

}
