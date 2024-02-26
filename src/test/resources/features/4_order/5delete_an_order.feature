@order
Feature: Delete an order

  Background:
    Given the user is in base uri
    And the user makes authontication with access token

  Scenario: Test delete an order method functionality
    When the user deletes a specific order
    Then the status code should be 204
    And Verify that the specified order is deleted