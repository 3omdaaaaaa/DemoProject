Feature: Home page cart functionality

  @AddToCart
  Scenario: User can add items to the cart
    Given the user is on the home page
    When the user adds one or more items to the cart
    Then the items should appear in the cart successfully

  @RemoveAndAddNewItem
  Scenario: User can remove an item from the cart and add another one
    Given the user has already added an item to the cart
    When the user clicks the remove button for that item
    And navigates to the cart page
    Then the item should no longer be present in the cart
    When the user returns to the home page
    And adds another item
    Then the new item should appear in the cart
