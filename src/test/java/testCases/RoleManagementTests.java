package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.RoleManagementPage;
import testBase.BaseClass;

public class RoleManagementTests extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void TC_001_admin_access_role_management_interface_successfully() {
        String testName = "TC_001_admin_access_role_management_interface_successfully";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Navigate to role management interface
            roleManagementPage.navigateToRoleManagement();
            
            // Click on "View Roles" menu option
            roleManagementPage.clickViewRoles();
            
            // Verify interface loads within 3 seconds and review displayed role list
            Assert.assertTrue(roleManagementPage.isInterfaceLoaded(), "Interface should load within 3 seconds");
            Assert.assertTrue(roleManagementPage.areRolesDisplayed(), "All existing roles should be displayed");
            Assert.assertTrue(roleManagementPage.arePermissionsVisible(), "Role permissions should be visible");
            Assert.assertTrue(roleManagementPage.isRoleCountDisplayed(), "Role count should be displayed");
            Assert.assertFalse(roleManagementPage.hasErrorMessages(), "No error messages should be present");
            
            captureScreenshot(testName + "_success");
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }

    @Test(groups={"Regression","Master"})
    public void TC_002_create_new_role_with_valid_parameters() {
        String testName = "TC_002_create_new_role_with_valid_parameters";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Navigate to role management interface
            roleManagementPage.navigateToRoleManagement();
            
            // Click "Create New Role" button
            roleManagementPage.clickCreateNewRole();
            
            // Enter role name "QA_Analyst"
            roleManagementPage.enterRoleName("QA_Analyst");
            
            // Select permissions: "view_reports", "create_test_cases"
            roleManagementPage.selectPermission("view_reports");
            roleManagementPage.selectPermission("create_test_cases");
            
            // Click "Save Role" button
            roleManagementPage.clickSaveRole();
            
            // Verify success message and role appears in list
            Assert.assertTrue(roleManagementPage.isSuccessMessageDisplayed(), "Success message should display");
            Assert.assertTrue(roleManagementPage.isRoleInList("QA_Analyst"), "Role QA_Analyst should appear in roles list");
            Assert.assertTrue(roleManagementPage.isRoleAvailableForAssignment("QA_Analyst"), "Role should be available for assignment");
            
            captureScreenshot(testName + "_success");
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }

    @Test(groups={"Regression","Master"})
    public void TC_003_edit_existing_role_name_and_permissions() {
        String testName = "TC_003_edit_existing_role_name_and_permissions";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Navigate to role management interface
            roleManagementPage.navigateToRoleManagement();
            
            // Select "Test_Role" from list
            roleManagementPage.selectRoleFromList("Test_Role");
            
            // Click "Edit" button
            roleManagementPage.clickEditRole();
            
            // Change name to "Updated_Test_Role" and modify permissions
            roleManagementPage.updateRoleName("Updated_Test_Role");
            roleManagementPage.modifyPermissions();
            
            // Save changes
            roleManagementPage.clickSaveChanges();
            
            // Verify role name updated and permission changes reflected
            Assert.assertTrue(roleManagementPage.isRoleInList("Updated_Test_Role"), "Role name should be updated in list");
            Assert.assertTrue(roleManagementPage.arePermissionChangesReflected(), "Permission changes should be reflected");
            Assert.assertTrue(roleManagementPage.isAuditLogEntryCreated(), "Audit log entry should be created");
            
            captureScreenshot(testName + "_success");
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }

    @Test(groups={"Regression","Master"})
    public void TC_004_delete_unused_role_successfully() {
        String testName = "TC_004_delete_unused_role_successfully";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // Access role management interface
            roleManagementPage.navigateToRoleManagement();
            
            // Select "Temp_Role" and verify 0 assigned users
            roleManagementPage.selectRoleFromList("Temp_Role");
            Assert.assertTrue(roleManagementPage.verifyZeroAssignedUsers("Temp_Role"), "Role should have 0 assigned users");
            
            // Click "Delete" button
            roleManagementPage.clickDeleteRole();
            
            // Confirm deletion in popup dialog
            roleManagementPage.confirmDeletion();
            
            // Verify role removed from list and confirmation message
            Assert.assertFalse(roleManagementPage.isRoleInList("Temp_Role"), "Role should be removed from list");
            Assert.assertTrue(roleManagementPage.isConfirmationMessageDisplayed(), "Confirmation message should display");
            Assert.assertFalse(roleManagementPage.isRoleInAssignmentDropdown("Temp_Role"), "Role should not appear in assignment dropdowns");
            
            captureScreenshot(testName + "_success");
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}