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

import bankguru.ValidationDataJson;
import commons.AbstractTest;
import commons.Constants;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;

public class Validation_04_NewAccount extends AbstractTest {
	WebDriver driver;
	String newAccountPageURL;
	LoginPageObject loginPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;
	ValidationDataJson validationData;

	@Parameters({"browser", "validationData"})
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
	public void TC_01_CustomerIDMustNotBeBlank() {
		log.info("TC_01_CustomerIDMustNotBeBlank - STEP 01: Open New Account page");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_ACCOUNT);
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));
		newAccountPageURL = newAccountPage.getNewAccountPageURL();

		log.info("TC_01_CustomerIDMustNotBeBlank STEP 02: Do not input a value in Customer ID field and press Tab key");
		newAccountPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD);

		log.info("TC_01_CustomerIDMustNotBeBlank STEP 03: Verify 'Customer ID is required' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_ID_FIELD_IN_NEW_ACCOUNT_PAGE), validationData.getCustomerIdBlankErrorMsg());
	}

	@Test
	public void TC_02_CustomerIDMustNotContainCharacter() {
		log.info("TC_02_CustomerIDMustNotContainCharacter - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_02_CustomerIDMustNotContainCharacter STEP 02: Input to Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, validationData.getCustomerIdContainCharacter());

		log.info("TC_02_CustomerIDMustNotContainCharacter STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_ID_FIELD_IN_NEW_ACCOUNT_PAGE), validationData.getCustomerIdContainCharacterErrorMsg());
	}

	@Test
	public void TC_03_CustomerIDMustNotContainSpecialCharacter() {
		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 01: Input to Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, validationData.getCustomerIdContainSpecialCharacter());

		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_ID_FIELD_IN_NEW_ACCOUNT_PAGE), validationData.getCustomerIdContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_04_CustomerIDMustNotContainSpace() {
		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 02: Input to Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, validationData.getCustomerIdContainSpace());

		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_ID_FIELD_IN_NEW_ACCOUNT_PAGE), validationData.getCustomerIdContainSpaceErrorMsg());
	}

	@Test
	public void TC_05_CustomerIDMustNotBeginWithSpace() {
		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 02: Input to Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, validationData.getCustomerIdBeginWithSpace());

		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CUSTOMER_ID_FIELD_IN_NEW_ACCOUNT_PAGE), validationData.getCustomerIdBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_06_InitialDepositMustNotBeBlank() {
		log.info("TC_06_InitialDepositMustNotBeBlank - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_06_InitialDepositMustNotBeBlank - STEP 02: Do not input a value in Initial Deposit field and press Tab key");
		newAccountPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD);

		log.info("TC_06_InitialDepositMustNotBeBlank - STEP 03: Verify 'Initial Deposit must not be blank' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_INITIAL_DEPOSIT_FIELD), validationData.getInitialDepositBlankErrorMsg());
	}

	@Test
	public void TC_07_InitialDepositMustNotContainCharacter() {
		log.info("TC_07_InitialDepositMustNotContainCharacter - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_07_InitialDepositMustNotContainCharacter - STEP 02: Input to Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD, validationData.getInitialDepositContainCharacter());

		log.info("TC_07_InitialDepositMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_INITIAL_DEPOSIT_FIELD), validationData.getInitialDepositContainCharacterErrorMsg());
	}

	@Test
	public void TC_08_InitialDepositMustNotContainSpecialCharacter() {
		log.info("TC_08_InitialDepositMustNotContainSpecialCharacter - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_08_InitialDepositMustNotContainSpecialCharacter - STEP 02: Input to Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD, validationData.getInitialDepositContainSpecialCharacter());

		log.info("TC_08_InitialDepositMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_INITIAL_DEPOSIT_FIELD), validationData.getInitialDepositContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_09_InitialDepositMustNotContainSpace() {
		log.info("TC_09_InitialDepositMustNotContainSpace - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_09_InitialDepositMustNotContainSpace - STEP 02: Input to Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD, validationData.getInitialDepositContainSpace());

		log.info("TC_09_InitialDepositMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_INITIAL_DEPOSIT_FIELD), validationData.getInitialDepositContainSpaceErrorMsg());
	}

	@Test
	public void TC_10_InitialDepositMustNotBeginWithSpace() {
		log.info("TC_10_InitialDepositMustNotBeginWithSpace - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_10_InitialDepositMustNotBeginWithSpace - STEP 02: Input to Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD, validationData.getInitialDepositBeginWithSpace());

		log.info("TC_10_InitialDepositMustNotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_INITIAL_DEPOSIT_FIELD), validationData.getInitialDepositBeginWithSpaceErrorMsg());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
