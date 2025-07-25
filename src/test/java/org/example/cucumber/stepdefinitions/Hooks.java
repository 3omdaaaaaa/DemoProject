package org.example.cucumber.stepdefinitions;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.JsonUtils;
import com.swaglabs.utils.LogsUtil;
import com.swaglabs.utils.PropertiesUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    public static GUIDriver driver;
    public static JsonUtils jsonUtils;
    private static boolean isDriverInitialized = false;

    public Hooks()
    {}

    @Before
    public static void setup() {
        if(!isDriverInitialized)
        {
            LogsUtil.info("===== Test Suite Starting =====");

            PropertiesUtils.loadProperties();
            driver = new GUIDriver(PropertiesUtils.getPropertyValue("browserType"));
            jsonUtils = new JsonUtils("test-data");
            isDriverInitialized = true;
            LogsUtil.info("Browser started successfully");
        }
    }

    @After
    public static void tearDown() {}

    public static void closeDriverAfterSuite() {
        if (driver != null) {
            driver.browser().closeBrowser();
            LogsUtil.info("===== Browser closed at end of suite =====");
        }
    }

}
