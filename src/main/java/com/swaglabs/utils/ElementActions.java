package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ElementActions {
    private WebDriver driver;
    private Waits waits;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);


    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    @Step("Sending data ; {data} to the element: {locator}")
    public void sendData( By locator, String data) {
        waits.waitForElementVisible( locator);
       scrollToElement( locator);
        findElement(locator).sendKeys(data);
    }

    @Step("Clicking on the element: {locator}")
    public void clicking( By locator) {
        waits.waitForElementClickable( locator);
        scrollToElement( locator);
        findElement(locator).click();
    }

    @Step("Getting text from the element: {locator}")
    public  String getText( By locator) {
        waits.waitForElementVisible(locator);
       scrollToElement(locator);
        return findElement(locator).getText();
    }

    public  String getTextFromInput(By locator)
    {
        waits.waitForElementVisible( locator);
    scrollToElement( locator);
        return findElement(locator).getDomAttribute("value");
    }

    public void scrollToElement (By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",findElement(locator));
    }
}
