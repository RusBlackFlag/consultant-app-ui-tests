package core;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected Page page;

    @BeforeEach
    void setUp() {
        BrowserContext context =
                PlaywrightFactory.getBrowser().newContext();
        page = context.newPage();
    }

    @AfterEach
    void tearDown() {
        page.context().close();
    }
}

