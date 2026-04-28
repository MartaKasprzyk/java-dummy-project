package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleManagementPage;
import testBase.BaseClass;

public class TC_011_UpdateRolePermissionsWithImmediateEffect extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_011_update_role_permissions_with_immediate_effect() {
        String testName = "TC_011_update_role_permissions_with_immediate_effect";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));

            // Initialize page object
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);

            // Access role management for "Live_Role"
            logger.info("Accessing role management for Live_Role");
            roleManagementPage.editRoleByName("Live_Role");

            // Edit permissions to add "delete_reports" capability
            logger.info("Adding delete_reports permission to Live_Role");
            roleManagementPage.addDeleteReportsPermission();

            // Save permission changes
            logger.info("Saving permission changes");
            roleManagementPage.savePermissionConfiguration();

            // Verify the new permission is added
            logger.info("Verifying delete_reports permission is added");
            Assert.assertTrue(roleManagementPage.isPermissionSelected("delete-reports"), 
                "Delete reports permission should be selected after update");

            logger.info("Test " + testName + " completed successfully");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test " + testName + " failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}