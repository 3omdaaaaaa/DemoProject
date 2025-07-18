package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class OverviewPage {

    private final GUIDriver driver;

    public OverviewPage(GUIDriver driver)
    {
        this.driver = driver;
    }


    //Locator

    private final By finishButtonLocator = By.id("finish");
    private final By cancelButtonLocator = By.id("cancel");

    //Actions

    @Step("Click finish button")
    public ConfirmationPage clickFinishButton()
    {
        driver.element().clicking(finishButtonLocator);
        return new ConfirmationPage(driver);
    }

    @Step("Click cancel button")
    public OverviewPage clickCancelButton()
    {
        driver.element().clicking(cancelButtonLocator);
        return this;
    }

}
