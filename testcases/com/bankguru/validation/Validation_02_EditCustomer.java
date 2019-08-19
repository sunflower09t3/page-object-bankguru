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
		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		// Navigate to New customer page
		homePage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
	}

	@Test
	public void TC_01_CustomerIDCannotBeBlank() {
		log.info("Validate Customer ID field with blank value - STEP 01: Do not input a value in Customer ID field and press Tab key");
		editCustomerPage.pressTabToDynamicTextbox(driver, "cusid");

		log.info("Validate Customer ID field with blank value - STEP 02: Verify 'Customer ID is required' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Customer ID is required");
	}

	@Test
	public void TC_02_CustomerIDCannotContainCharacter() {
		log.info("Validate Customer ID field with character - STEP 01: Input character into Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", customerIDWithCharacter);

		log.info("Validate Customer ID field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Characters are not allowed");
	}

	@Test
	public void TC_03_CustomerIDCannotContainSpecialCharacter() {
		log.info("Validate Customer ID field with special character - STEP 01: Input special character into Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", customerIDWithSpecialCharacter);
		
		log.info("Validate Customer ID field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_ValidCustomerID() {
		log.info("Valid customer ID - STEP 01: Open New Customer page");
		editCustomerPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Valid customer ID - STEP 02: Fill in New Customer form");
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
		
		log.info("Valid customer ID - STEP 03: Click Submit button");
		newCustomerPage.clickDynamicButton(driver, "sub");

		log.info("Valid customer ID - STEP 04: Store ID of the newly created customer");
		newCustomerID = newCustomerPage.getCustomerID();

		log.info("Valid customer ID - STEP 05: Open Edit Customer page");
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		log.info("Valid customer ID - STEP 06: Input ID of the newly created customer into Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		
		log.info("Valid customer ID - STEP 07: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");

		log.info("Valid customer ID - STEP 08: Verify Edit Customer Entry page is displayed");
		verifyTrue(editCustomerPage.isRedirectedToEditCustomerEntryPage());
	}

	@Test
	public void TC_05_AddressCannotBeBlank() {
		log.info("Validate Address field with blank value - STEP 01: Do not input a value into Address field and press Tab key");
		editCustomerPage.inputToDynamicTextarea(driver, "addr", "");
		editCustomerPage.pressTabToDynamicTextarea(driver, "addr");
		
		log.info("Validate Address field with blank value - STEP 02: Verify 'Address Field must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Address"), "Address Field must not be blank");
	}

	@Test
	public void TC_06_CityCannotBeBlank() {
		log.info("Validate City field field with blank value - STEP 01: Do not input a value into City field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "city", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "city");
		
		log.info("Validate City field field with blank value - STEP 02: Verify 'City Field must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "City Field must not be blank");
	}

	@Test
	public void TC_07_CityCannotContainNumber() {
		log.info("Validate City field with numeric value - STEP 01: Input numeric value into City field");
		editCustomerPage.inputToDynamicTextbox(driver, "city", cityWithNumber);
		
		log.info("Validate City field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "Numbers are not allowed");
	}

	@Test
	public void TC_08_CityCannotContainSpecialCharacter() {
		log.info("Validate City field with special character - STEP 01: Input special character into City field");
		editCustomerPage.inputToDynamicTextbox(driver, "city", cityWithSpecialCharacter);
		
		log.info("Validate City field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "Special characters are not allowed");
	}

	@Test
	public void TC_09_StateCannotBeBlank() {
		log.info("Validate State field with blank value - STEP 01: Do not input a value into State field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "state", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "state");
		
		log.info("Validate State field with blank value - STEP 02: Verify 'State must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "State must not be blank");
	}

	@Test
	public void TC_10_StateCannotContainNumber() {
		log.info("Validate State field with numeric value - STEP 01: Input numeric value into State field");
		editCustomerPage.inputToDynamicTextbox(driver, "state", stateWithNumber);
		
		log.info("Validate State field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "Numbers are not allowed");
	}

	@Test
	public void TC_11_StateCannotContainSpecialCharacter() {
		log.info("Validate State field with special character - STEP 01: Input special character into State field");
		editCustomerPage.inputToDynamicTextbox(driver, "state", stateWithSpecialCharacter);
		
		log.info("Validate State field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "Special characters are not allowed");
	}

	@Test
	public void TC_12_PinCannotContainCharacter() {
		log.info("Validate Pin field with character - STEP 01: Input character into Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithCharacter);
		
		log.info("Validate Pin field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Characters are not allowed");
	}

	@Test
	public void TC_13_PinCannotBeBlank() {
		log.info("Validate Pin field with blank value - STEP 01: Do not input a value into Pin field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "pinno");
		
		log.info("Validate Pin field with blank value - STEP 02: Verify 'PIN Code must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "PIN Code must not be blank");
	}

	@Test
	public void TC_14_PinCannotLessThan6Digits() {
		log.info("Validate Pin field with less than 6 digits - STEP 01: Input less than 6 digits into Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithLessThan6Digits);
		
		log.info("Validate Pin field with less than 6 digits - STEP 02: Verify 'PIN Code must have 6 Digits' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "PIN Code must have 6 Digits");
	}

	@Test
	public void TC_15_PinCannotHaveSpecialCharacter() {
		log.info("Validate Pin field with special character - STEP 01: Input special character into Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithSpecialCharacter);
		
		log.info("Validate Pin field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Special characters are not allowed");
	}

	@Test
	public void TC_16_TelephoneCannotBeBlank() {
		log.info("Validate Telephone field with blank value - STEP 01: Do not input a value into Telephone field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "telephoneno");
		
		log.info("Validate Telephone field with blank value - STEP 02: Verify 'Mobile no must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"), "Mobile no must not be blank");
	}

	@Test
	public void TC_17_TelephoneCannotContainSpecialCharacter() {
		log.info("Validate Telephone field with special character - STEP 01: Input speical character into Telephone field");
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", telephoneWithSpecialCharacter);
		
		log.info("Validate Telephone field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"), "Special characters are not allowed");
	}

	@Test
	public void TC_18_EmailCannotBeBlank() {
		log.info("Validate Email field with blank value - STEP 01: Do not input a value into Email field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", "");
		editCustomerPage.pressTabToDynamicTextbox(driver, "emailid");
		
		log.info("Validate Email field with blank value - STEP 02: Verify 'Email-ID must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "Email-ID must not be blank");
	}

	@Test
	public void TC_19_EmaitCannotBeInvalidFormat() {
		log.info("Validate Email field with invalid email - STEP 01: Input an invalid email into Email field");
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", invalidEmail);
		
		log.info("Validate Email field with invalid email - STEP 02: Verify 'Email-ID is not valid' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "Email-ID is not valid");
	}

	@AfterClass (alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}

