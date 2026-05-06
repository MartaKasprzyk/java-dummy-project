# Playwright Test Automation Framework

## Overview
This is a Playwright-based test automation framework for user management functionality, converted from a Java/JUnit project to TypeScript/Playwright.

## Prerequisites
- Node.js (v18 or higher)
- npm or yarn

## Installation

```bash
# Install dependencies
npm install

# Install Playwright browsers
npx playwright install
```

## Configuration

1. Copy `.env.example` to `.env`:
```bash
cp .env.example .env
```

2. Update environment variables in `.env` file with your test environment details.

## Project Structure

```
├── pages/                    # Page Object Model classes
│   ├── LoginPage.ts
│   └── UserManagementPage.ts
├── fixtures/                 # Test fixtures for dependency injection
│   └── test-fixtures.ts
├── utils/                    # Utility functions
│   ├── test-data-generator.ts
│   └── screenshot-helper.ts
├── tests/                    # Test specifications
│   └── user-management/
│       ├── create-user.spec.ts
│       └── user-list.spec.ts
├── screenshots/              # Auto-generated screenshots
├── test-results/            # Test execution results
├── playwright.config.ts     # Playwright configuration
└── package.json
```

## Running Tests

```bash
# Run all tests
npm test

# Run tests in headed mode
npm run test:headed

# Run tests in debug mode
npm run test:debug

# Run tests with UI mode
npm run test:ui

# Run specific test file
npx playwright test tests/user-management/create-user.spec.ts

# Run tests on specific browser
npx playwright test --project=chromium
```

## View Test Reports

```bash
# Show HTML report
npm run report
```

## Features

### Page Object Model
- Organized page classes with locators and methods
- Reusable page interactions
- Clear separation of test logic and page structure

### Test Fixtures
- Pre-authenticated user sessions
- Automatic page object initialization
- Shared test setup and teardown

### Screenshot Capture
- Automatic screenshots on test failure
- Manual screenshot capture for success scenarios
- Timestamped filenames to prevent overwrites
- Full-page screenshots for comprehensive coverage

### Test Data Generation
- Dynamic user data generation
- Unique email addresses
- Random phone numbers
- Reusable test data utilities

### Assertions
- Playwright's built-in expect assertions
- Auto-wait and retry mechanisms
- Clear error messages

## Test Cases

### User Management
- **TC001**: Create new user with valid details
- **TC002**: Create user with dynamically generated data
- **TC003**: Validate required field validation
- **TC004**: Display user list correctly
- **TC005**: Search users by name

## CI/CD Integration

The framework is CI/CD ready with:
- Automatic retry on failure
- Multiple report formats (HTML, JSON, JUnit)
- Configurable parallelization
- Environment-based configuration

## Best Practices

1. **Use data-testid attributes** for stable selectors
2. **Implement Page Object Model** for maintainability
3. **Capture screenshots** on both success and failure
4. **Use fixtures** for common setup
5. **Generate dynamic test data** to avoid conflicts
6. **Follow async/await patterns** consistently
7. **Keep tests independent** and idempotent

## Troubleshooting

### Tests failing to start
- Ensure Playwright browsers are installed: `npx playwright install`
- Check environment variables in `.env` file

### Screenshots not capturing
- Verify `screenshots/` directory exists
- Check file permissions

### Authentication issues
- Verify credentials in `.env` file
- Check base URL is correct

## Contributing

When adding new tests:
1. Create page objects for new pages
2. Add reusable utilities to `utils/` directory
3. Follow existing naming conventions
4. Include screenshot capture in test hooks
5. Update this README with new test cases

## License
MIT