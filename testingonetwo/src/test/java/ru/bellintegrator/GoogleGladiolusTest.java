package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.support.PageFactory;
import pages.google.GooglePF;
import pages.google.SearchedGoogle;
import pages.google.StartGoogle;

public class GoogleGladiolusTest extends BaseTests{

    @Feature("Проверка результатов поиска в Google")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"Гладиолус, ru.wikipedia.org"})
    public void testPO(String keyWords, String result){
        chromeDriver.get("https://www.google.com/");
        StartGoogle startGoogle = new StartGoogle(chromeDriver);
        startGoogle.find(keyWords);
        SearchedGoogle searchedGoogle = new SearchedGoogle(chromeDriver);

        Assertions.assertTrue(searchedGoogle.find().stream().anyMatch(x->x.getText().contains(result)),
                "Запрос по слову  "+ keyWords + " не дал искомого результата: " + result);
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PF")
    @Test
    public void testPF(){
        chromeDriver.get("https://www.google.com/");
        GooglePF googlePF = PageFactory.initElements(chromeDriver, GooglePF.class);
        googlePF.find("Гладиолус");
        Assertions.assertTrue(googlePF.getWebElements().stream().anyMatch(x->x.getText().contains("ru.wikipedia.org")),
                "Запрос по слову Гладиолус не дал искомого результата: ru.wikipedia.org ");

    }

}
