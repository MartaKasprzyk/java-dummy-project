package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserManagementPage;
import testBase.BaseClass;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TC_020_HandleBulkAssignmentWithMixedUserStates extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_020_handle_bulk_assignment_with_mixed_user_states() {
        String testName = "TC_020_handle_bulk_assignment_with_mixed_user_states";
        
        try {
            logger.info("Starting " + testName);
            
            // Navigate to application
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            UserManagementPage userPage = new UserManagementPage(driver);
            
            // Test steps
            logger.info("Step 1: Access bulk assignment interface");
            userPage.accessBulkAssignmentInterface();
            
            logger.info("Step 2: Select mixed users: 2 active, 1 inactive, 1 suspended");
            userPage.selectMixedUsers();
            
            logger.info("Step 3: Choose 'Analyst' role for bulk assignment");
            userPage.chooseBulkRole("Analyst");
            
            logger.info("Step 4: Execute bulk assignment operation");
            userPage.executeBulkAssignment();
            
            logger.info("Step 5: Review operation results summary");
            
            // Verify expected results
            logger.info("Verifying operation results are displayed");
            Assert.assertTrue(userPage.isOperationResultsDisplayed(), 
                "Operation results should be displayed after bulk assignment");
            
            logger.info("Verifying active users receive role successfully");
            Assert.assertTrue(userPage.hasSuccessResults(), 
                "Some users should receive role successfully");
            
            logger.info("Verifying inactive/suspended users show warnings");
            Assert.assertTrue(userPage.isWarningMessageDisplayed(), 
                "Warning messages should be displayed for inactive/suspended users");
            
            logger.info("Verifying detailed result report is displayed");
            List<WebElement> resultsSummary = userPage.getResultsSummary();
            Assert.assertTrue(resultsSummary.size() > 0, 
                "Detailed results summary should be displayed");
            
            logger.info("Verifying mixed status results (success and warnings)");
            boolean hasSuccessResults = false;
            boolean hasWarningResults = false;
            
            for (WebElement result : resultsSummary) {
                String resultText = result.getText().toLowerCase();
                if (resultText.contains("success") || resultText.contains("assigned")) {
                    hasSuccessResults = true;
                }
                if (resultText.contains("warning") || resultText.contains("inactive") || resultText.contains("suspended")) {
                    hasWarningResults = true;
                }
            }
            
            Assert.assertTrue(hasSuccessResults, "Should have successful assignments for active users");
            Assert.assertTrue(hasWarningResults, "Should have warnings for inactive/suspended users");
            
            logger.info("Test completed successfully - Bulk assignment handled mixed user states correctly");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}