import { test, expect } from '../../fixtures/test-fixtures';
import { ScreenshotHelper } from '../../utils/screenshot-helper';

test.describe('User Management - User List', () => {
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

  test('TC004 - Should display user list correctly', async ({
    authenticatedPage,
    page
  }) => {
    try {
      // Verify user list is visible
      await expect(authenticatedPage.userList).toBeVisible();

      // Verify table headers are present
      const headers = page.locator('th');
      await expect(headers).toHaveCount(4, { timeout: 10000 });

      await ScreenshotHelper.captureScreenshot(
        page,
        'user_list_display_success',
        'success'
      );
    } catch (error) {
      await ScreenshotHelper.captureScreenshot(
        page,
        'user_list_display_failure',
        'failure'
      );
      throw error;
    }
  });

  test('TC005 - Should search users by name', async ({
    authenticatedPage,
    page
  }) => {
    try {
      const searchInput = page.locator("input[placeholder*='Search']");
      await searchInput.fill('John');
      await page.waitForTimeout(1000);

      const searchResults = page.locator('tr:has-text("John")');
      await expect(searchResults.first()).toBeVisible();

      await ScreenshotHelper.captureScreenshot(
        page,
        'user_search_success',
        'success'
      );
    } catch (error) {
      await ScreenshotHelper.captureScreenshot(
        page,
        'user_search_failure',
        'failure'
      );
      throw error;
    }
  });
});