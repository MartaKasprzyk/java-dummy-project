package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleManagementPage;
import testBase.BaseClass;

public class TC_017_CreateRoleWithEmptyNameField extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_017_create_role_with_empty_name_field() {
        String testName = "TC_017_create_role_with_empty_name_field";
        
        try {
            logger.info("Starting " + testName);
            
            // Navigate to application
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            RoleManagementPage rolePage = new RoleManagementPage(driver);
            
            // Test steps
            logger.info("Step 1: Access role creation form");
            rolePage.accessRoleCreationForm();
            
            logger.info("Step 2: Leave role name field completely empty");
            rolePage.enterRoleName(""); // Empty string
            
            logger.info("Step 3: Select valid permissions from available options");
            rolePage.selectPermissions();
            
            logger.info("Step 4: Attempt to save role");
            rolePage.clickSaveRole();
            
            // Verify expected results
            logger.info("Verifying validation error is displayed");
            Assert.assertTrue(rolePage.isValidationErrorDisplayed(), 
                "Validation error should be displayed for empty role name");
            
            String errorText = rolePage.getValidationErrorText();
            logger.info("Validation error text: " + errorText);
            Assert.assertTrue(errorText.contains("Role name is required"), 
                "Error message should indicate role name is required");
            
            logger.info("Verifying role name field is highlighted in red");
            Assert.assertTrue(rolePage.isRoleNameFieldHighlighted(), 
                "Role name field should be highlighted to indicate error");
            
            logger.info("Test completed successfully - Form submission was blocked as expected");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}