package test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccount;
import java.time.Duration;
import static helpers.Urls.BASE_URL;

public class ToConstructorTest extends Base {
    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transferToMainPageByConstructorButton() {
        //Создадим пользователя
        createUser();

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
        By modalWindowLocatorBeforeClick1 = PersonalAccount.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick1));
        personalAccount.clickConstructorButton();
        Assert.assertEquals("Current Url doesn't match to Constructor Page", BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную, нажав на лого")
    public void transferToMainPageByLogoButton() {
        //Создадим пользователя
        createUser();

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
        By modalWindowLocatorBeforeClick2 = PersonalAccount.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick2));
        personalAccount.clickLogoButton();
        Assert.assertEquals("Current Url doesn't match to Constructor Page", BASE_URL, driver.getCurrentUrl());
    }
}
