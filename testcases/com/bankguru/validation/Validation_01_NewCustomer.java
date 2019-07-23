package com.bankguru.validation;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_01_NewCustomer {
	WebDriver driver;
	LoginPageObject loginPage;
	NewCustomerPageObject newCustomerPage;
	HomePageObject homePage;
	String userName, password;
	String emptyCustomerName, customerNameWithNumber, customerNameWithSpecialCharacter, customerNameBeginWithSpace;
	String emptyAddress, addressBeginWithSpace;
	String emptyCity, cityWithNumber, cityWithSpecialCharacter, cityBeginWithSpace;
	String emptyState, stateWithNumber, stateWithSpecialCharacter, stateBeginWithSpace;
	String emptyPin, pinWithCharacter, pinWithMoreThan6Digits, pinWithLessThan6Digits, pintWithSpecialCharacter, pinBeginWithSpace, pintWithSpace;
	String emptyTelephone, telephoneBeginWithSpace, telephoneWithSpace, telephoneWithSpecialCharacter;
	String emptyEmail, invalidEmail, emailBeginWithSpace;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		//loginPage = new LoginPageObject(driver);
		//homePage = new HomePageObject(driver);
		//newCustomerPage = new NewCustomerPageObject(driver);

		driver.get("http://demo.guru99.com/V4/");
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		// Data
		userName = "mngr204279";
		password = "UgUdUhu";
		emptyCustomerName = "";
		customerNameWithNumber = "myname123";
		customerNameWithSpecialCharacter = "myname!@$";
		customerNameBeginWithSpace = " ";
		emptyAddress = "";
		addressBeginWithSpace = " ";
		emptyCity = "";
		cityWithNumber = "city123";
		cityWithSpecialCharacter = "city@#%";
		cityBeginWithSpace = " ";
		emptyState = "";
		stateWithNumber = "state123";
		stateWithSpecialCharacter = "state*&";
		stateBeginWithSpace = " ";
		pinWithCharacter = "123pin";
		pinWithMoreThan6Digits = "123456789";
		pinWithLessThan6Digits = "123";
		pintWithSpecialCharacter = "123$";
		pinBeginWithSpace = " ";
		pintWithSpace = "123 45";
		emptyAddress = "";
		telephoneBeginWithSpace = " ";
		telephoneWithSpace = "123 123";
		telephoneWithSpecialCharacter = "123@#$";
		emptyEmail = "";
		invalidEmail = "myemail@";
		emailBeginWithSpace = " ";
		
		// Login 
		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
		
		// Navigate to New customer page
		newCustomerPage = homePage.clickNewCustomerLink();
	}

	@Test
	public void TC_01_CustomerNameCannotBeBlank() {
		newCustomerPage.inputToCustomerNameTextbox(emptyCustomerName);
		newCustomerPage.pressTabKey();
		Assert.assertTrue(newCustomerPage.isCustomerNameMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_02_CustomerNameCannotContainNumber() {
		newCustomerPage.inputToCustomerNameTextbox(customerNameWithNumber);
		Assert.assertTrue(newCustomerPage.isCustomerNameMustNotContainNumberMessageDisplayed());
	}
	
	@Test
	public void TC_03_CustomerNameCannotContainSpecialCharacter() {
		newCustomerPage.inputToCustomerNameTextbox(customerNameWithSpecialCharacter);
		Assert.assertTrue(newCustomerPage.isCustomerNameMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_04_CustomerNameCannotBeginWithSpaceCharacter() {
		newCustomerPage.inputToCustomerNameTextbox(customerNameBeginWithSpace);
		Assert.assertTrue(newCustomerPage.isCustomerNameMustNotBeginWithSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_05_AddressCannotBeBlank() {
		newCustomerPage.inputToAddressTextbox(emptyAddress);
		newCustomerPage.pressTabKey();
		Assert.assertTrue(newCustomerPage.isAddressMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_06_AddressCannotBeginWithSpaceCharacter() {
		newCustomerPage.inputToAddressTextbox(addressBeginWithSpace);
		Assert.assertTrue(newCustomerPage.isAddressMustNotBeginWithSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_07_CityCannotBeBlank() {
		newCustomerPage.inputToCityTextbox(emptyCity);
		newCustomerPage.pressTabKey();
		Assert.assertTrue(newCustomerPage.isCityMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_08_CityCannotContainNumber() {
		newCustomerPage.inputToCityTextbox(cityWithNumber);
		Assert.assertTrue(newCustomerPage.isCityMustNotContainNumberMessageDisplayed());
	}
	
	@Test
	public void TC_09_CityCannotContainSpecialCharacter() {
		newCustomerPage.inputToCityTextbox(cityWithSpecialCharacter);
		Assert.assertTrue(newCustomerPage.isCityMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_10_CityCannotBeginWithSpaceCharacter() {
		newCustomerPage.inputToCityTextbox(cityBeginWithSpace);
		Assert.assertTrue(newCustomerPage.isCityMustNotBeginWithSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_11_StateCannotBeBlank() {
		newCustomerPage.inputToStateTextbox(emptyState);
		newCustomerPage.pressTabKey();
		Assert.assertTrue(newCustomerPage.isStateMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_12_StateCannotContainNumber() {
		newCustomerPage.inputToStateTextbox(stateWithNumber);
		Assert.assertTrue(newCustomerPage.isStateMustNotContainNumberMessageDisplayed());
	}
	
	@Test
	public void TC_13_StateCanNotContainSpecialCharacter() {
		newCustomerPage.inputToStateTextbox(stateWithSpecialCharacter);
		Assert.assertTrue(newCustomerPage.isStateMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_14_StateCanNotBeginWithSpace() {
		newCustomerPage.inputToStateTextbox(stateBeginWithSpace);
		Assert.assertTrue(newCustomerPage.isStateMustNotBeginWithSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_15_PinCanNotContainCharacter() {
		newCustomerPage.inputToPinTextbox(pinWithCharacter);
		Assert.assertTrue(newCustomerPage.isPinMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_16_PinCanNotBeBlank() {
		newCustomerPage.inputToPinTextbox(emptyPin);
		newCustomerPage.pressTabKey();
		Assert.assertTrue(newCustomerPage.isPinMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_17_PinCannotHaveMoreThan6Digits() {
		newCustomerPage.inputToPinTextbox(pinWithMoreThan6Digits);
		Assert.assertEquals(newCustomerPage.getEnteredTextFromPinTextbox(), pinWithMoreThan6Digits.substring(0, 6));
	}
	
	@Test
	public void TC_17_PinCannotHaveLessThan6Digits() {
		newCustomerPage.inputToPinTextbox(pinWithLessThan6Digits);
		Assert.assertTrue(newCustomerPage.isPinMustHave6DigitsMessageDisplayed());
	}
	
	@Test
	public void TC_18_PinCannotContainSpecialCharacter() {
		newCustomerPage.inputToPinTextbox(pintWithSpecialCharacter);
		Assert.assertTrue(newCustomerPage.isPinMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_19_PinCannotBeginWithSpace() {
		newCustomerPage.inputToPinTextbox(pinBeginWithSpace);
		Assert.assertTrue(newCustomerPage.isPinMustNotBeginWithSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_20_PinCannotContainSpace() {
		newCustomerPage.inputToPinTextbox(pintWithSpace);
		Assert.assertTrue(newCustomerPage.isPinMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_21_TelephoneCannotBeBlank() {
		newCustomerPage.inputToTelephoneTextbox(emptyTelephone);
		newCustomerPage.pressTabKey();
		Assert.assertTrue(newCustomerPage.isTelephoneMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_22_TelephoneCannotBeginWithSpace() {
		newCustomerPage.inputToTelephoneTextbox(telephoneBeginWithSpace);
		Assert.assertTrue(newCustomerPage.isTelephoneMustNotBeginWithSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_23_TelephoneCannotContainSpace() {
		newCustomerPage.inputToTelephoneTextbox(telephoneWithSpace);
		Assert.assertTrue(newCustomerPage.isTelephoneMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_24_TelephoneCannotContainSpecialCharacter() {
		newCustomerPage.inputToTelephoneTextbox(telephoneWithSpecialCharacter);
		Assert.assertTrue(newCustomerPage.isTelephoneMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_25_EmailCannotBlank() {
		newCustomerPage.inputToEmailTextbox(emptyEmail);
		newCustomerPage.pressTabKey();
		Assert.assertTrue(newCustomerPage.isEmailMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_26_EmailCannotBeInvalidFormat() {
		newCustomerPage.inputToEmailTextbox(invalidEmail);
		Assert.assertTrue(newCustomerPage.isEmailInvalidFormatMessageDisplayed());
	}
	
	@Test
	public void TC_27_EmailCannotBeginWithSpace() {
		newCustomerPage.inputToEmailTextbox(emailBeginWithSpace);
		Assert.assertTrue(newCustomerPage.isEmailMustNotBeginWithSpaceMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
