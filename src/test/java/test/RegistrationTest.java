package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RegistrationPage;
import java.time.Duration;
import static helpers.Urls.LOGIN_PAGE;
import static helpers.Urls.REGISTRATION_PAGE;

public class RegistrationTest extends Base {
    @Test
    public void registrationTest() {
        driver.get(REGISTRATION_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.makeRegistration(name, email, password);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(LoginPage.getLoginButtonLocator()));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Actual Url doesn't match expected", LOGIN_PAGE, currentUrl);
        loginUser();
    }

    @Test
    public void registrationTestWithInvalidPassword() {
        driver.get(REGISTRATION_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.makeRegistration(name, email, invalidPassword);

        String incorrectPasswordText = registrationPage.getIncorrectPassword();
        Assert.assertFalse("Incorrect password message is not displayed", incorrectPasswordText.isEmpty());
    }
}
