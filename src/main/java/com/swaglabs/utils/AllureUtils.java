package com.swaglabs.utils;

import io.qameta.allure.Allure;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";
    public static final String ALLURE_REPORT_PATH = "test-outputs/allure-report";
    static String USER_HOME = System.getProperty("user.home");
    static String REPORT_PATH = USER_HOME + File.separator + "scoop"+ File.separator + "apps" + File.separator +"allure" + File.separator +"2.34.1" + File.separator
        +"bin"+ File.separator + "allure" ;
    private AllureUtils()
    {
        super();
    }

    public static void generateAllureReport()
    {
        if(PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win"))
        {
            String WIN = REPORT_PATH +".bat";
            TerminalUtils.executionCommand(WIN,"generate",ALLURE_RESULTS_PATH,"-o",ALLURE_REPORT_PATH,"--clean","--single-file");
            LogsUtil.info("Allure report generated successfully on windows");

        }
        else
        {
            TerminalUtils.executionCommand(REPORT_PATH,"generate",ALLURE_RESULTS_PATH,"-o",ALLURE_REPORT_PATH,"clean","--single-file");
            LogsUtil.info("Allure report generated successfully on: " + PropertiesUtils.getPropertyValue("os.name"));
        }
    }

    public static void openAllureReport()
    {
        String reportPath = ALLURE_REPORT_PATH +File.separator +"index.html";
        if(PropertiesUtils.getPropertyValue("openAllureAutomatically").equalsIgnoreCase("true"))
        {
            if(PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win"))
            {
                TerminalUtils.executionCommand("cmd.exe","/c","start", reportPath);
            }
            else
            {
                TerminalUtils.executionCommand("open",reportPath);
            }
        }
    }

    public static void attachLogsToAllureReport()
    {
        try
        {
            File logFile = FilesUtils.getLatestFile(LogsUtil.LOGS_PATH);
            if(!logFile.exists())
            {
                LogsUtil.warn("Logs file does not exist: " + LogsUtil.LOGS_PATH);
                return;
            }
            Allure.addAttachment("logs.log", Files.readString(Path.of (logFile.getPath())));
            LogsUtil.info("Logs attached to Allure report");
        } catch (Exception e)
        {
            LogsUtil.error("Failed to attach logs to Allure report: " + e.getMessage() );
        }

    }

    public static void attachScreenshotsToAllureReport(String screenshotName , String screenshotPath)
    {
        try
        {
            Allure.addAttachment(screenshotName,Files.newInputStream(Path.of(screenshotPath)));
        }
        catch (Exception e)
        {
            LogsUtil.error("Failed to attach screenshot to Allure report");
        }
    }
}
