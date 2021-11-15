package pages.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchedGoogle extends StartGoogle{

    List<WebElement> results;
    WebDriverWait wait = new WebDriverWait(chromeDriver, 120);

    public SearchedGoogle(WebDriver chromeDriver) {
        super(chromeDriver);}

    public List<WebElement> find() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class=\"iUh30 Zu0yb qLRx3b tjvcx\"]")));
        results=chromeDriver.findElements(By.xpath(".//*[@class=\"iUh30 Zu0yb qLRx3b tjvcx\"]"));
        return results;
    }


}
