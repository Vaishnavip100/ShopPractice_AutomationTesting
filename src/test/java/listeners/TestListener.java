package listeners;

import base.BaseTest;
import com.aventstack.extentreports.*;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener{
    private static ExtentReports extent=ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest=extent.createTest(result.getName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName=result.getName();
        WebDriver driver=null;

        try {
            driver=((BaseTest) result.getInstance()).getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String path="";

        if (driver!=null) {
            path=ScreenshotUtil.captureScreenshot(driver, testName);
        }
        test.get().fail(result.getThrowable());

        try {
            if (!path.isEmpty()) {
                test.get().addScreenCaptureFromPath(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}