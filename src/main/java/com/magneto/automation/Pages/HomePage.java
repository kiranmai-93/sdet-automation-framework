package com.magneto.automation.Pages;

import java.time.Duration;
import com.magneto.automation.utils.WaitUtils;
import com.magneto.automation.utils.ExcelUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	//private By gender_male = By.xpath("//input[@id='male']");
	//private By gender_female = By.xpath("//input[@id='female']");
    private By Alert = By.xpath("//button[contains(text(),'Simple Alert')]");
    private By confirmAlert = By.xpath("//button[contains(text(),'Confirm')]");
    private By promptAlert = By.xpath("//button[contains(text(),'Prompt')]");
	private By newTab= By.xpath("//button[contains(text(),'New Tab')]");
	 // 🔹 ========== CONSTRUCTOR ==========
	public HomePage() {
		this.driver = DriverFactory.getDriver();
	}
	// 🔹 ========== ACTION METHODS (what user does) ==========
	/**
     * Fills out the form with name, email, and phone.
     */
	public void fillForm(String name, String email, String phonenum,String Address) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement nameField=wait.until(ExpectedConditions.presenceOfElementLocated(Name));
        nameField.clear();
        nameField.sendKeys(name);
		 WebElement EmailField=	driver.findElement(Email);
         EmailField.clear();
         EmailField.sendKeys(email);
			WebElement phoneField=driver.findElement(phone);
            phoneField.clear();
            phoneField.sendKeys(phonenum);
			WebElement addressboxfield=driver.findElement(addressbox);
            addressboxfield.clear();
            addressboxfield.sendKeys(Address);
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
        WaitUtils.scrollToElement(driver,checkbox);
	    if (!checkbox.isSelected()) {
	        checkbox.click();
	    }
	}
    By countryDropdown =  By.xpath("//select[@id='country']");
    public void selectCountry(String CountryName){
        WebElement dropdownElement = driver.findElement(countryDropdown);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(CountryName);
    }
    private   By getRadioBygender(String gender) {
        return By.xpath(String.format("//input[@name='gender' and @value='%s']", gender.toLowerCase()));
         }
    public void selectGender(String gender){
        WebElement checkbox1 = driver.findElement(getRadioBygender(gender));
        WaitUtils.scrollToElement(driver,checkbox1);
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }
    }
    public boolean isDaySelected(String day) {
        WebElement checkbox = driver.findElement(getCheckboxByDay(day));
        return checkbox.isSelected();
    }
    public void selectGenderAndDay(String gender, String day) {
        selectGender(gender);
        selectDay(day);
    }
    public void handleAlert(){
        WebElement alert = driver.findElement(Alert);
        alert.click();
    }
    public void handleConfirmAlert(){
        WebElement confirmAlert1=driver.findElement(confirmAlert);
        WaitUtils.scrollToElement(driver,confirmAlert1);
        confirmAlert1.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void handlePromptAlert(){
        WebElement promptAlert3=driver.findElement(promptAlert);
        WaitUtils.scrollToElement(driver,promptAlert3);
        promptAlert3.click();
        Alert prompt = driver.switchTo().alert();
      prompt.sendKeys("Prompt Alert");
        prompt.accept();
    }
    public void openNewTab(){
        String mainWindow=driver.getWindowHandle();
        driver.findElement(newTab).click();
        String handle = driver.getWindowHandle();
        System.out.println(handle);
        driver.switchTo().window(handle);
        driver.switchTo().window(mainWindow);
        System.out.println("navigatedtomainwindow");
    }
    }



