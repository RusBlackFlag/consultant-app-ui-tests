package tests;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import steps.ConsultantSteps;

public class CashTests extends BaseTest {

    @Test
    void editNameOfCash() {
        ConsultantSteps steps = new ConsultantSteps(page);
        steps.loginAsAdmin();
        steps.openSettingsPage();
        steps.editCashName("Test1");
        steps.assertCashNameChanged("Test1");
    }
}
