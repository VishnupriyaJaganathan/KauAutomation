package utils;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class with static helpers for locating and waiting on WebElements.
 *
 * BUG FIX: Selenium 4 requires Duration for WebDriverWait, not raw int seconds.
 * The old WebDriverWait(driver, int) constructor was removed in Selenium 4.
 */
public class SeleniumUtils {

    private static final int DEFAULT_WAIT_TIME = 10;

    private SeleniumUtils() {
        // Utility class — not instantiable
    }

    /**
     * Try to find an element by xpath. Returns null if not found.
     */
    public static WebElement findElement(WebDriver driver, String xpath) {
        try {
            return driver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            System.out.println("[SeleniumUtils] findElement failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Try to find multiple elements by xpath. Returns null if not found.
     */
    public static List<WebElement> findElements(WebDriver driver, String xpath) {
        try {
            return driver.findElements(By.xpath(xpath));
        } catch (Exception e) {
            System.out.println("[SeleniumUtils] findElements failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Wait for an element to be visible, with a custom timeout.
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, String xpath, int timeOutInSeconds) {
        // FIX: Duration.ofSeconds() required in Selenium 4
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println("[SeleniumUtils] waitForElementToBeVisible timed out: " + xpath);
            return null;
        }
    }

    /**
     * Wait for an element to be visible using the default timeout.
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, String xpath) {
        return waitForElementToBeVisible(driver, xpath, DEFAULT_WAIT_TIME);
    }

    /**
     * Wait for an element to be present in the DOM, with a custom timeout.
     */
    public static WebElement waitForElement(WebDriver driver, String xpath, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println("[SeleniumUtils] waitForElement timed out: " + xpath);
            return null;
        }
    }

    /**
     * Wait for an element to be present in the DOM using the default timeout.
     */
    public static WebElement waitForElement(WebDriver driver, String xpath) {
        return waitForElement(driver, xpath, DEFAULT_WAIT_TIME);
    }

    /**
     * Wait for at least one element to be present in the DOM, with a custom timeout.
     */
    public static List<WebElement> waitForElements(WebDriver driver, String xpath, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println("[SeleniumUtils] waitForElements timed out: " + xpath);
            return null;
        }
    }

    /**
     * Wait for at least one element to be present in the DOM using the default timeout.
     */
    public static List<WebElement> waitForElements(WebDriver driver, String xpath) {
        return waitForElements(driver, xpath, DEFAULT_WAIT_TIME);
    }

    /**
     * Wait for an element to be clickable, with a custom timeout.
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, String xpath, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println("[SeleniumUtils] waitForElementToBeClickable timed out: " + xpath);
            return null;
        }
    }

    /**
     * Wait for an element to be clickable using the default timeout.
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, String xpath) {
        return waitForElementToBeClickable(driver, xpath, DEFAULT_WAIT_TIME);
    }

    /**
     * Wait for an element to become invisible, with a custom timeout.
     */
    public static boolean waitForElementToBeInvisible(WebDriver driver, String xpath, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println("[SeleniumUtils] waitForElementToBeInvisible timed out: " + xpath);
            return false;
        }
    }

    /**
     * Wait for an element to become invisible using the default timeout.
     */
    public static boolean waitForElementToBeInvisible(WebDriver driver, String xpath) {
        return waitForElementToBeInvisible(driver, xpath, DEFAULT_WAIT_TIME);
    }

    /**
     * Wait for an arbitrary expected condition, with a custom timeout.
     */
    public static <T> T waitUntil(WebDriver driver, Function<? super WebDriver, T> condition, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(condition);
    }

    /**
     * Wait for an arbitrary expected condition using the default timeout.
     */
    public static <T> T waitUntil(WebDriver driver, Function<? super WebDriver, T> condition) {
        return waitUntil(driver, condition, DEFAULT_WAIT_TIME);
    }
}
