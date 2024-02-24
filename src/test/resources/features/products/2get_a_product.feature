@product
Feature: Get a product

  Scenario: Test Get a product method funtionality with valid product id
    Given the user is in base uri
    When the user sends request to get a product endpoint with specified product id
    Then the status code should be 200
    And the product should match with is written json file

  Scenario Outline: Test Get a product method funtionality with invalid product id
    Given the user is in base uri
    When the user sends request to get a product endpoint with invalid product id as "<id>"
    Then the status code should be <status_code>
    And the error message "<error_message>" should be displayed
    Examples:
      | id    | status_code | error_message                    |
      | Abcd  | 400         | The product id must be a number. |
      | 10256 | 404         | No product with id 10256.        |



