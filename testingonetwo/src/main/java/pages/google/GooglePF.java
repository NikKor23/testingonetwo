package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GooglePF {

    private WebDriver chromeDriver;

    @FindBy(how= How.XPATH,xpath=".//*[@title=\"Поиск\"]")
    WebElement searchField;

    @FindBy(how = How.XPATH, xpath = ".//*[@value=\"Поиск в Google\"]")
    WebElement searchButton;

    @FindBy(how = How.XPATH, xpath = ".//*[@class=\"iUh30 Zu0yb qLRx3b tjvcx\"]")
    List<WebElement> webElements;


    public GooglePF(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public  void find(String keysFind){
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }

    public List<WebElement> getWebElements() {
        return webElements;
    }

}
