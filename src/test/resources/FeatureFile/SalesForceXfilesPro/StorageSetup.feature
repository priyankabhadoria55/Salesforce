Feature: XFilesPro - SalesForce project

  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @TC-Salesforce-1010 @DemoWeb-25082025 @Commes @StorageSetupStep
  Scenario: Create a Standard Lead in Sales app
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I verify Cloud Storage hover message is displayed
    Then I Click on cloud storage button
    Then I should be on the Storage Type page
    And I should see "Select the storage provider" dropdown
    And I should verify that "None" is selected by default in the storage provider dropdown
    Then I should verify that Next button is in default disabled state
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I should verify that Next button is enabled after selecting SharePoint Azure
    Then I click on cancel button
    Then I should verify that clicking Cancel button navigates to Storage Setup page
    Then I Click on cloud storage button
    Then I should verify that storage provider dropdown lists all 5 options
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I Navigate to Appregistration screen By clicking next button
    Then I should verify that clicking Next without entering Client Id and Tenant Id shows validation messages
    Then I should verify Previous and Next button navigation functionality
    Then I should verify that clicking Next with only Tenant Id shows Client Id validation message
    Then I should verify Previous and Next button navigation functionality
    Then I should verify that clicking Next with only Client Id shows Tenant Id validation message
    Then I navigate back to Appregistration screen By clicking previous button in 4 times
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "1"
    Then I register the app in microsoft azure
    Then i navigate back to the application and i entered client id and tenant id
    Then I should verify navigation to Generate Client Secret sub tab
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "2"
    Then I Add client Secret
    Then I navigate back to Application and entered Client Secret
    Then I should verify navigation to Grant App Permissions sub tab
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "3"
    Then I add permission in azure devops
    Then I navigate back to Application and continue the storage setup flow
    Then I authenticate the storage setup


  @Sanity @Regression @smoke @WEB @Dev @POC @Demo  @Commes @StorageSetupStep @TC16
  Scenario: Check by Clicking on Authenticate button is able to authenticate with valid client id, client Secret
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I Click on cloud storage button
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I Navigate to Appregistration screen By clicking next button
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "1"
    Then I register the app in microsoft azure
    Then i navigate back to the application and i entered client id and tenant id
    Then I click next 4 times
    Then I should verify that clicking Next without entering Client Secret shows validation message


  @Sanity @Regression @smoke @WEB @Dev @POC @Demo  @Commes @StorageSetupStep @TC20
  Scenario: Check by Clicking on Authenticate button is able to authenticate with valid client id, client Secret
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I Click on cloud storage button
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I Navigate to Appregistration screen By clicking next button
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "1"
    Then I register the app in microsoft azure
    Then i navigate back to the application and i entered client id and tenant id
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "2"
    Then I Add client Secret
    Then I navigated back to the application, entered the client secret, and clicked Next
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "3"
    Then I add permission in azure devops
    Then I navigate back to Application and continue the storage setup flow
    Then I authenticate the storage setup
    Then I verify authentication success message is displayed


  @Sanity @Regression @smoke @WEB @Dev @POC @Demo  @Commes @StorageSetupStep @TC21
  Scenario: Check by clicking on Done button without entering any mandatory fields in Configure tab of storage setup
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I Click on cloud storage button
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I Navigate to Appregistration screen By clicking next button
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "1"
    Then I register the app in microsoft azure
    Then i navigate back to the application and i entered client id and tenant id
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "2"
    Then I Add client Secret
    Then I navigated back to the application, entered the client secret, and clicked Next
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "3"
    Then I add permission in azure devops
    Then I navigate back to Application and continue the storage setup flow
    Then I authenticate the storage setup
    Then I verify validation messages are displayed when clicking Done button without filling fields





  @Sanity @Regression @smoke @WEB @Dev @Commes @StorageSetupStep @TC24
  Scenario: Verify whether Sharepoint (Azure) is configured in Object setup page with the title same as the one from excel sheet
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I should verify that the newly configured Azure storage is displayed in Storage Setup page


  @Sanity @Regression @smoke @WEB @Dev @Commes @StorageSetupStep @TC25
  Scenario: Check whether after configuring storage setup is still showing Sharepoint options in select storage provider
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I verify Cloud Storage hover message is displayed
    Then I Click on cloud storage button
    Then I should be on the Storage Type page
    And I should verify that all storage providers are listed except GoogleDrive


  @Sanity @Regression @smoke @WEB @Dev @Salesforce @Commes @StorageSetupStep @TC28
  Scenario: Check by entering invalid client id and check whether Sharepoint Azure storage provider is configured successfully
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I Click on cloud storage button
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I Navigate to Appregistration screen By clicking next button
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "1"
    Then I register the app in microsoft azure
    Then i navigate back to the application and i entered invalid client id and valid tenant id
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "2"
    Then I Add client Secret
    Then I navigate back to Application and entered Client Secret
    Then I click on Authenticate button for invalid configuration With tabid "3"
    Then I should verify that Microsoft Signin error is displayed in Signin tab


  @Sanity @Regression @smoke @WEB @Dev @Salesforce @Commes @StorageSetupStep @TC29
  Scenario: Check by entering invalid tenant id and check whether Sharepoint Azure storage provider is configured successfully
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I Click on cloud storage button
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I Navigate to Appregistration screen By clicking next button
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "1"
    Then I register the app in microsoft azure
    Then i navigate back to the application and i entered valid client id and invalid tenant id
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "2"
    Then I Add client Secret
    Then I navigate back to Application and entered Client Secret
    Then I click on Authenticate button for invalid configuration With tabid "3"
    Then I should verify that Microsoft Signin error is displayed in Signin tab


  @Sanity @Regression @smoke @WEB @Dev @Salesforce @Commes @StorageSetupStep @TC30
  Scenario: Check by providing invalid client Secret and enabling api and authenticating is validating or allowing to create Sharepoint Azure storage provider
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I Click on cloud storage button
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I Navigate to Appregistration screen By clicking next button
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "1"
    Then I register the app in microsoft azure
    Then i navigate back to the application and i entered client id and tenant id
    Then I navigate back to Application and entered invalid Client Secret
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "2"
    Then I add permission in azure devops
    Then I navigate back to Application and click next 6 times
    Then I click on Authenticate button for invalid configuration with tabid "3"
    Then I navigate back to Application and should verify that Invalid client secret error is displayed in authentication tab

  @Sanity @Regression @smoke @WEB @Dev @POC @Demo  @Commes @StorageSetupStep @TC31
  Scenario: Create a Standard Lead in Sales app
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I Click on cloud storage button
    Then i select "SharePoint (Azure)" from storage provider dropdown
    Then I Navigate to Appregistration screen By clicking next button
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "1"
    Then I register the app in microsoft azure
    Then i navigate back to the application and i entered client id and tenant id
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "2"
    Then I Add client Secret
    Then I navigated back to the application, entered the client secret, and clicked Next
    Then I Navigate to microsoft azure tab by clicking on click here link With tabid "3"
    Then I add permission in azure devops
    Then I navigate back to Application and continue the storage setup flow
    Then I authenticate the storage setup






  @Sanity @Regression @smoke @WEB @Dev @Salesforce @Commes @StorageSetupStep @TC32 @TC33
  Scenario: Check whether clicking on info button is displaying information about the Storage provider
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I click on Info button for the first storage provider
    Then I should verify that info popup is displayed with storage configuration details


  @Sanity @Regression @smoke @WEB @Dev @Salesforce @Commes @StorageSetupStep @TC34
  Scenario: Check whether clicking on info button is displaying information about the Storage provider
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I click on Info button for the first storage provider
    Then I close the info popup
    Then I should verify that clicking close button the info popup navigates to Storage Setup page


  @Sanity @Regression @smoke @WEB @Dev @Salesforce @Commes @StorageSetupStep @TC35
  Scenario: Check whether clicking on info button is displaying information about the Storage provider
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I click on Re-Authentication button for the first storage provider
    Then I should verify that Re-Authentication popup is displayed
    Then I should verify that Secret Key field is displayed and censored
    Then I take a screenshot of the censored Secret Key field


  @Sanity @Regression @smoke @WEB @Dev @Salesforce @Commes @StorageSetupStep @TC36
  Scenario: Check whether clicking on info button is displaying information about the Storage provider
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I click on Re-Authentication button for the first storage provider
    Then I should verify that Re-Authentication popup is displayed
    Then I click on Re-Authentication button on popup


  @Sanity @Regression @smoke @WEB @Dev @Salesforce @Commes @StorageSetupStep @TC37
  Scenario: Check whether clicking on info button is displaying information about the Storage provider
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item
    Then Click on "storage_setup" from side nav
    Then I click on Re-Authentication button for the first storage provider
    Then I should verify that Re-Authentication popup is displayed
    Then I close the Re-Authentication popup
    Then I should verify that clicking close button the Re-Authentication popup navigates to Storage Setup page