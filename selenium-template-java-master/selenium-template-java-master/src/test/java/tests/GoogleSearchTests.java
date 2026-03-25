package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import enums.Browser;
import factory.BrowserProvider;
import pages.GoogleHomePage;
import pages.GoogleResultsPage;

/**
 * TestNG tests for the KAU website.
 * Updated to target kau.se, removed Thread.sleep, fixed bugs.
 */
public class GoogleSearchTests {

    private WebDriver driver;
    private GoogleHomePage kauHomePage;

    @BeforeSuite
    public void initialize() {
        driver = BrowserProvider.createDriver(Browser.CHROME);
        driver.manage().window().maximize();
        driver.get("https://www.kau.se/");
        kauHomePage = new GoogleHomePage(driver);
        kauHomePage.dismissCookieBanner();
    }

    /**
     * Test 1: Verify the KAU homepage title.
     */
    @Test(priority = 1)
    public void verifyHomepageTitle() {
        Assert.assertTrue(
            driver.getTitle().contains("Karlstads universitet"),
            "Homepage title should contain 'Karlstads universitet'"
        );
    }

    /**
     * Test 2: Click Mitt Kau link and verify navigation.
     */
    @Test(priority = 2)
    public void testMittKauNavigation() {
        driver.get("https://www.kau.se/");
        kauHomePage.dismissCookieBanner();
        kauHomePage.clickMittKau();
        Assert.assertTrue(
            driver.getCurrentUrl().contains("kau.se"),
            "Should navigate within kau.se after clicking Mitt Kau"
        );
    }

    /**
     * Test 3: Search for "IT" and verify results appear.
     */
    @Test(priority = 3)
    public void testSearch() {
        driver.get("https://www.kau.se/");
        kauHomePage = new GoogleHomePage(driver);
        kauHomePage.dismissCookieBanner();
        GoogleResultsPage resultsPage = kauHomePage.searchFor("IT");
        String resultText = resultsPage.getSearchResultText();
        Assert.assertTrue(
            resultText.contains("IT"),
            "Search results page should mention 'IT'"
        );
    }

    @AfterSuite
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
```

Path:
```
src/test/java/tests/GoogleSearchTests.java
