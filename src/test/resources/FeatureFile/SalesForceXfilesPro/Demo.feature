@Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfilePro-21-08-2025
Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
Then Go to "Leads" Object
Then Perform "New" Operation
And Create a "Lead" Object with below details
| Salutation   | First Name   | Last Name   | Company   | Street   | Product Interest   | Description   | Phone   |
| <Salutation> | <First Name> | <Last Name> | <Company> | <Street> | <Product Interest> | <Description> | <Phone> |
Then "Save" Record
And Wait for 1 seconds
Then Go to "Leads" Object
Then Search the Record in List view with "360logica"
And Wait for 1 seconds
And Open Searched Record
And Wait for 1 seconds
Then Verify the Record "Details" in "Non Console"
| Name                                  | Company   | Address  | Product Interest   | Description   | Phone          |
| <Salutation> <First Name> <Last Name> | <Company> | <Street> | <Product Interest> | <Description> | (123) 456-7890 |
Then Change Status to "Working - Contacted"
Then Covert Entity to "converted"
And Update the "Account" with "New" Account Details on "Convert Lead"
| Account Name   |
| <Account Name> |
And Update the "Contact" with "Existing" Account Details on "Convert Lead"
| Contact Search   |
| <Contact Search> |
And Update the "Opportunity" with "New" Account Details on "Convert Lead"
| Opportunity Name   |
| <Opportunity Name> |
Then Click on "Convert" Record in "Non Console"
Then Go to "Opportunities" Object
Then Search the Record in List view with "Ashwani 360logica"
And Wait for 1 seconds
And Open Searched Record
Then Verify the Record "Details" in "Non Console"
| Opportunity Name   |
| <Opportunity Name> |
Then Go to "Accounts" Object
Then Search the Record in List view with "360Logica Updated"
And Wait for 1 seconds
And Open Searched Record
Then Verify the Record "Details" in "Non Console"
| Account Name   |
| <Account Name> |


Examples:
| Salutation | First Name | Last Name | Company   | Street      | Product Interest | Description      | Phone      | Account Name      | Contact Search | Opportunity Name  |
| Mr.        | John       | Doe       | 360Logica | Test Street | GC1000 series    | Test Description | 1234567890 | 360Logica Updated | Ashwani        | Ashwani 360logica |







@Sanity @Regression @smoke @MOBILE @Dev @test1 @customer2 @DemoMobile-23042025
Scenario Outline: Sample Mobile Test
Given Click on the Search Area
When I enter the "<SearchKey>" into Search area
When Verify the result displayed "<Expected>"

Examples:
| SearchKey | Expected |
| Wiki      | Wiki     |
| Wiki      | Wiki1    |

@Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @TC-Salesforce-1010 @DemoWeb-23052025
Scenario Outline: Submit a new requisition with valid data
When Login into Salesforce
Given Open "Saksoft HRMS" from App Launcher
Then Go to "Positions" Object
Then Perform "Add Requisition" Operation
And Wait for 10 seconds
And Switch to the frame named "accessibility title"
And Wait for 10 seconds
And Create a "Department" Object with below details
| Subsidiary   | Business Unit   | Client   |
| <Subsidiary> | <Business Unit> | <Client> |
And Create a "Requisition" Object with below details
| Job Title   | Priority   | Skills   |
| <Job Title> | <Priority> | <Skills> |
And Fill a custom form with below details
| Job Description   |
| <Job Description> |
And Create a "Job Location Details" Object with below details
| Work Location   | No Of Positions   | Job Shifts   |
| <Work Location> | <No Of Positions> | <Job Shifts> |

Then Perform "Save & Next" Operation
And Create a "Compensation" Object with below details
| Budget   | Company Designation   | Recruiting Manager   |
| <Budget> | <Company Designation> | <Recruiting Manager> |
And Create a "Experiences & Other Details" Object with below details
| Min. Experience   | Requisition Domain   | Max. Experience   |
| <Min. Experience> | <Requisition Domain> | <Max. Experience> |
And Fill a custom form with below details
| Mandatory Skills   | Responsibilities   |
| <Mandatory Skills> | <Responsibilities> |
And Update Below Dates
| Submission End Date |
| 2025-05-30          |
And Perform "Submit" Operation

Then Perform "Save & Next" Operation
And Create a "EVALUATION AND APPROVAL TEAM" Object with below details
| Interviewer   |
| <Interviewer> |
And Wait for 10 seconds
And Fill a custom form with below details
| Evalutation Type   |
| <Evalutation Type> |
Then Perform "Save & Next" Operation

