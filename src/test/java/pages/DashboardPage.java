package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {

    private final Page page;

    public DashboardPage(Page page) {
        this.page = page;
    }

    public void waitForDashboardLoaded() {
        page.locator("#gridLayout").waitFor();
    }

    public boolean isDashboardVisible() {
        return page.locator("#gridLayout").isVisible();
    }

    public void waitForNoLicenseDialog() {
        page.getByRole(
                AriaRole.ALERTDIALOG,
                new Page.GetByRoleOptions().setName("Ошибка лицензии!")
        ).waitFor();
    }

    public boolean isNoLicenseMessageVisible() {
        return page.getByRole(
                AriaRole.ALERTDIALOG,
                new Page.GetByRoleOptions().setName("Ошибка лицензии!")
        ).isVisible();
    }
}

