package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pages.PermissionManagementPage;
import pages.AuditLogPage;
import base.BaseClass;

public class PermissionManagementTest extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_009_configure_role_with_granular_permissions() {
        String testName = "TC_009_configure_role_with_granular_permissions";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            PermissionManagementPage permissionPage = new PermissionManagementPage(driver);
            
            // Create or edit role "Custom_Role"
            permissionPage.navigateToRoleManagement();
            permissionPage.createOrEditRole("Custom_Role");
            
            // Access granular permission configuration
            permissionPage.accessGranularPermissionConfiguration();
            
            // Select specific permissions: "read", "update" only
            permissionPage.selectPermission("read");
            permissionPage.selectPermission("update");
            permissionPage.deselectPermission("create");
            permissionPage.deselectPermission("delete");
            permissionPage.deselectPermission("admin");
            
            // Save permission configuration
            permissionPage.savePermissionConfiguration();
            
            // Verify permission matrix reflects selections
            Assert.assertTrue(permissionPage.isPermissionSelected("read"), "Read permission should be selected");
            Assert.assertTrue(permissionPage.isPermissionSelected("update"), "Update permission should be selected");
            Assert.assertFalse(permissionPage.isPermissionSelected("create"), "Create permission should not be selected");
            Assert.assertFalse(permissionPage.isPermissionSelected("delete"), "Delete permission should not be selected");
            Assert.assertFalse(permissionPage.isPermissionSelected("admin"), "Admin permission should not be selected");
            
            captureScreenshot(testName + "_success");
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }

    @Test(groups={"Regression","Master"})
    public void TC_010_view_detailed_permission_scope_and_description() {
        String testName = "TC_010_view_detailed_permission_scope_and_description";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            PermissionManagementPage permissionPage = new PermissionManagementPage(driver);
            
            // Navigate to permission management interface
            permissionPage.navigateToPermissionManagement();
            
            // Select "create_test_cases" permission
            permissionPage.selectPermissionForDetails("create_test_cases");
            
            // Click details or information icon
            permissionPage.clickDetailsIcon();
            
            // Review permission scope and controlled areas
            String permissionScope = permissionPage.getPermissionScope();
            String permissionDescription = permissionPage.getPermissionDescription();
            
            Assert.assertTrue(permissionScope.contains("Test Case Management Module"), "Permission scope should show Test Case Management Module");
            Assert.assertFalse(permissionDescription.isEmpty(), "Permission description should not be empty");
            Assert.assertTrue(permissionPage.isPermissionDetailsDisplayed(), "Permission details should be displayed");
            
            captureScreenshot(testName + "_success");
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }

    @Test(groups={"Regression","Master"})
    public void TC_011_update_role_permissions_with_immediate_effect() {
        String testName = "TC_011_update_role_permissions_with_immediate_effect";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            PermissionManagementPage permissionPage = new PermissionManagementPage(driver);
            
            // Access role management for "Live_Role"
            permissionPage.navigateToRoleManagement();
            permissionPage.selectRole("Live_Role");
            
            // Edit permissions to add "delete_reports" capability
            permissionPage.editRolePermissions();
            permissionPage.addPermission("delete_reports");
            
            // Save permission changes
            permissionPage.savePermissionChanges();
            
            // Verify permission was added successfully
            Assert.assertTrue(permissionPage.isPermissionAddedToRole("Live_Role", "delete_reports"), "Delete reports permission should be added to Live_Role");
            
            // Verify success message or confirmation
            Assert.assertTrue(permissionPage.isPermissionUpdateSuccessful(), "Permission update should be successful");
            
            captureScreenshot(testName + "_success");
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }

    @Test(groups={"Regression","Master"})
    public void TC_012_access_audit_logs_for_role_changes() {
        String testName = "TC_012_access_audit_logs_for_role_changes";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            AuditLogPage auditLogPage = new AuditLogPage(driver);
            
            // Navigate to audit log interface
            auditLogPage.navigateToAuditLogs();
            
            // Filter by "role_management" activities
            auditLogPage.filterByActivity("role_management");
            
            // Review log entries for recent changes
            auditLogPage.loadRecentLogEntries();
            
            // Verify log details include user, timestamp, changes
            Assert.assertTrue(auditLogPage.areLogEntriesVisible(), "Audit log entries should be visible");
            Assert.assertTrue(auditLogPage.logContainsField("user_id"), "Logs should contain user_id field");
            Assert.assertTrue(auditLogPage.logContainsField("timestamp"), "Logs should contain timestamp field");
            Assert.assertTrue(auditLogPage.logContainsField("action"), "Logs should contain action field");
            Assert.assertTrue(auditLogPage.logContainsField("details"), "Logs should contain details field");
            
            // Verify logs are sortable and filterable
            Assert.assertTrue(auditLogPage.areLogsSortable(), "Logs should be sortable");
            Assert.assertTrue(auditLogPage.areLogsFilterable(), "Logs should be filterable");
            
            captureScreenshot(testName + "_success");
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}