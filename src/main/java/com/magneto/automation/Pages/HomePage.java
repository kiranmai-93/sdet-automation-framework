package com.magneto.automation.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magneto.automation.drivers.DriverFactory;


public class HomePage {
	private WebDriver driver;
	// 🔹 ========== STATIC ELEMENT LOCATORS ==========
	private By Name = By.xpath("//input[contains(@placeholder,'Enter Name')]");
	private By Email = By.xpath("//input[contains(@placeholder,'Enter EMail') or contains(text(),'Enter EMail')]");
	private By phone = By.xpath("//input[contains(@placeholder,'Enter Phone')]");
	private By addressbox = By.id("textarea");
	private By title = By.xpath("//h1[@class='title']");
	private By	description = By.xpath("//span[text()='For Selenium, Cypress & Playwright']");
	private By	links = By.xpath("ul//a");
	private By gender_male = By.xpath("//input[@id='male']");
	private By gender_female = By.xpath("//input[@id='female']");
	
	 // 🔹 ========== CONSTRUCTOR ==========
	public static HomePage() {
		this.driver = DriverFactory.getDriver();
	}
	// 🔹 ========== ACTION METHODS (what user does) ==========
	/**
     * Fills out the form with name, email, and phone.
     */
	public void fillForm(String name, String email, String phonenum,String Address) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(Name)).sendKeys(name);
			driver.findElement(Email).sendKeys(email);
			driver.findElement(phone).sendKeys(phonenum);
			driver.findElement(addressbox).sendKeys(Address);
	}
	public String titleis() {
		WebElement titleElement=driver.findElement(title);
		return titleElement.getText();
		}
	/**
     * Selects a checkbox for the given day (e.g., "Monday").
     */
	private By getCheckboxByDay(String day) {
	    return By.xpath(String.format("//input[@id='%s']", day.toLowerCase()));
	}
	 // 🔹 ========== HELPER METHODS (for dynamic locators) ==========

    /**
     * Returns a dynamic By locator for the checkbox based on the day input.
     * Example: if day = "monday", it returns By.xpath("//input[@id='monday']")
     */
	public void selectDay(String day) {
	    WebElement checkbox = driver.findElement(getCheckboxByDay(day));
	    if (!checkbox.isSelected()) {
	        checkbox.click();
	    }
	}

	}

