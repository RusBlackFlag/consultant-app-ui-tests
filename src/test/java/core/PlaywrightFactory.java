package core;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import config.TestConfig;

public class PlaywrightFactory {
    private static Playwright playwright;
    private static Browser browser;

    public static Browser getBrowser() {
        if (browser == null) {
            playwright = Playwright.create();

            BrowserType.LaunchOptions options =
                    new BrowserType.LaunchOptions()
                            .setHeadless(TestConfig.HEADLESS)
                            .setSlowMo(TestConfig.SLOW_MO);


            browser = playwright.chromium().launch(options);
        }
        return browser;
    }
}