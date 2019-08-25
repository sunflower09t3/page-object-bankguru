package com.bankguru.payment;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import bankguru.AccountData;
import bankguru.CustomerData;
import bankguru.PaymentData;
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
	String firstCustomerEmail, secondCustomerEmail;
	int expectedBalance;
	
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

	@Parameters("browser")
	@BeforeClass
	public void setup(String browserName) {
		firstCustomerEmail = String.format(CustomerData.NewCustomer.FIRST_CUSTOMER_EMAIL, randomNumber());
		secondCustomerEmail = String.format(CustomerData.NewCustomer.SECOND_CUSTOMER_EMAIL, randomNumber());
		
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
		newCustomerPage.inputToDynamicTextbox(driver, "name", CustomerData.NewCustomer.FIRST_CUSTOMER_NAME);
		newCustomerPage.selectDynamicRadioButton(driver, CustomerData.NewCustomer.FIRST_CUSTOMER_GENDER.substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, "dob", CustomerData.NewCustomer.FIRST_CUSTOMER_DATE_OF_BIRTH);
		newCustomerPage.inputToDynamicTextarea(driver, "addr", CustomerData.NewCustomer.FIRST_CUSTOMER_ADDRESS);
		newCustomerPage.inputToDynamicTextbox(driver, "city", CustomerData.NewCustomer.FIRST_CUSTOMER_CITY);
		newCustomerPage.inputToDynamicTextbox(driver, "state", CustomerData.NewCustomer.FIRST_CUSTOMER_STATE);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", CustomerData.NewCustomer.FIRST_CUSTOMER_PIN);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", CustomerData.NewCustomer.FIRST_CUSTOMER_TELEPHONE);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", firstCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", CustomerData.NewCustomer.FIRST_CUSTOMER_PASSWORD);
		newCustomerPage.clickDynamicButton(driver, "sub");

		log.info("TC_01_CreateNewCustomer - STEP 03: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer Registered Successfully!!!"));

		log.info("TC_01_CreateNewCustomer - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Customer Name"), CustomerData.NewCustomer.FIRST_CUSTOMER_NAME);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Gender"), CustomerData.NewCustomer.FIRST_CUSTOMER_GENDER);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Birthdate"), CustomerData.NewCustomer.FIRST_CUSTOMER_DATE_OF_BIRTH);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Address"), CustomerData.NewCustomer.FIRST_CUSTOMER_ADDRESS);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "City"), CustomerData.NewCustomer.FIRST_CUSTOMER_CITY);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "State"), CustomerData.NewCustomer.FIRST_CUSTOMER_STATE);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Pin"), CustomerData.NewCustomer.FIRST_CUSTOMER_PIN);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Mobile No."), CustomerData.NewCustomer.FIRST_CUSTOMER_TELEPHONE);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Email"), firstCustomerEmail);
		firstCustomerID = newCustomerPage.getDanymicDataInTable(driver, "Customer ID");
		
		log.info("--------------------- Create second customer ---------------------");
		
		log.info("TC_01_CreateNewCustomer - STEP 05: Open Add New Customer page");
		newCustomerPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));
		
		log.info("TC_01_CreateNewCustomer - STEP 06: Input valid data to Add New Customer form and click Submit button");
		newCustomerPage.inputToDynamicTextbox(driver, "name", CustomerData.NewCustomer.SECOND_CUSTOMER_NAME);
		newCustomerPage.selectDynamicRadioButton(driver, CustomerData.NewCustomer.SECOND_CUSTOMER_GENDER.substring(0, 1));
		newCustomerPage.inputToDynamicTextbox(driver, "dob", CustomerData.NewCustomer.SECOND_CUSTOMER_DATE_OF_BIRTH);
		newCustomerPage.inputToDynamicTextarea(driver, "addr", CustomerData.NewCustomer.SECOND_CUSTOMER_ADDRESS);
		newCustomerPage.inputToDynamicTextbox(driver, "city", CustomerData.NewCustomer.SECOND_CUSTOMER_CITY);
		newCustomerPage.inputToDynamicTextbox(driver, "state", CustomerData.NewCustomer.SECOND_CUSTOMER_STATE);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", CustomerData.NewCustomer.SECOND_CUSTOMER_PIN);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", CustomerData.NewCustomer.SECOND_CUSTOMER_TELEPHONE);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", secondCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", CustomerData.NewCustomer.SECOND_CUSTOMER_PASSWORD);
		newCustomerPage.clickDynamicButton(driver, "sub");
		
		log.info("TC_01_CreateNewCustomer - STEP 07: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer Registered Successfully!!!"));

		log.info("TC_01_CreateNewCustomer - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Customer Name"), CustomerData.NewCustomer.SECOND_CUSTOMER_NAME);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Gender"), CustomerData.NewCustomer.SECOND_CUSTOMER_GENDER);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Birthdate"), CustomerData.NewCustomer.SECOND_CUSTOMER_DATE_OF_BIRTH);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Address"), CustomerData.NewCustomer.SECOND_CUSTOMER_ADDRESS);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "City"), CustomerData.NewCustomer.SECOND_CUSTOMER_CITY);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "State"), CustomerData.NewCustomer.SECOND_CUSTOMER_STATE);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Pin"), CustomerData.NewCustomer.SECOND_CUSTOMER_PIN);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Mobile No."), CustomerData.NewCustomer.SECOND_CUSTOMER_TELEPHONE);
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
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "name"), CustomerData.NewCustomer.FIRST_CUSTOMER_NAME);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "gender"), CustomerData.NewCustomer.FIRST_CUSTOMER_GENDER);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "dob"), CustomerData.NewCustomer.FIRST_CUSTOMER_DATE_OF_BIRTH);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextarea(driver, "addr"), CustomerData.NewCustomer.FIRST_CUSTOMER_ADDRESS);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "city"), CustomerData.NewCustomer.FIRST_CUSTOMER_CITY);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "state"), CustomerData.NewCustomer.FIRST_CUSTOMER_STATE);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "pinno"), CustomerData.NewCustomer.FIRST_CUSTOMER_PIN);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "telephoneno"), CustomerData.NewCustomer.FIRST_CUSTOMER_TELEPHONE);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "emailid"), firstCustomerEmail);

		log.info("TC_02_EditCustomer - STEP 04: Input to all editable fields and click Submit");
		editCustomerPage.inputToDynamicTextarea(driver, "addr", CustomerData.EditCustomer.ADDRESS);
		editCustomerPage.inputToDynamicTextbox(driver, "city", CustomerData.EditCustomer.CITY);
		editCustomerPage.inputToDynamicTextbox(driver, "state", CustomerData.EditCustomer.STATE);
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", CustomerData.EditCustomer.PIN);
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", CustomerData.EditCustomer.TELEPHONE);
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", CustomerData.EditCustomer.EMAIL);
		editCustomerPage.clickDynamicButton(driver, "sub");

		log.info("TC_02_EditCustomer - STEP 05: Verify 'Customer details updated Successfully!!!' message is displayed");
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer details updated Successfully!!!"));

		log.info("TC_02_EditCustomer - STEP 06: Verify actual data and expected data are matching");
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Address"), CustomerData.EditCustomer.ADDRESS);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "City"), CustomerData.EditCustomer.CITY);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "State"), CustomerData.EditCustomer.STATE);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Pin"), CustomerData.EditCustomer.PIN);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Mobile No."), CustomerData.EditCustomer.TELEPHONE);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Email"), CustomerData.EditCustomer.EMAIL);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Customer ID"), firstCustomerID);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Customer Name"), CustomerData.NewCustomer.FIRST_CUSTOMER_NAME);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Gender"), CustomerData.NewCustomer.FIRST_CUSTOMER_GENDER);
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
		newAccountPage.selectItemInDynamicDropdown(driver, AccountData.NewAccount.FIRST_ACCOUNT_TYPE, "selaccount");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", String.valueOf(AccountData.NewAccount.FIRST_ACCOUNT_INITIAL_DEPOSIT));
		newAccountPage.clickDynamicButton(driver, "button2");

		log.info("TC_03_CreateNewAccount - STEP 03: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account Generated Successfully!!!"));

		log.info("TC_03_CreateNewAccount - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer ID"), firstCustomerID);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer Name"), CustomerData.NewCustomer.FIRST_CUSTOMER_NAME);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Email"), CustomerData.EditCustomer.EMAIL);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Account Type"), AccountData.NewAccount.FIRST_ACCOUNT_TYPE);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Date of Opening"), formatDate(getCurrentDate(), "yyyy-MM-dd"));
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(AccountData.NewAccount.FIRST_ACCOUNT_INITIAL_DEPOSIT));
		firstAccountID = newAccountPage.getDanymicDataInTable(driver, "Account ID");
		firstAccountOpeningDate = newAccountPage.getDanymicDataInTable(driver, "Date of Opening");
		
		log.info("--------------------- Create a new account for second customer ---------------------");
		
		log.info("TC_03_CreateNewAccount - STEP 05: Open Add new account form");
		newAccountPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));
		
		log.info("TC_03_CreateNewAccount - STEP 06: Input valid data to Add New Account form and click Submit");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", secondCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, AccountData.NewAccount.SECOND_ACCOUNT_TYPE, "selaccount");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", String.valueOf(AccountData.NewAccount.SECOND_ACCOUNT_INITIAL_DEPOSIT));
		newAccountPage.clickDynamicButton(driver, "button2");
		
		log.info("TC_03_CreateNewAccount - STEP 07: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account Generated Successfully!!!"));

		log.info("TC_03_CreateNewAccount - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer ID"), secondCustomerID);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer Name"), CustomerData.NewCustomer.SECOND_CUSTOMER_NAME);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Email"), secondCustomerEmail);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Account Type"), AccountData.NewAccount.SECOND_ACCOUNT_TYPE);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Date of Opening"), formatDate(getCurrentDate(), "yyyy-MM-dd"));
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(AccountData.NewAccount.SECOND_ACCOUNT_INITIAL_DEPOSIT));
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
		verifyEquals(editAccountPage.getCurrentSelectedItemInDynamicDropdown(driver, "a_type"), AccountData.NewAccount.FIRST_ACCOUNT_TYPE);
		verifyEquals(editAccountPage.getTextValueInDynamicTextbox(driver, "txtinitdep"), String.valueOf(AccountData.NewAccount.FIRST_ACCOUNT_INITIAL_DEPOSIT));

		log.info("TC_04_EditAccout - STEP 04: Change Account Type and click Submit");
		editAccountPage.selectItemInDynamicDropdown(driver, AccountData.EditAccount.ACCOUNT_TYPE, "a_type");
		editAccountPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_04_EditAccout - STEP 05: Verify 'Account details updated Successfully!!!' message is displayed");
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account details updated Successfully!!!"));

		log.info("TC_04_EditAccout - STEP 06: Verify actual data and expected data are matching");
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Account ID"), firstAccountID);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Customer ID"), firstCustomerID);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Customer Name"), CustomerData.NewCustomer.FIRST_CUSTOMER_NAME);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Email"), CustomerData.EditCustomer.EMAIL);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Account Type"), AccountData.EditAccount.ACCOUNT_TYPE);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Date of Opening"), firstAccountOpeningDate);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(AccountData.NewAccount.FIRST_ACCOUNT_INITIAL_DEPOSIT));

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
		depositPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(PaymentData.Deposit.AMOUNT));
		depositPage.inputToDynamicTextbox(driver, "desc", PaymentData.Deposit.DESCRIPTION);
		depositPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_05_Deposit - STEP 03: Verify 'Transaction details of Deposit for Account account_id' message is displayed");
		verifyTrue(depositPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Transaction details of Deposit for Account " + firstAccountID));

		log.info("TC_05_Deposit - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Account No"), firstAccountID);
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Amount Credited"), String.valueOf(PaymentData.Deposit.AMOUNT));
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Type of Transaction"), "Deposit");
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Description"), PaymentData.Deposit.DESCRIPTION);
		expectedBalance = AccountData.NewAccount.FIRST_ACCOUNT_INITIAL_DEPOSIT + PaymentData.Deposit.AMOUNT;
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
		withdrawalPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(PaymentData.Withdrawl.AMOUNT));
		withdrawalPage.inputToDynamicTextbox(driver, "desc", PaymentData.Withdrawl.DESCRIPTION);
		withdrawalPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_06_Withdraw - STEP 03: Verify 'Transaction details of Withdrawal for Account account_id' message is displayed");
		verifyTrue(withdrawalPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Transaction details of Withdrawal for Account " + firstAccountID));

		log.info("TC_06_Withdraw - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Account No"), firstAccountID);
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Amount Debited"), String.valueOf(PaymentData.Withdrawl.AMOUNT));
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Type of Transaction"), "Withdrawal");
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Description"), PaymentData.Withdrawl.DESCRIPTION);
		expectedBalance = AccountData.NewAccount.FIRST_ACCOUNT_INITIAL_DEPOSIT + PaymentData.Deposit.AMOUNT - PaymentData.Withdrawl.AMOUNT;
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
		fundTransferPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(PaymentData.FundTransfer.AMOUNT));
		fundTransferPage.inputToDynamicTextbox(driver, "desc", PaymentData.FundTransfer.DESCRIPTION);
		fundTransferPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_07_FundTransfer - STEP 03: Verify 'Fund Transfer Details' message is displayed");
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund Transfer Details"));

		log.info("TC_07_FundTransfer - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "From Account Number"), firstAccountID);
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "To Account Number"), secondAccountID);
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "Amount"), String.valueOf(PaymentData.FundTransfer.AMOUNT));
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "Description"), PaymentData.FundTransfer.DESCRIPTION);
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
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Type of Account"), AccountData.EditAccount.ACCOUNT_TYPE);
		expectedBalance = AccountData.NewAccount.FIRST_ACCOUNT_INITIAL_DEPOSIT + PaymentData.Deposit.AMOUNT - PaymentData.Withdrawl.AMOUNT - PaymentData.FundTransfer.AMOUNT;
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
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Type of Account"), AccountData.NewAccount.SECOND_ACCOUNT_TYPE);
		expectedBalance = AccountData.NewAccount.SECOND_ACCOUNT_INITIAL_DEPOSIT + PaymentData.FundTransfer.AMOUNT;
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
