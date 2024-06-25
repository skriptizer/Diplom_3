package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
    private final WebDriver webDriver;

    private By emailLocator = By.xpath(".//fieldset[1]/div/div/input");
    private By passwordLocator = By.xpath(".//fieldset[2]/div/div/input");
    private By signUpLocator = By.xpath(".//a[text()='Зарегистрироваться']");
    private By logInLocator = By.xpath(".//button[text()='Войти']");

    public LogInPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step("Fill user data")
    public void fillUserData(String email, String password) {
        WebElement inputEmail = webDriver.findElement(emailLocator);
        WebElement inputPassword = webDriver.findElement(passwordLocator);

        inputEmail.click();
        inputEmail.sendKeys(email);
        inputPassword.click();
        inputPassword.sendKeys(password);
    }

    @Step("Click on the Sign Up button in LogIn page")
    public void singUpButtonClick() {
        WebElement signUpButton = webDriver.findElement(signUpLocator);
        signUpButton.click();
    }

    @Step("Click on the Log In button in LogIn page")
    public void logInButtonClick() {
        WebElement logInButton = webDriver.findElement(logInLocator);
        logInButton.click();
    }

    @Step("is Log In button Displayed check")
    public boolean isLogInButtonDisplayed() {
        return webDriver.findElement(logInLocator).isDisplayed();
    }

    @Step("Wait for load Log in page")
    public void waitForLoadLogInPage() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.elementToBeClickable(logInLocator));
    }
}
