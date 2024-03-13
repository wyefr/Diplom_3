package test;

import helpers.api.user.UserMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
public class RegistrationTest {
    WebDriver driver;
    private final String name;
    private final String email;
    private final String password;
    private String accessToken;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headed", "--disable-dev-shm-usage");
        driver = new ChromeDriver();
    }

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
            accessToken = UserMethods.loginUser(email, password).then().extract().path("accessToken").toString();
        } else {
            String incorrectPasswordText = registrationPage.getIncorrectPassword();
            Assert.assertTrue("Incorrect password message is not displayed", !incorrectPasswordText.isEmpty());
        }
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            UserMethods.deleteUser(accessToken);
        }
        driver.quit();
    }
}
