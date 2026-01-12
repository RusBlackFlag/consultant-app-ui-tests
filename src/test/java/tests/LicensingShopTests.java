package tests;

import core.BaseTest;
import db.LicenceDbHelper;
import org.junit.jupiter.api.Test;
import steps.ConsultantSteps;

public class LicensingShopTests extends BaseTest {


    @Test
    void adminCanLicenseShopSuccessfully() {
        LicenceDbHelper.deleteAllLicenses();

        ConsultantSteps steps = new ConsultantSteps(page);
        steps.loginAsAdmin();
        steps.licenseShop();
        steps.assertShopLicensed();
    }
}
