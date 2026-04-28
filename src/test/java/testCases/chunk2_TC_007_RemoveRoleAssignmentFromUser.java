package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserManagementPage;
import testBase.BaseClass;

public class TC_007_RemoveRoleAssignmentFromUser extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_007_remove_role_assignment_from_user() {
        String testName = "TC_007_remove_role_assignment_from_user";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Test data
            String userEmail = "mike.wilson@test.com";
            String roleName = "Editor";
            
            // Initialize page object
            UserManagementPage userMgmtPage = new UserManagementPage(driver);
            
            // Access user profile for "mike.wilson@test.com"
            logger.info("Navigating to user management section");
            userMgmtPage.navigateToUserManagement();
            
            logger.info("Searching for user: " + userEmail);
            userMgmtPage.searchUser(userEmail);
            
            logger.info("Clicking user profile");
            userMgmtPage.clickUserProfile(userEmail);
            
            // Navigate to assigned roles section
            logger.info("Navigating to assigned roles section");
            userMgmtPage.accessRoleAssignmentSection();
            
            // Select "Editor" role for removal
            logger.info("Selecting Editor role for removal");
            userMgmtPage.selectRoleForRemoval(roleName);
            
            // Click "Remove Role" button
            logger.info("Clicking Remove Role button");
            userMgmtPage.clickRemoveRole();
            
            // Confirm removal action
            logger.info("Confirming removal action");
            userMgmtPage.confirmRemovalAction();
            
            // Verify role removal
            logger.info("Verifying role removal");
            Assert.assertTrue(userMgmtPage.isRoleRemoved(roleName), 
                "Editor role should be removed from user profile");
            Assert.assertTrue(userMgmtPage.isSuccessMessageDisplayed(), 
                "Success message should be displayed");
            
            logger.info("Test completed successfully");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}