package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RoleManagementPage;
import testBase.BaseClass;

public class TC_015_AttemptCreateRoleDuplicateName extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_015_attempt_create_role_duplicate_name() {
        String testName = "TC_015_attempt_create_role_duplicate_name";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Access role creation interface
            logger.info("Accessing role creation interface");
            roleManagementPage.accessRoleManagement();
            roleManagementPage.clickCreateRole();
            
            // Enter role name "Existing_Role" (duplicate)
            logger.info("Entering duplicate role name: Existing_Role");
            roleManagementPage.enterRoleName("Existing_Role");
            
            // Select valid permissions
            logger.info("Selecting valid permissions");
            roleManagementPage.selectPermissions();
            
            // Attempt to save role
            logger.info("Attempting to save role with duplicate name");
            roleManagementPage.saveRole();
            
            // Verify validation error displays
            Assert.assertTrue(roleManagementPage.isValidationErrorDisplayed(), "Validation error should be displayed");
            
            // Verify specific error message
            String errorMessage = roleManagementPage.getValidationErrorText();
            Assert.assertTrue(errorMessage.contains("Role name already exists") || 
                            errorMessage.contains("already exists"), 
                            "Error message should indicate role name already exists");
            
            logger.info("Successfully validated duplicate role name prevention");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}