package com.magneto.automation.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.lang.reflect.Method;
import com.magneto.automation.drivers.DriverFactory;
import com.magneto.automation.utils.ConfigReader;
import com.magneto.automation.utils.ExtentReportManager;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.ITestResult;
import org.testng.annotations.*;
public class BaseTest {
	public ExtentReports extent;
    public ExtentTest logger;
    
    @BeforeClass
    
    public void setupClass() throws InterruptedException {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

       DriverFactory.initDriver(browser, headless); // ✅ Initializing the driver properly

        String url = ConfigReader.getProperty("base.url");
        DriverFactory.getDriver().get(url); // ✅ Now safe to use driver
        Thread.sleep(2000);
        DriverFactory.getDriver().manage().window().setSize(new Dimension(1200, 800));
        extent = ExtentReportManager.getReportInstance();
    }
    @BeforeMethod
    public void setupTest(Method method) {
        // Create Extent logger for the test
        logger = extent.createTest(method.getName());
    }
    @AfterMethod
    public void tearDownTest(ITestResult result) {
        // Log test result
        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.pass("✅ Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.fail("❌ Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.skip("⚠️ Test Skipped: " + result.getThrowable());
        }
    }
    
    @AfterSuite
    public void teardown() {	
            DriverFactory.quitDriver();
            if (extent != null) {
                extent.flush();
        }
    }
}