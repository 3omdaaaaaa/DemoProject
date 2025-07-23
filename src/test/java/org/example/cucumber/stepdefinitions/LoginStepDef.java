package org.example.cucumber.stepdefinitions;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.JsonUtils;
import com.swaglabs.utils.PropertiesUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef {
    JsonUtils testData ;
    LoginPage loginPage;

    public LoginStepDef()
    {
        loginPage = new LoginPage(Hooks.driver);
        testData = Hooks.jsonUtils;

    }



    @Given ("User navigate to the login page")
    public void navigateToLoginPage()
    {
        loginPage.navigateToLoginPage();
    }


    @When("User entered a valid username and password")
    public void enterCredentials()
    {
        loginPage.enterUsername(testData.getJsonData("login-credentials.username"));
        loginPage.enterPassword(testData.getJsonData("login-credentials.password"));
    }

    @And("User click on login button")
    public void clickLoginButton()
    {
        loginPage.clickLoginButton();
    }

    @Then("User login successfully")
    public void assertSuccessfulLogin()
    {
        loginPage.assertValidLogin();
    }

}
