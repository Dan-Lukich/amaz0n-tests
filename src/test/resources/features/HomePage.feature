Feature: Login to Amazon Website

  Background:
    Given User is on Amazon homepage "https://www.amazon.com"

  @ValidCredentials
  Scenario: Login with valid credentials

    When LastPass enters username and password
    Then User should be able to login successfully and new page open

