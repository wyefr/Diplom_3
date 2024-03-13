package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class RegistrationPage {
    private final WebDriver driver;
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private static final By authButton = By.className("Auth_link__1fOlj");
    private final By incorrectPassword = By.xpath(".//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p");
    private static final By modalWindow = By.xpath(".//*[@id=\"root\"]/div/div/div");
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Registration")
    public void makeRegistration(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registrationButton).click();
    }

    @Step("Go to auth page")
    public void goToAuthPage() {
        driver.findElement(authButton).click();
    }

    @Step("Get incorrect password")
    public String getIncorrectPassword() {
        return driver.findElement(incorrectPassword).getText();
    }

    @Step("Get auth button locator")
    public static By getAuthButtonLocator() {
        return authButton;
    }

    @Step("Get modal window locator")
    public static By getModalWindowLocator() {
        return modalWindow;
    }
}
