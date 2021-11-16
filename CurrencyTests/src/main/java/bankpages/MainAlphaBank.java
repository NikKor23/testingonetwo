package bankpages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import page.Page;

public class MainAlphaBank implements Page {

    private String mainAlphaURL = "https://alfabank.ru/";
    private String descriptionTable = "//p[@class=\"a2swtV d2swtV h2swtV D2swtV e23Qzr Q23Qzr\"]";
    private String currencyLink = "//a[contains(., \"Курсы валют\")]/..";
    private String rubButton = "//p[contains(., \"Рубли\")]";
    private String currencyTableXPath = "//table[@class =\"a3TwLG\"]";

    private WebDriver webDriver;

    public MainAlphaBank(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Альфа-Банк")
    public Page toCurrency() {
        this.webDriver.get(mainAlphaURL);
        WebElement element = webDriver.findElement(By.xpath("//a[@href=\"/currency/\"]"));
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("arguments[0].click()", element);
        return this;
    }
}
