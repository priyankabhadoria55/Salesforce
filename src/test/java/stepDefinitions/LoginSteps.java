package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import pages.LoginPage;
import utils.DriverManager;

public class LoginSteps {
    
    private LoginPage loginPage;
    
    @Given("I am on the Salesforce login page")
    public void i_am_on_the_salesforce_login_page() {
        // Initialize the login page
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        // Verify we are on the login page
        Assert.assertTrue("Failed to navigate to Salesforce login page", 
                         loginPage.isOnLoginPage());
    }
    
    @When("I enter username {string}")
    public void i_enter_username(String username) {
        loginPage.enterUsername(username);
    }
    
    @When("I enter password {string}")
    public void i_enter_password(String password) {
        loginPage.enterPassword(password);
    }
    
    @And("I click on login button")
    public void i_click_on_login_button() {
        loginPage.clickLoginButton();
    }
    
    @Then("I should be successfully logged into the application")
    public void i_should_be_successfully_logged_into_the_application() {
        // Wait for page to load and verify login
        try {
            Thread.sleep(3000); // Give time for redirect
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Assert.assertTrue("Login was not successful", 
                         loginPage.isLoginSuccessful());
    }
    
    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertFalse("Error message should be displayed", 
                          errorMsg.isEmpty());
    }
}