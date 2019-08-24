package com.bankguru.payment;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

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
	String newCustomerID, newCustomerName, newCustomerAddress, newCustomerState;
	String newCustomerCity, newCustomerPIN, newCustomerTelephone, newCustomerEmail, newCustomerPassword, newCustomerGender;
	String anotherCustomerID, anotherCustomerName, anotherCustomerGender, anotherCustomerAddress, anotherCustomerState;
	String anotherCustomerCity, anotherCustomerPIN, anotherCustomerTelephone, anotherCustomerEmail, anotherCustomerPassword;
	String editedCustomerAddress, editedCustomerState, editedCustomerCity, editedCustomerPIN, editedCustomerTelephone, editedCustomerEmail;
	String newAccountID, newAccountAccType, anotherAccountID, anotherAccountAccType, editedAccountAccType, newAccountOpeningDate;
	String depositDescription, withdrawDescription, fundTransferDescription;
	int newAccountInitialDeposit, anotherAccountInitialDeposit, depositAmount, withdrawAmount, fundTransferAmount, expectedBalance;
	DateTime newCustomerDateOfBirth, anotherCustomerDateOfBirth;
	
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

		// New customer data
		newCustomerName = "Jack";
		newCustomerGender = "female";
		newCustomerDateOfBirth = getParticularDate(1991, 1, 2);
		newCustomerAddress = "PO Box 911 8331 Duis Avenue";
		newCustomerState = "FL";
		newCustomerCity = "Tampa";
		newCustomerPIN = "466250";
		newCustomerTelephone = "4555442476";
		newCustomerEmail = "jack" + randomNumber() + "@gmail.com";
		newCustomerPassword = "firstcustomerpassword";

		// Another new customer data
		anotherCustomerName = "Amanda";
		anotherCustomerGender = "male";
		anotherCustomerDateOfBirth = getParticularDate(2000, 12, 5);
		anotherCustomerAddress = "Blk 136 Potong Pasir Ave 3";
		anotherCustomerState = "Jurong";
		anotherCustomerCity = "Singapore";
		anotherCustomerPIN = "778855";
		anotherCustomerTelephone = "1478965236";
		anotherCustomerEmail = "amanda" + randomNumber() + "@gmail.com";
		anotherCustomerPassword = "secondcustomerpassword";
		

		// Edit customer data
		editedCustomerAddress = "1883 Cursus Avenue";
		editedCustomerCity = "Houston";
		editedCustomerState = "Texas";
		editedCustomerPIN = "166455";
		editedCustomerTelephone = "1122334455";
		editedCustomerEmail = "JackLe" + randomNumber() + "@gmail.com";

		// New account data
		newAccountAccType = "Savings";
		newAccountInitialDeposit = 50000;

		// Another new account data
		anotherAccountAccType = "Savings";
		anotherAccountInitialDeposit = 100000;

		// Edit account data
		editedAccountAccType = "Current";

		// Deposit data
		depositAmount = 5000;
		depositDescription = "Deposit";

		// Withdraw data
		withdrawAmount = 15000;
		withdrawDescription = "Withdraw";

		// Fund transfer data
		fundTransferAmount = 10000;
		fundTransferDescription = "Transfer";

		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		// Login
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
		newCustomerPage.inputToDynamicTextbox(driver, "name", newCustomerName);
		newCustomerPage.selectDynamicRadioButton(driver, "f");
		newCustomerPage.inputToDynamicTextbox(driver, "dob", formatDate(newCustomerDateOfBirth, "MM/dd/yyyy"));
		newCustomerPage.inputToDynamicTextarea(driver, "addr", newCustomerAddress);
		newCustomerPage.inputToDynamicTextbox(driver, "city", newCustomerCity);
		newCustomerPage.inputToDynamicTextbox(driver, "state", newCustomerState);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", newCustomerPIN);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", newCustomerTelephone);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", newCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", newCustomerPassword);
		newCustomerPage.clickDynamicButton(driver, "sub");

		log.info("TC_01_CreateNewCustomer - STEP 03: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer Registered Successfully!!!"));

		log.info("TC_01_CreateNewCustomer - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Customer Name"), newCustomerName);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Gender"), newCustomerGender);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Birthdate"), formatDate(newCustomerDateOfBirth, "yyyy-MM-dd"));
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Address"), newCustomerAddress);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "City"), newCustomerCity);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "State"), newCustomerState);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Pin"), newCustomerPIN);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Mobile No."), newCustomerTelephone);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Email"), newCustomerEmail);
		newCustomerID = newCustomerPage.getDanymicDataInTable(driver, "Customer ID");
		
		log.info("--------------------- Create second customer ---------------------");
		
		log.info("TC_01_CreateNewCustomer - STEP 05: Open Add New Customer page");
		newCustomerPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add New Customer"));
		
		log.info("TC_01_CreateNewCustomer - STEP 06: Input valid data to Add New Customer form and click Submit button");
		newCustomerPage.inputToDynamicTextbox(driver, "name", anotherCustomerName);
		newCustomerPage.selectDynamicRadioButton(driver, "m");
		newCustomerPage.inputToDynamicTextbox(driver, "dob", formatDate(anotherCustomerDateOfBirth, "MM/dd/yyyy"));
		newCustomerPage.inputToDynamicTextarea(driver, "addr", anotherCustomerAddress);
		newCustomerPage.inputToDynamicTextbox(driver, "city", anotherCustomerCity);
		newCustomerPage.inputToDynamicTextbox(driver, "state", anotherCustomerState);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", anotherCustomerPIN);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", anotherCustomerTelephone);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", anotherCustomerEmail);
		newCustomerPage.inputToDynamicTextbox(driver, "password", anotherCustomerPassword);
		newCustomerPage.clickDynamicButton(driver, "sub");
		
		log.info("TC_01_CreateNewCustomer - STEP 07: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer Registered Successfully!!!"));

		log.info("TC_01_CreateNewCustomer - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Customer Name"), anotherCustomerName);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Gender"), anotherCustomerGender);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Birthdate"), formatDate(anotherCustomerDateOfBirth, "yyyy-MM-dd"));
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Address"), anotherCustomerAddress);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "City"), anotherCustomerCity);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "State"), anotherCustomerState);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Pin"), anotherCustomerPIN);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Mobile No."), anotherCustomerTelephone);
		verifyEquals(newCustomerPage.getDanymicDataInTable(driver, "Email"), anotherCustomerEmail);
		anotherCustomerID = newCustomerPage.getDanymicDataInTable(driver, "Customer ID");

	}

	@Test
	public void TC_02_EditCustomer() {
		log.info("--------------------- Edit first customer ---------------------");
		
		log.info("TC_02_EditCustomer - STEP 01: Open Edit Customer form");
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Customer Form"));

		log.info("TC_02_EditCustomer - STEP 02: Input the custoner ID and click Submit");
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		editCustomerPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_02_EditCustomer - STEP 03: Verify actual data and expected data are matching");
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "name"), newCustomerName);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "gender"), newCustomerGender);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "dob"), formatDate(newCustomerDateOfBirth, "yyyy-MM-dd"));
		verifyEquals(editCustomerPage.getTextValueInDynamicTextarea(driver, "addr"), newCustomerAddress);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "city"), newCustomerCity);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "state"), newCustomerState);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "pinno"), newCustomerPIN);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "telephoneno"), newCustomerTelephone);
		verifyEquals(editCustomerPage.getTextValueInDynamicTextbox(driver, "emailid"), newCustomerEmail);

		log.info("TC_02_EditCustomer - STEP 04: Input to all editable fields and click Submit");
		editCustomerPage.inputToDynamicTextarea(driver, "addr", editedCustomerAddress);
		editCustomerPage.inputToDynamicTextbox(driver, "city", editedCustomerCity);
		editCustomerPage.inputToDynamicTextbox(driver, "state", editedCustomerState);
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", editedCustomerPIN);
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", editedCustomerTelephone);
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", editedCustomerEmail);
		editCustomerPage.clickDynamicButton(driver, "sub");

		log.info("TC_02_EditCustomer - STEP 05: Verify 'Customer details updated Successfully!!!' message is displayed");
		verifyTrue(editCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Customer details updated Successfully!!!"));

		log.info("TC_02_EditCustomer - STEP 06: Verify actual data and expected data are matching");
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Address"), editedCustomerAddress);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "City"), editedCustomerCity);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "State"), editedCustomerState);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Pin"), editedCustomerPIN);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Mobile No."), editedCustomerTelephone);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Email"), editedCustomerEmail);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Customer ID"), newCustomerID);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Customer Name"), newCustomerName);
		verifyEquals(editCustomerPage.getDanymicDataInTable(driver, "Gender"), newCustomerGender);
	}

	@Test
	public void TC_03_CreateNewAccount() {
		log.info("--------------------- Create a new account for first customer ---------------------");
		
		log.info("TC_03_CreateNewAccount - STEP 01: Open New Account form");
		editCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));

		log.info("TC_03_CreateNewAccount - STEP 02: Input valid data to Add New Account form and click Submit");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, newAccountAccType, "selaccount");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", String.valueOf(newAccountInitialDeposit));
		newAccountPage.clickDynamicButton(driver, "button2");

		log.info("TC_03_CreateNewAccount - STEP 03: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account Generated Successfully!!!"));

		log.info("TC_03_CreateNewAccount - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer ID"), newCustomerID);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer Name"), newCustomerName);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Email"), editedCustomerEmail);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Account Type"), newAccountAccType);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Date of Opening"), formatDate(getCurrentDate(), "yyyy-MM-dd"));
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(newAccountInitialDeposit));
		newAccountID = newAccountPage.getDanymicDataInTable(driver, "Account ID");
		newAccountOpeningDate = newAccountPage.getDanymicDataInTable(driver, "Date of Opening");
		
		log.info("--------------------- Create a new account for second customer ---------------------");
		
		log.info("TC_03_CreateNewAccount - STEP 05: Open Add new account form");
		newAccountPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Add new account form"));
		
		log.info("TC_03_CreateNewAccount - STEP 06: Input valid data to Add New Account form and click Submit");
		newAccountPage.inputToDynamicTextbox(driver, "cusid", anotherCustomerID);
		newAccountPage.selectItemInDynamicDropdown(driver, anotherAccountAccType, "selaccount");
		newAccountPage.inputToDynamicTextbox(driver, "inideposit", String.valueOf(anotherAccountInitialDeposit));
		newAccountPage.clickDynamicButton(driver, "button2");
		
		log.info("TC_03_CreateNewAccount - STEP 07: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account Generated Successfully!!!"));

		log.info("TC_03_CreateNewAccount - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer ID"), anotherCustomerID);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Customer Name"), anotherCustomerName);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Email"), anotherCustomerEmail);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Account Type"), anotherAccountAccType);
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Date of Opening"), formatDate(getCurrentDate(), "yyyy-MM-dd"));
		verifyEquals(newAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(anotherAccountInitialDeposit));
		anotherAccountID = newAccountPage.getDanymicDataInTable(driver, "Account ID");
	}

	@Test
	public void TC_04_EditAccout() {
		log.info("--------------------- Edit account of first customer ---------------------");
		
		log.info("TC_04_EditAccout - STEP 01: Open Edit Account form");
		newAccountPage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Edit Account Form"));

		log.info("TC_04_EditAccout - STEP 02: Input account ID and click Submi");
		editAccountPage.inputToDynamicTextbox(driver, "accountno", newAccountID);
		editAccountPage.clickDynamicButton(driver, "AccSubmit");
		
		log.info("TC_04_EditAccout - STEP 03: Verify actual data and expected data are matching");
		verifyEquals(editAccountPage.getTextValueInDynamicTextbox(driver, "txtcid"), newCustomerID);
		verifyEquals(editAccountPage.getCurrentSelectedItemInDynamicDropdown(driver, "a_type"), newAccountAccType);
		verifyEquals(editAccountPage.getTextValueInDynamicTextbox(driver, "txtinitdep"), String.valueOf(newAccountInitialDeposit));

		log.info("TC_04_EditAccout - STEP 04: Change Account Type and click Submit");
		editAccountPage.selectItemInDynamicDropdown(driver, editedAccountAccType, "a_type");
		editAccountPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_04_EditAccout - STEP 05: Verify 'Account details updated Successfully!!!' message is displayed");
		verifyTrue(editAccountPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Account details updated Successfully!!!"));

		log.info("TC_04_EditAccout - STEP 06: Verify actual data and expected data are matching");
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Account ID"), newAccountID);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Customer ID"), newCustomerID);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Customer Name"), newCustomerName);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Email"), editedCustomerEmail);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Account Type"), editedAccountAccType);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Date of Opening"), newAccountOpeningDate);
		verifyEquals(editAccountPage.getDanymicDataInTable(driver, "Current Amount"), String.valueOf(newAccountInitialDeposit));

	}

	@Test
	public void TC_05_Deposit() {
		log.info("--------------------- Deposit money to first customer's account ---------------------");
		
		log.info("TC_05_Deposit - STEP 01: Open Amount Deposit Form");
		editAccountPage.openMultiplePage(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);
		verifyTrue(depositPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Amount Deposit Form"));

		log.info("TC_05_Deposit - STEP 02: Input valid data to Amount Deposit form and click Submit");
		depositPage.inputToDynamicTextbox(driver, "accountno", newAccountID);
		depositPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(depositAmount));
		depositPage.inputToDynamicTextbox(driver, "desc", depositDescription);
		depositPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_05_Deposit - STEP 03: Verify 'Transaction details of Deposit for Account account_id' message is displayed");
		verifyTrue(depositPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Transaction details of Deposit for Account " + newAccountID));

		log.info("TC_05_Deposit - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Account No"), newAccountID);
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Amount Credited"), String.valueOf(depositAmount));
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Type of Transaction"), "Deposit");
		verifyEquals(depositPage.getDanymicDataInTable(driver, "Description"), depositDescription);
		expectedBalance = newAccountInitialDeposit + depositAmount;
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
		withdrawalPage.inputToDynamicTextbox(driver, "accountno", newAccountID);
		withdrawalPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(withdrawAmount));
		withdrawalPage.inputToDynamicTextbox(driver, "desc", withdrawDescription);
		withdrawalPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_06_Withdraw - STEP 03: Verify 'Transaction details of Withdrawal for Account account_id' message is displayed");
		verifyTrue(withdrawalPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Transaction details of Withdrawal for Account " + newAccountID));

		log.info("TC_06_Withdraw - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Account No"), newAccountID);
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Amount Debited"), String.valueOf(withdrawAmount));
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Type of Transaction"), "Withdrawal");
		verifyEquals(withdrawalPage.getDanymicDataInTable(driver, "Description"), withdrawDescription);
		expectedBalance = newAccountInitialDeposit + depositAmount - withdrawAmount;
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
		fundTransferPage.inputToDynamicTextbox(driver, "payersaccount", newAccountID);
		fundTransferPage.inputToDynamicTextbox(driver, "payeeaccount", anotherAccountID);
		fundTransferPage.inputToDynamicTextbox(driver, "ammount", String.valueOf(fundTransferAmount));
		fundTransferPage.inputToDynamicTextbox(driver, "desc", fundTransferDescription);
		fundTransferPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_07_FundTransfer - STEP 03: Verify 'Fund Transfer Details' message is displayed");
		verifyTrue(fundTransferPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Fund Transfer Details"));

		log.info("TC_07_FundTransfer - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "From Account Number"), newAccountID);
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "To Account Number"), anotherAccountID);
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "Amount"), String.valueOf(fundTransferAmount));
		verifyEquals(fundTransferPage.getDanymicDataInTable(driver, "Description"), fundTransferDescription);
	}

	@Test
	public void TC_08_BalanceEnquiry() {
		log.info("--------------------- Check balance of first customer's account ---------------------");
		
		log.info("TC_08_BalanceEnquiry - STEP 01: Open Balance Enquiry form");
		fundTransferPage.openMultiplePage(driver, "Balance Enquiry");
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Balance Enquiry Form"));

		log.info("TC_08_BalanceEnquiry - STEP 02: Input to Account No textbox and click Submit");
		balanceEnquiryPage.inputToDynamicTextbox(driver, "accountno", newAccountID);
		balanceEnquiryPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_08_BalanceEnquiry - STEP 03: Verify 'Balance Details for Account account_id' message is displayed");
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Balance Details for Account " + newAccountID));

		log.info("TC_08_BalanceEnquiry - STEP 04: Verify actual data and expected data are matching");
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Account No"), newAccountID);
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Type of Account"), editedAccountAccType);
		expectedBalance = newAccountInitialDeposit + depositAmount - withdrawAmount - fundTransferAmount;
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Balance"), String.valueOf(expectedBalance));
		
		log.info("--------------------- Check balance of second customer's account ---------------------");
		
		log.info("TC_08_BalanceEnquiry - STEP 05: Open Balance Enquiry form");
		fundTransferPage.openMultiplePage(driver, "Balance Enquiry");
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
		verifyTrue(newCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Balance Enquiry Form"));

		log.info("TC_08_BalanceEnquiry - STEP 06: Input to Account No textbox and click Submit");
		balanceEnquiryPage.inputToDynamicTextbox(driver, "accountno", anotherAccountID);
		balanceEnquiryPage.clickDynamicButton(driver, "AccSubmit");

		log.info("TC_08_BalanceEnquiry - STEP 07: Verify 'Balance Details for Account account_id' message is displayed");
		verifyTrue(balanceEnquiryPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Balance Details for Account " + anotherAccountID));

		log.info("TC_08_BalanceEnquiry - STEP 08: Verify actual data and expected data are matching");
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Account No"), anotherAccountID);
		verifyEquals(balanceEnquiryPage.getDanymicDataInTable(driver, "Type of Account"), anotherAccountAccType);
		expectedBalance = anotherAccountInitialDeposit + fundTransferAmount;
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
		deleteAccountPage.inputToDynamicTextbox(driver, "accountno", newAccountID);
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
		editAccountPage.inputToDynamicTextbox(driver, "accountno", newAccountID);
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
		deleteAccountPage.inputToDynamicTextbox(driver, "accountno", anotherAccountID);
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
		editAccountPage.inputToDynamicTextbox(driver, "accountno", anotherAccountID);
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
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
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
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
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
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", anotherCustomerID);
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
		editCustomerPage.inputToDynamicTextbox(driver, "cusid", anotherCustomerID);
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
