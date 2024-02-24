@registration
Feature: Register a new client

  Background:
    Given the user is in base uri

  Scenario: Test register method functionality with valid credentials

    When the user sends POST request to registeration endpoint with valid "Ozgur" and "ozgurkartal4535435@gmail.com"
    Then the status code should be 201
    And the access token can not be null or empty

  Scenario: Test register method functionality with invalid credentials

    When the user sends POST request to registeration endpoint with parameters "Özgür" and "ozgurkartal5gmailom"
    Then the status code should be 400
    And the error message "Invalid or missing client email." should be displayed

  Scenario: Test register method fuctionality with invalid endpoint as "/api-client"
    When the user sends POST request to a invalid endpoint with followint details:
      | clientName  | Özgür                    |
      | clientEmail | ozgurkartal515@gmail.com |
    Then the status code should be 404