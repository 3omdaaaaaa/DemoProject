package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.PropertiesUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class ConfirmationPage {
    private final GUIDriver driver;

    public ConfirmationPage(GUIDriver driver)
    {
        this.driver = driver;
    }

    //Locator

    private final By confirmationMessage = By.className("complete-header");
    private final By backHomeButtonLocator = By.id("back-to-products");

    //Actions

    @Step("Get confirmation message")
    public String getConfirmationMessage()
    {
        return driver.element().getText(confirmationMessage);
    }

    @Step("Click back home button ")
    public ConfirmationPage clickBackHomeButton()
    {
        driver.element().clicking(backHomeButtonLocator);
        return this;
    }


    //Assertions

    @Step("Assert confirmation message")
    public ConfirmationPage assertConfirmationMessage()
    {
        driver.validate().assertEquals(getConfirmationMessage(), PropertiesUtils.getPropertyValue("confirmationMessage"),"Confirmation message is not as expected");
        return this;
    }



}
