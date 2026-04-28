package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AuditReportPage;
import testBase.BaseClass;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TC_014_GenerateExportComplianceAuditReport extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_014_generate_export_compliance_audit_report() {
        String testName = "TC_014_generate_export_compliance_audit_report";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            AuditReportPage auditReportPage = new AuditReportPage(driver);
            
            // Access audit reporting interface
            logger.info("Accessing audit reporting interface");
            auditReportPage.accessAuditReporting();
            
            // Set date range for last 30 days
            logger.info("Setting date range for last 30 days");
            LocalDate toDate = LocalDate.now();
            LocalDate fromDate = toDate.minusDays(30);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            
            auditReportPage.setDateRange(fromDate.format(formatter), toDate.format(formatter));
            
            // Select export format as PDF
            logger.info("Selecting PDF export format");
            auditReportPage.selectExportFormat("PDF");
            
            // Generate comprehensive audit report
            logger.info("Generating comprehensive audit report");
            auditReportPage.generateReport();
            
            // Verify report generation
            Assert.assertTrue(auditReportPage.isReportGenerated(), "Report should be generated successfully");
            
            // Download exported file
            logger.info("Downloading exported file");
            auditReportPage.downloadReport();
            
            // Verify file size > 100KB
            Assert.assertTrue(auditReportPage.isFileSizeValid(), "File size should be greater than 100KB");
            
            logger.info("Successfully generated and exported compliance audit report");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}