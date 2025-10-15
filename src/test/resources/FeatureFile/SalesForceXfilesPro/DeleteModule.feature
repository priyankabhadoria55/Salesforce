Feature:  As a Salesforce user
  Delete functionality for files and folder/Template folder
  So that I can manage files in the bisync system

  Background:
    Given Login into Salesforce
    And I navigate to the "Accounts" record module

  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileUploadDelete @TC1
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given Perform "New" Operation for create new record
    And Wait for 2 seconds
    And Create new Account with fill all the fields with random generated data under delete module
    Then "Save" new Record
    And Wait for 5 seconds
    And Go to Details tab under newly added account under delete module
    And Wait for 5 seconds
    Then Upload the multiple files "<Filenames>" in "Upload Files" section By "<Replace>" under upload delete module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload delete module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" upload delete module page
    And I Select a single file
    And Wait for 3 seconds
    Then Verify Delete button should be present


    Examples:
      | TestCase_ID | Test Case Name                                           | AccountName | Filenames                                                         | Replace |
      | TC16        | Verify multiple file upload simultaneously functionality | niteshdemo  | TestDocFile.doc,TestCSVfile.csv,TestPNGfile.png,TestJPEGfile.jpeg | True    |



