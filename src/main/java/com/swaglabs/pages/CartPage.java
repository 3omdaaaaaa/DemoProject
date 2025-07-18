package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private final GUIDriver driver;

    public CartPage (GUIDriver driver) {
        this.driver = driver;
    }

    // Locator
    private final By cartItemsLocator = By.className("cart_item");
    private final By itemNameLocator = By.className("inventory_item_name");
    private final By itemPriceLocator = By.className("inventory_item_price");
    private final By checkoutButtonLocator = By.id("checkout");
    private final By continueShoppingButtonLocator = By.id("continue-shopping");




    // Actions

    @Step("Navigating to the cart page")
    public CartPage navigateToCartPage()
    {
        driver.browser().navigateToURL( PropertiesUtils.getPropertyValue("cartURL"));
        return this;
    }

    @Step("Clicking on the checkout button")
    public InformationPage clickCheckoutButton()
    {
        driver.element().clicking(checkoutButtonLocator);
        return new InformationPage(driver);
    }

    @Step("Clicking on the continue shopping button")
    public HomePage clickContinueShoppingButton()
    {
        driver.element().clicking(continueShoppingButtonLocator);
        return new HomePage(driver);
    }




    //Assertions
    @Step("Checking specific item in the cart")
    public CartPage checkItem(String productName, String productPrice) {
        LogsUtil.info("Checking the cart");

        List<WebElement> items = driver.element().findElements(cartItemsLocator);
        boolean found = false;

        for (WebElement item : items) {
            String actualName = item.findElement(itemNameLocator).getText();
            String actualPrice = item.findElement(itemPriceLocator).getText();

            LogsUtil.info("Found product in cart: " + actualName + "with price: " + actualPrice);

            if (actualName.equals(productName) && actualPrice.equals(productPrice)) {
                found = true;
                break;
            }
        }

        CustomSoftAssertion.softAssertion.assertTrue(found, "Product is not present in the cart: " + productName);
        CustomSoftAssertion.softAssertion.assertTrue(found, "Price is not correct");

        LogsUtil.info("The product '" + productName + "' is in the cart and the price is: " + productPrice);

        return this;
    }

}