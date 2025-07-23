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


    @Before
    public void setBrowser()
    {
        PropertiesUtils.loadProperties();
        driver = new GUIDriver(PropertiesUtils.getPropertyValue("browserType"));
        jsonUtils = new JsonUtils("test-data");
        LogsUtil.info("Browser is started successfully");


    }

    @After
    public void closeBrowser()
    {
        driver.browser().closeBrowser();
        LogsUtil.info("Browser closed successfully");

    }
}
