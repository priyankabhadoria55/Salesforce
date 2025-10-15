package bddRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature"},
        glue = {"pageHelper"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/cucumber-pretty.html",
                "json:test-output/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@SalesforceLogin")
public class SalesforceLoginRunner extends AbstractTestNGCucumberTests {
}
