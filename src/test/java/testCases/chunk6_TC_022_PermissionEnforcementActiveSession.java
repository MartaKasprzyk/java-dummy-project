package testCases;

import testBase.BaseClass;
import pageObjects.UserManagementPage;
import pageObjects.RoleManagementPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TC_022_PermissionEnforcementActiveSession extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_022_permission_enforcement_during_active_session() {
        String testName = "TC_022_permission_enforcement_during_active_session";
        WebDriver userSession = null;
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Admin login
            loginAsAdmin();
            
            // Create a separate session for test user
            userSession = createUserSession("test.user@test.com", "Editor");
            
            // Verify user has initial permissions
            UserManagementPage userPage = new UserManagementPage(userSession);
            boolean initialAccess = userPage.hasPermission("EDIT_CONTENT");
            Assert.assertTrue(initialAccess, "User should initially have edit permissions");
            
            // Admin modifies user's role permissions while user is active
            UserManagementPage adminUserPage = new UserManagementPage(driver);
            adminUserPage.navigateToUsers();
            adminUserPage.editUser("test.user@test.com");
            adminUserPage.changeUserRole("Viewer"); // Restrict permissions
            adminUserPage.saveUserChanges();
            
            // Switch back to user session and test immediate enforcement
            userPage.testRestrictedFunction();
            
            // Verify restricted functions are immediately inaccessible
            boolean accessDenied = userPage.isAccessDenied();
            Assert.assertTrue(accessDenied, "User should immediately lose access to restricted functions");
            
            // Verify user no longer has edit permissions
            boolean hasEditPermission = userPage.hasPermission("EDIT_CONTENT");
            Assert.assertFalse(hasEditPermission, "User should no longer have edit permissions");
            
            captureScreenshot(testName + "_success");
            
        } catch (Exception e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        } finally {
            if (userSession != null) {
                userSession.quit();
            }
        }
    }
    
    private void loginAsAdmin() {
        // Admin login implementation
    }
    
    private WebDriver createUserSession(String email, String role) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver userDriver = new ChromeDriver(options);
        userDriver.get(p.getProperty("appURL"));
        // Login as test user
        return userDriver;
    }
}