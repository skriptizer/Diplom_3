import PageObject.ConstructorPage;
import PageObject.Header;
import PageObject.LogInPage;
import PageObject.RegistrationPage;
import Users.User;
import Users.UserMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import Users.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    public WebDriver webDriver;
    private User user;
    private String token;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get("https://stellarburgers.nomoreparties.site");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        user = new User();
    }

    @DisplayName("Registration correct data")
    @Test
    public void registrationCorrectData() {
        Header header = new Header(webDriver);
        LogInPage logInPage = new LogInPage(webDriver);
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        ConstructorPage constructorPage = new ConstructorPage(webDriver);

        user.setEmail(UserData.EMAIL);
        user.setName(UserData.NAME);
        user.setPassword(UserData.PASSWORD);

        header.waitForLoadHeader();
        header.personalAccountButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.singUpButtonClick();

        registrationPage.waitForLoadRegistrationPage();
        registrationPage.fillUserData(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.singUpButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.fillUserData(user.getEmail(), user.getPassword());
        logInPage.logInButtonClick();
        constructorPage.waitForLoadConstructorPage();

        token = UserMethods.getAccessToken(user);

        assertTrue(constructorPage.isCreateOrderButtonDisplayed());
    }

    @DisplayName("Registration incorrect password")
    @Test
    public void registrationIncorrectPasswordOk() {
        Header header = new Header(webDriver);
        LogInPage logInPage = new LogInPage(webDriver);
        RegistrationPage registrationPage = new RegistrationPage(webDriver);

        user.setEmail(UserData.EMAIL);
        user.setName(UserData.NAME);
        user.setPassword(UserData.INCORRECT_PASSWORD);

        header.waitForLoadHeader();
        header.personalAccountButtonClick();

        logInPage.waitForLoadLogInPage();
        logInPage.singUpButtonClick();

        registrationPage.waitForLoadRegistrationPage();
        registrationPage.fillUserData(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.singUpButtonClick();

        token = UserMethods.getAccessToken(user);

        assertTrue(registrationPage.isInvalidPasswordTextDisplayed());
    }

    @After
    public void teardown() {
        webDriver.quit();
        if (token != null) {
            UserMethods.deleteUser(token);
        }
    }
}
