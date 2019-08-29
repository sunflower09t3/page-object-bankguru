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
		
		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);

		homePage = loginPage.clickLoginButton();
	}

	@Test
	public void TC_01_CreateNewCustomer() {
		log.info("--------------------- Create first customer ---------------------");
		
		log.info("TC_01_CreateNewCustomer - STEP 01: Open New Customer page");
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));

		log.info("TC_01_CreateNewCustomer - STEP 02: Input valid data to Add New Customer form and click Submit button");
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerData.getFirstCustomerName());
		newCustomerPage.selectDynamicRadioButton(driver, customerData.getFirstCustomerGender().substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, "dob", customerData.getFirstCustomerDateOfBirth());
		newCustomerPage.inputToDynamicTextarea(driver, "addr", customerData.getFirstCustomerAddress());
		newCustomerPage.inputToDynamicTextbox(driver, "city", customerData.getFirstCustomerCity());
		newCustomerPage.inputToDynamicTextbox(driver, "state", customerData.getFirstCustomerState());
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", customerData.getFirstCustomerPin());
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", customerData.getFirstCustomerTelephone());
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", firstCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", customerData.getFirstCustomerPassword());
		newCustomerPage.clickDynamicButton(driver, "sub");

		log.info("TC_01_CreateNewCustomer - STEP 03: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer Registered Successfully!!!"));

		log.info("TC_01_CreateNewCustomer - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Customer Name"), customerData.getFirstCustomerName());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Gender"), customerData.getFirstCustomerGender());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Birthdate"), customerData.getFirstCustomerDateOfBirth());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Address"), customerData.getFirstCustomerAddress());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "City"), customerData.getFirstCustomerCity());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "State"), customerData.getFirstCustomerState());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Pin"), customerData.getFirstCustomerPin());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Mobile No."), customerData.getFirstCustomerTelephone());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Email"), firstCustomerEmail);
		firstCustomerID = newCustomerPage.getDanymicDataInTable(driver, "Customer ID");
		
		log.info("--------------------- Create second customer ---------------------");
		
		log.info("TC_01_CreateNewCustomer - STEP 05: Open Add New Customer page");
		newCustomerPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));
		
		log.info("TC_01_CreateNewCustomer - STEP 06: Input valid data to Add New Customer form and click Submit button");
		newCustomerPage.inputToDynamicTextbox(driver, "name", customerData.getSecondCustomerName());
		newCustomerPage.selectDynamicRadioButton(driver, customerData.getSecondCustomerGender().substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, "dob", customerData.getSecondCustomerDateOfBirth());
		newCustomerPage.inputToDynamicTextarea(driver, "addr", customerData.getSecondCustomerAddress());
		newCustomerPage.inputToDynamicTextbox(driver, "city", customerData.getSecondCustomerCity());
		newCustomerPage.inputToDynamicTextbox(driver, "state", customerData.getSecondCustomerState());
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", customerData.getSecondCustomerPin());
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", customerData.getSecondCustomerTelephone());
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", secondCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", customerData.getSecondCustomerPassword());
		newCustomerPage.clickDynamicButton(driver, "sub");
		
		log.info("TC_01_CreateNewCustomer - STEP 07: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer Registered Successfully!!!"));

		log.info("TC_01_CreateNewCustomer - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Customer Name"), customerData.getSecondCustomerName());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Gender"), customerData.getSecondCustomerGender());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Birthdate"), customerData.getSecondCustomerDateOfBirth());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Address"), customerData.getSecondCustomerAddress());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "City"), customerData.getSecondCustomerCity());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "State"), customerData.getSecondCustomerState());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Pin"), customerData.getSecondCustomerPin());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Mobile No."), customerData.getSecondCustomerTelephone());
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Email"), secondCustomerEmail);
		secondCustomerID = newCustomerPage.getDanymicDataInTable(driver, "Customer ID");

	}

	@Test
	public void TC_02_EditCustomer() {
		log.info("--------------------- Edit first customer ---------------------");
		
		log.info("TC_02_EditCustomer - STEP 01: Open Edit Customer form");
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));

		log.info("TC_02_EditCustomer - STEP 02: Input the custoner ID and click Submit");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", firstCustomerID);
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_02_EditCustomer - STEP 03: Verify actual data and expected data are matching");
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "name"), customerData.getFirstCustomerName());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "gender"), customerData.getFirstCustomerGender());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "dob"), customerData.getFirstCustomerDateOfBirth());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextarea(driver, "addr"), customerData.getFirstCustomerAddress());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "city"), customerData.getFirstCustomerCity());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "state"), customerData.getFirstCustomerState());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "pinno"), customerData.getFirstCustomerPin());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "telephoneno"), customerData.getFirstCustomerTelephone());
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "emailid"), firstCustomerEmail);

		log.info("TC_02_EditCustomer - STEP 04: Input to all editable fields and click Submit");
		editCustomerPage.inputToDynamicTextarea(driver, "addr", customerData.getFirstCustomerEditedAddress());
		editCustomerPage.inputToDynamicTextbox(driver, "city", customerData.getFirstCustomerEditedCity());
		editCustomerPage.inputToDynamicTextbox(driver, "state", customerData.getFirstCustomerEditedState());
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", customerData.getFirstCustomerEditedPin());
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", customerData.getFirstCustomerEditedTelephone());
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", firstCustomerEditedEmail);
		editCustomerPage.clickDynamicButton(driver, "sub");

		log.info("TC_02_EditCustomer - STEP 05: Verify 'Customer details updated Successfully!!!' message is displayed");
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer details updated Successfully!!!"));

		log.info("TC_02_EditCustomer - STEP 06: Verify actual data and expected data are matching");
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Address"), customerData.getFirstCustomerEditedAddress());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "City"), customerData.getFirstCustomerEditedCity());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "State"), customerData.getFirstCustomerEditedState());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Pin"), customerData.getFirstCustomerEditedPin());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Mobile No."), customerData.getFirstCustomerEditedTelephone());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Email"), firstCustomerEditedEmail);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Customer ID"), firstCustomerID);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Customer Name"), customerData.getFirstCustomerName());
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Gender"), customerData.getFirstCustomerGender());
	}

	@Test
	public void TC_03_CreateNewAccouDnt() {
		log.info("--------------------- Create a new account for first customer ---------------------");
		
		log.info("TC_03_CreateNewAccount - STEP 01: Open New Account form");
		editCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_03_CreateNewAccount - STEP 02: Input valid data to Add New Account form and click Submit");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", firstCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, accountData.getFirstAccountType(), "selaccount");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", String.valueOf(accountData.getFirstAccountInitialDeposit()));
		newAccountPage.clickDynamicButton(driver, "button2");

		log.info("TC_03_CreateNewAccount - STEP 03: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account Generated Successfully!!!"));

		log.info("TC_03_CreateNewAccount - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer ID"), firstCustomerID);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer Name"), customerData.getFirstCustomerName());
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Email"), firstCustomerEditedEmail);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Account Type"), accountData.getFirstAccountType());
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Date of Opening"), formatDate(getCurrentDate(), "yyyy-MM-dd"));
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(accountData.getFirstAccountInitialDeposit()));
		firstAccountID = newAccountPage.getDanymicDataInTable(driver, "Account ID");
		firstAccountOpeningDate = newAccountPage.getDanymicDataInTable(driver, "Date of Opening");
		
		log.info("--------------------- Create a new account for second customer ---------------------");
		
		log.info("TC_03_CreateNewAccount - STEP 05: Open Add new account form");
		newAccountPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));
		
		log.info("TC_03_CreateNewAccount - STEP 06: Input valid data to Add New Account form and click Submit");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", secondCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, accountData.getSecondAccountType(), "selaccount");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", String.valueOf(accountData.getSecondAccountInitialDeposit()));
		newAccountPage.clickDynamicButton(driver, "button2");
		
		log.info("TC_03_CreateNewAccount - STEP 07: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account Generated Successfully!!!"));

		log.info("TC_03_CreateNewAccount - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer ID"), secondCustomerID);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer Name"), customerData.getSecondCustomerName());
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Email"), secondCustomerEmail);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Account Type"), accountData.getSecondAccountType());
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Date of Opening"), formatDate(getCurrentDate(), "yyyy-MM-dd"));
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(accountData.getSecondAccountInitialDeposit()));
		secondAccountID = newAccountPage.getDanymicDataInTable(driver, "Account ID");
	}

	@Test
	public void TC_04_EditAccout() {
		log.info("--------------------- Edit account of first customer ---------------------");
		
		log.info("TC_04_EditAccout - STEP 01: Open Edit Account form");
		newAccountPage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_04_EditAccout - STEP 02: Input account ID and click Submi");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", firstAccountID);
		editAccountPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_04_EditAccout - STEP 03: Verify actual data and expected data are matching");
		verifyEquals(editAccountPage.getTextValueInDynamicTextbox(driver, "txtcid"), firstCustomerID);
		verifyEquals(editAccountPage.getCurrentSelectedItemInDynamicDropdown(driver, "a_type"), accountData.getFirstAccountType());
		verifyEquals(editAccountPage.getTextValueInDynamicTextbox(driver, "txtinitdep"), String.valueOf(accountData.getFirstAccountInitialDeposit()));

		log.info("TC_04_EditAccout - STEP 04: Change Account Type and click Submit");
		editAccountPage.selectItemInDynamicDropdown(driver, accountData.getFirstAccountEditedType(), "a_type");
		editAccountPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_04_EditAccout - STEP 05: Verify 'Account details updated Successfully!!!' message is displayed");
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account details updated Successfully!!!"));

		log.info("TC_04_EditAccout - STEP 06: Verify actual data and expected data are matching");
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Account ID"), firstAccountID);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Customer ID"), firstCustomerID);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Customer Name"), customerData.getFirstCustomerName());
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Email"), firstCustomerEditedEmail);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Account Type"), accountData.getFirstAccountEditedType());
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Date of Opening"), firstAccountOpeningDate);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(accountData.getFirstAccountInitialDeposit()));

	}

	@Test
	public void TC_05_Deposit() {
		log.info("--------------------- Deposit money to first customer's account ---------------------");
		
		log.info("TC_05_Deposit - STEP 01: Open Amount Deposit Form");
		editAccountPage.openMultiplePage(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);
		verifyTrue(depositPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Amount Deposit Form"));

		log.info("TC_05_Deposit - STEP 02: Input valid data to Amount Deposit form and click Submit");
		depositPage.inputToDynamicTextbox(driver, "accountno", firstAccountID);
		depositPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(accountData.getFirstAccountDepositAmount()));
		depositPage.inputToDynamicTextbox(driver, "desc", accountData.getFirstAccountDepositDescription());
		depositPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_05_Deposit - STEP 03: Verify 'Transaction details of Deposit for Account account_id' message is displayed");
		verifyTrue(depositPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Transaction details of Deposit for Account " + firstAccountID));

		log.info("TC_05_Deposit - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Account No"), firstAccountID);
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Amount Credited"), String.valueOf(accountData.getFirstAccountDepositAmount()));
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Type of Transaction"), "Deposit");
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Description"), accountData.getFirstAccountDepositDescription());
		expectedBalance = accountData.getFirstAccountInitialDeposit() + accountData.getFirstAccountDepositAmount();
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Current Balance"), String.valueOf(expectedBalance));

	}

	@Test
	public void TC_06_Withdraw() {
		log.info("--------------------- Withdraw money from first customer's account ---------------------");
		
		log.info("TC_06_Withdraw - STEP 01: Open Amount Withdrawal Form");
		depositPage.openMultiplePage(driver, "Withdrawal");
		withdrawalPage = PageGeneratorManager.getWithdrawlPage(driver);
		verifyTrue(withdrawalPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Amount Withdrawal Form"));

		log.info("TC_06_Withdraw - STEP 02: Input valid data to Amount Withdrawal form");
		withdrawalPage.inputToDynamicTextbox(driver, "accountno", firstAccountID);
		withdrawalPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(accountData.getFirstAccountWithdrawAmount()));
		withdrawalPage.inputToDynamicTextbox(driver, "desc", accountData.getFirstAccountWithdrawDescription());
		withdrawalPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_06_Withdraw - STEP 03: Verify 'Transaction details of Withdrawal for Account account_id' message is displayed");
		verifyTrue(withdrawalPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Transaction details of Withdrawal for Account " + firstAccountID));

		log.info("TC_06_Withdraw - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Account No"), firstAccountID);
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Amount Debited"), String.valueOf(accountData.getFirstAccountWithdrawAmount()));
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Type of Transaction"), "Withdrawal");
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Description"), accountData.getFirstAccountWithdrawDescription());
		expectedBalance = accountData.getFirstAccountInitialDeposit() + accountData.getFirstAccountDepositAmount() - accountData.getFirstAccountWithdrawAmount();
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Current Balance"), String.valueOf(expectedBalance));
	}

	@Test
	public void TC_07_FundTransfer() {
		log.info("--------------------- Transfer money from first customer's account to second customer's account ---------------------");
		
		log.info("TC_07_FundTransfer - STEP 01: Open Fund Transfer page");
		withdrawalPage.openMultiplePage(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund transfer"));

		log.info("TC_07_FundTransfer - STEP 02: Input valid data to Fund Transfer form and click Submit");
		fundTransferPage.inputToDynamicTextbox(driver, "payersaccount", firstAccountID);
		fundTransferPage.inputToDynamicTextbox(driver, "payeeaccount", secondAccountID);
		fundTransferPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(accountData.getFirstAccountTransferAmount()));
		fundTransferPage.inputToDynamicTextbox(driver, "desc", accountData.getFirstAccountTransferDescription());
		fundTransferPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_07_FundTransfer - STEP 03: Verify 'Fund Transfer Details' message is displayed");
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund Transfer Details"));

		log.info("TC_07_FundTransfer - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "From Account Number"), firstAccountID);
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "To Account Number"), secondAccountID);
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "Amount"), String.valueOf(accountData.getFirstAccountTransferAmount()));
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "Description"), accountData.getFirstAccountTransferDescription());
	}

	@Test
	public void TC_08_BalanceEnquiry() {
		log.info("--------------------- Check balance of first customer's account ---------------------");
		
		log.info("TC_08_BalanceEnquiry - STEP 01: Open Balance Enquiry form");
		fundTransferPage.openMultiplePage(driver, "Balance Enquiry");
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Balance Enquiry Form"));

		log.info("TC_08_BalanceEnquiry - STEP 02: Input to Account No textbox and click Submit");
		balanceEnquiryPage.inputToDynamicTextbox(driver, "accountno", firstAccountID);
		balanceEnquiryPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_08_BalanceEnquiry - STEP 03: Verify 'Balance Details for Account account_id' message is displayed");
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Balance Details for Account " + firstAccountID));

		log.info("TC_08_BalanceEnquiry - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Account No"), firstAccountID);
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Type of Account"), accountData.getFirstAccountEditedType());
		expectedBalance = accountData.getFirstAccountInitialDeposit() + accountData.getFirstAccountDepositAmount() - accountData.getFirstAccountWithdrawAmount() - accountData.getFirstAccountTransferAmount();
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Balance"), String.valueOf(expectedBalance));
		
		log.info("--------------------- Check balance of second customer's account ---------------------");
		
		log.info("TC_08_BalanceEnquiry - STEP 05: Open Balance Enquiry form");
		fundTransferPage.openMultiplePage(driver, "Balance Enquiry");
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Balance Enquiry Form"));

		log.info("TC_08_BalanceEnquiry - STEP 06: Input to Account No textbox and click Submit");
		balanceEnquiryPage.inputToDynamicTextbox(driver, "accountno", secondAccountID);
		balanceEnquiryPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_08_BalanceEnquiry - STEP 07: Verify 'Balance Details for Account account_id' message is displayed");
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Balance Details for Account " + secondAccountID));

		log.info("TC_08_BalanceEnquiry - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Account No"), secondAccountID);
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Type of Account"), accountData.getSecondAccountType());
		expectedBalance = accountData.getSecondAccountInitialDeposit() + accountData.getFirstAccountTransferAmount();
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Balance"), String.valueOf(expectedBalance));
	}

	@Test
	public void TC_09_DeleteAccount() {
		log.info("--------------------- Delete account of first customer ---------------------");
		
		log.info("TC_09_DeleteAccount - STEP 01: Open Delete Account form");
		balanceEnquiryPage.openMultiplePage(driver, "Delete Account");
		deleteAccountPage = PageGeneratorManager.getDeleteAccountpage(driver);
		verifyTrue(deleteAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Account Form"));

		log.info("TC_09_DeleteAccount - STEP 02: Input to Account No textbox and click Submit button");
		deleteAccountPage.inputToDynamicTextbox(driver, "accountno", firstAccountID);
		deleteAccountPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_09_DeleteAccount - STEP 03: Verify 'Do you really want to delete this Account?' alert message is displayed");
		deleteAccountPage.isDynamicAlertDisplayed(driver, "Do you really want to delete this Account?");
		
		log.info("TC_09_DeleteAccount - STEP 03: Accept the alert");
		deleteAccountPage.acceptDeleteAccountConfirmationAlert();

		log.info("TC_09_DeleteAccount - STEP 04: Verify 'Account Deleted Sucessfully' alert message is displayed");
		verifyTrue(deleteAccountPage.isDynamicAlertDisplayed(driver, "Account Deleted Sucessfully"));

		log.info("TC_09_DeleteAccount - STEP 05: Accept the alert");
		homePage = deleteAccountPage.acceptAccountDeletedSuccessfullyAlert();

		log.info("TC_09_DeleteAccount - STEP 06: Open Edit Account form");
		homePage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_09_DeleteAccount - STEP 07: Input to Account No textbox and click Submit button");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", firstAccountID);
		editAccountPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_09_DeleteAccount - STEP 08: Verify 'Account does not exist' alert message is displayed");
		verifyTrue(editAccountPage.isDynamicAlertDisplayed(driver, "Account does not exist"));

		log.info("TC_09_DeleteAccount - STEP 09: Accept the alert");
		editAccountPage.acceptAccountNotExistAlert();
		
		log.info("--------------------- Delete account of second customer ---------------------");
		
		log.info("TC_09_DeleteAccount - STEP 10: Open Delete Account form to delete payee account");
		balanceEnquiryPage.openMultiplePage(driver, "Delete Account");
		deleteAccountPage = PageGeneratorManager.getDeleteAccountpage(driver);
		verifyTrue(deleteAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Account Form"));

		log.info("TC_09_DeleteAccount - STEP 11: Input to Account No textbox and click Submit button");
		deleteAccountPage.inputToDynamicTextbox(driver, "accountno", secondAccountID);
		deleteAccountPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_09_DeleteAccount - STEP 12: Accept the alert");
		deleteAccountPage.acceptDeleteAccountConfirmationAlert();

		log.info("TC_09_DeleteAccount - STEP 13: Verify 'Account Deleted Sucessfully' alert message is displayed");
		verifyTrue(deleteAccountPage.isDynamicAlertDisplayed(driver, "Account Deleted Sucessfully"));

		log.info("TC_09_DeleteAccount - STEP 14: Accept the alert");
		homePage = deleteAccountPage.acceptAccountDeletedSuccessfullyAlert();

		log.info("TC_09_DeleteAccount - STEP 15: Open Edit Account form");
		homePage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_09_DeleteAccount - STEP 16: Input to Account No textbox and click Submit button");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", secondAccountID);
		editAccountPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_09_DeleteAccount - STEP 17: Verify 'Account does not exist' alert message is displayed");

		log.info("TC_09_DeleteAccount - STEP 18: Accept the alert");
		editAccountPage.acceptAccountNotExistAlert();
	}

	@Test
	public void TC_10_DeleteCustomer() {
		log.info("--------------------- Delete first customer ---------------------");
		
		log.info("TC_10_DeleteCustomer - STEP 01: Open Delete Customer form");
		editAccountPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Customer Form"));
		
		log.info("TC_10_DeleteCustomer - STEP 02: Input to Customer ID textbox and click Submit button");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", firstCustomerID);
		deleteCustomerPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_10_DeleteCustomer - STEP 03: Accept the alert");
		deleteCustomerPage.acceptDeleteCustomerConfirmationAlert();

		log.info("TC_10_DeleteCustomer - STEP 04: Verify 'Customer deleleted successfully' alert message is displayed");
		verifyTrue(deleteCustomerPage.isDynamicAlertDisplayed(driver, "Customer deleted Successfully"));

		log.info("TC_10_DeleteCustomer - STEP 05: Accept the alert");
		homePage = deleteCustomerPage.acceptCustomerDeletedSuccessfullyAlert();

		log.info("TC_10_DeleteCustomer - STEP 06: Open Edit Customer form");
		homePage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));

		log.info("TC_10_DeleteCustomer - STEP 07: Input to Customer ID textbox and click Submit button");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", firstCustomerID);
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_10_DeleteCustomer - STEP 08: Verify 'Customer does not exist!!' alert message is displayed");
		verifyTrue(editCustomerPage.isDynamicAlertDisplayed(driver, "Customer does not exist!!"));

		log.info("TC_10_DeleteCustomer - STEP 09: Accept the alert");
		editCustomerPage.acceptCustomerNotExistAlert();
		
		log.info("--------------------- Delete second customer ---------------------");
		
		log.info("TC_10_DeleteCustomer - STEP 10: Open Delete Customer form");
		editAccountPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Customer Form"));

		log.info("TC_10_DeleteCustomer - STEP 11: Input to Customer ID textbox and click Submit button");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", secondCustomerID);
		deleteCustomerPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_10_DeleteCustomer - STEP 12: Accept the alert");
		deleteCustomerPage.acceptDeleteCustomerConfirmationAlert();

		log.info("TC_10_DeleteCustomer - STEP 13: Verify 'Customer deleleted successfully' alert message is displayed");
		verifyTrue(deleteCustomerPage.isDynamicAlertDisplayed(driver, "Customer deleted Successfully"));

		log.info("TC_10_DeleteCustomer - STEP 14: Accept the alert");
		homePage = deleteCustomerPage.acceptCustomerDeletedSuccessfullyAlert();

		log.info("TC_10_DeleteCustomer - STEP 15: Open Edit Customer form");
		homePage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));

		log.info("TC_10_DeleteCustomer - STEP 16: Input to Customer ID textbox and click Submit button");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", secondCustomerID);
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_10_DeleteCustomer - STEP 17: Verify 'Customer does not exist!!' alert message is displayed");
		verifyTrue(editCustomerPage.isDynamicAlertDisplayed(driver, "Customer does not exist!!"));

		log.info("TC_10_DeleteCustomer - STEP 18: Accept the alert");
		editCustomerPage.acceptCustomerNotExistAlert();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
