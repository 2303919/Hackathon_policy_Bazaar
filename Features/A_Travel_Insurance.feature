Feature: Travel Insurance for student

  Scenario: Insurance plans for students
    Given User should load the website
    When user fills travel details
    Then Student plans should be displayed
