package ru.futopedia.TestData;

/**
 * Базовый класс для тестовых данных
 * Ответственный за создание класса -
 * Дата создания 2022-07-05
 */

public class TestData {

    // Тестовые данные для сайта futopedia.ru
    private final String LOGIN = "selena";
    private final String PASSWORD = "Test111";
    private final String COMPANY = "АО \"МЭС\"";
    private final String POSITION = "Начальник ПТО";

    // Геттеры на поля класса

    public String getLOGIN() {
        return LOGIN;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }
    public String getCOMPANY() {
        return COMPANY;
    }
    public String getPOSITION() {
        return POSITION;
    }
}
