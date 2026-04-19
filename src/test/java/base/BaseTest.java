package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestContext;

import utils.ConfigReader;


public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected ConfigReader config;

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp(ITestContext context) {
        config=new ConfigReader();

        String browser=config.getBrowser();
        String url=config.getBaseUrl();

        if (browser==null || url==null) {
            throw new RuntimeException("Config values missing");
        }
        WebDriver localDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                localDriver=new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                localDriver=new FirefoxDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }

        localDriver.manage().window().maximize();
        localDriver.get(url);

        driver.set(localDriver);
        context.setAttribute("driver", localDriver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get()!= null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
