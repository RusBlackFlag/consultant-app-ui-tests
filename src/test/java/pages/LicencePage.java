package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class LicencePage {

    private final Page page;
    private final Locator licenceErrorNotification;

    public LicencePage(Page page) {
        this.page = page;
        this.licenceErrorNotification =
                page.locator("vaadin-notification-card[theme~='error']");
    }

    public void open(String url) {
        page.navigate(url);
    }

    /** Выбрать первое значение в combo-box */
    public void selectFirstShopInComboBox() {
        Locator comboInput = page.locator("#input-vaadin-combo-box-18");
        comboInput.click();

        Locator firstItem = page.locator("#vaadin-combo-box-item-0");
        firstItem.click();
    }

    /** Нажать кнопку Сохранить */
    public void clickSave() {
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Сохранить")
        ).click();
    }

    public void uploadLicenceFile(String fileName) {
        Locator fileInput = page.locator("input[type='file']");

        fileInput.setInputFiles(
                Paths.get("src/test/resources/files/" + fileName)
        );
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

    public void waitForLicenceErrorNotification() {
        licenceErrorNotification.waitFor();
    }

    public boolean isLicenceErrorMessageCorrect(String expectedText) {
        String actualText = licenceErrorNotification.textContent();
        return actualText != null && actualText.contains(expectedText);
    }

    /** Проверка, что магазин выбран */
    public boolean isShopSelectedInComboBox() {
        Locator comboInput = page.locator("#input-vaadin-combo-box-18");
        comboInput.waitFor();
        return !comboInput.inputValue().trim().isEmpty();
    }
}
