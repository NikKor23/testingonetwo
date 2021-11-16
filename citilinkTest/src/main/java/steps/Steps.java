package steps;

import citilink.CitilinkPage;
import citilink.CitilinkVideoPage;
import drivers.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class Steps {

    public static WebDriver webDriver = WebDriverManager.getCurrentDriver();

    @Step("Переход на сайт Ситилинк -> Видеокарты")
    public static void gotoCitilinkVideoCards() {
        CitilinkPage page = new CitilinkPage(webDriver);
        page.catalogClick();
        page.hoverNotebooksTab();
        page.videoCardsClick();
    }

    @Step("Получение минимальной цены видеокарты {name} ")
    public static int getVideoCardPrice(String name) {
        CitilinkVideoPage videoPage = new CitilinkVideoPage(webDriver);
        videoPage.setFieldValue(name);
        videoPage.setFilterByPrice();
        int price = videoPage.getMinPriceOfItems();
        Allure.attachment("Цена: ", String.valueOf(price));
        return price;
    }

    @Step("Проверяем, что цена первой видеокарты, меньше цены второй")
    public static void priceCompare(int first, int second) {
        Assertions.assertTrue(first < second,
                "Не меньше");
    }

}
