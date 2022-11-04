import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderImplTest {


    @Test
    void send_test_Russia () {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, "172.123.12.19");
        Mockito.when(geoService.byIp(String.valueOf(headers.get(IP_ADDRESS_HEADER))))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String actual = messageSender.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void send_test_USA () {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, "96.44.183.149");
        Mockito.when(geoService.byIp(String.valueOf(headers.get(IP_ADDRESS_HEADER))))
                .thenReturn(new Location("New York", Country.USA, null,  0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String actual = messageSender.send(headers);
        String expected = "Welcome";
        Assertions.assertEquals(expected, actual);
    }




}
