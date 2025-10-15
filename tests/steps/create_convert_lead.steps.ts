import { Given, When, Then } from '@cucumber/cucumber';
import { expect } from '@playwright/test';

// Playwright World is provided by @cucumber/cucumber with a custom world that exposes page/context
// If you don't have a custom world yet, you can wire one or use the default and import a singleton page.
// For simplicity, we will assume a global `page` is available via test runner setup (playwright-cucumber integration).

type LeadInput = {
  Salutation: string;
  'First Name': string;
  'Last Name': string;
  Company: string;
  Street: string;
  'Product Interest': string;
  Description: string;
  Phone: string;
};

type ConversionInput = {
  'Account Name': string;
  'Contact Search': string;
  'Opportunity Name': string;
};

const sfUrl = process.env.SF_URL as string;
const sfUsername = process.env.SF_USERNAME as string;
const sfPassword = process.env.SF_PASSWORD as string;

async function loginIfNeeded() {
  // Navigate directly to Leads list; Salesforce will redirect to login if needed
  await page.goto(sfUrl);
  if (await page.getByRole('textbox', { name: 'Username' }).isVisible({ timeout: 2000 }).catch(() => false)) {
    await page.getByRole('textbox', { name: 'Username' }).fill(sfUsername);
    await page.getByRole('textbox', { name: 'Password' }).fill(sfPassword);
    await page.getByRole('button', { name: 'Log In' }).click();
  }
  // Wait for Lightning shell
  await expect(page.getByRole('navigation', { name: 'Global Header' })).toBeVisible();
}

Given('I open the Salesforce app {string}', async (url: string) => {
  await page.goto(url);
});

Given('I log in with username {string} and password {string}', async (username: string, password: string) => {
  // If already logged in, Salesforce will land on Lightning and no login fields will be visible
  const usernameField = page.getByRole('textbox', { name: 'Username' });
  if (await usernameField.isVisible({ timeout: 3000 }).catch(() => false)) {
    await usernameField.fill(username);
    await page.getByRole('textbox', { name: 'Password' }).fill(password);
    await page.getByRole('button', { name: 'Log In' }).click();
  }
  await expect(page.getByRole('navigation', { name: 'Global Header' })).toBeVisible();
});

When('I create a Lead with the following details', async (dataTable) => {
  const row = dataTable.hashes<LeadInput>()[0];

  // Go to Leads
  await page.getByRole('link', { name: 'Leads' }).click();
  // New lead
  await page.getByRole('button', { name: 'New' }).click();

  // Fill core fields
  await page.getByRole('textbox', { name: 'Phone' }).fill(row.Phone);
  await page.getByRole('textbox', { name: 'First Name' }).fill(row['First Name']);
  await page.getByRole('textbox', { name: '*Last Name' }).fill(row['Last Name']);
  await page.getByRole('textbox', { name: '*Company' }).fill(row.Company);
  await page.getByRole('textbox', { name: 'Street' }).fill(row.Street);
  await page.getByRole('textbox', { name: 'Description' }).fill(row.Description);

  // Picklists
  await page.getByRole('combobox', { name: 'Salutation' }).click();
  await page.getByRole('option', { name: row.Salutation }).click();

  await page.getByRole('combobox', { name: 'Product Interest' }).click();
  await page.getByRole('option', { name: row['Product Interest'] }).click();

  // Save
  await page.getByRole('button', { name: 'Save', exact: true }).click();

  // Confirm lead page
  await expect(page.getByRole('heading', { name: new RegExp(`Lead .*${row['Last Name']}`) })).toBeVisible();
});

When('I convert the Lead with', async (dataTable) => {
  const row = dataTable.hashes<ConversionInput>()[0];

  await page.getByRole('button', { name: 'Convert' }).click();

  // Account section: update Account Name text box
  const accountName = page.getByRole('textbox', { name: 'Account Name *' });
  if (await accountName.isVisible({ timeout: 2000 }).catch(() => false)) {
    await accountName.fill(row['Account Name']);
  }

  // Contact section: choose existing and search/select contact
  // Toggle to existing if not selected
  const chooseExistingContact = page.getByRole('radio', { name: 'Choose Existing Contact' });
  if (await chooseExistingContact.isVisible({ timeout: 2000 }).catch(() => false)) {
    // click the label to avoid overlay
    await page.getByText('Choose Existing Contact').click();
  }
  const contactSearch = page.getByRole('combobox', { name: 'Contact Search' });
  await contactSearch.fill(row['Contact Search']);
  await contactSearch.press('Enter');

  // Select first matching option that contains the search text
  const option = page.getByRole('option', { name: new RegExp(row['Contact Search'], 'i') }).first();
  if (await option.isVisible({ timeout: 2000 }).catch(() => false)) {
    await option.click();
  }

  // Opportunity name
  const oppName = page.getByRole('textbox', { name: 'Opportunity Name *' });
  if (await oppName.isVisible({ timeout: 2000 }).catch(() => false)) {
    await oppName.fill(row['Opportunity Name']);
  } else {
    // Fallback for button-styled default name control
    const defaultOppBtn = page.getByRole('button', { name: /360Logica-/ });
    if (await defaultOppBtn.isVisible({ timeout: 2000 }).catch(() => false)) {
      await defaultOppBtn.click();
      await oppName.fill(row['Opportunity Name']);
    }
  }

  // Convert
  await page.getByRole('button', { name: 'Convert' }).click();
});

Then('I should see conversion success for', async (dataTable) => {
  const row = dataTable.hashes<{ 'Account Name': string; 'Contact Name': string; 'Opportunity Name': string; }>()[0];

  // Success dialog
  const successHeading = page.getByRole('heading', { name: 'Your lead has been converted' });
  await expect(successHeading).toBeVisible();

  // Verify names
  await expect(page.getByRole('link', { name: row['Account Name'] })).toBeVisible();
  await expect(page.getByRole('link', { name: row['Contact Name'] })).toBeVisible();
  await expect(page.getByRole('link', { name: row['Opportunity Name'] })).toBeVisible();
});



