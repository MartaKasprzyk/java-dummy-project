import { test, expect } from '../../fixtures/test-fixtures';
import { TestDataGenerator } from '../../utils/test-data-generator';
import { ScreenshotHelper } from '../../utils/screenshot-helper';

test.describe('User Management - Create New User', () => {
  test.beforeEach(async () => {
    ScreenshotHelper.ensureScreenshotDirectory();
  });

  test.afterEach(async ({ page }, testInfo) => {
    if (testInfo.status === 'failed') {
      await ScreenshotHelper.captureScreenshot(
        page,
        testInfo.title.replace(/\s+/g, '_'),
        'failure'
      );
    }
  });

  test('TC001 - Should successfully create a new user with valid details', async ({
    authenticatedPage,
    page
  }) => {
    const testData = {
      userName: 'John Smith',
      email: 'john.smith@bank.com',
      contact: '+1-555-0123'
    };

    try {
      // Step 1: Click Create New User button
      await authenticatedPage.clickCreateNewUser();
      await page.waitForTimeout(1000);

      // Step 2: Fill user details
      await authenticatedPage.fillUserDetails(
        testData.userName,
        testData.email,
        testData.contact
      );

      // Step 3: Click Save button
      await authenticatedPage.clickSave();

      // Step 4: Verify success message
      await authenticatedPage.verifyUserCreationSuccess(testData.userName);

      // Step 5: Verify user appears in list with unique ID
      const userId = await authenticatedPage.verifyUserInList(testData.userName);
      expect(userId).toBeTruthy();

      // Capture success screenshot
      await ScreenshotHelper.captureScreenshot(
        page,
        'create_user_success',
        'success'
      );

      console.log(`User created successfully with ID: ${userId}`);
    } catch (error) {
      await ScreenshotHelper.captureScreenshot(
        page,
        'create_user_error',
        'failure'
      );
      throw error;
    }
  });

  test('TC002 - Should create user with dynamically generated data', async ({
    authenticatedPage,
    page
  }) => {
    const testData = TestDataGenerator.generateUserData();

    try {
      await authenticatedPage.clickCreateNewUser();
      await authenticatedPage.fillUserDetails(
        testData.userName,
        testData.email,
        testData.contact
      );
      await authenticatedPage.clickSave();
      await authenticatedPage.verifyUserCreationSuccess(testData.userName);

      const userId = await authenticatedPage.verifyUserInList(testData.userName);
      expect(userId).toBeTruthy();

      await ScreenshotHelper.captureScreenshot(
        page,
        'create_user_dynamic_data_success',
        'success'
      );
    } catch (error) {
      await ScreenshotHelper.captureScreenshot(
        page,
        'create_user_dynamic_data_error',
        'failure'
      );
      throw error;
    }
  });

  test('TC003 - Should display validation for required fields', async ({
    authenticatedPage,
    page
  }) => {
    try {
      await authenticatedPage.clickCreateNewUser();
      await authenticatedPage.clickSave();

      // Verify validation messages appear
      const userNameError = page.locator(".error:near(input[name='userName'])");
      const emailError = page.locator(".error:near(input[name='email'])");
      
      await expect(userNameError).toBeVisible({ timeout: 5000 });
      await expect(emailError).toBeVisible({ timeout: 5000 });

      await ScreenshotHelper.captureScreenshot(
        page,
        'validation_errors_success',
        'success'
      );
    } catch (error) {
      await ScreenshotHelper.captureScreenshot(
        page,
        'validation_errors_failure',
        'failure'
      );
      throw error;
    }
  });
});