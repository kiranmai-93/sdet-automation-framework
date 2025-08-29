package com.magneto.automation.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
public class ExtentReportManager {
    private static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ExtentReports getReportInstance() {

        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);

            /*extent.setSystemInfo("Tester", "Kiranmai");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");*/
        }
        return extent;
    }

    public static void createTest(String testName) {
        ExtentTest t = getReportInstance().createTest(testName);
        test.set(t);
    }

    public static ExtentTest getTest() {
         return test.get();
    }
    public static void unload() {
        test.remove();
    }
}
