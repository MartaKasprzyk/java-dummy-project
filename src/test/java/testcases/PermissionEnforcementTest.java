package testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pages.RoleManagementPage;
import pages.UserSessionPage;
import base.BaseClass;

public class PermissionEnforcementTest extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_022_permission_enforcement_during_active_session() {
        String testName = "TC_022_permission_enforcement_during_active_session";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            UserSessionPage userSessionPage = new UserSessionPage(driver);
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            
            // User logs in with Editor role
            userSessionPage.loginAsUser("test.user@test.com", "password123");
            
            // Verify initial permissions
            Assert.assertTrue(userSessionPage.canAccessEditorFunctions(), "User should have editor access initially");
            
            // Admin modifies user's role permissions (simulate in new session)
            roleManagementPage.loginAsAdminInNewTab();
            roleManagementPage.modifyUserRolePermissions("test.user@test.com", "Editor");
            
            // Switch back to user session and test real-time enforcement
            userSessionPage.switchToUserTab();
            userSessionPage.refreshCurrentPage();
            
            // Test previously accessible functions
            boolean restrictedFunctionAccessible = userSessionPage.attemptRestrictedFunction();
            Assert.assertFalse(restrictedFunctionAccessible, "Restricted functions should be immediately inaccessible");
            
            // Verify no re-authentication required
            Assert.assertTrue(userSessionPage.isStillLoggedIn(), "User should remain logged in");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
}