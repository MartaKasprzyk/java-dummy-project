package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserManagementPage;
import testBase.BaseClass;

public class TC_008_BulkRoleAssignmentToUsers extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_008_perform_bulk_role_assignment_to_multiple_users() {
        String testName = "TC_008_perform_bulk_role_assignment_to_multiple_users";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Test data
            String[] userEmails = {"user1@test.com", "user2@test.com", "user3@test.com"};
            String roleName = "Analyst";
            
            // Initialize page object
            UserManagementPage userMgmtPage = new UserManagementPage(driver);
            
            // Navigate to bulk user management interface
            logger.info("Navigating to user management section");
            userMgmtPage.navigateToUserManagement();
            
            // Select multiple users using checkboxes
            logger.info("Selecting multiple users using checkboxes");
            userMgmtPage.selectMultipleUsers(userEmails);
            
            // Choose "Analyst" role for bulk assignment
            logger.info("Choosing Analyst role for bulk assignment");
            userMgmtPage.selectRole(roleName);
            
            // Execute bulk assignment operation
            logger.info("Executing bulk assignment operation");
            userMgmtPage.executeBulkAssignment();
            
            // Verify all users received role assignment
            logger.info("Verifying all users received role assignment");
            Assert.assertTrue(userMgmtPage.verifyAllUsersHaveRole(userEmails, roleName), 
                "All selected users should receive Analyst role");
            Assert.assertTrue(userMgmtPage.isSuccessMessageDisplayed(), 
                "Bulk success message should be displayed");
            
            logger.info("Test completed successfully");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}