package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorPage {
    private final WebDriver webDriver;
    private static final String ACTIVE_CATEGORY = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

    private By logInButtonLocator = By.xpath(".//button[text()='Войти в аккаунт']");
    private By createOrderButtonLocator = By.xpath(".//button[text()='Оформить заказ']");
    private By bunButtonLocator = By.xpath(".//span[text()='Булки']/parent::div");
    private By sauceButtonLocator = By.xpath(".//span[text()='Соусы']/parent::div");
    private By fillingButtonLocator = By.xpath(".//span[text()='Начинки']/parent::div");

    public ConstructorPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step("is Create Order Button Displayed check")
    public boolean isCreateOrderButtonDisplayed() {
        return webDriver.findElement(createOrderButtonLocator).isDisplayed();
    }

    @Step("wait for load constructor page")
    public void waitForLoadConstructorPage() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(bunButtonLocator));
    }

    @Step("Click on the Log In button in Constructor page")
    public void loginButtonClick() {
        WebElement loginButton = webDriver.findElement(logInButtonLocator);
        loginButton.click();
    }

    @Step("Click on the Sauces button in Constructor page")
    public void sauceButtonClick() {
        WebElement sauceButton = webDriver.findElement(sauceButtonLocator);
        sauceButton.click();
    }

    @Step("Click on the Buns button in Constructor page")
    public void bunButtonClick() {
        WebElement bunButton = webDriver.findElement(bunButtonLocator);
        bunButton.click();
    }

    @Step("Click on the Fillings button in Constructor page")
    public void fillingButtonClick() {
        WebElement fillingButton = webDriver.findElement(fillingButtonLocator);
        fillingButton.click();
    }

    @Step("is Buns Selected check")
    public boolean isBunsSelected() {
        String bunAttribute = webDriver.findElement(bunButtonLocator).getAttribute("class");
        System.out.println(bunAttribute);
        return bunAttribute.equals(ACTIVE_CATEGORY);
    }

    @Step("is Sauces Selected check")
    public boolean isSaucesSelected() {
        String sauceAttribute = webDriver.findElement(sauceButtonLocator).getAttribute("class");
        System.out.println(sauceAttribute);
        return sauceAttribute.equals(ACTIVE_CATEGORY);
    }

    @Step("is Fillings Selected check")
    public boolean isFillingsSelected() {
        String fillingAttribute = webDriver.findElement(fillingButtonLocator).getAttribute("class");
        System.out.println(fillingAttribute);
        return fillingAttribute.equals(ACTIVE_CATEGORY);
    }
}
