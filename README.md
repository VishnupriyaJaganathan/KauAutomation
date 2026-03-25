# kau-automation

A Java Selenium test framework targeting **[Karlstad University (KAU)](https://www.kau.se/)**, built with the **Factory Pattern**, **Page Object Model (POM)**, **TestNG**, and **Cucumber BDD**.

---

## Stack

| Tool | Version |
|---|---|
| Java | 11 |
| Selenium | 4.18.1 |
| WebDriverManager | 5.7.0 |
| TestNG | 7.9.0 |
| Cucumber | 7.15.0 |
| Maven | 3.x |

---

## Running Tests

### Locally (headed browser)

```bash
mvn test
```

### Locally (headless)

```bash
mvn test -Dheadless=true
```

### CI (GitHub Actions)

Tests run automatically on every push and pull request via `.github/workflows/selenium-ci.yml`. Headless mode is enabled automatically when the `CI` environment variable is set.

---

## Project Structure

```
src/
  main/java/
    enums/Browser.java          # CHROME / FIREFOX factory enum
    factory/BrowserProvider.java # createDriver() entry point
    factory/PageBase.java        # Base class for all page objects
    utils/SeleniumUtils.java     # Wait/find helpers
  test/java/
    pages/KauHomePage.java       # kau.se page object
    pages/KauDashboardPage.java  # Mitt KAU dashboard page object
    tests/KauTests.java          # TestNG tests
    stepdefinitions/
      KauStepDefinitions.java    # Cucumber step definitions
      TestRunner.java            # JUnit/Cucumber runner
  test/resources/
    Features/Kau.feature         # BDD scenarios
testng.xml                       # TestNG suite config
```

---

## Design Patterns

### Factory Pattern — `BrowserProvider`

Instantiate a driver without coupling tests to browser-specific code:

```java
WebDriver driver = BrowserProvider.createDriver(Browser.CHROME);
```

### Page Object Model — no Page Factory

Page objects extend `PageBase` and store element locators as XPath strings. `SeleniumUtils` provides explicit waits rather than relying on `@FindBy` annotations, keeping the framework flexible and avoiding Page Factory initialisation bugs.

```java
public class KauHomePage extends PageBase {
    private final String lnkMittKau = "//span[text()='Mitt Kau']";

    public void clickMittKau() {
        SeleniumUtils.waitForElementToBeClickable(driver, lnkMittKau).click();
    }
}
```

### Element naming convention

| Type | Prefix | Example |
|---|---|---|
| Text box | `txt` | `txtUsername` |
| Button | `btn` | `btnSubmit` |
| Link | `lnk` | `lnkMittKau` |
| Header | `hdr` | `hdrWelcome` |
| Label | `lbl` | `lblError` |
| Dropdown | `dd` | `ddLanguage` |
| Checkbox | `cb` | `cbRememberMe` |

---

## Bug Fixes Applied

| File | Issue | Fix |
|---|---|---|
| `SeleniumUtils.java` | `WebDriverWait(driver, int)` removed in Selenium 4 | Changed to `WebDriverWait(driver, Duration.ofSeconds(n))` |
| `PageBase.java` | `driver` field was `private` — subclasses couldn't access it | Changed to `protected final` |
| `GoogleHomePage.java` | Redeclared `driver` field — shadowed `PageBase.driver`, always null | Removed duplicate field |
| `GoogleHomePage.java` | Used `@FindBy` without calling `PageFactory.initElements()` | Replaced with `SeleniumUtils` XPath helpers |
| `GoogleSearchStepDefinitions.java` | `@Then` method had an illegal extra `GoogleResultsPage` parameter | Removed extra parameter |
| `GoogleSearchStepDefinitions.java` | `googleHomePage` was `null` when `@Given` ran (assigned after use) | Moved initialisation to `@Before` |
| `GoogleSearchTests.java` | `Thread.sleep(5000)` for page load | Removed; waits handled by `SeleniumUtils` |
| `GoogleSearchTests.java` | `@BeforeSuite/@AfterSuite` — driver shared across entire suite | Changed to `@BeforeClass/@AfterClass` |
