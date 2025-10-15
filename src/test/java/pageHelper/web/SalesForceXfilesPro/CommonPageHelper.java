package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageHelper.bddDriver;
import utils.PropertyReader;
import utils.xmlreader;

/**
 * CommonPageHelper - Reusable methods for SalesForce XfilesPro automation
 * This class contains common functionality that can be used across all step definition files
 */
public class CommonPageHelper {

    private webHelper webDriver;
    private xmlreader xml;
    private bddDriver DriverInstance;

    /**
     * Constructor for BDD context
     */
    public CommonPageHelper(bddDriver contextSteps) throws Exception {
        this.DriverInstance = contextSteps;
        this.webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        this.xml = new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
    }

    /**
     * Constructor with existing webHelper and xmlreader
     */
    public CommonPageHelper(webHelper webDriver, xmlreader xml) {
        this.webDriver = webDriver;
        this.xml = xml;
    }

    /**
     * Login into Salesforce using credentials from config.properties
     *
     * Reads XfilePro_user and XfilePro_password from configuration file
     * and performs login operation
     *
     * @throws Exception if login fails
     */
    @When("Login into Salesforce")
    public void loginIntoSalesforce() throws Exception {
        PropertyReader pr = new PropertyReader();

        // Read username and password from config.properties
        String username = pr.readproperty("XfilePro_user");
        String password = pr.readproperty("XfilePro_password");

        System.out.println("🔐 Logging into Salesforce...");
        System.out.println("   Username: " + username);

        // Enter username
        String fieldname = webDriver.Getattribute(
                webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}", "Username")),
                "for"
        );
        webDriver.SendKeys(
                webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}", fieldname)),
                username
        );

        // Enter password
        fieldname = webDriver.Getattribute(
                webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}", "Password")),
                "for"
        );
        webDriver.SendKeys(
                webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}", fieldname)),
                password
        );

        // Click login button
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("LoginButton")));

        System.out.println("✅ Login credentials submitted");

    }

    /**
     * Verify successful login to Salesforce
     * Checks if the App Launcher is visible after login
     *
     * @throws Exception if verification fails
     */
    @io.cucumber.java.en.Then("Verify successful login to Salesforce")
    public void verifySuccessfulLogin() throws Exception {
        // Wait for App Launcher to be visible (indicates successful login)
        webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
        System.out.println("✅ Successfully logged into Salesforce - App Launcher is visible");
    }

    /**
     * Verify App Launcher is visible
     *
     * @throws Exception if App Launcher is not visible
     */
    @io.cucumber.java.en.Then("Verify App Launcher is visible")
    public void verifyAppLauncherIsVisible() throws Exception {
        webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
        System.out.println("✅ App Launcher is visible");
    }

    /**
     * Navigate to a specific Nav item in Salesforce
     *
     * @param navItemName Name of the navigation item to click
     * @throws Exception if navigation fails
     */
//    public void goToNavItem(String navItemName) throws Exception {
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("NavItem").replace("{paramlink}", navItemName)));
//        Thread.sleep(5000);
//        System.out.println("✅ Navigated to: " + navItemName);
//    }

    /**
     * Open an app from the Salesforce App Launcher
     *
     * @param appName Name of the app to open
     * @throws Exception if app opening fails
     */
//    public void openAppFromLauncher(String appName) throws Exception {
//        webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("AppLauncher")));
//        Thread.sleep(300);
//
//        webDriver.verifyElementToBePresent(xml.getlocator("SearchApp"));
//        webDriver.SendKeys(webDriver.getwebelement(xml.getlocator("SearchApp")), appName);
//        Thread.sleep(300);
//
//        webDriver.verifyElementToBePresent(xml.getlocator("AppLink").replace("{paramlink}", appName));
//        webDriver.safeJavaScriptClick(webDriver.getwebelement(xml.getlocator("AppLink").replace("{paramlink}", appName)));
//        Thread.sleep(500);
//
//        System.out.println("✅ Opened app: " + appName);
//    }
//
//    /**
//     * Switch to XfilesPro iframe and click on side navigation link
//     *
//     * @param linkName Name of the side navigation link
//     * @throws Exception if navigation fails
//     */
//    public void clickSideNavLink(String linkName) throws Exception {
//        webDriver.SwitchToFrameByElement(webDriver.getwebelement(xml.getlocator("XfileProIframe")));
//        Thread.sleep(5000);
//        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("SideNavLink").replace("{paramlink}", linkName)));
//        System.out.println("✅ Clicked on side nav link: " + linkName);
//    }
}