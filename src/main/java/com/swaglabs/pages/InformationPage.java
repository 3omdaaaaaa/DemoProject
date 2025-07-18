package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.CustomSoftAssertion;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class InformationPage {
    private final GUIDriver driver;

    public InformationPage(GUIDriver driver)
    {
        this.driver = driver;
    }

//    Locator

    private final By firstNameLocator = new By.ById("first-name");
    private final By lastNameLocator = new By.ById("last-name");
    private final By postalCodeLocator = new By.ById("postal-code");
    private final By continueButtonLocator = new By.ById("continue");
    private final By cancelButtonLocator = new By.ById("cancel");

    //Actions

    @Step("Fill information form: First Name: {0}, Last Name: {1}, ZIP Code: {2}")
    public InformationPage fillInformationForm(String firstName,String lastName,String ZIPCode)
    {
        driver.element().sendData(firstNameLocator,firstName);
        driver.element().sendData(lastNameLocator,lastName);
        driver.element().sendData(postalCodeLocator,ZIPCode);
        return this;
    }

    @Step("Click continue button")
    public OverviewPage clickContinueButton()
    {
        driver.element().clicking(continueButtonLocator);
        return new OverviewPage(driver);
    }

    @Step("Click cancel button")
    public InformationPage clickCancelButton()
    {
        driver.element().clicking(cancelButtonLocator);
        return this;
    }

    //Assertions

    @Step("Assert information page")
    public void assertInformationPage(String firstName,String lastName,String ZIPCode)
    {
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput(firstNameLocator),firstName);
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput(lastNameLocator),lastName);
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput(postalCodeLocator),ZIPCode);

    }


}
