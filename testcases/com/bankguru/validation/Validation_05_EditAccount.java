package com.bankguru.validation;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.EditAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_05_EditAccount extends AbstractTest {
	WebDriver driver;
	String userName, password;
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

		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";

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

		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("Validate Account No field with blank value - STEP 01: Do not input a value in Account No field and press Tab key");
		editAccountPage.inputNothingToAccountNoTextboxAndPressTabKey();
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Account Number must not be blank' message is displayed");
		verifyTrue(editAccountPage.isAccountNoMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("Validate Account No field with character - STEP 01: Input character into Account No field");
		editAccountPage.inputToAccountNoTextbox(accountNoContainingCharacter);
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(editAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("Validate Account No field with special character - STEP 01: Input special character into Account No field");
		editAccountPage.inputToAccountNoTextbox(accountNoContainingSpecialCharacter);
		
		log.info("Validate Account No field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(editAccountPage.isAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("Validate Account No field with space - STEP 01: Input a number which contains a space into Account No field");
		editAccountPage.inputToAccountNoTextbox(accountNoContainingSpace);
		
		log.info("Validate Account No field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(editAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("Validate Account No field with space at the beginning - STEP 01: Input first character as a space");
		editAccountPage.inputToAccountNoTextbox(accountNoBeginWithSpace);
		
		log.info("Validate Account No field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(editAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_06_validAccountNo() {
		log.info("Valid Account No - STEP 01: Open New Customer page");
		editAccountPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		
		log.info("Valid Account No - STEP 02: Fill in New Customer form");
		newCustomerPage.inputToCustomerNameTextbox(newCustomerName);
		newCustomerPage.selectFemaleGenderRadioButton();
		newCustomerPage.inputToDateOfBirthTextbox(newCustomerDateOfBirth);
		newCustomerPage.inputToAddressTextarea(newCustomerAddress);
		newCustomerPage.inputToCityTextbox(newCustomerCity);
		newCustomerPage.inputToStateTextbox(newCustomerState);
		newCustomerPage.inputToPinTextbox(newCustomerPIN);
		newCustomerPage.inputToTelephoneTextbox(newCustomerTelephone);
		newCustomerPage.inputToEmailTextbox(newCustomerEmail);
		newCustomerPage.inputToPasswordTextbox(newCustomerPassword);
		
		log.info("Valid Account No - STEP 03: Click Submut button");
		newCustomerPage.clickSubmitButton();
		
		log.info("Valid Account No - STEP 04: Store ID of the newly created customer");
		newCustomerID = newCustomerPage.getCustomerID();
		
		log.info("Valid Account No - STEP 05: Open New Account page");
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		log.info("Valid Account No - STEP 06: Fill in New Customer page");
		newAccountPage.inputToCustomerIDTextbox(newCustomerID);
		newAccountPage.selectAccountType(newAccountAccType);
		newAccountPage.inputToInitialDepositTextbox(String.valueOf(newAccountInitialDeposit));
		
		log.info("Valid Account No - STEP 07: Click Submit button");
		newAccountPage.clickSubmitButton();
		
		log.info("Valid Account No - STEP 08: Store ID of newly created account");
		newAccountID = newAccountPage.getAccountID();
		
		log.info("Valid Account No - STEP 09: Open Edit Account page");
		newAccountPage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		
		log.info("Valid Account No - STEP 10: Input account ID");
		editAccountPage.inputToAccountNoTextbox(newAccountID);
		
		log.info("Valid Account No - STEP 11: Click Submit button");
		editAccountPage.clickSubmitButton();

		log.info("Valid Account No - STEP 12: Verify Edit Account Entry form is displayed");
		verifyTrue(editAccountPage.isEditAccountEntryFormDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000000);
	}

}
