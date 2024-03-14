package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import factory.PageBase;

public class GoogleHomePage extends PageBase {
    public GoogleHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	protected WebDriver driver;
	
    @FindBy(xpath = "//header/div[2]/div[1]//a/span")
    public WebElement pageHeader;

    @FindBy(xpath = "//span[text()='Mitt Kau']")
    public WebElement MittKau;

    @FindBy(xpath = "//button[text()='Logga in']")
    public WebElement Loggain;

    @FindBy(xpath = "//input[@id='username']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;

    public void clickMittKau() {
        if (MittKau != null) {
            MittKau.click();
        } else {
            System.out.println("MittKau element is null. Cannot perform click.");
        }
    }

    public void clickLoggain() {
        Loggain.click();
    }

    public void enterUsername() {
        username.sendKeys("");
    }

    public void enterPassword() {
        password.sendKeys("");
    }

    public GoogleResultsPage searchFor(String query) {
        // Implement search functionality here
        return new GoogleResultsPage(driver);
    }
}
