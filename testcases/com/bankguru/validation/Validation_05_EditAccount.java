package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.EditAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_05_EditAccount extends AbstractTest {
	WebDriver driver;
	String newCustomerID, newCustomerName, newCustomerDateOfBirth, newCustomerAddress, newCustomerState;
	String newCustomerCity, newCustomerPIN, newCustomerTelephone, newCustomerEmail, newCustomerPassword;
	String newAccountID, newAccountAccType;
	String accountNoContainingCharacter, accountNoContainingSpecialCharacter;
	String accountNoContainingSpace, accountNoBeginWithSpace;
	int newAccountInitialDeposit;

	LoginPageObject loginPage;
	HomePageObject homePage;
	EditAccountPageObject editAccountPage;
	NewCustomerPageObject newCustomerPage;
	NewAccountPageObject newAccountPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Data
		accountNoContainingCharacter = "123Acc";
		accountNoContainingSpecialCharacter = "123$%^";
		accountNoContainingSpace = "123 456";
		accountNoBeginWithSpace = " ";

		// New customer data
		newCustomerName = "first customer";
		newCustomerDateOfBirth = "01/01/1989";
		newCustomerAddress = "PO Box 911 8331 Duis Avenue";
		newCustomerState = "FL";
		newCustomerCity = "Tampa";
		newCustomerPIN = "466250";
		newCustomerTelephone = "4555442476";
		newCustomerEmail = "firstcustomer" + randomNumber() + "@gmail.com";
		newCustomerPassword = "firstcustomerpassword";

		// New account data
		newAccountAccType = "Savings";
		newAccountInitialDeposit = 50000;

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("Validate Account No field with blank value - STEP 01: Do not input a value in Account No field and press Tab key");
		editAccountPage.pressTabToDynamicTextbox(driver, "accountno");
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Account Number must not be blank' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Account Number must not be blank");
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("Validate Account No field with character - STEP 01: Input character into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", accountNoContainingCharacter);
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("Validate Account No field with special character - STEP 01: Input special character into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", accountNoContainingSpecialCharacter);
		
		log.info("Validate Account No field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("Validate Account No field with space - STEP 01: Input a number which contains a space into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", accountNoContainingSpace);
		
		log.info("Validate Account No field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("Validate Account No field with space at the beginning - STEP 01: Input first character as a space");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", accountNoBeginWithSpace);
		
		log.info("Validate Account No field with space at the beginning - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_06_validAccountNo() {
		log.info("Valid Account No - STEP 01: Open New Customer page");
		editAccountPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		
		log.info("Valid Account No - STEP 02: Fill in New Customer form");
		newCustomerPage.inputToDynamicTextbox(driver, "name", newCustomerName);
		newCustomerPage.selectDynamicRadioButton(driver, "f");
		newCustomerPage.inputToDynamicTextbox(driver, "dob", newCustomerDateOfBirth);
		newCustomerPage.inputToDynamicTextarea(driver, "addr", newCustomerAddress);
		newCustomerPage.inputToDynamicTextbox(driver, "city", newCustomerCity);
		newCustomerPage.inputToDynamicTextbox(driver, "state", newCustomerState);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", newCustomerPIN);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", newCustomerTelephone);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", newCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", newCustomerPassword);
		
		log.info("Valid Account No - STEP 03: Click Submit button");
		newCustomerPage.clickDynamicButton(driver, "sub");
		
		log.info("Valid Account No - STEP 04: Store ID of the newly created customer");
		newCustomerID = newCustomerPage.getCustomerID();
		
		log.info("Valid Account No - STEP 05: Open New Account page");
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		log.info("Valid Account No - STEP 06: Fill in New Account page");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		newAccountPage.selectAccountType(newAccountAccType);
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", String.valueOf(newAccountInitialDeposit));
		
		log.info("Valid Account No - STEP 07: Click Submit button");
		newAccountPage.clickDynamicButton(driver, "button2");
		
		log.info("Valid Account No - STEP 08: Store ID of newly created account");
		newAccountID = newAccountPage.getAccountID();
		
		log.info("Valid Account No - STEP 09: Open Edit Account page");
		newAccountPage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		
		log.info("Valid Account No - STEP 10: Input account ID");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", newAccountID);
		
		log.info("Valid Account No - STEP 11: Click Submit button");
		editAccountPage.clickDynamicButton(driver, "AccSubmit");

		log.info("Valid Account No - STEP 12: Verify Edit Account Entry form is displayed");
		verifyTrue(editAccountPage.isEditAccountEntryFormDisplayed());
	}

	@AfterClass (alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
