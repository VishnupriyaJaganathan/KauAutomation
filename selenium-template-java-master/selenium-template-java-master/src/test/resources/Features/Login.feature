Feature: KAU Website Search

  Scenario: Perform a search on the KAU homepage
    Given I navigate to the Google homepage
    When I search for "IT"
    Then the title of the results page should be "IT"
```

Path:
```
src/test/resources/Features/Login.feature
