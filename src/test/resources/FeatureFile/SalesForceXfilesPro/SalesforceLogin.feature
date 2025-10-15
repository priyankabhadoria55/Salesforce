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
