package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AuditReportingPage;
import testBase.BaseClass;

public class TC_014_GenerateAndExportComplianceAuditReport extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_014_GenerateAndExportComplianceAuditReport() {
        String testName = "TC_014_GenerateAndExportComplianceAuditReport";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            AuditReportingPage auditPage = new AuditReportingPage(driver);
            
            // Access audit reporting interface
            logger.info("Accessing audit reporting interface");
            auditPage.accessAuditReportingInterface();
            
            // Set date range for last 30 days
            logger.info("Setting date range for last 30 days");
            auditPage.setDateRangeForLast30Days();
            
            // Select export format as PDF
            logger.info("Selecting export format as PDF");
            auditPage.selectPDFExportFormat();
            
            // Generate comprehensive audit report
            logger.info("Generating comprehensive audit report");
            auditPage.generateAuditReport();
            
            boolean reportGenerated = auditPage.isReportGenerated();
            Assert.assertTrue(reportGenerated, "PDF report should be generated successfully");
            
            // Download exported file
            logger.info("Downloading exported file");
            auditPage.downloadExportedFile();
            
            boolean fileDownloaded = auditPage.isFileDownloaded();
            Assert.assertTrue(fileDownloaded, "File should download without corruption");
            
            boolean containsRoleManagementActivities = auditPage.verifyReportContainsRoleActivities();
            Assert.assertTrue(containsRoleManagementActivities, "Report should contain all role management activities");
            
            long fileSize = auditPage.getDownloadedFileSize();
            Assert.assertTrue(fileSize > 100000, "File size should be greater than 100KB");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}