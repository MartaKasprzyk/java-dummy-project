package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserSessionPage {
    
    WebDriver driver;
    WebDriverWait wait;
    
    public UserSessionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(id = "username")
    WebElement usernameField;
    
    @FindBy(id = "password")
    WebElement passwordField;
    
    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;
    
    @FindBy(xpath = "//a[text()='Editor Functions']")
    WebElement editorFunctionsLink;
    
    @FindBy(xpath = "//button[text()='Restricted Function']")
    WebElement restrictedFunctionButton;
    
    @FindBy(xpath = "//div[@class='user-profile']")
    WebElement userProfileDiv;
    
    public void loginAsUser(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
    
    public boolean canAccessEditorFunctions() {
        try {
            return editorFunctionsLink.isDisplayed() && editorFunctionsLink.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void switchToUserTab() {
        // Implementation for switching between tabs
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(originalWindow);
                break;
            }
        }
    }
    
    public void refreshCurrentPage() {
        driver.navigate().refresh();
    }
    
    public boolean attemptRestrictedFunction() {
        try {
            restrictedFunctionButton.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isStillLoggedIn() {
        try {
            return userProfileDiv.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}