package com.swaglabs.utils;

import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {

    public static CustomSoftAssertion softAssertion = new CustomSoftAssertion();

    public static void customAssertAll(ITestResult result) {
        try {
            softAssertion.assertAll("custom soft assertion");
        } catch (AssertionError e) {
            System.out.println("custom soft Assertion Failed: " + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);

        }
        finally {
            resetSoftAssertion();
        }

    }

    private static void resetSoftAssertion()
    {
        softAssertion = new CustomSoftAssertion();
    }
}

