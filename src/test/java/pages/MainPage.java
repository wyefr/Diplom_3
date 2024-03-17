package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    private static final By logIntoAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By personalAccountButton = By.xpath(".//*[@id=\"root\"]/div/header/nav/a");
    private static final By modalWindow = By.xpath(".//*[@id=\"root\"]/div/div/div");
    private static final By fillingConstructorButton = By.xpath(".//span[text() = 'Начинки']");
    private static final By sousesConstructorButton = By.xpath(".//span[text() = 'Соусы']");
    private static final By BunsConstructorButton = By.xpath(".//span[text() = 'Булки']");
    private static final By fillingConstructor = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]");
    private static final By sousesConstructor = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[2]");
    private static final By bunsConstructor = By.xpath(".//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]/span");
    private static final By selectedSection = By.xpath(".//*[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click log into account button")
    public void clickLogIntoAccountButton() {
        driver.findElement(logIntoAccountButton).click();
    }

    @Step("Click personal account button")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Get log into account button")
    public static By getLogIntoAccountButtonLocator() {
        return logIntoAccountButton;
    }

    @Step("Get personal account button")
    public static By getPersonalAccountButtonLocator() {
        return personalAccountButton;
    }

    @Step("Get modal window")
    public static By getModalWindowLocator() {
        return modalWindow;
    }

    @Step("Click fillings constructor button")
    public void clickFillingConstructorButton() {
        driver.findElement(fillingConstructorButton).click();
    }

    @Step("Click souses constructor button")
    public void clickSousesConstructorButton() {
        driver.findElement(sousesConstructorButton).click();
    }

    @Step("Click buns constructor button")
    public void clickBunsConstructorButton() {
        driver.findElement(BunsConstructorButton).click();
    }

    @Step("Get fillings constructor")
    public static By getFillingConstructorLocator() {
        return fillingConstructor;
    }

    @Step("Get souses constructor")
    public static By getSousesConstructorLocator() {
        return sousesConstructor;
    }

    @Step("Get buns constructor")
    public static By getBunsConstructorLocator() {
        return bunsConstructor;
    }

    @Step("Get selected section")
    public static By getSelectedSectionLocator() {
        // Задержка перед получением выбранного раздела
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectedSection;
    }
}
