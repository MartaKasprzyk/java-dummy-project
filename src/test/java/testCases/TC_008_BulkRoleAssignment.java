package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserManagementPage;
import testBase.BaseClass;

public class TC_008_BulkRoleAssignment extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_008_perform_bulk_role_assignment_to_multiple_users() {
        String testName = "TC_008_perform_bulk_role_assignment_to_multiple_users";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            UserManagementPage userMgmtPage = new UserManagementPage(driver);
            
            // Navigate to bulk user management interface
            logger.info("Navigating to bulk user management interface");
            
            // Select multiple users using checkboxes
            logger.info("Selecting multiple users using checkboxes");
            String[] users = {"user1@test.com", "user2@test.com", "user3@test.com"};
            userMgmtPage.selectUsersForBulkOperation(users);
            
            // Choose "Analyst" role for bulk assignment
            logger.info("Choosing Analyst role for bulk assignment");
            userMgmtPage.selectRole("Analyst");
            
            // Execute bulk assignment operation
            logger.info("Executing bulk assignment operation");
            userMgmtPage.clickBulkAssign();
            
            // Verify all users received role assignment
            logger.info("Verifying all users received role assignment");
            Assert.assertTrue(userMgmtPage.isSuccessMessageDisplayed(), "Bulk success message should be displayed");
            
            // Verify each user has the Analyst role assigned
            for (String user : users) {
                userMgmtPage.searchForUser(user);
                userMgmtPage.clickUserProfile(user);
                Assert.assertTrue(userMgmtPage.isRoleAssigned("Analyst"), "Analyst role should be assigned to " + user);
            }
            
            logger.info("Test completed successfully");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}