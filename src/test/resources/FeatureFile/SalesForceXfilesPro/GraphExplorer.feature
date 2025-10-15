Feature: Microsoft Graph Explorer - Storage Setup

  As a developer
  I want to test Microsoft Graph API permissions
  So that I can verify storage setup functionality

  Background:
    Given I navigate to Microsoft Graph Explorer

  @Sanity @Regression @Smoke @WEB @Dev @POC @MicrosoftGraph @Demo @GraphExplorer-01-10-2025
  Scenario: Graph explorer test case for automation
    Given I navigate to Microsoft Graph Explorer
    When I sign in to Microsoft Graph Explorer
    And I select "POST" method and endpoint "https://graph.microsoft.com/v1.0/sites/b0a307de-3595-4a76-a765-f26c55a9baec/permissions"
    And I paste the following JSON in the request body:
      """
      {
        "roles": [
            "write"
        ],
        "grantedToIdentities": [
            {
                "application": {
                    "id": "48a317a8-524e-4593-aa9a-3ea97e4cd2e8",
                    "displayName": "AfconsInfraQA"
                }
            }
        ]
      }
      """
    When I click on "Run Query" button
    Then I should see a successful response from the API
    And I should verify the response contains the expected data structure