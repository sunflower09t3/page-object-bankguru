package com.bankguru.validation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_02_EditCustomer extends AbstractTest{
	WebDriver driver;
	String validCustomerID, userName, password;
	HomePageObject homePage;
	LoginPageObject loginPage;
	EditCustomerPageObject editCustomerPage;
	String customerIDWithCharacter, customerIDWithSpecialCharacter;
	String cityWithNumber, cityWithSpecialCharacter;
	String stateWithSpecialCharacter, stateWithNumber;
	String pinWithCharacter, emptyPin, pinWithLessThan6Digits, pinWithMoreThan6Digits, pinWithSpecialCharacter;
	String telephoneWithSpecialCharacter;
	String invalidEmail;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Data
		userName = "mngr213678";
		password = "Anajabu";
		validCustomerID = "76519";
		customerIDWithCharacter = "123acc";
		customerIDWithSpecialCharacter = "123$%^";
		cityWithNumber = "city123";
		cityWithSpecialCharacter = "city@#%";
		stateWithSpecialCharacter = "state#$%";
		stateWithNumber = "state123";
		pinWithCharacter = "123abc";
		emptyPin = "";
		pinWithLessThan6Digits = "123";
		pinWithMoreThan6Digits = "123456789";
		pinWithSpecialCharacter = "123%$@";
		telephoneWithSpecialCharacter = "123456789$%";
		invalidEmail = "tuongvi@";

		// Login
		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

		// Navigate to New customer page
		homePage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
	}

	@Test  
	public void TC_01_CustomerIDCannotBeBlank() {
		editCustomerPage.inputNothingToCustomerIDTextboxAndPressTabKey();
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
		editCustomerPage.clickAccSubmitButton();
		Assert.assertTrue(editCustomerPage.isRedirectedToEditCustomerEntryPage());
	}

	@Test  
	public void TC_08_AddressCannotBeBlank() {
		editCustomerPage.inputNothingToAddressTextareaAndPressTabKey();
		Assert.assertTrue(editCustomerPage.isAddressMustNotBlankMessageDisplayed());
	}

	@Test  
	public void TC_09_CityCannotBeBlank() {
		editCustomerPage.inputNothingToCityTextboxAndPressTabKey();
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
		editCustomerPage.inputNothingToStateTextboxAndPressTabKey();
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
	public void TC_16_PinCannotBeBlank() {
		editCustomerPage.inputNothingToPinTextboxAndPressTabKey();
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
	public void TC_19_TelephoneCannotBeBlank() {
		editCustomerPage.inputNothingToTelephoneTextboxAndPressTabKey();
		Assert.assertTrue(editCustomerPage.isTelephoneMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_20_TelephoneCannotContainSpecialCharacter() {
		editCustomerPage.inputToTelephoneTextbox(telephoneWithSpecialCharacter);
		Assert.assertTrue(editCustomerPage.isTelephoneMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test  
	public void TC_21_EmailCannotBeBlank() {
		editCustomerPage.inputNothingToEmailTextboxAndPressTabKey();
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
