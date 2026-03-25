package enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import factory.BrowserProvider;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This enum defines how to initialize each browser driver.
 * Updated for Selenium 4 - removed deprecated DesiredCapabilities.
 * Headless mode is enabled automatically when running in CI (no display).
 */
public enum Browser {
    FIREFOX {
        @Override
        public WebDriver initialize() {
            synchronized (BrowserProvider.class) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (isHeadless()) {
                    options.addArguments("--headless");
                }
                return new FirefoxDriver(options);
            }
        }
    },

    CHROME {
        @Override
        public WebDriver initialize() {
            synchronized (BrowserProvider.class) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (isHeadless()) {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                }
                return new ChromeDriver(options);
            }
        }
    };

    /**
     * Initialize the browser driver.
     */
    public abstract WebDriver initialize();

    /**
     * Returns true if running in CI environment (no display available).
     */
    private static boolean isHeadless() {
        return System.getenv("CI") != null || System.getProperty("headless", "false").equals("true");
    }

    @Override
    public String toString() {
        switch (this) {
            case FIREFOX: return "FIREFOX";
            case CHROME:  return "CHROME";
            default: throw new IllegalArgumentException();
        }
    }
}
