package com.magneto.automation.tests;

import com.magneto.automation.Pages.HomePage;
import org.testng.Assert;

public class HomeFlows {
    private final HomePage home =  new HomePage();
    public HomeFlows openHome(){
        System.out.println("Navigating to the home");
        return this;
    }
    public HomeFlows fillContact(String name, String email, String phonenum, String address){
        System.out.println("Filling contact details");
        home.fillForm(name,email,phonenum,address);
        return this;
    }
    public HomeFlows selectGenderAndDay(String gender, String day){
        System.out.println("Selecting gender and day");
        home.selectGender(gender);
        home.selectDay(day);
        return this;
    }
    public HomeFlows selectCountry(String country){
        System.out.println("Selecting country");
        home.selectCountry(country);
        return this;
    }
    public HomeFlows verifyTitle(String expected){
        String actual = home.titleis();
        Assert.assertEquals(actual,expected,"title mismatch");
        return this;
    }
    public HomeFlows handleSimpleAlert(){
        home.handleAlert();
        return this;
    }
    public HomeFlows handleConfirmAlert(){
        home.handleConfirmAlert();
        return this;
    }
    public HomeFlows handlePromptAlert(){
        home.handlePromptAlert();
        return this;
    }
    public HomeFlows openTab(){
        home.openNewTab();
        return this;
    }
}
