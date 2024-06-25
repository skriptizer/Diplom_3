package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private final WebDriver webDriver;

    private By personalAccountLocator = By.xpath(".//p[text()='Личный Кабинет']");
    private By logoLocator = By.className("AppHeader_header__logo__2D0X2");
    private By constructorLocator = By.xpath(".//p[text()='Конструктор']");

    public Header(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step("Click on the Personal account button in Header")
    public void personalAccountButtonClick() {
        WebElement personalAccount = webDriver.findElement(personalAccountLocator);
        personalAccount.click();
    }

    @Step("Click on the Logo button in Header")
    public void logoButtonClick() {
        WebElement logoButton = webDriver.findElement(logoLocator);
        logoButton.click();
    }

    @Step("Click on the Constructor button in Header")
    public void constructorButtonClick() {
        WebElement constructorButton = webDriver.findElement(constructorLocator);
        constructorButton.click();
    }

    @Step("Wait for load header")
    public void waitForLoadHeader() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(logoLocator));
    }
}
