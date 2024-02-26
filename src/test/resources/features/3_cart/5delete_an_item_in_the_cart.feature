@cart
Feature: Delete an item in the cart

  Scenario: Test delete an item in the cart method functionality
    Given the user is in base uri
    When the user deletes an specific item
    Then the status code should be 204
    And Verify that the specified item is deleted