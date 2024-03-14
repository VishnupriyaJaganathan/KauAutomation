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
    private GoogleHomePage googleHomePage;
    public GoogleResultsPage googleResultsPage;

    @Before
    public void setUp() {
    		driver = BrowserProvider.createDriver(Browser.CHROME);
    		driver.manage().window().maximize();
        // Put your setup code here if needed
    }

    @Given("^I navigate to the Google homepage$")
    public void navigateToGoogleHomepage() {
driver.get("http://www.google.com");
		
		GoogleHomePage googleHomePage = new GoogleHomePage(driver);
		
    }

    @When("^I search for \"([^\"]*)\"$")
    public void searchFor(String searchTerm) {
    	GoogleResultsPage googleResultsPage = googleHomePage.searchFor("Selenium with java");
        // Put your code to perform the search here
    	
    }


    @Then("^the title of the results page should be \"([^\"]*)\"$")
    public void verifyResultsPageTitle(String expectedTitle,GoogleResultsPage googleResultsPage) {
    	assertTrue(googleResultsPage.getTitle().equals("Selenium with java - Pesquisa Google"));
		assertTrue(googleResultsPage.isResultPresent("Selenium Tutorial"));
    }

    @After
    public void tearDown() {
    	driver.quit();
		driver = null;
    }
}
