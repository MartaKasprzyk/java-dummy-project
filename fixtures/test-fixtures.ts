import { test as base } from '@playwright/test';
import { LoginPage } from '../pages/LoginPage';
import { UserManagementPage } from '../pages/UserManagementPage';

type TestFixtures = {
  loginPage: LoginPage;
  userManagementPage: UserManagementPage;
  authenticatedPage: UserManagementPage;
};

export const test = base.extend<TestFixtures>({
  loginPage: async ({ page }, use) => {
    const loginPage = new LoginPage(page);
    await use(loginPage);
  },

  userManagementPage: async ({ page }, use) => {
    const userManagementPage = new UserManagementPage(page);
    await use(userManagementPage);
  },

  authenticatedPage: async ({ page }, use) => {
    const loginPage = new LoginPage(page);
    await loginPage.navigateToLogin();
    await loginPage.loginAsAdmin();
    
    const userManagementPage = new UserManagementPage(page);
    await userManagementPage.navigateToUserManagement();
    await use(userManagementPage);
  }
});

export { expect } from '@playwright/test';