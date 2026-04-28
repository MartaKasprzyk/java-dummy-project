package pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class UserManagementPage {
    WebDriver driver;
    WebDriverWait wait;

    public UserManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By userManagementSection = By.xpath("//nav//a[contains(text(),'User Management')]");
    By userSearchField = By.xpath("//input[@placeholder='Search users...' or @id='userSearch']");
    By searchButton = By.xpath("//button[contains(text(),'Search') or @type='submit']");
    By roleDropdown = By.xpath("//select[@name='role' or @id='roleSelect']");
    By assignRoleButton = By.xpath("//button[contains(text(),'Assign Role')]");
    By removeRoleButton = By.xpath("//button[contains(text(),'Remove Role')]");
    By bulkUserCheckboxes = By.xpath("//table//input[@type='checkbox' and contains(@name,'user')]");
    By bulkAssignButton = By.xpath("//button[contains(text(),'Bulk Assign') or contains(text(),'Execute Bulk')]");
    By successMessage = By.xpath("//div[contains(@class,'success') or contains(@class,'alert-success')]");
    By userProfileSection = By.xpath("//div[@class='user-profile' or @id='userProfile']");
    By assignedRolesSection = By.xpath("//div[@class='assigned-roles' or contains(@class,'roles-section')]");
    By confirmRemovalButton = By.xpath("//button[contains(text(),'Confirm') or @id='confirmRemoval']");

    public void navigateToUserManagement() {
        WebElement userMgmtLink = wait.until(ExpectedConditions.elementToBeClickable(userManagementSection));
        userMgmtLink.click();
    }

    public void searchUser(String email) {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(userSearchField));
        searchField.clear();
        searchField.sendKeys(email);
        
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchBtn.click();
    }

    public void clickUserProfile(String email) {
        By userProfileLink = By.xpath("//td[contains(text(),'" + email + "')]/..//a[contains(text(),'Profile') or contains(@class,'profile')]");
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(userProfileLink));
        profileLink.click();
    }

    public void selectRole(String roleName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        Select roleSelect = new Select(dropdown);
        roleSelect.selectByVisibleText(roleName);
    }

    public void selectMultipleRoles(String[] roles) {
        for (String role : roles) {
            By roleCheckbox = By.xpath("//input[@type='checkbox' and @value='" + role + "']");
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(roleCheckbox));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void clickAssignRole() {
        WebElement assignBtn = wait.until(ExpectedConditions.elementToBeClickable(assignRoleButton));
        assignBtn.click();
    }

    public void accessRoleAssignmentSection() {
        WebElement roleSection = wait.until(ExpectedConditions.elementToBeClickable(assignedRolesSection));
        roleSection.click();
    }

    public void selectRoleForRemoval(String roleName) {
        By roleForRemoval = By.xpath("//div[contains(@class,'role-item') and contains(text(),'" + roleName + "')]//input[@type='checkbox']");
        WebElement roleCheckbox = wait.until(ExpectedConditions.elementToBeClickable(roleForRemoval));
        roleCheckbox.click();
    }

    public void clickRemoveRole() {
        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeRoleButton));
        removeBtn.click();
    }

    public void confirmRemovalAction() {
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmRemovalButton));
        confirmBtn.click();
    }

    public void selectMultipleUsers(String[] emails) {
        for (String email : emails) {
            By userCheckbox = By.xpath("//tr[td[contains(text(),'" + email + "')]]//input[@type='checkbox']");
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(userCheckbox));
            checkbox.click();
        }
    }

    public void executeBulkAssignment() {
        WebElement bulkBtn = wait.until(ExpectedConditions.elementToBeClickable(bulkAssignButton));
        bulkBtn.click();
    }

    public boolean isRoleAssigned(String email, String roleName) {
        By roleInProfile = By.xpath("//div[contains(@class,'user-profile')]//span[contains(text(),'" + roleName + "')]");
        try {
            WebElement roleElement = wait.until(ExpectedConditions.presenceOfElementLocated(roleInProfile));
            return roleElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRoleRemoved(String roleName) {
        By removedRole = By.xpath("//div[contains(@class,'assigned-roles')]//span[contains(text(),'" + roleName + "')]");
        try {
            List<WebElement> elements = driver.findElements(removedRole);
            return elements.isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

    public boolean verifyAllUsersHaveRole(String[] emails, String roleName) {
        for (String email : emails) {
            if (!isRoleAssigned(email, roleName)) {
                return false;
            }
        }
        return true;
    }
}