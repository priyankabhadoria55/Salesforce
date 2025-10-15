@WEB @Salesforce @Login
Feature: Salesforce login via qautomation domain
  As a user, I want to log into Salesforce using the provided credentials.

  @TC-Login-QAutomation
  Scenario: Login to Salesforce using qautomation domain
    Then Goto "https://qautomation.my.salesforce.com/"
    When Login into Salesforce
    Then Wait for 3 seconds
