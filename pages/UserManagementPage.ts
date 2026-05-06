import { Page, Locator, expect } from '@playwright/test';

export class UserManagementPage {
  readonly page: Page;
  readonly createUserButton: Locator;
  readonly userNameInput: Locator;
  readonly emailInput: Locator;
  readonly contactInput: Locator;
  readonly saveButton: Locator;
  readonly confirmationMessage: Locator;
  readonly userList: Locator;
  readonly cancelButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.createUserButton = page.locator("button:has-text('Create New User')");
    this.userNameInput = page.locator("input[name='userName']");
    this.emailInput = page.locator("input[name='email']");
    this.contactInput = page.locator("input[name='contact']");
    this.saveButton = page.locator("button:has-text('Save')");
    this.confirmationMessage = page.locator('.alert-success, .notification');
    this.userList = page.locator('.user-list, table[data-testid="users-table"]');
    this.cancelButton = page.locator("button:has-text('Cancel')");
  }

  async navigateToUserManagement() {
    await this.page.goto('/user-management');
    await this.page.waitForLoadState('networkidle');
  }

  async clickCreateNewUser() {
    await this.createUserButton.click();
    await this.page.waitForSelector("input[name='userName']", { state: 'visible' });
  }

  async fillUserDetails(userName: string, email: string, contact: string) {
    await this.userNameInput.fill(userName);
    await this.emailInput.fill(email);
    await this.contactInput.fill(contact);
  }

  async clickSave() {
    await this.saveButton.click();
  }

  async verifyUserCreationSuccess(userName: string) {
    await expect(this.confirmationMessage).toBeVisible({ timeout: 10000 });
    await expect(this.confirmationMessage).toContainText('User created successfully');
    await expect(this.userList).toContainText(userName);
  }

  async verifyUserInList(userName: string) {
    const userRow = this.page.locator(`tr:has-text("${userName}")`);
    await expect(userRow).toBeVisible();
    const userId = await userRow.getAttribute('data-user-id');
    expect(userId).toBeTruthy();
    return userId;
  }

  async captureScreenshot(name: string) {
    const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
    await this.page.screenshot({
      path: `screenshots/${name}_${timestamp}.png`,
      fullPage: true
    });
  }
}