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
	String emptyPin, pinWithCharacter, pinWithMoreThan6Digits, pinWithLessThan6Digits, pintWithSpecialCharacter,
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
		pinWithMoreThan6Digits = "123456789";
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
		loginPage.inputToUserIDTextbox(Common_01_RegisterToSystem.username);
		loginPage.inputToPasswordTextbox(Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		// Navigate to New customer page
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
	}

	@Test
	public void TC_01_CustomerNameCannotBeBlank() {
		log.info("Validate Customer Name field with blank value - STEP 01: Do not input a value in Customer Name field and press Tab key");
		newCustomerPage.inputNothingToCustomerNameTextboxAndPressTabKey();

		log.info(
				"Validate Customer Name field with blank value - STEP 02: Verify 'Customer name must not be blank' message is displayed");
		verifyTrue(newCustomerPage.isCustomerNameMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_CustomerNameCannotContainNumber() {
		log.info("Validate Customer Name field with numeric value - STEP 01: Input numeric value into Customer Name field");
		newCustomerPage.inputToCustomerNameTextbox(customerNameWithNumber);

		log.info("Validate Customer Name field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isCustomerNameMustNotContainNumberMessageDisplayed());
	}

	@Test
	public void TC_03_CustomerNameCannotContainSpecialCharacter() {
		log.info("Validate Customer Name field with special character - STEP 01: Input special character into Customer Name field");
		newCustomerPage.inputToCustomerNameTextbox(customerNameWithSpecialCharacter);
		
		log.info("Validate Customer Name field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isCustomerNameMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_04_CustomerNameCannotBeginWithSpaceCharacter() {
		log.info("Validate Customer Name field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToCustomerNameTextbox(customerNameBeginWithSpace);
		
		log.info("Validate Customer Name field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newCustomerPage.isCustomerNameMustNotBeginWithSpaceMessageDisplayed());
	}

	@Test
	public void TC_05_AddressCannotBeBlank() {
		log.info("Validate Address field with blank value - STEP 01: Do not input a value into Address field and press Tab key");
		newCustomerPage.inputNothingToAddressTextareaAndPressTabKey();
		
		log.info("Validate Address field with blank value - STEP 02: Verify 'Address Field must not be blank' message is displayed");
		verifyTrue(newCustomerPage.isAddressMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_06_AddressCannotBeginWithSpaceCharacter() {
		log.info("Validate Address field field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToAddressTextarea(addressBeginWithSpace);
		
		log.info("Validate Address field field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newCustomerPage.isAddressMustNotBeginWithSpaceMessageDisplayed());
	}

	@Test
	public void TC_07_CityCannotBeBlank() {
		log.info("Validate City field field with blank value - STEP 01: Do not input a value into City field and press Tab key");
		newCustomerPage.inputNothingToCityTextboxAndPressTabKey();
		
		log.info("Validate City field field with blank value - STEP 02: Verify 'City Field must not be blank' message is displayed");
		verifyTrue(newCustomerPage.isCityMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_08_CityCannotContainNumber() {
		log.info("Validate City field with numeric value - STEP 01: Input numeric value into City field");
		newCustomerPage.inputToCityTextbox(cityWithNumber);
		
		log.info("Validate City field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isCityMustNotContainNumberMessageDisplayed());
	}

	@Test
	public void TC_09_CityCannotContainSpecialCharacter() {
		log.info("Validate City field with special character - STEP 01: Input special character into City field");
		newCustomerPage.inputToCityTextbox(cityWithSpecialCharacter);
		
		log.info("Validate City field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isCityMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_10_CityCannotBeginWithSpaceCharacter() {
		log.info("Validate City field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToCityTextbox(cityBeginWithSpace);
		
		log.info("Validate City field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newCustomerPage.isCityMustNotBeginWithSpaceMessageDisplayed());
	}

	@Test
	public void TC_11_StateCannotBeBlank() {
		log.info("Validate State field with blank value - STEP 01: Do not input a value into State field and press Tab key");
		newCustomerPage.inputNothingToStateTextboxAndPressTabKey();
		
		log.info("Validate State field with blank value - STEP 02: Verify 'State must not be blank' message is displayed");
		verifyTrue(newCustomerPage.isStateMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_12_StateCannotContainNumber() {
		log.info("Validate State field with numeric value - STEP 01: Input numeric value into State field");
		newCustomerPage.inputToStateTextbox(stateWithNumber);
		
		log.info("Validate State field with numeric value - STEP 02: Verify 'Numbers are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isStateMustNotContainNumberMessageDisplayed());
	}

	@Test
	public void TC_13_StateCanNotContainSpecialCharacter() {
		log.info("Validate State field with special character - STEP 01: Input special character into State field");
		newCustomerPage.inputToStateTextbox(stateWithSpecialCharacter);
		
		log.info("Validate State field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isStateMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_14_StateCanNotBeginWithSpace() {
		log.info("Validate State field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToStateTextbox(stateBeginWithSpace);
		
		log.info("Validate State field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newCustomerPage.isStateMustNotBeginWithSpaceMessageDisplayed());
	}

	@Test
	public void TC_15_PinCanNotContainCharacter() {
		log.info("Validate Pin field with character - STEP 01: Input character into Pin field");
		newCustomerPage.inputToPinTextbox(pinWithCharacter);
		
		log.info("Validate Pin field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isPinMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_16_PinCanNotBeBlank() {
		log.info("Validate Pin field with blank value - STEP 01: Do not input a value into Pin field and press Tab key");
		newCustomerPage.inputNothingToPinTextboxAndPressTabKey();
		
		log.info("Validate Pin field with blank value - STEP 02: Verify 'PIN Code must not be blank' message is displayed");
		verifyTrue(newCustomerPage.isPinMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_17_PinCannotHaveMoreThan6Digits() {
		log.info("Validate Pin field with more than 6 digits - STEP 01: Input more than 6 digits into Pin field");
		newCustomerPage.inputToPinTextbox(pinWithMoreThan6Digits);
		
		log.info("Validate Pin field with more than 6 digits - STEP 02: Verify only able to input 6 digits");
		verifyEquals(newCustomerPage.getEnteredTextFromPinTextbox(), pinWithMoreThan6Digits.substring(0, 6));
	}

	@Test
	public void TC_17_PinCannotHaveLessThan6Digits() {
		log.info("Validate Pin field with less than 6 digits - STEP 01: Input less than 6 digits into Pin field");
		newCustomerPage.inputToPinTextbox(pinWithLessThan6Digits);
		
		log.info("Validate Pin field with less than 6 digits - STEP 02: Verify 'PIN Code must have 6 Digits' message is displayed");
		verifyTrue(newCustomerPage.isPinMustHave6DigitsMessageDisplayed());
	}

	@Test
	public void TC_18_PinCannotContainSpecialCharacter() {
		log.info("Validate Pin field with special character - STEP 01: Input special character into Pin field");
		newCustomerPage.inputToPinTextbox(pintWithSpecialCharacter);
		
		log.info("Validate Pin field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isPinMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_19_PinCannotBeginWithSpace() {
		log.info("Validate Pin field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToPinTextbox(pinBeginWithSpace);
		
		log.info("Validate Pin field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newCustomerPage.isPinMustNotBeginWithSpaceMessageDisplayed());
	}

	@Test
	public void TC_20_PinCannotContainSpace() {
		log.info("Validate Pin field with space - STEP 01: Input pin containing a space into Pin field");
		newCustomerPage.inputToPinTextbox(pintWithSpace);
		
		log.info("Validate Pin field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isPinMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_21_TelephoneCannotBeBlank() {
		log.info("Validate Telephone field with blank value - STEP 01: Do not input a value into Telephone field and press Tab key");
		newCustomerPage.inputNothingToTelephoneTextboxAndPressTabKey();
		
		log.info("Validate Telephone field with blank value - STEP 02: Verify 'Mobile no must not be blank' message is displayed");
		verifyTrue(newCustomerPage.isTelephoneMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_22_TelephoneCannotBeginWithSpace() {
		log.info("Validate Telephone field with space at the beginning - STEP 01: Input first character as a space");
		newCustomerPage.inputToTelephoneTextbox(telephoneBeginWithSpace);
		
		log.info("Validate Telephone field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newCustomerPage.isTelephoneMustNotBeginWithSpaceMessageDisplayed());
	}

	@Test
	public void TC_23_TelephoneCannotContainSpace() {
		log.info("Validate Telephone field with space - STEP 01: Input telephone containing space into Telephone field");
		newCustomerPage.inputToTelephoneTextbox(telephoneWithSpace);
		
		log.info("Validate Telephone field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isTelephoneMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_24_TelephoneCannotContainSpecialCharacter() {
		log.info("Validate Telephone field with special character - STEP 01: Input speical character into Telephone field");
		newCustomerPage.inputToTelephoneTextbox(telephoneWithSpecialCharacter);
		
		log.info("Validate Telephone field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(newCustomerPage.isTelephoneMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_25_EmailCannotBeBlank() {
		log.info("Validate Email field with blank value - STEP 01: Do not input a value into Email field and press Tab key");
		newCustomerPage.inputNothingToEmailTextboxAndPressTabKey();
		
		log.info("Validate Email field with blank value - STEP 02: Verify 'Email-ID must not be blank' message is displayed");
		verifyTrue(newCustomerPage.isEmailMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_26_EmailCannotBeInvalidFormat() {
		log.info("Validate Email field with invalid email - STEP 01: Input an invalid email into Email field");
		newCustomerPage.inputToEmailTextbox(invalidEmail);
		
		log.info("Validate Email field with invalid email - STEP 02: Verify 'Email-ID is not valid' message is displayed");
		verifyTrue(newCustomerPage.isEmailInvalidFormatMessageDisplayed());
	}

	@Test
	public void TC_27_EmailCannotBeginWithSpace() {
		log.info("Validate Email field with space at the beginning - STEP 01: Inout first character as a space");
		newCustomerPage.inputToEmailTextbox(emailBeginWithSpace);
		
		log.info("Validate Email field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newCustomerPage.isEmailMustNotBeginWithSpaceMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
