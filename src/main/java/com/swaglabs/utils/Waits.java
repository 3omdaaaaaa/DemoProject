package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private WebDriver driver;
    public Waits(WebDriver driver) {
        this.driver = driver;
    }

    // wait for element to be present
    public  WebElement waitForElementPresent( By locator) {
        LogsUtil.info("waiting for element to be present: ",locator.toString());

       return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> driver.findElement(locator));

    }


    // wait for element to be visible
    public  WebElement waitForElementVisible( By locator)  {
        LogsUtil.info("waiting for element to be visible: ",locator.toString());

        return new WebDriverWait(driver,Duration.ofSeconds(10)).until(driver1 ->
        {
            WebElement element = waitForElementPresent(locator) ;
                return element.isDisplayed() ? element : null ;

        });

    }

    // wait of element to be clickable
    public  WebElement waitForElementClickable( By locator) {
        LogsUtil.info("waiting for element to be clickable: ",locator.toString());

        return new WebDriverWait(driver,Duration.ofSeconds(10)).until(driver1 ->
        {
            WebElement element = waitForElementVisible(locator);
            return element.isEnabled() ? element : null;
        });

    }
}
