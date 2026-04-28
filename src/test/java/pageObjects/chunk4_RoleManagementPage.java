package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class RoleManagementPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By roleManagementLink = By.xpath("//a[text()='Role Management']");
    private By createRoleButton = By.xpath("//button[@id='create-role']");
    private By roleNameField = By.xpath("//input[@id='role-name']");
    private By permissionsCheckboxes = By.xpath("//input[@type='checkbox' and @name='permissions']");
    private By saveRoleButton = By.xpath("//button[@id='save-role']");
    private By validationErrorMessage = By.xpath("//div[@class='error-message']");
    private By roleListTable = By.xpath("//table[@id='roles-table']");
    private By deleteRoleButton = By.xpath("//button[contains(@class, 'delete-role')]");
    private By confirmDeleteButton = By.xpath("//button[@id='confirm-delete']");
    private By warningMessage = By.xpath("//div[@class='warning-message']");
    
    public RoleManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void accessRoleManagement() {
        wait.until(ExpectedConditions.elementToBeClickable(roleManagementLink)).click();
    }
    
    public void clickCreateRole() {
        wait.until(ExpectedConditions.elementToBeClickable(createRoleButton)).click();
    }
    
    public void enterRoleName(String roleName) {
        wait.until(ExpectedConditions.elementToBeClickable(roleNameField)).clear();
        driver.findElement(roleNameField).sendKeys(roleName);
    }
    
    public void selectPermissions() {
        List<WebElement> permissions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(permissionsCheckboxes));
        if (!permissions.isEmpty()) {
            permissions.get(0).click(); // Select at least one permission
        }
    }
    
    public void saveRole() {
        wait.until(ExpectedConditions.elementToBeClickable(saveRoleButton)).click();
    }
    
    public boolean isValidationErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(validationErrorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getValidationErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(validationErrorMessage)).getText();
    }
    
    public void selectRoleByName(String roleName) {
        By roleRowLocator = By.xpath("//tr[td[text()='" + roleName + "']]");
        wait.until(ExpectedConditions.elementToBeClickable(roleRowLocator)).click();
    }
    
    public void clickDeleteRole() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteRoleButton)).click();
    }
    
    public boolean isWarningMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getWarningMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessage)).getText();
    }
    
    public boolean isRoleStillInSystem(String roleName) {
        By roleLocator = By.xpath("//td[text()='" + roleName + "']");
        try {
            return driver.findElement(roleLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}