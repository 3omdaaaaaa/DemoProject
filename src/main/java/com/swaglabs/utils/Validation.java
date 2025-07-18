package com.swaglabs.utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation {

    private WebDriver driver;
    private BrowserActions browserActions;
    public Validation(WebDriver driver){
        this.driver = driver;
        browserActions = new BrowserActions(driver);

    }

    public  void assertTrue(boolean condition , String message){
        Assert.assertTrue(condition, message);
    }

    public  void assertFalse(boolean condition , String message){
        Assert.assertFalse(condition, message);
    }

    public  void assertEquals(String actual , String expected, String message){
        Assert.assertEquals(actual,expected, message);
    }

    public  void assertNotEquals(String actual , String expected, String message){
        Assert.assertNotEquals(actual,expected, message);
    }

    public void validatePageUrl(String expected){
        Assert.assertEquals(browserActions.getCurrentUrl(),expected);
    }

    public void validatePageTitle(String expected){
        Assert.assertEquals(browserActions.getPageTitle(),expected);
    }
}
