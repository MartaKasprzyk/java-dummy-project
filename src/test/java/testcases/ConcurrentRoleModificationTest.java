package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pages.RoleManagementPage;
import base.BaseClass;

public class ConcurrentRoleModificationTest extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_021_concurrent_role_modification_scenarios() {
        String testName = "TC_021_concurrent_role_modification_scenarios";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Login as Admin1
            roleManagementPage.loginAsAdmin("admin1@test.com", "password123");
            
            // Navigate to role management and access shared role
            roleManagementPage.navigateToRoleManagement();
            roleManagementPage.selectRole("Shared_Role");
            
            // Note: In real scenario, this would require parallel execution
            // For test purposes, we simulate the conflict scenario
            
            // Admin1 modifies permissions
            roleManagementPage.modifyPermissions();
            
            // Simulate Admin2 attempting conflicting changes
            // This would trigger conflict detection
            boolean conflictDetected = roleManagementPage.attemptConflictingModification();
            
            // Verify conflict warning is displayed
            Assert.assertTrue(conflictDetected, "Conflict warning should be displayed");
            
            // Verify audit log shows both attempts
            Assert.assertTrue(roleManagementPage.verifyAuditLogEntries(), "Both modification attempts should be logged");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}