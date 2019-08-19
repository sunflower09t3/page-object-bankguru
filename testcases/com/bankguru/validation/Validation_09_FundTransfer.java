package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_09_FundTransfer extends AbstractTest {
	WebDriver driver;
	String payerAccountNoContainingCharacter, payerAccountNoContainSpecialCharacter;
	String payeeAccountNoContainingCharacter, payeeAccountNoContainSpecialCharacter;
	String amountContainingCharacter, amountContainSpecialCharacter;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	FundTransferPageObject fundTransferPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Data
		payerAccountNoContainingCharacter = "123payer";
		payerAccountNoContainSpecialCharacter = "123@#$";
		payeeAccountNoContainingCharacter = "123payee";
		payeeAccountNoContainSpecialCharacter = "123&*(";
		amountContainingCharacter = "123amount";
		amountContainSpecialCharacter = "123$";
	
		
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
	}

	@Test
	public void TC_01_PayerAccountNoMustNotBeBlank() {
		log.info("Validate Payer Account No field with blank value - STEP 01: Do not input a value in Payer Account No field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, "payersaccount");
		
		log.info("Validate Payer Account No field with blank value - STEP 02: Verify 'Payers Account Number must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payers account no"), "Payers Account Number must not be blank");
	}
	
	@Test
	public void TC_02_PayerAccountNoMustNotContainCharacter() {
		log.info("Validate Payer Account No field with character - STEP 01: Input character into Payer Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, "payersaccount", payerAccountNoContainingCharacter);
		
		log.info("Validate Payer Account No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payers account no"), "Characters are not allowed");
	}
	
	@Test
	public void TC_03_PayerAccountNoMustNotContainSpecialCharacter() {
		log.info("Validate Payer Account No field with special character - STEP 01: Input special character into Payer Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, "payersaccount", payerAccountNoContainSpecialCharacter);
		
		log.info("Validate Payer Account No field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payers account no"), "Special characters are not allowed");
	}
			
	@Test
	public void TC_04_PayeeAccountNoMustNotBeBlank() {
		log.info("Validate Payee Account No field with blank value - STEP 01: Do not input a value in Payee Account No field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, "payeeaccount");
		
		log.info("Validate Payee Account No field with blank value - STEP 02: Verify 'Payees Account Number must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payees account no"), "Payees Account Number must not be blank");
	}
	
	@Test
	public void TC_05_PayeeAccountNoMustNotContainCharacter() {
		log.info("Validate Payee Account No field with character - STEP 01: Input character into Payee Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, "payeeaccount", payeeAccountNoContainingCharacter);
		
		log.info("Validate Payee Account No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payees account no"), "Characters are not allowed");
	}
	
	@Test
	public void TC_06_PayeeAccountNoMustNotContainSpecialCharacter() {
		log.info("Validate Payee Account No field with special character - STEP 01: Input special character into Payee Account No field");
		fundTransferPage.inputToDynamicTextbox(driver, "payeeaccount", payeeAccountNoContainSpecialCharacter);
		
		log.info("Validate Payee Account No field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Payees account no"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_07_AmountMustNotBeBlank() {
		log.info("Validate Amount field with blank value - STEP 01: Do not input a value in Amount field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, "ammount");
		
		log.info("Validate Amount field with blank value - STEP 02: Verify 'Amount field must not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Amount"), "Amount field must not be blank");
	}
	
	@Test
	public void TC_08_AmountMustNotContainCharacter() {
		log.info("Validate Amount field with character - STEP 01: Input character into Amount field");
		fundTransferPage.inputToDynamicTextbox(driver, "ammount", amountContainingCharacter);
		
		log.info("Validate Amount No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Amount"), "Characters are not allowed");
	}
	
	@Test
	public void TC_09_AmountMustNotContainSpecialCharacter() {
		log.info("Validate Amount field with special character - STEP 01: Input special character into Amount field");
		fundTransferPage.inputToDynamicTextbox(driver, "ammount", amountContainSpecialCharacter);
		
		log.info("Validate Amount field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Amount"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_10_DescriptionMustNotBeBlank() {
		log.info("Validate Description field with blank value - STEP 01: Do not input a value in Description field and press Tab key");
		fundTransferPage.pressTabToDynamicTextbox(driver, "desc");
		
		log.info("Validate Description field with blank value - STEP 02: Verify 'Description can not be blank' message is displayed");
		verifyEquals(fundTransferPage.getErrorMessageOfDynamicField(driver, "Description"), "Description can not be blank");
	}

	@AfterClass (alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
