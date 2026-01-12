package tests;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import steps.ConsultantSteps;
import db.LicenceDbHelper;

public class LoginTests extends BaseTest {

    @Test
    void adminCanLoginSuccessWithLicence() {
        LicenceDbHelper.deleteAllLicenses();
        LicenceDbHelper.insertLicense();

        new ConsultantSteps(page).loginAsAdmin();
        new ConsultantSteps(page).assertDashboardVisible();
    }

    @Test
    void adminCanLoginSuccessWithoutLicence() {
        LicenceDbHelper.deleteAllLicenses();

        new ConsultantSteps(page).loginAsAdmin();
        new ConsultantSteps(page).assertNoLicenseErrorVisible();
    }
}
