package bddRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.*;
import utils.*;

import java.io.IOException;

/**
 * Salesforce Login Test Runner
 * 
 * This runner executes the Salesforce login feature file
 * Uses TestNG and Cucumber for BDD test execution
 * 
 * Features:
 * - Executes login scenarios from SalesforceLogin.feature
 * - Generates HTML, JSON and Extent reports
 * - Supports tag-based execution
 * 
 * @author Automation Team
 */
@CucumberOptions(
        features = {"src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature"},
        glue = {"pageHelper"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/salesforce-login.html",
                "json:test-output/cucumber-reports/salesforce-login.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@SalesforceLogin-001")

public class SalesforceLoginRunner extends AbstractTestNGCucumberTests {
    public static String message;
    MockServer mock = new MockServer();

    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        Object[][] allScenarios = super.scenarios();
        // Return only the first scenario to run single instance
        if (allScenarios.length > 0) {
            return new Object[][] { allScenarios[0] };
        }
        return allScenarios;
    }

    @BeforeSuite
    public void initializeMock() throws IOException, InvalidFormatException {
        PropertyReader propertyreader = new PropertyReader();
        propertyreader.updateproprty("CurrentTest", "1");
        System.out.println("===========================================");
        System.out.println("   Salesforce Login Test Suite Started    ");
        System.out.println("===========================================");
    }

    @AfterClass
    public static void writeExtentReport() throws IOException {
        PropertyReader prpertyreader = new PropertyReader();
        System.out.println("✅ Test execution completed");
    }

    @AfterSuite
    public void TearDown() throws IOException, InvalidFormatException {
        System.out.println("===========================================");
        System.out.println("   Salesforce Login Test Suite Completed  ");
        System.out.println("===========================================");
    }
}
