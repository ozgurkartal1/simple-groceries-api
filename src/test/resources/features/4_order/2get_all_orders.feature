@order
Feature: Get all orders

  Background:
    Given the user is in base uri
    And the user makes authontication with access token

  Scenario: Test get all orders method functionality
    When the user gets all orders
    Then the status code should be 200
    And the response orders must be a array of order
