package com.bankguru.validation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import bankguru.CustomerDataJson;
import bankguru.ValidationDataJson;
import commons.AbstractTest;
import commons.Constants;
import commons.PageGeneratorManager;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_02_EditCustomer extends AbstractTest {
	WebDriver driver;
	String editCustomerPageURL, newCustomerID, thirdCustomerEmail;
	HomePageObject homePage;
	LoginPageObject loginPage;
	EditCustomerPageObject editCustomerPage;
	NewCustomerPageObject newCustomerPage;
	ValidationDataJson validationData;
	CustomerDataJson customerData;

	@Parameters({"browser", "validationData", "customerData"})
	@BeforeClass
	public void setup(String browserName, String validationDataFilePath, String customerDataFilePath) throws JsonParseException, JsonMappingException, IOException {
		validationData = ValidationDataJson.get(validationDataFilePath);
		customerData = CustomerDataJson.get(customerDataFilePath);
		
		thirdCustomerEmail = String.format(customerData.getThirdCustomerEmail(), randomNumber());
		
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_USER_ID_FIELD, Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_CustomerIDCannotBeBlank() {
		log.info("TC_01_CustomerIDCannotBeBlank - STEP 01: Open Edit Customer page");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_CUSTOMER);
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));
		editCustomerPageURL = editCustomerPage.getEditCustomerPageURL();

		log.info("TC_01_CustomerIDCannotBeBlank - STEP 02: Do not input a value in Customer ID field and press Tab key");
		editCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD);

		log.info("TC_01_CustomerIDCannotBeBlank - STEP 03: Verify 'Customer ID is required' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_ID_FIELD), validationData.getCustomerIdBlankErrorMsg());
	}

	@Test
	public void TC_02_CustomerIDCannotContainCharacter() {
		log.info("TC_02_CustomerIDCannotContainCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_02_CustomerIDCannotContainCharacter - STEP 02: Input to Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, validationData.getCustomerIdContainCharacter());

		log.info("TC_02_CustomerIDCannotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_ID_FIELD), validationData.getCustomerIdContainCharacterErrorMsg());
	}

	@Test
	public void TC_03_CustomerIDCannotContainSpecialCharacter() {
		log.info("TC_03_CustomerIDCannotContainSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_03_CustomerIDCannotContainSpecialCharacter STEP 02: Input to Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, validationData.getCustomerIdContainSpecialCharacter());

		log.info("TC_03_CustomerIDCannotContainSpecialCharacter STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_ID_FIELD), validationData.getCustomerIdContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_04_ValidCustomerID() {
		log.info("TC_04_ValidCustomerID - STEP 01: Open New Customer page");
		editCustomerPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_CUSTOMER);
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_04_ValidCustomerID - STEP 02: Input valid data to Add New Customer form");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD, customerData.getThirdCustomerName());
		newCustomerPage.selectDynamicRadioButton(driver, customerData.getThirdCustomerGender().substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DOB_FIELD, customerData.getThirdCustomerDateOfBirth());
		newCustomerPage.inputToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD, customerData.getThirdCustomerAddress());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, customerData.getThirdCustomerCity());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, customerData.getThirdCustomerState());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, customerData.getThirdCustomerPin());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, thirdCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, customerData.getThirdCustomerTelephone());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, customerData.getThirdCustomerPassword());

		log.info("TC_04_ValidCustomerID - STEP 03: Click Submit button");
		newCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_SUBMIT_BUTTON);
		newCustomerID = newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_ID_ROW);

		log.info("TC_04_ValidCustomerID - STEP 04: Open Edit Customer page");
		newCustomerPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_CUSTOMER);
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_04_ValidCustomerID - STEP 05: Input ID of the newly created customer into Customer ID field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_04_ValidCustomerID - STEP 06: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_04_ValidCustomerID - STEP 07: Verify Edit Customer Entry page is displayed");
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_EDIT_CUSTOMER));
	}

	@Test
	public void TC_05_AddressCannotBeBlank() {
		log.info("TC_05_AddressCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_05_AddressCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_05_AddressCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_05_AddressCannotBeBlank - STEP 04: Do not input a value into Address field and press Tab key");
		editCustomerPage.inputToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD, "");
		editCustomerPage.pressTabToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD);

		log.info("TC_05_AddressCannotBeBlank - STEP 05: Verify 'Address Field must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ADDRESS_FIELD), validationData.getAddressBlankErrorMsg());
	}

	@Test
	public void TC_06_CityCannotBeBlank() {
		log.info("TC_06_CityCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_06_CityCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_06_CityCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_06_CityCannotBeBlank - STEP 04: Do not input a value into City field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, "");
		editCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD);

		log.info("TC_06_CityCannotBeBlank - STEP 05: Verify 'City Field must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CITY_FIELD), validationData.getCityBlankErrorMsg());
	}

	@Test
	public void TC_07_CityCannotContainNumber() {
		log.info("TC_07_CityCannotContainNumber - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_07_CityCannotContainNumber - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_07_CityCannotContainNumber - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_07_CityCannotContainNumber - STEP 04: Input to City field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, validationData.getCityContainNumber());

		log.info("TC_07_CityCannotContainNumber - STEP 05: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CITY_FIELD), validationData.getCityContainNumberErrorMsg());
	}

	@Test
	public void TC_08_CityCannotContainSpecialCharacter() {
		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 04: Input to City field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, validationData.getCityContainSpecialCharacter());

		log.info("TC_08_CityCannotContainSpecialCharacter - STEP 05: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CITY_FIELD), validationData.getCityContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_09_StateCannotBeBlank() {
		log.info("TC_09_StateCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_09_StateCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_09_StateCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_09_StateCannotBeBlank - STEP 04: Do not input a value into State field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, "");
		editCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD);

		log.info("TC_09_StateCannotBeBlank - STEP 05: Verify 'State must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_STATE_FIELD), validationData.getStateBlankErrorMsg());
	}

	@Test
	public void TC_10_StateCannotContainNumber() {
		log.info("TC_10_StateCannotContainNumber - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_10_StateCannotContainNumber - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_10_StateCannotContainNumber - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_10_StateCannotContainNumber - STEP 04: Input to State field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, validationData.getStateContainNumber());

		log.info("TC_10_StateCannotContainNumber - STEP 05: Verify 'Numbers are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_STATE_FIELD), validationData.getStateContainNumberErrorMsg());
	}

	@Test
	public void TC_11_StateCannotContainSpecialCharacter() {
		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 04: Input to State field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, validationData.getStateContainSpecialCharacter());

		log.info("TC_11_StateCannotContainSpecialCharacter - STEP 05: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_STATE_FIELD), validationData.getStateContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_12_PinCannotContainCharacter() {
		log.info("TC_12_PinCannotContainCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_12_PinCannotContainCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_12_PinCannotContainCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_12_PinCannotContainCharacter - STEP 04: Input to Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, validationData.getPinContainCharacter());

		log.info("TC_12_PinCannotContainCharacter - STEP 05: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinContainCharacterErrorMsg());
	}

	@Test
	public void TC_13_PinCannotBeBlank() {
		log.info("TC_13_PinCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_13_PinCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_13_PinCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_13_PinCannotBeBlank - STEP 04: Do not input a value into Pin field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, "");
		editCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD);

		log.info("TC_13_PinCannotBeBlank - STEP 05: Verify 'PIN Code must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinBlankErrorMsg());
	}

	@Test
	public void TC_14_PinCannotLessThan6Digits() {
		log.info("TC_14_PinCannotLessThan6Digits - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_14_PinCannotLessThan6Digits - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_14_PinCannotLessThan6Digits - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_14_PinCannotLessThan6Digits - STEP 04: Input to Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, validationData.getPinWithLessThan6Digits());

		log.info("TC_14_PinCannotLessThan6Digits - STEP 05: Verify 'PIN Code must have 6 Digits' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinWithLessThan6DigitsErrorMsg());
	}

	@Test
	public void TC_15_PinCannotHaveSpecialCharacter() {
		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 04: Input to Pin field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, validationData.getPinContainSpecialCharacter());

		log.info("TC_15_PinCannotHaveSpecialCharacter - STEP 05: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PIN_FIELD), validationData.getPinContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_16_TelephoneCannotBeBlank() {
		log.info("TC_16_TelephoneCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_16_TelephoneCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_16_TelephoneCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_16_TelephoneCannotBeBlank - STEP 04: Do not input a value into Telephone field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, "");
		editCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD);

		log.info("TC_16_TelephoneCannotBeBlank - STEP 05: Verify 'Mobile no must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MOBILE_FIELD), validationData.getTelephoneBlankErrorMsg());
	}

	@Test
	public void TC_17_TelephoneCannotContainSpecialCharacter() {
		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 04: Input to Telephone field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, validationData.getTelephoneContainSpecialCharacter());

		log.info("TC_17_TelephoneCannotContainSpecialCharacter - STEP 05: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MOBILE_FIELD), validationData.getTelephoneContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_18_EmailCannotBeBlank() {
		log.info("TC_18_EmailCannotBeBlank - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_18_EmailCannotBeBlank - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_18_EmailCannotBeBlank - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_18_EmailCannotBeBlank - STEP 04: Do not input a value into Email field and press Tab key");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, "");
		editCustomerPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD);

		log.info("TC_18_EmailCannotBeBlank - STEP 05: Verify 'Email-ID must not be blank' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_EMAIL_FIELD), validationData.getEmailBlankErrorMsg());
	}

	@Test
	public void TC_19_EmaitCannotBeInvalidFormat() {
		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 01: Open Edit Customer page");
		editCustomerPage.openURL(driver, editCustomerPageURL);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 02: Input to Customer ID textbox");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);

		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 03: Click Submit button");
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 04: Input to Email field");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, validationData.getInvalidEmail());

		log.info("TC_19_EmaitCannotBeInvalidFormat - STEP 05: Verify 'Email-ID is not valid' message is displayed");
		verifyEquals(editCustomerPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_EMAIL_FIELD), validationData.getInvalidEmailErrorMsg());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}
}
