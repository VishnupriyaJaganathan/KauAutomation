Feature: Google Search

  Scenario: Perform a Google Search for "Selenium with java"
    Given I navigate to the Google homepage
    When I search for "Selenium with java"
    Then the title of the results page should be "Selenium with java - Pesquisa Google"
