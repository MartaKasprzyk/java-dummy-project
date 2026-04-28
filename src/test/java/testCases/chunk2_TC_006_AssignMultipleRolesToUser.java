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
            
            // Test data
            String userEmail = "sarah.smith@test.com";
            String[] roles = {"Viewer", "Editor"};
            
            // Initialize page object
            UserManagementPage userMgmtPage = new UserManagementPage(driver);
            
            // Navigate to user profile for "sarah.smith@test.com"
            logger.info("Navigating to user management section");
            userMgmtPage.navigateToUserManagement();
            
            logger.info("Searching for user: " + userEmail);
            userMgmtPage.searchUser(userEmail);
            
            logger.info("Clicking user profile");
            userMgmtPage.clickUserProfile(userEmail);
            
            // Access role assignment section
            logger.info("Accessing role assignment section");
            userMgmtPage.accessRoleAssignmentSection();
            
            // Select both "Viewer" and "Editor" roles using multi-select
            logger.info("Selecting multiple roles: Viewer and Editor");
            userMgmtPage.selectMultipleRoles(roles);
            
            // Apply role assignments simultaneously
            logger.info("Applying role assignments");
            userMgmtPage.clickAssignRole();
            
            // Verify combined permissions in user profile
            logger.info("Verifying combined permissions in user profile");
            for (String role : roles) {
                Assert.assertTrue(userMgmtPage.isRoleAssigned(userEmail, role), 
                    "Role " + role + " should be assigned to user");
            }
            
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