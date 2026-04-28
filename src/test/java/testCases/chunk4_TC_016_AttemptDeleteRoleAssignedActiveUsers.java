package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleManagementPage;
import testBase.BaseClass;

public class TC_016_AttemptDeleteRoleAssignedActiveUsers extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_016_attempt_delete_role_assigned_active_users() {
        String testName = "TC_016_attempt_delete_role_assigned_active_users";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Navigate to role management interface
            logger.info("Navigating to role management interface");
            roleManagementPage.accessRoleManagement();
            
            // Select "Active_Role" with active assignments
            logger.info("Selecting Active_Role with active assignments");
            roleManagementPage.selectRoleByName("Active_Role");
            
            // Attempt to delete role
            logger.info("Attempting to delete role assigned to active users");
            roleManagementPage.clickDeleteRole();
            
            // Observe system response and warnings
            logger.info("Verifying system prevents deletion and shows warning");
            
            // Verify system prevents deletion with warning message
            Assert.assertTrue(roleManagementPage.isWarningMessageDisplayed(), "Warning message should be displayed");
            
            String warningMessage = roleManagementPage.getWarningMessage();
            Assert.assertTrue(warningMessage.contains("Role assigned to") && 
                            warningMessage.contains("active users"), 
                            "Warning should indicate role is assigned to active users");
            
            // Verify role remains in system
            Assert.assertTrue(roleManagementPage.isRoleStillInSystem("Active_Role"), 
                            "Role should remain in the system after failed deletion attempt");
            
            logger.info("Successfully validated role deletion prevention for active assignments");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}
