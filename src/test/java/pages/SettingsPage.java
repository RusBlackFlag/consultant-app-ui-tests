package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SettingsPage {

    private final Page page;

    public SettingsPage(Page page) {
        this.page = page;
    }

    /** Клик по иконке редактирования */
    public void clickEditCashButton() {
        Locator editButton = page.locator("vaadin-button:has(vaadin-icon[src=\"icons/edit.svg\"])");
        editButton.waitFor();
        editButton.click();
    }

    /** Изменить название кассы */
    public void changeCashName(String newName) {
        Locator input = page.locator("input[type='text']");
        input.waitFor();

        input.fill("");
        input.fill(newName);
    }

    /** Нажать Сохранить */
    public void clickSave() {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Сохранить")
        ).click();
    }

    /** Проверить, что имя кассы отображается */
    public void assertCashNameVisible(String expectedName) {
        Locator cashName = page.locator(
                "span:has-text('" + expectedName + "')"
        );

        assertThat(cashName).isVisible();
    }
}
