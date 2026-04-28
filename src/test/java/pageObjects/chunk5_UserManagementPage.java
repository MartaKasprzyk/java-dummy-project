package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class UserManagementPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By userSearchField = By.xpath("//input[@id='userSearch']");
    private By searchButton = By.xpath("//button[@id='searchUser']");
    private By assignRoleButton = By.xpath("//button[@id='assignRole']");
    private By roleDropdown = By.xpath("//select[@name='roleSelection']");
    private By warningMessage = By.xpath("//div[@class='warning-message']");
    private By inactiveUserWarning = By.xpath("//span[contains(text(),'User account inactive')]");
    private By userCheckboxes = By.xpath("//input[@type='checkbox'][@name='userSelect']");
    private By bulkAssignButton = By.xpath("//button[@id='bulkAssign']");
    private By operationResultsSummary = By.xpath("//div[@id='operationResults']");
    private By activeUserResults = By.xpath("//div[contains(@class,'success-result')]");
    private By inactiveUserResults = By.xpath("//div[contains(@class,'warning-result')]");
    private By userManagementInterface = By.xpath("//div[@id='userManagement']");
    
    public UserManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void accessUserManagementInterface() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userManagementInterface));
    }
    
    public void searchForUser(String email) {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(userSearchField));
        searchField.clear();
        searchField.sendKeys(email);
        
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        search.click();
    }
    
    public void selectRole(String roleName) {
        WebElement role = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        role.click();
        By roleOption = By.xpath("//option[text()='" + roleName + "']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(roleOption));
        option.click();
    }
    
    public void clickAssignRole() {
        WebElement assignButton = wait.until(ExpectedConditions.elementToBeClickable(assignRoleButton));
        assignButton.click();
    }
    
    public boolean isInactiveUserWarningDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(inactiveUserWarning)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void selectMixedUsers() {
        List<WebElement> checkboxes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userCheckboxes));
        // Select first 4 users (2 active, 1 inactive, 1 suspended)
        for (int i = 0; i < Math.min(4, checkboxes.size()); i++) {
            checkboxes.get(i).click();
        }
    }
    
    public void executeBulkAssignment() {
        WebElement bulkButton = wait.until(ExpectedConditions.elementToBeClickable(bulkAssignButton));
        bulkButton.click();
    }
    
    public boolean isOperationResultsSummaryDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(operationResultsSummary)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areActiveUserResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(activeUserResults)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean areWarningResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(inactiveUserResults)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getWarningMessageText() {
        WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessage));
        return warning.getText();
    }
}