#!/bin/bash

echo "=========================================="
echo "Salesforce Automation Test Execution"
echo "=========================================="

# Function to display menu
show_menu() {
    echo ""
    echo "Select test execution option:"
    echo "1) Run all Cucumber tests"
    echo "2) Run login tests only (@Login tag)"
    echo "3) Run smoke tests (@SmokeTest tag)"
    echo "4) Run standalone Java test"
    echo "5) Run with Firefox browser"
    echo "6) Run in headless mode"
    echo "7) Clean and compile project"
    echo "8) Exit"
    echo ""
    echo -n "Enter your choice [1-8]: "
}

# Function to run tests
run_tests() {
    case $1 in
        1)
            echo "Running all Cucumber tests..."
            mvn clean test
            ;;
        2)
            echo "Running login tests only..."
            mvn clean test -Dcucumber.filter.tags="@Login"
            ;;
        3)
            echo "Running smoke tests..."
            mvn clean test -Dcucumber.filter.tags="@SmokeTest"
            ;;
        4)
            echo "Running standalone Java test..."
            mvn clean compile
            mvn exec:java -Dexec.mainClass="tests.SalesforceLoginTest"
            ;;
        5)
            echo "Running tests with Firefox browser..."
            mvn clean test -Dbrowser=firefox
            ;;
        6)
            echo "Running tests in headless mode..."
            mvn clean test -Dheadless=true
            ;;
        7)
            echo "Cleaning and compiling project..."
            mvn clean compile
            echo "Project compiled successfully!"
            ;;
        8)
            echo "Exiting..."
            exit 0
            ;;
        *)
            echo "Invalid option. Please try again."
            ;;
    esac
}

# Main execution loop
while true; do
    show_menu
    read choice
    run_tests $choice
    
    echo ""
    echo "Press Enter to continue..."
    read
done