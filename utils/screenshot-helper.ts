import { Page } from '@playwright/test';
import * as fs from 'fs';
import * as path from 'path';

export class ScreenshotHelper {
  private static screenshotDir = 'screenshots';

  static ensureScreenshotDirectory() {
    if (!fs.existsSync(this.screenshotDir)) {
      fs.mkdirSync(this.screenshotDir, { recursive: true });
    }
  }

  static getTimestamp(): string {
    return new Date().toISOString().replace(/[:.]/g, '-').slice(0, -5);
  }

  static async captureScreenshot(
    page: Page,
    testName: string,
    status: 'success' | 'failure'
  ): Promise<string> {
    this.ensureScreenshotDirectory();
    const timestamp = this.getTimestamp();
    const filename = `${testName}_${status}_${timestamp}.png`;
    const filepath = path.join(this.screenshotDir, filename);

    await page.screenshot({
      path: filepath,
      fullPage: true
    });

    return filepath;
  }

  static async captureFullPageScreenshot(
    page: Page,
    name: string
  ): Promise<string> {
    this.ensureScreenshotDirectory();
    const timestamp = this.getTimestamp();
    const filename = `${name}_${timestamp}.png`;
    const filepath = path.join(this.screenshotDir, filename);

    await page.screenshot({
      path: filepath,
      fullPage: true
    });

    return filepath;
  }
}