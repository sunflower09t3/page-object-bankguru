package com.bankguru.validation;

import org.testng.annotations.Test;

import commons.PageGeneratorManager;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Validation_02_EditCustomer {
	WebDriver driver;
	String validCustomerID, userName, password;
	HomePageObject homePage;
	LoginPageObject loginPage;
	EditCustomerPageObject editCustomerPage;
	String emptyCustomerID, customerIDWithCharacter, customerIDWithSpecialCharacter;
	String emptyAddress;
	String emptyCity, cityWithNumber, cityWithSpecialCharacter;
	String emptyState, stateWithSpecialCharacter, stateWithNumber;
	String pinWithCharacter, emptyPin, pinWithLessThan6Digits, pinWithMoreThan6Digits, pinWithSpecialCharacter;
	String emptyTelephone, telephoneWithSpecialCharacter;
	String emptyEmail, invalidEmail;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//homePage = new HomePageObject(driver);
		//loginPage = new LoginPageObject(driver);
		//editCustomerPage = new EditCustomerPageObject(driver);

		driver.get("http://demo.guru99.com/V4/");
		
		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Data
		userName = "mngr204279";
		password = "UgUdUhu";
		validCustomerID = "53666";
		emptyCustomerID = "";
		customerIDWithCharacter = "123acc";
		customerIDWithSpecialCharacter = "123$%^";
		emptyAddress = "";
		emptyCity = "";
		cityWithNumber = "city123";
		cityWithSpecialCharacter = "city@#%";
		emptyState = "";
		stateWithSpecialCharacter = "state#$%";
		stateWithNumber = "state123";
		pinWithCharacter = "123abc";
		emptyPin = "";
		pinWithLessThan6Digits = "123";
		pinWithMoreThan6Digits = "123456789";
		pinWithSpecialCharacter = "123%$@";
		emptyTelephone = "";
		telephoneWithSpecialCharacter = "123456789$%";
		invalidEmail = "tuongvi@";

		// Login
		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

		// Navigate to New customer page
		editCustomerPage = homePage.clickEditCustomerLink();
	}

	@Test
	public void TC_01_CustomerIDCannotBlank() {
		editCustomerPage.inputToCustomerIDTextbox(emptyCustomerID);
		editCustomerPage.pressTabKey();
		Assert.assertTrue(editCustomerPage.isCustomerIDMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_CustomerIDCannotContainCharacter() {
		editCustomerPage.inputToCustomerIDTextbox(customerIDWithCharacter);
		Assert.assertTrue(editCustomerPage.isCustomerIDMustNotContainCharacterDisplayed());
	}

	@Test
	public void TC_03_CustomerIDCannotContainSpecialCharacter() {
		editCustomerPage.inputToCustomerIDTextbox(customerIDWithSpecialCharacter);
		Assert.assertTrue(editCustomerPage.isCustomerIDMustNotContainSpecialCharacterMessgaeDisplayed());
	}

	@Test
	public void TC_04_ValidCustomerID() {
		editCustomerPage.inputToCustomerIDTextbox(validCustomerID);
		editCustomerPage.clickSubmitButton();
		Assert.assertTrue(editCustomerPage.isRedirectedToEditCustomerEntryPage());
	}

	@Test
	public void TC_08_AddressCannotBeBlank() {
		editCustomerPage.inpuToAddressTextarea(emptyAddress);
		editCustomerPage.pressTabKey();
		Assert.assertTrue(editCustomerPage.isAddressMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_09_CityCannotBeBlank() {
		editCustomerPage.inputToCityTextbox(emptyCity);
		editCustomerPage.pressTabKey();
		Assert.assertTrue(editCustomerPage.isCityMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_10_CityCannotContainNumber() {
		editCustomerPage.inputToCityTextbox(cityWithNumber);
		Assert.assertTrue(editCustomerPage.isCityMustNotContainNumberMessageDisplayed());
	}

	@Test
	public void TC_11_CityCannotContainSpecialCharacter() {
		editCustomerPage.inputToCityTextbox(cityWithSpecialCharacter);
		Assert.assertTrue(editCustomerPage.isCityMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_12_StateCannotBeBlank() {
		editCustomerPage.inputToStateTextbox(emptyState);
		editCustomerPage.pressTabKey();
		Assert.assertTrue(editCustomerPage.isStateMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_13_StateCannotContainNumber() {
		editCustomerPage.inputToStateTextbox(stateWithNumber);
		Assert.assertTrue(editCustomerPage.isStateMustNotContainNumberMessageDisplayed());
	}

	@Test
	public void TC_14_StateCannotContainSpecialCharacter() {
		editCustomerPage.inputToStateTextbox(stateWithSpecialCharacter);
		Assert.assertTrue(editCustomerPage.isStateMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_15_PinCannotContainCharacter() {
		editCustomerPage.inputToPinTextbox(pinWithCharacter);
		Assert.assertTrue(editCustomerPage.isPinMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_16_PinCannotBlank() {
		editCustomerPage.inputToPinTextbox(emptyPin);
		editCustomerPage.pressTabKey();
		Assert.assertTrue(editCustomerPage.isPinMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_17_PinCannotMoreThan6Digits() {
		editCustomerPage.inputToPinTextbox(pinWithMoreThan6Digits);
		Assert.assertEquals(pinWithMoreThan6Digits.substring(0, 6), editCustomerPage.getEnteredTextFromPinTextbox());
	}

	@Test
	public void TC_17_PinCannotLessThan6Digits() {
		editCustomerPage.inputToPinTextbox(pinWithLessThan6Digits);
		Assert.assertTrue(editCustomerPage.isPinMustBe6DigitsMessageDisplayed());
	}

	@Test
	public void TC_18_PinCannotHaveSpecialCharacter() {
		editCustomerPage.inputToPinTextbox(pinWithSpecialCharacter);
		Assert.assertTrue(editCustomerPage.isPinMustNotHaveSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_19_TelephoneCannotBlank() {
		editCustomerPage.inputToTelephoneTextbox(emptyTelephone);
		editCustomerPage.pressTabKey();
		Assert.assertTrue(editCustomerPage.isTelephoneMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_20_TelephoneCannotContainSpecialCharacter() {
		editCustomerPage.inputToTelephoneTextbox(telephoneWithSpecialCharacter);
		Assert.assertTrue(editCustomerPage.isTelephoneMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_21_EmailCannotBlank() {
		editCustomerPage.inputToEmailTextbox(emptyEmail);
		editCustomerPage.pressTabKey();
		Assert.assertTrue(editCustomerPage.isEmailMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_22_EmaitCannotBeInvalidFormat() {
		editCustomerPage.inputToEmailTextbox(invalidEmail);
		Assert.assertTrue(editCustomerPage.isEmailInvalidFormatMessageDisplayed());
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
