package testCases;

import testBase.BaseClass;
import pageObjects.RoleManagementPage;
import pageObjects.AuditLogPage;
import pageObjects.UserManagementPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_024_AuditLogIntegrityCompleteness extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_024_audit_log_integrity_and_completeness() {
        String testName = "TC_024_audit_log_integrity_and_completeness";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Admin login with audit access
            loginAsAdmin();
            
            // Record initial audit log count
            AuditLogPage auditLogPage = new AuditLogPage(driver);
            auditLogPage.navigateToAuditLog();
            int initialLogCount = auditLogPage.getAuditLogCount();
            
            // Perform sequence of role management activities
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            roleManagementPage.navigateToRoles();
            
            // Activity 1: Create role
            roleManagementPage.createRole("Test_Audit_Role");
            roleManagementPage.selectPermissions(5);
            roleManagementPage.saveRole();
            
            // Activity 2: Modify role
            roleManagementPage.editRole("Test_Audit_Role");
            roleManagementPage.selectPermissions(8);
            roleManagementPage.saveRole();
            
            // Activity 3: Assign role to user
            UserManagementPage userManagementPage = new UserManagementPage(driver);
            userManagementPage.navigateToUsers();
            userManagementPage.editUser("test.user@test.com");
            userManagementPage.changeUserRole("Test_Audit_Role");
            userManagementPage.saveUserChanges();
            
            // Activity 4: Delete role (if supported)
            // roleManagementPage.deleteRole("Test_Audit_Role");
            
            // Access audit logs immediately after activities
            auditLogPage.navigateToAuditLog();
            auditLogPage.refreshAuditLog();
            
            // Verify all activities logged
            Assert.assertTrue(auditLogPage.verifyAuditLogEntry("CREATE", "Role"), 
                "Role creation should be logged");
            Assert.assertTrue(auditLogPage.verifyAuditLogEntry("UPDATE", "Role"), 
                "Role modification should be logged");
            Assert.assertTrue(auditLogPage.verifyAuditLogEntry("ASSIGN", "User"), 
                "Role assignment should be logged");
            
            // Verify complete metadata present
            Assert.assertTrue(auditLogPage.verifyCompleteMetadata(), 
                "All audit log entries should have complete metadata (user, timestamp, action, details)");
            
            // Verify log count increased
            int finalLogCount = auditLogPage.getAuditLogCount();
            Assert.assertTrue(finalLogCount > initialLogCount, 
                "Audit log count should increase after activities");
            
            // Check timestamp accuracy (within reasonable time window)
            // This would require more detailed timestamp validation
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
    
    private void loginAsAdmin() {
        // Admin login implementation with audit access
    }
}