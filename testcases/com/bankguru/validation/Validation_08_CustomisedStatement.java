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

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("TC_01_AccountNoMustNotBeBlank - STEP 01: Open Customised Statement form");
		homePage.openMultiplePage(driver, "Customised Statement");
		customisedStatementPage = PageGeneratorManager.getCustomisedStatementPage(driver);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));
		customisedStatementPageURL = customisedStatementPage.getCustomisedStatementPageURL();

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 02: Do not input a value in Account No field and press Tab key");
		customisedStatementPage.pressTabToDynamicTextbox(driver, "accountno");

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 03: Verify 'Account Number must not be blank' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Account Number must not be blank");
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 02: Input character into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, "accountno", validationData.getAccountNoContainCharacter());

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, "accountno", validationData.getAccountNoContainSpecialCharacter());

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("TC_04_AccountNoMustNotContainSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 02: Input a space into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, "accountno", validationData.getAccountNoContainSpace());

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 02: Input a space into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, "accountno", validationData.getAccountNoBeginWithSpace());

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_06_MinimumTransactionValueMustNotContainCharacter() {
		log.info("TC_06_MinimumTransactionValueMustNotContainCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_06_MinimumTransactionValueMustNotContainCharacter - STEP 02: Input character into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, "amountlowerlimit", validationData.getMinimumTransactionValueContainCharacter());

		log.info("Validate Minimum Transaction Value field with blank value - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Minimum Transaction Value"), "Characters are not allowed");
	}

	@Test
	public void TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter() {
		log.info("TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter - STEP 02: Input special character into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, "amountlowerlimit", validationData.getMinimumTransactionValueContainSpecialCharacter());

		log.info("TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Minimum Transaction Value"), "Special characters are not allowed");
	}

	@Test
	public void TC_08_MinimumTransactionValueMustNotContainSpace() {
		log.info("TC_08_MinimumTransactionValueMustNotContainSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_08_MinimumTransactionValueMustNotContainSpace - STEP 02: Input a number which contains a space into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, "amountlowerlimit", validationData.getMinimumTransactionValueContainSpace());

		log.info("TC_08_MinimumTransactionValueMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Minimum Transaction Value"), "Characters are not allowed");
	}

	@Test
	public void TC_09_MinimumTransactionValueoMustNotBeginWithSpace() {
		log.info("TC_09_MinimumTransactionValueoMustNotBeginWithSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_09_MinimumTransactionValueoMustNotBeginWithSpace - STEP 02: Input first character as a space");
		customisedStatementPage.inputToDynamicTextbox(driver, "amountlowerlimit", validationData.getMinimumTransactionValueBeginWithSpace());

		log.info("TC_09_MinimumTransactionValueoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Minimum Transaction Value"), "Characters are not allowed");
	}

	@Test
	public void TC_10_NumberOfTransactionMustNotContainCharacter() {
		log.info("TC_10_NumberOfTransactionMustNotContainCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_10_NumberOfTransactionMustNotContainCharacter - STEP 02: Input character into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, "numtransaction", validationData.getNumOfTransactionContainCharacter());

		log.info("Validate Number of Transaction field with blank value - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Number of Transaction"), "Characters are not allowed");
	}

	@Test
	public void TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter() {
		log.info("TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter - STEP 02: Input special character into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, "numtransaction", validationData.getNumOfTransactionContainSpecialCharacter());

		log.info("TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Number of Transaction"), "Special characters are not allowed");
	}

	@Test
	public void TC_12_NumberOfTransactionValueMustNotContainSpace() {
		log.info("TC_12_NumberOfTransactionValueMustNotContainSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_12_NumberOfTransactionValueMustNotContainSpace - STEP 02: Input a number which contains a space into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, "numtransaction", validationData.getNumOfTransactionContainSpace());

		log.info("TC_12_NumberOfTransactionValueMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Number of Transaction"), "Characters are not allowed");
	}

	@Test
	public void TC_13_NumberOfTransactionValueoMustNotBeginWithSpace() {
		log.info("TC_13_NumberOfTransactionValueoMustNotBeginWithSpace - STEP 01: Open Customised Statement form");
		customisedStatementPage.openURL(driver, customisedStatementPageURL);
		verifyTrue(customisedStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customized Statement Form"));

		log.info("TC_13_NumberOfTransactionValueoMustNotBeginWithSpace - STEP 02: Input first character as a space");
		customisedStatementPage.inputToDynamicTextbox(driver, "numtransaction", validationData.getNumOfTransactionBeginWithSpace());

		log.info("TC_13_NumberOfTransactionValueoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Number of Transaction"), "Characters are not allowed");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
