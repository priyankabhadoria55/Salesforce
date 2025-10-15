Feature: XFilesPro- Object Setup Module
 #Given Login into xFilesPro for object setup
  @Sanity @Regression @smoke @WEB @ObjectSetup @AddObjectSetup @TC1
  Scenario: Create an Object Setup
    When Login into Salesforce
    Given  Go to "XfilesPro" Nav item tab
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    Then Click on Done for creating the Object Setup


  @Sanity @Regression @smoke @WEB @ObjectSetup @ObjectTabCases @TC2 @TC3 @TC4 @TC5
    Scenario: Verify Object selection, next and cancel
      When Login into Salesforce
      #When Click on App Launcher and search item XFilesPro for object setup testing
      And  Go to "XfilesPro" Nav item for object setup
      And Click on "object_setup" from side nav to setup object
      And I click New Object Setup button
      And Click on next button from object setup panel
      And Select an option like Assets from an object dropdown
      And Click on Cancel from object setup panel

  @Sanity @Regression @smoke @WEB @ObjectSetup @FolderStructureTab @TC7 @TC8 @TC9 @TC10 @TC11 @TC12 @TC13 @TC14
  Scenario: Verify Folder Structure - Commit, Edit, Preview and Proceed
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Assets from an object dropdown
    And Click on next button from object setup panel
    And Click on Commit button in the Folder Structure tab from object setup panel
    And Click on edit button in the Folder Structure tab from object setup panel
    And Click on cancel button in folder structure popup
    And Click on edit button in the Folder Structure tab from object setup panel
    And Click on proceed button in folder structure popup
    And Click on save from XFP Folder Structure Page link
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Assets from an object dropdown
    And Click on next button from object setup panel
    And Click on preview button in the Folder Structure tab from object setup panel
    And Click on close popup on preview folder structure popup from object setup panel
    And Click on next button from object setup panel

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @TC18
  Scenario: Verify Add Template flow with LowerCase, Uppercase, alpha numeric and special character and error handling
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Check hover on Add Template button from object setup panel
    And Click on Add Template button from object setup panel
    And Cancel on Add Template popup from object setup panel
    And Click on Add Template button from object setup panel
    And Add Template name with LowerCase, Uppercase, alpha numeric and special character and click on okay
    And Click on next button and matching the expected message from object setup panel

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @TemplateWithSameName @TC20
  Scenario: Create and validate Add Template with multiple inputs
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Check hover on Add Template button from object setup panel
    And Click on Add Template button from object setup panel
    And Cancel on Add Template popup from object setup panel
    And Click on Add Template button from object setup panel
    And Add Template name with LowerCase, Uppercase, alpha numeric and special character and click on okay
    And Click on Add Template button from object setup panel
    And Add Template with same name and click on okay, also verify the error message


  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @EditTemplateFlow @TC21 @TC22 @TC23
  Scenario: Verify Add Template flow and verified updated template
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Edit button for template
    And I cancel the edit template popup
    And I click on Edit button for template
    Then I edit the template name successfully and verified the updated template

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @DeleteTemplateFlow @TC24 @TC25
  Scenario: Verify Add Template flow and Delete Template
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on delete template
    And I cancel the delete template popup
    And I click on delete template
    Then I confirm the template has been deleted successfully

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddFolderTemplateFlow @TC26 @TC27
  Scenario: Verify Add Folder flow with Cancel and Okay popups and verify folder added successfully
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    And I cancel the add folder template popup
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddFolderWithSpecialChar @TC28
  Scenario: Verify Add Folder flow with LowerCase, Uppercase, alpha numeric and with special character
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter the folder name with LowerCase, Uppercase, alpha numeric and with special character and click on okay

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddFolderFlow @EditFolder @TC29 @TC30 @TC31
  Scenario: Verify Add Folder flow with Cancel and Okay popups and verify folder edited successfully
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    And I cancel the add folder template popup
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And I click on Edit folder from template structure panel
    And I cancel the edit template popup
    And I click on Edit folder from template structure panel
    Then I edit the folder name successfully and verified the updated folder name in template structure tab


  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddFolder @DeleteFolder @TC32 @TC33
  Scenario: Verify Add and delete folder flow in template structure
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And I click on delete folder button
    And I cancel the delete folder popup
    And I click on delete folder button
    Then I confirm the folder has been deleted successfully

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddFolder @ControlAccessToAnyone @TC34
  Scenario: Verify newly created folder control access set to anyone by default
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And I click on Control Access for the newly created folder
    Then Verify newly created folder has default Control Access set to Anyone and confirm with okay

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddFolder @ControlAccessToUser @TC35
  Scenario: Verify newly created folder control access to user
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And I click on Control Access for the newly created folder
    And I select the "Users" from the dropdown
    And Verify user search in Control Access popup
    Then Verify newly created folder access control to user and confirm with okay

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddFolder @ControlAccessToProfile @TC36
  Scenario: Verify newly created folder control access to profile and select system administrator profile
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And I click on Control Access for the newly created folder
    And I select the "Profile" from the control access popup
    And Verify select System Administration in Control Access popup
    Then Verify new folder access control set to Profile and confirm with Okay

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddFolder @AddSubFolder  @TC37
  Scenario: Verify creating folder and sub folder
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And I click on Add sub folder
    And I enter the name of sub folder and click on okay subfolder
    And I click on Add sub folder
    And I enter the name of sub folder and click on okay subfolder
    And I click on Add sub folder
    And I enter the name of sub folder and click on okay subfolder
    And I click on Add sub folder
    And I enter the name of sub folder and click on okay subfolder
    And I click on Add sub folder
    And I enter the name of sub folder and click on okay subfolder
    And I click on Add sub folder
    And I enter the name of sub folder and click on okay subfolder
    And I click on Add sub folder
    And I enter the name of sub folder and click on okay subfolder
    And I click on Add sub folder
    And I enter the name of sub folder and click on okay subfolder

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddMoreThan5Template @TC38
  Scenario: Check whether more than 5 template can be created for a one object
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And Click on Add Template button from object setup panel
    And Enter the Template name and save

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @NextMoveToTab @TC39 @TC40
  Scenario: Check by clicking on next button is moving to the multisite tab
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    Then Click on next button and verify Template Structure tab should be completed shows as green

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @TagsTabHover @TC41
  Scenario: Check whether mouse hover message is visible when mouse is hovered on Add tag
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And Click on next button from object setup panel
    Then Check Mouse hover on Add tag button in Tags tab


  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddTags @TC42
  Scenario: On click of add tag button, check whether delete button is present or not with empty row tag fields
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And Click on next button from object setup panel
    And Click on Add Tag button in tags tab
    Then I verify Name, Type and Delete fields are visible in Add Tag row

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddTags @TC43
  Scenario: Check whether clicking on next without entering any mandatory tag value fields is throwing any error message or not
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And Click on next button from object setup panel
    And Click on Add Tag button in tags tab
    Then Verify error message if the tags are not added and click on next

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddTags @DeleteTag @TC44
  Scenario: Check whether clicking on delete button is deleting the tag row containing Name, Type and Mapping field
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Click on next button after selecting the object as Asset
    And Click on next button from object setup panel
    And Click on Add Template button from object setup panel
    And Enter the Template name and save
    And I click on Add folder template
    Then I enter folder name and verified the folder has been added successfully
    And Click on next button from object setup panel
    And Click on Add Tag button in tags tab
    Then I click on delete button for clear the tag fields

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddTags @DeleteTag @TC45.0
  Scenario: Check by creating a tags with randem name and clicking on next, and Done
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Select a provider from object setup page
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on Add Tag button in tags tab
    And I enter the tag name
    And I select the tag Type as "Auto"
    And I select the object field as "Name"
    And Click on next button from object setup panel
    Then Click on Done for creating the Object Setup


  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddTags @AddTagWithSameName @TC45
  Scenario: Check by creating 2 tags with same name and clicking on next is throwing any error message or not.
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Select a provider from object setup page
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on Add Tag button in tags tab
    And I enter the tag name
    Then I enter the same tag name with type "Auto" and object field as "Name"
    And Click on Add Tag button in tags tab
    Then I enter the same tag name with type "Auto" and object field as "Name"
    And Click on Add Tag button in tags tab
    Then Delete the last blank tag
    And Click on next button and verify an expected error message

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddTags @AddTagWithNumbers @TC46
  Scenario: Check by providing tag name with only number and provider necessary field and click on next
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Select a provider from object setup page
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on Add Tag button in tags tab
    And I enter the tag name with numbers only
    Then I enter the same tag name with type "Auto" and object field as "Name"
    And Click on Add Tag button in tags tab
    Then Delete the last blank tag
    And Click on next button from object setup panel
    Then Click on Done for creating the Object Setup

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddTags @AddTagsWithManual @TC49
  Scenario: Check by add new tag and selecting manual from dropdown and Data type dropdown with options
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Select a provider from object setup page
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on Add Tag button in tags tab
    And I enter the tag name
    And I select the tag Type as "Manual"
    And I select the data type manual as "Text"
    And Click on next button from object setup panel
    Then Click on Done for creating the Object Setup

  @Sanity @Regression @smoke @WEB @ObjectSetup @AddTemplateFlow @AddTags @AddTagsWithManual @TC50
  Scenario: Check by creating a new manual tag with text option for Data type, mapping the field (Optional)
    When Login into Salesforce
    And  Go to "XfilesPro" Nav item for object setup
    And Click on "object_setup" from side nav to setup object
    And I click New Object Setup button
    And Select an option like Campaign from an object dropdown
    And Select a provider from object setup page
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on next button from object setup panel
    And Click on Add Tag button in tags tab
    And I enter the tag name
    And I select the tag Type as "Manual"
    And I select the data type manual as "Text"
    And I select the mapping field as "App Created By"
    And Click on next button from object setup panel
    Then Click on Done for creating the Object Setup