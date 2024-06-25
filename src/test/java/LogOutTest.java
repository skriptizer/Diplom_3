import PageObject.Header;
import PageObject.LogInPage;
import PageObject.PersonalAccountPage;
import Users.User;
import Users.UserData;
import Users.UserMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class LogOutTest {
    public WebDriver webDriver;
    private User user;
    private String token;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        webDriver.get("https://stellarburgers.nomoreparties.site/login");
        user = new User(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        UserMethods.createUser(user);
        token = UserMethods.getAccessToken(user);
    }

    @DisplayName("Log out using personal account button")
    @Test
    public void logOutUsingPersonalAccountButton() {
        LogInPage logInPage = new LogInPage(webDriver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        Header header = new Header(webDriver);

        logInPage.waitForLoadLogInPage();
        logInPage.fillUserData(user.getEmail(), user.getPassword());
        logInPage.logInButtonClick();

        header.personalAccountButtonClick();
        personalAccountPage.waitForLoadPersonalAccountPage();
        personalAccountPage.logOutButtonClick();

        logInPage.waitForLoadLogInPage();

        assertTrue(logInPage.isLogInButtonDisplayed());
    }

    @After
    public void teardown() {
        webDriver.quit();
        UserMethods.deleteUser(token);
    }
}
