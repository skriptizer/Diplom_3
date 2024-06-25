package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    private final WebDriver webDriver;

    private By logInLocator = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step("Click on the Log In button in Forgot Password page")
    public void logInButtonClick() {
        WebElement logInButton = webDriver.findElement(logInLocator);
        logInButton.click();
    }

    @Step("wait for load Forgot password page")
    public void waitForLoadForgotPasswordPage() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(logInLocator));
    }
}
