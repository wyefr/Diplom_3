package test;

import helpers.api.user.UserMethods;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegistrationPage;
import java.util.Random;
import static helpers.Urls.*;

public class Login extends Base {
    String name = "Иннокентий";
    String email = "test" + new Random().nextInt(100000) + "@yandex.ru";
    String password = "password";
    private String accessToken;

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginByLoginIntoAccountButton() {
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();

        //Login by login into account button
        driver.get(BASE_URL);
        driver.findElement(MainPage.getLogIntoAccountButtonLocator()).click();

        //Redirect to login page and login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        MatcherAssert.assertThat("Can't login, probably wrong email or password, or there is no such user", driver.getCurrentUrl().contains(BASE_URL));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginByPersonalAccountButton() {
        //Creating user
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();

        //Login by personal account button
        driver.get(BASE_URL);
        driver.findElement(MainPage.getPersonalAccountButtonLocator()).click();

        //Redirect to login page and login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        MatcherAssert.assertThat("Can't login, probably wrong email or password, or there is no such user", driver.getCurrentUrl().contains(BASE_URL));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginByAuthButtonInRegistrationForm() {
        //Creating user
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();

        //Login by auth button in registration form
        driver.get(REGISTRATION_PAGE);
        driver.findElement(RegistrationPage.getAuthButtonLocator()).click();

        //Redirect to login page and login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        MatcherAssert.assertThat("Can't login, probably wrong email or password, or there is no such user", driver.getCurrentUrl().contains(BASE_URL));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginByAuthButtonInForgotPasswordForm() {
        //Creating user
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();

        //Login by auth button in forgot password form
        driver.get(FORGOT_PASSWORD);
        driver.findElement(PasswordRecoveryPage.getAuthButtonLocator()).click();

        //Redirect to login page and login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        MatcherAssert.assertThat("Can't login, probably wrong email or password, or there is no such user", driver.getCurrentUrl().contains(BASE_URL));
    }

    @After
    public void cleanUp() {
        driver.quit();
        UserMethods.deleteUser(accessToken);
    }
}
