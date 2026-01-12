package core;

import com.microsoft.playwright.Page;
import pages.LicencePage;

public class LicenceService {

    private final LicencePage licencePage;

    public LicenceService(Page page) {
        this.licencePage = new LicencePage(page);
    }

//    public boolean isLicenseInstalled() {
//        licensePage.open();
//
//        return licencePage.isShopSelectedInComboBox()
//                && licencePage.isLicenseCounterVisibleWithValue();
//    }
}
