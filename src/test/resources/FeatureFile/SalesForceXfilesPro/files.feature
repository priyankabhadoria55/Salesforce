@Sanity @Regression @smoke @WEB @Dev @POC @Salesforce @Files @FilesFeature-2025
Feature: Salesforce Files Management
  As a user
  I want to login to Salesforce and manage files
  So that I can access and work with files in the Salesforce platform

  @TC-Files-Login-001
  Scenario: Login to Salesforce for file management
    Given I navigate to https://qautomation.my.salesforce.com/
    When I enter the Login Id as priyanka.bhadoria@xfp.com and password XfilesPro@123
    Then Click on login button and user should be able to login successfully

  @TC-Files-Login-002
  Scenario Outline: Login to Salesforce with different credentials for file access
    Given I navigate to <url>
    When I enter the Login Id as <username> and password <password>
    Then Click on login button and user should be able to login successfully

    Examples:
      | url                                    | username                    | password        |
      | https://qautomation.my.salesforce.com/ | priyanka.bhadoria@xfp.com  | XfilesPro@123   |

  @TC-Files-Login-003
  Scenario: Verify login page elements before file operations
    Given I navigate to https://qautomation.my.salesforce.com/
    Then I should see the login form with username and password fields
    And I should see the login button
    When I enter the Login Id as priyanka.bhadoria@xfp.com and password XfilesPro@123
    Then Click on login button and user should be able to login successfully
    And I should be redirected to Salesforce home page
