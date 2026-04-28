package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class PermissionManagementPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By roleManagementMenu = By.xpath("//a[contains(text(),'Role Management')]");
    private By permissionManagementMenu = By.xpath("//a[contains(text(),'Permission Management')]");
    private By createRoleButton = By.xpath("//button[contains(text(),'Create Role')]");
    private By roleNameInput = By.xpath("//input[@name='roleName']");
    private By editRoleButton = By.xpath("//button[contains(text(),'Edit')]");
    private By granularPermissionTab = By.xpath("//tab[contains(text(),'Granular Permissions')]");
    private By permissionCheckbox = By.xpath("//input[@type='checkbox' and @data-permission='%s']");
    private By savePermissionButton = By.xpath("//button[contains(text(),'Save Permissions')]");
    private By permissionDetailsIcon = By.xpath("//i[@class='details-icon']");
    private By permissionScopeText = By.xpath("//div[@class='permission-scope']");
    private By permissionDescriptionText = By.xpath("//div[@class='permission-description']");
    private By permissionDetailsPanel = By.xpath("//div[@class='permission-details-panel']");
    private By roleDropdown = By.xpath("//select[@name='roleSelect']");
    private By addPermissionButton = By.xpath("//button[contains(text(),'Add Permission')]");
    private By saveChangesButton = By.xpath("//button[contains(text(),'Save Changes')]");
    private By successMessage = By.xpath("//div[contains(@class,'success-message')]");
    private By permissionList = By.xpath("//div[@class='permission-list']//span");
    
    public PermissionManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateToRoleManagement() {
        WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(roleManagementMenu));
        menuItem.click();
    }
    
    public void navigateToPermissionManagement() {
        WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(permissionManagementMenu));
        menuItem.click();
    }
    
    public void createOrEditRole(String roleName) {
        // Check if role exists, if not create it
        List<WebElement> existingRoles = driver.findElements(By.xpath("//td[text()='" + roleName + "']"));
        if (existingRoles.isEmpty()) {
            WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(createRoleButton));
            createButton.click();
            
            WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(roleNameInput));
            nameInput.clear();
            nameInput.sendKeys(roleName);
        } else {
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[text()='" + roleName + "']//following-sibling::td//button[contains(text(),'Edit')]"))
            );
            editButton.click();
        }
    }
    
    public void accessGranularPermissionConfiguration() {
        WebElement granularTab = wait.until(ExpectedConditions.elementToBeClickable(granularPermissionTab));
        granularTab.click();
    }
    
    public void selectPermission(String permission) {
        By checkbox = By.xpath(String.format("//input[@type='checkbox' and @data-permission='%s']", permission));
        WebElement permissionCheckbox = wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if (!permissionCheckbox.isSelected()) {
            permissionCheckbox.click();
        }
    }
    
    public void deselectPermission(String permission) {
        By checkbox = By.xpath(String.format("//input[@type='checkbox' and @data-permission='%s']", permission));
        WebElement permissionCheckbox = wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if (permissionCheckbox.isSelected()) {
            permissionCheckbox.click();
        }
    }
    
    public void savePermissionConfiguration() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(savePermissionButton));
        saveButton.click();
    }
    
    public boolean isPermissionSelected(String permission) {
        By checkbox = By.xpath(String.format("//input[@type='checkbox' and @data-permission='%s']", permission));
        WebElement permissionCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkbox));
        return permissionCheckbox.isSelected();
    }
    
    public void selectPermissionForDetails(String permission) {
        By permissionRow = By.xpath("//tr[td[contains(text(),'" + permission + "')]]");
        WebElement row = wait.until(ExpectedConditions.elementToBeClickable(permissionRow));
        row.click();
    }
    
    public void clickDetailsIcon() {
        WebElement detailsIcon = wait.until(ExpectedConditions.elementToBeClickable(permissionDetailsIcon));
        detailsIcon.click();
    }
    
    public String getPermissionScope() {
        WebElement scopeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(permissionScopeText));
        return scopeElement.getText();
    }
    
    public String getPermissionDescription() {
        WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(permissionDescriptionText));
        return descriptionElement.getText();
    }
    
    public boolean isPermissionDetailsDisplayed() {
        try {
            WebElement detailsPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(permissionDetailsPanel));
            return detailsPanel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void selectRole(String roleName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        dropdown.click();
        
        By roleOption = By.xpath("//option[text()='" + roleName + "']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(roleOption));
        option.click();
    }
    
    public void editRolePermissions() {
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(editRoleButton));
        editButton.click();
    }
    
    public void addPermission(String permission) {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addPermissionButton));
        addButton.click();
        
        By permissionOption = By.xpath("//option[@value='" + permission + "']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(permissionOption));
        option.click();
    }
    
    public void savePermissionChanges() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton));
        saveButton.click();
    }
    
    public boolean isPermissionAddedToRole(String roleName, String permission) {
        By permissionInRole = By.xpath("//div[@data-role='" + roleName + "']//span[contains(text(),'" + permission + "')]");
        try {
            WebElement permissionElement = wait.until(ExpectedConditions.presenceOfElementLocated(permissionInRole));
            return permissionElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isPermissionUpdateSuccessful() {
        try {
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return successMsg.isDisplayed() && successMsg.getText().toLowerCase().contains("success");
        } catch (Exception e) {
            return false;
        }
    }
}