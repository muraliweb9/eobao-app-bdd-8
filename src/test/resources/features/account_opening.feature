Feature: Get the eOBAO account types available

  Scenario: Get all the available accounts
    Given As a new customer
    When I want to see all available accounts
    Then then see a successful response
    And I should get a total of 7 account types

  Scenario: Get available accounts for RETAIL customers
    Given As a new customer
    When I want to see available accounts for "RETAIL" customers
    Then then see a successful response
    And I should get a total of 4 account types

  Scenario: Get available accounts for COMMERCIAL customers
    Given As a new customer
    When I want to see available accounts for "COMMERCIAL" customers
    Then then see a successful response
    And I should get a total of 3 account types

  Scenario: Fail getting available accounts for UNKNOWN customers
    Given As a new customer
    When I want to see available accounts for "UNKNOWN" customers
    Then then see a failure response

  Scenario Outline: Get available accounts for different customers
    Given As a new customer
    When I want to see available accounts for "<customer-type>" customers
    Then then see a successful response
    And I should get a total of <account-count> account types
    Examples:
      | customer-type  | account-count  |
      | RETAIL         | 4              |
      | COMMERCIAL     | 3              |