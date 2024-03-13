package test;

import helpers.api.user.UserMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccount;

import java.time.Duration;
import java.util.Random;

import static helpers.Urls.*;

public class LogoutTest {
    String name = "Иннокентий";
    String email = "test" + new Random().nextInt(100000) + "@yandex.ru";
    String password = "password";
    WebDriver driver;
    private String accessToken;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headed", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transferToConstructor() {
        //Создадим пользователя
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();

        //идем на главную страницу и оттуда в личный кабинет, чтобы залогиниться
        driver.get(BASE_URL);
        driver.findElement(MainPage.getLogIntoAccountButtonLocator()).click();

        //Логинимся
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(BASE_URL));

        //Пробролсились на главную страницу, теперь перейдем в личный кабинет
        MainPage mainPage = new MainPage(driver);
        By modalWindowLocatorBeforeClick = MainPage.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick));
        mainPage.clickPersonalAccountButton();

        //Выходим из личного кабинета
        PersonalAccount personalAccount = new PersonalAccount(driver);
        By modalWindowLocatorBeforeClickLogout = PersonalAccount.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClickLogout));
        personalAccount.clickLogoutButton();

        //Проверяем, что мы находимся на странице логина
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(LOGIN_PAGE));
        Assert.assertEquals("Current Url doesn't match to Main Page", LOGIN_PAGE, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        UserMethods.deleteUser(accessToken);
        driver.quit();
    }
}
