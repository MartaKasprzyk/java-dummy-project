package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
    private By auditLogInterface = By.xpath("//div[@id='audit-log-interface']");
    private By activityFilter = By.xpath("//select[@id='activity-filter']");
    private By logEntriesTable = By.xpath("//table[@id='audit-log-table']");
    private By logEntries = By.xpath("//table[@id='audit-log-table']//tr[@class='log-entry']");
    private By userIdColumn = By.xpath("//td[@class='user-id-column']");
    private By timestampColumn = By.xpath("//td[@class='timestamp-column']");
    private By actionColumn = By.xpath("//td[@class='action-column']");
    private By detailsColumn = By.xpath("//td[@class='details-column']");
    private By sortButton = By.xpath("//button[@class='sort-button']");
    private By filterButton = By.xpath("//button[@class='filter-button']");

    // Methods
    public void navigateToAuditLog() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(auditLogInterface));
    }

    public void filterByRoleManagementActivities() {
        WebElement filterDropdown = wait.until(ExpectedConditions.elementToBeClickable(activityFilter));
        Select select = new Select(filterDropdown);
        select.selectByValue("role_management");
    }

    public List<WebElement> getLogEntries() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logEntriesTable));
        return driver.findElements(logEntries);
    }

    public boolean areLogEntriesVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logEntriesTable));
            return getLogEntries().size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyLogEntryDetails() {
        List<WebElement> entries = getLogEntries();
        if (entries.isEmpty()) return false;

        WebElement firstEntry = entries.get(0);
        
        // Check if user_id column has content
        WebElement userIdCell = firstEntry.findElement(userIdColumn);
        if (userIdCell.getText().trim().isEmpty()) return false;

        // Check if timestamp column has content
        WebElement timestampCell = firstEntry.findElement(timestampColumn);
        if (timestampCell.getText().trim().isEmpty()) return false;

        // Check if action column has content
        WebElement actionCell = firstEntry.findElement(actionColumn);
        if (actionCell.getText().trim().isEmpty()) return false;

        // Check if details column has content
        WebElement detailsCell = firstEntry.findElement(detailsColumn);
        if (detailsCell.getText().trim().isEmpty()) return false;

        return true;
    }

    public boolean isLogsSortable() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(sortButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogsFilterable() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(filterButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstLogEntryUserId() {
        List<WebElement> entries = getLogEntries();
        if (entries.isEmpty()) return "";
        return entries.get(0).findElement(userIdColumn).getText();
    }

    public String getFirstLogEntryTimestamp() {
        List<WebElement> entries = getLogEntries();
        if (entries.isEmpty()) return "";
        return entries.get(0).findElement(timestampColumn).getText();
    }

    public String getFirstLogEntryAction() {
        List<WebElement> entries = getLogEntries();
        if (entries.isEmpty()) return "";
        return entries.get(0).findElement(actionColumn).getText();
    }

    public String getFirstLogEntryDetails() {
        List<WebElement> entries = getLogEntries();
        if (entries.isEmpty()) return "";
        return entries.get(0).findElement(detailsColumn).getText();
    }
}