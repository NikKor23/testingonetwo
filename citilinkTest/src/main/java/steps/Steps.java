package steps;

import citilink.CitilinkPage;
import citilink.CitilinkVideoPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class Steps {

    public static WebDriver webDriver;

    @Step("Переход на сайт Ситилинк -> Видеокарты")
    public static void gotoCitilinkVideoCards(WebDriver chromeDriver) {
        webDriver = chromeDriver;
        CitilinkPage page = new CitilinkPage(chromeDriver);
        page.catalogClick();
        page.hoverNotebooksTab();
        page.videoCardsClick();
    }

    @Step("Получение минимальной цены видеокарты {name} ")
    public static int getVideoCardPrice(WebDriver webDriver, String name) {
        CitilinkVideoPage videoPage = new CitilinkVideoPage(webDriver);
        videoPage.setFieldValue(name);
        videoPage.setFilterByPrice();
        int price = videoPage.getMinPriceOfItems();
        Allure.attachment("Цена: ", String.valueOf(price));
        return price;
    }

}
