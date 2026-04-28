package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleManagementPage;
import testBase.BaseClass;

public class TC_018_CreateRoleWithInvalidSpecialCharacters extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_018_create_role_with_invalid_special_characters() {
        String testName = "TC_018_create_role_with_invalid_special_characters";
        
        try {
            logger.info("Starting " + testName);
            
            // Navigate to application
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            RoleManagementPage rolePage = new RoleManagementPage(driver);
            
            // Test steps
            logger.info("Step 1: Access role creation form");
            rolePage.accessRoleCreationForm();
            
            logger.info("Step 2: Enter role name with special characters 'Test@Role#$%'");
            rolePage.enterRoleName("Test@Role#$%");
            
            logger.info("Step 3: Select valid permissions");
            rolePage.selectPermissions();
            
            logger.info("Step 4: Attempt to save role");
            rolePage.clickSaveRole();
            
            // Verify expected results
            logger.info("Verifying validation error is displayed");
            Assert.assertTrue(rolePage.isValidationErrorDisplayed(), 
                "Validation error should be displayed for invalid special characters");
            
            String errorText = rolePage.getValidationErrorText();
            logger.info("Validation error text: " + errorText);
            Assert.assertTrue(errorText.contains("Role name contains invalid characters"), 
                "Error message should indicate role name contains invalid characters");
            
            logger.info("Verifying that allowed characters are specified in error message");
            Assert.assertTrue(errorText.toLowerCase().contains("allowed") || 
                           errorText.toLowerCase().contains("valid") || 
                           errorText.toLowerCase().contains("permitted"), 
                "Error message should specify allowed characters");
            
            logger.info("Test completed successfully - Form submission was blocked as expected");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}