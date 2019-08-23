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
	String emptyPin, pinWithCharacter, pinWithLessThan6Digits, pintWithSpecialCharacter, pinBeginWithSpace, pintWithSpace;
	String telephoneBeginWithSpace, telephoneWithSpace, telephoneWithSpecialCharacter;
	String invalidEmail, emailBeginWithSpace;
	String newCustomerPageURL;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browserName) {
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
		telephoneBeginWithSpace = " ";
		telephoneWithSpace = "123 123";
		telephoneWithSpecialCharacter = "123@#$";
		invalidEmail = "myemail@";
		emailBeginWithSpace = " ";

		// Login
		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();
	}

	@Test
	public void TC_01_CustomerNameCannotBeBlank() {
		log.info("TC_01_CustomerNameCannotBeBlank - STEP 01: Open New Customer page");
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));
		newCustomerPageURL = newCustomerPage.getNewCustomerPageURL();

		log.info("TC_01_CustomerNameCannotBeBlank - STEP 02: Do not input to Customer Name textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, "name");

		log.info("TC_01_CustomerNameCannotBeBlank - STEP 03: Verify 'Customer name must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Customer Name"), "Customer name must not be blank");
	}

	@Test
	public void TC_02_CustomerNameCannotContainNumber() {
		log.info("TC_02_CustomerNameCannotContainNumber - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_02_CustomerNameCannotContainNumber - STEP 02: Input to Customer Name textbox");
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerNameWithNumber);

		log.info("TC_02_CustomerNameCannotContainNumber - STEP 03: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Customer Name"), "Numbers are not allowed");
	}

	@Test
	public void TC_03_CustomerNameCannotContainSpecialCharacter() {
		log.info("TC_03_CustomerNameCannotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_03_CustomerNameCannotContainSpecialCharacter - STEP 02: Input to Customer Name textbox");
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerNameWithSpecialCharacter);

		log.info("TC_03_CustomerNameCannotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Customer Name"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_CustomerNameCannotBeginWithSpaceCharacter() {
		log.info("TC_04_CustomerNameCannotBeginWithSpaceCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_04_CustomerNameCannotBeginWithSpaceCharacter - STEP 02: Input to Customer Name textbox");
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerNameBeginWithSpace);

		log.info("TC_04_CustomerNameCannotBeginWithSpaceCharacter - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Customer Name"), "First character can not have space");
	}

	@Test
	public void TC_05_AddressCannotBeBlank() {
		log.info("TC_05_AddressCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_05_AddressCannotBeBlank - STEP 02: Do not input to Address textarea and press Tab");
		newCustomerPage.pressTabToDynamicTextarea(driver, "addr");

		log.info("TC_05_AddressCannotBeBlank - STEP 03: Verify 'Address Field must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Address"), "Address Field must not be blank");
	}

	@Test
	public void TC_06_AddressCannotBeginWithSpaceCharacter() {
		log.info("TC_06_AddressCannotBeginWithSpaceCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_06_AddressCannotBeginWithSpaceCharacter - STEP 02: Input to Address textarea");
		newCustomerPage.inputToDynamicTextarea(driver, "addr", addressBeginWithSpace);

		log.info("TC_06_AddressCannotBeginWithSpaceCharacter - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Address"), "First character can not have space");
	}

	@Test
	public void TC_07_CityCannotBeBlank() {
		log.info("TC_07_CityCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_07_CityCannotBeBlank - STEP 02: Do not input to City textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, "city");

		log.info("TC_07_CityCannotBeBlank - STEP 03: Verify 'City Field must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "City Field must not be blank");
	}

	@Test
	public void TC_08_CityCannotContainNumber() {
		log.info("TC_08_CityCannotContainNumber - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_08_CityCannotContainNumber - STEP 02: Input to City textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "city", cityWithNumber);

		log.info("TC_08_CityCannotContainNumber - STEP 03: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "Numbers are not allowed");
	}

	@Test
	public void TC_09_CityCannotContainSpecialCharacter() {
		log.info("TC_09_CityCannotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_09_CityCannotContainSpecialCharacter - STEP 02: Input to City textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "city", cityWithSpecialCharacter);

		log.info("TC_09_CityCannotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "Special characters are not allowed");
	}

	@Test
	public void TC_10_CityCannotBeginWithSpaceCharacter() {
		log.info("TC_10_CityCannotBeginWithSpaceCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_10_CityCannotBeginWithSpaceCharacter - STEP 02: Input to City textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "city", cityBeginWithSpace);

		log.info("TC_10_CityCannotBeginWithSpaceCharacter - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "City"), "First character can not have space");
	}

	@Test
	public void TC_11_StateCannotBeBlank() {
		log.info("TC_11_StateCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_11_StateCannotBeBlank - STEP 02: Do not input to State textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, "state");

		log.info("TC_11_StateCannotBeBlank - STEP 03: Verify 'State must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "State must not be blank");
	}

	@Test
	public void TC_12_StateCannotContainNumber() {
		log.info("TC_12_StateCannotContainNumber - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_12_StateCannotContainNumber - STEP 02: Input to State textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "state", stateWithNumber);

		log.info("TC_12_StateCannotContainNumber - STEP 03: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "Numbers are not allowed");
	}

	@Test
	public void TC_13_StateCanNotContainSpecialCharacter() {
		log.info("TC_13_StateCanNotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_13_StateCanNotContainSpecialCharacter - STEP 02: Input to State textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "state", stateWithSpecialCharacter);

		log.info("TC_13_StateCanNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "Special characters are not allowed");
	}

	@Test
	public void TC_14_StateCanNotBeginWithSpace() {
		log.info("TC_14_StateCanNotBeginWithSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_14_StateCanNotBeginWithSpace - STEP 02: Input to State textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "state", stateBeginWithSpace);

		log.info("TC_14_StateCanNotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "State"), "First character can not have space");
	}

	@Test
	public void TC_15_PinCanNotBeBlank() {
		log.info("TC_15_PinCanNotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_15_PinCanNotBeBlank - STEP 02: Do not input to PIN textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, "pinno");

		log.info("TC_15_PinCanNotBeBlank - STEP 03: Verify 'PIN Code must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "PIN Code must not be blank");
	}

	@Test
	public void TC_16_PinCanNotContainCharacter() {
		log.info("TC_16_PinCanNotContainCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_16_PinCanNotContainCharacter - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithCharacter);

		log.info("TC_16_PinCanNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Characters are not allowed");
	}

	@Test
	public void TC_17_PinCannotHaveLessThan6Digits() {
		log.info("TC_17_PinCannotHaveLessThan6Digits - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_17_PinCannotHaveLessThan6Digits - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pinWithLessThan6Digits);

		log.info("TC_17_PinCannotHaveLessThan6Digits - STEP 03: Verify 'PIN Code must have 6 Digits' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "PIN Code must have 6 Digits");
	}

	@Test
	public void TC_18_PinCannotContainSpecialCharacter() {
		log.info("TC_18_PinCannotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_18_PinCannotContainSpecialCharacter - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pintWithSpecialCharacter);

		log.info("TC_18_PinCannotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Special characters are not allowed");
	}

	@Test
	public void TC_19_PinCannotBeginWithSpace() {
		log.info("TC_19_PinCannotBeginWithSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_19_PinCannotBeginWithSpace - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pinBeginWithSpace);

		log.info("TC_19_PinCannotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "First character can not have space");
	}

	@Test
	public void TC_20_PinCannotContainSpace() {
		log.info("TC_20_PinCannotContainSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_20_PinCannotContainSpace - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pintWithSpace);

		log.info("TC_20_PinCannotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "PIN"), "Characters are not allowed");
	}

	@Test
	public void TC_21_TelephoneCannotBeBlank() {
		log.info("TC_21_TelephoneCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_21_TelephoneCannotBeBlank - STEP 02: Do not input to Mobile Number textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, "telephoneno");

		log.info("TC_21_TelephoneCannotBeBlank - STEP 03: Verify 'Mobile no must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"), "Mobile no must not be blank");
	}

	@Test
	public void TC_22_TelephoneCannotBeginWithSpace() {
		log.info("TC_22_TelephoneCannotBeginWithSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_22_TelephoneCannotBeginWithSpace - STEP 02: Input to Mobile Number textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", telephoneBeginWithSpace);

		log.info("TC_22_TelephoneCannotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"), "First character can not have space");
	}

	@Test
	public void TC_23_TelephoneCannotContainSpace() {
		log.info("TC_23_TelephoneCannotContainSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_23_TelephoneCannotContainSpace - STEP 02: Input to Mobile Number textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", telephoneWithSpace);

		log.info("TC_23_TelephoneCannotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"), "Characters are not allowed");
	}

	@Test
	public void TC_24_TelephoneCannotContainSpecialCharacter() {
		log.info("TC_24_TelephoneCannotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_24_TelephoneCannotContainSpecialCharacter - STEP 02: Input to Mobile Number textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", telephoneWithSpecialCharacter);

		log.info("TC_24_TelephoneCannotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "Mobile Number"), "Special characters are not allowed");
	}

	@Test
	public void TC_25_EmailCannotBeBlank() {
		log.info("TC_25_EmailCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_25_EmailCannotBeBlank - STEP 02: Do not input to Email textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, "emailid");

		log.info("TC_25_EmailCannotBeBlank - STEP 03: Verify 'Email-ID must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "Email-ID must not be blank");
	}

	@Test
	public void TC_26_EmailCannotBeInvalidFormat() {
		log.info("TC_26_EmailCannotBeInvalidFormat - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_26_EmailCannotBeInvalidFormat - STEP 02: Input to Email textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", invalidEmail);

		log.info("TC_26_EmailCannotBeInvalidFormat - STEP 03: Verify 'Email-ID is not valid' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "Email-ID is not valid");
	}

	@Test
	public void TC_27_EmailCannotBeginWithSpace() {
		log.info("TC_27_EmailCannotBeginWithSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_27_EmailCannotBeginWithSpace - STEP 02: Input to Email textabox");
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", emailBeginWithSpace);

		log.info("TC_27_EmailCannotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, "E-mail"), "First character can not have space");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
