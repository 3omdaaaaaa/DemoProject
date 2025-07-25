package org.example.cucumber.stepdefinitions;

import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.JsonUtils;
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



    @Given ("the user is on the login page")
    public void navigateToLoginPage()
    {
        loginPage.navigateToLoginPage();
    }


    @When("the user enters a valid username and password")
    public void enterCredentials()
    {
        loginPage.enterUsername(testData.getJsonData("login-credentials.username"))
                 .enterPassword(testData.getJsonData("login-credentials.password"));
    }

    @And("clicks the login button")
    public void clickLoginButton()
    {
        loginPage.clickLoginButton();
    }

    @Then("the user should be logged in successfully")
    public void assertSuccessfulLogin()
    {
        loginPage.assertValidLogin();
    }

}
