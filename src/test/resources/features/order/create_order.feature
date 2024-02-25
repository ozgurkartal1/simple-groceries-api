Feature: Create order

  Background:
    Given the user is in base uri
    And the user makes authontication with access token

  Scenario: Test create order method functionality
    When the user creates a order with customer name as "Yusuf Bilal Çetin"
    Then the status code should be 201
    And the response order id can not be empty or null
    And the response order created must be true