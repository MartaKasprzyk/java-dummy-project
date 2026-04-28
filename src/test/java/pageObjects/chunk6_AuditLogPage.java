package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AuditLogPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public AuditLogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Locators
    By auditLogMenuLink = By.xpath("//a[contains(text(),'Audit Log')]");
    By auditLogTable = By.xpath("//table[@id='audit-log-table']");
    By auditLogRows = By.xpath("//table[@id='audit-log-table']//tbody//tr");
    By userIdColumn = By.xpath("//td[contains(@class,'user-id')]");
    By timestampColumn = By.xpath("//td[contains(@class,'timestamp')]");
    By actionColumn = By.xpath("//td[contains(@class,'action')]");
    By resourceColumn = By.xpath("//td[contains(@class,'resource')]");
    By detailsColumn = By.xpath("//td[contains(@class,'details')]");
    By refreshButton = By.xpath("//button[contains(text(),'Refresh')]");
    By filterInput = By.xpath("//input[@name='filter']");
    
    // Methods
    public void navigateToAuditLog() {
        wait.until(ExpectedConditions.elementToBeClickable(auditLogMenuLink)).click();
    }
    
    public void refreshAuditLog() {
        wait.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
    }
    
    public List<WebElement> getAuditLogEntries() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(auditLogTable));
        return driver.findElements(auditLogRows);
    }
    
    public boolean verifyAuditLogEntry(String action, String resource) {
        List<WebElement> rows = getAuditLogEntries();
        for (WebElement row : rows) {
            String actionText = row.findElement(actionColumn).getText();
            String resourceText = row.findElement(resourceColumn).getText();
            if (actionText.contains(action) && resourceText.contains(resource)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean verifyCompleteMetadata() {
        List<WebElement> rows = getAuditLogEntries();
        for (WebElement row : rows) {
            String userId = row.findElement(userIdColumn).getText();
            String timestamp = row.findElement(timestampColumn).getText();
            String action = row.findElement(actionColumn).getText();
            String resource = row.findElement(resourceColumn).getText();
            String details = row.findElement(detailsColumn).getText();
            
            if (userId.isEmpty() || timestamp.isEmpty() || action.isEmpty() || 
                resource.isEmpty() || details.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public int getAuditLogCount() {
        return getAuditLogEntries().size();
    }
}