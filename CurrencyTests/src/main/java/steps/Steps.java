package steps;

import bankpages.CurrencyAlphaBank;
import bankpages.MainAlphaBank;
import drivermanagers.WebDriverManager;
import google.GoogleWithSearchAndResults;
import google.MainGooglePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import bankpages.MainOpenPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import bankpages.MainSberPage;
import bankpages.MainVTBPage;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;



public class Steps {

    private static double usdThreshold = 72.7;
    private static double differenceThreshold = 2;

    private static List<Double> coursesList = new LinkedList<>();

    private static WebDriver webDriver = WebDriverManager.getCurrentDriver();

    @Step("Проверка результатов поиска в Google по запросу: {query}")
    public static void checkGoogleQueryResults(WebDriver chromeDriver, String query, String result){
        MainGooglePage mainGooglePage = new MainGooglePage(chromeDriver);
        mainGooglePage.find(query);
        GoogleWithSearchAndResults google = new GoogleWithSearchAndResults(chromeDriver);
        List<String> list = google.getURLsResults();
        Assertions.assertTrue(
        list.contains(result), "Запрос {" + query + "} не дал результата " + result);
    }

    @Step("Сайт Открытия")
    public static void OpenCurrencyCourses(){
        MainOpenPage open = new MainOpenPage(webDriver);
        List<LinkedHashMap<String, String>> mapList = open.getCurrencyCourses();
        getUnprofitableUSDCourseFromBank(mapList);
        Assertions.assertTrue(getUSDCourse(mapList) < usdThreshold,
                "Банк покупает USD дороже, чем " + usdThreshold);
    }

    @Step("Альфа-Банка")
    public static void AlfaCurrencyCourses(){
        MainAlphaBank alpha = new MainAlphaBank(webDriver);
        alpha.toCurrency();
        CurrencyAlphaBank currencyAlphaBank = new CurrencyAlphaBank(webDriver);
        List<LinkedHashMap<String, String>> mapList = currencyAlphaBank.getCurrencyCourses();
        getUnprofitableUSDCourseFromBank(mapList);
        Assertions.assertTrue(getUSDCourse(mapList) < usdThreshold,
                "Банк покупает USD дороже, чем " + usdThreshold);

    }

    @Step("СБЕР")
    public static void SBERCurrencyCourses(){
        MainSberPage sber = new MainSberPage(webDriver);
        List<LinkedHashMap<String, String>> mapList = sber.getCurrencyCourses();
        getUnprofitableUSDCourseFromBank(mapList);
        Assertions.assertTrue(getUSDCourse(mapList) < usdThreshold,
                "Банк покупает USD дороже, чем " + usdThreshold);
    }
    @Step("VTB")
    public static void VTBCurrencyCourses(){
        MainVTBPage vtb = new MainVTBPage(webDriver);
        vtb.toCurrency();
        List<LinkedHashMap<String, String>> mapList = vtb.getCurrencyCourses();
        getUnprofitableUSDCourseFromBank(mapList);
        Assertions.assertTrue(getUSDCourse(mapList) < usdThreshold,
                "Банк покупает USD дороже, чем " + usdThreshold);
    }

    private static void getUnprofitableUSDCourseFromBank(List<LinkedHashMap<String, String>> list){
        list.forEach(m -> {
            if (m.get("Валюта обмена").equals("EUR")) {
                coursesList.add(Double.parseDouble(m.get("Банк продает")));
            }
        });
    }

    @Step("Вычисляем разницу между курсами продажи банками валюты EUR")
    public static void getDifferenceBetweenCourses() {
        if (coursesList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Collections.sort(coursesList);
        double diff = coursesList.get(coursesList.size()-1) - coursesList.get(0);
        Allure.attachment("Лучший курс: ", coursesList.get(0).toString());
        Allure.attachment("Худший курс: ", coursesList.get(coursesList.size()-1).toString());
        Allure.attachment("Разница: ", String.valueOf(diff));

        Assertions.assertTrue(coursesList.get(coursesList.size()-1) - coursesList.get(0) <= differenceThreshold,
                "Разница между наихудшим и наилучшим курсами больше единицы");
    }

    @Step("Получение курса покупки банком доллара")
    public static double getUSDCourse(List<LinkedHashMap<String, String>> list){
        double value = 0;
        for (int i = 0; i < list.size(); i ++) {
            if (list.get(i).get("Валюта обмена").equals("USD")) {
                value = Double.parseDouble(list.get(i).get("Банк покупает"));
            }
        }
        Allure.addAttachment("Банк покупает USD: ", String.valueOf(value));
        return value;
    }
}
