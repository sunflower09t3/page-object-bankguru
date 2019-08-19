package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.bankguru.commons.Common_01_RegisterToSystem;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_01_NewCustomer extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	NewCustomerPageObject newCustomerPage;
	HomePageObject homePage;
	String customerNameWithNumber, customerNameWithSpecialCharacter, customerNameBeginWithSpace;
	String addressBeginWithSpace;
	String cityWithNumber, cityWithSpecialCharacter, cityBeginWithSpace;
	String stateWithNumber, stateWithSpecialCharacter, stateBeginWithSpace;
	String emptyPin, pinWithCharacter, pinWithLessThan6Digits, pintWithSpecialCharacter,
			pinBeginWithSpace, pintWithSpace;
	String telephoneBeginWithSpace, telephoneWithSpace, telephoneWithSpecialCharacter;
	String invalidEmail, emailBeginWithSpace;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Data
		customerNameWithNumber = "myname123";
		customerNameWithSpecialCharacter = "myname!@$";
		customerNameBeginWithSpace = " ";
		addressBeginWithSpace = " ";
		cityWithNumber = "city123";
		cityWithSpecialCharacter = "city@#%";
		cityBeginWithSpace = " ";
		stateWithNumber = "state123";
		stateWithSpecialCharacter = "state*&";
		stateBeginWithSpace = " ";
		pinWithCharacter = "123pin";
		pinWithLessThan6Digits = "123";
		pintWithSpecialCharacter = "123$";
		pinBeginWithSpace = " ";
		pintWithSpace = "123 45";
		emptyPin = "";
		telephoneBeginWithSpace = " ";
		telephoneWithSpace = "123 123";
		telephoneWithSpecialCharacter = "123@#$";
		invalidEmail = "myemail@";
		emailBeginWithSpace = " ";

		// Login
		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		// Navigate to New customer page
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
	}

	@Test
	public void TC_01_CustomerNameCannotBeBlank() {
		log.info(
				"Validate Customer Name field with blank value - STEP 01: Do not input a value in Customer Name field and press Tab key");
		newCustomerPage.pressTabToDynamicTextbox(driver, "name");

		log.info(
				"Validate Customer Name field with blank value - STEP 02: Verify 'Customer name must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Customer Name"),
				"Customer name must not be blank");
	}

	@Test
	public void TC_02_CustomerNameCannotContainNumber() {
		log.info(
				"Validate Customer Name field with numeric value - STEP 01: Input numeric value into Customer Name field");
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerNameWithNumber);

		log.info(
				"Validate Customer Name field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Customer Name"), "Numbers are not allowed");
	}

	@Test
	public void TC_03_CustomerNameCannotContainSpecialCharacter() {
		log.info(
				"Validate Customer Name field with special character - STEP 01: Input special character into Customer Name field");
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerNameWithSpecialCharacter);

		log.info(
				"Validate Customer Name field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Customer Name"),
				"Special characters are not allowed");
	}

	@Test
	public void TC_04_CustomerNameCannotBeginWithSpaceCharacter() {
		log.info(
				"Validate Customer Name field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerNameBeginWithSpace);

		log.info(
				"Validate Customer Name field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Customer Name"),
				"First character can not have space");
	}

	@Test
	public void TC_05_AddressCannotBeBlank() {
		log.info(
				"Validate Address field with blank value - STEP 01: Do not input a value into Address field and press Tab key");
		newCustomerPage.pressTabToDynamicTextarea(driver, "addr");

		log.info(
				"Validate Address field with blank value - STEP 02: Verify 'Address Field must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Address"),
				"Address Field must not be blank");
	}

	@Test
	public void TC_06_AddressCannotBeginWithSpaceCharacter() {
		log.info(
				"Validate Address field field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToDynamicTextarea(driver, "addr", addressBeginWithSpace);

		log.info(
				"Validate Address field field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Address"),
				"First character can not have space");
	}

	@Test
	public void TC_07_CityCannotBeBlank() {
		log.info(
				"Validate City field field with blank value - STEP 01: Do not input a value into City field and press Tab key");
		newCustomerPage.pressTabToDynamicTextbox(driver, "city");

		log.info(
				"Validate City field field with blank value - STEP 02: Verify 'City Field must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "City Field must not be blank");
	}

	@Test
	public void TC_08_CityCannotContainNumber() {
		log.info("Validate City field with numeric value - STEP 01: Input numeric value into City field");
		newCustomerPage.inputToDynamicTextbox(driver, "city", cityWithNumber);

		log.info(
				"Validate City field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "Numbers are not allowed");
	}

	@Test
	public void TC_09_CityCannotContainSpecialCharacter() {
		log.info("Validate City field with special character - STEP 01: Input special character into City field");
		newCustomerPage.inputToDynamicTextbox(driver, "city", cityWithSpecialCharacter);

		log.info(
				"Validate City field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "City"),
				"Special characters are not allowed");
	}

	@Test
	public void TC_10_CityCannotBeginWithSpaceCharacter() {
		log.info("Validate City field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToDynamicTextbox(driver, "city", cityBeginWithSpace);

		log.info(
				"Validate City field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "City"),
				"First character can not have space");
	}

	@Test
	public void TC_11_StateCannotBeBlank() {
		log.info(
				"Validate State field with blank value - STEP 01: Do not input a value into State field and press Tab key");
		newCustomerPage.pressTabToDynamicTextbox(driver, "state");

		log.info(
				"Validate State field with blank value - STEP 02: Verify 'State must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "State must not be blank");
	}

	@Test
	public void TC_12_StateCannotContainNumber() {
		log.info("Validate State field with numeric value - STEP 01: Input numeric value into State field");
		newCustomerPage.inputToDynamicTextbox(driver, "state", stateWithNumber);

		log.info(
				"Validate State field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "Numbers are not allowed");
	}

	@Test
	public void TC_13_StateCanNotContainSpecialCharacter() {
		log.info("Validate State field with special character - STEP 01: Input special character into State field");
		newCustomerPage.inputToDynamicTextbox(driver, "state", stateWithSpecialCharacter);

		log.info(
				"Validate State field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "State"),
				"Special characters are not allowed");
	}

	@Test
	public void TC_14_StateCanNotBeginWithSpace() {
		log.info("Validate State field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToDynamicTextbox(driver, "state", stateBeginWithSpace);

		log.info(
				"Validate State field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "State"),
				"First character can not have space");
	}

	@Test
	public void TC_15_PinCanNotBeBlank() {
		log.info(
				"Validate Pin field with blank value - STEP 01: Do not input a value into Pin field and press Tab key");
		newCustomerPage.pressTabToDynamicTextbox(driver, "pinno");

		log.info(
				"Validate Pin field with blank value - STEP 02: Verify 'PIN Code must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "PIN Code must not be blank");
	}

	@Test
	public void TC_16_PinCanNotContainCharacter() {
		log.info("Validate Pin field with character - STEP 01: Input character into Pin field");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithCharacter);

		log.info(
				"Validate Pin field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Characters are not allowed");
	}

	@Test
	public void TC_17_PinCannotHaveLessThan6Digits() {
		log.info("Validate Pin field with less than 6 digits - STEP 01: Input less than 6 digits into Pin field");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithLessThan6Digits);

		log.info(
				"Validate Pin field with less than 6 digits - STEP 02: Verify 'PIN Code must have 6 Digits' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "PIN Code must have 6 Digits");
	}

	@Test
	public void TC_18_PinCannotContainSpecialCharacter() {
		log.info("Validate Pin field with special character - STEP 01: Input special character into Pin field");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pintWithSpecialCharacter);

		log.info(
				"Validate Pin field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Special characters are not allowed");
	}

	@Test
	public void TC_19_PinCannotBeginWithSpace() {
		log.info("Validate Pin field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pinBeginWithSpace);

		log.info(
				"Validate Pin field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"),
				"First character can not have space");
	}

	@Test
	public void TC_20_PinCannotContainSpace() {
		log.info("Validate Pin field with space - STEP 01: Input pin containing a space into Pin field");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pintWithSpace);

		log.info("Validate Pin field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Characters are not allowed");
	}

	@Test
	public void TC_21_TelephoneCannotBeBlank() {
		log.info(
				"Validate Telephone field with blank value - STEP 01: Do not input a value into Telephone field and press Tab key");
		newCustomerPage.pressTabToDynamicTextbox(driver, "telephoneno");

		log.info(
				"Validate Telephone field with blank value - STEP 02: Verify 'Mobile no must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"),
				"Mobile no must not be blank");
	}

	@Test
	public void TC_22_TelephoneCannotBeginWithSpace() {
		log.info("Validate Telephone field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", telephoneBeginWithSpace);

		log.info(
				"Validate Telephone field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"),
				"First character can not have space");
	}

	@Test
	public void TC_23_TelephoneCannotContainSpace() {
		log.info(
				"Validate Telephone field with space - STEP 01: Input telephone containing space into Telephone field");
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", telephoneWithSpace);

		log.info(
				"Validate Telephone field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"),
				"Characters are not allowed");
	}

	@Test
	public void TC_24_TelephoneCannotContainSpecialCharacter() {
		log.info(
				"Validate Telephone field with special character - STEP 01: Input speical character into Telephone field");
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", telephoneWithSpecialCharacter);

		log.info(
				"Validate Telephone field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"),
				"Special characters are not allowed");
	}

	@Test
	public void TC_25_EmailCannotBeBlank() {
		log.info(
				"Validate Email field with blank value - STEP 01: Do not input a value into Email field and press Tab key");
		newCustomerPage.pressTabToDynamicTextbox(driver, "emailid");

		log.info(
				"Validate Email field with blank value - STEP 02: Verify 'Email-ID must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "Email-ID must not be blank");
	}

	@Test
	public void TC_26_EmailCannotBeInvalidFormat() {
		log.info("Validate Email field with invalid email - STEP 01: Input an invalid email into Email field");
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", invalidEmail);

		log.info(
				"Validate Email field with invalid email - STEP 02: Verify 'Email-ID is not valid' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "Email-ID is not valid");
	}

	@Test
	public void TC_27_EmailCannotBeginWithSpace() {
		log.info("Validate Email field with space at the beginning - STEP 01: Inout first character as a space");
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", emailBeginWithSpace);

		log.info(
				"Validate Email field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"),
				"First character can not have space");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
