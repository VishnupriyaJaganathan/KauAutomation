package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.PageBase;

import java.time.Duration;

/**
 * Page Object Model for the KAU homepage (https://www.kau.se).
 */
public class GoogleHomePage extends PageBase {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "button.cm-btn-success")
    public WebElement acceptCookiesBtn;

    @FindBy(xpath = "//span[text()='Mitt Kau']")
    public WebElement mittKau;

    @FindBy(css = "button.js-search-modal-toggle")
    public WebElement searchToggle;

    @FindBy(css = "input[type='search']")
    public WebElement searchInput;

    public GoogleHomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Dismiss the Klaro cookie consent overlay if present.
     */
    public void dismissCookieBanner() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            acceptCookiesBtn.click();
            wait.until(ExpectedConditions.invisibilityOf(acceptCookiesBtn));
        } catch (Exception e) {
            System.out.println("Cookie banner not present or already dismissed.");
        }
    }

    /**
     * Click the Mitt Kau navigation link.
     */
    public void clickMittKau() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(mittKau));
            mittKau.click();
        } catch (Exception e) {
            System.out.println("MittKau element not clickable: " + e.getMessage());
        }
    }

    /**
     * Perform a search on kau.se and return the results page.
     */
    public GoogleResultsPage searchFor(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(searchToggle));
        searchToggle.click();
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(query);
        searchInput.sendKeys(org.openqa.selenium.Keys.ENTER);
        return new GoogleResultsPage(driver);
    }
}

