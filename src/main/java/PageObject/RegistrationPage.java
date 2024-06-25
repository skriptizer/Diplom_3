package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver webDriver;

    private By nameLocator = By.xpath(".//fieldset[1]/div/div/input");
    private By emailLocator = By.xpath(".//fieldset[2]/div/div/input");
    private By passwordLocator = By.xpath(".//fieldset[3]/div/div/input");
    private By signUpLocator = By.xpath(".//button[text()='Зарегистрироваться']");
    private By logInLocator = By.xpath(".//a[text()='Войти']");
    private By invalidPassword = By.xpath(".//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step("Click on the Sign Up button in Registration page")
    public void singUpButtonClick() {
        WebElement signUpButton = webDriver.findElement(signUpLocator);
        signUpButton.click();
    }

    @Step("is Invalid Password Text Displayed check")
    public boolean isInvalidPasswordTextDisplayed() {
        return webDriver.findElement(invalidPassword).isDisplayed();
    }

    @Step("Click on the Log In button in Registration page")
    public void logInButtonClick() {
        WebElement logInButton = webDriver.findElement(logInLocator);
        logInButton.click();
    }

    @Step("Fill user data")
    public void fillUserData(String name, String email, String password) {
        WebElement inputName = webDriver.findElement(nameLocator);
        WebElement inputEmail = webDriver.findElement(emailLocator);
        WebElement inputPassword = webDriver.findElement(passwordLocator);

        inputName.click();
        inputName.sendKeys(name);
        inputEmail.click();
        inputEmail.sendKeys(email);
        inputPassword.click();
        inputPassword.sendKeys(password);
    }

    @Step("wait for load registration page")
    public void waitForLoadRegistrationPage() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(nameLocator));
    }
}
