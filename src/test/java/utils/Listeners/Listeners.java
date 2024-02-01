package utils.Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import common.BaseTest;
import common.actions.Actions;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {

    public static ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {

        test = BaseTest.extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");

        try {
            String screenshotPath = Actions.captureScreenshot(BaseTest.driver); // Capture screenshot on failure

            test.addScreenCaptureFromPath(System.getProperty("user.dir")+ File.separator+screenshotPath); // Add screenshot to the Extent Report
        } catch (IOException e) {
            test.log(Status.WARNING, "Failed to attach screenshot to the report: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
