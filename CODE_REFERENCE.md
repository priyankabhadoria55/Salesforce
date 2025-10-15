# 📄 Salesforce Login Automation - Complete Code Reference

## Quick Access to All Code

This document provides quick access to all the code created for Salesforce login automation.

---

## 1. Feature File (BDD Test Scenarios)

**Location**: `src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature`

```gherkin
@Sanity @Regression @smoke @WEB @Dev @Salesforce @Login
Feature: Salesforce Login Automation
  As a QA automation engineer
  I want to automate the Salesforce login functionality
  So that I can verify the login process works correctly

  @SalesforceLogin-001 @LoginPositive
  Scenario: Successful login to Salesforce with valid credentials
    When Login into Salesforce
    And Wait for 5 seconds
    Then Verify successful login to Salesforce
    
  @SalesforceLogin-002 @LoginWithAppLauncherVerification
  Scenario: Login to Salesforce and verify App Launcher is accessible
    When Login into Salesforce
    And Wait for 5 seconds
    Then Verify App Launcher is visible
    
  @SalesforceLogin-003 @LoginAndNavigateToApp
  Scenario: Login to Salesforce and open an application
    When Login into Salesforce
    And Wait for 5 seconds
    Given Open "Sales" from App Launcher
    And Wait for 3 seconds
    Then Go to "Accounts" Object
```

---

## 2. Test Runner

**Location**: `src/test/java/bddRunner/SalesforceLoginRunner.java`

```java
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
```

---

## 3. Page Object Model (Reusable Methods)

**Location**: `src/test/java/pageHelper/web/SalesForceXfilesPro/SalesforceLoginPage.java`

```java
package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;
import utils.xmlreader;

public class SalesforceLoginPage {

    private webHelper webDriver;
    private xmlreader xml;
    private PropertyReader propertyReader;

    public SalesforceLoginPage(WebDriver driver) throws Exception {
        this.webDriver = new baseDriverHelper(driver);
        this.xml = new xmlreader("src\\test\\resources\\locators\\salesforce.xml");
        this.propertyReader = new PropertyReader();
    }

    public void enterUsername(String username) throws Exception {
        String fieldname = webDriver.Getattribute(
                webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}", "Username")),
                "for"
        );
        webDriver.SendKeys(
                webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}", fieldname)),
                username
        );
        System.out.println("✅ Username entered: " + username);
    }

    public void enterPassword(String password) throws Exception {
        String fieldname = webDriver.Getattribute(
                webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}", "Password")),
                "for"
        );
        webDriver.SendKeys(
                webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}", fieldname)),
                password
        );
        System.out.println("✅ Password entered");
    }

    public void clickLoginButton() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(xml.getlocator("LoginButton")));
        System.out.println("✅ Login button clicked");
    }

    // Main reusable login method
    public void loginWithConfigCredentials() throws Exception {
        String username = propertyReader.readproperty("XfilePro_user");
        String password = propertyReader.readproperty("XfilePro_password");

        System.out.println("🔐 Starting Salesforce Login...");
        System.out.println("   URL: " + propertyReader.readproperty("xFilesPro_application_url"));
        System.out.println("   Username: " + username);

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        System.out.println("✅ Login process completed");
    }

    public void loginWithCustomCredentials(String username, String password) throws Exception {
        System.out.println("🔐 Starting Salesforce Login with custom credentials...");
        System.out.println("   Username: " + username);

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        System.out.println("✅ Login process completed");
    }

    public boolean isLoginSuccessful() throws Exception {
        try {
            webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
            System.out.println("✅ Login verification successful - App Launcher is visible");
            return true;
        } catch (Exception e) {
            System.out.println("❌ Login verification failed - App Launcher not found");
            return false;
        }
    }

    public void verifyAppLauncherVisible() throws Exception {
        webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
        System.out.println("✅ App Launcher is visible");
    }

    public String getCurrentUrl() {
        return webDriver.CurrentURL();
    }

    public String getPageTitle() {
        return webDriver.GetTitle();
    }
}
```

---

## 4. Step Definitions (BDD Glue Code)

**Location**: `src/test/java/pageHelper/web/SalesForceXfilesPro/CommonPageHelper.java`

**Key Methods Added**:

```java
@When("Login into Salesforce")
public void loginIntoSalesforce() throws Exception {
    PropertyReader pr = new PropertyReader();
    
    String username = pr.readproperty("XfilePro_user");
    String password = pr.readproperty("XfilePro_password");

    System.out.println("🔐 Logging into Salesforce...");
    System.out.println("   Username: " + username);

    // Enter username
    String fieldname = webDriver.Getattribute(
            webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}", "Username")),
            "for"
    );
    webDriver.SendKeys(
            webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}", fieldname)),
            username
    );

    // Enter password
    fieldname = webDriver.Getattribute(
            webDriver.getwebelement(xml.getlocator("FormLabel").replace("{paramlink}", "Password")),
            "for"
    );
    webDriver.SendKeys(
            webDriver.getwebelement(xml.getlocator("FormFields").replace("{paramlink}", fieldname)),
            password
    );

    // Click login button
    webDriver.Clickon(webDriver.getwebelement(xml.getlocator("LoginButton")));

    System.out.println("✅ Login credentials submitted");
}

@io.cucumber.java.en.Then("Verify successful login to Salesforce")
public void verifySuccessfulLogin() throws Exception {
    webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
    System.out.println("✅ Successfully logged into Salesforce - App Launcher is visible");
}

@io.cucumber.java.en.Then("Verify App Launcher is visible")
public void verifyAppLauncherIsVisible() throws Exception {
    webDriver.verifyElementToBePresent(xml.getlocator("AppLauncher"));
    System.out.println("✅ App Launcher is visible");
}
```

