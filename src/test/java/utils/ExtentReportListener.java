package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath = "test-output/HtmlReport/ExtentHtml.html";

    @Override
    public void onStart(ITestContext context) {
        System.out.println("📊 Initializing Extent Report...");
        initializeReport();
    }

    private void initializeReport() {
        // Create report directory if not exists
        File reportDir = new File("test-output/HtmlReport");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("UNITE - XfilesPro Automation Report");
        sparkReporter.config().setReportName("XfilesPro Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("UTF-8");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        // Enable offline mode
        sparkReporter.config().setOfflineMode(false);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System Information
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Project", "XfilesPro - Salesforce Integration");
        extent.setSystemInfo("Build", "1.0.0");
        extent.setSystemInfo("Test Type", "BDD - Cucumber");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);

        System.out.println("▶️ Starting Test: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ Test Passed: " + result.getMethod().getMethodName());
        System.out.println("✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ Test Failed: " + result.getMethod().getMethodName());
        test.get().log(Status.FAIL, "Failure Reason: " + result.getThrowable());

        // Capture screenshot on failure
        try {
            String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
            if (screenshotPath != null) {
                test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            }
        } catch (Exception e) {
            test.get().log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
        }

        System.out.println("❌ Test Failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⏭️ Test Skipped: " + result.getMethod().getMethodName());
        System.out.println("⏭️ Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            System.out.println("📊 Extent Report Generated: " + reportPath);
            System.out.println("🌐 Open report: file:///" + new File(reportPath).getAbsolutePath());
        }
    }

    private String captureScreenshot(String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotDir = "test-output/screenshots/";
            Files.createDirectories(Paths.get(screenshotDir));

            String screenshotPath = screenshotDir + testName + "_" + timestamp + ".png";
            // Add your screenshot capture logic here using Selenium
            // Example: File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // FileUtils.copyFile(screenshot, new File(screenshotPath));

            return screenshotPath;
        } catch (Exception e) {
            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }
}