package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotsUtils {
    private ScreenshotsUtils()
    {
        super();
    }

    public static final String SCREENSHOT_PATH = "test-outputs/screenshots/";

    public static void takeScreenshot(WebDriver driver , String screenshotName)
    {
        try
        {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName+ "_" + TimestampUtils.getTimestamp() + ".png");
            FileUtils.copyFile(screenshot,screenshotFile);
            AllureUtils.attachScreenshotsToAllureReport(screenshotName,screenshotFile.getPath());
        }
        catch (Exception e)
        {
            LogsUtil.error("Failed to take a screenshot" + e.getMessage());
        }
    }
}
