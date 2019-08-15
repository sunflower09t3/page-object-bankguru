package com.bankguru.validation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;

public class Validation_04_NewAccount extends AbstractTest{
	WebDriver driver;
	String userName, password;
	String customerIDContainingCharacter, customerIDContaingSpecialCharacter; 
	String customerIDContainingSpace, customerIDBeginWithSpace;
	String initialDepositContainingCharacter, initialDepositContainingSpecialCharacter, initialDepositContainingSpace;
	String initialDepositBeginWithSpace;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";

		// Data
		customerIDContainingCharacter = "123abc";
		customerIDContaingSpecialCharacter = "123$%^";
		customerIDContainingSpace = "123 45";
		customerIDBeginWithSpace = " 123";
		initialDepositContainingCharacter = "10000Acc";
		initialDepositContainingSpecialCharacter = "10000#$%";
		initialDepositContainingSpace = "100 00";
		initialDepositBeginWithSpace = " 10000";

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
		
		homePage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
	}

	@Test
	public void TC_01_CustomerIDMustNotBeBlank() {
		log.info("Validate Customer ID field with blank value - STEP 01: Do not input a value in Customer ID field and press Tab key");
		newAccountPage.inputNothingToCustomerIDTextboxAndPressTabKey();
		
		log.info("Validate Customer ID field with blank value - STEP 02: Verify 'Customer ID is required' message is displayed");
		verifyTrue(newAccountPage.isCustomerIDMustNoBlankMessageDisplayed());
	}
	
	@Test
	public void TC_02_CustomerIDMustNotContainCharacter() {
		log.info("Validate Customer ID field with character - STEP 01: Input character into Customer ID field");
		newAccountPage.inputToCustomerIDTextbox(customerIDContainingCharacter);
		
		log.info("Validate Customer ID field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(newAccountPage.isCustomerIDMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_03_CustomerIDMustNotContainSpecialCharacter() {
		log.info("Validate Customer ID field with special character - STEP 01: Input special character into Customer ID field");
		newAccountPage.inputToCustomerIDTextbox(customerIDContaingSpecialCharacter);
		
		log.info("Validate Customer ID field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(newAccountPage.isCustomerIDMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_04_CustomerIDMustNotContainSpace() {
		log.info("Validate Customer ID field with space - STEP 01: Input a number which contains a space into Customer ID field");
		newAccountPage.inputToCustomerIDTextbox(customerIDContainingSpace);
		
		log.info("Validate Customer ID field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(newAccountPage.isCustomerIDMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_05_CustomerIDMustNotBeginWithSpace() {
		log.info("Validate Customer ID field with space at the beginning - STEP 01: Input first character as a space");
		newAccountPage.inputToCustomerIDTextbox(customerIDBeginWithSpace);
		
		log.info("Validate Customer ID field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newAccountPage.isCustomerIDMustNotBeginWithSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_06_InitialDepositMustNotBeBlank() {
		log.info("Validate Initial Deposit field with blank value - STEP 01: Do not input a value in Initial Deposit field and press Tab key");
		newAccountPage.inputNothingToInitialDepositTextboxAndPressTabKey();
		
		log.info("Validate Initial Deposit field with blank value - STEP 02: Verify 'Initial Deposit must not be blank' message is displayed");
		verifyTrue(newAccountPage.isInitialDepositMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_07_InitialDepositMustNotContainCharacter() {
		log.info("Validate Initial Deposit field with character - STEP 01: Input character into Initial Deposit field");
		newAccountPage.inputToInitialDepositTextbox(initialDepositContainingCharacter);
		
		log.info("Validate Initial Deposit field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(newAccountPage.isInitialDepositMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_08_InitialDepositMustNotContainSpecialCharacter() {
		log.info("Validate Initial Deposit field with special character - STEP 01: Input special character into Initial Deposit field");
		newAccountPage.inputToInitialDepositTextbox(initialDepositContainingSpecialCharacter);
		
		log.info("Validate Initial Deposit field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(newAccountPage.isInitialDepositMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_09_InitialDepositMustNotContainSpace() {
		log.info("Validate Initial Deposit field with space - STEP 01: Input a number which contains a space into Initial Deposit field");
		newAccountPage.inputToInitialDepositTextbox(initialDepositContainingSpace);
		
		log.info("Validate Initial Deposit field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(newAccountPage.isInitialDepositMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_10_InitialDepositMustNotBeginWithSpace() {
		log.info("Validate Initial Deposit field with space at the beginning - STEP 01: Input first character as a space");
		newAccountPage.inputToInitialDepositTextbox(initialDepositBeginWithSpace);
		
		log.info("Validate Initial Deposit field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(newAccountPage.isInitialDepositMustNotBeginWithSpaceMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
