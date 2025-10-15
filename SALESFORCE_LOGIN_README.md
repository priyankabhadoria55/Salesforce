# Salesforce Login Automation - BDD Cucumber Framework

## Overview
This project automates the Salesforce login functionality using Selenium WebDriver with BDD Cucumber framework. The implementation follows Page Object Model design pattern for better maintainability and reusability.

## 📁 Project Structure

```
workspace/
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── bddRunner/
│   │   │   │   └── SalesforceLoginRunner.java          # Test Runner
│   │   │   ├── pageHelper/
│   │   │   │   ├── bddDriver.java                      # Driver Management
│   │   │   │   └── web/
│   │   │   │       ├── WebActions.java                 # Common Web Actions
│   │   │   │       └── SalesForceXfilesPro/
│   │   │   │           ├── CommonPageHelper.java       # Common Salesforce Methods
│   │   │   │           └── SalesforceLoginPage.java    # Login Page Object
│   │   └── resources/
│   │       ├── FeatureFile/
│   │       │   └── SalesForceXfilesPro/
│   │       │       └── SalesforceLogin.feature         # BDD Feature File
│   │       └── locators/
│   │           └── salesforce.xml                       # Element Locators
├── config.properties                                     # Configuration File
└── pom.xml                                              # Maven Dependencies
```

## 🔧 Configuration

### config.properties
The application URL and credentials are configured in `config.properties`:

```properties
# Salesforce Configuration
xFilesPro_application_url=https://qautomation.my.salesforce.com
XfilePro_user=priyanka.bhadoria@xfp.com
XfilePro_password=XfilesPro@123
browser=chrome
```

## 📝 Feature File

### Location: `src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature`

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

## 🎯 Key Components

### 1. SalesforceLoginPage.java (Page Object Model)

Reusable methods for Salesforce login:

```java
// Login with credentials from config.properties
public void loginWithConfigCredentials() throws Exception

// Login with custom credentials
public void loginWithCustomCredentials(String username, String password) throws Exception

// Verify successful login
public boolean isLoginSuccessful() throws Exception

// Enter username
public void enterUsername(String username) throws Exception

// Enter password
public void enterPassword(String password) throws Exception

// Click login button
public void clickLoginButton() throws Exception
```

### 2. CommonPageHelper.java

BDD Step Definitions:

```java
@When("Login into Salesforce")
public void loginIntoSalesforce() throws Exception

@Then("Verify successful login to Salesforce")
public void verifySuccessfulLogin() throws Exception

@Then("Verify App Launcher is visible")
public void verifyAppLauncherIsVisible() throws Exception
```

### 3. SalesforceLoginRunner.java

TestNG + Cucumber Runner:

```java
@CucumberOptions(
    features = {"src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature"},
    glue = {"pageHelper"},
    plugin = {
        "pretty",
        "html:test-output/cucumber-reports/salesforce-login.html",
        "json:test-output/cucumber-reports/salesforce-login.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    tags = "@SalesforceLogin-001"
)
```

## 🚀 How to Run Tests

### Prerequisites
- Java 16 or higher
- Maven 3.6+
- Chrome browser
- Internet connection

### Run via Maven Command Line

1. **Run specific scenario:**
   ```bash
   mvn test -Dtest=SalesforceLoginRunner
   ```

2. **Run with specific tag:**
   ```bash
   mvn test -Dtest=SalesforceLoginRunner -Dcucumber.filter.tags="@SalesforceLogin-001"
   ```

3. **Run all login scenarios:**
   ```bash
   mvn test -Dtest=SalesforceLoginRunner -Dcucumber.filter.tags="@Login"
   ```

### Run via TestNG XML

1. Update `src/test/testNGSuites/SalesforceTests.xml` to include:
   ```xml
   <test name="Salesforce Login Tests">
       <classes>
           <class name="bddRunner.SalesforceLoginRunner"/>
       </classes>
   </test>
   ```

2. Run:
   ```bash
   mvn test -DsuiteXmlFile=src/test/testNGSuites/SalesforceTests.xml
   ```

### Run from IDE (Eclipse/IntelliJ)
1. Right-click on `SalesforceLoginRunner.java`
2. Select "Run As" → "TestNG Test"

## 📊 Test Reports

After execution, reports are generated at:

1. **HTML Report:** `test-output/cucumber-reports/salesforce-login.html`
2. **JSON Report:** `test-output/cucumber-reports/salesforce-login.json`
3. **Extent Report:** `test-output/SparkReport/Spark.html`

## 🎨 BDD Steps Available

### Login Steps
```gherkin
When Login into Salesforce
Then Verify successful login to Salesforce
Then Verify App Launcher is visible
```

### Navigation Steps
```gherkin
Given Open "Sales" from App Launcher
Then Go to "Accounts" Object
```

### Wait Steps
```gherkin
And Wait for 5 seconds
```

## 📌 Element Locators

Locators are maintained in `src/test/resources/locators/salesforce.xml`:

```xml
<LoginButton>//*[@id="Login"]</LoginButton>
<AppLauncher>//*[@title='App Launcher']</AppLauncher>
<FormLabel>//label[text()='{paramlink}']</FormLabel>
<FormFields>//*[@id='{paramlink}']</FormFields>
```

## 🔄 Reusable Methods

### Example 1: Using CommonPageHelper in Feature File
```gherkin
Scenario: Login to Salesforce
  When Login into Salesforce
  Then Verify successful login to Salesforce
```

### Example 2: Using Page Object in Java Code
```java
SalesforceLoginPage loginPage = new SalesforceLoginPage(driver);

// Login with config credentials
loginPage.loginWithConfigCredentials();

// Verify login
if (loginPage.isLoginSuccessful()) {
    System.out.println("Login successful!");
}

// Login with custom credentials
loginPage.loginWithCustomCredentials("user@example.com", "password123");
```

## 🐛 Troubleshooting

### Issue: Login fails
- Verify credentials in `config.properties`
- Check if URL is accessible
- Ensure Chrome browser is installed
- Check internet connection

### Issue: Element not found
- Verify locators in `salesforce.xml`
- Check if Salesforce UI has changed
- Increase wait time in feature file

### Issue: Browser doesn't open
- Check WebDriver Manager in `bddDriver.java`
- Ensure Chrome browser is compatible
- Check browser path in system

## 📚 Additional Resources

- **Cucumber Documentation:** https://cucumber.io/docs/
- **Selenium Documentation:** https://www.selenium.dev/documentation/
- **TestNG Documentation:** https://testng.org/doc/

## 👥 Support

For issues or questions, contact the automation team.

---

**Created by:** Automation Team  
**Last Updated:** 2025-10-15  
**Version:** 1.0
