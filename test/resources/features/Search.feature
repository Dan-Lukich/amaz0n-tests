Feature: Overall pick is provided with product searches

  Background:
    Given User is on Amazon homepage "https://www.amazon.com"

  Scenario Outline: The search page should display an Overall Pick badge on the first result

    When User types "<search_term>" into the search bar
    Then The Overall Pick badge is visible on the first result

    Examples:
      | search term |
      | guitar      |
      | basketball  |
      | baseball    |