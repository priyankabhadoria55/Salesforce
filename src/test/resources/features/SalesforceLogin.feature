Feature: Salesforce Login Functionality
  As a user
  I want to login to Salesforce application
  So that I can access the application features

  @SmokeTest @Login
  Scenario: Successful login to Salesforce application
    Given I am on the Salesforce login page
    When I enter username "priyanka.bhadoria@xfp.com"
    And I enter password "XfilesPro@123"
    And I click on login button
    Then I should be successfully logged into the application

  @Login @Positive
  Scenario Outline: Login with different credentials
    Given I am on the Salesforce login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I click on login button
    Then I should be successfully logged into the application

    Examples:
      | username                  | password      |
      | priyanka.bhadoria@xfp.com | XfilesPro@123 |