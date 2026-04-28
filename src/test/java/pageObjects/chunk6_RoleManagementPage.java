package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class RoleManagementPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public RoleManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Locators
    By rolesMenuLink = By.xpath("//a[contains(text(),'Roles')]");
    By createRoleButton = By.xpath("//button[contains(text(),'Create Role')]");
    By roleNameInput = By.xpath("//input[@name='roleName']");
    By permissionCheckboxes = By.xpath("//input[@type='checkbox'][@name='permissions']");
    By saveRoleButton = By.xpath("//button[contains(text(),'Save')]");
    By editRoleButton = By.xpath("//button[contains(text(),'Edit')]");
    By deleteRoleButton = By.xpath("//button[contains(text(),'Delete')]");
    By conflictWarningMessage = By.xpath("//div[contains(@class,'conflict-warning')]");
    By successMessage = By.xpath("//div[contains(@class,'success-message')]");
    By errorMessage = By.xpath("//div[contains(@class,'error-message')]");
    By rolesList = By.xpath("//table[@id='roles-table']//tr");
    By maxPermissionError = By.xpath("//div[contains(text(),'Maximum permission limit')]");
    
    // Methods
    public void navigateToRoles() {
        wait.until(ExpectedConditions.elementToBeClickable(rolesMenuLink)).click();
    }
    
    public void createRole(String roleName) {
        wait.until(ExpectedConditions.elementToBeClickable(createRoleButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(roleNameInput)).sendKeys(roleName);
    }
    
    public void selectPermissions(int count) {
        List<WebElement> checkboxes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(permissionCheckboxes));
        for (int i = 0; i < Math.min(count, checkboxes.size()); i++) {
            if (!checkboxes.get(i).isSelected()) {
                checkboxes.get(i).click();
            }
        }
    }
    
    public void saveRole() {
        wait.until(ExpectedConditions.elementToBeClickable(saveRoleButton)).click();
    }
    
    public void editRole(String roleName) {
        WebElement roleRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//tr[td[contains(text(),'" + roleName + "')]]")));
        roleRow.findElement(editRoleButton).click();
    }
    
    public boolean isConflictWarningDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(conflictWarningMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isMaxPermissionErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(maxPermissionError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getPermissionCount() {
        List<WebElement> checkboxes = driver.findElements(permissionCheckboxes);
        return checkboxes.size();
    }
}