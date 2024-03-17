package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccount {
    private final WebDriver driver;
    private static final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private static final By logoButton = By.xpath(".//*[@id=\"root\"]/div/header/nav/div");
    private static final By modalWindow = By.xpath(".//*[@id=\"root\"]/div/div/div");
    private static final By logoutButton = By.xpath(".//button[text() = 'Выход']");

    public PersonalAccount(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click constructor button")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Click logo button")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Get modal window locator")
    public static By getModalWindowLocator() {
        return modalWindow;
    }

    @Step("Click logout button")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
