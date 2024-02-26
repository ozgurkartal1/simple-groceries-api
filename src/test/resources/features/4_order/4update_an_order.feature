@order
Feature: Update an order

  Background:
    Given the user is in base uri
    And the user makes authontication with access token

  Scenario: Test update an order method functionality
    When the user updates an order with new customer name as "Özgür Kartal"
    Then the status code should be 204
    And Verify that the customer name in specified order is updated with name "Özgür Kartal"