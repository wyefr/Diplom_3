package test;

import helpers.api.user.UserMethods;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccount;
import java.time.Duration;
import java.util.Random;
import static helpers.Urls.BASE_URL;

public class ToConstructorTest {
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
    public void transferToMainPageByConstructorButton() {
        //Создадим пользователя
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();

        //идем на главную страницу и оттуда в личный кабинет, чтобы залогиниться
        driver.get(BASE_URL);
        driver.findElement(MainPage.getLogIntoAccountButtonLocator()).click();

        //Логинимся
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        //Пробролсились на главную страницу, теперь перейдем в личный кабинет
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        //Перейдем в конструктор
        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.clickConstructorButton();
        Assert.assertEquals("Current Url doesn't match to Constructor Page", BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную, нажав на лого")
    public void transferToMainPageByLogoButton() {
        //Создадим пользователя
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();

        //идем на главную страницу и оттуда в личный кабинет, чтобы залогиниться
        driver.get(BASE_URL);
        driver.findElement(MainPage.getLogIntoAccountButtonLocator()).click();

        //Логинимся
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        //Пробролсились на главную страницу, теперь перейдем в личный кабинет
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        //Перейдем в конструктор
        PersonalAccount personalAccount = new PersonalAccount(driver);
        By modalWindowLocatorBeforeClick = PersonalAccount.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick));
        personalAccount.clickLogoButton();
        Assert.assertEquals("Current Url doesn't match to Constructor Page", BASE_URL, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        UserMethods.deleteUser(accessToken);
        driver.quit();
    }
}
