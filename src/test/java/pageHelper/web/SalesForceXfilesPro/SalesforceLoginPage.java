package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;
import utils.xmlreader;

/**
 * Salesforce Login Page - Page Object Model
 * 
 * This class contains all methods related to Salesforce login functionality
 * following the Page Object Model design pattern for better maintainability
 * 
 * @author Automation Team
 * @version 1.0
 */
public class SalesforceLoginPage {

    private webHelper webDriver;
    private xmlreader xml;
    private PropertyReader propertyReader;

    /**
     * Constructor to initialize webDriver and load locators
     * 
     * @param driver WebDriver instance
     * @throws Exception if initialization fails
     */
    public SalesforceLoginPage(WebDriver driver) throws Exception {
        this.webDriver = new baseDriverHelper(driver);
        this.xml = new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
        this.propertyReader = new PropertyReader();
    }

    /**
     * Enter username in the login form
     * 
     * @param username Username to enter
     * @throws Exception if element is not found
     */
    public void enterUsername(String username) throws Exception {
        String fieldname = webDriver.Getattribute(
                webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}", "Username")),
                "for"
        );
        webDriver.SendKeys(
                webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}", fieldname)),
                username
        );
        System.out.println("✅ Username entered: " + username);
    }

    /**
     * Enter password in the login form
     * 
     * @param password Password to enter
     * @throws Exception if element is not found
     */
    public void enterPassword(String password) throws Exception {
        String fieldname = webDriver.Getattribute(
                webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}", "Password")),
                "for"
        );
        webDriver.SendKeys(
                webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}", fieldname)),
                password
        );
        System.out.println("✅ Password entered");
    }

    /**
     * Click the Login button
     * 
     * @throws Exception if button is not clickable
     */
    public void clickLoginButton() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("LoginButton")));
        System.out.println("✅ Login button clicked");
    }

    /**
     * Perform complete login with credentials from config.properties
     * This is the main reusable login method
     * 
     * @throws Exception if login fails
     */
    public void loginWithConfigCredentials() throws Exception {
        String username = propertyReader.readproperty("XfilePro_user");
        String password = propertyReader.readproperty("XfilePro_password");

        System.out.println("🔐 Starting Salesforce Login...");
        System.out.println("   URL: " + propertyReader.readproperty("xFilesPro_application_url"));
        System.out.println("   Username: " + username);

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        System.out.println("✅ Login process completed");
    }

    /**
     * Perform login with custom credentials
     * 
     * @param username Custom username
     * @param password Custom password
     * @throws Exception if login fails
     */
    public void loginWithCustomCredentials(String username, String password) throws Exception {
        System.out.println("🔐 Starting Salesforce Login with custom credentials...");
        System.out.println("   Username: " + username);

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        System.out.println("✅ Login process completed");
    }

    /**
     * Verify successful login by checking if App Launcher is visible
     * 
     * @return true if login successful, false otherwise
     * @throws Exception if verification fails
     */
    public boolean isLoginSuccessful() throws Exception {
        try {
            webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
            System.out.println("✅ Login verification successful - App Launcher is visible");
            return true;
        } catch (Exception e) {
            System.out.println("❌ Login verification failed - App Launcher not found");
            return false;
        }
    }

    /**
     * Verify if App Launcher button is visible on the page
     * 
     * @throws Exception if App Launcher is not visible
     */
    public void verifyAppLauncherVisible() throws Exception {
        webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
        System.out.println("✅ App Launcher is visible");
    }

    /**
     * Get the current page URL
     * 
     * @return Current page URL
     */
    public String getCurrentUrl() {
        return webDriver.CurrentURL();
    }

    /**
     * Get the current page title
     * 
     * @return Current page title
     */
    public String getPageTitle() {
        return webDriver.GetTitle();
    }
}
