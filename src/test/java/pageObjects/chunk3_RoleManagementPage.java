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
    private By createRoleButton = By.xpath("//button[@id='create-role-btn']");
    private By editRoleButton = By.xpath("//button[@class='edit-role-btn']");
    private By roleNameField = By.xpath("//input[@id='role-name-input']");
    private By permissionMatrix = By.xpath("//div[@class='permission-matrix']");
    private By readPermissionCheckbox = By.xpath("//input[@id='permission-read']");
    private By updatePermissionCheckbox = By.xpath("//input[@id='permission-update']");
    private By createPermissionCheckbox = By.xpath("//input[@id='permission-create']");
    private By deletePermissionCheckbox = By.xpath("//input[@id='permission-delete']");
    private By adminPermissionCheckbox = By.xpath("//input[@id='permission-admin']");
    private By savePermissionButton = By.xpath("//button[@id='save-permissions-btn']");
    private By permissionDetailsIcon = By.xpath("//i[@class='permission-details-icon']");
    private By permissionScopeText = By.xpath("//div[@class='permission-scope-description']");
    private By deleteReportsPermission = By.xpath("//input[@id='permission-delete-reports']");
    private By roleByName = By.xpath("//tr[td[text()='%s']]");

    // Methods
    public void clickCreateRole() {
        wait.until(ExpectedConditions.elementToBeClickable(createRoleButton)).click();
    }

    public void clickEditRole() {
        wait.until(ExpectedConditions.elementToBeClickable(editRoleButton)).click();
    }

    public void enterRoleName(String roleName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(roleNameField));
        element.clear();
        element.sendKeys(roleName);
    }

    public void selectPermissions(String... permissions) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(permissionMatrix));
        for (String permission : permissions) {
            switch (permission.toLowerCase()) {
                case "read":
                    wait.until(ExpectedConditions.elementToBeClickable(readPermissionCheckbox)).click();
                    break;
                case "update":
                    wait.until(ExpectedConditions.elementToBeClickable(updatePermissionCheckbox)).click();
                    break;
                case "create":
                    wait.until(ExpectedConditions.elementToBeClickable(createPermissionCheckbox)).click();
                    break;
                case "delete":
                    wait.until(ExpectedConditions.elementToBeClickable(deletePermissionCheckbox)).click();
                    break;
                case "admin":
                    wait.until(ExpectedConditions.elementToBeClickable(adminPermissionCheckbox)).click();
                    break;
            }
        }
    }

    public void savePermissionConfiguration() {
        wait.until(ExpectedConditions.elementToBeClickable(savePermissionButton)).click();
    }

    public void clickPermissionDetailsIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(permissionDetailsIcon)).click();
    }

    public String getPermissionScopeDescription() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(permissionScopeText)).getText();
    }

    public void addDeleteReportsPermission() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteReportsPermission)).click();
    }

    public void editRoleByName(String roleName) {
        By roleLocator = By.xpath(String.format("//tr[td[text()='%s']]//button[@class='edit-role-btn']", roleName));
        wait.until(ExpectedConditions.elementToBeClickable(roleLocator)).click();
    }

    public boolean isPermissionSelected(String permission) {
        By permissionLocator;
        switch (permission.toLowerCase()) {
            case "read":
                permissionLocator = readPermissionCheckbox;
                break;
            case "update":
                permissionLocator = updatePermissionCheckbox;
                break;
            case "create":
                permissionLocator = createPermissionCheckbox;
                break;
            case "delete":
                permissionLocator = deletePermissionCheckbox;
                break;
            case "admin":
                permissionLocator = adminPermissionCheckbox;
                break;
            case "delete-reports":
                permissionLocator = deleteReportsPermission;
                break;
            default:
                return false;
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(permissionLocator)).isSelected();
    }
}