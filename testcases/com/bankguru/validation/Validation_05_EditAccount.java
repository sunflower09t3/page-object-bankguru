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
import bankguru.AccountDataJson;
import bankguru.CustomerDataJson;
import bankguru.ValidationDataJson;
import commons.AbstractTest;
import commons.Constants;
import commons.PageGeneratorManager;
import pageObjects.EditAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_05_EditAccount extends AbstractTest {
	WebDriver driver;
	String editAccountPageURL, newCustomerID, newAccountID, forthCustomerEmail;
	LoginPageObject loginPage;
	HomePageObject homePage;
	EditAccountPageObject editAccountPage;
	NewCustomerPageObject newCustomerPage;
	NewAccountPageObject newAccountPage;
	ValidationDataJson validationData;
	CustomerDataJson customerData;
	AccountDataJson accountData;

	@Parameters({"browser", "validationData", "customerData", "accountData"})
	@BeforeClass
	public void setup(String browserName, String validationDataFilePath, String customerDataFilePath, String accountDataFilePath) throws JsonParseException, JsonMappingException, IOException {
		validationData = ValidationDataJson.get(validationDataFilePath);
		customerData = CustomerDataJson.get(customerDataFilePath);
		accountData = AccountDataJson.get(accountDataFilePath);
		
		forthCustomerEmail = String.format(customerData.getForthCustomerEmail(), randomNumber());
		
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_USER_ID_FIELD, Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("TC_01_AccountNoMustNotBeBlank - STEP 01: Open Edit Account form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_ACCOUNT);
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));
		editAccountPageURL = editAccountPage.getEditAccountPageURL();

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 02: Do not input a value in Account No field and press Tab key");
		editAccountPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD);

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 03: Verify 'Account Number must not be blank' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoBlankErrorMsg());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 01: Open Edit Account form");
		editAccountPage.openURL(driver, editAccountPageURL);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 02: Input character into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainCharacter());

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainCharacterErrorMsg());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 01: Open Edit Account form");
		editAccountPage.openURL(driver, editAccountPageURL);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainSpecialCharacter());

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("TC_04_AccountNoMustNotContainSpace - STEP 01: Open Edit Account form");
		editAccountPage.openURL(driver, editAccountPageURL);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 02: Input a number which contains a space into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainSpace());

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainSpaceErrorMsg());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 01: Open Edit Account form");
		editAccountPage.openURL(driver, editAccountPageURL);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 02: Input first character as a space");
		editAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoBeginWithSpace());

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_06_validAccountNo() {
		log.info("TC_06_validAccountNo - STEP 01: Open New Customer page");
		editAccountPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_CUSTOMER);
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_06_validAccountNo - STEP 02: Input valid data to New Customer form");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD, customerData.getForthCustomerName());
		newCustomerPage.selectDynamicRadioButton(driver, customerData.getForthCustomerGender().substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DOB_FIELD, customerData.getForthCustomerDateOfBirth());
		newCustomerPage.inputToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD, customerData.getForthCustomerAddress());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, customerData.getForthCustomerCity());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, customerData.getForthCustomerState());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, customerData.getForthCustomerPin());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, customerData.getForthCustomerTelephone());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, forthCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, customerData.getForthCustomerPassword());

		log.info("TC_06_validAccountNo - STEP 03: Click Submit button");
		newCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_SUBMIT_BUTTON);
		newCustomerID = newCustomerPage.getDanymicDataInTable(driver, "Customer ID");

		log.info("TC_06_validAccountNo - STEP 04: Open New Account page");
		newCustomerPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_ACCOUNT);
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_06_validAccountNo - STEP 05: Fill in New Account page");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, newCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, accountData.getThirdAccountType(), "selaccount");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD, String.valueOf(accountData.getThirdAccountInitialDeposit()));

		log.info("TC_06_validAccountNo - STEP 06: Click Submit button");
		newAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_SUBMIT_BUTTON_IN_NEW_ACCOUNT_PAGE);
		newAccountID = newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_ID_ROW);

		log.info("TC_06_validAccountNo - STEP 07: Open Edit Account page");
		newAccountPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_ACCOUNT);
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));

		log.info("TC_06_validAccountNo - STEP 10: Input to Account No textbox");
		editAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, newAccountID);

		log.info("TC_06_validAccountNo - STEP 11: Click Submit button");
		editAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_06_validAccountNo - STEP 12: Verify Edit Account Entry form is displayed");
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_EDIT_ACCOUNT));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
