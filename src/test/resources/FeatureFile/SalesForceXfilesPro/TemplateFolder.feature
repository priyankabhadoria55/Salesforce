Feature: XFilesPro- Template Folder Module


  @Sanity @Regression @smoke @WEB @TemplateFolder @TC01 @TC02 @TC03
  Scenario: Template Module Add new record, select template, submit template, and refresh
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item tab to verify template folder in biSync
    When Click on App Launcher and search item as Account and click on Account
    And I click on the "New" button in Account page
    And I enter the account name as "Priyanka User1"
    And I click on the Save button for adding the new record
    #And I click on the created account name "Priyanka User1"
    And I click on the second Details tab in biSync
    And I select Template from the third More Actions dropdown from BiSync
    And I select the template "test template 1" from the dropdown
    And I click on the Submit button in Template popup
    And I click on the Refresh button on Account page for verifying created template folder is present or not

  @Sanity @Regression @smoke @WEB @TemplateFolder @TemplateSelectTwice @TC04
  Scenario: Check the template functionality by creating same template twice
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item tab to verify template folder in biSync
    When Click on App Launcher and search item as Account and click on Account
    And I click on the latest created Account from Account list page
    And I click on the second Details tab in biSync
    And I select Template from the third More Actions dropdown from BiSync
    And I select the template "test template 1" from the dropdown
    And I click on the Submit button in Template popup
    And I select the template "test template 1" from the dropdown
    And I click on the Submit button in Template popup

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @TC05
  Scenario: Verify Add Template flow with LowerCase, Uppercase, alpha numeric and special character and error handling
    When Login into Salesforce
#    Given  Go to "XfilesPro" Nav item tab to verify template folder in biSync
#    And Click on "object_setup" from side nav to setup object to verify template folder in biSync
#    And I click New Object Setup button to verify template folder in biSync
#    And Select an option like Lead from an object dropdown to verify template folder in biSync
#    Then Select a provider from object setup page to verify template folder in biSync
#    And Click on next button from object setup panel to verify template folder in biSync
#    And Click on next button from object setup panel to verify template folder in biSync
#
#    And Click on Add Template button from object setup panel for verifying template in biSync
#    And Enter the Template name and save for verifying template in biSync
#    And I click on Add folder from template for verifying template in biSync
#    Then I enter folder name and okay from popup for verifying template in biSync
#    And Click on next button from object setup panel to verify template folder in biSync
#    And Click on next button from object setup panel to verify template folder in biSync
#    Then Click on Done for creating the Object Setup to verify template folder in biSync
#    And Skip from manage column popup window
    When Click on App Launcher and search item as Lead and click on Lead to verify template folder in biSync
    And I click on the New button from Lead page
    And I fill the Lead details as "Ms." "Priyanka" "Bhadoria" "Saksoft Company" and click on Save button
    And I click on the most recently created Lead from the Lead list page
    And I click on settings gear icon for checking the biSync
    And I click on Edit page from settings for checking the biSync
    And I drag & drop the xFilesPro biSync
    And I select Template from the third More Actions dropdown from BiSync
    And I select the template "PriyankaTest Template" from the dropdown








