package bddRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.*;
import utils.*;

import java.io.IOException;

@CucumberOptions(
        features = {"src/test/resources/FeatureFile/SalesForceXfilesPro/Upload.feature"},
        glue = {"pageHelper"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/cucumber-pretty.html",
                "json:test-output/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@TC16")

public class UploadTestRunner extends AbstractTestNGCucumberTests {
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
    }

    @AfterClass
    public static void writeExtentReport() throws IOException {
        PropertyReader prpertyreader = new PropertyReader();
    }

    @AfterSuite
    public void TearDown() throws IOException, InvalidFormatException {
        //mock.MockServerTearDown();
    }
}
