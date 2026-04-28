package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleCreationPage;
import testBase.BaseClass;

public class TC_015_AttemptCreateRoleWithDuplicateName extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_015_AttemptCreateRoleWithDuplicateName() {
        String testName = "TC_015_AttemptCreateRoleWithDuplicateName";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleCreationPage roleCreationPage = new RoleCreationPage(driver);
            
            // Access role creation interface
            logger.info("Accessing role creation interface");
            roleCreationPage.accessRoleCreationInterface();
            
            // Enter role name "Existing_Role" (duplicate)
            logger.info("Entering duplicate role name 'Existing_Role'");
            roleCreationPage.enterRoleName("Existing_Role");
            
            // Select valid permissions
            logger.info("Selecting valid permissions");
            roleCreationPage.selectValidPermissions();
            
            // Attempt to save role
            logger.info("Attempting to save role");
            roleCreationPage.attemptToSaveRole();
            
            // Verify validation error displays
            boolean validationErrorDisplayed = roleCreationPage.isValidationErrorDisplayed();
            Assert.assertTrue(validationErrorDisplayed, "Validation error should be displayed");
            
            String errorMessage = roleCreationPage.getValidationErrorMessage();
            Assert.assertEquals(errorMessage, "Role name already exists", "Error message should indicate role name already exists");
            
            // Verify form submission blocked
            boolean formSubmissionBlocked = roleCreationPage.isFormSubmissionBlocked();
            Assert.assertTrue(formSubmissionBlocked, "Form submission should be blocked");
            
            // Verify no role created
            boolean roleCreated = roleCreationPage.isRoleCreated("Existing_Role");
            Assert.assertFalse(roleCreated, "No duplicate role should be created");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}