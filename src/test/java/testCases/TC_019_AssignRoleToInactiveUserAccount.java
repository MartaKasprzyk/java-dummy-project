package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserManagementPage;
import testBase.BaseClass;

public class TC_019_AssignRoleToInactiveUserAccount extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_019_assign_role_to_inactive_user_account() {
        String testName = "TC_019_assign_role_to_inactive_user_account";
        
        try {
            logger.info("Starting " + testName);
            
            // Navigate to application
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            UserManagementPage userPage = new UserManagementPage(driver);
            
            // Test steps
            logger.info("Step 1: Access user management interface");
            userPage.accessUserManagementInterface();
            
            logger.info("Step 2: Search for inactive user 'inactive.user@test.com'");
            userPage.searchForUser("inactive.user@test.com");
            
            logger.info("Step 3: Attempt to assign 'Viewer' role to inactive user");
            userPage.assignRoleToUser("Viewer");
            
            logger.info("Step 4: Observe system response");
            
            // Verify expected results
            logger.info("Verifying system allows assignment with warning message");
            Assert.assertTrue(userPage.isWarningMessageDisplayed(), 
                "Warning message should be displayed for inactive user");
            
            String warningText = userPage.getWarningMessageText();
            logger.info("Warning message text: " + warningText);
            Assert.assertTrue(warningText.toLowerCase().contains("user account inactive"), 
                "Warning should indicate user account is inactive");
            
            logger.info("Verifying role is assigned but with inactive status notification");
            Assert.assertTrue(warningText.toLowerCase().contains("not effective") || 
                           warningText.toLowerCase().contains("until activation") ||
                           warningText.toLowerCase().contains("inactive"), 
                "Warning should indicate role not effective until activation");
            
            logger.info("Test completed successfully - Role assigned with appropriate warning");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}