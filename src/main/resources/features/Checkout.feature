Feature: Checkout process functionality

  Scenario: User completes the checkout process successfully
    Given User is on the cart page with items added
    When The user clicks on the "Checkout" button
    And User fills in personal information
    And User clicks on the "Continue" button
    And User confirms the order by clicking the "Finish" button
    Then A confirmation message is displayed saying "Thank you for your order!"
