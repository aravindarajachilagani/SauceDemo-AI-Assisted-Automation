
	package Listeners;

	import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Base.BaseClassSauceDemo;

	public class ScreenshotListener implements ITestListener {

		 private static ExtentReports extent;
		    private static ThreadLocal<ExtentTest> test =
		        new ThreadLocal<>();

		    // Fires when suite starts — sets up report
		    @Override
		    public void onStart(ITestContext context) {
		        String timestamp = new SimpleDateFormat(
		            "yyyy-MM-dd_HH-mm-ss").format(new Date());

		        String reportPath = System.getProperty("user.dir")
		            + File.separator + "reports"
		            + File.separator + "TestReport_"
		            + timestamp + ".html";

		        ExtentSparkReporter spark =
		            new ExtentSparkReporter(reportPath);
		        spark.config().setTheme(Theme.DARK);
		        spark.config().setDocumentTitle(
		            "SauceDemo Automation Report");
		        spark.config().setReportName(
		            "SauceDemo Test Execution Report");

		        extent = new ExtentReports();
		        extent.attachReporter(spark);
		        extent.setSystemInfo("Project", "SauceDemo");
		        extent.setSystemInfo("Tester", "Aravinda Raja");
		        extent.setSystemInfo("Environment", "QA");
		        extent.setSystemInfo("Browser", "Chrome");

		        System.out.println("ExtentReports initialized");
		    }

		    // Fires when test starts
		    @Override
		    public void onTestStart(ITestResult result) {
		        ExtentTest extentTest = extent.createTest(
		            result.getName(),
		            result.getMethod().getDescription());
		        test.set(extentTest);
		        System.out.println("Test Started: "
		            + result.getName());
		    }

		    // Fires when test passes
		    @Override
		    public void onTestSuccess(ITestResult result) {
		        test.get().log(Status.PASS,
		            "Test Passed: " + result.getName());
		        System.out.println("Test Passed: "
		            + result.getName());
		    }

		    // Fires when test fails
		    @Override
		    public void onTestFailure(ITestResult result) {
		        System.out.println("Test Failed: "
		            + result.getName());

		        // Log failure in report
		        test.get().log(Status.FAIL,
		            "Test Failed: " + result.getThrowable());

		        // Take screenshot
		        Object testClass = result.getInstance();
		        WebDriver driver =
		            ((BaseClassSauceDemo) testClass).driver;

		        if (driver != null) {
		            try {
		                String timestamp = new SimpleDateFormat(
		                    "yyyy-MM-dd_HH-mm-ss").format(new Date());

		                String fileName = result.getName()
		                    + "_" + timestamp + ".png";

		                File screenshot = ((TakesScreenshot) driver)
		                    .getScreenshotAs(OutputType.FILE);

		                String destination =
		                    System.getProperty("user.dir")
		                    + File.separator + "screenshots"
		                    + File.separator + fileName;

		                org.apache.commons.io.FileUtils.copyFile(
		                    screenshot, new File(destination));

		                // Attach screenshot to report
		                test.get().addScreenCaptureFromPath(
		                    destination,
		                    "Screenshot on Failure");

		                System.out.println("Screenshot saved: "
		                    + destination);

		            } catch (Exception e) {
		                System.out.println(
		                    "Failed to save screenshot: "
		                    + e.getMessage());
		            }
		        }
		    }

		    // Fires when test is skipped
		    @Override
		    public void onTestSkipped(ITestResult result) {
		        test.get().log(Status.SKIP,
		            "Test Skipped: " + result.getName());
		        System.out.println("Test Skipped: "
		            + result.getName());
		    }

		    // Fires when suite finishes — flushes report to file
		    @Override
		    public void onFinish(ITestContext context) {
		        extent.flush();
		        System.out.println("ExtentReports saved to reports folder");
		    }
		}
