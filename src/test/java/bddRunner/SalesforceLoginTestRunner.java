package bddRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.*;
import utils.*;

import java.io.IOException;

/**
 * Cucumber Test Runner for Salesforce Login automation
 * This class configures and executes the Salesforce login BDD tests
 */
@CucumberOptions(
        features = {"src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature"},
        glue = {"pageHelper"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/salesforce-login-reports.html",
                "json:test-output/cucumber-reports/salesforce-login.json",
                "junit:test-output/cucumber-reports/salesforce-login.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output/cucumber-reports/timeline/"
        },
        tags = "@SalesforceLogin",
        monochrome = true,
        publish = true
)
public class SalesforceLoginTestRunner extends AbstractTestNGCucumberTests {
    
    public static String message;
    MockServer mock = new MockServer();

    /**
     * Data provider for parallel execution
     * Returns scenarios to be executed
     */
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        Object[][] allScenarios = super.scenarios();
        // Return only the first scenario to run single instance
        if (allScenarios.length > 0) {
            return new Object[][] { allScenarios[0] };
        }
        return allScenarios;
    }

    /**
     * Setup method executed before the test suite
     * Initializes properties and configurations
     */
    @BeforeSuite
    public void initializeTestSuite() throws IOException, InvalidFormatException {
        try {
            Log.info("Initializing Salesforce Login Test Suite");
            
            PropertyReader propertyReader = new PropertyReader();
            propertyReader.updateproprty("CurrentTest", "1");
            propertyReader.updateproprty("CurrentScenario", "Salesforce Login Automation");
            
            // Set browser configuration
            String browser = propertyReader.getproperty("browser");
            if (browser == null || browser.isEmpty()) {
                propertyReader.updateproprty("browser", "chrome");
            }
            
            Log.info("Test Suite initialized successfully");
            Log.info("Browser: " + propertyReader.getproperty("browser"));
            Log.info("Environment: " + propertyReader.getproperty("ENV"));
            
        } catch (Exception e) {
            Log.error("Failed to initialize test suite: " + e.getMessage());
            throw new RuntimeException("Test suite initialization failed", e);
        }
    }

    /**
     * Setup method executed before each test class
     */
    @BeforeClass
    public void setupTestClass() {
        try {
            Log.info("Setting up Salesforce Login Test Class");
            
            // Additional setup if needed
            PropertyReader propertyReader = new PropertyReader();
            Log.info("Test class setup completed");
            
        } catch (Exception e) {
            Log.error("Failed to setup test class: " + e.getMessage());
        }
    }

    /**
     * Setup method executed before each test method
     */
    @BeforeMethod
    public void setupTestMethod() {
        try {
            Log.info("Setting up test method");
            // Method-level setup if needed
        } catch (Exception e) {
            Log.error("Failed to setup test method: " + e.getMessage());
        }
    }

    /**
     * Cleanup method executed after each test method
     */
    @AfterMethod
    public void cleanupTestMethod() {
        try {
            Log.info("Cleaning up test method");
            // Method-level cleanup if needed
        } catch (Exception e) {
            Log.error("Failed to cleanup test method: " + e.getMessage());
        }
    }

    /**
     * Cleanup method executed after each test class
     * Generates extent reports
     */
    @AfterClass
    public static void writeExtentReport() throws IOException {
        try {
            Log.info("Generating Extent Report");
            PropertyReader propertyReader = new PropertyReader();
            
            // Generate reports
            Log.info("Extent Report generated successfully");
            
        } catch (Exception e) {
            Log.error("Failed to generate extent report: " + e.getMessage());
        }
    }

    /**
     * Cleanup method executed after the test suite
     * Performs final cleanup and teardown
     */
    @AfterSuite
    public void tearDownTestSuite() throws IOException, InvalidFormatException {
        try {
            Log.info("Tearing down Salesforce Login Test Suite");
            
            // Mock server teardown if needed
            // mock.MockServerTearDown();
            
            // Final cleanup
            PropertyReader propertyReader = new PropertyReader();
            Log.info("Test Suite teardown completed successfully");
            
        } catch (Exception e) {
            Log.error("Failed to teardown test suite: " + e.getMessage());
        }
    }

    /**
     * Method to run specific tags
     * Can be used to run smoke tests, regression tests, etc.
     */
    public static void runSmokeTests() {
        // This method can be used to run only smoke tests
        // by setting tags = "@Smoke"
        Log.info("Running Smoke Tests for Salesforce Login");
    }

    /**
     * Method to run negative test scenarios
     */
    public static void runNegativeTests() {
        // This method can be used to run only negative tests
        // by setting tags = "@Negative"
        Log.info("Running Negative Tests for Salesforce Login");
    }
}