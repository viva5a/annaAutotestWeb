package ru.futopedia.PersonalAccountPages;

import ru.futopedia.BaseClassForPages;
import ru.futopedia.PageElements.SidebarMenu;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Базовый класс для страницы "Уведомления"
 * Ответственный за создание класса -
 * Дата создания 2022-07-05
 */

public class Page_01_Main extends BaseClassForPages {

    // Объект меню
    private SidebarMenu sidebarMenu;

    // Тайтл страницы
    private String tile;

    @FindBy(xpath = "//div[contains(@class, 'Header-module__authContainer')]//a[2]")
    private WebElement importButton;

    @FindBy(xpath = "//div[contains(@class, 'Header-module__authContainer')]//a[2]")
    private WebElement settingsButton;

    @FindBy(xpath = "//div[contains(@class, 'Header-module__authContainer')]//button")
    private WebElement exitButton;

    // Конструктор класса (!!! добавить по необходимости в конструктор элементы, например меню)
    public Page_01_Main(ChromeDriver webDriver) {
        super(webDriver);
    }

    // Геттеры
    public WebElement getImportButton() {
        return importButton;
    }
    public WebElement getSettingsButton() {
        return settingsButton;
    }
    public WebElement getExitButton() {
        return exitButton;
    }
    public String getTitle() {
        return getWebDriver().getTitle();
    }
}
