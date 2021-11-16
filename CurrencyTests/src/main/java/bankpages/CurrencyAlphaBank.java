package bankpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class CurrencyAlphaBank {

    private String mainURL = "https://alfabank.ru/currency/";
    private String rubButton = "//p[contains(., \"Рубли\")]";
    private String usdButton = "//p[contains(., \"USD\") and @class = \"a3NuQD b3NuQD H3NuQD k3NuQD g1rN1L\"]/..";
    private String eurButton = "//p[contains(., \"EUR\") and @class = \"a3NuQD b3NuQD H3NuQD k3NuQD g1rN1L\"]/..";

    private String svg = "//svg[@class=\"n_lNfk\"]";

    private String currencyTableXPath = "//table[@class =\"a3TwLG\"]";

    private WebDriver webDriver;

    private LinkedList<LinkedHashMap<String, String>> currencyList;

    public CurrencyAlphaBank(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
     public List<LinkedHashMap<String, String>> getCurrencyCourses(){
        webDriver.get(mainURL);
        currencyList = new LinkedList<>();

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rubButton)));

        LinkedHashMap<String, String> usd = new LinkedHashMap<>();
        LinkedHashMap<String, String> eur = new LinkedHashMap<>();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(svg)));

        usd.put("Валюта обмена", "USD");
        String usdS = webDriver.findElement(By.xpath("//td[contains(text(), \"$\")]")).getText().replaceAll(" .*", "").replace(",", ".");
        usd.put("Банк продает", usdS);
        eur.put("Валюта обмена", "EUR");
        String eurS =  webDriver.findElement(By.xpath("//td[contains(., \"€\")]")).getText().replaceAll(" .*", "").replace(",", ".");
        eur.put("Банк продает", eurS);

        JavascriptExecutor jse = (JavascriptExecutor)webDriver;

        WebElement usdButtonElement = webDriver.findElement(By.xpath(usdButton));
        jse.executeScript("arguments[0].click()", usdButtonElement);
        usd.put("Банк покупает", webDriver.findElement(By.xpath("//td[contains(., \"RUB\")]/../td[2]")).getText().replaceAll(" .*", "").replace(",", "."));
        WebElement eurButtonElement = webDriver.findElement(By.xpath(eurButton));
         jse.executeScript("arguments[0].click()", eurButtonElement);
        eur.put("Банк покупает", webDriver.findElement(By.xpath("//td[contains(., \"RUB\")]/../td[2]")).getText().replaceAll(" .*", "").replace(",", "."));

        currencyList.add(usd);
        currencyList.add(eur);

        return currencyList;
     }

    public List<LinkedHashMap<String, String>> getCurrencyCoursesTable() {
        return currencyList;
    }
}
