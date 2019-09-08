package com.bankguru.validation;

import java.io.IOException;

import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import bankguru.ValidationDataJson;
import commons.AbstractTest;
import commons.Constants;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_01_NewCustomer extends AbstractTest {
	WebDriver driver;
	String newCustomerPageURL;
	ValidationDataJson validationData;
	LoginPageObject loginPage;
	NewCustomerPageObject newCustomerPage;
	HomePageObject homePage;

	@Parameters({ "browser", "validationData" })
	@BeforeClass
	public void setup(String browserName, String validationDataFilePath) throws JsonParseException, JsonMappingException, IOException {
		validationData = ValidationDataJson.get(validationDataFilePath);

		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_USER_ID_FIELD, Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();
	}

	@Test
	public void TC_01_CustomerNameCannotBeBlank() {
		log.info("TC_01_CustomerNameCannotBeBlank - STEP 01: Open New Customer page");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_CUSTOMER);
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));
		newCustomerPageURL = newCustomerPage.getNewCustomerPageURL();
		
		log.info("TC_01_CustomerNameCannotBeBlank - STEP 02: Do not input to Customer Name textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD);

		log.info("TC_01_CustomerNameCannotBeBlank - STEP 03: Verify 'Customer name must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_NAME_FIELD), validationData.getCustomerNameBlankErrorMsg());
	}

	@Test
	public void TC_02_CustomerNameCannotContainNumber() {
		log.info("TC_02_CustomerNameCannotContainNumber - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_02_CustomerNameCannotContainNumber - STEP 02: Input to Customer Name textbox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD, validationData.getCustomerNameContainNumber());

		log.info("TC_02_CustomerNameCannotContainNumber - STEP 03: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_NAME_FIELD), validationData.getCustomerNameContainNumberErrorMsg());
	}

	@Test
	public void TC_03_CustomerNameCannotContainSpecialCharacter() {
		log.info("TC_03_CustomerNameCannotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_03_CustomerNameCannotContainSpecialCharacter - STEP 02: Input to Customer Name textbox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD, validationData.getCustomerNameContainSpecialCharacter());

		log.info("TC_03_CustomerNameCannotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_NAME_FIELD),
				validationData.getCustomerNameContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_04_CustomerNameCannotBeginWithSpaceCharacter() {
		log.info("TC_04_CustomerNameCannotBeginWithSpaceCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_04_CustomerNameCannotBeginWithSpaceCharacter - STEP 02: Input to Customer Name textbox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD, validationData.getCustomerNameBeginWithSpace());

		log.info("TC_04_CustomerNameCannotBeginWithSpaceCharacter - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_NAME_FIELD), validationData.getCustomerNameBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_05_AddressCannotBeBlank() {
		log.info("TC_05_AddressCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_05_AddressCannotBeBlank - STEP 02: Do not input to Address textarea and press Tab");
		newCustomerPage.pressTabToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD);

		log.info("TC_05_AddressCannotBeBlank - STEP 03: Verify 'Address Field must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ADDRESS_FIELD), validationData.getAddressBlankErrorMsg());
	}

	@Test
	public void TC_06_AddressCannotBeginWithSpaceCharacter() {
		log.info("TC_06_AddressCannotBeginWithSpaceCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_06_AddressCannotBeginWithSpaceCharacter - STEP 02: Input to Address textarea");
		newCustomerPage.inputToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD, validationData.getAddressBeginWithSpace());

		log.info("TC_06_AddressCannotBeginWithSpaceCharacter - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ADDRESS_FIELD), validationData.getAddressBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_07_CityCannotBeBlank() {
		log.info("TC_07_CityCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_07_CityCannotBeBlank - STEP 02: Do not input to City textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD);

		log.info("TC_07_CityCannotBeBlank - STEP 03: Verify 'City Field must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CITY_FIELD), validationData.getCityBlankErrorMsg());
	}

	@Test
	public void TC_08_CityCannotContainNumber() {
		log.info("TC_08_CityCannotContainNumber - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_08_CityCannotContainNumber - STEP 02: Input to City textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, validationData.getCityContainNumber());

		log.info("TC_08_CityCannotContainNumber - STEP 03: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CITY_FIELD), validationData.getCityContainNumberErrorMsg());
	}

	@Test
	public void TC_09_CityCannotContainSpecialCharacter() {
		log.info("TC_09_CityCannotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_09_CityCannotContainSpecialCharacter - STEP 02: Input to City textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, validationData.getCityContainSpecialCharacter());

		log.info("TC_09_CityCannotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CITY_FIELD), validationData.getCityContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_10_CityCannotBeginWithSpaceCharacter() {
		log.info("TC_10_CityCannotBeginWithSpaceCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_10_CityCannotBeginWithSpaceCharacter - STEP 02: Input to City textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, validationData.getCityBeginWithSpace());

		log.info("TC_10_CityCannotBeginWithSpaceCharacter - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CITY_FIELD), validationData.getCityBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_11_StateCannotBeBlank() {
		log.info("TC_11_StateCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_11_StateCannotBeBlank - STEP 02: Do not input to State textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD);

		log.info("TC_11_StateCannotBeBlank - STEP 03: Verify 'State must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_STATE_FIELD), validationData.getStateBlankErrorMsg());
	}

	@Test
	public void TC_12_StateCannotContainNumber() {
		log.info("TC_12_StateCannotContainNumber - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_12_StateCannotContainNumber - STEP 02: Input to State textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, validationData.getStateContainNumber());

		log.info("TC_12_StateCannotContainNumber - STEP 03: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_STATE_FIELD), validationData.getStateContainNumberErrorMsg());
	}

	@Test
	public void TC_13_StateCanNotContainSpecialCharacter() {
		log.info("TC_13_StateCanNotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_13_StateCanNotContainSpecialCharacter - STEP 02: Input to State textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, validationData.getStateContainSpecialCharacter());

		log.info("TC_13_StateCanNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_STATE_FIELD), validationData.getStateContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_14_StateCanNotBeginWithSpace() {
		log.info("TC_14_StateCanNotBeginWithSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_14_StateCanNotBeginWithSpace - STEP 02: Input to State textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, validationData.getStateBeginWithSpace());

		log.info("TC_14_StateCanNotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_STATE_FIELD), validationData.getStateBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_15_PinCanNotBeBlank() {
		log.info("TC_15_PinCanNotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_15_PinCanNotBeBlank - STEP 02: Do not input to PIN textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD);

		log.info("TC_15_PinCanNotBeBlank - STEP 03: Verify 'PIN Code must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinBlankErrorMsg());
	}

	@Test
	public void TC_16_PinCanNotContainCharacter() {
		log.info("TC_16_PinCanNotContainCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_16_PinCanNotContainCharacter - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, validationData.getPinContainCharacter());

		log.info("TC_16_PinCanNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinContainCharacterErrorMsg());
	}

	@Test
	public void TC_17_PinCannotHaveLessThan6Digits() {
		log.info("TC_17_PinCannotHaveLessThan6Digits - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_17_PinCannotHaveLessThan6Digits - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, validationData.getPinWithLessThan6Digits());

		log.info("TC_17_PinCannotHaveLessThan6Digits - STEP 03: Verify 'PIN Code must have 6 Digits' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinWithLessThan6DigitsErrorMsg());
	}

	@Test
	public void TC_18_PinCannotContainSpecialCharacter() {
		log.info("TC_18_PinCannotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_18_PinCannotContainSpecialCharacter - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, validationData.getPinContainSpecialCharacter());

		log.info("TC_18_PinCannotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_19_PinCannotBeginWithSpace() {
		log.info("TC_19_PinCannotBeginWithSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_19_PinCannotBeginWithSpace - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, validationData.getPinBeginWithSpace());

		log.info("TC_19_PinCannotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_20_PinCannotContainSpace() {
		log.info("TC_20_PinCannotContainSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_20_PinCannotContainSpace - STEP 02: Input to Pin textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, validationData.getPinContainSpace());

		log.info("TC_20_PinCannotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinContainSpaceErrorMsg());
	}

	@Test
	public void TC_21_TelephoneCannotBeBlank() {
		log.info("TC_21_TelephoneCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_21_TelephoneCannotBeBlank - STEP 02: Do not input to Mobile Number textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD);

		log.info("TC_21_TelephoneCannotBeBlank - STEP 03: Verify 'Mobile no must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MOBILE_FIELD), validationData.getTelephoneBlankErrorMsg());
	}

	@Test
	public void TC_22_TelephoneCannotBeginWithSpace() {
		log.info("TC_22_TelephoneCannotBeginWithSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_22_TelephoneCannotBeginWithSpace - STEP 02: Input to Mobile Number textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, validationData.getTelephoneBeginWithSpace());

		log.info("TC_22_TelephoneCannotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MOBILE_FIELD), validationData.getTelephoneBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_23_TelephoneCannotContainSpace() {
		log.info("TC_23_TelephoneCannotContainSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_23_TelephoneCannotContainSpace - STEP 02: Input to Mobile Number textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, validationData.getTelephoneContainSpace());

		log.info("TC_23_TelephoneCannotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MOBILE_FIELD), validationData.getTelephoneContainSpaceErrorMsg());
	}

	@Test
	public void TC_24_TelephoneCannotContainSpecialCharacter() {
		log.info("TC_24_TelephoneCannotContainSpecialCharacter - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_24_TelephoneCannotContainSpecialCharacter - STEP 02: Input to Mobile Number textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, validationData.getTelephoneContainSpecialCharacter());

		log.info("TC_24_TelephoneCannotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MOBILE_FIELD),
				validationData.getTelephoneContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_25_EmailCannotBeBlank() {
		log.info("TC_25_EmailCannotBeBlank - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_25_EmailCannotBeBlank - STEP 02: Do not input to Email textbox and press Tab");
		newCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD);

		log.info("TC_25_EmailCannotBeBlank - STEP 03: Verify 'Email-ID must not be blank' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_EMAIL_FIELD), validationData.getEmailBlankErrorMsg());
	}

	@Test
	public void TC_26_EmailCannotBeInvalidFormat() {
		log.info("TC_26_EmailCannotBeInvalidFormat - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_26_EmailCannotBeInvalidFormat - STEP 02: Input to Email textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, validationData.getInvalidEmail());

		log.info("TC_26_EmailCannotBeInvalidFormat - STEP 03: Verify 'Email-ID is not valid' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_EMAIL_FIELD), validationData.getInvalidEmailErrorMsg());
	}

	@Test
	public void TC_27_EmailCannotBeginWithSpace() {
		log.info("TC_27_EmailCannotBeginWithSpace - STEP 01: Open New Customer page");
		newCustomerPage.openURL(driver, newCustomerPageURL);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_27_EmailCannotBeginWithSpace - STEP 02: Input to Email textabox");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, validationData.getEmailBeginWithSpace());

		log.info("TC_27_EmailCannotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_EMAIL_FIELD), validationData.getEmailBeginWithSpaceErrorMsg());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
