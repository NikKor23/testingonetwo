package google;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.Page;

public class MainGooglePage implements Page {

    private String googleURL = "https://www.google.ru/";
    private String searchFieldXPath = "//input[@name=\"q\"]";
    private String searchButtonXPath = "";

    private WebDriver webDriver;

    public MainGooglePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(googleURL);
    }

    @Step("Поиск по запросу {query}")
    public Page find(String query) {
        WebElement searchField = webDriver.findElement(By.xpath(searchFieldXPath));
        searchField.sendKeys(query + Keys.ENTER);
        return this;
    }
}
