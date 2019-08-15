package com.bankguru.validation;

import java.util.Random;

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
import pageObjects.NewCustomerPageObject;

public class Validation_02_EditCustomer extends AbstractTest {
	WebDriver driver;
	String userName, password;
	String customerIDWithCharacter, customerIDWithSpecialCharacter;
	String cityWithNumber, cityWithSpecialCharacter;
	String stateWithSpecialCharacter, stateWithNumber;
	String pinWithCharacter, emptyPin, pinWithLessThan6Digits, pinWithMoreThan6Digits, pinWithSpecialCharacter;
	String telephoneWithSpecialCharacter;
	String invalidEmail;
	String newCustomerID, newCustomerName, newCustomerDateOfBirth, newCustomerAddress, newCustomerState;
	String newCustomerCity, newCustomerPIN, newCustomerTelephone, newCustomerEmail, newCustomerPassword;

	HomePageObject homePage;
	LoginPageObject loginPage;
	EditCustomerPageObject editCustomerPage;
	NewCustomerPageObject newCustomerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Data
		userName = "mngr213678";
		password = "Anajabu";
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
		log.info("Validate Customer ID field with blank value - STEP 01: Do not input a value in Customer ID field and press Tab key");
		editCustomerPage.inputNothingToCustomerIDTextboxAndPressTabKey();

