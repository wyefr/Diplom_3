package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Base {
    WebDriver driver;
    private static final String YANDEX_DRIVER = "src/main/resources/yandexdriver";

    @Step("Инициализация драйвера")
    public void initDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headed", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER);
                driver = new ChromeDriver();
                break;
        }
    }

    @Before
    public void setUp() {
        initDriver("chrome");
    }
}