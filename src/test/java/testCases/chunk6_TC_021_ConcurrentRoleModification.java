package testCases;

import testBase.BaseClass;
import pageObjects.RoleManagementPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TC_021_ConcurrentRoleModification extends BaseClass {
    
    @Test(groups={"Regression","Master"})
    public void TC_021_concurrent_role_modification_scenarios() {
        String testName = "TC_021_concurrent_role_modification_scenarios";
        try {
            logger.info("Starting " + testName);
            driver.get(p.getProperty("appURL"));
            
            // Login as first admin
            loginAsAdmin();
            
            RoleManagementPage roleManagementPage = new RoleManagementPage(driver);
            roleManagementPage.navigateToRoles();
            
            // Create shared role if not exists
            roleManagementPage.createRole("Shared_Role");
            roleManagementPage.selectPermissions(5);
            roleManagementPage.saveRole();
            
            // Simulate concurrent access with second driver
            WebDriver secondDriver = createSecondDriver();
            CompletableFuture<Boolean> admin1Task = CompletableFuture.supplyAsync(() -> {
                try {
                    // Admin1 modifies permissions
                    roleManagementPage.editRole("Shared_Role");
                    roleManagementPage.selectPermissions(10);
                    roleManagementPage.saveRole();
                    return roleManagementPage.isSuccessMessageDisplayed();
                } catch (Exception e) {
                    return false;
                }
            });
            
            CompletableFuture<Boolean> admin2Task = CompletableFuture.supplyAsync(() -> {
                try {
                    // Admin2 attempts conflicting changes
                    RoleManagementPage secondRoleManagementPage = new RoleManagementPage(secondDriver);
                    secondRoleManagementPage.navigateToRoles();
                    secondRoleManagementPage.editRole("Shared_Role");
                    secondRoleManagementPage.selectPermissions(8);
                    secondRoleManagementPage.saveRole();
                    return secondRoleManagementPage.isConflictWarningDisplayed();
                } catch (Exception e) {
                    return false;
                }
            });
            
            // Wait for both tasks to complete
            Boolean admin1Result = admin1Task.get();
            Boolean admin2Result = admin2Task.get();
            
            // Verify conflict resolution behavior
            Assert.assertTrue(admin1Result || admin2Result, "Expected either success or conflict warning");
            
            secondDriver.quit();
            captureScreenshot(testName + "_success");
            
        } catch (Exception | ExecutionException | InterruptedException e) {
            captureScreenshot(testName + "_failure");
            Assert.fail(e.getMessage());
        }
    }
    
    private void loginAsAdmin() {
        // Login implementation would go here
        // Using test credentials from properties
    }
    
    private WebDriver createSecondDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver secondDriver = new ChromeDriver(options);
        secondDriver.get(p.getProperty("appURL"));
        return secondDriver;
    }
}