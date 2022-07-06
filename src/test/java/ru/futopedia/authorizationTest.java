package ru.futopedia;

import ru.futopedia.PersonalAccountPages.*;
import ru.futopedia.TestData.TestData;
import ru.futopedia.PageElements.SidebarMenu;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Класс для сайта authorization.ru с использованием паттерна Page Object
 * Ответственный за создание класса -
 * Дата создания 2022-07-05
 *
 * Предусловия: авторизация проверка доступности главной страницы сайта
 * Постусловия: выход из учётной записи, либо очистка cookie
 */

public class authorizationTest {

//     Создаём экземпляр драйвера для возможности доступа к нему по всему классу
    private static ChromeDriver webDriver;

    // Создаём экземпляр опций для браузера
    private static ChromeOptions chromeOptions;

    // Создаём экземпляр ожиданий
    private static WebDriverWait webDriverWait;

    // Создаём экземпляр логера
    private static Logger logger = LoggerFactory.getLogger(ru.futopedia.authorizationTest.class);

    // Создаём экземпляр тестовых данных
    private static TestData testData = new TestData();

    @BeforeAll
    static void registerDriver() {

        // Инициализируем драйвер менеджер
        WebDriverManager.chromedriver().setup();

        // Создаём экземпляр класса ChromeOptions (!!! Утонить необходимость)
        chromeOptions = new ChromeOptions();

        /* Передаём в объект chromeOptions настройки для браузера
        --no-sandbox - для работы в докер-контейнере для Chrome
        start-maximized - запуск окна "на весь экран"
        --disable-notification - отключение всплывающих окон
        user-agent=Googlebot/2.1 (+http://www.google.com/bot.html) - запуск в режиме поискового бота
        --incognito - запуск окна в режиме инкогнито
        .setPageLoadTimeout(Duration.ofSeconds(10)) - максимальное время ожидания загрузки страницы
         */
        chromeOptions
                .addArguments("--no-sandbox")
//                .addArguments("start-maximized")
                .addArguments("--disable-notification")
                .addArguments("user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)")
                .addArguments("--incognito")
                .setPageLoadTimeout(Duration.ofSeconds(30));
    }

    @BeforeEach
    void setUpBrowser() {

        // Создаём экземпляр класса webdriver
        webDriver = new ChromeDriver(chromeOptions);

        // Implicit Wait (Неявное ожидание) - выставляем 5 секунд (!!! обязательно определяем в коде)
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    @DisplayName("01 Проверка доступности главной страницы")
    void homePageLoaded() {

        logger.info("\n[INFO] Проверка доступности главной страницы");

        Assertions
                .assertDoesNotThrow(()-> webDriver
                        .navigate()
                        .to("https://futopedia.ru"), "Страница недоступна");
    }

    @Test
    @DisplayName("02 Позитивная проверка авторизации на сайте")
    void PositiveAuthorizationCheck() {

        logger.info("\n[INFO] Проверка авторизации в личном кабинете");
        logger.debug("\n[DEBUG] Проверяем доступность страницы авторизации");
        Assertions
                .assertDoesNotThrow(()-> webDriver
                                .navigate()
                                .to("https://futopedia.ru/login?redirectPath=/FIFA22"),
                        "Страница авторизации недоступна");



        logger.debug("\n[DEBUG] Создаём экземпляр страницы авторизации");
        LoginPage loginPage = new LoginPage(webDriver);

        logger.info("\n[INFO] Переходим в личный кабинет");
        logger.debug("\n[DEBUG] Заполняем поля \"Имя пользователя\" и \"Пароль\"" +
                "\n[DEBUG] Тестовые учётные данные: логин ******, пароль *******");
        Page_01_Main page01Main = loginPage.loginToYourAccount(testData.getLOGIN(), testData.getPASSWORD());

        logger.debug("\n[DEBUG] Проверяем тайтл главной страницы");
        Assertions.assertEquals(page01Main.getTitle(), "Futopedia - Главная",
                "Тайтл страницы не соответствует значению по умолчанию");
    }

    @Test
    @DisplayName("03 Негативная проверка авторизации на сайте (пустые поля)")
    void NegativeAuthorizationCheck() {

        logger.info("\n[INFO] Проверка возможности авторизации в личном кабинете с пустыми полями e-mail & password");
        Assertions
                .assertDoesNotThrow(()-> webDriver
                                .navigate()
                                .to("https://www.kommersant.ru/lk/login"),
                        "Страница авторизации недоступна");

        logger.debug("\n[DEBUG] Создаём экземпляр страницы авторизации");
        LoginPage loginPage = new LoginPage(webDriver);

        logger.info("\n[INFO] Переходим в личный кабинет" +
                "\n[INFO] Поля e-mail & password оставляем не заполненными");
        loginPage.loginToYourAccount("","");
//        Assertions.assertEquals(true, loginPage.getLabelEmailError().isDisplayed());
//        Assertions.assertEquals(true, loginPage.getLabelPasswordError().isDisplayed());
    }

    // Метод запускающийся после каждого теста
    @AfterEach
    void exitTheBrowser() {

        // Закрываем браузер
        webDriver.quit();
    }
}
