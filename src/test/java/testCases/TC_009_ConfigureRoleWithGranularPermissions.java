package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleManagementPage;
import testBase.BaseClass;

public class TC_009_ConfigureRoleWithGranularPermissions extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_009_configure_role_with_granular_permissions() {
        String testName = "TC_009_configure_role_with_granular_permissions";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));

            // Initialize page object
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);

            // Create or edit role "Custom_Role"
            logger.info("Creating role Custom_Role");
            roleManagementPage.clickCreateRole();
            roleManagementPage.enterRoleName("Custom_Role");

            // Access granular permission configuration and select specific permissions
            logger.info("Configuring granular permissions - read and update only");
            roleManagementPage.selectPermissions("read", "update");

            // Save permission configuration
            logger.info("Saving permission configuration");
            roleManagementPage.savePermissionConfiguration();

            // Verify the permissions are set correctly
            logger.info("Verifying granular permissions are configured correctly");
            Assert.assertTrue(roleManagementPage.isPermissionSelected("read"), "Read permission should be selected");
            Assert.assertTrue(roleManagementPage.isPermissionSelected("update"), "Update permission should be selected");
            Assert.assertFalse(roleManagementPage.isPermissionSelected("create"), "Create permission should not be selected");
            Assert.assertFalse(roleManagementPage.isPermissionSelected("delete"), "Delete permission should not be selected");
            Assert.assertFalse(roleManagementPage.isPermissionSelected("admin"), "Admin permission should not be selected");

            logger.info("Test " + testName + " completed successfully");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test " + testName + " failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}