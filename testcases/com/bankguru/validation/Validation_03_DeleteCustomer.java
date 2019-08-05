package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_03_DeleteCustomer extends AbstractTest{
	WebDriver  driver;
	String userName, password;
	String customerIDContainingCharacter, customerIDContainingSpecialCharacter, customerIDContainingSpace, customerIDBeginWithSpace;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	DeleteCustomerPageObject deleteCustomerPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);
		
		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";
		
		// Data
		customerIDContainingCharacter = "123abc";
		customerIDContainingSpecialCharacter = "123$%^";
		customerIDContainingSpace = "123 45";
		customerIDBeginWithSpace = " 123";
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
		
		homePage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
	}

	@Test
	public void TC_01_CustomerIDMustNotBeBlank() {
		deleteCustomerPage.inputNothingToCustomerIDTextboxAndPressTabKey();
		Assert.assertTrue(deleteCustomerPage.isCustomerIDMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_02_CustomerIDMustNotContainCharacter() {
		deleteCustomerPage.inputToCustomerIDTextbox(customerIDContainingCharacter);
		Assert.assertTrue(deleteCustomerPage.isCustomerIDMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_03_CustomerIDMustNotContainSpecialCharacter() {
		deleteCustomerPage.inputToCustomerIDTextbox(customerIDContainingSpecialCharacter);
		Assert.assertTrue(deleteCustomerPage.isCustomerIDMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_04_CustomerIDMustNotContainSpace() {
		deleteCustomerPage.inputToCustomerIDTextbox(customerIDContainingSpace);
		Assert.assertTrue(deleteCustomerPage.isCustomerIDMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_05_CustomerIDMustNotBeginWithSpace() {
		deleteCustomerPage.inputToCustomerIDTextbox(customerIDBeginWithSpace);
		Assert.assertTrue(deleteCustomerPage.isCustomerIDMustNotBeginWithSpaceMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
