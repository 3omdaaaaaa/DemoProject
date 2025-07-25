package org.example.cucumber.stepdefinitions;

import com.swaglabs.context.TestContext;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.ConfirmationPage;
import com.swaglabs.pages.InformationPage;
import com.swaglabs.pages.OverviewPage;
import com.swaglabs.utils.JsonUtils;
import com.swaglabs.utils.PropertiesUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutStepDef {
    CartPage cartPage;
    InformationPage informationPage;
    OverviewPage overviewPage;
    ConfirmationPage confirmationPage;
    JsonUtils testData;

    public CheckoutStepDef()
    {
//        cartPage = new CartPage(Hooks.driver);
//        informationPage = new InformationPage(Hooks.driver);
//        overviewPage = new OverviewPage(Hooks.driver);
//        confirmationPage = new ConfirmationPage(Hooks.driver);
//        testData = Hooks.jsonUtils;

        cartPage = new CartPage(TestContext.driver);
        informationPage = new InformationPage(TestContext.driver);
        overviewPage = new OverviewPage(TestContext.driver);
        confirmationPage = new ConfirmationPage(TestContext.driver);
        testData = TestContext.jsonUtils;
    }


    @Given("User is on the cart page with items added")
    public void navigateToCart()
    {
        cartPage.navigateToCartPage();
    }

    @When("The user clicks on the {string} button")
    public void clickCheckoutButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Checkout")) {
            cartPage.clickCheckoutButton();
        }
    }

    @And("User fills in personal information")
    public void fillInformationForm()
    {
        informationPage.fillInformationForm(testData.getJsonData("information-form.first-name"),
                                            testData.getJsonData("information-form.last-name"),
                                            testData.getJsonData("information-form.ZIP-code"))
                       .assertInformationPage(testData.getJsonData("information-form.first-name"),
                                            testData.getJsonData("information-form.last-name"),
                                            testData.getJsonData("information-form.ZIP-code"));
    }

    @And("User clicks on the {string} button")
    public void clickContinueButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Continue")) {
            informationPage.clickContinueButton();
        }
    }

    @And("User confirms the order by clicking the {string} button")
    public void clickFinishButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Finish")) {
            overviewPage.clickFinishButton();
        }
    }

    @Then("A confirmation message is displayed saying {string}")
    public void verifyConfirmationMessage(String expectedMessage) {
        if(expectedMessage.equalsIgnoreCase(PropertiesUtils.getPropertyValue("confirmationMessage")))
        {
            confirmationPage.assertConfirmationMessage();
        }

    }


}
