import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocalizationServiceImplTest {

    @Test
    public void testLocaleRussia() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.RUSSIA);
        
        assertEquals("Добро пожаловать", message);
    }

    @Test
    public void testLocaleUSA() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.USA);
        
        assertEquals("Welcome", message);
    }

    @Test
    public void testLocaleOtherCountry() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.BRAZIL);
        
        assertEquals("Welcome", message);
    }
}
