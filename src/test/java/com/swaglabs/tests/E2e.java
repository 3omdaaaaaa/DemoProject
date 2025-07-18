package com.swaglabs.tests;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.*;
import com.swaglabs.utils.*;
import org.testng.annotations.*;


@Listeners(TestNGListeners.class)
public class E2e {

    //Variables
    GUIDriver driver ;
    JsonUtils testData;


    @Test
    public void validLogin()
    {
        new LoginPage(driver).enterUsername(testData.getJsonData("login-credentials.username"))
                             .enterPassword(testData.getJsonData("login-credentials.password"))
                             .clickLoginButton()
                             .assertValidLogin();

    }

    @Test(dependsOnMethods = "validLogin" )
    public void addToCart()
    {
        new HomePage(driver).addToCart(testData.getJsonData("product-list.item1.name"))
                            .assertProductAddedToCart(testData.getJsonData("product-list.item1.name"))
                            .addToCart(testData.getJsonData("product-list.item4.name"))
                            .assertProductAddedToCart(testData.getJsonData("product-list.item4.name"))
                            .addToCart(testData.getJsonData("product-list.item5.name"))
                            .assertProductAddedToCart(testData.getJsonData("product-list.item5.name"));


    }

    @Test(dependsOnMethods = "addToCart")
    public void removeItems()
    {
        new HomePage(driver).removeItem(testData.getJsonData("product-list.item1.name"))
                            .assertProductRemovedFromCart(testData.getJsonData("product-list.item1.name"));

    }

    @Test(dependsOnMethods = "removeItems")
    public void checkingItems()
    {
        new HomePage(driver).clickCartIcon()
                            .checkItem((testData.getJsonData("product-list.item5.name")),testData.getJsonData("product-list.item5.price"))
                            .checkItem((testData.getJsonData("product-list.item4.name")),testData.getJsonData("product-list.item4.price"));

    }

    @Test(dependsOnMethods = "checkingItems")
    public void addingAnotherItem()
    {
        new CartPage(driver).clickContinueShoppingButton()
                            .addToCart(testData.getJsonData("product-list.item1.name"))
                            .assertProductAddedToCart(testData.getJsonData("product-list.item1.name"))
                            .clickCartIcon()
                            .checkItem(testData.getJsonData("product-list.item1.name"),testData.getJsonData("product-list.item1.price"));
    }

    @Test(dependsOnMethods = "addingAnotherItem" )
    public void fillInformationForm()
    {
        new CartPage(driver).clickCheckoutButton()
                                   .fillInformationForm(testData.getJsonData("information-form.first-name"),
                                                        testData.getJsonData("information-form.last-name"),
                                                        testData.getJsonData("information-form.ZIP-code"))
                                   .assertInformationPage(testData.getJsonData("information-form.first-name"),
                                                        testData.getJsonData("information-form.last-name"),
                                                        testData.getJsonData("information-form.ZIP-code"));
    }

    @Test(dependsOnMethods = "fillInformationForm")
    public void finishCheckout()
    {
        new InformationPage(driver).clickContinueButton()
                                   .clickFinishButton()
                                   .assertConfirmationMessage();

    }



    // Configurations
    @BeforeClass(alwaysRun = true)
    public void beforeClass()
    {
        testData = new JsonUtils("test-data");
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        driver = new GUIDriver(browserName);
        new LoginPage(driver).navigateToLoginPage();

    }


    @AfterClass(alwaysRun = true)
    public void closeDriver()
    {
        driver.browser().closeBrowser();
    }


}
