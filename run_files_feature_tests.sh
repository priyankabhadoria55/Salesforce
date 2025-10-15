#!/bin/bash

echo "🚀 Files Feature Test Runner"
echo "============================"

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

# Run the Files feature tests
echo "🧪 Running Files feature tests..."
mvn test -Dtest=FilesTestRunner

if [ $? -eq 0 ]; then
    echo "✅ Files feature tests completed successfully"
    echo "📊 Reports generated in: test-output/cucumber-reports/"
    echo "   - HTML Report: files-test-report.html"
    echo "   - JSON Report: files-test.json"
else
    echo "❌ Files feature tests failed"
    exit 1
fi

echo "🎉 Files feature automation completed!"
