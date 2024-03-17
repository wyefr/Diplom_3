package test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import java.time.Duration;

import static helpers.Urls.BASE_URL;

public class ConstructorTest extends Base {
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void transferToFillingConstructor() {
        //Пробросились на главную страницу, теперь перейдем к разделу Начинки
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        By modalWindowLocatorBeforeClick = MainPage.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick));

        //Переходим к разделу Начинки
        mainPage.clickFillingConstructorButton();

        // Ожидаем, пока локатор раздела "Соусы" сменится, прежде чем проверять раздел "Начинки"
        By sousesConstructorLocator = MainPage.getSousesConstructorLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(sousesConstructorLocator));

        //Проверяем, что после клика на кнопку "Начинки", выбранный раздел приобрел новый локатор
        By selectedSectionLocator = MainPage.getSelectedSectionLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(selectedSectionLocator));

        Assert.assertEquals("Filling section hasn't been selected", "Начинки", driver.findElement(selectedSectionLocator).getText());
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void transferToSousesConstructor() {
        //Пробросились на главную страницу, теперь перейдем к разделу Соусы
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        By modalWindowLocatorBeforeClick = MainPage.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick));

        //Переходим к разделу Соусы
        mainPage.clickSousesConstructorButton();

        // Ожидаем, пока локатор раздела "Булки" сменится, прежде чем проверять раздел "Соусы"
        By bunsConstructorLocator = MainPage.getBunsConstructorLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsConstructorLocator));

        //Проверяем, что после клика на кнопку "Соусы", выбранный раздел приобрел новый локатор
        By selectedSectionLocator = MainPage.getSelectedSectionLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(selectedSectionLocator));

        Assert.assertEquals("Souses constructor hasn't been located", "Соусы", driver.findElement(selectedSectionLocator).getText());
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void transferToBunsConstructor() {
        //Пробросились на главную страницу, теперь перейдем к разделу Булки
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        By modalWindowLocatorBeforeClick = MainPage.getModalWindowLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalWindowLocatorBeforeClick));

       //Сначала перейдем к разделу Соусы, чтобы потом перейти в раздел Булки, чтобы проверить работу переходов между разделами
        mainPage.clickSousesConstructorButton();

        //Переходим к разделу Булки
        mainPage.clickBunsConstructorButton();

        // Ожидаем, пока локатор раздела "Соусы" сменится, прежде чем проверять раздел "Булки"
        By getSousesConstructorLocator = MainPage.getSousesConstructorLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(getSousesConstructorLocator));

        //Проверяем, что после клика на кнопку "Булки", выбранный раздел приобрел новый локатор
        By selectedSectionLocator = MainPage.getSelectedSectionLocator();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(selectedSectionLocator));

        Assert.assertEquals("Buns constructor hasn't been located", "Булки", driver.findElement(selectedSectionLocator).getText());
    }
}
