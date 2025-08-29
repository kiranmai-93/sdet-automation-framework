package com.magneto.automation.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.magneto.automation.base.BaseTest; // ✅ For BaseTest
import com.magneto.automation.utils.ExcelUtils;
import com.magneto.automation.Pages.HomePage; // ✅ For HomePage
import com.test.listeners.RetryAnalyzer;

public class HomeTest extends BaseTest {

	@Test(dataProvider = "formData",retryAnalyzer = RetryAnalyzer.class,priority  =0, groups ={"smoke","regression"})

    public void fillForm(String name, String email, String phonenum, String address,String gender, String day) {
		new HomeFlows()
                .openHome()
                .fillContact(name, email, phonenum, address)
                .selectGenderAndDay(gender, day);
	}
    @Test(groups = {"smoke","regression"})
    public void testSelectCountry() {
        new HomeFlows()
                .openHome()
                .selectCountry("INDIA");
    }
    @Test
    public void testAlert(){
        new HomeFlows().handleSimpleAlert();
    }
	@Test(retryAnalyzer = com.test.listeners.RetryAnalyzer.class,priority = -1,groups = "smoke")
	public void title() {
		new HomeFlows()
                .openHome()
                .verifyTitle("Automation Testing Practice");
	}
    @Test
    public void testConfirmAlert(){
        new HomeFlows()
                .handleConfirmAlert();
    }
    @Test(groups = {"smoke","regression"})
    public void testPromptAlert(){
        new HomeFlows()
                .handlePromptAlert();
    }
    @Test(groups = {"smoke","regression"})
    public void opentheTab(){
        new HomeFlows()
                .openTab();
    }

	@DataProvider(name = "formData")
	public Object[][] getFormData() {
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx";
		return ExcelUtils.readExcelData(path, "Sheet1");
	}

}