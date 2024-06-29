package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import common.actions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

import testdata.Constants;

public class BaseTest {

    public static WebDriver driver;

    public static ExtentReports extent;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/extent-report_"+ Actions.formattedDateTime()+".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        extent.flush();
        }

    @BeforeMethod(alwaysRun = true)
    @Parameters(value="BrowserName")
    public void setupDriver (String browserName) {
        setBrowserDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    public void setBrowserDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }

    public static WebDriver getDriver() {

//        WebDriver driver;
//
//        if (browserName.equalsIgnoreCase("chrome")) {
//            driver = new ChromeDriver();
//        } else if (browserName.equalsIgnoreCase("firefox")) {
//            driver = new FirefoxDriver();
//        } else {
//            driver = new ChromeDriver();
//        }

        return driver;
    }
}
