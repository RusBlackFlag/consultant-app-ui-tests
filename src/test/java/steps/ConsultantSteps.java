package steps;

import com.microsoft.playwright.Page;
import config.TestConfig;
import pages.DashboardPage;
import pages.LicencePage;
import pages.LoginPage;
import pages.SettingsPage;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsultantSteps {

    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;
    private final LicencePage licencePage;
    private final SettingsPage settingsPage;


    public ConsultantSteps(Page page) {
        this.loginPage = new LoginPage(page);
        this.dashboardPage = new DashboardPage(page);
        this.licencePage = new LicencePage(page);
        this.settingsPage = new SettingsPage(page);
    }

    public void loginAsAdmin() {
        loginPage.open(TestConfig.BASE_URL + "/login");
        loginPage.login(
                TestConfig.ADMIN_LOGIN,
                TestConfig.ADMIN_PASSWORD
        );

    };

    public void openLicencePage() {
        licencePage.open(TestConfig.BASE_URL + "/licence");
    };

    public void openSettingsPage() {
        licencePage.open(TestConfig.BASE_URL + "/settings");
    };

    public void licenseShop() {
        openLicencePage();

        licencePage.selectFirstShopInComboBox();
        licencePage.clickSave();
        licencePage.confirmOverwrite();
        licencePage.waitForSuccessNotification();
    }

    public void assertDashboardVisible() {
        dashboardPage.waitForDashboardLoaded();

        assertTrue(dashboardPage.isDashboardVisible(),
                "Ожидалось отображение касс при установленной лицензии"
        );
    }

    public void assertShopLicensed() {
        assertTrue(
                licencePage.isShopSelectedInComboBox(),
                "Ожидалось, что магазин будет лицензирован"
        );
    }

    public void assertNoLicenseErrorVisible() {
        dashboardPage.waitForNoLicenseDialog();

        assertTrue(
                dashboardPage.isNoLicenseMessageVisible(),
                "Ожидалось сообщение об отсутствии лицензии"
        );
    }

    public void editCashName(String newName) {
        settingsPage.clickEditCashButton();
        settingsPage.changeCashName(newName);
        settingsPage.clickSave();
    }

    public void assertCashNameChanged(String expectedName) {
        settingsPage.assertCashNameVisible(expectedName);
    }
}

