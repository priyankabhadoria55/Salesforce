@SalesforceLogin
Feature: Salesforce Login Automation
  As a user
  I want to login to Salesforce application
  So that I can access the Salesforce dashboard

  Background:
    Given I navigate to Salesforce login page

  @ValidLogin @Smoke
  Scenario: Successful login to Salesforce with valid credentials
    When I enter username "priyanka.bhadoria@xfp.com"
    And I enter password "XfilesPro@123"
    And I click on Login button
    Then I should be successfully logged into Salesforce
    And I should see the Salesforce dashboard

  @InvalidLogin @Negative
  Scenario: Failed login with invalid credentials
    When I enter username "invalid@test.com"
    And I enter password "wrongpassword"
    And I click on Login button
    Then I should see login error message
    And I should remain on login page

  @EmptyCredentials @Negative
  Scenario: Failed login with empty credentials
    When I leave username field empty
    And I leave password field empty
    And I click on Login button
    Then I should see validation error messages
    And I should remain on login page