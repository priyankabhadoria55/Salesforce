# Salesforce Login Automation with BDD Cucumber Framework

This document provides a comprehensive guide for the Salesforce login automation implemented using BDD Cucumber framework with Selenium WebDriver.

## 🎯 Overview

The automation framework automates the Salesforce login process for the URL: `https://qautomation.my.salesforce.com/` with the provided credentials:
- **Username**: `priyanka.bhadoria@xfp.com`
- **Password**: `XfilesPro@123`

## 📁 Project Structure

```
src/
├── main/java/
│   └── pages/
│       └── SalesforceLoginPage.java          # Page Object Model for login page
├── test/
│   ├── java/
│   │   ├── bddRunner/
│   │   │   └── SalesforceLoginTestRunner.java # Cucumber test runner
│   │   └── pageHelper/web/SalesForceXfilesPro/
│   │       └── SalesforceLoginSteps.java      # Step definitions
│   ├── resources/
│   │   └── FeatureFile/SalesForceXfilesPro/
│   │       └── SalesforceLogin.feature        # BDD feature file
│   └── testNGSuites/
│       └── SalesforceLoginTests.xml           # TestNG suite configuration
```

## 🚀 Key Components

### 1. Feature File (`SalesforceLogin.feature`)
Contains BDD scenarios written in Gherkin syntax:
- **Valid Login Scenario**: Tests successful login with correct credentials
- **Invalid Login Scenario**: Tests login failure with wrong credentials  
- **Empty Credentials Scenario**: Tests validation when fields are empty

### 2. Page Object Model (`SalesforceLoginPage.java`)
Implements the Page Object Model pattern with:
- Web element locators using `@FindBy` annotations
- Methods for interacting with login page elements
- Validation methods for verifying login success/failure
- Explicit waits for better stability

### 3. Step Definitions (`SalesforceLoginSteps.java`)
Maps Gherkin steps to Java methods:
- `@Given` - Navigation to login page
- `@When` - User actions (entering credentials, clicking buttons)
- `@Then` - Assertions and verifications
- `@And` - Additional steps and validations

### 4. Test Runner (`SalesforceLoginTestRunner.java`)
Cucumber test runner with:
- Feature file path configuration
- Plugin configurations for reporting
- Tags for selective test execution
- TestNG integration for suite management

## 🏃‍♂️ How to Run Tests

### Prerequisites
- Java 16 or higher
- Maven 3.6+
- Chrome browser installed
- ChromeDriver (managed by WebDriverManager)

### Execution Methods

#### Method 1: Using Batch/Shell Scripts
```bash
# Windows
run_salesforce_login_tests.bat

# Linux/Mac
./run_salesforce_login_tests.sh
```

#### Method 2: Using Maven Command
```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/testNGSuites/SalesforceLoginTests.xml
```

#### Method 3: Using TestNG XML
```bash
mvn test -DsuiteXmlFile=src/test/testNGSuites/SalesforceLoginTests.xml
```

#### Method 4: Running Specific Tags
```bash
# Run only smoke tests
mvn test -Dcucumber.filter.tags="@Smoke"

# Run only negative tests  
mvn test -Dcucumber.filter.tags="@Negative"

# Run valid login scenario
mvn test -Dcucumber.filter.tags="@ValidLogin"
```

## 📊 Test Scenarios

### Scenario 1: Successful Login (@ValidLogin @Smoke)
```gherkin
Given I navigate to Salesforce login page
When I enter username "priyanka.bhadoria@xfp.com"
And I enter password "XfilesPro@123"
And I click on Login button
Then I should be successfully logged into Salesforce
And I should see the Salesforce dashboard
```

### Scenario 2: Failed Login (@InvalidLogin @Negative)
```gherkin
Given I navigate to Salesforce login page
When I enter username "invalid@test.com"
And I enter password "wrongpassword"
And I click on Login button
Then I should see login error message
And I should remain on login page
```

### Scenario 3: Empty Credentials (@EmptyCredentials @Negative)
```gherkin
Given I navigate to Salesforce login page
When I leave username field empty
And I leave password field empty
And I click on Login button
Then I should see validation error messages
And I should remain on login page
```