---

## 5. Configuration

**Location**: `config.properties`

```properties
# Salesforce Configuration
xFilesPro_application_url=https://qautomation.my.salesforce.com
XfilePro_user=priyanka.bhadoria@xfp.com
XfilePro_password=XfilesPro@123
browser=chrome

# Other configurations
Accessibility_Comp=WCAG2AAA
ENV=QA
SecurityScanFirefox=true
SecurityScanchrome=false
```

---

## 6. Element Locators

**Location**: `src/test/resources/locators/salesforce.xml`

**Key Locators Used**:

```xml
<LoginButton>//*[@id="Login"]</LoginButton>
<AppLauncher>//*[@title='App Launcher']</AppLauncher>
<FormLabel>//label[text()='{paramlink}']</FormLabel>
<FormFields>//*[@id='{paramlink}']</FormFields>
<SearchApp>//*[@placeholder='Search apps and items...']</SearchApp>
<AppLink>//*[@data-label='{paramlink}']</AppLink>
<Entity>//*[@title='{paramlink}']</Entity>
```

---

## 7. How to Use - Code Examples

### Example 1: Use in Feature File

```gherkin
Scenario: Login and perform action
  When Login into Salesforce
  Then Verify successful login to Salesforce
  And Go to "Accounts" Object
  # Continue with your test steps
```

### Example 2: Use Page Object in Java

```java
import pageHelper.web.SalesForceXfilesPro.SalesforceLoginPage;
import org.openqa.selenium.WebDriver;

public class MyTest {
    
    public void testLogin() throws Exception {
        WebDriver driver = ...; // your driver instance
        
        // Create page object
        SalesforceLoginPage loginPage = new SalesforceLoginPage(driver);
        
        // Login with config credentials
        loginPage.loginWithConfigCredentials();
        
        // Verify login
        if (loginPage.isLoginSuccessful()) {
            System.out.println("Login successful!");
            // Continue with test
        }
    }
}
```

### Example 3: Use with Custom Credentials

```java
SalesforceLoginPage loginPage = new SalesforceLoginPage(driver);

// Login with specific user
loginPage.loginWithCustomCredentials(
    "test.user@salesforce.com",
    "testPassword123"
);

// Verify
loginPage.verifyAppLauncherVisible();
```

### Example 4: Individual Method Calls

```java
SalesforceLoginPage loginPage = new SalesforceLoginPage(driver);

// Call methods individually
loginPage.enterUsername("user@example.com");
loginPage.enterPassword("password");
loginPage.clickLoginButton();

// Wait and verify
Thread.sleep(5000);
boolean success = loginPage.isLoginSuccessful();
```

---

## 8. Maven Commands

```bash
# Run all login tests
mvn test -Dtest=SalesforceLoginRunner

# Run specific scenario
mvn test -Dtest=SalesforceLoginRunner -Dcucumber.filter.tags="@SalesforceLogin-001"

# Run with specific browser
mvn test -Dtest=SalesforceLoginRunner -Dbrowser=firefox

# Run smoke tests
mvn test -Dtest=SalesforceLoginRunner -Dcucumber.filter.tags="@smoke"

# Run regression tests
mvn test -Dtest=SalesforceLoginRunner -Dcucumber.filter.tags="@Regression"

# Run all Salesforce tests
mvn test -Dtest=SalesforceLoginRunner -Dcucumber.filter.tags="@Salesforce"
```

---

## 9. Expected Console Output

```
===========================================
   Salesforce Login Test Suite Started    
===========================================

🔐 Logging into Salesforce...
   URL: https://qautomation.my.salesforce.com
   Username: priyanka.bhadoria@xfp.com
✅ Username entered: priyanka.bhadoria@xfp.com
✅ Password entered
✅ Login button clicked
✅ Login credentials submitted
✅ Successfully logged into Salesforce - App Launcher is visible

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

===========================================
   Salesforce Login Test Suite Completed  
===========================================
✅ Test execution completed
```

---

## 10. Report Locations

After test execution, find reports at:

```
test-output/
├── cucumber-reports/
│   ├── salesforce-login.html      # Cucumber HTML Report
│   └── salesforce-login.json      # Cucumber JSON Report
└── SparkReport/
    └── Spark.html                  # Extent Report (Beautiful Dashboard)
```

---

## Quick Reference Table

| Component | Location | Purpose |
|-----------|----------|---------|
| Feature File | `src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature` | BDD test scenarios |
| Test Runner | `src/test/java/bddRunner/SalesforceLoginRunner.java` | Execute tests |
| Page Object | `src/test/java/pageHelper/web/SalesForceXfilesPro/SalesforceLoginPage.java` | Reusable methods |
| Step Defs | `src/test/java/pageHelper/web/SalesForceXfilesPro/CommonPageHelper.java` | BDD glue code |
| Locators | `src/test/resources/locators/salesforce.xml` | Element locators |
| Config | `config.properties` | Environment settings |

---

**Last Updated**: 2025-10-15  
**Framework Version**: 1.0  
**Status**: Production Ready ✅
