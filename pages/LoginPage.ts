import { Page, Locator } from '@playwright/test';

export class LoginPage {
  readonly page: Page;
  readonly usernameInput: Locator;
  readonly passwordInput: Locator;
  readonly loginButton: Locator;
  readonly errorMessage: Locator;

  constructor(page: Page) {
    this.page = page;
    this.usernameInput = page.locator("input[name='username'], input[type='email']");
    this.passwordInput = page.locator("input[name='password'], input[type='password']");
    this.loginButton = page.locator("button[type='submit'], button:has-text('Login')");
    this.errorMessage = page.locator('.error-message, .alert-danger');
  }

  async navigateToLogin() {
    await this.page.goto('/login');
    await this.page.waitForLoadState('networkidle');
  }

  async login(username: string, password: string) {
    await this.usernameInput.fill(username);
    await this.passwordInput.fill(password);
    await this.loginButton.click();
    await this.page.waitForLoadState('networkidle');
  }

  async loginAsAdmin() {
    const adminUsername = process.env.ADMIN_USERNAME || 'admin@example.com';
    const adminPassword = process.env.ADMIN_PASSWORD || 'SecurePassword123';
    await this.login(adminUsername, adminPassword);
  }
}