package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserManagementPage;
import testBase.BaseClass;

public class TC_007_RemoveRoleFromUser extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_007_remove_role_assignment_from_user() {
        String testName = "TC_007_remove_role_assignment_from_user";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            UserManagementPage userMgmtPage = new UserManagementPage(driver);
            
            // Access user profile for "mike.wilson@test.com"
            logger.info("Accessing user profile for mike.wilson@test.com");
            userMgmtPage.searchForUser("mike.wilson@test.com");
            userMgmtPage.clickUserProfile("mike.wilson@test.com");
            
            // Navigate to assigned roles section
            logger.info("Navigating to assigned roles section");
            userMgmtPage.navigateToAssignedRolesSection();
            
            // Select "Editor" role for removal
            logger.info("Selecting Editor role for removal");
            userMgmtPage.selectRole("Editor");
            
            // Click "Remove Role" button
            logger.info("Clicking Remove Role button");
            userMgmtPage.clickRemoveRole();
            
            // Confirm removal action
            logger.info("Confirming removal action");
            userMgmtPage.confirmRemoval();
            
            // Verify "Editor" role removed from user profile
            logger.info("Verifying Editor role removal");
            Assert.assertTrue(userMgmtPage.isRoleRemoved("Editor"), "Editor role should be removed from user");
            Assert.assertTrue(userMgmtPage.isSuccessMessageDisplayed(), "Success message should be displayed");
            
            logger.info("Test completed successfully");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}