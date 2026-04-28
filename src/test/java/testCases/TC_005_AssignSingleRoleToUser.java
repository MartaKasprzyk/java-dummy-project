package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserManagementPage;
import testBase.BaseClass;

public class TC_005_AssignSingleRoleToUser extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_005_assign_single_role_to_user_successfully() {
        String testName = "TC_005_assign_single_role_to_user_successfully";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            UserManagementPage userMgmtPage = new UserManagementPage(driver);
            
            // Navigate to user management section
            logger.info("Navigating to user management section");
            
            // Search for user "john.doe@test.com"
            logger.info("Searching for user john.doe@test.com");
            userMgmtPage.searchForUser("john.doe@test.com");
            
            // Click user profile to access role assignment
            logger.info("Clicking user profile to access role assignment");
            userMgmtPage.clickUserProfile("john.doe@test.com");
            
            // Select "Viewer" role from dropdown
            logger.info("Selecting Viewer role from dropdown");
            userMgmtPage.selectRole("Viewer");
            
            // Click "Assign Role" button
            logger.info("Clicking Assign Role button");
            userMgmtPage.clickAssignRole();
            
            // Verify user receives "Viewer" permissions immediately
            logger.info("Verifying role assignment");
            Assert.assertTrue(userMgmtPage.isRoleAssigned("Viewer"), "Viewer role should be assigned to user");
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