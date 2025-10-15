package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import pageHelper.bddDriver;
import utils.PropertyReader;

/**
 * FilesFeatureDemo - Demo class showing how to use the files.feature login functionality
 * This class demonstrates different ways to use the loginModule step definitions
 */
public class FilesFeatureDemo {

    public static void main(String[] args) {
        try {
            System.out.println("🚀 Files Feature Login Automation Demo");
            System.out.println("=====================================");
            
            // Demo 1: Show feature file scenarios
            demoFeatureFileScenarios();
            
            // Demo 2: Show step definition usage
            demoStepDefinitionUsage();
            
            // Demo 3: Show utility methods
            demoUtilityMethods();
            
        } catch (Exception e) {
            System.out.println("❌ Demo failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Demo: Show feature file scenarios
     */
    public static void demoFeatureFileScenarios() throws Exception {
        System.out.println("\n📋 Demo 1: Feature File Scenarios");
        System.out.println("----------------------------------");
        
        System.out.println("Available scenarios in files.feature:");
        System.out.println("1. @TC-Files-Login-001: Basic login scenario");
        System.out.println("   Given I navigate to https://qautomation.my.salesforce.com/");
        System.out.println("   When I enter the Login Id as priyanka.bhadoria@xfp.com and password XfilesPro@123");
        System.out.println("   Then Click on login button and user should be able to login successfully");
        System.out.println("");
        
        System.out.println("2. @TC-Files-Login-002: Data-driven login scenario");
        System.out.println("   Given I navigate to <url>");
        System.out.println("   When I enter the Login Id as <username> and password <password>");
        System.out.println("   Then Click on login button and user should be able to login successfully");
        System.out.println("");
        
        System.out.println("3. @TC-Files-Login-003: Login with form validation");
        System.out.println("   Given I navigate to https://qautomation.my.salesforce.com/");
        System.out.println("   Then I should see the login form with username and password fields");
        System.out.println("   And I should see the login button");
        System.out.println("   When I enter the Login Id as priyanka.bhadoria@xfp.com and password XfilesPro@123");
        System.out.println("   Then Click on login button and user should be able to login successfully");
        System.out.println("   And I should be redirected to Salesforce home page");
        
        System.out.println("✅ Feature file scenarios documented");
    }

    /**
     * Demo: Show step definition usage
     */
    public static void demoStepDefinitionUsage() throws Exception {
        System.out.println("\n📋 Demo 2: Step Definition Usage");
        System.out.println("---------------------------------");
        
        System.out.println("Available step definitions in loginModule.java:");
        System.out.println("");
        System.out.println("@Given(\"I navigate to {string}\")");
        System.out.println("public void iNavigateTo(String url)");
        System.out.println("// Navigates to the specified URL and waits for page load");
        System.out.println("");
        
        System.out.println("@When(\"I enter the Login Id as {string} and password {string}\")");
        System.out.println("public void iEnterTheLoginIdAndPassword(String username, String password)");
        System.out.println("// Enters username and password in the login form");
        System.out.println("");
        
        System.out.println("@Then(\"Click on login button and user should be able to login successfully\")");
        System.out.println("public void clickOnLoginButtonAndVerifySuccess()");
        System.out.println("// Clicks login button and verifies successful login");
        System.out.println("");
        
        System.out.println("@Then(\"I should see the login form with username and password fields\")");
        System.out.println("public void iShouldSeeTheLoginFormWithFields()");
        System.out.println("// Verifies that login form elements are present");
        System.out.println("");
        
        System.out.println("@And(\"I should see the login button\")");
        System.out.println("public void iShouldSeeTheLoginButton()");
        System.out.println("// Verifies that login button is present");
        System.out.println("");
        
        System.out.println("@And(\"I should be redirected to Salesforce home page\")");
        System.out.println("public void iShouldBeRedirectedToSalesforceHomePage()");
        System.out.println("// Verifies redirection to Salesforce home page");
        
        System.out.println("✅ Step definitions documented");
    }

    /**
     * Demo: Show utility methods
     */
    public static void demoUtilityMethods() throws Exception {
        System.out.println("\n📋 Demo 3: Utility Methods");
        System.out.println("--------------------------");
        
        System.out.println("Available utility methods in loginModule.java:");
        System.out.println("");
        System.out.println("• performLogin(url, username, password)");
        System.out.println("  - Performs complete login process");
        System.out.println("  - Returns boolean indicating success/failure");
        System.out.println("");
        
        System.out.println("• isLoggedIn()");
        System.out.println("  - Checks if user is currently logged in");
        System.out.println("  - Returns boolean");
        System.out.println("");
        
        System.out.println("• getCurrentUrl()");
        System.out.println("  - Gets current page URL");
        System.out.println("  - Returns String");
        System.out.println("");
        
        System.out.println("• getPageTitle()");
        System.out.println("  - Gets current page title");
        System.out.println("  - Returns String");
        System.out.println("");
        
        System.out.println("• takeScreenshot(fileName)");
        System.out.println("  - Takes screenshot of current page");
        System.out.println("  - Parameter: fileName (String)");
        
        System.out.println("✅ Utility methods documented");
    }

    /**
     * Example of how to use the loginModule in a test
     */
    public static void exampleTestUsage() throws Exception {
        System.out.println("\n📋 Example Test Usage");
        System.out.println("----------------------");
        
        System.out.println("// Initialize the loginModule");
        System.out.println("loginModule login = new loginModule(bddDriver);");
        System.out.println("");
        System.out.println("// Method 1: Use individual step methods");
        System.out.println("login.iNavigateTo(\"https://qautomation.my.salesforce.com/\");");
        System.out.println("login.iEnterTheLoginIdAndPassword(\"priyanka.bhadoria@xfp.com\", \"XfilesPro@123\");");
        System.out.println("login.clickOnLoginButtonAndVerifySuccess();");
        System.out.println("");
        System.out.println("// Method 2: Use utility method for complete login");
        System.out.println("boolean success = login.performLogin(");
        System.out.println("    \"https://qautomation.my.salesforce.com/\",");
        System.out.println("    \"priyanka.bhadoria@xfp.com\",");
        System.out.println("    \"XfilesPro@123\"");
        System.out.println(");");
        System.out.println("");
        System.out.println("// Check login status");
        System.out.println("if (login.isLoggedIn()) {");
        System.out.println("    System.out.println(\"User is logged in\");");
        System.out.println("    System.out.println(\"Current URL: \" + login.getCurrentUrl());");
        System.out.println("    System.out.println(\"Page Title: \" + login.getPageTitle());");
        System.out.println("}");
        
        System.out.println("✅ Example usage documented");
    }

    /**
     * Show how to run the tests
     */
    public static void showTestExecution() throws Exception {
        System.out.println("\n📋 Test Execution");
        System.out.println("------------------");
        
        System.out.println("To run the files.feature tests:");
        System.out.println("");
        System.out.println("1. Using Maven:");
        System.out.println("   mvn test -Dtest=FilesTestRunner");
        System.out.println("");
        System.out.println("2. Using TestNG:");
        System.out.println("   Right-click on FilesTestRunner.java > Run As > TestNG Test");
        System.out.println("");
        System.out.println("3. Run specific scenario:");
        System.out.println("   mvn test -Dtest=FilesTestRunner -Dcucumber.options=\"--tags @TC-Files-Login-001\"");
        System.out.println("");
        System.out.println("4. Run all files feature tests:");
        System.out.println("   mvn test -Dtest=FilesTestRunner -Dcucumber.options=\"--tags @Files\"");
        
        System.out.println("✅ Test execution methods documented");
    }
}
