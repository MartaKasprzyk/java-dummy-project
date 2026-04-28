package testCases;

import testBase.BaseClass;
import pageObjects.UserManagementPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_019_AssignRoleToInactiveUserAccount extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_019_AssignRoleToInactiveUserAccount() {
        String testName = "TC_019_AssignRoleToInactiveUserAccount";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            UserManagementPage userManagementPage = new UserManagementPage(driver);
            
            // Test steps
            logger.info("Accessing user management interface");
            userManagementPage.accessUserManagementInterface();
            
            logger.info("Searching for inactive user 'inactive.user@test.com'");
            userManagementPage.searchForUser("inactive.user@test.com");
            
            logger.info("Selecting 'Viewer' role for assignment");
            userManagementPage.selectRole("Viewer");
            
            logger.info("Attempting to assign role to inactive user");
            userManagementPage.clickAssignRole();
            
            // Verifications
            logger.info("Verifying warning message is displayed");
            Assert.assertTrue(userManagementPage.isInactiveUserWarningDisplayed(), 
                "Inactive user warning should be displayed");
            
            logger.info("Verifying warning message content");
            String warningText = userManagementPage.getWarningMessageText();
            Assert.assertTrue(warningText.contains("User account inactive"), 
                "Warning message should contain 'User account inactive'");
            
            captureScreenshot(testName + "_success");
            logger.info(testName + " completed successfully");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}