package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
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

	String userName, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";
		
		// Data
		payerAccountNoContainingCharacter = "123payer";
		payerAccountNoContainSpecialCharacter = "123@#$";
		payeeAccountNoContainingCharacter = "123payee";
		payeeAccountNoContainSpecialCharacter = "123&*(";
		amountContainingCharacter = "123amount";
		amountContainSpecialCharacter = "123$";
	
		
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
	}

	@Test
	public void TC_01_PayerAccountNoMustNotBeBlank() {
		fundTransferPage.inputNothingToPayerAccountNoTextboxAndPressTabKey();
		Assert.assertTrue(fundTransferPage.isPayerAccountNoMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_02_PayerAccountNoMustNotContainCharacter() {
		fundTransferPage.inputToPayerAccountNoTextbox(payerAccountNoContainingCharacter);
		Assert.assertTrue(fundTransferPage.isPayerAccountNoMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_03_PayerAccountNoMustNotContainSpecialCharacter() {
		fundTransferPage.inputToPayerAccountNoTextbox(payerAccountNoContainSpecialCharacter);
		Assert.assertTrue(fundTransferPage.isPayerAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}
			
	@Test
	public void TC_04_PayeeAccountNoMustNotBeBlank() {
		fundTransferPage.inputNothingToPayeeAccountNoTextboxAndPressTabKey();
		Assert.assertTrue(fundTransferPage.isPayeeAccountNoMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_05_PayeeAccountNoMustNotContainCharacter() {
		fundTransferPage.inputToPayeeAccountNoTextbox(payeeAccountNoContainingCharacter);
		Assert.assertTrue(fundTransferPage.isPayeeAccountNoMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_06_PayeeAccountNoMustNotContainSpecialCharacter() {
		fundTransferPage.inputToPayeeAccountNoTextbox(payeeAccountNoContainSpecialCharacter);
		Assert.assertTrue(fundTransferPage.isPayeeAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_07_AmountMustNotBeBlank() {
		fundTransferPage.inputNothingToAmountTextboxAndPressTabKey();
		Assert.assertTrue(fundTransferPage.isAmountMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_08_AmountMustNotContainCharacter() {
		fundTransferPage.inputToAmountTextbox(amountContainingCharacter);
		Assert.assertTrue(fundTransferPage.isAmountMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_09_AmountMustNotContainSpecialCharacter() {
		fundTransferPage.inputToAmountTextbox(amountContainSpecialCharacter);
		Assert.assertTrue(fundTransferPage.isAmountMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_10_DescriptionMustNotBeBlank() {
		fundTransferPage.inputNothingToDescriptionTextboxAndPressTabKey();
		Assert.assertTrue(fundTransferPage.isDescriptionMustNotBlankMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
