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

import static helpers.Urls.*;

public class LogoutTest extends Base {

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transferToConstructor() {
        //Создадим пользователя
        createUser();

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
}
