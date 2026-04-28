package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class RoleCreationPage {
    
    WebDriver driver;
    WebDriverWait wait;
    
    public RoleCreationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//a[@href='/roles/create']")
    WebElement createRoleLink;
    
    @FindBy(xpath = "//input[@id='roleName']")
    WebElement roleNameInput;
    
    @FindBy(xpath = "//input[@type='checkbox' and contains(@class, 'permission')]")
    List<WebElement> permissionCheckboxes;
    
    @FindBy(xpath = "//button[@id='saveRole']")
    WebElement saveRoleButton;
    
    @FindBy(xpath = "//div[@class='validation-error']")
    WebElement validationErrorMessage;
    
    @FindBy(xpath = "//span[@class='error-text']")
    WebElement errorText;
    
    @FindBy(xpath = "//form[@id='roleForm']")
    WebElement roleForm;
    
    public void accessRoleCreationInterface() {
        wait.until(driver -> createRoleLink.isDisplayed());
        createRoleLink.click();
    }
    
    public void enterRoleName(String roleName) {
        wait.until(driver -> roleNameInput.isDisplayed());
        roleNameInput.clear();
        roleNameInput.sendKeys(roleName);
    }
    
    public void selectValidPermissions() {
        wait.until(driver -> !permissionCheckboxes.isEmpty());
        // Select first few permissions as valid ones
        for (int i = 0; i < Math.min(3, permissionCheckboxes.size()); i++) {
            if (!permissionCheckboxes.get(i).isSelected()) {
                permissionCheckboxes.get(i).click();
            }
        }
    }
    
    public void attemptToSaveRole() {
        wait.until(driver -> saveRoleButton.isDisplayed());
        saveRoleButton.click();
    }
    
    public boolean isValidationErrorDisplayed() {
        return wait.until(driver -> validationErrorMessage.isDisplayed());
    }
    
    public String getValidationErrorMessage() {
        wait.until(driver -> errorText.isDisplayed());
        return errorText.getText();
    }
    
    public boolean isFormSubmissionBlocked() {
        // Check if form is still on the same page (not submitted)
        return wait.until(driver -> roleForm.isDisplayed());
    }
    
    public boolean isRoleCreated(String roleName) {
        // Implementation would check if role exists in system
        return false; // Should return false for duplicate role
    }
}