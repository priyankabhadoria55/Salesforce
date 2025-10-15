# Salesforce Automation - Cucumber BDD Framework

## Overview
This is a Selenium WebDriver automation framework using Cucumber BDD for automating Salesforce login functionality.

## Project Structure
```
salesforce-cucumber-automation/
├── pom.xml                                 # Maven configuration
├── src/test/java/
│   ├── pages/
│   │   └── LoginPage.java                 # Page Object Model for login page
│   ├── stepDefinitions/
│   │   ├── LoginSteps.java                # Step definitions for login feature
│   │   └── Hooks.java                     # Setup and teardown hooks
│   ├── runners/
│   │   └── TestRunner.java                # Cucumber test runner
│   ├── utils/
│   │   ├── DriverManager.java            # WebDriver management
│   │   ├── ConfigReader.java             # Configuration reader
│   │   └── LoginHelper.java              # Login helper methods
│   └── tests/
│       └── SalesforceLoginTest.java      # Standalone test class
└── src/test/resources/
    ├── features/
    │   └── SalesforceLogin.feature       # Cucumber feature file
    ├── config.properties                  # Application configuration
    ├── extent.properties                  # Extent report configuration
    └── extent-config.xml                  # Extent report styling

```

## Features
- **BDD Framework**: Uses Cucumber for behavior-driven development
- **Page Object Model**: Organized page objects for better maintainability
- **Configurable**: External configuration file for easy updates
- **Multi-browser Support**: Chrome, Firefox, Edge
- **Reporting**: HTML and Extent reports
- **Screenshots**: Automatic screenshots on failure
- **Parallel Execution Ready**: ThreadLocal WebDriver management

## Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser

## Installation

1. Clone or download the project
2. Install dependencies:
```bash
mvn clean install
```

## Configuration

### Update Credentials
Edit `src/test/resources/config.properties`:
```properties
app.username=priyanka.bhadoria@xfp.com
app.password=XfilesPro@123
```

### Browser Configuration
Default browser is Chrome. To change:
```properties
browser=chrome  # Options: chrome, firefox, edge
headless=false  # Set to true for headless execution
```

## Running Tests

### Method 1: Run via Maven (Cucumber)
```bash
# Run all tests
mvn clean test

# Run specific tags
mvn clean test -Dcucumber.filter.tags="@Login"

# Run with different browser
mvn clean test -Dbrowser=firefox

# Run in headless mode
mvn clean test -Dheadless=true
```

### Method 2: Run via JUnit Runner
```bash
# Run TestRunner class
mvn test -Dtest=TestRunner

# Run standalone test
mvn test -Dtest=SalesforceLoginTest
```

### Method 3: Run Standalone Java Class
```bash
# Compile and run
mvn compile
mvn exec:java -Dexec.mainClass="tests.SalesforceLoginTest"
```

### Method 4: IDE Execution
- Right-click on `TestRunner.java` and select "Run"
- Or right-click on `SalesforceLogin.feature` and select "Run"

## Test Credentials
- **URL**: https://qautomation.my.salesforce.com/
- **Username**: priyanka.bhadoria@xfp.com
- **Password**: XfilesPro@123

## Key Components

### 1. LoginPage.java
Page Object Model class containing:
- Element locators
- Page methods (enterUsername, enterPassword, clickLogin)
- Combined login method

### 2. LoginHelper.java
Contains the main automation method:
```java
public void automateLogin() {
    // Navigates to Salesforce
    // Enters credentials
    // Clicks login
    // Verifies success
}
```

### 3. Feature File
BDD scenarios for login testing:
- Successful login scenario
- Data-driven login with Examples

### 4. Step Definitions
Maps feature file steps to Java methods

## Reports

After test execution, reports are generated in:
- **Cucumber HTML Report**: `target/cucumber-reports/cucumber.html`
- **Extent Report**: `test-output/SparkReport/Spark.html`
- **JUnit XML**: `target/cucumber-reports/cucumber.xml`

## Troubleshooting

### Common Issues

1. **WebDriver not found**
   - The framework uses WebDriverManager for automatic driver management
   - Ensure internet connection for first-time driver download

2. **Login fails**
   - Verify credentials are correct
   - Check if Salesforce has any security verifications (2FA, security token)
   - May need to append security token to password

3. **Element not found**
   - Salesforce may have updated their UI
   - Check and update locators in LoginPage.java

## Best Practices

1. **Don't hardcode credentials** - Use config file
2. **Use Page Object Model** - Maintain page objects separately
3. **Add waits** - Use explicit waits for reliable execution
4. **Take screenshots** - Capture on failure for debugging
5. **Clean browser state** - Clear cookies between tests

## Extension Ideas

1. Add more test scenarios (negative testing, forgot password)
2. Implement data-driven testing with Excel/CSV
3. Add API integration for test data setup
4. Implement retry mechanism for flaky tests
5. Add cross-browser testing in parallel

## Support

For issues or questions, please check:
1. Console logs for error messages
2. Screenshots in test-output folder
3. Reports for detailed execution logs