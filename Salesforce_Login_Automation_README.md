# Salesforce Login Automation with Cucumber BDD Framework

This project provides a comprehensive Salesforce login automation solution using Selenium WebDriver and Cucumber BDD framework.

## 🚀 Features

- **BDD Framework**: Uses Cucumber for behavior-driven development
- **Selenium WebDriver**: Automated browser interactions
- **Multiple Login Methods**: Support for config-based and custom credentials
- **Comprehensive Testing**: Login validation, error handling, and page verification
- **Reusable Components**: Utility classes for easy integration
- **Detailed Reporting**: Extent reports and console logging

## 📁 Project Structure

```
src/test/
├── java/
│   ├── bddRunner/
│   │   └── SalesforceLoginTestRunner.java    # Test runner for login tests
│   └── pageHelper/web/SalesForceXfilesPro/
│       ├── SalesforceLoginStep.java          # Step definitions for BDD
│       ├── SalesforceLoginUtility.java       # Reusable utility methods
│       └── SalesforceLoginDemo.java          # Demo and examples
└── resources/
    ├── FeatureFile/SalesForceXfilesPro/
    │   └── SalesforceLogin.feature           # BDD feature file
    └── config.properties                     # Configuration file
```

## 🔧 Configuration

### config.properties
The configuration file contains the Salesforce login details:

```properties
xFilesPro_application_url=https://qautomation.my.salesforce.com
XfilePro_user=priyanka.bhadoria@xfp.com
XfilePro_password=XfilesPro@123
browser=chrome
```

## 🎯 Test Scenarios

### 1. Basic Login Test
- Navigate to Salesforce login page
- Enter valid credentials
- Verify successful login
- Check home page display

### 2. Login Page Validation
- Verify all login form elements are present
- Check username field
- Check password field
- Check login button
- Check "Remember me" checkbox

### 3. Data-Driven Testing
- Support for multiple credential sets
- Parameterized testing using Scenario Outline

## 🚀 How to Run Tests

### Method 1: Using TestNG Runner
```bash
# Run all login tests
mvn test -Dtest=SalesforceLoginTestRunner

# Run specific test scenario
mvn test -Dtest=SalesforceLoginTestRunner -Dcucumber.options="--tags @TC-Salesforce-Login-001"
```

### Method 2: Using Maven
```bash
# Run with Maven
mvn clean test -Dtest=SalesforceLoginTestRunner
```

### Method 3: IDE Execution
- Right-click on `SalesforceLoginTestRunner.java`
- Select "Run As" > "TestNG Test"

## 📝 Feature File Examples

### Basic Login Scenario
```gherkin
@TC-Salesforce-Login-001
Scenario: Login to Salesforce with valid credentials
  Given I navigate to Salesforce login page "https://qautomation.my.salesforce.com/"
  When I enter username "priyanka.bhadoria@xfp.com"
  And I enter password "XfilesPro@123"
  And I click on Login button
  Then I should be successfully logged into Salesforce
  And I should see the Salesforce home page
```

### Data-Driven Scenario
```gherkin
@TC-Salesforce-Login-002
Scenario Outline: Login to Salesforce with different credentials
  Given I navigate to Salesforce login page "https://qautomation.my.salesforce.com/"
  When I enter username "<username>"
  And I enter password "<password>"
  And I click on Login button
  Then I should be successfully logged into Salesforce
  And I should see the Salesforce home page

  Examples:
    | username                    | password        |
    | priyanka.bhadoria@xfp.com  | XfilesPro@123   |
```

## 🛠️ Utility Class Usage

### Basic Usage
```java
// Initialize utility
SalesforceLoginUtility loginUtil = new SalesforceLoginUtility(bddDriver);

// Login using config.properties
boolean success = loginUtil.loginToSalesforceWithConfig();

// Login with custom credentials
boolean success = loginUtil.loginToSalesforce(
    "https://qautomation.my.salesforce.com/",
    "priyanka.bhadoria@xfp.com",
    "XfilesPro@123"
);
```

### Advanced Usage
```java
// Check if logged in
if (loginUtil.isLoggedIn()) {
    System.out.println("Already logged in");
}

// Verify login success
if (loginUtil.verifyLoginSuccess()) {
    System.out.println("Login verification passed");
}

// Take screenshot
loginUtil.takeScreenshot("login_success.png");

// Logout
loginUtil.logoutFromSalesforce();
```

## 📊 Reports

After test execution, reports are generated in:
- **HTML Report**: `test-output/cucumber-reports/salesforce-login-report.html`
- **JSON Report**: `test-output/cucumber-reports/salesforce-login.json`
- **Extent Report**: Generated via ExtentCucumberAdapter

## 🔍 Troubleshooting

### Common Issues

1. **WebDriver Not Found**
   - Ensure ChromeDriver is in PATH
   - Check browser configuration in config.properties

2. **Login Fails**
   - Verify credentials in config.properties
   - Check if Salesforce URL is accessible
   - Ensure network connectivity

3. **Element Not Found**
   - Check if Salesforce page structure has changed
   - Update locators in step definitions
   - Increase wait time if needed

### Debug Mode
Enable debug logging by setting log level to DEBUG in log4j.properties

## 🎯 Best Practices

1. **Wait Strategies**: Use explicit waits for better reliability
2. **Error Handling**: Implement proper exception handling
3. **Data Management**: Use external data sources for test data
4. **Page Object Model**: Consider implementing POM for complex pages
5. **Parallel Execution**: Use TestNG parallel execution for faster runs

## 📚 Dependencies

Key dependencies in pom.xml:
- Selenium WebDriver
- Cucumber Java
- TestNG
- ExtentReports
- Apache POI

## 🤝 Contributing

1. Follow existing code structure
2. Add proper JavaDoc comments
3. Write comprehensive test scenarios
4. Update documentation as needed

## 📞 Support

For issues or questions:
- Check the troubleshooting section
- Review the demo class for usage examples
- Examine the step definitions for implementation details

---

**Note**: This automation is specifically configured for the Salesforce instance at `https://qautomation.my.salesforce.com/` with the provided credentials. Modify the configuration as needed for different environments.
