@echo off
echo ==========================================
echo Salesforce Automation Test Execution
echo ==========================================
echo.

:menu
echo Select test execution option:
echo 1) Run all Cucumber tests
echo 2) Run login tests only (@Login tag)
echo 3) Run smoke tests (@SmokeTest tag)
echo 4) Run standalone Java test
echo 5) Run with Firefox browser
echo 6) Run in headless mode
echo 7) Clean and compile project
echo 8) Exit
echo.
set /p choice="Enter your choice [1-8]: "

if "%choice%"=="1" goto all_tests
if "%choice%"=="2" goto login_tests
if "%choice%"=="3" goto smoke_tests
if "%choice%"=="4" goto standalone_test
if "%choice%"=="5" goto firefox_tests
if "%choice%"=="6" goto headless_tests
if "%choice%"=="7" goto clean_compile
if "%choice%"=="8" goto end

echo Invalid choice. Please try again.
pause
goto menu

:all_tests
echo Running all Cucumber tests...
call mvn clean test
pause
goto menu

:login_tests
echo Running login tests only...
call mvn clean test -Dcucumber.filter.tags="@Login"
pause
goto menu

:smoke_tests
echo Running smoke tests...
call mvn clean test -Dcucumber.filter.tags="@SmokeTest"
pause
goto menu

:standalone_test
echo Running standalone Java test...
call mvn clean compile
call mvn exec:java -Dexec.mainClass="tests.SalesforceLoginTest"
pause
goto menu

:firefox_tests
echo Running tests with Firefox browser...
call mvn clean test -Dbrowser=firefox
pause
goto menu

:headless_tests
echo Running tests in headless mode...
call mvn clean test -Dheadless=true
pause
goto menu

:clean_compile
echo Cleaning and compiling project...
call mvn clean compile
echo Project compiled successfully!
pause
goto menu

:end
echo Exiting...
exit /b 0