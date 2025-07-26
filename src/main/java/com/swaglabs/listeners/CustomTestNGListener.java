package com.swaglabs.listeners;

import com.swaglabs.context.DriverManager;
import com.swaglabs.utils.AllureUtils;
import com.swaglabs.utils.LogsUtil;
import org.testng.IExecutionListener;


public class CustomTestNGListener implements IExecutionListener {


    @Override
    public void onExecutionFinish() {
        LogsUtil.info("===== TestNG Execution Finished =====");
        DriverManager.closeDriver();
        LogsUtil.info("Browser closed successfully by listener.");
        AllureUtils.generateAllureReport();
        AllureUtils.openAllureReport();
    }

}