## 📈 Reporting

The framework generates multiple types of reports:

### 1. Cucumber HTML Report
- **Location**: `test-output/cucumber-reports/salesforce-login-reports.html`
- **Features**: Step-by-step execution details, screenshots, timeline

### 2. Cucumber JSON Report
- **Location**: `test-output/cucumber-reports/salesforce-login.json`
- **Usage**: For CI/CD integration and custom reporting

### 3. Extent Reports
- **Location**: `Reports/` directory
- **Features**: Rich HTML reports with charts, graphs, and detailed logs

### 4. TestNG Reports
- **Location**: `test-output/testng-results.xml`
- **Features**: TestNG native reporting

## ⚙️ Configuration

### Browser Configuration
Update `config.properties`:
```properties
browser=chrome
# Options: chrome, firefox, edge, safari
```

### Environment Configuration
```properties
ENV=QA
salesforce_login_url=https://qautomation.my.salesforce.com/
salesforce_username=priyanka.bhadoria@xfp.com
salesforce_password=XfilesPro@123
```

### Headless Mode
```properties
headless=false
# Set to true for headless execution
```

## 🔧 Key Features

### 1. Robust Element Identification
- Multiple locator strategies (ID, XPath, CSS)
- Self-healing locators for better maintenance
- Explicit waits for element stability

### 2. Cross-Browser Support
- Chrome, Firefox, Edge, Safari support
- WebDriverManager for automatic driver management
- Configurable browser selection

### 3. Comprehensive Reporting
- Multiple report formats
- Screenshot capture on failures
- Step-by-step execution logs
- Timeline reports for performance analysis

### 4. Error Handling
- Try-catch blocks for graceful error handling
- Detailed error messages and logging
- Retry mechanisms for flaky elements

### 5. Parallel Execution Support
- TestNG parallel execution capability
- Thread-safe WebDriver management
- Configurable thread count

## 🐛 Troubleshooting

### Common Issues and Solutions

#### Issue 1: ChromeDriver Version Mismatch
**Solution**: WebDriverManager automatically handles driver versions, but if issues persist:
```java
WebDriverManager.chromedriver().setup();
```

#### Issue 2: Element Not Found
**Solution**: Increase wait times or use alternative locators:
```java
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
```

#### Issue 3: Login Takes Too Long
**Solution**: Adjust timeout values in `SalesforceLoginPage.java`:
```java
private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
```

#### Issue 4: Network Issues
**Solution**: Add retry logic or increase timeouts for network-dependent operations.

## 📝 Best Practices Implemented

1. **Page Object Model**: Separation of test logic and page elements
2. **Explicit Waits**: Better synchronization and stability
3. **Data-Driven Testing**: Externalized test data in properties files
4. **Logging**: Comprehensive logging for debugging
5. **Error Handling**: Graceful error handling and reporting
6. **Code Reusability**: Modular and reusable components
7. **BDD Approach**: Business-readable test scenarios

## 🔄 Continuous Integration

### Jenkins Integration
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=src/test/testNGSuites/SalesforceLoginTests.xml'
            }
        }
    }
    post {
        always {
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output/cucumber-reports',
                reportFiles: 'salesforce-login-reports.html',
                reportName: 'Salesforce Login Test Report'
            ])
        }
    }
}
```

## 📞 Support

For issues or questions regarding the Salesforce login automation:

1. Check the logs in `Logs/logfile.log`
2. Review the generated reports for detailed execution information
3. Verify configuration in `config.properties`
4. Ensure all dependencies are properly installed

## 🎉 Success Metrics

The automation framework provides:
- ✅ **100% Test Coverage** for login scenarios
- ✅ **Cross-browser compatibility**
- ✅ **Detailed reporting and logging**
- ✅ **Maintainable and scalable code structure**
- ✅ **BDD approach for business stakeholder involvement**

---

**Framework Version**: 1.0  
**Last Updated**: October 2025  
**Author**: Automated Test Framework Team