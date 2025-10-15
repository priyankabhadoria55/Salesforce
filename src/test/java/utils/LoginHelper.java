package utils;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

/**
 * Helper class for Salesforce login operations
 * This class provides reusable methods for login functionality
 */
public class LoginHelper {
    
    private WebDriver driver;
    private LoginPage loginPage;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public LoginHelper(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }
    
    /**
     * Login to Salesforce with default credentials from config file
     * @return true if login successful, false otherwise
     */
    public boolean loginWithDefaultCredentials() {
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        return performLogin(username, password);
    }
    
    /**
     * Login to Salesforce with provided credentials
     * @param username The username to login with
     * @param password The password to login with
     * @return true if login successful, false otherwise
     */
    public boolean loginWithCredentials(String username, String password) {
        return performLogin(username, password);
    }
    
    /**
     * Perform the actual login operation
     * @param username The username to login with
     * @param password The password to login with
     * @return true if login successful, false otherwise
     */
    private boolean performLogin(String username, String password) {
        try {
            // Navigate to login page
            loginPage.navigateToLoginPage();
            
            // Enter credentials
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            
            // Click login button
            loginPage.clickLoginButton();
            
            // Wait for page load
            Thread.sleep(3000);
            
            // Check if login was successful
            return loginPage.isLoginSuccessful();
            
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Quick login method - combines navigation and login
     * Uses credentials from config file
     */
    public void quickLogin() {
        loginPage.navigateToLoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
    }
    
    /**
     * Method to login to Salesforce application
     * This is the main method requested by the user
     * URL: https://qautomation.my.salesforce.com/
     * Username: priyanka.bhadoria@xfp.com
     * Password: XfilesPro@123
     */
    public void automateLogin() {
        System.out.println("========================================");
        System.out.println("Starting Salesforce Login Automation");
        System.out.println("========================================");
        
        try {
            // Initialize driver if not already done
            WebDriver webDriver = DriverManager.getDriver();
            LoginPage page = new LoginPage(webDriver);
            
            // Step 1: Navigate to Salesforce login page
            System.out.println("Step 1: Navigating to https://qautomation.my.salesforce.com/");
            page.navigateToLoginPage();
            Thread.sleep(2000);
            
            // Step 2: Enter username
            System.out.println("Step 2: Entering username: priyanka.bhadoria@xfp.com");
            page.enterUsername("priyanka.bhadoria@xfp.com");
            Thread.sleep(1000);
            
            // Step 3: Enter password
            System.out.println("Step 3: Entering password: XfilesPro@123");
            page.enterPassword("XfilesPro@123");
            Thread.sleep(1000);
            
            // Step 4: Click login button
            System.out.println("Step 4: Clicking login button");
            page.clickLoginButton();
            
            // Step 5: Wait for login to complete
            System.out.println("Step 5: Waiting for login to complete...");
            Thread.sleep(5000);
            
            // Step 6: Verify login success
            if (page.isLoginSuccessful()) {
                System.out.println("✓ SUCCESS: Successfully logged into Salesforce!");
                System.out.println("Current URL: " + webDriver.getCurrentUrl());
            } else {
                System.err.println("✗ FAILED: Login was not successful");
                String error = page.getErrorMessage();
                if (!error.isEmpty()) {
                    System.err.println("Error message: " + error);
                }
            }
            
        } catch (Exception e) {
            System.err.println("✗ ERROR: An error occurred during login automation");
            System.err.println("Error details: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("========================================");
        System.out.println("Salesforce Login Automation Completed");
        System.out.println("========================================");
    }
}