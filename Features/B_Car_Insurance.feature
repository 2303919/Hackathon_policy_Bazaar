Feature: car Insurance

  Scenario: user enters invalid data
    Given user should be on the car insurance page
    When user enters all car details
    And user provides invalid data for email
    Then error message should displayed

  Scenario: user enters valid data and write car quote
    Given user should be on the car insurance page
    When user enters all car details
    And user provides valid data and details
    Then user should receive a car insurance quote
