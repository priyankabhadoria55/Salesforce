# 🚀 Salesforce Login Automation - Quick Start Guide

## What Has Been Created

I've created a complete **Salesforce Login Automation** using **Selenium WebDriver with BDD Cucumber Framework**. Here's what you have:

### 📁 Files Created

1. **Feature File** (BDD Test Scenarios)
   - Location: `src/test/resources/FeatureFile/SalesForceXfilesPro/SalesforceLogin.feature`
   - Contains 3 test scenarios for login automation

2. **Test Runner** (Execute Tests)
   - Location: `src/test/java/bddRunner/SalesforceLoginRunner.java`
   - TestNG + Cucumber runner to execute feature files

3. **Page Object Model** (Reusable Methods)
   - Location: `src/test/java/pageHelper/web/SalesForceXfilesPro/SalesforceLoginPage.java`
   - Contains reusable login methods following Page Object Model pattern

4. **Step Definitions** (Already existed, enhanced)
   - Location: `src/test/java/pageHelper/web/SalesForceXfilesPro/CommonPageHelper.java`
   - Contains BDD step definitions for login and verification

5. **Configuration** (Updated)
   - Location: `config.properties`
   - Updated with your Salesforce URL and credentials

### 🎯 Your Salesforce Configuration

```properties
URL:      https://qautomation.my.salesforce.com
Username: priyanka.bhadoria@xfp.com
Password: XfilesPro@123
Browser:  Chrome
```

## ⚡ Quick Run Commands

### Option 1: Run via Maven (Recommended)
```bash
# Navigate to project directory
cd /workspace

# Run the login test
mvn test -Dtest=SalesforceLoginRunner
```

### Option 2: Run specific scenario with tag
```bash
# Run only the first scenario
mvn test -Dtest=SalesforceLoginRunner -Dcucumber.filter.tags="@SalesforceLogin-001"

# Run only positive login test
mvn test -Dtest=SalesforceLoginRunner -Dcucumber.filter.tags="@LoginPositive"
```

### Option 3: Run from IDE
1. Open `SalesforceLoginRunner.java` in Eclipse/IntelliJ
2. Right-click → "Run As" → "TestNG Test"

## 📝 Test Scenarios Available

### Scenario 1: Basic Login (@SalesforceLogin-001)
```gherkin
When Login into Salesforce
And Wait for 5 seconds
Then Verify successful login to Salesforce
```

### Scenario 2: Login with App Launcher Verification (@SalesforceLogin-002)
```gherkin
When Login into Salesforce
And Wait for 5 seconds
Then Verify App Launcher is visible
```

### Scenario 3: Login and Navigate to App (@SalesforceLogin-003)
```gherkin
When Login into Salesforce
And Wait for 5 seconds
Given Open "Sales" from App Launcher
And Wait for 3 seconds
Then Go to "Accounts" Object
```

## 🔧 Reusable Login Methods

### Method 1: Using in Feature Files (BDD Style)
```gherkin
Scenario: My Test
  When Login into Salesforce
  Then Verify successful login to Salesforce
```

### Method 2: Using Page Object in Java Code
```java
// Create instance
SalesforceLoginPage loginPage = new SalesforceLoginPage(driver);

// Login with credentials from config.properties
loginPage.loginWithConfigCredentials();

// Verify login success
if (loginPage.isLoginSuccessful()) {
    System.out.println("Login successful!");
}
```

### Method 3: Using with Custom Credentials
```java
SalesforceLoginPage loginPage = new SalesforceLoginPage(driver);

// Login with custom credentials
loginPage.loginWithCustomCredentials(
    "custom.user@salesforce.com", 
    "customPassword123"
);
```

## 📊 Where to Find Reports

After running tests, check these locations:

1. **Cucumber HTML Report**
   - Path: `test-output/cucumber-reports/salesforce-login.html`
   - Open in browser to see detailed results

2. **Extent Report**
   - Path: `test-output/SparkReport/Spark.html`
   - Beautiful HTML report with screenshots

3. **Console Output**
   - Check terminal/console for real-time execution logs

## 🎨 How to Modify Tests

### Change Test Data
Edit `config.properties`:
```properties
XfilePro_user=your.email@example.com
XfilePro_password=yourPassword
```

### Add New Scenario
Edit `SalesforceLogin.feature`:
```gherkin
@SalesforceLogin-004
Scenario: Your new scenario name
  When Login into Salesforce
  # Add your steps here
```

### Change Browser
Edit `config.properties`:
```properties
browser=chrome  # or firefox, edge
```

## 🛠️ Framework Features

✅ **BDD Cucumber** - Write tests in plain English  
✅ **Page Object Model** - Reusable, maintainable code  
✅ **TestNG Integration** - Powerful test execution  
✅ **Extent Reports** - Beautiful HTML reports  
✅ **XML Locator Management** - Easy to update locators  
✅ **Property-based Configuration** - Easy environment switching  
✅ **Parallel Execution Support** - Run tests faster  
✅ **Screenshot on Failure** - Auto-capture failed test screenshots  

## 📚 Key Files Reference

| File | Purpose | Location |
|------|---------|----------|
| SalesforceLogin.feature | BDD test scenarios | src/test/resources/FeatureFile/SalesForceXfilesPro/ |
| SalesforceLoginRunner.java | Test runner | src/test/java/bddRunner/ |
| SalesforceLoginPage.java | Page Object Model | src/test/java/pageHelper/web/SalesForceXfilesPro/ |
| CommonPageHelper.java | Step definitions | src/test/java/pageHelper/web/SalesForceXfilesPro/ |
| salesforce.xml | Element locators | src/test/resources/locators/ |
| config.properties | Configuration | Root directory |

## 🔍 Example Console Output

When you run the test, you'll see:
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
✅ Login process completed
✅ Login verification successful - App Launcher is visible

===========================================
   Salesforce Login Test Suite Completed  
===========================================
```

## 🎯 Next Steps

1. **Run the test** to verify everything works
2. **Check the reports** to see test results
3. **Customize scenarios** based on your needs
4. **Add more test cases** to the feature file
5. **Extend the framework** with more page objects

## ❓ Need Help?

- Check `SALESFORCE_LOGIN_README.md` for detailed documentation
- Review the feature file for available steps
- Look at `SalesforceLoginPage.java` for available methods
- Check console output for error messages

---

**Happy Testing! 🎉**

Created by: Automation Framework  
Date: 2025-10-15
