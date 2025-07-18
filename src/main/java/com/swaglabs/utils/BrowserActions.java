package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private WebDriver driver;
    public BrowserActions(WebDriver driver) {
        this.driver = driver;

    }

    @Step("Navigating to URL; {url}")
    public void navigateToURL( String url) {
        driver.get(url);
    }

    @Step("Getting current URL")
    public  String getCurrentUrl ()
    {
       return driver.getCurrentUrl();
    }

    @Step("Getting page title")
    public  String getPageTitle(){
        return driver.getTitle();
    }

    @Step("Refreshing the page")
    public  void refreshPage()
    {
        driver.navigate().refresh();
    }

    @Step("Closing the browser")
    public  void closeBrowser(){
        LogsUtil.info("closing the browser");
        driver.quit();
    }
}
