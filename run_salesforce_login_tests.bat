@echo off
echo ========================================
echo Salesforce Login Automation Test Suite
echo ========================================
echo.
echo Starting Salesforce Login BDD Tests...
echo.

REM Set Maven options
set MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=256m

REM Run the tests
mvn clean test -Dsurefire.suiteXmlFiles=src/test/testNGSuites/SalesforceLoginTests.xml

echo.
echo ========================================
echo Test Execution Completed
echo ========================================
echo.
echo Check the following locations for reports:
echo - HTML Report: test-output/cucumber-reports/salesforce-login-reports.html
echo - JSON Report: test-output/cucumber-reports/salesforce-login.json
echo - Extent Report: Reports/
echo.
pause