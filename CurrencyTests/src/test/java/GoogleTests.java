import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import steps.Steps;

public class GoogleTests extends BaseTest {


    @DisplayName("Получение и сравнение курса валют с разных банков")
    @Test
    public void coursesDifference() {
        Steps.OpenCurrencyCourses();
        Steps.VTBCurrencyCourses();
        Steps.SBERCurrencyCourses();
        Steps.AlfaCurrencyCourses();
        Steps.getDifferenceBetweenCourses();
    }


}
