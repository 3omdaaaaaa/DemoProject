package org.example.cucumber.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features/HomePage.feature",
        glue = {"org.example.cucumber.stepdefinitions"},
        plugin = { "pretty",
                "html:test-outputs/target/cucumber-reports.html",},
        monochrome = true
)
public class HomePageTestRunner extends AbstractTestNGCucumberTests {
}
