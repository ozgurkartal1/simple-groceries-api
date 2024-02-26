@product
Feature: Get all products

  Background:
    Given the user is in base uri

  Scenario: Test get all products method functionality
    When the user sends GET request to get all products endpoint
    Then the status code should be 200
    And the array of products should be displayed

  Scenario Outline: Test get all products method functionality with some query
    When the user sends GET request to get all products endpoint with "<query>" key and "<value>"
    Then the status code should be 200
    And the response array of products should includes specified "<query>" and "<value>"
    Examples:
      | query     | value  |
      | available | true   |
      | available | false  |
      | category  | coffee |
      | category  | candy  |

