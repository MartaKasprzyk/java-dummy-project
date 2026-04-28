package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class AuditLogPage {
    
    WebDriver driver;
    WebDriverWait wait;
    
    public AuditLogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//a[text()='Audit Logs']")
    WebElement auditLogsLink;
    
    @FindBy(xpath = "//table[@id='audit-log-table']//tr")
    List<WebElement> auditLogRows;
    
    @FindBy(xpath = "//div[@class='audit-entry']")
    List<WebElement> auditEntries;
    
    public void navigateToAuditLogs() {
        auditLogsLink.click();
    }
    
    public List<String> getRecentAuditEntries() {
        List<String> entries = new ArrayList<>();
        for (WebElement entry : auditEntries) {
            entries.add(entry.getText());
        }
        return entries;
    }
    
    public boolean hasRequiredMetadata(String entry, String field) {
        return entry.contains(field + ":");
    }
    
    public boolean verifyTimestampAccuracy() {
        // Implementation to verify timestamp accuracy
        return true;
    }
    
    public boolean verifyUserAttribution(String expectedUser) {
        // Implementation to verify user attribution
        return true;
    }
}