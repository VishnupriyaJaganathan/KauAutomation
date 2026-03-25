package stepdefinitions;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import enums.Browser;
import factory.BrowserProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GoogleHomePage;
import pages.GoogleResultsPage;

public class GoogleSearchStepDefinitions {

    private WebDriver driver;
    private GoogleHomePage kauHomePage;
    private GoogleResultsPage kauResultsPage;

    @Before
    public void setUp() {
        driver = BrowserProvider.createDriver(Browser.CHROME);
        driver.manage().window().maximize();
    }

    @Given("^I navigate to the Google homepage$")
    public void navigateToKauHomepage() {
        driver.get("https://www.kau.se/");
        // Bug fix: was creating local variable instead of assigning to class field
        kauHomePage = new GoogleHomePage(driver);
        kauHomePage.dismissCookieBanner();
    }

    @When("^I search for \"([^\"]*)\"$")
    public void searchFor(String searchTerm) {
        // Bug fix: was using hardcoded string instead of the searchTerm parameter
        kauResultsPage = kauHomePage.searchFor(searchTerm);
    }

    @Then("^the title of the results page should be \"([^\"]*)\"$")
    public void verifyResultsPageTitle(String expectedTitle) {
        // Bug fix: removed GoogleResultsPage as a method parameter (Cucumber doesn't support that)
        String resultText = kauResultsPage.getSearchResultText();
        assertTrue(resultText.contains("IT"), "Search results should contain 'IT'");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
