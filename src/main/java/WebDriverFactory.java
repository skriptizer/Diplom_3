import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver getWebDriver(String browserType) {
        if (browserType.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "C:\\cygwin64\\WebDriver\\bin\\yandexdriver.exe");
            return new ChromeDriver();
        } else if (browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        return null;
    }
}
