Feature: testing the login functionality

  Scenario: User could login using valid username and password
    Given User navigate to the login page
    When User entered a valid username and password 
    And User click on login button
    Then User login successfully
