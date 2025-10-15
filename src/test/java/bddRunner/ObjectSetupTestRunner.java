package bddRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Reporter;
import org.testng.annotations.*;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CucumberOptions(
        features = {"src/test/resources/FeatureFile/SalesForceXfilesPro/ObjectSetup.feature"},
        glue = {"pageHelper"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/cucumber-pretty.html",
                "json:test-output/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@TC49")

public class ObjectSetupTestRunner extends AbstractTestNGCucumberTests {
/*    public static String message;*/
/*    MockServer mock = new MockServer();*/

 /*   @DataProvider(parallel = false)
    public Object[][] scenarios() {
        Object[][] allScenarios = super.scenarios();
        // Return only the first scenario to run single instance
        if (allScenarios.length > 0) {
            return new Object[][] { allScenarios[0] };
        }
        return allScenarios;
    }
*/
/*    @BeforeSuite
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
    }*/
}
