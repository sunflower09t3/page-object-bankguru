package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import bankguru.ValidationData;
import commons.AbstractTest;
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

	@Parameters("browser")
	@BeforeClass
	public void setup(String browserName) {
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_PayerAccountNoMustNotBeBlank() {
		log.info("TC_01_PayerAccountNoMustNotBeBlank - STEP 01: Open Fund Transfer form");
		homePage.openMultiplePage(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));
		fundTransferPageURL = fundTransferPage.getFundTransferPageURL();

		log.info("TC_01_PayerAccountNoMustNotBeBlank - STEP 02: Do not input a value in Payer Account No field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, "payersaccount");

		log.info("TC_01_PayerAccountNoMustNotBeBlank - STEP 03: Verify 'Payers Account Number must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payers account no"), "Payers Account Number must not be blank");
	}

	@Test
	public void TC_02_PayerAccountNoMustNotContainCharacter() {
		log.info("TC_02_PayerAccountNoMustNotContainCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));

		log.info("TC_02_PayerAccountNoMustNotContainCharacter - STEP 02: Input character into Payer Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, "payersaccount", ValidationData.FundTransfer.PAYER_ACCOUNT_NO_CONTAIN_CHARACTER);

		log.info("TC_02_PayerAccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payers account no"), "Characters are not allowed");
	}

	@Test
	public void TC_03_PayerAccountNoMustNotContainSpecialCharacter() {
		log.info("TC_03_PayerAccountNoMustNotContainSpecialCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));

		log.info("TC_03_PayerAccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Payer Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, "payersaccount", ValidationData.FundTransfer.PAYER_ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER);

		log.info("TC_03_PayerAccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payers account no"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_PayeeAccountNoMustNotBeBlank() {
		log.info("TC_04_PayeeAccountNoMustNotBeBlank - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));
		
		log.info("TC_04_PayeeAccountNoMustNotBeBlank - STEP 02: Do not input a value in Payee Account No field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, "payeeaccount");

		log.info("TC_04_PayeeAccountNoMustNotBeBlank - STEP 03: Verify 'Payees Account Number must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payees account no"), "Payees Account Number must not be blank");
	}

	@Test
	public void TC_05_PayeeAccountNoMustNotContainCharacter() {
		log.info("TC_05_PayeeAccountNoMustNotContainCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));
		
		log.info("TC_05_PayeeAccountNoMustNotContainCharacter - STEP 02: Input character into Payee Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, "payeeaccount", ValidationData.FundTransfer.PAYEE_ACCOUNT_NO_CONTAIN_CHARACTER);

		log.info("TC_05_PayeeAccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payees account no"), "Characters are not allowed");
	}

	@Test
	public void TC_06_PayeeAccountNoMustNotContainSpecialCharacter() {
		log.info("TC_06_PayeeAccountNoMustNotContainSpecialCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));
		
		log.info("TC_06_PayeeAccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Payee Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, "payeeaccount", ValidationData.FundTransfer.PAYEE_ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER);

		log.info("TC_06_PayeeAccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payees account no"), "Special characters are not allowed");
	}

	@Test
	public void TC_07_AmountMustNotBeBlank() {
		log.info("TC_07_AmountMustNotBeBlank - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));
		
		log.info("TC_07_AmountMustNotBeBlank - STEP 02: Do not input a value in Amount field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, "ammount");

		log.info("TC_07_AmountMustNotBeBlank - STEP 03: Verify 'Amount field must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Amount"), "Amount field must not be blank");
	}

	@Test
	public void TC_08_AmountMustNotContainCharacter() {
		log.info("TC_08_AmountMustNotContainCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));
		
		log.info("TC_08_AmountMustNotContainCharacter - STEP 02: Input character into Amount field");
		fundTransferPage.inputToDynamicTextbox(driver, "ammount", ValidationData.FundTransfer.AMOUNT_CONTAIN_CHARACTER);

		log.info("TC_08_AmountMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Amount"), "Characters are not allowed");
	}

	@Test
	public void TC_09_AmountMustNotContainSpecialCharacter() {
		log.info("TC_09_AmountMustNotContainSpecialCharacter - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));
		
		log.info("TC_09_AmountMustNotContainSpecialCharacter - STEP 02: Input special character into Amount field");
		fundTransferPage.inputToDynamicTextbox(driver, "ammount", ValidationData.FundTransfer.AMOUNT_CONTAIN_SPECIAL_CHARACTER);

		log.info("TC_09_AmountMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Amount"), "Special characters are not allowed");
	}

	@Test
	public void TC_10_DescriptionMustNotBeBlank() {
		log.info("TC_10_DescriptionMustNotBeBlank - STEP 01: Open Fund Transfer form");
		fundTransferPage.openURL(driver, fundTransferPageURL);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));
		
		log.info("TC_10_DescriptionMustNotBeBlank - STEP 02: Do not input a value in Description field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, "desc");

		log.info("TC_10_DescriptionMustNotBeBlank - STEP 03: Verify 'Description can not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Description"), "Description can not be blank");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
