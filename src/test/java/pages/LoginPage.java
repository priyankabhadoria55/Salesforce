package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // URL for Salesforce login
    private static final String LOGIN_URL = "https://qautomation.my.salesforce.com/";
    
    // Page Factory - OR (Object Repository)
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "Login")
    private WebElement loginButton;
    
    // Alternative locators if the above don't work
    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameFieldAlt;
    
    @FindBy(xpath = "//input[@name='pw']")
    private WebElement passwordFieldAlt;
    
    @FindBy(xpath = "//input[@type='submit'][@name='Login']")
    private WebElement loginButtonAlt;
    
    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//span[@class='uiImage']")
    private WebElement userProfileIcon;
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    
    // Page Actions/Methods
    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
    }
    
    public void enterUsername(String username) {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            usernameField.clear();
            usernameField.sendKeys(username);
        } catch (Exception e) {
            // Try alternative locator
            wait.until(ExpectedConditions.visibilityOf(usernameFieldAlt));
            usernameFieldAlt.clear();
            usernameFieldAlt.sendKeys(username);
        }
    }
    
    public void enterPassword(String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            passwordField.clear();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            // Try alternative locator
            wait.until(ExpectedConditions.visibilityOf(passwordFieldAlt));
            passwordFieldAlt.clear();
            passwordFieldAlt.sendKeys(password);
        }
    }
    
    public void clickLoginButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (Exception e) {
            // Try alternative locator
            wait.until(ExpectedConditions.elementToBeClickable(loginButtonAlt));
            loginButtonAlt.click();
        }
    }
    
    public boolean isLoginSuccessful() {
        try {
            // Wait for either the user profile icon or home page element
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='uiImage']")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='slds-icon-waffle']")),
                ExpectedConditions.urlContains("lightning"),
                ExpectedConditions.urlContains("home")
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isOnLoginPage() {
        try {
            return driver.getCurrentUrl().contains("salesforce.com") && 
                   (usernameField.isDisplayed() || usernameFieldAlt.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }
    
    // Combined login method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}