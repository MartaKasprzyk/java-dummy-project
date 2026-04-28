package testCases;

import testBase.BaseClass;
import pageObjects.RoleCreationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_018_CreateRoleWithInvalidSpecialCharacters extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_018_CreateRoleWithInvalidSpecialCharacters() {
        String testName = "TC_018_CreateRoleWithInvalidSpecialCharacters";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Initialize page object
            RoleCreationPage roleCreationPage = new RoleCreationPage(driver);
            
            // Test steps
            logger.info("Accessing role creation form");
            roleCreationPage.accessRoleCreationForm();
            
            logger.info("Entering role name with special characters 'Test@Role#$%'");
            roleCreationPage.enterRoleName("Test@Role#$%");
            
            logger.info("Selecting valid permissions");
            roleCreationPage.selectPermissions();
            
            logger.info("Attempting to save role");
            roleCreationPage.clickSaveRole();
            
            // Verifications
            logger.info("Verifying validation error is displayed");
            Assert.assertTrue(roleCreationPage.isInvalidCharactersErrorDisplayed(), 
                "Invalid characters error should be displayed");
            
            logger.info("Verifying validation error message content");
            String errorText = roleCreationPage.getValidationErrorText();
            Assert.assertTrue(errorText.contains("Role name contains invalid characters"), 
                "Error message should contain 'Role name contains invalid characters'");
            
            captureScreenshot(testName + "_success");
            logger.info(testName + " completed successfully");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}