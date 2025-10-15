@Sanity @WEB @Salesforce @SalesforceLogin
Feature: Salesforce Login on custom domain
  As a Salesforce user
  I want to navigate to my custom domain and login
  So that I can access the application

  Scenario: Login to Salesforce at custom domain
    Then Go to "https://qautomation.my.salesforce.com"
    When Login into Salesforce
    Then Wait for 5 seconds
