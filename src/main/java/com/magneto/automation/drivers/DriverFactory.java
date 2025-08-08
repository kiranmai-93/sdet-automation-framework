package com.magneto.automation.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public static void initDriver(String browser, boolean headless) {
	    if (browser.equalsIgnoreCase("chrome")) {
	        WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        if (headless) {
	            options.addArguments("--headless=new", "--window-size=1200,800");
	        }
	        tlDriver.set(new ChromeDriver(options));

	    } else if (browser.equalsIgnoreCase("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        tlDriver.set(new FirefoxDriver());

	    } else {
	        throw new RuntimeException("Unsupported browser: " + browser);
	    }

	    getDriver().manage().window().maximize(); // ✅ Now this will work!
	}


    public static WebDriver getDriver() {
    	return tlDriver.get();
    }
    public static void quitDriver() {
            getDriver().quit();
            tlDriver.remove();
        }
    }