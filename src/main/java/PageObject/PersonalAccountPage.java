package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    WebDriver webDriver;

    private By exitButtonLocator = By.xpath(".//button[text()='Выход']");

    public PersonalAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Click on the Log Out button in Personal account page")
    public void logOutButtonClick() {
        WebElement exitButton = webDriver.findElement(exitButtonLocator);
        exitButton.click();
    }

    @Step("is Log Out Button Displayed check")
    public boolean isLogOutButtonDisplayed() {
        return webDriver.findElement(exitButtonLocator).isDisplayed();
    }

    @Step("wait for load personal account page")
    public void waitForLoadPersonalAccountPage() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(exitButtonLocator));
    }
}
