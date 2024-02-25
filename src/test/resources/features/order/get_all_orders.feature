Feature: Get all orders

  Background:
    Given the user is in base uri
    And the user makes authontication with access token

  Scenario: Test get all orders method functionality
    When the user gets all orders
    Then the status code should be 200
    And the response order which is last must match with following details:
      | productId | quantity | customerName      | comment |
      | 4646      | 1        | Yusuf Bilal Çetin |         |