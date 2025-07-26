package com.swaglabs.listeners;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import org.testng.*;
import java.io.File;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener  {

    File allure_result ;
    File logs ;
    File screenshots ;



    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test execution started");
        PropertiesUtils.loadProperties();

        allure_result = new File(PropertiesUtils.getPropertyValue("allure.results.directory"));

        logs = new File(LogsUtil.LOGS_PATH);
        screenshots = new File(ScreenshotsUtils.SCREENSHOT_PATH);

        FilesUtils.deleteFiles(allure_result);
        FilesUtils.cleanDirectory(logs);
        FilesUtils.deleteFiles(screenshots);

        FilesUtils.createDirectory(allure_result);
        FilesUtils.createDirectory(logs);
        FilesUtils.createDirectory(screenshots);
    }

    @Override
    public void onExecutionFinish() {
        LogsUtil.info("Test execution finished");
        AllureUtils.generateAllureReport();
        AllureUtils.openAllureReport();
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        if(method.isTestMethod()){

            CustomSoftAssertion.customAssertAll(testResult);

            switch (testResult.getStatus())
            {

                case ITestResult.SUCCESS -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(),"passed-" +testResult.getName());

                case ITestResult.FAILURE -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(),"failed-" +testResult.getName());

                case ITestResult.SKIP -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(),"skipped-" +testResult.getName());
            }

            AllureUtils.attachLogsToAllureReport();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("test case" , result.getName() , "passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("test case" , result.getName() , "failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("test case" , result.getName() , "skipped");
    }
}
