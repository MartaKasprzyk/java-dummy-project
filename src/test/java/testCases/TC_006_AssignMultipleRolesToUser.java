package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserManagementPage;
import testBase.BaseClass;

public class TC_006_AssignMultipleRolesToUser extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_006_assign_multiple_roles_to_single_user() {
        String testName = "TC_006_assign_multiple_roles_to_single_user";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            UserManagementPage userMgmtPage = new UserManagementPage(driver);
            
            // Navigate to user profile for "sarah.smith@test.com"
            logger.info("Navigating to user profile for sarah.smith@test.com");
            userMgmtPage.searchForUser("sarah.smith@test.com");
            userMgmtPage.clickUserProfile("sarah.smith@test.com");
            
            // Access role assignment section
            logger.info("Accessing role assignment section");
            userMgmtPage.navigateToAssignedRolesSection();
            
            // Select both "Viewer" and "Editor" roles using multi-select
            logger.info("Selecting both Viewer and Editor roles");
            String[] roles = {"Viewer", "Editor"};
            userMgmtPage.selectMultipleRoles(roles);
            
            // Apply role assignments simultaneously
            logger.info("Applying role assignments simultaneously");
            userMgmtPage.clickAssignRole();
            
            // Verify combined permissions in user profile
            logger.info("Verifying combined permissions in user profile");
            Assert.assertTrue(userMgmtPage.isRoleAssigned("Viewer"), "Viewer role should be assigned to user");
            Assert.assertTrue(userMgmtPage.isRoleAssigned("Editor"), "Editor role should be assigned to user");
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