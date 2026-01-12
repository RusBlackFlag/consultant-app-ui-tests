package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LicencePage {

    private final Page page;

    public LicencePage(Page page) {
        this.page = page;
    }

    public void open(String url) {
        page.navigate(url);
    }

    /** Выбрать первое значение в combo-box */
    public void selectFirstShopInComboBox() {
        Locator comboInput = page.locator("#input-vaadin-combo-box-18");
        comboInput.waitFor();
        comboInput.click();

        Locator firstItem = page.locator("#vaadin-combo-box-item-0");
        firstItem.waitFor();
        firstItem.click();
    }

    /** Нажать кнопку Сохранить */
    public void clickSave() {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Сохранить")
        ).click();
    }

    /** Подтвердить диалог */
    public void confirmOverwrite() {
        Locator confirmButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Да")
        );

        confirmButton.waitFor();
        confirmButton.click();
    }

    /** Ожидание успешного уведомления */
    public void waitForSuccessNotification() {
        page.locator(
                "vaadin-notification-card:has-text('Успешно сохранено')"
        ).waitFor();
    }

    /** Проверка, что магазин выбран */
    public boolean isShopSelectedInComboBox() {
        Locator comboInput = page.locator("#input-vaadin-combo-box-18");
        comboInput.waitFor();
        return !comboInput.inputValue().trim().isEmpty();
    }
}
