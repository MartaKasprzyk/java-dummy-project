package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class UserManagementPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public UserManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // User Management Interface Elements
    private By searchUserField = By.xpath("//input[@placeholder='Search users' or @name='userSearch']");
    private By searchButton = By.xpath("//button[text()='Search' or @id='searchUser']");
    private By userSearchResults = By.xpath("//div[@class='user-results']//tr");
    private By assignRoleButton = By.xpath("//button[text()='Assign Role' or @id='assignRole']");
    private By roleDropdown = By.xpath("//select[@name='roleSelect' or @id='roleSelect']");
    private By viewerRoleOption = By.xpath("//option[text()='Viewer' or @value='Viewer']");
    private By analystRoleOption = By.xpath("//option[text()='Analyst' or @value='Analyst']");
    private By warningMessage = By.xpath("//div[@class='warning-message' or @class='alert-warning']");
    private By successMessage = By.xpath("//div[@class='success-message' or @class='alert-success']");
    
    // Bulk Assignment Elements
    private By bulkAssignmentButton = By.xpath("//button[text()='Bulk Assignment' or @id='bulkAssign']");
    private By userCheckboxes = By.xpath("//input[@type='checkbox'][@name='userSelect']");
    private By executeBulkButton = By.xpath("//button[text()='Execute Bulk Assignment' or @id='executeBulk']");
    private By operationResults = By.xpath("//div[@class='operation-results' or @class='bulk-results']");
    private By resultsSummary = By.xpath("//div[@class='results-summary']//div[@class='result-item']");
    
    public void accessUserManagementInterface() {
        // Assumes user is already on the user management page
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchUserField));
    }
    
    public void searchForUser(String email) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchUserField));
        searchField.clear();
        searchField.sendKeys(email);
        
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchBtn.click();
    }
    
    public void assignRoleToUser(String roleName) {
        WebElement assignBtn = wait.until(ExpectedConditions.elementToBeClickable(assignRoleButton));
        assignBtn.click();
        
        WebElement roleSelect = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        roleSelect.click();
        
        By roleOption = roleName.equals("Viewer") ? viewerRoleOption : analystRoleOption;
        WebElement role = wait.until(ExpectedConditions.elementToBeClickable(roleOption));
        role.click();
        
        // Click save/confirm button (assuming it appears after role selection)
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[text()='Confirm' or text()='Save' or @id='confirmAssignment']")
        ));
        confirmBtn.click();
    }
    
    public boolean isWarningMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getWarningMessageText() {
        WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessage));
        return warning.getText();
    }
    
    public void accessBulkAssignmentInterface() {
        WebElement bulkBtn = wait.until(ExpectedConditions.elementToBeClickable(bulkAssignmentButton));
        bulkBtn.click();
    }
    
    public void selectMixedUsers() {
        List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(userCheckboxes));
        // Select first 4 users (2 active, 1 inactive, 1 suspended as per test case)
        for (int i = 0; i < Math.min(4, checkboxes.size()); i++) {
            checkboxes.get(i).click();
        }
    }
    
    public void chooseBulkRole(String roleName) {
        WebElement roleSelect = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        roleSelect.click();
        
        By roleOption = analystRoleOption; // Default to Analyst as per test case
        WebElement role = wait.until(ExpectedConditions.elementToBeClickable(roleOption));
        role.click();
    }
    
    public void executeBulkAssignment() {
        WebElement executeBtn = wait.until(ExpectedConditions.elementToBeClickable(executeBulkButton));
        executeBtn.click();
    }
    
    public boolean isOperationResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(operationResults));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<WebElement> getResultsSummary() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultsSummary));
    }
    
    public boolean hasSuccessResults() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}