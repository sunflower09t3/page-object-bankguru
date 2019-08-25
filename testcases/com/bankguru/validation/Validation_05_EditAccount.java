package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import bankguru.AccountData;
import bankguru.CustomerData;
import bankguru.ValidationData;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.EditAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;

public class Validation_05_EditAccount extends AbstractTest {
	WebDriver driver;
	
	String editAccountPageURL, newCustomerID, newAccountID, forthCustomerEmail;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	EditAccountPageObject editAccountPage;
	NewCustomerPageObject newCustomerPage;
	NewAccountPageObject newAccountPage;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browserName) {
		forthCustomerEmail = String.format(CustomerData.NewCustomer.FORTH_CUSTOMER_EMAIL, randomNumber());
		
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("TC_01_AccountNoMustNotBeBlank - STEP 01: Open Edit Account form");
		homePage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));
		editAccountPageURL = editAccountPage.getEditAccountPageURL();

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 02: Do not input a value in Account No field and press Tab key");
		editAccountPage.pressTabToDynamicTextbox(driver, "accountno");

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 03: Verify 'Account Number must not be blank' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Account Number must not be blank");
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 01: Open Edit Account form");
		editAccountPage.openURL(driver, editAccountPageURL);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 02: Input character into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", ValidationData.EditAccount.ACCOUNT_NO_CONTAIN_CHARACTER);

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 01: Open Edit Account form");
		editAccountPage.openURL(driver, editAccountPageURL);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", ValidationData.EditAccount.ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER);

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("TC_04_AccountNoMustNotContainSpace - STEP 01: Open Edit Account form");
		editAccountPage.openURL(driver, editAccountPageURL);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 02: Input a number which contains a space into Account No field");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", ValidationData.EditAccount.ACCOUNT_NO_CONTAIN_SPACE);

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 01: Open Edit Account form");
		editAccountPage.openURL(driver, editAccountPageURL);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 02: Input first character as a space");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", ValidationData.EditAccount.ACCOUNT_NO_BEGIN_WITH_SPACE);

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(editAccountPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_06_validAccountNo() {
		log.info("TC_06_validAccountNo - STEP 01: Open New Customer page");
		editAccountPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_06_validAccountNo - STEP 02: Input valid data to New Customer form");
		newCustomerPage.inputToDynamicTextbox(driver, "name", CustomerData.NewCustomer.FORTH_CUSTOMER_NAME);
		newCustomerPage.selectDynamicRadioButton(driver, CustomerData.NewCustomer.FORTH_CUSTOMER_GENDER.substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, "dob", CustomerData.NewCustomer.FORTH_CUSTOMER_DATE_OF_BIRTH);
		newCustomerPage.inputToDynamicTextarea(driver, "addr", CustomerData.NewCustomer.FORTH_CUSTOMER_ADDRESS);
		newCustomerPage.inputToDynamicTextbox(driver, "city", CustomerData.NewCustomer.FORTH_CUSTOMER_CITY);
		newCustomerPage.inputToDynamicTextbox(driver, "state", CustomerData.NewCustomer.FORTH_CUSTOMER_STATE);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", CustomerData.NewCustomer.FORTH_CUSTOMER_PIN);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", CustomerData.NewCustomer.FORTH_CUSTOMER_TELEPHONE);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", forthCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", CustomerData.NewCustomer.FORTH_CUSTOMER_PASSWORD);

		log.info("TC_06_validAccountNo - STEP 03: Click Submit button");
		newCustomerPage.clickDynamicButton(driver, "sub");
		newCustomerID = newCustomerPage.getDanymicDataInTable(driver, "Customer ID");

		log.info("TC_06_validAccountNo - STEP 04: Open New Account page");
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_06_validAccountNo - STEP 05: Fill in New Account page");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, AccountData.NewAccount.FIRST_ACCOUNT_TYPE, "selaccount");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", String.valueOf(AccountData.NewAccount.FIRST_ACCOUNT_INITIAL_DEPOSIT));

		log.info("TC_06_validAccountNo - STEP 06: Click Submit button");
		newAccountPage.clickDynamicButton(driver, "button2");
		newAccountID = newAccountPage.getDanymicDataInTable(driver, "Account ID");

		log.info("TC_06_validAccountNo - STEP 07: Open Edit Account page");
		newAccountPage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_06_validAccountNo - STEP 10: Input to Account No textbox");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", newAccountID);

		log.info("TC_06_validAccountNo - STEP 11: Click Submit button");
		editAccountPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_06_validAccountNo - STEP 12: Verify Edit Account Entry form is displayed");
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Entry Form"));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
