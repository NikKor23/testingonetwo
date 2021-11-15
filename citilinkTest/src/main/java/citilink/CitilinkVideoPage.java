package citilink;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class CitilinkVideoPage {

    private String searchFieldXPath = "//input[@type=\"search\"]";
    private String filterByPriceXPath = "//div[@data-bidirectional=\"1\"]/div[contains(., \"по цене\")]";
    private String itemsXPath = "//span[contains(@class, \"price-current__price\")]/ancestor::div[contains(@class, \"product_data__gtm-js\") and contains(.,\"Видеокарта\")]";
    private String itemsDescriptionXPath = ".//div[contains(@class, \"description\")]//a";
    private String itemsPriceXPath = ".//div[contains(@class, \"wrapper-cart\")]//span[contains(@class, \"price-current__price\")]";
    private String filter = "//div[@data-meta-name=\"FiltersLayout\"]";
    private String loader = "//div[@class=\"BasketPreloader\"]";

    private WebDriver webDriver;

    public CitilinkVideoPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setFieldValue(String query) {
        List<WebElement> elements = webDriver.findElements(By.xpath(searchFieldXPath));
        elements.get(1).clear();
        elements.get(1).sendKeys(query + Keys.ENTER);
    }

    public void setFilterByPrice() {
        WebElement element = webDriver.findElement(By.xpath(filterByPriceXPath));
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor je = (JavascriptExecutor) webDriver;
        je.executeScript("arguments[0].click()",element);
        WebElement preLoader = webDriver.findElement(By.xpath(loader));
        wait.until(ExpectedConditions.invisibilityOf(preLoader));
    }

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
            /*String desc = e.findElement(By.xpath(itemsDescriptionXPath)).getText();
            System.out.println(desc);*/
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(itemsPriceXPath)));
            Integer currentPrice = Integer.parseInt(e.findElement(By.xpath(itemsPriceXPath)).getText().replaceAll("[^0-9]", ""));
            itemsPrices.add(currentPrice);
        });
        return itemsPrices.get(0);
    }
}
