package com.magneto.automation.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.magneto.automation.base.BaseTest; // ✅ For BaseTest
import com.magneto.automation.utils.ExcelUtils;
import com.magneto.automation.Pages.HomePage; // ✅ For HomePage
import com.test.listeners.RetryAnalyzer;

public class HomeTest extends BaseTest {

	@Test(dataProvider = "formData", retryAnalyzer = com.test.listeners.RetryAnalyzer.class)

	public void fillForm(String name, String email, String phonenum, String address) {
		System.out.println("Navigating to Home Page");
		HomePage home = new HomePage();
		home.fillForm(name, email, phonenum, address);
		System.out.println("Form is accessed");
		Assert.fail("💣 Intentional failure for screenshot test");
	}

	@Test(retryAnalyzer = com.test.listeners.RetryAnalyzer.class)
	public void title() {
		HomePage homepage = new HomePage();
		String text = homepage.titleis();
		Assert.assertEquals(text, "Automation Testing Practice", "title mismatch");
	}

	@DataProvider(name = "formData")
	public Object[][] getFormData() {
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx";
		return ExcelUtils.readExcelData(path, "Sheet1");
	}

}