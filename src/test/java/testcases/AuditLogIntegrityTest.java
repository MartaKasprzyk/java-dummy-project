package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pages.RoleManagementPage;
import pages.AuditLogPage;
import base.BaseClass;
import java.util.List;

public class AuditLogIntegrityTest extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_024_audit_log_integrity_and_completeness() {
        String testName = "TC_024_audit_log_integrity_and_completeness";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            AuditLogPage auditLogPage = new AuditLogPage(driver);
            
            // Login as admin with audit access
            roleManagementPage.loginAsAdmin("admin@test.com", "password123");
            
            // Perform sequence of role management activities
            roleManagementPage.navigateToRoleManagement();
            
            // Create role
            roleManagementPage.createRole("Test_Audit_Role");
            
            // Modify role
            roleManagementPage.modifyRole("Test_Audit_Role");
            
            // Assign role
            roleManagementPage.assignRoleToUser("Test_Audit_Role", "testuser@test.com");
            
            // Delete role
            roleManagementPage.deleteRole("Test_Audit_Role");
            
            // Access audit logs immediately after activities
            auditLogPage.navigateToAuditLogs();
            
            // Verify all activities logged with complete metadata
            List<String> auditEntries = auditLogPage.getRecentAuditEntries();
            Assert.assertTrue(auditEntries.size() >= 4, "All role management activities should be logged");
            
            // Check for required metadata fields
            for (String entry : auditEntries) {
                Assert.assertTrue(auditLogPage.hasRequiredMetadata(entry, "user_id"), "Entry should contain user_id");
                Assert.assertTrue(auditLogPage.hasRequiredMetadata(entry, "timestamp"), "Entry should contain timestamp");
                Assert.assertTrue(auditLogPage.hasRequiredMetadata(entry, "action"), "Entry should contain action");
                Assert.assertTrue(auditLogPage.hasRequiredMetadata(entry, "resource"), "Entry should contain resource");
                Assert.assertTrue(auditLogPage.hasRequiredMetadata(entry, "details"), "Entry should contain details");
            }
            
            // Check timestamp accuracy and user attribution
            Assert.assertTrue(auditLogPage.verifyTimestampAccuracy(), "Timestamps should be accurate");
            Assert.assertTrue(auditLogPage.verifyUserAttribution("admin@test.com"), "User attribution should be correct");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}