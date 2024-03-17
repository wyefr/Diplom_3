package test;

import helpers.api.user.UserMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;


public class Base {
    WebDriver driver;
    String name = "Иннокентий";
    String email = "test" + new Random().nextInt(100000) + "@yandex.ru";
    String password = "password";
    String invalidPassword = "pa1";
    private String accessToken;
    private static final String YANDEX_DRIVER = "src/main/resources/yandexdriver";

    @Step("Инициализация драйвера")
    public void initDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER);
                driver = new ChromeDriver();
                break;
        }
    }

    @Step("Создание тестового пользователя")
    public void createUser() {
        accessToken = UserMethods.createUser(email, password, name).then().extract().path("accessToken").toString();
    }

    @Step("Логин в созданного юзера")
    public void loginUser() {
        accessToken = UserMethods.loginUser(email, password).then().extract().path("accessToken").toString();
    }

    @Before
    public void setUp() {
        initDriver("chrome");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserMethods.deleteUser(accessToken);
        }
        driver.quit();
    }
}