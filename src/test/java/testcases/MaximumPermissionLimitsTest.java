package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pages.RoleManagementPage;
import base.BaseClass;

public class MaximumPermissionLimitsTest extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_023_maximum_permission_limits_boundary() {
        String testName = "TC_023_maximum_permission_limits_boundary";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Login as admin
            roleManagementPage.loginAsAdmin("admin@test.com", "password123");
            
            // Navigate to role management
            roleManagementPage.navigateToRoleManagement();
            
            // Create or edit role with maximum permissions
            roleManagementPage.createOrEditRole("Max_Permission_Role");
            
            // Select maximum allowed permissions (50)
            roleManagementPage.selectMaximumPermissions(50);
            
            // Attempt to add 51st permission
            boolean limitExceeded = roleManagementPage.attemptToAddPermissionBeyondLimit();
            Assert.assertTrue(limitExceeded, "System should prevent exceeding 50 permission limit");
            
            // Test system performance with maximum permission role
            long responseTime = roleManagementPage.measureRoleLoadTime();
            Assert.assertTrue(responseTime < 3000, "Performance should remain acceptable with max permissions");
            
            // Assign maximum permission role to test user
            roleManagementPage.assignRoleToUser("Max_Permission_Role", "testuser@test.com");
            
            // Verify user receives all 50 permissions
            int permissionCount = roleManagementPage.getUserPermissionCount("testuser@test.com");
            Assert.assertEquals(permissionCount, 50, "User should receive all 50 permissions");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}