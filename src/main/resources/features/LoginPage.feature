Feature: Login functionality

  @ValidLogin
  Scenario: User can log in with valid credentials
    Given the user is on the login page
    When the user enters a valid username and password
    And clicks the login button
    Then the user should be logged in successfully
