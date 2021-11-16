package bankpages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.Page;

import java.util.*;

public class MainOpenPage implements Page {

    private String mainURL = "https://www.open.ru/";
    private String standardCourses = "//div[text() = \"Льготный курс\"]";
    private String exchangeRateHeadersXPath = "//div[@tabindex=\"0\"]//tr[contains(@class, \"header\")]/td";
    private String exchangeRateBordersXPath = "//div[@tabindex=\"0\"]//tr[contains(@class, \"border\")]";

    private List<LinkedHashMap<String, String>> currencyList;
    private WebDriver webDriver;


    public MainOpenPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriver.get(mainURL);
    }

    public List<LinkedHashMap<String, String>> getCurrencyCourses() {
        currencyList = new LinkedList<>();

        try {
            webDriver.findElement(By.xpath(standardCourses)).click();
        } catch (Exception ex) {
            System.out.println("Искал элемент снова");
        } finally {
            webDriver.findElement(By.xpath(standardCourses)).click();
        }

        List<WebElement> tableHeaders = webDriver.findElements(By.xpath(exchangeRateHeadersXPath));
        System.out.println(tableHeaders.size());
        List<WebElement> tableRows = webDriver.findElements(By.xpath(exchangeRateBordersXPath));

        for(int i= 0; i<tableRows.size();i++){
            LinkedHashMap<String,String> collectRow=new LinkedHashMap<>();
            for (int j=0;j<tableHeaders.size();j++){
                if (tableHeaders.get(j).getText().length() == 0) continue;
                collectRow.put(
                        tableHeaders.get(j).getText(),
                        tableRows.get(i).findElement(By.xpath("./td["+(j+1)+"]")).getText().replace(",", ".")
                );
            }
            currencyList.add(collectRow);
        }

        return currencyList;
    }

    public List<LinkedHashMap<String, String>> getCurrencyCoursesTable() {
        return currencyList;
    }
}
