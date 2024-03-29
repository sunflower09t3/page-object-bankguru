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
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_09_FundTransfer extends AbstractTest {
	WebDriver driver;
	String fundTransferPageURL;
	LoginPageObject loginPage;
	HomePageObject homePage;
	FundTransferPageObject fundTransferPage;
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
	public void TC_01_PayerAccountNoMustNotBeBlank() {
		log.info("TC_01_PayerAccountNoMustNotBeBlank - STEP 01: Open Fund Transfer form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_FUND_TRANSFER);
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));
		fundTransferPageURL = fundTransferPage.getFundTransferPageURL();

		log.info("TC_01_PayerAccountNoMustNotBeBlank - STEP 02: Do not input a value in Payer Account No field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PAYER_ACC_FIELD);

		log.info("TC_01_PayerAccountNoMustNotBeBlank - STEP 03: Verify 'Payers Account Number must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PAYER_ACC_FIELD), validationData.getPayerAccountNoBlankErrorMsg());
	}

	@Test
	public void TC_02_PayerAccountNoMustNotContainCharacter() {
		log.info("TC_02_PayerAccountNoMustNotContainCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));

		log.info("TC_02_PayerAccountNoMustNotContainCharacter - STEP 02: Input character into Payer Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PAYER_ACC_FIELD, validationData.getPayerAccountNoContainCharacter());

		log.info("TC_02_PayerAccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PAYER_ACC_FIELD), validationData.getPayerAccountNoContainCharacterErrorMsg());
	}

	@Test
	public void TC_03_PayerAccountNoMustNotContainSpecialCharacter() {
		log.info("TC_03_PayerAccountNoMustNotContainSpecialCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));

		log.info("TC_03_PayerAccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Payer Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PAYER_ACC_FIELD, validationData.getPayerAccountNoContainSpecialCharacter());

		log.info("TC_03_PayerAccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PAYER_ACC_FIELD), validationData.getPayerAccountNoContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_04_PayeeAccountNoMustNotBeBlank() {
		log.info("TC_04_PayeeAccountNoMustNotBeBlank - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));
		
		log.info("TC_04_PayeeAccountNoMustNotBeBlank - STEP 02: Do not input a value in Payee Account No field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PAYEE_ACC_FIELD);

		log.info("TC_04_PayeeAccountNoMustNotBeBlank - STEP 03: Verify 'Payees Account Number must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PAYEE_ACC_FIELD), validationData.getPayeeAccountNoBlankErrorMsg());
	}

	@Test
	public void TC_05_PayeeAccountNoMustNotContainCharacter() {
		log.info("TC_05_PayeeAccountNoMustNotContainCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));
		
		log.info("TC_05_PayeeAccountNoMustNotContainCharacter - STEP 02: Input character into Payee Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PAYEE_ACC_FIELD, validationData.getPayeeAccountNoContainCharacter());

		log.info("TC_05_PayeeAccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PAYEE_ACC_FIELD), validationData.getPayeeAccountNoContainCharacterErrorMsg());
	}

	@Test
	public void TC_06_PayeeAccountNoMustNotContainSpecialCharacter() {
		log.info("TC_06_PayeeAccountNoMustNotContainSpecialCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));
		
		log.info("TC_06_PayeeAccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Payee Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PAYEE_ACC_FIELD, validationData.getPayeeAccountNoContainSpecialCharacter());

		log.info("TC_06_PayeeAccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_PAYEE_ACC_FIELD), validationData.getPayeeAccountNoContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_07_AmountMustNotBeBlank() {
		log.info("TC_07_AmountMustNotBeBlank - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));
		
		log.info("TC_07_AmountMustNotBeBlank - STEP 02: Do not input a value in Amount field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_AMOUNT_FIELD);

		log.info("TC_07_AmountMustNotBeBlank - STEP 03: Verify 'Amount field must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_AMOUNT_FIELD), validationData.getFundTransferAmountBlankErrorMsg());
	}

	@Test
	public void TC_08_AmountMustNotContainCharacter() {
		log.info("TC_08_AmountMustNotContainCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));
		
		log.info("TC_08_AmountMustNotContainCharacter - STEP 02: Input character into Amount field");
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_AMOUNT_FIELD, validationData.getFundTransferAmountContainCharacter());

		log.info("TC_08_AmountMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_AMOUNT_FIELD), validationData.getFundTransferAmountContainCharacterErrorMsg());
	}

	@Test
	public void TC_09_AmountMustNotContainSpecialCharacter() {
		log.info("TC_09_AmountMustNotContainSpecialCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));
		
		log.info("TC_09_AmountMustNotContainSpecialCharacter - STEP 02: Input special character into Amount field");
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_AMOUNT_FIELD, validationData.getFundTransferAmountContainSpecialCharacter());

		log.info("TC_09_AmountMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_AMOUNT_FIELD), validationData.getFundTransferAmountContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_10_DescriptionMustNotBeBlank() {
		log.info("TC_10_DescriptionMustNotBeBlank - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));
		
		log.info("TC_10_DescriptionMustNotBeBlank - STEP 02: Do not input a value in Description field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DESCRIPTION_FIELD);

		log.info("TC_10_DescriptionMustNotBeBlank - STEP 03: Verify 'Description can not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_DESCRIPTION_FIELD), validationData.getFundTransferDescriptionBlankErrorMsg());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
