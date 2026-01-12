package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void open(String url) {
        page.navigate(url);
    }

    public void enterUsername(String username) {
        page.locator("vaadin-text-field[name='username'] input")
                .fill(username);
    }

    public void enterPassword(String password) {
        page.locator("vaadin-password-field[name='password'] input")
                .fill(password);
    }

    public void submit() {
        page.locator("vaadin-button[theme~='submit']")
                .click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submit();
    }
}

