package com.swaglabs.context;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;

public class DriverManager {
    private static boolean isDriverInitialized = false;

    public static void initDriver() {
        if (!isDriverInitialized) {
            LogsUtil.info("===== Test Suite Starting =====");
            TestContext.driver = new GUIDriver(PropertiesUtils.getPropertyValue("browserType"));
            TestContext.jsonUtils = new JsonUtils("test-data");
            isDriverInitialized = true;
            LogsUtil.info("Browser started successfully");
        }
    }

    public static void closeDriver() {
        if (TestContext.driver != null) {
            TestContext.driver.browser().closeBrowser();
        }
    }
}
