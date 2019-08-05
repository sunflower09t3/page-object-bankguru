package com.bankguru.validation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;

public class Validation_04_NewAccount extends AbstractTest{
	WebDriver driver;
	String userName, password;
	String customerIDContainingCharacter, customerIDContaingSpecialCharacter; 
	String customerIDContainingSpace, customerIDBeginWithSpace;
	String initialDepositContainingCharacter, initialDepositContainingSpecialCharacter, initialDepositContainingSpace;
	String initialDepositBeginWithSpace;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";

		// Data
		customerIDContainingCharacter = "123abc";
		customerIDContaingSpecialCharacter = "123$%^";
		customerIDContainingSpace = "123 45";
		customerIDBeginWithSpace = " 123";
		initialDepositContainingCharacter = "10000Acc";
		initialDepositContainingSpecialCharacter = "10000#$%";
		initialDepositContainingSpace = "100 00";
		initialDepositBeginWithSpace = " 10000";

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
		
		homePage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
	}

	@Test
	public void TC_01_CustomerIDMustNotBeBlank() {
		newAccountPage.inputNothingToCustomerIDTextboxAndPressTabKey();
		Assert.assertTrue(newAccountPage.isCustomerIDMustNoBlankMessageDisplayed());
	}
	
	@Test
	public void TC_02_CustomerIDMustNotContainCharacter() {
		newAccountPage.inputToCustomerIDTextbox(customerIDContainingCharacter);
		Assert.assertTrue(newAccountPage.isCustomerIDMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_03_CustomerIDMustNotContainSpecialCharacter() {
		newAccountPage.inputToCustomerIDTextbox(customerIDContaingSpecialCharacter);
		Assert.assertTrue(newAccountPage.isCustomerIDMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_04_CustomerIDMustNotContainSpace() {
		newAccountPage.inputToCustomerIDTextbox(customerIDContainingSpace);
		Assert.assertTrue(newAccountPage.isCustomerIDMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_05_CustomerIDMustNotBeginWithSpace() {
		newAccountPage.inputToCustomerIDTextbox(customerIDBeginWithSpace);
		Assert.assertTrue(newAccountPage.isCustomerIDMustNotBeginWithSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_06_InitialDepositMustNotBeBlank() {
		newAccountPage.inputNothingToInitialDepositTextboxAndPressTabKey();
		Assert.assertTrue(newAccountPage.isInitialDepositMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_07_InitialDepositMustNotContainCharacter() {
		newAccountPage.inputToInitialDepositTextbox(initialDepositContainingCharacter);
		Assert.assertTrue(newAccountPage.isInitialDepositMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_08_InitialDepositMustNotContainSpecialCharacter() {
		newAccountPage.inputToInitialDepositTextbox(initialDepositContainingSpecialCharacter);
		Assert.assertTrue(newAccountPage.isInitialDepositMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_09_InitialDepositMustNotContainSpace() {
		newAccountPage.inputToInitialDepositTextbox(initialDepositContainingSpace);
		Assert.assertTrue(newAccountPage.isInitialDepositMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_10_InitialDepositMustNotBeginWithSpace() {
		newAccountPage.inputToInitialDepositTextbox(initialDepositBeginWithSpace);
		Assert.assertTrue(newAccountPage.isInitialDepositMustNotBeginWithSpaceMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
