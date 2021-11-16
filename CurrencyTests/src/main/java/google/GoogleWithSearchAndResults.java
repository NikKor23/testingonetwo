package google;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.Page;

import java.util.ArrayList;
import java.util.List;

public class GoogleWithSearchAndResults implements Page {

    private String urlsResultsXPath = "//cite";

    private WebDriver webDriver;

    public GoogleWithSearchAndResults(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Получение списка URL элементов по запросу")
    public List<String> getURLsResults() {
        List<WebElement> list = webDriver.findElements(By.xpath(urlsResultsXPath));
        List<String> strList = new ArrayList<>();
        list.forEach(e -> {
            String str = e.getText();
            if (!str.contains(" ")) strList.add(str);
            else strList.add(str.substring(0, str.indexOf(" ")));
        });
        return strList;
    }

}
