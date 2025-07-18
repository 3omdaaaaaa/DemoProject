package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;



public class LoginPage {
    private final GUIDriver driver;

    public  LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Locator
    private final By usernameLocator = By.id("user-name");
    private final By passwordLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By errorMessageLocator = By.cssSelector("[data-test='error']");


    // Actions
    @Step("Navigating to the login page")
    public void navigateToLoginPage()
    {
        driver.browser().navigateToURL(PropertiesUtils.getPropertyValue("baseURL"));
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username){
        driver.element().sendData(usernameLocator, username);
        return this;

    }

    @Step("Enter password: {password}")
    public LoginPage enterPassword(String password) {
        driver.element().sendData(passwordLocator,password);
        return this;
    }

    @Step("Clicking on the login button")
    public LoginPage clickLoginButton() {
        driver.element().clicking(loginButtonLocator);
        return this;
    }

    public String getErrorMessage()
    {
        return driver.element().getText(errorMessageLocator);
    }


    // Assertions
    public LoginPage assertLoginPageTitle()
    {
        CustomSoftAssertion.softAssertion.assertEquals(driver.browser().getPageTitle(),PropertiesUtils.getPropertyValue("appTitle"));
        return this;
    }

    public LoginPage assertValidLogin()
    {
        driver.validate().validatePageUrl(PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }

    public LoginPage  assertInvalidLogin()
    {
        driver.validate().assertEquals(getErrorMessage(),PropertiesUtils.getPropertyValue("errorMessage"),"Error message is not as expected");
        return this;
    }
}
