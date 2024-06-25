import PageObject.ConstructorPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class ConstructorCategoryTest {
    public WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get("https://stellarburgers.nomoreparties.site");
    }

    @DisplayName("Transition to buns category make active bun category")
    @Test
    public void transitionToBunsCategoryMakeActiveBunCategory() {
        ConstructorPage constructorPage = new ConstructorPage(webDriver);

        constructorPage.waitForLoadConstructorPage();
        constructorPage.sauceButtonClick();
        constructorPage.bunButtonClick();

        assertTrue(constructorPage.isBunsSelected());
    }

    @DisplayName("Transition to sauces category make active buns category")
    @Test
    public void transitionToSaucesCategoryMakeActiveSaucesCategory() {
        ConstructorPage constructorPage = new ConstructorPage(webDriver);

        constructorPage.waitForLoadConstructorPage();
        constructorPage.sauceButtonClick();

        assertTrue(constructorPage.isSaucesSelected());
    }

    @DisplayName("Transition to fillings category make active fillings category")
    @Test
    public void transitionToFillingsCategoryMakeActiveFillingsCategory() {
        ConstructorPage constructorPage = new ConstructorPage(webDriver);

        constructorPage.waitForLoadConstructorPage();
        constructorPage.fillingButtonClick();

        assertTrue(constructorPage.isFillingsSelected());
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
