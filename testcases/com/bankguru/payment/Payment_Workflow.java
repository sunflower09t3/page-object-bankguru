package com.bankguru.payment;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.bankguru.commons.Common_01_RegisterToSystem;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import bankguru.AccountDataJson;
import bankguru.CustomerDataJson;
import commons.AbstractTest;
import commons.Constants;
import commons.PageGeneratorManager;
import pageObjects.BalanceEnquiryPageObject;
import pageObjects.DeleteAccountPageObject;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.DepositPageObject;
import pageObjects.EditAccountPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.WithdrawalPageObject;

public class Payment_Workflow extends AbstractTest {
	WebDriver driver;
	String firstCustomerID, secondCustomerID, secondAccountID, firstAccountID, firstAccountOpeningDate;
	String firstCustomerEmail, firstCustomerEditedEmail, secondCustomerEmail;
	int expectedBalance;
	CustomerDataJson customerData;
	AccountDataJson accountData;
	LoginPageObject loginPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;
	NewAccountPageObject newAccountPage;
	EditAccountPageObject editAccountPage;
	DepositPageObject depositPage;
	WithdrawalPageObject withdrawalPage;
	FundTransferPageObject fundTransferPage;
	BalanceEnquiryPageObject balanceEnquiryPage;
	DeleteAccountPageObject deleteAccountPage;
	DeleteCustomerPageObject deleteCustomerPage;

	@Parameters({"browser","customerData", "accountData"})
	@BeforeClass
	public void setup(String browserName, String customerDataFilePath, String accountDataFilePath) throws JsonParseException, JsonMappingException, IOException {
		customerData = CustomerDataJson.get(customerDataFilePath);
		accountData = AccountDataJson.get(accountDataFilePath);
		
		firstCustomerEmail = String.format(customerData.getFirstCustomerEmail(), randomNumber());
		secondCustomerEmail = String.format(customerData.getSecondCustomerEmail(), randomNumber());
		firstCustomerEditedEmail = String.format(customerData.getFirstCustomerEditedEmail(), randomNumber());
		
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_USER_ID_FIELD, Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, Common_01_RegisterToSystem.password);