		log.info("Validate Customer ID field with blank value - STEP 02: Verify 'Customer ID is required' message is displayed");
		verifyTrue(editCustomerPage.isCustomerIDMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_CustomerIDCannotContainCharacter() {
		log.info("Validate Customer ID field with character - STEP 01: Input character into Customer ID field");
		editCustomerPage.inputToCustomerIDTextbox(customerIDWithCharacter);

		log.info("Validate Customer ID field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isCustomerIDMustNotContainCharacterDisplayed());
	}

	@Test
	public void TC_03_CustomerIDCannotContainSpecialCharacter() {
		log.info("Validate Customer ID field with special character - STEP 01: Input special character into Customer ID field");
		editCustomerPage.inputToCustomerIDTextbox(customerIDWithSpecialCharacter);
		
		log.info("Validate Customer ID field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isCustomerIDMustNotContainSpecialCharacterMessgaeDisplayed());
	}

	@Test
	public void TC_04_ValidCustomerID() {
		log.info("Valid customer ID - STEP 01: Open New Customer page");
		editCustomerPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Valid customer ID - STEP 02: Fill in New Customer form");
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
		
		log.info("Valid customer ID - STEP 03: Click Submit button");
		newCustomerPage.clickSubmitButton();

		log.info("Valid customer ID - STEP 04: Store ID of the newly created customer");
		newCustomerID = newCustomerPage.getCustomerID();

		log.info("Valid customer ID - STEP 05: Open Edit Customer page");
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		log.info("Valid customer ID - STEP 06: Input ID of the newly created customer into Customer ID field");
		editCustomerPage.inputToCustomerIDTextbox(newCustomerID);
		
		log.info("Valid customer ID - STEP 07: Click Submit button");
		editCustomerPage.clickAccSubmitButton();

		log.info("Valid customer ID - STEP 08: Verify Edit Customer Entry page is displayed");
		verifyTrue(editCustomerPage.isRedirectedToEditCustomerEntryPage());
	}

	@Test
	public void TC_08_AddressCannotBeBlank() {
		log.info("Validate Address field with blank value - STEP 01: Do not input a value into Address field and press Tab key");
		editCustomerPage.inputNothingToAddressTextareaAndPressTabKey();
		
		log.info("Validate Address field with blank value - STEP 02: Verify 'Address Field must not be blank' message is displayed");
		verifyTrue(editCustomerPage.isAddressMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_09_CityCannotBeBlank() {
		log.info("Validate City field field with blank value - STEP 01: Do not input a value into City field and press Tab key");
		editCustomerPage.inputNothingToCityTextboxAndPressTabKey();
		
		log.info("Validate City field field with blank value - STEP 02: Verify 'City Field must not be blank' message is displayed");
		verifyTrue(editCustomerPage.isCityMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_10_CityCannotContainNumber() {
		log.info("Validate City field with numeric value - STEP 01: Input numeric value into City field");
		editCustomerPage.inputToCityTextbox(cityWithNumber);
		
		log.info("Validate City field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isCityMustNotContainNumberMessageDisplayed());
	}

	@Test
	public void TC_11_CityCannotContainSpecialCharacter() {
		log.info("Validate City field with special character - STEP 01: Input special character into City field");
		editCustomerPage.inputToCityTextbox(cityWithSpecialCharacter);
		
		log.info("Validate City field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isCityMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_12_StateCannotBeBlank() {
		log.info("Validate State field with blank value - STEP 01: Do not input a value into State field and press Tab key");
		editCustomerPage.inputNothingToStateTextboxAndPressTabKey();
		
		log.info("Validate State field with blank value - STEP 02: Verify 'State must not be blank' message is displayed");
		verifyTrue(editCustomerPage.isStateMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_13_StateCannotContainNumber() {
		log.info("Validate State field with numeric value - STEP 01: Input numeric value into State field");
		editCustomerPage.inputToStateTextbox(stateWithNumber);
		
		log.info("Validate State field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isStateMustNotContainNumberMessageDisplayed());
	}

	@Test
	public void TC_14_StateCannotContainSpecialCharacter() {
		log.info("Validate State field with special character - STEP 01: Input special character into State field");
		editCustomerPage.inputToStateTextbox(stateWithSpecialCharacter);
		
		log.info("Validate State field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isStateMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_15_PinCannotContainCharacter() {
		log.info("Validate Pin field with character - STEP 01: Input character into Pin field");
		editCustomerPage.inputToPinTextbox(pinWithCharacter);
		
		log.info("Validate Pin field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isPinMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_16_PinCannotBeBlank() {
		log.info("Validate Pin field with blank value - STEP 01: Do not input a value into Pin field and press Tab key");
		editCustomerPage.inputNothingToPinTextboxAndPressTabKey();
		
		log.info("Validate Pin field with blank value - STEP 02: Verify 'PIN Code must not be blank' message is displayed");
		verifyTrue(editCustomerPage.isPinMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_17_PinCannotMoreThan6Digits() {
		log.info("Validate Pin field with more than 6 digits - STEP 01: Input more than 6 digits into Pin field");
		editCustomerPage.inputToPinTextbox(pinWithMoreThan6Digits);
		
		log.info("Validate Pin field with more than 6 digits - STEP 02: Verify only able to input 6 digits");
		verifyEquals(pinWithMoreThan6Digits.substring(0, 6), editCustomerPage.getEnteredTextFromPinTextbox());
	}

	@Test
	public void TC_17_PinCannotLessThan6Digits() {
		log.info("Validate Pin field with less than 6 digits - STEP 01: Input less than 6 digits into Pin field");
		editCustomerPage.inputToPinTextbox(pinWithLessThan6Digits);
		
		log.info("Validate Pin field with less than 6 digits - STEP 02: Verify 'PIN Code must have 6 Digits' message is displayed");
		verifyTrue(editCustomerPage.isPinMustBe6DigitsMessageDisplayed());
	}

	@Test
	public void TC_18_PinCannotHaveSpecialCharacter() {
		log.info("Validate Pin field with special character - STEP 01: Input special character into Pin field");
		editCustomerPage.inputToPinTextbox(pinWithSpecialCharacter);
		
		log.info("Validate Pin field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isPinMustNotHaveSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_19_TelephoneCannotBeBlank() {
		log.info("Validate Telephone field with blank value - STEP 01: Do not input a value into Telephone field and press Tab key");
		editCustomerPage.inputNothingToTelephoneTextboxAndPressTabKey();
		
		log.info("Validate Telephone field with blank value - STEP 02: Verify 'Mobile no must not be blank' message is displayed");
		verifyTrue(editCustomerPage.isTelephoneMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_20_TelephoneCannotContainSpecialCharacter() {
		log.info("Validate Telephone field with special character - STEP 01: Input speical character into Telephone field");
		editCustomerPage.inputToTelephoneTextbox(telephoneWithSpecialCharacter);
		
		log.info("Validate Telephone field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(editCustomerPage.isTelephoneMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_21_EmailCannotBeBlank() {
		log.info("Validate Email field with blank value - STEP 01: Do not input a value into Email field and press Tab key");
		editCustomerPage.inputNothingToEmailTextboxAndPressTabKey();
		
		log.info("Validate Email field with blank value - STEP 02: Verify 'Email-ID must not be blank' message is displayed");
		verifyTrue(editCustomerPage.isEmailMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_22_EmaitCannotBeInvalidFormat() {
		log.info("Validate Email field with invalid email - STEP 01: Input an invalid email into Email field");
		editCustomerPage.inputToEmailTextbox(invalidEmail);
		
		log.info("Validate Email field with invalid email - STEP 02: Verify 'Email-ID is not valid' message is displayed");
		verifyTrue(editCustomerPage.isEmailInvalidFormatMessageDisplayed());
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
