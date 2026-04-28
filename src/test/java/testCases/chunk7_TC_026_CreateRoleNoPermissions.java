package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleCreationPage;
import testBase.BaseClass;

public class TC_026_CreateRoleNoPermissions extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_026_create_role_with_no_permissions_selected() {
        String testName = "TC_026_create_role_with_no_permissions_selected";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleCreationPage roleCreationPage = new RoleCreationPage(driver);
            
            // Enter valid role name "Empty_Role"
            logger.info("Entering role name: Empty_Role");
            roleCreationPage.enterRoleName("Empty_Role");
            
            // Leave all permission checkboxes unselected
            logger.info("Ensuring all permission checkboxes are unselected");
            roleCreationPage.leaveAllPermissionsUnselected();
            
            // Attempt to save role with zero permissions
            logger.info("Attempting to save role with zero permissions");
            roleCreationPage.attemptToSaveRole();
            
            // Verify validation error displays "At least one permission must be selected"
            Assert.assertTrue(roleCreationPage.isValidationErrorDisplayed(), "Validation error should be displayed");
            
            String errorMessage = roleCreationPage.getValidationErrorText();
            Assert.assertTrue(errorMessage.contains("At least one permission must be selected"), 
                "Error message should indicate that at least one permission must be selected. Actual: " + errorMessage);
            
            // Verify form submission is blocked
            Assert.assertTrue(roleCreationPage.isFormSubmissionBlocked(), "Form submission should be blocked");
            
            logger.info("Validation error correctly displayed and form submission blocked");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}