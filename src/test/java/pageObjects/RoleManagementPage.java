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
    
    // Role Creation Form Elements
    private By roleNameField = By.xpath("//input[@name='roleName' or @id='roleName']");
    private By permissionsSection = By.xpath("//div[@class='permissions-section']//input[@type='checkbox']");
    private By saveRoleButton = By.xpath("//button[text()='Save Role' or @id='saveRole']");
    private By validationErrorMessage = By.xpath("//div[@class='error-message' or @class='validation-error']");
    private By roleNameError = By.xpath("//div[@class='field-error'][contains(text(),'Role name')]|//span[@class='error'][contains(text(),'Role name')]");
    
    // Role Management Interface
    private By createRoleButton = By.xpath("//button[text()='Create Role' or @id='createRole']");
    private By rolesList = By.xpath("//div[@class='roles-list']//tr");
    
    public void accessRoleCreationForm() {
        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(createRoleButton));
        createBtn.click();
    }
    
    public void enterRoleName(String roleName) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(roleNameField));
        nameField.clear();
        if (roleName != null && !roleName.isEmpty()) {
            nameField.sendKeys(roleName);
        }
    }
    
    public void selectPermissions() {
        List<WebElement> permissions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(permissionsSection));
        if (!permissions.isEmpty()) {
            permissions.get(0).click(); // Select first available permission
        }
    }
    
    public void clickSaveRole() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveRoleButton));
        saveBtn.click();
    }
    
    public boolean isValidationErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(validationErrorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getValidationErrorText() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(validationErrorMessage));
        return errorElement.getText();
    }
    
    public boolean isRoleNameFieldHighlighted() {
        try {
            WebElement nameField = driver.findElement(roleNameField);
            String classAttribute = nameField.getAttribute("class");
            return classAttribute != null && (classAttribute.contains("error") || classAttribute.contains("invalid"));
        } catch (Exception e) {
            return false;
        }
    }
}