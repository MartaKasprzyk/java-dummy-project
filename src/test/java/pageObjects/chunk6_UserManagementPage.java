package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class UserManagementPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public UserManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Locators
    By usersMenuLink = By.xpath("//a[contains(text(),'Users')]");
    By userSearchInput = By.xpath("//input[@name='userSearch']");
    By searchButton = By.xpath("//button[contains(text(),'Search')]");
    By editUserButton = By.xpath("//button[contains(text(),'Edit User')]");
    By roleDropdown = By.xpath("//select[@name='userRole']");
    By saveUserButton = By.xpath("//button[contains(text(),'Save User')]");
    By userPermissionsList = By.xpath("//div[@class='user-permissions']//li");
    By accessDeniedMessage = By.xpath("//div[contains(text(),'Access Denied')]");
    By unauthorizedMessage = By.xpath("//div[contains(text(),'Unauthorized')]");
    
    // Methods
    public void navigateToUsers() {
        wait.until(ExpectedConditions.elementToBeClickable(usersMenuLink)).click();
    }
    
    public void searchUser(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userSearchInput)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
    
    public void editUser(String email) {
        searchUser(email);
        wait.until(ExpectedConditions.elementToBeClickable(editUserButton)).click();
    }
    
    public void changeUserRole(String roleName) {
        Select roleSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(roleDropdown)));
        roleSelect.selectByVisibleText(roleName);
    }
    
    public void saveUserChanges() {
        wait.until(ExpectedConditions.elementToBeClickable(saveUserButton)).click();
    }
    
    public boolean isAccessDenied() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(accessDeniedMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean hasPermission(String permission) {
        try {
            WebElement permissionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='user-permissions']//li[contains(text(),'" + permission + "')]"
            )));
            return permissionElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void testRestrictedFunction() {
        // Attempt to access a function that should be restricted
        try {
            WebElement restrictedButton = driver.findElement(By.xpath("//button[@id='restricted-function']"));
            restrictedButton.click();
        } catch (Exception e) {
            // Function may not be accessible
        }
    }
}