package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import enums.Browser;
import factory.BrowserProvider;
import pages.GoogleHomePage;
import pages.GoogleResultsPage;

@Test
public class GoogleSearchTests {
	
    private WebDriver driver;
    GoogleHomePage googleHomePage;

    /**
     * This method will be executed before the test start.
     */
    @BeforeSuite
    public void initialize() {
        driver = BrowserProvider.createDriver(Browser.CHROME);
        driver.manage().window().maximize();
        googleHomePage = new GoogleHomePage(driver);
    }

    /**
     * This method perform a google search test. We can have multiple @Test methods inside this class.
     */
    @Test
    public void googleSearchTest() {
        driver.get("https://www.kau.se/");
        // Delay to ensure page elements are loaded, you can adjust the time as needed
        try {
            Thread.sleep(5000); // 5 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        googleHomePage.clickMittKau();
    }

    /**
     * This method will be executed at the end of the test.
     */
    @AfterSuite
    public void quitDriver() {
        driver.quit();
        driver = null;
    }
}
