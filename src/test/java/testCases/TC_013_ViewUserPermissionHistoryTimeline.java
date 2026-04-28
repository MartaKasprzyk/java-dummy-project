package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserProfilePage;
import testBase.BaseClass;

public class TC_013_ViewUserPermissionHistoryTimeline extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_013_ViewUserPermissionHistoryTimeline() {
        String testName = "TC_013_ViewUserPermissionHistoryTimeline";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            UserProfilePage userProfilePage = new UserProfilePage(driver);
            
            // Access user profile for "history.user@test.com"
            logger.info("Accessing user profile for history.user@test.com");
            userProfilePage.accessUserProfile("history.user@test.com");
            
            // Navigate to permission history section
            logger.info("Navigating to permission history section");
            userProfilePage.navigateToPermissionHistory();
            
            // Review chronological role assignment timeline
            logger.info("Reviewing chronological role assignment timeline");
            boolean timelineVisible = userProfilePage.isTimelineVisible();
            Assert.assertTrue(timelineVisible, "Permission history timeline should be visible");
            
            // Verify assignment and removal timestamps
            logger.info("Verifying assignment and removal timestamps");
            boolean hasTimestamps = userProfilePage.verifyTimestamps();
            Assert.assertTrue(hasTimestamps, "Timeline should show assignment and removal timestamps");
            
            boolean hasRoleChanges = userProfilePage.verifyRoleChanges();
            Assert.assertTrue(hasRoleChanges, "Timeline should show all role changes with dates");
            
            boolean hasChangeAuthors = userProfilePage.verifyChangeAuthors();
            Assert.assertTrue(hasChangeAuthors, "Timeline should include who made changes");
            
            boolean isChronological = userProfilePage.verifyChronologicalOrder();
            Assert.assertTrue(isChronological, "Timeline should maintain chronological order");
            
            int historyEntries = userProfilePage.getHistoryEntriesCount();
            Assert.assertTrue(historyEntries >= 3, "Should have at least 3 history entries");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}