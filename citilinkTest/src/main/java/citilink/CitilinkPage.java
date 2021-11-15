package citilink;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CitilinkPage {

    private String mainURL = "https://www.citilink.ru/";
    private String catalogButtonXPath = "//span[contains(., \"Каталог товаров\")]";
    private String notebooksAndComputersTabXPath = "//span[contains(., \"Ноутбуки\")]/..";
    private String videoСardsXPath = "//a[contains(., \"Видеокарты\")]";

    private WebDriver webDriver;

    public CitilinkPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(mainURL);
    }

    public void catalogClick() {
        webDriver.findElement(By.xpath(catalogButtonXPath)).click();
    }

    public void hoverNotebooksTab() {
        Actions act = new Actions(webDriver);
        WebElement element = webDriver.findElement(By.xpath(notebooksAndComputersTabXPath));
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        act.moveToElement(element).perform();
    }

    public void videoCardsClick() {
        WebElement element = webDriver.findElement(By.xpath(videoСardsXPath));
        JavascriptExecutor je = (JavascriptExecutor) webDriver;
        je.executeScript("arguments[0].click()",element);
        /*Actions act = new Actions(webDriver);
        act.moveToElement(element).click();*/
    }

}
