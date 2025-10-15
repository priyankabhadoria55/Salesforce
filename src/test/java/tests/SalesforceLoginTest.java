package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.DriverManager;
import utils.LoginHelper;

/**
 * Standalone test class for Salesforce login
 * This can be run independently without Cucumber
 */
public class SalesforceLoginTest {
    
    private LoginHelper loginHelper;
    
    @Before
    public void setup() {
        System.out.println("Initializing test...");
        loginHelper = new LoginHelper(DriverManager.getDriver());
    }
    
    @Test
    public void testSalesforceLogin() {
        // Run the automated login
        loginHelper.automateLogin();
    }
    
    @After
    public void teardown() {
        System.out.println("Cleaning up...");
        DriverManager.quitDriver();
    }
    
    /**
     * Main method to run the test directly
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SalesforceLoginTest test = new SalesforceLoginTest();
        test.setup();
        test.testSalesforceLogin();
        test.teardown();
    }
}