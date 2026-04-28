package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PermissionManagementPage;
import testBase.BaseClass;

public class TC_010_ViewDetailedPermissionScopeAndDescription extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_010_view_detailed_permission_scope_and_description() {
        String testName = "TC_010_view_detailed_permission_scope_and_description";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));

            // Initialize page object
            PermissionManagementPage permissionManagementPage = new PermissionManagementPage(driver);

            // Navigate to permission management interface
            logger.info("Navigating to permission management interface");
            permissionManagementPage.navigateToPermissionManagement();

            // Select "create_test_cases" permission
            logger.info("Selecting create_test_cases permission");
            permissionManagementPage.selectCreateTestCasesPermission();

            // Click details or information icon
            logger.info("Clicking permission details icon");
            permissionManagementPage.clickPermissionDetailsIcon();

            // Review permission scope and controlled areas
            logger.info("Reviewing permission scope and description");
            Assert.assertTrue(permissionManagementPage.isDetailViewDisplayed(), "Permission detail view should be displayed");
            
            // Verify detailed view shows permission controls test case creation module
            Assert.assertTrue(permissionManagementPage.isPermissionScopeTestCaseModule(), 
                "Permission scope should indicate Test Case Management Module");

            // Verify description of accessible functions is present
            String description = permissionManagementPage.getPermissionDescription();
            Assert.assertFalse(description.isEmpty(), "Permission description should not be empty");

            String accessibleFunctions = permissionManagementPage.getAccessibleFunctions();
            Assert.assertFalse(accessibleFunctions.isEmpty(), "Accessible functions should not be empty");

            logger.info("Test " + testName + " completed successfully");
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            logger.error("Test " + testName + " failed: " + e.getMessage());
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}