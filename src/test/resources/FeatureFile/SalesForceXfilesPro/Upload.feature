Feature:  As a Salesforce user
  I want to upload various file types to accounts
  So that I can manage files in the bisync system

  Background:
    Given Login into Salesforce
    And I navigate to the "Accounts" module

  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC1
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC1         | Check uploading a file and verify whether file is present in the bisync- File Formate .xlsx | niteshdemo   | Sharepoint Azure Upload.xlsx | True    |



  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC2
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC2         | Check uploading a file and verify whether file is present in the bisync- File Formate .mp3 | niteshdemo  | (Bryan Adams) EVERYTHING I DO.mp3| True    |

  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC3
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC3         | Check uploading a file and verify whether file is present in the bisync- File Formate .csv | niteshdemo   | TestCSVfile.csv | True    |



  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC4
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC4         | Check uploading a file and verify whether file is present in the bisync- File Formate .dng | niteshdemo   | TestDNGfile.dng | True    |


  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC5
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC5         | Check uploading a file and verify whether file is present in the bisync- File Formate .doc | niteshdemo   | TestDocFile.doc | True    |


  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC6
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC6         | Check uploading a file and verify whether file is present in the bisync- File Formate .docx | niteshdemo  | TestDocxFile.docx | True    |



  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC7
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC7         | Check uploading a file and verify whether file is present in the bisync- File Formate .jpeg | niteshdemo   | TestJPEGfile.jpeg | True    |


  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC8
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC8         | Check uploading a file and verify whether file is present in the bisync- File Formate .jpg | niteshdemo   | TestJPGfile.jpg | True    |



  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC9
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC9         | Check uploading a file and verify whether file is present in the bisync- File Formate .mp4 | niteshdemo   | TestMP4File.mp4 | True    |


  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC10
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC10         | Check uploading a file and verify whether file is present in the bisync- File Formate .png | niteshdemo  | TestPNGfile.png | True    |


  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC11
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC11         | Check uploading a file and verify whether file is present in the bisync- File Formate .pptx | niteshdemo   | TestpptxFile.pptx | True    |



  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC12
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC12         | Check uploading a file and verify whether file is present in the bisync- File Formate .webM | niteshdemo   | TestWEBMFile.webM | True    |


  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC13
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC13         | Check uploading a file and verify whether file is present in the bisync- File Formate .xls | niteshdemo  | TestXLSfile.xls | True    |

  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC14
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC14         | Check uploading a file and verify whether file is present in the bisync- File Formate .txt | niteshdemo  | TestTXTfile.txt | True    |


  @Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfileProuploadfile @TC15
  Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
      | TC15         | Check uploading a file and verify whether file is present in the bisync- File Formate .wav | niteshdemo  | TestWAVFile.wav | True    |

  @Sanity @Regression @WEB @POC @MultipleUpload @TC16
  Scenario Outline: Upload multiple files simultaneously in <TestCase_ID>:<Test Case Name>-<AccountName>
    Given I search for account "<AccountName>"
    And Wait for 1 seconds
    And Open Searched Record under upload module
    And Go to Details tab under upload module
    Then Upload the multiple files "<Filenames>" in "Upload Files" section By "<Replace>" under upload module
    And Wait for 5 seconds
    And Click on "Done" Button in normal mode under upload module
    Then Verify text "File(s) successfully attached." is present on the "salesforce" page

    Examples:
      | TestCase_ID | Test Case Name                                          | AccountName | Filenames                                                        | Replace |
      | TC16        | Verify multiple file upload simultaneously functionality | niteshdemo  | TestDocFile.doc,TestCSVfile.csv,TestPNGfile.png,TestJPEGfile.jpeg | True    |
