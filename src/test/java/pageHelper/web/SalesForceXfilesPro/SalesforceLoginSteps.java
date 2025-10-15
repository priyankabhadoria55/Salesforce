package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageHelper.bddDriver;
import pages.SalesforceLoginPage;
import utils.PropertyReader;
import utils.Log;

/**
 * Step Definition class for Salesforce Login functionality
 * Contains all step definitions for Salesforce login scenarios
 */
public class SalesforceLoginSteps {

    private webHelper webDriver;
    private bddDriver DriverInstance;
    private SalesforceLoginPage loginPage;
    private PropertyReader propertyReader;
    
    // Salesforce login URL and credentials
    private final String SALESFORCE_URL = "https://qautomation.my.salesforce.com/";
    private final String USERNAME = "priyanka.bhadoria@xfp.com";
    private final String PASSWORD = "XfilesPro@123";

    /**
     * Constructor for BDD context
     */
    public SalesforceLoginSteps(bddDriver contextSteps) throws Exception {
        this.DriverInstance = contextSteps;
        this.webDriver = new baseDriverHelper(bddDriver.getWebDriver());
        this.propertyReader = new PropertyReader();
        this.loginPage = new SalesforceLoginPage(bddDriver.getWebDriver());
        Log.info("SalesforceLoginSteps initialized successfully");
    }

    @Given("I navigate to Salesforce login page")
    public void i_navigate_to_salesforce_login_page() {
        try {
            Log.info("Navigating to Salesforce login page: " + SALESFORCE_URL);
            loginPage.navigateToLoginPage(SALESFORCE_URL);
            loginPage.waitForPageLoad();
            Log.info("Successfully navigated to Salesforce login page");
        } catch (Exception e) {
            Log.error("Failed to navigate to Salesforce login page: " + e.getMessage());
            Assert.fail("Failed to navigate to Salesforce login page: " + e.getMessage());
        }
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        try {
            Log.info("Entering username: " + username);
            loginPage.enterUsername(username);
            Log.info("Username entered successfully");
        } catch (Exception e) {
            Log.error("Failed to enter username: " + e.getMessage());
            Assert.fail("Failed to enter username: " + e.getMessage());
        }
    }

    @And("I enter password {string}")
    public void i_enter_password(String password) {
        try {
            Log.info("Entering password");
            loginPage.enterPassword(password);
            Log.info("Password entered successfully");
        } catch (Exception e) {
            Log.error("Failed to enter password: " + e.getMessage());
            Assert.fail("Failed to enter password: " + e.getMessage());
        }
    }

    @And("I click on Login button")
    public void i_click_on_login_button() {
        try {
            Log.info("Clicking on Login button");
            loginPage.clickLoginButton();
            Log.info("Login button clicked successfully");
            
            // Wait a moment for the login process
            Thread.sleep(3000);
        } catch (Exception e) {
            Log.error("Failed to click login button: " + e.getMessage());
            Assert.fail("Failed to click login button: " + e.getMessage());
        }
    }

    @Then("I should be successfully logged into Salesforce")
    public void i_should_be_successfully_logged_into_salesforce() {
        try {
            Log.info("Verifying successful login to Salesforce");
            
            // Wait for login to complete
            Thread.sleep(5000);
            
            boolean isLoginSuccessful = loginPage.isLoginSuccessful();
            Assert.assertTrue(isLoginSuccessful, "Login was not successful - Dashboard elements not found");
            
            Log.info("Successfully verified login to Salesforce");
        } catch (Exception e) {
            Log.error("Login verification failed: " + e.getMessage());
            Assert.fail("Login verification failed: " + e.getMessage());
        }
    }

    @And("I should see the Salesforce dashboard")
    public void i_should_see_the_salesforce_dashboard() {
        try {
            Log.info("Verifying Salesforce dashboard is visible");
            
            String currentUrl = bddDriver.getWebDriver().getCurrentUrl();
            String pageTitle = loginPage.getPageTitle();
            
            Log.info("Current URL: " + currentUrl);
            Log.info("Page Title: " + pageTitle);
            
            // Verify we're not on the login page anymore
            Assert.assertFalse(loginPage.isOnLoginPage(), "Still on login page - login may have failed");
            
            // Verify we're on a Salesforce page
            Assert.assertTrue(currentUrl.contains("salesforce.com"), "Not on Salesforce domain");
            
            Log.info("Successfully verified Salesforce dashboard");
        } catch (Exception e) {
            Log.error("Dashboard verification failed: " + e.getMessage());
            Assert.fail("Dashboard verification failed: " + e.getMessage());
        }
    }

