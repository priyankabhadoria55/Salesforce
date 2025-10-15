#!/bin/bash

echo "🚀 Salesforce Login Automation Test Runner"
echo "=========================================="

# Check if Maven is available
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed or not in PATH"
    exit 1
fi

echo "✅ Maven found"

# Clean and compile the project
echo "🧹 Cleaning and compiling project..."
mvn clean compile test-compile

if [ $? -eq 0 ]; then
    echo "✅ Project compiled successfully"
else
    echo "❌ Project compilation failed"
    exit 1
fi

# Run the Salesforce login tests
echo "🧪 Running Salesforce login tests..."
mvn test -Dtest=SalesforceLoginTestRunner

if [ $? -eq 0 ]; then
    echo "✅ Tests completed successfully"
    echo "📊 Reports generated in: test-output/cucumber-reports/"
else
    echo "❌ Tests failed"
    exit 1
fi

echo "🎉 Salesforce login automation completed!"
