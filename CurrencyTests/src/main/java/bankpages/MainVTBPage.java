package bankpages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.Page;

import java.util.*;

public class MainVTBPage implements Page {

    private String mainVTBURL = "https://www.vtb.ru/";
    private String currencyCourses = "//div[@class=\"heading color-blue heading-level-2\" and text() = \"Курсы валют\"]";
    private String currencyListXPath = ".//div[contains(text(),\"USD\") or contains(text(), \"EUR\")]/../..";

    private WebDriver webDriver;
    private List<LinkedHashMap<String, String>> currencyList;

    public MainVTBPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Переход на страницу курса валют ВТБ")
    public Page toCurrency() {
        this.webDriver.get(mainVTBURL);
        WebElement element = webDriver.findElement(By.xpath(currencyCourses));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click()", element);
        return this;
    }

    @Step("VTB")
    public List<LinkedHashMap<String, String>> getCurrencyCourses() {
        currencyList = new LinkedList<>();

        List<WebElement> list = webDriver.findElements(By.xpath(currencyListXPath));

        for (int i = 0; i < 2; i++) {
            LinkedHashMap<String, String> currentMap = new LinkedHashMap<>();
            currentMap.put("Валюта обмена",
                    list.get(i).findElement(By.xpath(".//div")).getText().substring(0,3));

            List<WebElement> price = list.get(i).findElements(By.xpath(".//span[contains(text(), \",\")]"));
            String sell = price.get(0).getText().replace(",", ".");
            String buy = price.get(1).getText().replace(",", ".");

            currentMap.put("Банк покупает", sell);
            currentMap.put("Банк продает", buy);

            currencyList.add(currentMap);
        }

        return currencyList;
    }

    public List<LinkedHashMap<String, String>> getCurrencyCoursesTable() {
        return currencyList;
    }

}
