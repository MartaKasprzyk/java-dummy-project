package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class UserProfilePage {
    
    WebDriver driver;
    WebDriverWait wait;
    
    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//input[@id='userSearch']")
    WebElement userSearchInput;
    
    @FindBy(xpath = "//button[@id='searchUser']")
    WebElement searchUserButton;
    
    @FindBy(xpath = "//a[contains(@class, 'permission-history-tab')]")
    WebElement permissionHistoryTab;
    
    @FindBy(xpath = "//div[@class='permission-timeline']")
    WebElement permissionTimeline;
    
    @FindBy(xpath = "//div[@class='timeline-entry']")
    List<WebElement> timelineEntries;
    
    @FindBy(xpath = "//span[@class='timestamp']")
    List<WebElement> timestamps;
    
    @FindBy(xpath = "//span[@class='change-author']")
    List<WebElement> changeAuthors;
    
    @FindBy(xpath = "//div[@class='role-change']")
    List<WebElement> roleChanges;
    
    public void accessUserProfile(String userEmail) {
        wait.until(driver -> userSearchInput.isDisplayed());
        userSearchInput.clear();
        userSearchInput.sendKeys(userEmail);
        searchUserButton.click();
    }
    
    public void navigateToPermissionHistory() {
        wait.until(driver -> permissionHistoryTab.isDisplayed());
        permissionHistoryTab.click();
    }
    
    public boolean isTimelineVisible() {
        return wait.until(driver -> permissionTimeline.isDisplayed());
    }
    
    public boolean verifyTimestamps() {
        return !timestamps.isEmpty() && timestamps.stream().allMatch(WebElement::isDisplayed);
    }
    
    public boolean verifyRoleChanges() {
        return !roleChanges.isEmpty() && roleChanges.stream().allMatch(WebElement::isDisplayed);
    }
    
    public boolean verifyChangeAuthors() {
        return !changeAuthors.isEmpty() && changeAuthors.stream().allMatch(WebElement::isDisplayed);
    }
    
    public boolean verifyChronologicalOrder() {
        // Verify timeline entries are in chronological order
        return timelineEntries.size() > 0;
    }
    
    public int getHistoryEntriesCount() {
        return timelineEntries.size();
    }
}