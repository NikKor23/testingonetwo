package citilink;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class CitilinkVideoPage {
    private String itemsXPath = "//span[contains(@class, \"price-current__price\")]/ancestor::div[contains(@class, \"product_data__gtm-js\") and contains(.,\"Видеокарта\")]";
    private String itemsPriceXPath = ".//div[contains(@class, \"wrapper-cart\")]//span[contains(@class, \"price-current__price\")]";
    private String loader = "//div[@class=\"BasketPreloader\"]";

    @FindBy(how = How.XPATH, using = "//div[@data-bidirectional=\"1\"]/div[contains(., \"по цене\")]")
    WebElement priceFilter;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,\"MainHeader__inner_bottom\")]//input[@type=\"search\"]")
    WebElement searchField;

    private WebDriver webDriver;

    public CitilinkVideoPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Ищем видеокарту: {query}")
    public void setFieldValue(String query) {
        PageFactory.initElements(webDriver, this);
        searchField.clear();
        searchField.sendKeys(query + Keys.ENTER);
    }

    @Step("Утанавливаем порядок по возрастанию цены")
    public void setFilterByPrice() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(priceFilter));
        JavascriptExecutor je = (JavascriptExecutor) webDriver;
        je.executeScript("arguments[0].click()",priceFilter);
        WebElement preLoader = webDriver.findElement(By.xpath(loader));
        wait.until(ExpectedConditions.invisibilityOf(preLoader));
    }

    @Step("Получаем самый дешёвый элемент из списка")
    public int getMinPriceOfItems() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(itemsXPath)));
        List<WebElement> elements = new LinkedList<>();
        try {
            elements = webDriver.findElements(By.xpath(itemsXPath));
        } catch (Exception ex) {
            System.out.println("Нет результатов поиска...");
        }
        List<Integer> itemsPrices = new LinkedList<>();
        if (elements.isEmpty()) return 0;
        elements.forEach(e -> {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(itemsPriceXPath)));
            Integer currentPrice = Integer.parseInt(e.findElement(By.xpath(itemsPriceXPath)).getText().replaceAll("[^0-9]", ""));
            itemsPrices.add(currentPrice);
        });
        return itemsPrices.get(0);
    }
}
