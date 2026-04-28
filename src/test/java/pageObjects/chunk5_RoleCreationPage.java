package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RoleCreationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By roleNameField = By.xpath("//input[@name='roleName']");
    private By permissionsDropdown = By.xpath("//select[@name='permissions']");
    private By saveRoleButton = By.xpath("//button[@id='saveRole']");
    private By validationError = By.xpath("//div[@class='validation-error']");
    private By roleNameRequiredError = By.xpath("//span[contains(text(),'Role name is required')]");
    private By invalidCharactersError = By.xpath("//span[contains(text(),'Role name contains invalid characters')]");
    private By roleCreationForm = By.xpath("//form[@id='roleCreationForm']");
    private By fieldHighlight = By.xpath("//input[@name='roleName'][contains(@class,'error')]");
    
    public RoleCreationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void accessRoleCreationForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(roleCreationForm));
    }
    
    public void enterRoleName(String roleName) {
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(roleNameField));
        nameField.clear();
        nameField.sendKeys(roleName);
    }
    
    public void leaveRoleNameEmpty() {
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(roleNameField));
        nameField.clear();
    }
    
    public void selectPermissions() {
        WebElement permissions = wait.until(ExpectedConditions.elementToBeClickable(permissionsDropdown));
        permissions.click();
    }
    
    public void clickSaveRole() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveRoleButton));
        saveButton.click();
    }
    
    public boolean isRoleNameRequiredErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(roleNameRequiredError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isInvalidCharactersErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCharactersError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isFieldHighlighted() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(fieldHighlight)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getValidationErrorText() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(validationError));
        return error.getText();
    }
}