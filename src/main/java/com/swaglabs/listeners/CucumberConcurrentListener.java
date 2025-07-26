package com.swaglabs.listeners;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import java.io.File;

public class CucumberConcurrentListener implements ConcurrentEventListener {

    File allureResult ;
    File logs ;
    File screenshots ;
    File allure_report;


    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::onExecutionStart);
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStart);
        publisher.registerHandlerFor(TestStepFinished.class, this::onStepFinished);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinish);
        publisher.registerHandlerFor(TestRunFinished.class, this::onExecutionFinish);
    }

    private void onExecutionStart(TestRunStarted event) {
        LogsUtil.info("Cucumber Test Execution Started");
        PropertiesUtils.loadProperties();

        allureResult = new File(PropertiesUtils.getPropertyValue("allure.results.directory"));
        allure_report = new File(PropertiesUtils.getPropertyValue("allure.report.directory"));
        logs = new File(LogsUtil.LOGS_PATH);
        screenshots = new File(ScreenshotsUtils.SCREENSHOT_PATH);

        FilesUtils.deleteFiles(allureResult);
        FilesUtils.cleanDirectory(logs);
        FilesUtils.deleteFiles(screenshots);

        FilesUtils.createDirectory(allureResult);
        FilesUtils.createDirectory(logs);
        FilesUtils.createDirectory(screenshots);
    }

    private void onTestCaseStart(TestCaseStarted event) {
        LogsUtil.info("Started Scenario: " + event.getTestCase().getName());
    }

    private void onStepFinished(TestStepFinished event) {
        if (event.getTestStep() instanceof PickleStepTestStep step) {
            String stepName = step.getStep().getText();
           // String scenarioName = step.getCodeLocation(); 
            String status = event.getResult().getStatus().name();

            LogsUtil.info("Step: " + stepName + " | Status: " + status);

            switch (event.getResult().getStatus()) {
                case PASSED -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(), "passed-" + stepName);
                case FAILED -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(), "failed-" + stepName);
                case SKIPPED -> ScreenshotsUtils.takeScreenshot(GUIDriver.getInstance(), "skipped-" + stepName);
            }

            AllureUtils.attachLogsToAllureReport();
        }
    }

    private void onTestCaseFinish(TestCaseFinished event) {
        LogsUtil.info("Finished Scenario: " + event.getTestCase().getName() + " → Status: " + event.getResult().getStatus());
    }

    private void onExecutionFinish(TestRunFinished event) {
        LogsUtil.info("Cucumber Test Execution Finished");

    }
}
