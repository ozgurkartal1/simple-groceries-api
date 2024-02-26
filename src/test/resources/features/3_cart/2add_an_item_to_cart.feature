@cart
Feature: Add an item to cart

  Background:
    Given the user is in base uri

  Scenario: Test add an item to cart functionality
    When the user added two product with ids as 4643 and 4646
    Then the status code should be 201
    And the response item id can not be null or empty
    And the response item created must be true