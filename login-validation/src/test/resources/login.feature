Feature: Login
  Scenario: Valid login
    Given I am a user of amazon.com
    When I log in using valid credentials with "<username>" and "<password>"
    Then I should be logged in

  Scenario: Invalid login
    Given I am a user of amazon.com
    When I log in using invalid credentials with "<username>" and "<password>"
    Then I should not be logged in
