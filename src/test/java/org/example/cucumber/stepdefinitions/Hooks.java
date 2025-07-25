package org.example.cucumber.stepdefinitions;

import com.swaglabs.context.DriverManager;
import com.swaglabs.utils.JsonUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    public static JsonUtils jsonUtils;


    @Before
    public static void setup() {
        DriverManager.initDriver();
        jsonUtils = com.swaglabs.context.TestContext.jsonUtils;
    }

    @After
    public static void tearDown()
    {
        // doing nothing
    }
}
