package com.swaglabs.drivers;

import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.LogsUtil;
import com.swaglabs.utils.Validation;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class GUIDriver {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    WebDriver driver;
    public GUIDriver(String browserName) {
         driver = getDriver(browserName).startDriver();
        setDriver(driver);
    }


    private AbstractDriver getDriver(String browserName) {

        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeFactory();
            case "edge" -> new EdgeFactory();
            case "firefox" -> new FirefoxFactory();
            default -> throw new IllegalArgumentException();
        };

    }

    private void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static WebDriver getInstance()
    {
        return driverThreadLocal.get();
    }

    public WebDriver get(){
        if(driverThreadLocal.get() == null)
        {
            LogsUtil.error("Driver is null");
            fail("Driver is null");
            return null;
        }
        return driverThreadLocal.get();
    }

    public ElementActions element()
    {
        return new ElementActions(get());
    }

    public BrowserActions browser()
    {
        return new BrowserActions(get());
    }

    public Validation validate()
    {
        return new Validation(get());
    }

}
