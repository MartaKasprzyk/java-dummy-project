package testCases;

import testBase.BaseClass;
import pageObjects.RoleCreationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_017_CreateRoleWithEmptyNameField extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_017_CreateRoleWithEmptyNameField() {
        String testName = "TC_017_CreateRoleWithEmptyNameField";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            RoleCreationPage roleCreationPage = new RoleCreationPage(driver);
            
            // Test steps
            logger.info("Accessing role creation form");
            roleCreationPage.accessRoleCreationForm();
            
            logger.info("Leaving role name field completely empty");
            roleCreationPage.leaveRoleNameEmpty();
            
            logger.info("Selecting valid permissions from available options");
            roleCreationPage.selectPermissions();
            
            logger.info("Attempting to save role");
            roleCreationPage.clickSaveRole();
            
            // Verifications
            logger.info("Verifying validation error is displayed");
            Assert.assertTrue(roleCreationPage.isRoleNameRequiredErrorDisplayed(), 
                "Role name required error should be displayed");
            
            logger.info("Verifying field is highlighted in red");
            Assert.assertTrue(roleCreationPage.isFieldHighlighted(), 
                "Role name field should be highlighted in red");
            
            logger.info("Verifying validation error message content");
            String errorText = roleCreationPage.getValidationErrorText();
            Assert.assertTrue(errorText.contains("Role name is required"), 
                "Error message should contain 'Role name is required'");
            
            captureScreenshot(testName + "_success");
            logger.info(testName + " completed successfully");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}