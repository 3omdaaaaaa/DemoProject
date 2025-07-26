package org.example.cucumber.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features/Checkout.feature",
        glue = {"org.example.cucumber.stepdefinitions"},
        plugin = { "pretty",
                    "html:test-outputs/target/cucumber-reports.html",
                    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)
public class CheckoutTestRunner extends AbstractTestNGCucumberTests {
}
