package tests;

import core.BaseTest;
import db.LicenceDbHelper;
import org.junit.jupiter.api.Test;
import steps.ConsultantSteps;

public class LicenceTests extends BaseTest {

    @Test
    void uploadWrongIDLicenceFileTest() {
        ConsultantSteps steps = new ConsultantSteps(page);

        steps.loginAsAdmin();
        steps.openLicencePage();
        steps.uploadWrongFileLicence();
    }
}
