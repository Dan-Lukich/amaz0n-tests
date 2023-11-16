Feature: Logout of Amazon Website

  Background:
    Given User is on Amazon homepage "https://www.amazon.com"
    Given The user is logged in, evidenced by the account greeting "Hello, Daniel"

  Scenario: Logout

    When User hovers over the Accounts & Lists tab and clicks Sign Out
    Then User should be able to logout successfully

