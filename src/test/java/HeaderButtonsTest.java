import PageObject.ConstructorPage;
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

public class HeaderButtonsTest {
    public WebDriver webDriver;
    private String token;
    private Header header;
    private ConstructorPage constructorPage;
    private PersonalAccountPage personalAccountPage;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        webDriver.get("https://stellarburgers.nomoreparties.site/login");
        User user = new User(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        UserMethods.createUser(user);
        token = UserMethods.getAccessToken(user);

        header = new Header(webDriver);
        constructorPage = new ConstructorPage(webDriver);
        personalAccountPage = new PersonalAccountPage(webDriver);
        LogInPage logInPage = new LogInPage(webDriver);

        logInPage.waitForLoadLogInPage();
        logInPage.fillUserData(user.getEmail(), user.getPassword());
        logInPage.logInButtonClick();
    }

    @DisplayName("Transition to personal account")
    @Test
    public void transitionToPersonalAccount() {
        constructorPage.waitForLoadConstructorPage();
        header.personalAccountButtonClick();
        personalAccountPage.waitForLoadPersonalAccountPage();

        assertTrue(personalAccountPage.isLogOutButtonDisplayed());
    }

    @DisplayName("Transition to constructor using logo button")
    @Test
    public void transitionToConstructorUsingLogoButton() {
        constructorPage.waitForLoadConstructorPage();
        header.personalAccountButtonClick();
        personalAccountPage.waitForLoadPersonalAccountPage();

        header.logoButtonClick();
        constructorPage.waitForLoadConstructorPage();

        assertTrue(constructorPage.isCreateOrderButtonDisplayed());
    }

    @DisplayName("Transition to constructor using constructor button")
    @Test
    public void transitionToConstructorUsingConstructorButton() {
        constructorPage.waitForLoadConstructorPage();
        header.personalAccountButtonClick();
        personalAccountPage.waitForLoadPersonalAccountPage();

        header.constructorButtonClick();
        constructorPage.waitForLoadConstructorPage();

        assertTrue(constructorPage.isCreateOrderButtonDisplayed());
    }

    @After
    public void teardown() {
        webDriver.quit();
        UserMethods.deleteUser(token);
    }
}
