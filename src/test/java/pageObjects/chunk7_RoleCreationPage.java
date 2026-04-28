package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class RoleCreationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By roleNameField = By.xpath("//input[@id='role-name']");
    private By permissionCheckboxes = By.xpath("//input[@type='checkbox'][@name='permissions']");
    private By saveRoleButton = By.xpath("//button[@id='save-role']");
    private By validationError = By.xpath("//div[@class='validation-error']");
    private By errorMessage = By.xpath("//div[@class='error-message']");
    
    public RoleCreationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void enterRoleName(String roleName) {
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(roleNameField));
        nameField.clear();
        nameField.sendKeys(roleName);
    }
    
    public void leaveAllPermissionsUnselected() {
        List<WebElement> checkboxes = driver.findElements(permissionCheckboxes);
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }
    
    public void attemptToSaveRole() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveRoleButton));
        saveBtn.click();
    }
    
    public boolean isValidationErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(validationError));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getValidationErrorText() {
        WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(validationError));
        return errorElement.getText();
    }
    
    public boolean isFormSubmissionBlocked() {
        try {
            // Check if we're still on the same page
            wait.until(ExpectedConditions.presenceOfElementLocated(saveRoleButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}