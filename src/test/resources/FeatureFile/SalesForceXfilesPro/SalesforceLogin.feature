@Sanity @Regression @smoke @WEB @Dev @POC @Salesforce @Login @SalesforceLogin-2025
Feature: Salesforce Login Automation
  As a user
  I want to login to Salesforce application
  So that I can access the Salesforce platform with my credentials

  @TC-Salesforce-Login-001
  Scenario: Login to Salesforce with valid credentials
    Given I navigate to Salesforce login page "https://qautomation.my.salesforce.com/"
    When I enter username "priyanka.bhadoria@xfp.com"
    And I enter password "XfilesPro@123"
    And I click on Login button
    Then I should be successfully logged into Salesforce
    And I should see the Salesforce home page

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

  @TC-Salesforce-Login-003
  Scenario: Verify login page elements
    Given I navigate to Salesforce login page "https://qautomation.my.salesforce.com/"
    Then I should see username field
    And I should see password field
    And I should see login button
    And I should see "Remember me" checkbox
