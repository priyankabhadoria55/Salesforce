package bddRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.*;
import utils.*;

import java.io.IOException;

@CucumberOptions(
        features = {"src/test/resources/FeatureFile/SalesForceXfilesPro/files.feature"},
        glue = {"pageHelper"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/files-test-report.html",
                "json:test-output/cucumber-reports/files-test.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@Files")

public class FilesTestRunner extends AbstractTestNGCucumberTests {
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
        propertyreader.updateproprty("CurrentTest", "FilesFeature");
        System.out.println("🚀 Starting Salesforce Files Feature Test Suite");
    }

    @AfterClass
    public static void writeExtentReport() throws IOException {
        PropertyReader prpertyreader = new PropertyReader();
        System.out.println("📊 Extent Report generated for Files feature tests");
    }

    @AfterSuite
    public void TearDown() throws IOException, InvalidFormatException {
        System.out.println("✅ Files Feature Test Suite completed");
    }
}
