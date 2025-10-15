package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;

public class Hooks {
    
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("===========================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("Tags: " + scenario.getSourceTagNames());
        System.out.println("===========================================");
        
        // Driver initialization is handled by DriverManager when needed
    }
    
    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                // Take screenshot on failure
                TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
                byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", "Screenshot");
                
                System.err.println("Scenario Failed: " + scenario.getName());
                System.err.println("Error: " + scenario.getStatus());
            } else {
                System.out.println("Scenario Passed: " + scenario.getName());
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        } finally {
            // Quit the driver after each scenario
            DriverManager.quitDriver();
            System.out.println("===========================================\n");
        }
    }
    
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        // Optional: Take screenshot after each step for better debugging
        if (scenario.isFailed()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
                byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", "Step Failed Screenshot");
            } catch (Exception e) {
                System.err.println("Could not capture screenshot: " + e.getMessage());
            }
        }
    }
}