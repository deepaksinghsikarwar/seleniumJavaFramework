package testcases;

import com.aventstack.extentreports.Status;
import common.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.HomePage;
import common.actions.Actions;
import utils.dataprovider.DataProviderUtils;
import utils.logging.Log;

import java.time.Duration;

import static utils.Listeners.Listeners.test;


public class CustomTest extends BaseTest {

    @Test
    public void customTest1() {
        HomePage homePage = new HomePage(driver);
        homePage.titlePage.isDisplayed();
        homePage.searchBar.isDisplayed();
        Actions.type(homePage.searchBar, "hello world");
        Actions.type(homePage.searchBar, Keys.ENTER);
        System.out.println("Inserted text into search bar");
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
        test.log(Status.INFO, "ending customTest1");
        Log.logMessage("ending customTest1");
    }

    @Test(dataProvider = "loginPageData", dataProviderClass = DataProviderUtils.class)
    public void yourTestMethod(String data, String value) {
        System.out.println("Data: " + data);
        System.out.println("Value: " + value);
        test.log(Status.INFO, "ending yourTestMethod");
    }

    @Test(dataProvider = "excelDataProvider", dataProviderClass = DataProviderUtils.class)
    public void test2(String data, String value) {
        System.out.println("Data: " + data);
        System.out.println("Value: " + value);
        test.log(Status.INFO, "ending test2");
    }

    @Test
    public void customTest2(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(5, 4, "First assertion failed");
        softAssert.assertTrue(false, "Second assertion failed");
        driver.get("https://www.fb.com/");
        softAssert.assertAll();
        test.log(Status.INFO, "ending customTest2");
    }
}
