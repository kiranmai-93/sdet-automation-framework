package com.magneto.automation.listeners;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.magneto.automation.utils.ExtentReportManager;
import com.magneto.automation.base.BaseTest;
import com.magneto.automation.drivers.DriverFactory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;
import com.magneto.automation.utils.ExtentReportManager;
import com.magneto.automation.drivers.DriverFactory;

import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        ExtentTest node = ExtentReportManager.getTest();

        String testName  = result.getMethod().getMethodName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // <project>/test-output/screenshots
        String baseDir  = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "screenshots";
        String filePath = baseDir + File.separator + testName + "_" + timestamp + ".png";

        // ensure dir
        try { Files.createDirectories(Paths.get(baseDir)); } catch (IOException ignored) {}

        // take screenshot (only if driver is available)
        if (driver != null) {
            try {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(src.toPath(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("📸 Screenshot saved: " + filePath);
            } catch (Exception e) {
                System.out.println("⚠ Could not capture screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("⚠ WebDriver is null; skipping screenshot.");
        }

        // attach to Extent (if node exists)
        if (node != null) {
            try {
                File img = new File(filePath);
                if (img.exists()) {
                    node.fail("Test Failed: " + testName,
                            MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
                } else {
                    node.fail("Test Failed: " + testName + " (⚠ screenshot not available at: " + filePath + ")");
                }

                // also attach the throwable/stacktrace for easier debugging
                if (result.getThrowable() != null) {
                    node.fail(result.getThrowable());
                }
            } catch (Exception e) {
                node.fail("⚠ Error attaching screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("⚠ Extent node is null; did you create the test in @BeforeMethod?");
        }
    }
}
