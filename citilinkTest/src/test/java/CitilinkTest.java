import drivers.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import steps.Steps;


public class CitilinkTest extends BaseTest {

    @DisplayName("Сравнение цен на видеокарты")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"gtx 1660ti, rtx 3060"})
    public void firstTest(String first, String second) {
        Steps.gotoCitilinkVideoCards();
        int firstPrice = Steps.getVideoCardPrice(first);
        int secondPrice = Steps.getVideoCardPrice(second);
        Steps.priceCompare(firstPrice, secondPrice);

    }
}
