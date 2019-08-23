package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.bankguru.commons.Common_01_RegisterToSystem;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_02_EditCustomer extends AbstractTest {
	WebDriver driver;
	String customerIDWithCharacter, customerIDWithSpecialCharacter;
	String cityWithNumber, cityWithSpecialCharacter;
	String stateWithSpecialCharacter, stateWithNumber;
	String pinWithCharacter, emptyPin, pinWithLessThan6Digits, pinWithMoreThan6Digits, pinWithSpecialCharacter;
	String telephoneWithSpecialCharacter;
	String invalidEmail;
	String newCustomerID, newCustomerName, newCustomerDateOfBirth, newCustomerAddress, newCustomerState;
	String newCustomerCity, newCustomerPIN, newCustomerTelephone, newCustomerEmail, newCustomerPassword;
	String editCustomerPageURL;

	HomePageObject homePage;
	LoginPageObject loginPage;
	EditCustomerPageObject editCustomerPage;
	NewCustomerPageObject newCustomerPage;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browserName) {
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Data
		customerIDWithCharacter = "123acc";
		customerIDWithSpecialCharacter = "123$%^";
		cityWithNumber = "city123";
		cityWithSpecialCharacter = "city@#%";
		stateWithSpecialCharacter = "state#$%";
		stateWithNumber = "state123";
		pinWithCharacter = "123abc";
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
		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_CustomerIDCannotBeBlank() {
		log.info("TC_01_CustomerIDCannotBeBlank - STEP 01: Open Edit Customer page");
		homePage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		editCustomerPageURL = editCustomerPage.getEditCustomerPageURL();

		log.info("TC_01_CustomerIDCannotBeBlank - STEP 02: Do not input a value in Customer ID field and press Tab key");
		editCustomerPage.pressTabToDynamicTextbox(driver, "cusid");

		log.info("TC_01_CustomerIDCannotBeBlank - STEP 03: Verify 'Customer ID is required' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Customer ID is required");
	}

	@Test
	public void TC_02_CustomerIDCannotContainCharacter() {
		log.info("TC_02_CustomerIDCannotContainCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_02_CustomerIDCannotContainCharacter - STEP 02: Input to Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", customerIDWithCharacter);

		log.info("TC_02_CustomerIDCannotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Characters are not allowed");
	}

	@Test
	public void TC_03_CustomerIDCannotContainSpecialCharacter() {
		log.info("TC_03_CustomerIDCannotContainSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_03_CustomerIDCannotContainSpecialCharacter STEP 02: Input to Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", customerIDWithSpecialCharacter);

		log.info("TC_03_CustomerIDCannotContainSpecialCharacter STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_ValidCustomerID() {
		log.info("TC_04_ValidCustomerID - STEP 01: Open New Customer page");
		editCustomerPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_04_ValidCustomerID - STEP 02: Input valid data to Add New Customer form");
		newCustomerPage.inputToDynamicTextbox(driver, "name", newCustomerName);
		newCustomerPage.selectDynamicRadioButton(driver, "f");
		newCustomerPage.inputToDynamicTextbox(driver, "dob", newCustomerDateOfBirth);
		newCustomerPage.inputToDynamicTextarea(driver, "addr", newCustomerAddress);
		newCustomerPage.inputToDynamicTextbox(driver, "city", newCustomerCity);
		newCustomerPage.inputToDynamicTextbox(driver, "state", newCustomerState);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", newCustomerPIN);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", newCustomerTelephone);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", newCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", newCustomerPassword);

		log.info("TC_04_ValidCustomerID - STEP 03: Click Submit button");
		newCustomerPage.clickDynamicButton(driver, "sub");
		newCustomerID = newCustomerPage.getDanymicDataInTable(driver, "Customer ID");

		log.info("TC_04_ValidCustomerID - STEP 04: Open Edit Customer page");
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));

		log.info("TC_04_ValidCustomerID - STEP 05: Input ID of the newly created customer into Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);

		log.info("TC_04_ValidCustomerID - STEP 06: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_04_ValidCustomerID - STEP 07: Verify Edit Customer Entry page is displayed");
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer"));
	}

	@Test 
	public void TC_05_AddressCannotBeBlank() {
		log.info("TC_05_AddressCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_05_AddressCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_05_AddressCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_05_AddressCannotBeBlank - STEP 04: Do not input a value into Address field and press Tab key");
		editCustomerPage.inputToDynamicTextarea(driver, "addr", "");
		editCustomerPage.pressTabToDynamicTextarea(driver, "addr");

		log.info("TC_05_AddressCannotBeBlank - STEP 05: Verify 'Address Field must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Address"), "Address Field must not be blank");
	}

	@Test 
	public void TC_06_CityCannotBeBlank() {
		log.info("TC_06_CityCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_06_CityCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_06_CityCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_06_CityCannotBeBlank - STEP 04: Do not input a value into City field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "city", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "city");

		log.info("TC_06_CityCannotBeBlank - STEP 05: Verify 'City Field must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "City Field must not be blank");
	}

	@Test 
	public void TC_07_CityCannotContainNumber() {
		log.info("TC_07_CityCannotContainNumber - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_07_CityCannotContainNumber - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_07_CityCannotContainNumber - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_07_CityCannotContainNumber - STEP 04: Input to City field");
		editCustomerPage.inputToDynamicTextbox(driver, "city", cityWithNumber);

		log.info("TC_07_CityCannotContainNumber - STEP 05: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "Numbers are not allowed");
	}

	@Test 
	public void TC_08_CityCannotContainSpecialCharacter() {
		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 04: Input to City field");
		editCustomerPage.inputToDynamicTextbox(driver, "city", cityWithSpecialCharacter);

		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 05: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "Special characters are not allowed");
	}

	@Test 
	public void TC_09_StateCannotBeBlank() {
		log.info("TC_09_StateCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_09_StateCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_09_StateCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_09_StateCannotBeBlank - STEP 04: Do not input a value into State field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "state", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "state");

		log.info("TC_09_StateCannotBeBlank - STEP 05: Verify 'State must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "State must not be blank");
	}

	@Test 
	public void TC_10_StateCannotContainNumber() {
		log.info("TC_10_StateCannotContainNumber - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_10_StateCannotContainNumber - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_10_StateCannotContainNumber - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_10_StateCannotContainNumber - STEP 04: Input to State field");
		editCustomerPage.inputToDynamicTextbox(driver, "state", stateWithNumber);

		log.info("TC_10_StateCannotContainNumber - STEP 05: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "Numbers are not allowed");
	}

	@Test 
	public void TC_11_StateCannotContainSpecialCharacter() {
		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 04: Input to State field");
		editCustomerPage.inputToDynamicTextbox(driver, "state", stateWithSpecialCharacter);

		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 05: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "Special characters are not allowed");
	}

	@Test 
	public void TC_12_PinCannotContainCharacter() {
		log.info("TC_12_PinCannotContainCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_12_PinCannotContainCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_12_PinCannotContainCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_12_PinCannotContainCharacter - STEP 04: Input to Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithCharacter);

		log.info("TC_12_PinCannotContainCharacter - STEP 05: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Characters are not allowed");
	}

	@Test 
	public void TC_13_PinCannotBeBlank() {
		log.info("TC_13_PinCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_13_PinCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_13_PinCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_13_PinCannotBeBlank - STEP 04: Do not input a value into Pin field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "pinno");

		log.info("TC_13_PinCannotBeBlank - STEP 05: Verify 'PIN Code must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "PIN Code must not be blank");
	}

	@Test
	public void TC_14_PinCannotLessThan6Digits() {
		log.info("TC_14_PinCannotLessThan6Digits - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_14_PinCannotLessThan6Digits - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_14_PinCannotLessThan6Digits - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_14_PinCannotLessThan6Digits - STEP 04: Input to Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithLessThan6Digits);

		log.info("TC_14_PinCannotLessThan6Digits - STEP 05: Verify 'PIN Code must have 6 Digits' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "PIN Code must have 6 Digits");
	}

	@Test 
	public void TC_15_PinCannotHaveSpecialCharacter() {
		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 04: Input to Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithSpecialCharacter);

		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 05: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Special characters are not allowed");
	}

	@Test 
	public void TC_16_TelephoneCannotBeBlank() {
		log.info("TC_16_TelephoneCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_16_TelephoneCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_16_TelephoneCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_16_TelephoneCannotBeBlank - STEP 04: Do not input a value into Telephone field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "telephoneno");

		log.info("TC_16_TelephoneCannotBeBlank - STEP 05: Verify 'Mobile no must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"), "Mobile no must not be blank");
	}

	@Test 
	public void TC_17_TelephoneCannotContainSpecialCharacter() {
		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 04: Input to Telephone field");
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", telephoneWithSpecialCharacter);

		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 05: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"), "Special characters are not allowed");
	}

	@Test 
	public void TC_18_EmailCannotBeBlank() {
		log.info("TC_18_EmailCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_18_EmailCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_18_EmailCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_18_EmailCannotBeBlank - STEP 04: Do not input a value into Email field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "emailid");

		log.info("TC_18_EmailCannotBeBlank - STEP 05: Verify 'Email-ID must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "Email-ID must not be blank");
	}

	@Test 
	public void TC_19_EmaitCannotBeInvalidFormat() {
		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 04: Input to Email field");
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", invalidEmail);

		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 05: Verify 'Email-ID is not valid' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "Email-ID is not valid");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}
}
