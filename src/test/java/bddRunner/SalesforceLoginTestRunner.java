package bddRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.*;
import utils.*;

import java.io.IOException;

@CucumberOptions(
        features = {"src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature"},
        glue = {"pageHelper"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/salesforce-login-report.html",
                "json:test-output/cucumber-reports/salesforce-login.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@SalesforceLogin")

public class SalesforceLoginTestRunner extends AbstractTestNGCucumberTests {
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
        propertyreader.updateproprty("CurrentTest", "SalesforceLogin");
        System.out.println("🚀 Starting Salesforce Login Automation Test Suite");
    }

    @AfterClass
    public static void writeExtentReport() throws IOException {
        PropertyReader prpertyreader = new PropertyReader();
        System.out.println("📊 Extent Report generated for Salesforce Login tests");
    }

    @AfterSuite
    public void TearDown() throws IOException, InvalidFormatException {
        System.out.println("✅ Salesforce Login Test Suite completed");
    }
}
