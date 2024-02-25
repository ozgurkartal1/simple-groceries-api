@cart
Feature: Create a new cart

  Background:
    Given the user is in base uri

  Scenario: Test create cart method functionality
    When the user sends POST request to create cart endpoint
    Then the status code should be 201
    And the response cartId can not be null or empty
    And the response created should be true

  Scenario: Test create cart method functionality with wrong endpoint
    When the user sends POST request to wrong endpoint as "abcd"
    Then the status code should be 404
    And the error message "The resource could not be found. Check your endpoint and request method." should be displayed