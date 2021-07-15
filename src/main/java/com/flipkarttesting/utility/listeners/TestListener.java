/*
 *Purpose : Class is implemented for taking screenshots after every test execution
 *          by implements ITestListener interface and uses ITestResults interface
 *          to identify the results of test
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 25-06-2021
 */

package com.flipkarttesting.utility.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.flipkarttesting.base.BaseClass;
import com.flipkarttesting.utility.CaptureScreenshot;
import com.flipkarttesting.utility.Log;
import com.flipkarttesting.utility.extent_report.ExtentManager;
import com.flipkarttesting.utility.extent_report.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Arrays;

public class TestListener extends BaseClass implements ITestListener {

    CaptureScreenshot captureScreenshot = new CaptureScreenshot(); //capture screenshot object is created
    //excelUtil object is created

    public void onTestStart(ITestResult result) {
        Log.info(result.getName() + " test is starting.");
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext testContext) {
        Log.info("I am in onFinish method " + testContext.getName());
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    /**
     * onTestFailure method is used to perform some action which are given after test case is failed
     * @param result ITestResult interface is used to get results of the testcase
     */
    public void onTestFailure(ITestResult result) {
        System.out.println("Method failed " + result.getName());
        Log.error(result.getName() + " Test is failed");
        System.out.println(Arrays.toString(result.getParameters()));

        String screenshotPath = captureScreenshot.captureScreenshot(result.getName(), "failed");
        try {
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
            MediaEntityBuilder screenshot = MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath);
            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed",screenshot.build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        captureScreenshot.captureScreenshot(result.getName(), "failed");
    }

    /**
     * onTestSuccess method is used to perform some action which are given after test case is passed
     * @param result ITestResult interface is used to get results of the testcase
     */
    public void onTestSuccess(ITestResult result) {
        System.out.println("Method passed " + result.getName());
        Log.info(result.getName()+ " Test is passed");
        System.out.println(Arrays.toString(result.getParameters()));

        captureScreenshot.captureScreenshot(result.getName(), "success");
        String screenshotPath = captureScreenshot.captureScreenshot(result.getName(), "success");
        try {
            MediaEntityBuilder screenshot = MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath);
            ExtentTestManager.getTest().log(Status.PASS, "Test passed",screenshot.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * onTestSuccess method is used to perform some action which are given after test case is skipped or ignored
     * @param result ITestResult interface is used to get results of the testcase
     */
    public void onTestSkipped(ITestResult result) {
        System.out.println("Method skipped " + result.getName());
        Log.warn(result.getName()+" Test is skipped");
        System.out.println(Arrays.toString(result.getParameters()));

        captureScreenshot.captureScreenshot(result.getName(), "skipped");
        String screenshotPath = captureScreenshot.captureScreenshot(result.getName(), "skipped");
        try {
            MediaEntityBuilder screenshot = MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath);
            ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped",screenshot.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + iTestResult.getName());
    }
}
