@cart
Feature: Get cart items

  Background:
    Given the user is in base uri

  Scenario: Test get cart items method functionality
    When the user gets cart items
    Then the status code should be 200
    And the response information must match with following item details:
      | productId | quantity |
      | 4643      | 1        |
      | 4646      | 1        |

  Scenario Outline: Test get cart items method functionality with invalid cartId
    When the user get cart items with invalid cart id as "<cart_id>"
    Then the status code should be 404
    And the error message "<error message> <cart_id>." should be displayed
    Examples:
      | cart_id   | error message   |
      | ABCD      | No cart with id |
      | 105678493 | No cart with id |
