package bankpages;

import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import page.Page;

import java.util.*;

public class MainSberPage implements Page {

    private String mainURL = "https://www.sberbank.ru/";
    private String currencyRowsXPath = "//tr[@class=\"kitt-row currency-table__iso\"]";

    private LinkedList<LinkedHashMap<String, String>> currencyList;
    private WebDriver webDriver;

    public MainSberPage(WebDriver webDriver) {
        this.webDriver =  webDriver;
    }

    @Step("Sber")
    public List<LinkedHashMap<String, String>> getCurrencyCourses() {
        webDriver.get(mainURL);
        currencyList = new LinkedList<>();

        LinkedHashMap<String, String> usd = new LinkedHashMap<>();
        LinkedHashMap<String, String> eur = new LinkedHashMap<>();

        WebElement element =webDriver.findElement(By.xpath("//div[contains(@class, \"rates-calc-main__wrap\")]"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).build().perform();

        List<WebElement> list = webDriver.findElements(By.xpath("//tr[@class=\"kitt-row currency-table__iso\"]//div"));

        usd.put("Валюта обмена",list.get(0).getText().replace(",", "."));
        usd.put("Банк покупает",list.get(1).getText().replace(",", "."));
        usd.put("Банк продает",list.get(2).getText().replace(",", "."));

        eur.put("Валюта обмена",list.get(3).getText().replace(",", "."));
        eur.put("Банк покупает",list.get(4).getText().replace(",", "."));
        eur.put("Банк продает",list.get(5).getText().replace(",", "."));


        currencyList.add(usd);
        currencyList.add(eur);

        return currencyList;
    }

    public List<LinkedHashMap<String, String>> getCurrencyCoursesTable() {
        return currencyList;
    }


}
