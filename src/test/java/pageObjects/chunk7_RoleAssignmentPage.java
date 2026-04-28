package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RoleAssignmentPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By userSelectDropdown = By.xpath("//select[@id='user-select']");
    private By roleSelectDropdown = By.xpath("//select[@id='role-select']");
    private By assignRoleButton = By.xpath("//button[@id='assign-role']");
    private By errorMessage = By.xpath("//div[@class='error-message']");
    private By databaseErrorMessage = By.xpath("//div[@class='database-error']");
    private By retryMechanism = By.xpath("//button[@id='retry-operation']");
    private By systemStatus = By.xpath("//div[@id='system-status']");
    
    public RoleAssignmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void selectUser(String username) {
        WebElement userDropdown = wait.until(ExpectedConditions.elementToBeClickable(userSelectDropdown));
        userDropdown.click();
        WebElement userOption = driver.findElement(By.xpath("//option[text()='" + username + "']"));
        userOption.click();
    }
    
    public void selectRole(String roleName) {
        WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(roleSelectDropdown));
        roleDropdown.click();
        WebElement roleOption = driver.findElement(By.xpath("//option[text()='" + roleName + "']"));
        roleOption.click();
    }
    
    public void initiateRoleAssignment() {
        WebElement assignBtn = wait.until(ExpectedConditions.elementToBeClickable(assignRoleButton));
        assignBtn.click();
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isDatabaseErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(databaseErrorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
        return errorElement.getText();
    }
    
    public boolean isRetryMechanismAvailable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(retryMechanism));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isGracefulDegradationShown() {
        try {
            WebElement status = wait.until(ExpectedConditions.presenceOfElementLocated(systemStatus));
            return status.getText().contains("degraded") || status.getText().contains("limited");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isPartialAssignmentPrevented() {
        // Check that no partial assignments were created during failure
        try {
            WebElement status = wait.until(ExpectedConditions.presenceOfElementLocated(systemStatus));
            return !status.getText().contains("partial assignment");
        } catch (Exception e) {
            return true;
        }
    }
}