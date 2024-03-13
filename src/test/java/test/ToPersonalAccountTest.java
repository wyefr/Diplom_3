package test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;
import static helpers.Urls.*;

import static helpers.Urls.BASE_URL;

public class ToPersonalAccountTest extends Base {
    @Test
    @DisplayName("Переход в личный кабинет")
    public void transferToPersonalAccount() {
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

        //Проверим, что мы находимся в личном кабинете
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(PERSONAL_ACCOUNT));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Current Url doesn't match to Personal Account Page", PERSONAL_ACCOUNT, currentUrl);
    }
}
