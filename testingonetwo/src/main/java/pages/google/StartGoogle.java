package pages.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartGoogle {

    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchButton;

    public StartGoogle(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public  void find(String keysFind){
        searchField = chromeDriver.findElement(By.xpath(".//*[@title=\"Поиск\"]"));
        searchButton = chromeDriver.findElement(By.xpath(".//*[@value=\"Поиск в Google\"]"));
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }
}