    @Then("I should see login error message")
    public void i_should_see_login_error_message() {
        try {
            Log.info("Verifying login error message is displayed");
            
            // Wait for error message to appear
            Thread.sleep(2000);
            
            boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
            Assert.assertTrue(isErrorDisplayed, "Login error message is not displayed");
            
            String errorMessage = loginPage.getErrorMessage();
            Log.info("Error message displayed: " + errorMessage);
            
            Log.info("Successfully verified login error message");
        } catch (Exception e) {
            Log.error("Error message verification failed: " + e.getMessage());
            Assert.fail("Error message verification failed: " + e.getMessage());
        }
    }

    @And("I should remain on login page")
    public void i_should_remain_on_login_page() {
        try {
            Log.info("Verifying user remains on login page");
            
            boolean isOnLoginPage = loginPage.isOnLoginPage();
            Assert.assertTrue(isOnLoginPage, "User is not on login page");
            
            String currentUrl = bddDriver.getWebDriver().getCurrentUrl();
            Log.info("Current URL: " + currentUrl);
            
            Log.info("Successfully verified user remains on login page");
        } catch (Exception e) {
            Log.error("Login page verification failed: " + e.getMessage());
            Assert.fail("Login page verification failed: " + e.getMessage());
        }
    }

    @When("I leave username field empty")
    public void i_leave_username_field_empty() {
        try {
            Log.info("Leaving username field empty");
            loginPage.clearUsername();
            Log.info("Username field cleared/left empty");
        } catch (Exception e) {
            Log.error("Failed to clear username field: " + e.getMessage());
            Assert.fail("Failed to clear username field: " + e.getMessage());
        }
    }

    @And("I leave password field empty")
    public void i_leave_password_field_empty() {
        try {
            Log.info("Leaving password field empty");
            loginPage.clearPassword();
            Log.info("Password field cleared/left empty");
        } catch (Exception e) {
            Log.error("Failed to clear password field: " + e.getMessage());
            Assert.fail("Failed to clear password field: " + e.getMessage());
        }
    }

    @Then("I should see validation error messages")
    public void i_should_see_validation_error_messages() {
        try {
            Log.info("Verifying validation error messages are displayed");
            
            // Wait for validation messages to appear
            Thread.sleep(2000);
            
            boolean areValidationErrorsDisplayed = loginPage.areValidationErrorsDisplayed();
            Assert.assertTrue(areValidationErrorsDisplayed, "Validation error messages are not displayed");
            
            Log.info("Successfully verified validation error messages");
        } catch (Exception e) {
            Log.error("Validation error verification failed: " + e.getMessage());
            Assert.fail("Validation error verification failed: " + e.getMessage());
        }
    }

    /**
     * Helper method to perform complete login with default credentials
     */
    public void performDefaultLogin() {
        try {
            Log.info("Performing login with default credentials");
            i_navigate_to_salesforce_login_page();
            i_enter_username(USERNAME);
            i_enter_password(PASSWORD);
            i_click_on_login_button();
            i_should_be_successfully_logged_into_salesforce();
            Log.info("Default login completed successfully");
        } catch (Exception e) {
            Log.error("Default login failed: " + e.getMessage());
            throw new RuntimeException("Default login failed: " + e.getMessage());
        }
    }

    /**
     * Helper method to perform login with custom credentials
     */
    public void performLogin(String username, String password) {
        try {
            Log.info("Performing login with custom credentials");
            i_navigate_to_salesforce_login_page();
            i_enter_username(username);
            i_enter_password(password);
            i_click_on_login_button();
            Log.info("Custom login completed");
        } catch (Exception e) {
            Log.error("Custom login failed: " + e.getMessage());
            throw new RuntimeException("Custom login failed: " + e.getMessage());
        }
    }
}