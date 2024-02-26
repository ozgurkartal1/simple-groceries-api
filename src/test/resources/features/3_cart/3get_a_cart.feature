@cart
Feature: Get a cart

  Background:
    Given the user is in base uri

    Scenario: Test get a cart method functionality
      When the user wants to get a cart
      Then the status code should be 200
      And the response information must match with following cart details:
        | productId | quantity |
        | 4643      | 1        |
        | 4646      | 1        |

  Scenario: Test get a cart method functionality with invalid endpoint
    When the user wants to get a cart with wrong endpoint as "/cart"
    Then the status code should be 404
    And the error message "The resource could not be found. Check your endpoint and request method." should be displayed