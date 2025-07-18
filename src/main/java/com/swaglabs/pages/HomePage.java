package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    private final GUIDriver driver;

    public HomePage(GUIDriver driver) {
        this.driver = driver;
    }

    // Locator
    private final By cartIconLocator = By.cssSelector("[data-test=shopping-cart-link]");



    // Actions
    @Step("Navigating to the home page")
    public HomePage navigateToHomePage()
    {
        driver.browser().navigateToURL(PropertiesUtils.getPropertyValue("homeURL"));
        return this;
    }

    @Step("Clicking on add to cart button")
    public HomePage addToCart(String productName )
    {
        LogsUtil.info("adding " + productName + " to the cart");
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+ productName +"']"));
        driver.element().clicking(addToCartButton);
        return this;
    }

    @Step("Clicking on remove button")
    public HomePage removeItem(String productName )
    {
        LogsUtil.info("removing " + productName + " from the cart");
        By removeItemButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+ productName +"']"));
        driver.element().clicking(removeItemButton);
        return this;
    }

    @Step("Clicking on the cart button")
    public CartPage clickCartIcon()
    {
        driver.element().clicking(cartIconLocator);
        return new CartPage(driver);
    }



    //Assertions

    @Step("Assert product added to cart")
    public HomePage assertProductAddedToCart(String productName)
    {

        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+ productName +"']"));
        String actualValue = driver.element().getText(addToCartButton);
        LogsUtil.info("Actual value: " + actualValue);
        driver.validate().assertEquals(actualValue,"Remove","Product not added to cart");
        LogsUtil.info(productName + " added to cart successfully");
        return this;
    }

    @Step("Assert product removed from cart")
    public HomePage assertProductRemovedFromCart(String productName)
    {

        By removeItemButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+ productName +"']"));
        String actualValue = driver.element().getText(removeItemButton);
        LogsUtil.info("Actual value: " + actualValue);
        driver.validate().assertEquals(actualValue,"Add to cart","Product added to cart");
        LogsUtil.info(productName + " removed from cart successfully");
        return this;
    }


}
