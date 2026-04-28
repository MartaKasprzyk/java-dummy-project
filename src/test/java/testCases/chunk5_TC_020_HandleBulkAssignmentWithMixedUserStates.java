package testCases;

import testBase.BaseClass;
import pageObjects.UserManagementPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_020_HandleBulkAssignmentWithMixedUserStates extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_020_HandleBulkAssignmentWithMixedUserStates() {
        String testName = "TC_020_HandleBulkAssignmentWithMixedUserStates";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            UserManagementPage userManagementPage = new UserManagementPage(driver);
            
            // Test steps
            logger.info("Accessing bulk assignment interface");
            userManagementPage.accessUserManagementInterface();
            
            logger.info("Selecting mixed users: 2 active, 1 inactive, 1 suspended");
            userManagementPage.selectMixedUsers();
            
            logger.info("Choosing 'Analyst' role for bulk assignment");
            userManagementPage.selectRole("Analyst");
            
            logger.info("Executing bulk assignment operation");
            userManagementPage.executeBulkAssignment();
            
            // Verifications
            logger.info("Verifying operation results summary is displayed");
            Assert.assertTrue(userManagementPage.isOperationResultsSummaryDisplayed(), 
                "Operation results summary should be displayed");
            
            logger.info("Verifying active users receive role successfully");
            Assert.assertTrue(userManagementPage.areActiveUserResultsDisplayed(), 
                "Active user results should be displayed");
            
            logger.info("Verifying inactive/suspended users show warnings");
            Assert.assertTrue(userManagementPage.areWarningResultsDisplayed(), 
                "Warning results for inactive/suspended users should be displayed");
            
            captureScreenshot(testName + "_success");
            logger.info(testName + " completed successfully");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}