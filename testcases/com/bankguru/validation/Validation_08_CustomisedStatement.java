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
import pageObjects.CustomisedStatementPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_08_CustomisedStatement extends AbstractTest {
	WebDriver driver;
	String customisedStatementPageURL;
	LoginPageObject loginPage;
	HomePageObject homePage;
	CustomisedStatementPageObject customisedStatementPage;
	ValidationDataJson validationData;

	@Parameters({"browser", "validationData"})
	@BeforeClass
	public void setup(String browserName, String validationDataFilePath) throws JsonParseException, JsonMappingException, IOException{
		validationData = ValidationDataJson.get(validationDataFilePath);
		
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_USER_ID_FIELD, Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("TC_01_AccountNoMustNotBeBlank - STEP 01: Open Customised Statement form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_CUSTOMISED_STATEMENT);
		customisedStatementPage = PageGeneratorManager.getCustomisedStatementPage(driver);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));
		customisedStatementPageURL = customisedStatementPage.getCustomisedStatementPageURL();

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 02: Do not input a value in Account No field and press Tab key");
		customisedStatementPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD);

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 03: Verify 'Account Number must not be blank' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoBlankErrorMsg());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 02: Input character into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainCharacter());

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainCharacterErrorMsg());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainSpecialCharacter());

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("TC_04_AccountNoMustNotContainSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 02: Input a space into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainSpace());

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainSpaceErrorMsg());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 02: Input a space into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoBeginWithSpace());

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_06_MinimumTransactionValueMustNotContainCharacter() {
		log.info("TC_06_MinimumTransactionValueMustNotContainCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_06_MinimumTransactionValueMustNotContainCharacter - STEP 02: Input character into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MINIMUM_TRANSACTION_FIELD, validationData.getMinimumTransactionValueContainCharacter());

		log.info("Validate Minimum Transaction Value field with blank value - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MINIMUM_TRANSACTION_FIELD), validationData.getMinimumTransactionValueContainCharacterErrorMsg());
	}

	@Test
	public void TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter() {
		log.info("TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter - STEP 02: Input special character into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MINIMUM_TRANSACTION_FIELD, validationData.getMinimumTransactionValueContainSpecialCharacter());

		log.info("TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MINIMUM_TRANSACTION_FIELD), validationData.getMinimumTransactionValueContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_08_MinimumTransactionValueMustNotContainSpace() {
		log.info("TC_08_MinimumTransactionValueMustNotContainSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_08_MinimumTransactionValueMustNotContainSpace - STEP 02: Input a number which contains a space into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MINIMUM_TRANSACTION_FIELD, validationData.getMinimumTransactionValueContainSpace());

		log.info("TC_08_MinimumTransactionValueMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MINIMUM_TRANSACTION_FIELD), validationData.getMinimumTransactionValueContainSpaceErrorMsg());
	}

	@Test
	public void TC_09_MinimumTransactionValueoMustNotBeginWithSpace() {
		log.info("TC_09_MinimumTransactionValueoMustNotBeginWithSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_09_MinimumTransactionValueoMustNotBeginWithSpace - STEP 02: Input first character as a space");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MINIMUM_TRANSACTION_FIELD, validationData.getMinimumTransactionValueBeginWithSpace());

		log.info("TC_09_MinimumTransactionValueoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_MINIMUM_TRANSACTION_FIELD), validationData.getMinimumTransactionValueBeginWithSpaceErrorMsg());
	}

	@Test
	public void TC_10_NumberOfTransactionMustNotContainCharacter() {
		log.info("TC_10_NumberOfTransactionMustNotContainCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_10_NumberOfTransactionMustNotContainCharacter - STEP 02: Input character into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_TRANSACTION_NUMBER, validationData.getNumOfTransactionContainCharacter());

		log.info("Validate Number of Transaction field with blank value - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_TRANSACTION_NUMBER_FIELD), validationData.getNumOfTransactionContainCharacterErrorMsg());
	}

	@Test
	public void TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter() {
		log.info("TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter - STEP 02: Input special character into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_TRANSACTION_NUMBER, validationData.getNumOfTransactionContainSpecialCharacter());

		log.info("TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_TRANSACTION_NUMBER_FIELD), validationData.getNumOfTransactionContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_12_NumberOfTransactionValueMustNotContainSpace() {
		log.info("TC_12_NumberOfTransactionValueMustNotContainSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_12_NumberOfTransactionValueMustNotContainSpace - STEP 02: Input a number which contains a space into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_TRANSACTION_NUMBER, validationData.getNumOfTransactionContainSpace());

		log.info("TC_12_NumberOfTransactionValueMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_TRANSACTION_NUMBER_FIELD), validationData.getNumOfTransactionContainSpaceErrorMsg());
	}

	@Test
	public void TC_13_NumberOfTransactionValueoMustNotBeginWithSpace() {
		log.info("TC_13_NumberOfTransactionValueoMustNotBeginWithSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CUSTOMISED_STATEMENT));

		log.info("TC_13_NumberOfTransactionValueoMustNotBeginWithSpace - STEP 02: Input first character as a space");
		customisedStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_TRANSACTION_NUMBER, validationData.getNumOfTransactionBeginWithSpace());

		log.info("TC_13_NumberOfTransactionValueoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_TRANSACTION_NUMBER_FIELD), validationData.getNumOfTransactionBeginWithSpaceErrorMsg());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
