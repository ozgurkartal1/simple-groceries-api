@order
Feature: Get a single order

  Background:
    Given the user is in base uri
    And the user makes authontication with access token

  Scenario: Test get a single order method functionality
    When the user get a single order
    Then the status code should be 200
    And the response order must match with following order information:
      | productId | quantity | customerName      |
      | 4646      | 1        | Yusuf Bilal Çetin |