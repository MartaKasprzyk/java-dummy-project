package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class PermissionManagementPage {
    WebDriver driver;
    WebDriverWait wait;

    public PermissionManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By permissionManagementInterface = By.xpath("//div[@id='permission-management-interface']");
    private By createTestCasesPermission = By.xpath("//div[@data-permission='create_test_cases']");
    private By permissionDetailsIcon = By.xpath("//i[@class='permission-details-icon']");
    private By permissionDetailView = By.xpath("//div[@class='permission-detail-view']");
    private By permissionDescription = By.xpath("//div[@class='permission-description']");
    private By permissionScope = By.xpath("//div[@class='permission-scope']");
    private By accessibleFunctions = By.xpath("//div[@class='accessible-functions']");

    // Methods
    public void navigateToPermissionManagement() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(permissionManagementInterface));
    }

    public void selectCreateTestCasesPermission() {
        wait.until(ExpectedConditions.elementToBeClickable(createTestCasesPermission)).click();
    }

    public void clickPermissionDetailsIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(permissionDetailsIcon)).click();
    }

    public boolean isDetailViewDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(permissionDetailView)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPermissionDescription() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(permissionDescription)).getText();
    }

    public String getPermissionScope() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(permissionScope)).getText();
    }

    public String getAccessibleFunctions() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accessibleFunctions)).getText();
    }

    public boolean isPermissionScopeTestCaseModule() {
        String scope = getPermissionScope();
        return scope.contains("Test Case Management Module");
    }
}