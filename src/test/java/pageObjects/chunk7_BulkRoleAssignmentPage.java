package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class BulkRoleAssignmentPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By userCheckboxes = By.xpath("//input[@type='checkbox'][@name='selected-users']");
    private By bulkAssignButton = By.xpath("//button[@id='bulk-assign-roles']");
    private By assignmentStatus = By.xpath("//div[@id='assignment-status']");
    private By retryButton = By.xpath("//button[@id='retry-assignment']");
    private By progressIndicator = By.xpath("//div[@class='progress-indicator']");
    private By completionStatus = By.xpath("//div[@id='completion-status']");
    private By networkErrorMessage = By.xpath("//div[@class='network-error']");
    
    public BulkRoleAssignmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void selectMultipleUsers(int numberOfUsers) {
        List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(userCheckboxes));
        for (int i = 0; i < Math.min(numberOfUsers, checkboxes.size()); i++) {
            if (!checkboxes.get(i).isSelected()) {
                checkboxes.get(i).click();
            }
        }
    }
    
    public void initiateBulkRoleAssignment() {
        WebElement bulkBtn = wait.until(ExpectedConditions.elementToBeClickable(bulkAssignButton));
        bulkBtn.click();
    }
    
    public boolean isRetryMechanismAvailable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(retryButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isPartialCompletionStatusShown() {
        try {
            WebElement status = wait.until(ExpectedConditions.presenceOfElementLocated(completionStatus));
            return status.getText().contains("partial") || status.getText().contains("incomplete");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isNetworkErrorHandledGracefully() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(networkErrorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void retryAssignment() {
        WebElement retryBtn = wait.until(ExpectedConditions.elementToBeClickable(retryButton));
        retryBtn.click();
    }
}