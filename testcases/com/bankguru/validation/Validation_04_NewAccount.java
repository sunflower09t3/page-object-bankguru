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
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;

public class Validation_04_NewAccount extends AbstractTest {
	WebDriver driver;
	
	String newAccountPageURL;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;

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
	public void TC_01_CustomerIDMustNotBeBlank() {
		log.info("TC_01_CustomerIDMustNotBeBlank - STEP 01: Open New Account page");
		homePage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));
		newAccountPageURL = newAccountPage.getNewAccountPageURL();

		log.info("TC_01_CustomerIDMustNotBeBlank STEP 02: Do not input a value in Customer ID field and press Tab key");
		newAccountPage.pressTabToDynamicTextbox(driver, "cusid");

		log.info("TC_01_CustomerIDMustNotBeBlank STEP 03: Verify 'Customer ID is required' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "Customer ID is required");
	}

	@Test
	public void TC_02_CustomerIDMustNotContainCharacter() {
		log.info("TC_02_CustomerIDMustNotContainCharacter - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_02_CustomerIDMustNotContainCharacter STEP 02: Input to Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", ValidationData.NewAccount.CUSTOMER_ID_CONTAIN_CHARACTER);

		log.info("TC_02_CustomerIDMustNotContainCharacter STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "Characters are not allowed");
	}

	@Test
	public void TC_03_CustomerIDMustNotContainSpecialCharacter() {
		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 01: Input to Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", ValidationData.NewAccount.CUSTOMER_ID_CONTAIN_SPECIAL_CHARACTER);

		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_CustomerIDMustNotContainSpace() {
		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 02: Input to Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", ValidationData.NewAccount.CUSTOMER_ID_CONTAIN_SPACE);

		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "Characters are not allowed");
	}

	@Test
	public void TC_05_CustomerIDMustNotBeginWithSpace() {
		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 02: Input to Customer ID field");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", ValidationData.NewAccount.CUSTOMER_ID_BEGIN_WITH_SPACE);

		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Customer id"), "First character can not have space");
	}

	@Test
	public void TC_06_InitialDepositMustNotBeBlank() {
		log.info("TC_06_InitialDepositMustNotBeBlank - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_06_InitialDepositMustNotBeBlank - STEP 02: Do not input a value in Initial Deposit field and press Tab key");
		newAccountPage.pressTabToDynamicTextbox(driver, "inideposit");

		log.info("TC_06_InitialDepositMustNotBeBlank - STEP 03: Verify 'Initial Deposit must not be blank' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "Initial Deposit must not be blank");
	}

	@Test
	public void TC_07_InitialDepositMustNotContainCharacter() {
		log.info("TC_07_InitialDepositMustNotContainCharacter - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_07_InitialDepositMustNotContainCharacter - STEP 02: Input to Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", ValidationData.NewAccount.INITIAL_DEPOSIT_CONTAIN_CHARACTER);

		log.info("TC_07_InitialDepositMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "Characters are not allowed");
	}

	@Test
	public void TC_08_InitialDepositMustNotContainSpecialCharacter() {
		log.info("TC_08_InitialDepositMustNotContainSpecialCharacter - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_08_InitialDepositMustNotContainSpecialCharacter - STEP 02: Input to Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", ValidationData.NewAccount.INITIAL_DEPOSIT_CONTAIN_SPECIAL_CHARACTER);

		log.info("TC_08_InitialDepositMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "Special characters are not allowed");
	}

	@Test
	public void TC_09_InitialDepositMustNotContainSpace() {
		log.info("TC_09_InitialDepositMustNotContainSpace - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_09_InitialDepositMustNotContainSpace - STEP 02: Input to Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", ValidationData.NewAccount.INITIAL_DEPOSIT_CONTAIN_SPACE);

		log.info("TC_09_InitialDepositMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "Characters are not allowed");
	}

	@Test
	public void TC_10_InitialDepositMustNotBeginWithSpace() {
		log.info("TC_10_InitialDepositMustNotBeginWithSpace - STEP 01: Open New Account page");
		newAccountPage.openURL(driver, newAccountPageURL);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_10_InitialDepositMustNotBeginWithSpace - STEP 02: Input to Initial Deposit field");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", ValidationData.NewAccount.INITIAL_DEPOSIT_BEGIN_WITH_SPACE);

		log.info("TC_10_InitialDepositMustNotBeginWithSpace - STEP 03: Verify 'First character can not have space' message is displayed");
		verifyEquals(newAccountPage.getErrorMessageOfDynamicField(driver, "Initial deposit"), "First character can not have space");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