Examples:
| Subsidiary  | Business Unit | Client | Job Title | Job Description | Skills          | Priority | Work Location | No Of Positions | Job Shifts | Budget | Company Designation | Recruiting Manager | Min. Experience | Requisition Domain | Mandatory Skills | Max. Experience | Responsibilities | Interviewer  | Evalutation Type      |
| Saksoft Inc | Others        | TU     | Sr. QA    | Test Automation | Java,Playwright | Medium   | Noida         | 1               | Day India  | 1      | Sr. QA              | Ritu Singh         | 2               | Engineering        | Java,Selenium    | 7               | Test Automation  | Employee 001 | First Round Interview |


@Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @TC-Salesforce-1010 @DemoDotCom-11062025
Scenario Outline: Create Job Functions,Training Plan and Requirement and complete the training as user
When Login into Salesforce
Given Open "Training Center" from App Launcher
Then Perform "Job Functions" Operation
And Wait for 1 seconds
Then Perform "New" Operation
And Wait for 1 seconds
#    # Job Function Creation
And Create a "Create Job Function" Object with below details
| Job Function/Group Name |
| Test Job-Random         |
Then "Next" Wizard
And Wait for 1 seconds

And Select "Select All" Checkbox
And Wait for 1 seconds
Then "Next" Wizard
And Wait for 1 seconds
Then "Finish" Wizard
And Wait for 1 seconds



#    # Training Plan Creation
#    Then Perform "Training Plans" Operation
#    And Wait for 2 seconds
#    Then Perform "New" Operation
#    And Create a "Create Training Plan" Object with below details
#      | Training Plan Name   |
#      | Test Training Plan-Random |
#    Then "Next" Wizard
#    And Search for Record "NewlyAddedJobFunction"
#    And Select "Select All" Checkbox
#    And Wait for 1 seconds
#    Then "Next" Wizard
#    And Wait for 1 seconds
#    Then "Finish" Wizard
#    And Wait for 1 seconds
##    # Training Requirements Creation
#    Then Perform "Training Requirements" Operation
#    And Wait for 1 seconds
#    Then Perform "New" Operation
#    And Create a "Create a new Training Requirement" Object with below details
#      | Training Requirement Name  |Type|Master Document|Days to Complete|
#      | Test Requirement-Random |Awareness      |MT Test| 10                      |
#    Then "Next" Wizard
#    Then Add "NewlyAddedTrainingPlan"
#    Then "Next" Wizard
#    And Create a "Requirement Revision Details" Object with below details
#      | Training Objective  |Training Purpose|
#      | Awareness |Testing Training Center      |
#    Then "Next" Wizard
#    Then "Finish" Wizard
#    And Wait for 1 seconds
##    # Uploading the Training Content in New Training Requirement Revision
#    And Search the Record in List view with "NewlyAddedTestRequirement"
#    And Open Searched Record
#    And Wait for 2 seconds
#    And Switch to the Next Tab
#    And Wait for 2 seconds
#    Then Upload the "RunTimeAdvancedCalls_SCORM20043rdEdition (1).zip" file in "Initial File Upload" section
#    And Switch to the parent Window
#    And Wait for 3 seconds
# #    # Initiating the Training Assignment
#    Then Perform "Initiate Training Creation" Operation
#    And Wait for 3 seconds
#    And Switch to the Next Tab
#    Then Click on the Refresh button in the browser
#    And Wait for 5 seconds
#    And Open Searched Record
#    And Wait for 2 seconds
#    And Switch to the 3 Tab
#    And Wait for 3 seconds
#    And Scroll the screen
# #   #Lauching the SCORM Course and Completing
#    And Click on "View All" link for the Article "Training Assignments"
#    And Open Assignment for the user "Prem Admin User"
#    Then Verify the Document Loaded
#    And Click on "SCORM Preview" tab
#    And Click on "Launch Course" Button in normal mode
#    And Wait for 10 seconds
#    And Switch to the 4 Tab
#    And Click on element "Next" on the "Salesforce" page 14 times
#
#    And Wait for 1 seconds
#    And Switch to the frame named "contentFrame"
##   #Completing the Test as User
#    Then Submit the Exam
#      | Field Name                                                                 | Value                     |
#      | The rules of golf are maintained by:'?                                       | USGA and Royal and Ancient|
#      | A score of two under par on a given hole is known as a(n):                 | eagle                     |
#      | A typical golf course has ____ holes                                      | 18                        |
#      | In stableford scoring, the highest score wins.                            | True                      |
#      | Par for a 175 yard hole is typically                                      | 3                         |
#      | When another player is attempting a shot, it is best to stand             | Out of the player's line of sight |
#      | Generally sand trap rakes should be left outside of the hazard            | True                      |
#      | The player with the best score on previous hole tees off                  | First                     |
#      | Which formula is used to calculate the 'course handicap'?                 | Course Handicap = Handicap index * Slope Rating / 113 |
#      | Golfer B wins the match be how many strokes?                              | 1                         |
#      | A 'scratch golfer' has a handicap of ___                                  | 0                         |
#      | how many strokes will Golfer A have to give Golfer B in match play?       | 2                         |
#      | To make friends on the golf course, you should play really slowly.        | False                     |
#      | Knickers indicate a refined sense of style.                               | False                     |
#      | You should take your score very seriously if you want to have a lot of fun on the course.         | False                     |
#    Then Switch to the parent page
#    And Click on element "Exit" on the "Salesforce" page
#    And Wait for 10 seconds
#    Then Switch to the 3 Tab
#    And Wait for 15 seconds
##   #Verify the Score
#    Then Verify the Below Fields
#    |SCORM Score|SCORM Result|SCORM Completion Status|
#    |100.00         |Passed      |Completed              |
##   #E-Sign the Score
#    And Perform "Actions" Operation
#    And Click on "Sign Training Completion" icon
#    And Do the E-sign
#    # Re -take the Examples:
#    And Wait for 3 seconds
#    And Click on "SCORM Preview" tab
##    And Click on "Launch Course" Button in normal mode
#    And Wait for 10 seconds
#    And Switch to the 4 Tab
#    And Click on element "Next" on the "Salesforce" page 14 times
#    And Wait for 1 seconds
#    And Switch to the frame named "contentFrame"
##   #Completing the Test as User
#    Then Submit the Exam
#      | Field Name                                                                 | Value                     |
#      | The rules of golf are maintained by:'?                                       | USGA and Royal and Ancient|
#      | A score of two under par on a given hole is known as a(n):                 | eagle                     |
#      | A typical golf course has ____ holes                                      | 18                        |
#      | In stableford scoring, the highest score wins.                            | True                      |
#      | Par for a 175 yard hole is typically                                      | 3                         |
#      | When another player is attempting a shot, it is best to stand             | Out of the player's line of sight |
#      | Generally sand trap rakes should be left outside of the hazard            | True                      |
#      | The player with the best score on previous hole tees off                  | First                     |
#      | Which formula is used to calculate the 'course handicap'?                 | Course Handicap = Handicap index * Slope Rating / 113 |
#      | Golfer B wins the match be how many strokes?                              | 1                         |
#      | A 'scratch golfer' has a handicap of ___                                  | 0                         |
#      | how many strokes will Golfer A have to give Golfer B in match play?       | 2                         |
#
#    Then Switch to the parent page
#    And Click on element "Exit" on the "Salesforce" page
#    And Wait for 10 seconds
#    Then Switch to the 3 Tab
#    And Wait for 15 seconds
##   #Re Verify the Score
#    Then Verify the Below Fields
#      |SCORM Score|SCORM Result|SCORM Completion Status|
#      |100.00         |Passed      |Completed              |

