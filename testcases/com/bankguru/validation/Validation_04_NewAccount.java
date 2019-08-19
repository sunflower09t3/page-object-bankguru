package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;

public class Validation_04_NewAccount extends AbstractTest{
	WebDriver driver;
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

		customerIDContainingCharacter = "123abc";
		customerIDContaingSpecialCharacter = "123$%^";
		customerIDContainingSpace = "123 45";
		customerIDBeginWithSpace = " 123";
		initialDepositContainingCharacter = "10000Acc";
		initialDepositContainingSpecialCharacter = "10000#$%";
		initialDepositContainingSpace = "100 00";
		initialDepositBeginWithSpace = " 10000";

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();
		
		homePage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
	}

	@Test
	public void TC_01_CustomerIDMustNotBeBlank() {
		log.info("Validate Customer ID field with blank value - STEP 01: Do not input a value in Customer ID field and press Tab key");
		newAccountPage.pressTabToDynamicTextbox(driver, "cusid");
		
		log.info("Validate Customer ID field with blank value - STEP 02: Verify 'Customer ID is required' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "Customer ID is required");
	}
	
	@Test
	public void TC_02_CustomerIDMustNotContainCharacter() {
		log.info("Validate Customer ID field with character - STEP 01: Input character into Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", customerIDContainingCharacter);
		
		log.info("Validate Customer ID field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "Characters are not allowed");
	}
	
	@Test
	public void TC_03_CustomerIDMustNotContainSpecialCharacter() {
		log.info("Validate Customer ID field with special character - STEP 01: Input special character into Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", customerIDContaingSpecialCharacter);
		
		log.info("Validate Customer ID field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_04_CustomerIDMustNotContainSpace() {
		log.info("Validate Customer ID field with space - STEP 01: Input a number which contains a space into Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", customerIDContainingSpace);
		
		log.info("Validate Customer ID field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "Characters are not allowed");
	}
	
	@Test
	public void TC_05_CustomerIDMustNotBeginWithSpace() {
		log.info("Validate Customer ID field with space at the beginning - STEP 01: Input first character as a space");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", customerIDBeginWithSpace);
		
		log.info("Validate Customer ID field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "First character can not have space");
	}
	
	@Test
	public void TC_06_InitialDepositMustNotBeBlank() {
		log.info("Validate Initial Deposit field with blank value - STEP 01: Do not input a value in Initial Deposit field and press Tab key");
		newAccountPage.pressTabToDynamicTextbox(driver, "inideposit");
		
		log.info("Validate Initial Deposit field with blank value - STEP 02: Verify 'Initial Deposit must not be blank' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "Initial Deposit must not be blank");
	}
	
	@Test
	public void TC_07_InitialDepositMustNotContainCharacter() {
		log.info("Validate Initial Deposit field with character - STEP 01: Input character into Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", initialDepositContainingCharacter);
		
		log.info("Validate Initial Deposit field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "Characters are not allowed");
	}
	
	@Test
	public void TC_08_InitialDepositMustNotContainSpecialCharacter() {
		log.info("Validate Initial Deposit field with special character - STEP 01: Input special character into Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", initialDepositContainingSpecialCharacter);
		
		log.info("Validate Initial Deposit field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_09_InitialDepositMustNotContainSpace() {
		log.info("Validate Initial Deposit field with space - STEP 01: Input a number which contains a space into Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", initialDepositContainingSpace);
		
		log.info("Validate Initial Deposit field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "Characters are not allowed");
	}
	
	@Test
	public void TC_10_InitialDepositMustNotBeginWithSpace() {
		log.info("Validate Initial Deposit field with space at the beginning - STEP 01: Input first character as a space");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", initialDepositBeginWithSpace);
		
		log.info("Validate Initial Deposit field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "First character can not have space");
	}

	@AfterClass (alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
