import PageObject.*;
import Users.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import Users.UserData;
import Users.UserMethods;
import static org.junit.Assert.assertTrue;

public class LogInButtonsTest {
    public WebDriver webDriver;
    private User user;
    private String token;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        user = new User(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        UserMethods.createUser(user);
        token = UserMethods.getAccessToken(user);
    }

    @DisplayName("Log In using constructor page button")
    @Test
    public void logInUsingConstructorPageButton() {
        webDriver.get("https://stellarburgers.nomoreparties.site");

        LogInPage logInPage = new LogInPage(webDriver);
        ConstructorPage constructorPage = new ConstructorPage(webDriver);

        constructorPage.waitForLoadConstructorPage();
        constructorPage.loginButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.fillUserData(user.getEmail(), user.getPassword());
        logInPage.logInButtonClick();

        constructorPage.waitForLoadConstructorPage();
        assertTrue(constructorPage.isCreateOrderButtonDisplayed());
    }

    @DisplayName("Log In using personal account button")
    @Test
    public void logInUsingPersonalAccountButton() {
        webDriver.get("https://stellarburgers.nomoreparties.site");

        Header header = new Header(webDriver);
        LogInPage logInPage = new LogInPage(webDriver);
        ConstructorPage constructorPage = new ConstructorPage(webDriver);

        header.waitForLoadHeader();
        header.personalAccountButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.fillUserData(user.getEmail(), user.getPassword());
        logInPage.logInButtonClick();

        constructorPage.waitForLoadConstructorPage();
        assertTrue(constructorPage.isCreateOrderButtonDisplayed());
    }

    @DisplayName("Log In using registration page button")
    @Test
    public void logInUsingRegistrationPageButton() {
        webDriver.get("https://stellarburgers.nomoreparties.site/register");

        LogInPage logInPage = new LogInPage(webDriver);
        ConstructorPage constructorPage = new ConstructorPage(webDriver);
        RegistrationPage registrationPage = new RegistrationPage(webDriver);

        registrationPage.waitForLoadRegistrationPage();
        registrationPage.logInButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.fillUserData(user.getEmail(), user.getPassword());
        logInPage.logInButtonClick();

        constructorPage.waitForLoadConstructorPage();
        assertTrue(constructorPage.isCreateOrderButtonDisplayed());
    }

    @DisplayName("Log In using forgot password page button")
    @Test
    public void logInUsingForgotPasswordPageButton() {
        webDriver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        LogInPage logInPage = new LogInPage(webDriver);
        ConstructorPage constructorPage = new ConstructorPage(webDriver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(webDriver);

        forgotPasswordPage.waitForLoadForgotPasswordPage();
        forgotPasswordPage.logInButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.fillUserData(user.getEmail(), user.getPassword());
        logInPage.logInButtonClick();

        constructorPage.waitForLoadConstructorPage();
        assertTrue(constructorPage.isCreateOrderButtonDisplayed());
    }

    @After
    public void teardown() {
        webDriver.quit();
        UserMethods.deleteUser(token);
    }
}