Examples:
| Subsidiary  | Business Unit | Client | Job Title | Job Description | Skills          | Priority | Work Location | No Of Positions | Job Shifts | Budget | Company Designation | Recruiting Manager | Min. Experience | Requisition Domain | Mandatory Skills | Max. Experience | Responsibilities | Interviewer  | Evalutation Type      |
| Saksoft Inc | Others        | TU     | Sr. QA    | Test Automation | Java,Playwright | Medium   | Noida         | 1               | Day India  | 1      | Sr. QA              | Ritu Singh         | 2               | Engineering        | Java,Selenium    | 7               | Test Automation  | Employee 001 | First Round Interview |
| Saksoft Inc | Others        | TU     | Sr. QA    | Test Automation | Java,Playwright | Medium   | Noida         | 1               | Day India  | 1      | Sr. QA              | Ritu Singh         | 2               | Engineering        | Java,Selenium    | 7               | Test Automation  | Employee 001 | First Round Interview |

#      | Saksoft Inc | Others        | TU     | Sr. QA    | Test Automation | Java,Playwright | Medium   | Noida         | 1               | Day India  | 1        |  Sr. QA                     |  Ritu Singh   |   2               | Engineering          | Java,Selenium     |  7                |Test Automation      |  Employee 001                |First Round Interview                    |




@Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @TC-Salesforce-1010 @Revonova-1
Scenario Outline: Create Job Functions,Training Plan and Requirement and complete the training as user
When Login into Salesforce
Then Go to "Loads" Object
Then Perform "New" Operation
And Create a "Loads" Object with below details
| Customer Search |
| Boston Beans    |
And Click on element "Searchresult" with text "Boston Beans - 526 Beacon Street, Boston MA 02215 - Customer;Shipper/Consignee" on the "salesforce" page

And Create a "Loads" Object with below details
| Mode |
| LTL  |
And Wait for 10 seconds




Examples:
| Subsidiary   | Business Unit | Client | Job Title | Job Description | Skills          | Priority | Work Location | No Of Positions | Job Shifts | Budget | Company Designation | Recruiting Manager | Min. Experience | Requisition Domain | Mandatory Skills | Max. Experience | Responsibilities | Interviewer  | Evalutation Type      |
| Saksoft Inc  | Others        | TU     | Sr. QA    | Test Automation | Java,Playwright | Medium   | Noida         | 1               | Day India  | 1      | Sr. QA              | Ritu Singh         | 2               | Engineering        | Java,Selenium    | 7               | Test Automation  | Employee 001 | First Round Interview |
| Saksoft Inc1 | Others        | TU     | Sr. QA    | Test Automation | Java,Playwright | Medium   | Noida         | 1               | Day India  | 1      | Sr. QA              | Ritu Singh         | 2               | Engineering        | Java,Selenium    | 7               | Test Automation  | Employee 001 | First Round Interview |

@Sanity @Regression @smoke @WEB @Dev @POC  @Salesforce @Demo @XfilePro-01-10-2025
Scenario Outline: Upload Files in the bisync <TestCase_ID>:<Test Case Name>-<AccountName>
When Login into Salesforce
Given Open "Accounts" from App Launcher
Then Search the Record in List view with "<AccountName>"
And Wait for 1 seconds
And Open Searched Record
And Go to "XFP" in "Non Console"
Then Upload the "<Filename>" file in "Upload Files" section By "<Replace>"
And Wait for 1 seconds
And Click on "Done" Button in normal mode
Then Verify text "File(s) successfully attached." is present on the "salesforce" page

Examples:
| TestCase_ID | Test Case Name                                                          | AccountName | Filename                     | Replace |
| TC1         | Check uploading a file and verify whether file is present in the bisync- File Formate .xlsx | niteshdemo   | Sharepoint Azure Upload.xlsx | True    |
| TC2         | Check uploading a file and verify whether file is present in the bisync- File Formate .mp3 | niteshdemo  | (Bryan Adams) EVERYTHING I DO.mp3| True    |
| TC3         | Check uploading a file and verify whether file is present in the bisync- File Formate .csv | niteshdemo   | TestCSVfile.csv | True    |
| TC4         | Check uploading a file and verify whether file is present in the bisync- File Formate .dng | niteshdemo   | TestDNGfile.dng | True    |
| TC5         | Check uploading a file and verify whether file is present in the bisync- File Formate .doc | niteshdemo   | TestDocFile.doc | True    |
| TC6         | Check uploading a file and verify whether file is present in the bisync- File Formate .docx | niteshdemo  | TestDocxFile.docx | True    |
| TC7         | Check uploading a file and verify whether file is present in the bisync- File Formate .jpeg | niteshdemo   | TestJPEGfile.jpeg | True    |
| TC8         | Check uploading a file and verify whether file is present in the bisync- File Formate .jpg | niteshdemo   | TestJPGfile.jpg | True    |
| TC9         | Check uploading a file and verify whether file is present in the bisync- File Formate .mp4 | niteshdemo   | TestMP4File.mp4 | True    |
| TC10         | Check uploading a file and verify whether file is present in the bisync- File Formate .png | niteshdemo  | TestPNGfile.png | True    |
| TC11         | Check uploading a file and verify whether file is present in the bisync- File Formate .pptx | niteshdemo   | TestpptxFile.pptx | True    |
| TC12         | Check uploading a file and verify whether file is present in the bisync- File Formate .webM | niteshdemo   | TestWEBMFile.webM | True    |
| TC13         | Check uploading a file and verify whether file is present in the bisync- File Formate .xls | niteshdemo  | TestXLSfile.xls | True    |
| TC14         | Check uploading a file and verify whether file is present in the bisync- File Formate .txt | niteshdemo  | TestTXTfile.txt | True    |
| TC15         | Check uploading a file and verify whether file is present in the bisync- File Formate .wav | niteshdemo  | TestWAVFile.wav | True    |

#      | TC2         | Check uploading a file and verify whether file is present in the bisync | XfilesPro   | Sharepoint Azure Upload.xlsx | False   |

