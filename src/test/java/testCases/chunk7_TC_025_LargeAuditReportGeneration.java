package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AuditReportingPage;
import testBase.BaseClass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC_025_LargeAuditReportGeneration extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_025_large_audit_report_generation_performance() {
        String testName = "TC_025_large_audit_report_generation_performance";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            AuditReportingPage auditPage = new AuditReportingPage(driver);
            
            // Access audit reporting interface
            logger.info("Accessing audit reporting interface");
            auditPage.accessAuditReportingInterface();
            
            // Select large date range covering extensive audit data (10000+ entries)
            String startDate = "2020-01-01";
            String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            logger.info("Selecting large date range: " + startDate + " to " + endDate);
            auditPage.selectLargeDateRange(startDate, endDate);
            
            // Generate comprehensive audit report with all data
            long startTime = System.currentTimeMillis();
            logger.info("Generating comprehensive audit report");
            auditPage.generateReport();
            
            // Monitor system performance during generation
            logger.info("Monitoring system performance during generation");
            Assert.assertTrue(auditPage.isSystemResponsive(), "System should remain responsive during report generation");
            
            // Verify report completeness and download success
            logger.info("Verifying report generation within acceptable time");
            Assert.assertTrue(auditPage.isReportGenerated(), "Report should be generated successfully");
            
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime) / 1000; // Convert to seconds
            
            // Verify generation time is within acceptable limit (5 minutes = 300 seconds)
            Assert.assertTrue(duration < 300, "Report generation should complete within 5 minutes. Actual time: " + duration + " seconds");
            
            // Verify download functionality
            Assert.assertTrue(auditPage.isDownloadButtonVisible(), "Download button should be visible after report generation");
            auditPage.downloadReport();
            
            logger.info("Large audit report generated successfully within acceptable time: " + duration + " seconds");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}