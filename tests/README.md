Setup to run Playwright + Cucumber BDD

1) Install deps

   npm install --save-dev @playwright/test @cucumber/cucumber ts-node typescript

2) Export Salesforce env vars (or use a .env loader)

   - SF_URL: https://unite3-dev-ed.develop.lightning.force.com/lightning/o/Lead/list?filterName=AllOpenLeads
   - SF_USERNAME: adminashwani@unite3.com
   - SF_PASSWORD: Admin@234568

3) Suggested cucumber config (cucumber.js)

   {
     "default": "--require-module ts-node/register --require tests/steps/**/*.ts --publish-quiet"
   }

4) Run

   npx cucumber-js tests/features/create_convert_lead.feature

Notes

- The steps assume Playwright exposes a global `page`. If you don't have that wiring yet, integrate a custom World that attaches Playwright's `page` to the Cucumber world, or use a runner like `playwright-bdd`.



