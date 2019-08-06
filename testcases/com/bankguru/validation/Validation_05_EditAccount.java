package com.bankguru.validation;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.EditAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_05_EditAccount extends AbstractTest {
	WebDriver driver;
	String userName, password;
	String newCustomerID, newCustomerName, newCustomerDateOfBirth, newCustomerAddress, newCustomerState;
	String newCustomerCity, newCustomerPIN, newCustomerTelephone, newCustomerEmail, newCustomerPassword;
	String newAccountID, newAccountAccType;
	String accountNoContainingCharacter, accountNoContainingSpecialCharacter;
	String accountNoContainingSpace, accountNoBeginWithSpace;
	int newAccountInitialDeposit;

	LoginPageObject loginPage;
	HomePageObject homePage;
	EditAccountPageObject editAccountPage;
	NewCustomerPageObject newCustomerPage;
	NewAccountPageObject newAccountPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";

		// Data
		accountNoContainingCharacter = "123Acc";
		accountNoContainingSpecialCharacter = "123$%^";
		accountNoContainingSpace = "123 456";
		accountNoBeginWithSpace = " ";

		// New customer data
		newCustomerName = "first customer";
		newCustomerDateOfBirth = "01/01/1989";
		newCustomerAddress = "PO Box 911 8331 Duis Avenue";
		newCustomerState = "FL";
		newCustomerCity = "Tampa";
		newCustomerPIN = "466250";
		newCustomerTelephone = "4555442476";
		newCustomerEmail = "firstcustomer" + randomNumber() + "@gmail.com";
		newCustomerPassword = "firstcustomerpassword";

		// New account data
		newAccountAccType = "Savings";
		newAccountInitialDeposit = 50000;

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		editAccountPage.inputNothingToAccountNoTextboxAndPressTabKey();
		Assert.assertTrue(editAccountPage.isAccountNoMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		editAccountPage.inputToAccountNoTextbox(accountNoContainingCharacter);
		Assert.assertTrue(editAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		editAccountPage.inputToAccountNoTextbox(accountNoContainingSpecialCharacter);
		Assert.assertTrue(editAccountPage.isAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		editAccountPage.inputToAccountNoTextbox(accountNoContainingSpace);
		Assert.assertTrue(editAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		editAccountPage.inputToAccountNoTextbox(accountNoBeginWithSpace);
		Assert.assertTrue(editAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_06_validAccountNo() {
		editAccountPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		
		newCustomerPage.inputToCustomerNameTextbox(newCustomerName);
		newCustomerPage.selectFemaleGenderRadioButton();
		newCustomerPage.inputToDateOfBirthTextbox(newCustomerDateOfBirth);
		newCustomerPage.inputToAddressTextarea(newCustomerAddress);
		newCustomerPage.inputToCityTextbox(newCustomerCity);
		newCustomerPage.inputToStateTextbox(newCustomerState);
		newCustomerPage.inputToPinTextbox(newCustomerPIN);
		newCustomerPage.inputToTelephoneTextbox(newCustomerTelephone);
		newCustomerPage.inputToEmailTextbox(newCustomerEmail);
		newCustomerPage.inputToPasswordTextbox(newCustomerPassword);
		newCustomerPage.clickSubmitButton();
		
		newCustomerID = newCustomerPage.getCustomerID();
		
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		newAccountPage.inputToCustomerIDTextbox(newCustomerID);
		newAccountPage.selectAccountType(newAccountAccType);
		newAccountPage.inputToInitialDepositTextbox(String.valueOf(newAccountInitialDeposit));
		newAccountPage.clickSubmitButton();
		
		newAccountID = newAccountPage.getAccountID();
		
		newAccountPage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		
		editAccountPage.inputToAccountNoTextbox(newAccountID);
		editAccountPage.clickSubmitButton();

		Assert.assertTrue(editAccountPage.isEditAccountEntryFormDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000000);
	}

}
