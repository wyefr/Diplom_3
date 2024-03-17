package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private static final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private static final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private static final By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Login")
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

   @Step("Get login button")
    public static By getLoginButtonLocator() {
        return loginButton;
    }
}
