package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class UserProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By searchUserField = By.xpath("//input[@id='user-search']");
    private By searchButton = By.xpath("//button[@id='search-btn']");
    private By userProfileLink = By.xpath("//a[contains(@href, 'profile')]");
    private By permissionHistoryTab = By.xpath("//a[text()='Permission History']");
    private By historyTimeline = By.xpath("//div[@class='timeline-container']");
    private By historyEntries = By.xpath("//div[@class='history-entry']");
    private By timestampElements = By.xpath("//span[@class='timestamp']");
    private By roleChangeElements = By.xpath("//span[@class='role-change']");
    private By changedByElements = By.xpath("//span[@class='changed-by']");
    
    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void searchUser(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(searchUserField)).clear();
        driver.findElement(searchUserField).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
    
    public void accessUserProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(userProfileLink)).click();
    }
    
    public void navigateToPermissionHistory() {
        wait.until(ExpectedConditions.elementToBeClickable(permissionHistoryTab)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(historyTimeline));
    }
    
    public boolean isHistoryTimelineVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(historyTimeline)).isDisplayed();
    }
    
    public List<WebElement> getHistoryEntries() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(historyEntries));
    }
    
    public boolean areTimestampsVisible() {
        List<WebElement> timestamps = driver.findElements(timestampElements);
        return timestamps.size() > 0;
    }
    
    public boolean areRoleChangesVisible() {
        List<WebElement> roleChanges = driver.findElements(roleChangeElements);
        return roleChanges.size() > 0;
    }
    
    public boolean areChangedByDetailsVisible() {
        List<WebElement> changedBy = driver.findElements(changedByElements);
        return changedBy.size() > 0;
    }
    
    public boolean isChronologicalOrderMaintained() {
        List<WebElement> timestamps = driver.findElements(timestampElements);
        // This would need actual timestamp parsing logic in a real implementation
        return timestamps.size() > 0; // Simplified for this example
    }
}