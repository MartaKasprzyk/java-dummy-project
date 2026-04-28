package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleManagementPage;
import testBase.BaseClass;

public class TC_016_AttemptDeleteRoleAssignedToActiveUsers extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_016_AttemptDeleteRoleAssignedToActiveUsers() {
        String testName = "TC_016_AttemptDeleteRoleAssignedToActiveUsers";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Navigate to role management interface
            logger.info("Navigating to role management interface");
            roleManagementPage.navigateToRoleManagementInterface();
            
            // Select "Active_Role" with active assignments
            logger.info("Selecting 'Active_Role' with active assignments");
            roleManagementPage.selectRole("Active_Role");
            
            // Verify role has active assignments before deletion attempt
            int activeAssignments = roleManagementPage.getActiveAssignmentsCount("Active_Role");
            Assert.assertTrue(activeAssignments >= 3, "Role should have at least 3 active assignments");
            
            // Attempt to delete role
            logger.info("Attempting to delete role with active assignments");
            roleManagementPage.attemptToDeleteRole();
            
            // Observe system response and warnings
            logger.info("Observing system response and warnings");
            
            // Verify system prevents deletion
            boolean deletionPrevented = roleManagementPage.isDeletionPrevented();
            Assert.assertTrue(deletionPrevented, "System should prevent deletion of role with active assignments");
            
            // Verify warning message
            boolean warningMessageDisplayed = roleManagementPage.isWarningMessageDisplayed();
            Assert.assertTrue(warningMessageDisplayed, "Warning message should be displayed");
            
            String warningMessage = roleManagementPage.getWarningMessage();
            Assert.assertTrue(warningMessage.contains("Role assigned to") && warningMessage.contains("active users"), 
                "Warning message should indicate role is assigned to active users");
            
            // Verify role remains in system
            boolean roleStillExists = roleManagementPage.doesRoleExist("Active_Role");
            Assert.assertTrue(roleStillExists, "Role should remain in system after failed deletion attempt");
            
            // Verify user assignments remain unchanged
            int assignmentsAfterAttempt = roleManagementPage.getActiveAssignmentsCount("Active_Role");
            Assert.assertEquals(assignmentsAfterAttempt, activeAssignments, "User assignments should remain unchanged");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}