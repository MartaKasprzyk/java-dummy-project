package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserProfilePage;
import testBase.BaseClass;

public class TC_013_ViewUserPermissionHistoryTimeline extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_013_view_user_permission_history_timeline() {
        String testName = "TC_013_view_user_permission_history_timeline";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            UserProfilePage userProfilePage = new UserProfilePage(driver);
            
            // Access user profile for "history.user@test.com"
            logger.info("Searching for user: history.user@test.com");
            userProfilePage.searchUser("history.user@test.com");
            userProfilePage.accessUserProfile();
            
            // Navigate to permission history section
            logger.info("Navigating to permission history section");
            userProfilePage.navigateToPermissionHistory();
            
            // Review chronological role assignment timeline
            logger.info("Reviewing chronological role assignment timeline");
            Assert.assertTrue(userProfilePage.isHistoryTimelineVisible(), "History timeline should be visible");
            
            // Verify assignment and removal timestamps
            logger.info("Verifying timestamps and role changes");
            Assert.assertTrue(userProfilePage.areTimestampsVisible(), "Timestamps should be visible");
            Assert.assertTrue(userProfilePage.areRoleChangesVisible(), "Role changes should be visible");
            Assert.assertTrue(userProfilePage.areChangedByDetailsVisible(), "Changed by details should be visible");
            
            // Verify chronological order is maintained
            Assert.assertTrue(userProfilePage.isChronologicalOrderMaintained(), "Chronological order should be maintained");
            
            // Verify minimum history entries (3+)
            Assert.assertTrue(userProfilePage.getHistoryEntries().size() >= 3, "Should have at least 3 history entries");
            
            logger.info("Successfully verified user permission history timeline");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}