		homePage = loginPage.clickLoginButton();
	}

	@Test
	public void TC_01_CreateNewCustomer() {
		log.info("--------------------- Create first customer ---------------------");
		
		log.info("TC_01_CreateNewCustomer - STEP 01: Open New Customer page");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_CUSTOMER);
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));

		log.info("TC_01_CreateNewCustomer - STEP 02: Input valid data to Add New Customer form and click Submit button");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD, customerData.getFirstCustomerName());
		newCustomerPage.selectDynamicRadioButton(driver, customerData.getFirstCustomerGender().substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DOB_FIELD, customerData.getFirstCustomerDateOfBirth());
		newCustomerPage.inputToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD, customerData.getFirstCustomerAddress());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, customerData.getFirstCustomerCity());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, customerData.getFirstCustomerState());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, customerData.getFirstCustomerPin());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, customerData.getFirstCustomerTelephone());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, firstCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, customerData.getFirstCustomerPassword());
		newCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_SUBMIT_BUTTON);

		log.info("TC_01_CreateNewCustomer - STEP 03: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, customerData.getFirstCustomerAddingResultMessage()));

		log.info("TC_01_CreateNewCustomer - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_NAME_ROW), customerData.getFirstCustomerName());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_GENDER_ROW), customerData.getFirstCustomerGender());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_DOB_ROW), customerData.getFirstCustomerDateOfBirth());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ADDRESS_ROW), customerData.getFirstCustomerAddress());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CITY_ROW), customerData.getFirstCustomerCity());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_STATE_ROW), customerData.getFirstCustomerState());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_PIN_ROW), customerData.getFirstCustomerPin());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_MOBILE_NUMBER_ROW), customerData.getFirstCustomerTelephone());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_EMAIL_ROW), firstCustomerEmail);
		firstCustomerID = newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_ID_ROW);
		
		log.info("--------------------- Create second customer ---------------------");
		
		log.info("TC_01_CreateNewCustomer - STEP 05: Open Add New Customer page");
		newCustomerPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_CUSTOMER);
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_CUSTOMER));
		
		log.info("TC_01_CreateNewCustomer - STEP 06: Input valid data to Add New Customer form and click Submit button");
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD, customerData.getSecondCustomerName());
		newCustomerPage.selectDynamicRadioButton(driver, customerData.getSecondCustomerGender().substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DOB_FIELD, customerData.getSecondCustomerDateOfBirth());
		newCustomerPage.inputToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD, customerData.getSecondCustomerAddress());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, customerData.getSecondCustomerCity());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, customerData.getSecondCustomerState());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, customerData.getSecondCustomerPin());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, customerData.getSecondCustomerTelephone());
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, secondCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, customerData.getSecondCustomerPassword());
		newCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_SUBMIT_BUTTON);
		
		log.info("TC_01_CreateNewCustomer - STEP 07: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, customerData.getSecondCustomerAddingResultMessage()));

		log.info("TC_01_CreateNewCustomer - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_NAME_ROW), customerData.getSecondCustomerName());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_GENDER_ROW), customerData.getSecondCustomerGender());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_DOB_ROW), customerData.getSecondCustomerDateOfBirth());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ADDRESS_ROW), customerData.getSecondCustomerAddress());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CITY_ROW), customerData.getSecondCustomerCity());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_STATE_ROW), customerData.getSecondCustomerState());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_PIN_ROW), customerData.getSecondCustomerPin());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_MOBILE_NUMBER_ROW), customerData.getSecondCustomerTelephone());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_EMAIL_ROW), secondCustomerEmail);
		secondCustomerID = newCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_ID_ROW);

	}

	@Test
	public void TC_02_EditCustomer() {
		log.info("--------------------- Edit first customer ---------------------");
		
		log.info("TC_02_EditCustomer - STEP 01: Open Edit Customer form");
		newCustomerPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_CUSTOMER);
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_02_EditCustomer - STEP 02: Input the custoner ID and click Submit");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, firstCustomerID);
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);
		
		log.info("TC_02_EditCustomer - STEP 03: Verify actual data and expected data are matching");
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_NAME_FIELD), customerData.getFirstCustomerName());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_GENDER_FIELD), customerData.getFirstCustomerGender());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DOB_FIELD), customerData.getFirstCustomerDateOfBirth());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD), customerData.getFirstCustomerAddress());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD), customerData.getFirstCustomerCity());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD), customerData.getFirstCustomerState());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD), customerData.getFirstCustomerPin());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD), customerData.getFirstCustomerTelephone());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD), firstCustomerEmail);

		log.info("TC_02_EditCustomer - STEP 04: Input to all editable fields and click Submit");
		editCustomerPage.inputToDynamicTextarea(driver, Constants.NAME_ATTRIBUTE_OF_ADDRESS_FIELD, customerData.getFirstCustomerEditedAddress());
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CITY_FIELD, customerData.getFirstCustomerEditedCity());
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_STATE_FIELD, customerData.getFirstCustomerEditedState());
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PIN_FIELD, customerData.getFirstCustomerEditedPin());
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_MOBILE_FIELD, customerData.getFirstCustomerEditedTelephone());
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_EMAIL_FIELD, firstCustomerEditedEmail);
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_SUBMIT_BUTTON);

		log.info("TC_02_EditCustomer - STEP 05: Verify 'Customer details updated Successfully!!!' message is displayed");
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, customerData.getFirstCustomerEditingResultMessage()));

		log.info("TC_02_EditCustomer - STEP 06: Verify actual data and expected data are matching");
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ADDRESS_ROW), customerData.getFirstCustomerEditedAddress());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CITY_ROW), customerData.getFirstCustomerEditedCity());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_STATE_ROW), customerData.getFirstCustomerEditedState());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_PIN_ROW), customerData.getFirstCustomerEditedPin());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_MOBILE_NUMBER_ROW), customerData.getFirstCustomerEditedTelephone());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_EMAIL_ROW), firstCustomerEditedEmail);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_ID_ROW), firstCustomerID);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_NAME_ROW), customerData.getFirstCustomerName());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, Constants.LABEL_OF_GENDER_ROW), customerData.getFirstCustomerGender());
	}

	@Test
	public void TC_03_CreateNewAccount() {
		log.info("--------------------- Create a new account for first customer ---------------------");
		
		log.info("TC_03_CreateNewAccount - STEP 01: Open New Account form");
		editCustomerPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_ACCOUNT);
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));

		log.info("TC_03_CreateNewAccount - STEP 02: Input valid data to Add New Account form and click Submit");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, firstCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, accountData.getFirstAccountType(), Constants.NAME_ATTRIBUTE_OF_ACC_TYPE_DROPDOW);
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD, String.valueOf(accountData.getFirstAccountInitialDeposit()));
		newAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_SUBMIT_BUTTON_IN_NEW_ACCOUNT_PAGE);

		log.info("TC_03_CreateNewAccount - STEP 03: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, accountData.getFirstAccountAddingResultMessage()));

		log.info("TC_03_CreateNewAccount - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_ID_ROW), firstCustomerID);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_NAME_ROW), customerData.getFirstCustomerName());
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_EMAIL_ROW), firstCustomerEditedEmail);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_TYPE_ROW), accountData.getFirstAccountType());
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_OPENING_DATE_ROW), formatDate(getCurrentDate(), "yyyy-MM-dd"));
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CURRENT_AMOUNT_ROW), String.valueOf(accountData.getFirstAccountInitialDeposit()));
		firstAccountID = newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_ID_ROW);
		firstAccountOpeningDate = newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_OPENING_DATE_ROW);
		
		log.info("--------------------- Create a new account for second customer ---------------------");
		
		log.info("TC_03_CreateNewAccount - STEP 05: Open Add new account form");
		newAccountPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_NEW_ACCOUNT);
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_NEW_ACCOUNT));
		
		log.info("TC_03_CreateNewAccount - STEP 06: Input valid data to Add New Account form and click Submit");
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, secondCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, accountData.getSecondAccountType(), Constants.NAME_ATTRIBUTE_OF_ACC_TYPE_DROPDOW);
		newAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD, String.valueOf(accountData.getSecondAccountInitialDeposit()));
		newAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_SUBMIT_BUTTON_IN_NEW_ACCOUNT_PAGE);
		
		log.info("TC_03_CreateNewAccount - STEP 07: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, accountData.getSecondAccountAddingResultMessage()));

		log.info("TC_03_CreateNewAccount - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_ID_ROW), secondCustomerID);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_NAME_ROW), customerData.getSecondCustomerName());
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_EMAIL_ROW), secondCustomerEmail);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_TYPE_ROW), accountData.getSecondAccountType());
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_OPENING_DATE_ROW), formatDate(getCurrentDate(), "yyyy-MM-dd"));
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CURRENT_AMOUNT_ROW), String.valueOf(accountData.getSecondAccountInitialDeposit()));
		secondAccountID = newAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_ID_ROW);
	}

	@Test
	public void TC_04_EditAccout() {
		log.info("--------------------- Edit account of first customer ---------------------");
		
		log.info("TC_04_EditAccout - STEP 01: Open Edit Account form");
		newAccountPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_ACCOUNT);
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));

		log.info("TC_04_EditAccout - STEP 02: Input account ID and click Submi");
		editAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, firstAccountID);
		editAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);
		
		log.info("TC_04_EditAccout - STEP 03: Verify actual data and expected data are matching");
		verifyEquals(editAccountPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD_IN_EDIT_ACCOUNT_PAGE), firstCustomerID);
		verifyEquals(editAccountPage.getCurrentSelectedItemInDynamicDropdown(driver, Constants.NAME_ATTRIBUTE_OF_ACC_TYPE_DROPDOW_IN_EDIT_ACCOUNT_PAGE), accountData.getFirstAccountType());
		verifyEquals(editAccountPage.getTextValueInDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_INITIAL_DEPOSIT_FIELD_IN_EDIT_ACCOUNT_PAGE), String.valueOf(accountData.getFirstAccountInitialDeposit()));

		log.info("TC_04_EditAccout - STEP 04: Change Account Type and click Submit");
		editAccountPage.selectItemInDynamicDropdown(driver, accountData.getFirstAccountEditedType(), Constants.NAME_ATTRIBUTE_OF_ACC_TYPE_DROPDOW_IN_EDIT_ACCOUNT_PAGE);
		editAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_04_EditAccout - STEP 05: Verify 'Account details updated Successfully!!!' message is displayed");
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, accountData.getFirstAccountEditingResultMessage()));

		log.info("TC_04_EditAccout - STEP 06: Verify actual data and expected data are matching");
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_ID_ROW), firstAccountID);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_ID_ROW), firstCustomerID);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CUSTOMER_NAME_ROW), customerData.getFirstCustomerName());
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_EMAIL_ROW), firstCustomerEditedEmail);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_TYPE_ROW), accountData.getFirstAccountEditedType());
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_OPENING_DATE_ROW), firstAccountOpeningDate);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CURRENT_AMOUNT_ROW), String.valueOf(accountData.getFirstAccountInitialDeposit()));

	}

	@Test
	public void TC_05_Deposit() {
		log.info("--------------------- Deposit money to first customer's account ---------------------");
		
		log.info("TC_05_Deposit - STEP 01: Open Amount Deposit Form");
		editAccountPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_DEPOSIT);
		depositPage = PageGeneratorManager.getDepositPage(driver);
		verifyTrue(depositPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_DEPOSIT));

		log.info("TC_05_Deposit - STEP 02: Input valid data to Amount Deposit form and click Submit");
		depositPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, firstAccountID);
		depositPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_AMOUNT_FIELD, String.valueOf(accountData.getFirstAccountDepositAmount()));
		depositPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DESCRIPTION_FIELD, accountData.getFirstAccountDepositDescription());
		depositPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_05_Deposit - STEP 03: Verify 'Transaction details of Deposit for Account " + firstAccountID + "' message is displayed");
		verifyTrue(depositPage.isPageTitleOrTableHeaderMessageDisplayed(driver, accountData.getFirstAccountDepositResultMessage() + firstAccountID));

		log.info("TC_05_Deposit - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(depositPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_NO_ROW), firstAccountID);
		verifyEquals(depositPage.getDanymicDataInTable(driver, Constants.LABEL_OF_AMOUNT_CREDITED_ROW), String.valueOf(accountData.getFirstAccountDepositAmount()));
		verifyEquals(depositPage.getDanymicDataInTable(driver, Constants.LABEL_OF_TRANSACTION_TYPE_ROW), "Deposit");
		verifyEquals(depositPage.getDanymicDataInTable(driver, Constants.LABEL_OF_DESCRIPTION_ROW), accountData.getFirstAccountDepositDescription());
		expectedBalance = accountData.getFirstAccountInitialDeposit() + accountData.getFirstAccountDepositAmount();
		verifyEquals(depositPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CURRENT_BALANCE_ROW), String.valueOf(expectedBalance));

	}

	@Test
	public void TC_06_Withdraw() {
		log.info("--------------------- Withdraw money from first customer's account ---------------------");
		
		log.info("TC_06_Withdraw - STEP 01: Open Amount Withdrawal Form");
		depositPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_WITHDRAW);
		withdrawalPage = PageGeneratorManager.getWithdrawlPage(driver);
		verifyTrue(withdrawalPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_WITHDRAW));

		log.info("TC_06_Withdraw - STEP 02: Input valid data to Amount Withdrawal form");
		withdrawalPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, firstAccountID);
		withdrawalPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_AMOUNT_FIELD, String.valueOf(accountData.getFirstAccountWithdrawAmount()));
		withdrawalPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DESCRIPTION_FIELD, accountData.getFirstAccountWithdrawDescription());
		withdrawalPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_06_Withdraw - STEP 03: Verify 'Transaction details of Withdrawal for Account " + firstAccountID + "' message is displayed");
		verifyTrue(withdrawalPage.isPageTitleOrTableHeaderMessageDisplayed(driver, accountData.getFirstAccountWithdrawResultMessage() + firstAccountID));

		log.info("TC_06_Withdraw - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_NO_ROW), firstAccountID);
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, Constants.LABEL_OF_AMOUNT_DEBITED_ROW), String.valueOf(accountData.getFirstAccountWithdrawAmount()));
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, Constants.LABEL_OF_TRANSACTION_TYPE_ROW), "Withdrawal");
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, Constants.LABEL_OF_DESCRIPTION_ROW), accountData.getFirstAccountWithdrawDescription());
		expectedBalance = accountData.getFirstAccountInitialDeposit() + accountData.getFirstAccountDepositAmount() - accountData.getFirstAccountWithdrawAmount();
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, Constants.LABEL_OF_CURRENT_BALANCE_ROW), String.valueOf(expectedBalance));
	}

	@Test
	public void TC_07_FundTransfer() {
		log.info("--------------------- Transfer money from first customer's account to second customer's account ---------------------");
		
		log.info("TC_07_FundTransfer - STEP 01: Open Fund Transfer page");
		withdrawalPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_FUND_TRANSFER);
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_FUND_TRANSFER));

		log.info("TC_07_FundTransfer - STEP 02: Input valid data to Fund Transfer form and click Submit");
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PAYER_ACC_FIELD, firstAccountID);
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PAYEE_ACC_FIELD, secondAccountID);
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_AMOUNT_FIELD, String.valueOf(accountData.getFirstAccountTransferAmount()));
		fundTransferPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_DESCRIPTION_FIELD, accountData.getFirstAccountTransferDescription());
		fundTransferPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_07_FundTransfer - STEP 03: Verify 'Fund Transfer Details' message is displayed");
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, accountData.getFirstAccountTransferResultMessage()));

		log.info("TC_07_FundTransfer - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, Constants.LABEL_OF_FROM_ACC_ROW), firstAccountID);
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, Constants.LABEL_OF_TO_ACC_ROW), secondAccountID);
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, Constants.LABEL_OF_AMOUNT_ROW), String.valueOf(accountData.getFirstAccountTransferAmount()));
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, Constants.LABEL_OF_DESCRIPTION_ROW), accountData.getFirstAccountTransferDescription());
	}

	@Test
	public void TC_08_BalanceEnquiry() {
		log.info("--------------------- Check balance of first customer's account ---------------------");
		
		log.info("TC_08_BalanceEnquiry - STEP 01: Open Balance Enquiry form");
		fundTransferPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_BALANCE_ENQUIRY);
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_BALANCE_ENQUIRY));

		log.info("TC_08_BalanceEnquiry - STEP 02: Input to Account No textbox and click Submit");
		balanceEnquiryPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, firstAccountID);
		balanceEnquiryPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_08_BalanceEnquiry - STEP 03: Verify 'Balance Details for Account " + firstAccountID + "' message is displayed");
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, accountData.getFirstAccountBalanceEnquiryResultMessage() + firstAccountID));

		log.info("TC_08_BalanceEnquiry - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_NO_ROW), firstAccountID);
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, Constants.LABEL_OF_TYPE_OF_ACC_ROW), accountData.getFirstAccountEditedType());
		expectedBalance = accountData.getFirstAccountInitialDeposit() + accountData.getFirstAccountDepositAmount() - accountData.getFirstAccountWithdrawAmount() - accountData.getFirstAccountTransferAmount();
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, Constants.LABEL_OF_BALANCE_ROW), String.valueOf(expectedBalance));
		
		log.info("--------------------- Check balance of second customer's account ---------------------");
		
		log.info("TC_08_BalanceEnquiry - STEP 05: Open Balance Enquiry form");
		fundTransferPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_BALANCE_ENQUIRY);
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_BALANCE_ENQUIRY));

		log.info("TC_08_BalanceEnquiry - STEP 06: Input to Account No textbox and click Submit");
		balanceEnquiryPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, secondAccountID);
		balanceEnquiryPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_08_BalanceEnquiry - STEP 07: Verify 'Balance Details for Account "+ secondAccountID +"' message is displayed");
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, accountData.getSecondAccountBalanceEnquiryResultMessage() + secondAccountID));

		log.info("TC_08_BalanceEnquiry - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, Constants.LABEL_OF_ACC_NO_ROW), secondAccountID);
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, Constants.LABEL_OF_TYPE_OF_ACC_ROW), accountData.getSecondAccountType());
		expectedBalance = accountData.getSecondAccountInitialDeposit() + accountData.getFirstAccountTransferAmount();
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, Constants.LABEL_OF_BALANCE_ROW), String.valueOf(expectedBalance));
	}

	@Test
	public void TC_09_DeleteAccount() {
		log.info("--------------------- Delete account of first customer ---------------------");
		
		log.info("TC_09_DeleteAccount - STEP 01: Open Delete Account form");
		balanceEnquiryPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_DELETE_ACCOUNT);
		deleteAccountPage = PageGeneratorManager.getDeleteAccountpage(driver);
		verifyTrue(deleteAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_DELETE_ACCOUNT));

		log.info("TC_09_DeleteAccount - STEP 02: Input to Account No textbox and click Submit button");
		deleteAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, firstAccountID);
		deleteAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);
		
		log.info("TC_09_DeleteAccount - STEP 03: Verify 'Do you really want to delete this Account?' alert message is displayed");
		deleteAccountPage.isDynamicAlertDisplayed(driver, Constants.TEXT_OF_DELETE_ACCOUNT_CONFIRMATION_ALERT);
		
		log.info("TC_09_DeleteAccount - STEP 03: Accept the alert");
		deleteAccountPage.acceptDeleteAccountConfirmationAlert();

		log.info("TC_09_DeleteAccount - STEP 04: Verify 'Account Deleted Sucessfully' alert message is displayed");
		verifyTrue(deleteAccountPage.isDynamicAlertDisplayed(driver, accountData.getFirstAccountDeletingResultMessage()));

		log.info("TC_09_DeleteAccount - STEP 05: Accept the alert");
		homePage = deleteAccountPage.acceptAccountDeletedSuccessfullyAlert();

		log.info("TC_09_DeleteAccount - STEP 06: Open Edit Account form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_ACCOUNT);
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));

		log.info("TC_09_DeleteAccount - STEP 07: Input to Account No textbox and click Submit button");
		editAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, firstAccountID);
		editAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_09_DeleteAccount - STEP 08: Verify 'Account does not exist' alert message is displayed");
		verifyTrue(editAccountPage.isDynamicAlertDisplayed(driver, Constants.TEXT_OF_ACCOUNT_NOT_EXIST_ALERT));

		log.info("TC_09_DeleteAccount - STEP 09: Accept the alert");
		editAccountPage.acceptAccountNotExistAlert();
		
		log.info("--------------------- Delete account of second customer ---------------------");
		
		log.info("TC_09_DeleteAccount - STEP 10: Open Delete Account form to delete payee account");
		balanceEnquiryPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_DELETE_ACCOUNT);
		deleteAccountPage = PageGeneratorManager.getDeleteAccountpage(driver);
		verifyTrue(deleteAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_DELETE_ACCOUNT));

		log.info("TC_09_DeleteAccount - STEP 11: Input to Account No textbox and click Submit button");
		deleteAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, secondAccountID);
		deleteAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_09_DeleteAccount - STEP 12: Accept the alert");
		deleteAccountPage.acceptDeleteAccountConfirmationAlert();

		log.info("TC_09_DeleteAccount - STEP 13: Verify 'Account Deleted Sucessfully' alert message is displayed");
		verifyTrue(deleteAccountPage.isDynamicAlertDisplayed(driver, accountData.getSecondAccountDeletingResultMessage()));

		log.info("TC_09_DeleteAccount - STEP 14: Accept the alert");
		homePage = deleteAccountPage.acceptAccountDeletedSuccessfullyAlert();

		log.info("TC_09_DeleteAccount - STEP 15: Open Edit Account form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_ACCOUNT);
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_ACCOUNT));

		log.info("TC_09_DeleteAccount - STEP 16: Input to Account No textbox and click Submit button");
		editAccountPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, secondAccountID);
		editAccountPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_09_DeleteAccount - STEP 17: Verify 'Account does not exist' alert message is displayed");

		log.info("TC_09_DeleteAccount - STEP 18: Accept the alert");
		editAccountPage.acceptAccountNotExistAlert();
	}

	@Test
	public void TC_10_DeleteCustomer() {
		log.info("--------------------- Delete first customer ---------------------");
		
		log.info("TC_10_DeleteCustomer - STEP 01: Open Delete Customer form");
		editAccountPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_DELETE_CUSTOMER);
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_DELETE_CUSTOMER));
		
		log.info("TC_10_DeleteCustomer - STEP 02: Input to Customer ID textbox and click Submit button");
		deleteCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, firstCustomerID);
		deleteCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_10_DeleteCustomer - STEP 03: Accept the alert");
		deleteCustomerPage.acceptDeleteCustomerConfirmationAlert();

		log.info("TC_10_DeleteCustomer - STEP 04: Verify 'Customer deleleted successfully' alert message is displayed");
		verifyTrue(deleteCustomerPage.isDynamicAlertDisplayed(driver, customerData.getFirstCustomerDeletingResultMessage()));

		log.info("TC_10_DeleteCustomer - STEP 05: Accept the alert");
		homePage = deleteCustomerPage.acceptCustomerDeletedSuccessfullyAlert();

		log.info("TC_10_DeleteCustomer - STEP 06: Open Edit Customer form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_CUSTOMER);
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_10_DeleteCustomer - STEP 07: Input to Customer ID textbox and click Submit button");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, firstCustomerID);
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_10_DeleteCustomer - STEP 08: Verify 'Customer does not exist!!' alert message is displayed");
		verifyTrue(editCustomerPage.isDynamicAlertDisplayed(driver, Constants.TEXT_OF_CUSTOMER_NOT_EXIST_ALERT));

		log.info("TC_10_DeleteCustomer - STEP 09: Accept the alert");
		editCustomerPage.acceptCustomerNotExistAlert();
		
		log.info("--------------------- Delete second customer ---------------------");
		
		log.info("TC_10_DeleteCustomer - STEP 10: Open Delete Customer form");
		editAccountPage.openMultiplePage(driver, Constants.PAGE_NAME_OF_DELETE_CUSTOMER);
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_DELETE_CUSTOMER));

		log.info("TC_10_DeleteCustomer - STEP 11: Input to Customer ID textbox and click Submit button");
		deleteCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, secondCustomerID);
		deleteCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_10_DeleteCustomer - STEP 12: Accept the alert");
		deleteCustomerPage.acceptDeleteCustomerConfirmationAlert();

		log.info("TC_10_DeleteCustomer - STEP 13: Verify 'Customer deleleted successfully' alert message is displayed");
		verifyTrue(deleteCustomerPage.isDynamicAlertDisplayed(driver, customerData.getSecondCustomerDeletingResultMessage()));

		log.info("TC_10_DeleteCustomer - STEP 14: Accept the alert");
		homePage = deleteCustomerPage.acceptCustomerDeletedSuccessfullyAlert();

		log.info("TC_10_DeleteCustomer - STEP 15: Open Edit Customer form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_EDIT_CUSTOMER);
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.FORM_TITLE_OF_EDIT_CUSTOMER));

		log.info("TC_10_DeleteCustomer - STEP 16: Input to Customer ID textbox and click Submit button");
		editCustomerPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CUSTOMER_ID_FIELD, secondCustomerID);
		editCustomerPage.clickDynamicButton(driver, Constants.NAME_ATTRIBUTE_OF_ACC_SUBMIT_BUTTON);

		log.info("TC_10_DeleteCustomer - STEP 17: Verify 'Customer does not exist!!' alert message is displayed");
		verifyTrue(editCustomerPage.isDynamicAlertDisplayed(driver, Constants.TEXT_OF_CUSTOMER_NOT_EXIST_ALERT));

		log.info("TC_10_DeleteCustomer - STEP 18: Accept the alert");
		editCustomerPage.acceptCustomerNotExistAlert();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
