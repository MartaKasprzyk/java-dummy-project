package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AuditLogPage;
import testBase.BaseClass;

public class TC_012_AccessAuditLogsForRoleChanges extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_012_access_audit_logs_for_role_changes() {
        String testName = "TC_012_access_audit_logs_for_role_changes";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));

            // Initialize page object
            AuditLogPage auditLogPage = new AuditLogPage(driver);

            // Navigate to audit log interface
            logger.info("Navigating to audit log interface");
            auditLogPage.navigateToAuditLog();

            // Filter by "role_management" activities
            logger.info("Filtering by role_management activities");
            auditLogPage.filterByRoleManagementActivities();

            // Review log entries for recent changes
            logger.info("Reviewing audit log entries");
            Assert.assertTrue(auditLogPage.areLogEntriesVisible(), "Audit log entries should be visible");

            // Verify log details include user, timestamp, changes
            logger.info("Verifying log entry details");
            Assert.assertTrue(auditLogPage.verifyLogEntryDetails(), "Log entries should contain user, timestamp, action, and details");

            // Verify specific log entry details
            String userId = auditLogPage.getFirstLogEntryUserId();
            String timestamp = auditLogPage.getFirstLogEntryTimestamp();
            String action = auditLogPage.getFirstLogEntryAction();
            String details = auditLogPage.getFirstLogEntryDetails();

            Assert.assertFalse(userId.isEmpty(), "User ID should not be empty in audit log");
            Assert.assertFalse(timestamp.isEmpty(), "Timestamp should not be empty in audit log");
            Assert.assertFalse(action.isEmpty(), "Action should not be empty in audit log");
            Assert.assertFalse(details.isEmpty(), "Details should not be empty in audit log");

            // Verify logs are sortable and filterable
            Assert.assertTrue(auditLogPage.isLogsSortable(), "Audit logs should be sortable");
            Assert.assertTrue(auditLogPage.isLogsFilterable(), "Audit logs should be filterable");

            logger.info("Test " + testName + " completed successfully");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test " + testName + " failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}