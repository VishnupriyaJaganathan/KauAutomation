package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.PageBase;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Model for KAU search results page.
 * Updated to target kau.se search result elements.
 */
public class GoogleResultsPage extends PageBase {

    private WebDriver driver;
    private WebDriverWait wait;

    private By searchResultText = By.xpath("//p[contains(text(), 'Din sökning på')]");
    private By resultLinks = By.cssSelector("h2.search-result-title a");

    public GoogleResultsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Wait for search results to load and return the results info text.
     */
    public String getSearchResultText() {
        WebElement resultInfo = wait.until(
            ExpectedConditions.visibilityOfElementLocated(searchResultText)
        );
        return resultInfo.getText();
    }

    /**
     * Check if search results contain the given keyword.
     */
    public boolean isResultPresent(String keyword) {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchResultText));
        List<WebElement> results = driver.findElements(resultLinks);
        for (WebElement result : results) {
            System.out.println("Result: " + result.getText());
            if (result.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
