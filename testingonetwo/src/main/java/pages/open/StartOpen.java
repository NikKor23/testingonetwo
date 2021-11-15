package pages.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartOpen {

    protected WebDriver chromeDriver;

    protected WebElement usdCell;

    protected WebElement usdBuy;

    protected WebElement eurCell;

    protected WebElement eurBuy;


    public StartOpen(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public WebElement getUsdSale() {
        usdCell = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[9]/section/div/div/div[1]/div/div/div/div/div[2]/table/tbody/tr[2]/td[4]/div/span"));
        return usdCell;
    }

    public WebElement getUsdBuy() {
        usdBuy = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[9]/section/div/div/div[1]/div/div/div/div/div[2]/table/tbody/tr[2]/td[2]/div/span"));
        return usdBuy;
    }

    public WebElement getEurSale() {
        usdCell = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[9]/section/div/div/div[1]/div/div/div/div/div[2]/table/tbody/tr[3]/td[4]/div/span"));
        return usdCell;
    }

    public WebElement getEurBuy() {
        usdBuy = chromeDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[9]/section/div/div/div[1]/div/div/div/div/div[2]/table/tbody/tr[3]/td[2]/div/span"));
        return usdBuy;
    }






}
