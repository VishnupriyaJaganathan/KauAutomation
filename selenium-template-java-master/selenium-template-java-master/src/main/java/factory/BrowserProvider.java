package factory;

import org.openqa.selenium.WebDriver;

import enums.Browser;

/**
 * Class responsible for handling WebDriver creation.
 * Updated for Selenium 4 - removed deprecated DesiredCapabilities.
 */
public class BrowserProvider {

    /**
     * Create a driver for the given browser.
     *
     * @param browser
     * @return
     */
    public static WebDriver createDriver(Browser browser) {
        return browser.initialize();
    }
}
