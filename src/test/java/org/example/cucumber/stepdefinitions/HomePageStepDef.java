package org.example.cucumber.stepdefinitions;

import com.swaglabs.context.TestContext;
import com.swaglabs.pages.HomePage;
import com.swaglabs.utils.JsonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageStepDef {
    JsonUtils testData ;
    HomePage homePage;

    public HomePageStepDef()
    {
//        homePage = new HomePage(Hooks.driver);
//        testData = Hooks.jsonUtils;
        homePage = new HomePage(TestContext.driver);
        testData = TestContext.jsonUtils;

    }

    @Given("the user is on the home page")
    public void navigateToHomePage()
    {
        homePage.navigateToHomePage();
    }
    @When("the user adds one or more items to the cart")
    public void addToCart()
    {
        homePage.addToCart(testData.getJsonData("product-list.item1.name"))
                .addToCart(testData.getJsonData("product-list.item4.name"))
                .addToCart(testData.getJsonData("product-list.item5.name"));
    }

    @Then("the items should appear in the cart successfully")
    @Given("the user has already added an item to the cart")
    public void assertAddedItem ()
    {
        homePage.assertProductAddedToCart(testData.getJsonData("product-list.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-list.item4.name"))
                .assertProductAddedToCart(testData.getJsonData("product-list.item5.name"));
    }


    @When("the user clicks the remove button for that item")
    public void removeItem()
    {
        homePage.removeItem(testData.getJsonData("product-list.item1.name"))
                .assertProductRemovedFromCart(testData.getJsonData("product-list.item1.name"));
    }

   @And("navigates to the cart page")
   @Then("the item should no longer be present in the cart")
   @When("the user returns to the home page")
    public void goToCart()
    {
        homePage.clickCartIcon()
                .checkItem((testData.getJsonData("product-list.item5.name")),testData.getJsonData("product-list.item5.price"))
                .checkItem((testData.getJsonData("product-list.item4.name")),testData.getJsonData("product-list.item4.price"))
                .clickContinueShoppingButton();
    }


    @And("adds another item")
    public void addingAnotherItem()
    {
     homePage.addToCart(testData.getJsonData("product-list.item1.name"))
             .assertProductAddedToCart(testData.getJsonData("product-list.item1.name"));
    }


    @Then("the new item should appear in the cart")
    public void checkingCart(){
        homePage.clickCartIcon()
                .checkItem(testData.getJsonData("product-list.item1.name"),testData.getJsonData("product-list.item1.price"));
    }

}
