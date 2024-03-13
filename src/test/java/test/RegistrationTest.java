package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RegistrationPage;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import static helpers.Urls.LOGIN_PAGE;
import static helpers.Urls.REGISTRATION_PAGE;

@RunWith(Parameterized.class)
public class RegistrationTest extends Base {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Иннокентий", "test" + new Random().nextInt(100000) + "@yandex.ru", "password"},
                {"Александр", "test" + new Random().nextInt(100000) + "@yandex.ru", "pa1"}
        });
    }

    public RegistrationTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Test
    public void registrationTest() {
        driver.get(REGISTRATION_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.makeRegistration(name, email, password);

        if ("Иннокентий".equals(name) && "password".equals(password)) {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(LoginPage.getLoginButtonLocator()));
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals("Actual Url doesn't match expected", LOGIN_PAGE, currentUrl);
            loginUser();
        } else {
            String incorrectPasswordText = registrationPage.getIncorrectPassword();
            Assert.assertFalse("Incorrect password message is not displayed", incorrectPasswordText.isEmpty());
        }
    }
}
