package pageHelper.web.SalesForceXfilesPro;

import core.baseDriverHelper;
import core.webHelper;
import pageHelper.bddDriver;
import utils.PropertyReader;

/**
 * SalesforceLoginDemo - Demo class showing how to use Salesforce login automation
 * This class demonstrates different ways to use the Salesforce login functionality
 */
public class SalesforceLoginDemo {

    public static void main(String[] args) {
        try {
            System.out.println("🚀 Salesforce Login Automation Demo");
            System.out.println("=====================================");
            
            // Demo 1: Login using config.properties
            demoLoginWithConfig();
            
            // Demo 2: Login with custom credentials
            demoLoginWithCustomCredentials();
            
            // Demo 3: Using the utility class
            demoUsingUtilityClass();
            
        } catch (Exception e) {
            System.out.println("❌ Demo failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Demo: Login using credentials from config.properties
     */
    public static void demoLoginWithConfig() throws Exception {
        System.out.println("\n📋 Demo 1: Login using config.properties");
        System.out.println("----------------------------------------");
        
        PropertyReader pr = new PropertyReader();
        String url = pr.readproperty("xFilesPro_application_url");
        String username = pr.readproperty("XfilePro_user");
        String password = pr.readproperty("XfilePro_password");
        
        System.out.println("URL: " + url);
        System.out.println("Username: " + username);
        System.out.println("Password: " + "******");
        
        // Note: This is just a demonstration of how to read config
        // Actual login would require WebDriver initialization
        System.out.println("✅ Config values read successfully");
    }

    /**
     * Demo: Login with custom credentials
     */
    public static void demoLoginWithCustomCredentials() throws Exception {
        System.out.println("\n📋 Demo 2: Login with custom credentials");
        System.out.println("----------------------------------------");
        
        String url = "https://qautomation.my.salesforce.com/";
        String username = "priyanka.bhadoria@xfp.com";
        String password = "XfilesPro@123";
        
        System.out.println("URL: " + url);
        System.out.println("Username: " + username);
        System.out.println("Password: " + "******");
        
        System.out.println("✅ Custom credentials set successfully");
    }

    /**
     * Demo: Using the utility class
     */
    public static void demoUsingUtilityClass() throws Exception {
        System.out.println("\n📋 Demo 3: Using SalesforceLoginUtility");
        System.out.println("----------------------------------------");
        
        System.out.println("Available methods in SalesforceLoginUtility:");
        System.out.println("• loginToSalesforceWithConfig() - Login using config.properties");
        System.out.println("• loginToSalesforce(url, username, password) - Login with custom credentials");
        System.out.println("• verifyLoginSuccess() - Verify if login was successful");
        System.out.println("• isLoggedIn() - Check if currently logged in");
        System.out.println("• logoutFromSalesforce() - Logout from Salesforce");
        System.out.println("• getCurrentUrl() - Get current URL");
        System.out.println("• getPageTitle() - Get page title");
        System.out.println("• takeScreenshot(fileName) - Take screenshot");
        
        System.out.println("✅ Utility class methods documented");
    }

    /**
     * Example of how to use the login automation in a test
     */
    public static void exampleTestUsage() throws Exception {
        System.out.println("\n📋 Example Test Usage");
        System.out.println("----------------------");
        
        // This is how you would use it in an actual test
        System.out.println("// Initialize the utility");
        System.out.println("SalesforceLoginUtility loginUtil = new SalesforceLoginUtility(bddDriver);");
        System.out.println("");
        System.out.println("// Login using config.properties");
        System.out.println("boolean loginSuccess = loginUtil.loginToSalesforceWithConfig();");
        System.out.println("");
        System.out.println("// Or login with custom credentials");
        System.out.println("boolean loginSuccess = loginUtil.loginToSalesforce(");
        System.out.println("    \"https://qautomation.my.salesforce.com/\",");
        System.out.println("    \"priyanka.bhadoria@xfp.com\",");
        System.out.println("    \"XfilesPro@123\"");
        System.out.println(");");
        System.out.println("");
        System.out.println("// Verify login was successful");
        System.out.println("if (loginSuccess) {");
        System.out.println("    System.out.println(\"Login successful!\");");
        System.out.println("} else {");
        System.out.println("    System.out.println(\"Login failed!\");");
        System.out.println("}");
        
        System.out.println("✅ Example usage documented");
    }
}
