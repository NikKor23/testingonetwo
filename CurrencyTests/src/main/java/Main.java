import org.openqa.selenium.WebDriver;

import java.util.*;


public class Main {

    private static String mainURL = "https://www.open.ru/";

    private static LinkedList<LinkedHashMap<String, String>> currencyList;
    protected static WebDriver chromeDriver;


    public static void main(String[] args) {

        List<Double> list = new LinkedList<>();
        list.add(2.3);
        list.add(5.2);
        list.add(1.1);
        list.add(1.6);
        list.add(8.9);
        list.add(7.3);

        Collections.sort(list);

        System.out.println(list);
    }
}


