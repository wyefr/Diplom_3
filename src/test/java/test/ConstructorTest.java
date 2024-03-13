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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;
import java.util.Random;

import static helpers.Urls.BASE_URL;

public class ConstructorTest {
    WebDriver driver;
    String name = "Иннокентий";
    String email = "test" + new Random().nextInt(100000) + "@yandex.ru";
    String password = "password";
    private String accessToken;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headed", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void transferToFillingConstructor() {
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

        //Пробросились на главную страницу, теперь перейдем к разделу Начинки
        MainPage mainPage = new MainPage(driver);
        By modalWindowLocatorBeforeClick = MainPage.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick));

        //Переходим к разделу Начинки
        mainPage.clickFillingConstructorButton();
        By getFillingConstructorLocator = MainPage.getFillingConstructorLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(getFillingConstructorLocator));
        Assert.assertEquals("Buns constructor hasn't been located", "Начинки", driver.findElement(getFillingConstructorLocator).getText());
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void transferToSousesConstructor() {
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

        //Пробросились на главную страницу, теперь перейдем к разделу Соусы
        MainPage mainPage = new MainPage(driver);
        By modalWindowLocatorBeforeClick = MainPage.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick));

        mainPage.clickSousesConstructorButton();
        By getSousesConstructorLocator = MainPage.getSousesConstructorLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(getSousesConstructorLocator));
        Assert.assertEquals("Buns constructor hasn't been located", "Соусы", driver.findElement(getSousesConstructorLocator).getText());
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void transferToBunsConstructor() {
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

        //Пробросились на главную страницу, теперь перейдем к разделу Булки
        MainPage mainPage = new MainPage(driver);
        By modalWindowLocatorBeforeClick = MainPage.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick));

        mainPage.clickFillingConstructorButton();
        mainPage.clickBunsConstructorButton();
        By getBunsConstructorLocator = MainPage.getBunsConstructorLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(getBunsConstructorLocator));
        Assert.assertEquals("Buns constructor hasn't been located", "Булки", driver.findElement(getBunsConstructorLocator).getText());
    }

    @After
    public void tearDown() {
        UserMethods.deleteUser(accessToken);
        driver.quit();
    }
}
