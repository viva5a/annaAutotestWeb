package ru.futopedia;

import ru.futopedia.PersonalAccountPages.Page_01_Main;
import ru.futopedia.PersonalAccountPages.Page_04_Profile;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Базовый класс для страницы авторизации
 * Ответственный за создание класса -
 * Дата создания 2022-07-05
 */

public class LoginPage extends BaseClassForPages {

    // Создаём конструктор
    public LoginPage(ChromeDriver webDriver) {
        super(webDriver);
    }

    // Заголовок "Авторизация"
    @FindBy(xpath = "//*[contains(@class, 'AuthWrapper-module__authWrapper__left')]/h1")
    private WebElement h1Heading;

    // Поле ввода "имя пользователя"
    @FindBy(xpath = "//*[@name=\"username\"]")
    private WebElement inputUsername;

    // Поле ввода "пароль"
    @FindBy(xpath = "//*[@name=\"password\"]")
    private WebElement inputPassword;

    // Кнопка "Войти" //form//button
    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement buttonEntry;

//  Уведомления об ошибках (!!! исправить локаторы, раскомментировать Геттеры либо использовать Ломбок)
    /*// Уведомление "Имя пользователя обязательно для заполнения"
    @FindBy(xpath = "//*[text()=\"Ошибки\"]//...")
    private WebElement labelEmailError;

    // Уведомление "Пароль обязателен для заполнения"
    @FindBy(xpath = "//*[text()=\"Ошибки\"]//...")
    private WebElement labelPasswordError;*/

    // Метод заполнения поля "E-mail"
    @Step("Выполняем заполнение поля \"username\"") // Добавляем в отчёт информацию с помощью Assertj
    public void fillUsername(String username) {
        inputUsername.sendKeys(username);
    }

    // Метод заполнения поля "Пароль"
    @Step("Выполняем заполнение поля \"Пароль\"") // Добавляем в отчёт информацию с помощью Assertj
    public void fillPassword(String password) {
        inputPassword.sendKeys(password);
    }

    // Метод входа в ЛК
    public Page_01_Main loginToYourAccount(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        buttonEntry.click();
        return new Page_01_Main(getWebDriver()); // Возвращает страницу "Мой профиль"
    }

    // Геттеры на уведомления
//    public WebElement getLabelEmailError() {
//        return labelEmailError;
//    }
//    public WebElement getLabelPasswordError() {
//        return labelPasswordError;
//    }
}
