package com.test.listeners;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.magneto.automation.base.BaseTest;
import com.magneto.automation.drivers.DriverFactory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = DriverFactory.getDriver();       if (driver != null) {
            String testName = result.getName();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filePath = "screenshots/" + testName + "_" + timestamp + ".png";

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.createDirectories(Paths.get("screenshots"));
                Files.copy(src.toPath(), Paths.get(filePath));
             
                System.out.println("✅ Screenshot saved: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
