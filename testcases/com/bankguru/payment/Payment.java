package com.bankguru.payment;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
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

public class Payment extends AbstractTest{
	WebDriver driver;
	String userName, password;
	String newCustomerID, newCustomerName, newCustomerDateOfBirth, newCustomerAddress, newCustomerState;
	String newCustomerCity, newCustomerPIN, newCustomerTelephone, newCustomerEmail, newCustomerPassword;
	String anotherCustomerID, anotherCustomerName, anotherCustomerDateOfBirth, anotherCustomerAddress, anotherCustomerState;
	String anotherCustomerCity, anotherCustomerPIN, anotherCustomerTelephone, anotherCustomerEmail, anotherCustomerPassword;
	String editedCustomerAddress, editedCustomerState, editedCustomerCity, editedCustomerPIN, editedCustomerTelephone, editedCustomerEmail;
	String newAccountID, newAccountAccType, anotherAccountID, anotherAccountAccType, editedAccountAccType;
	String depositDescription, withdrawDescription, fundTransferDescription;
	int newAccountInitialDeposit, anotherAccountInitialDeposit, depositAmount, withdrawAmount, fundTransferAmount, expectedBalance;
	
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
	@BeforeClass public void beforeClass(String browserName) {
		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";
		
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
		
		// Another new customer data
		anotherCustomerName = "second customer";
		anotherCustomerDateOfBirth = "02/01/1991";
		anotherCustomerAddress = "Blk 136 Potong Pasir Ave 3";
		anotherCustomerState = "Jurong";
		anotherCustomerCity = "Singapore";
		anotherCustomerPIN = "778855";
		anotherCustomerTelephone = "1478965236";
		anotherCustomerEmail = "second customer" + randomNumber() + "@gmail.com";
		anotherCustomerPassword = "secondcustomerpassword";
		
		// Edit customer data
		editedCustomerAddress = "1883 Cursus Avenue";
		editedCustomerCity = "Houston";
		editedCustomerState = "Texas";
		editedCustomerPIN = "166455";
		editedCustomerTelephone = "1122334455";
		editedCustomerEmail = "automationtesting" + randomNumber() + "@gmail.com";
		
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
		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
	}
	
	@Test
	public void TC_01_CreateNewCustomer() {
		log.info("Create new customer - STEP 01: Open New Customer page");
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		
		log.info("Create new customer - STEP 02: Fill in New Customer form");
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
		
		log.info("Create new customer - STEP 03: Click Submit button");
		newCustomerPage.clickSubmitButton();
		
		log.info("Create new customer - STEP 04: Verify 'Customer Registered Successfully!!!' message is displayed");
		verifyTrue(newCustomerPage.isCustomerRegisteredSuccessfulMessageDisplayed());
		
		newCustomerID = newCustomerPage.getCustomerID();
	}
	
	@Test 
	public void TC_02_EditCustomer() {
		log.info("Edit customer - STEP 01: Open Edit Customer page");
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		
		log.info("Edit customer - STEP 02: Input the custoner ID");
		editCustomerPage.inputToCustomerIDTextbox(newCustomerID);
		
		log.info("Edit customer - STEP 03: Click Submit button");
		editCustomerPage.clickAccSubmitButton();
		
		log.info("Edit customer - STEP 04: Fill in Edit Customer Form");
		editCustomerPage.inputToAddressTextarea(editedCustomerAddress);
		editCustomerPage.inputToCityTextbox(editedCustomerCity);
		editCustomerPage.inputToStateTextbox(editedCustomerState);
		editCustomerPage.inputToPinTextbox(editedCustomerPIN);
		editCustomerPage.inputToTelephoneTextbox(editedCustomerTelephone);
		editCustomerPage.inputToEmailTextbox(editedCustomerEmail);
		
		log.info("Edit customer - STEP 04: Click Submit button");
		editCustomerPage.clickSubmitButton();
		
		log.info("Edit customer - STEP 05: Verify 'Customer details updated Successfully!!!' message is displayed");
		verifyTrue(editCustomerPage.isCustomerUpdatedSuccessfulMessageDisplayed());
	}
	
	@Test 
	public void TC_03_CreateNewAccount() {
		log.info("Create new account - STEP 01: Open New Account page");
		editCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		log.info("Create new account - STEP 02: Fill in New Account Form");
		newAccountPage.inputToCustomerIDTextbox(newCustomerID);
		newAccountPage.selectAccountType(newAccountAccType);
		newAccountPage.inputToInitialDepositTextbox(String.valueOf(newAccountInitialDeposit));
		
		log.info("Create new account - STEP 03: Click Submit button");
		newAccountPage.clickSubmitButton();
		
		log.info("Create new account - STEP 04: Verify 'Account Generated Successfully!!!' message is displayed");
		verifyTrue(newAccountPage.isAccountGeneratedSuccessfulMessageDisplayed());
		
		log.info("Create new account - STEP 05: Verify Current Amount is equal to Initial Deposit");
		verifyEquals(newAccountPage.getCurrentAmount(), String.valueOf(newAccountInitialDeposit));
		
		newAccountID = newAccountPage.getAccountID();
	}
	
	@Test  
	public void TC_04_EditAccoutType() {
		log.info("Edit account type - STEP 01: Open Edit Account page");
		newAccountPage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		
		log.info("Edit account type - STEP 02: Input account ID");
		editAccountPage.inputToAccountNoTextbox(newAccountID);
		
		log.info("Edit account type - STEP 03: Click Submit button");
		editAccountPage.clickSubmitButton();
		
		log.info("Edit account type - STEP 04: Change Account Type");
		editAccountPage.selectAccountType(editedAccountAccType);
		
		log.info("Edit account type - STEP 05: Click Submit button");
		editAccountPage.clickSubmitButton();
		
		log.info("Edit account type - STEP 06: Verify 'Account details updated Successfully!!!' message is displayed");
		verifyTrue(editAccountPage.isAccountDetailsUpdatedSuccessfulMessageDisplayed());
		
		log.info("Edit account type - STEP 07: Verify Account Type is updated successfully");
		verifyEquals(editAccountPage.getAccountType(), editedAccountAccType);
	}
	
	@Test  
	public void TC_05_Deposit() {
		log.info("Deposit - STEP 01: Open Deposit page");
		editAccountPage.openMultiplePage(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);
		
		log.info("Deposit - STEP 02: Fill in Deposit page");
		depositPage.inputToAccountNoTextbox(newAccountID);
		depositPage.inputToAmountTextbox(String.valueOf(depositAmount));
		depositPage.inputToDescriptionTextbox(depositDescription);
		
		log.info("Deposit - STEP 03: click Submit button");
		depositPage.clickSubmitButton();
		
		log.info("Deposit - STEP 04: Verify 'Transaction details of Deposit for Account account_id' message is displayed");
		verifyEquals(depositPage.getResultMessage(), "Transaction details of Deposit for Account " + newAccountID);
		
		log.info("Deposit - STEP 05: Verify Current Balance is correct");
		expectedBalance = newAccountInitialDeposit + depositAmount;
		verifyEquals(depositPage.getCurrentBalance(), String.valueOf(expectedBalance));
	}
	 
	@Test  
	public void TC_06_Withdraw() {
		log.info("Withdraw - STEP 01: Open Withdraw page");
		depositPage.openMultiplePage(driver, "Withdrawal");
		withdrawalPage = PageGeneratorManager.getWithdrawlPage(driver);
		
		log.info("Withdraw - STEP 02: Fill in Withdraw form");
		withdrawalPage.inputToAccountNoTextbox(newAccountID);
		withdrawalPage.inputToAmountTextbox(String.valueOf(withdrawAmount));
		withdrawalPage.inputToDescriptionTextbox(withdrawDescription);
		
		log.info("Withdraw - STEP 03: Click Submit button");
		withdrawalPage.clickSubmitButton();
		
		log.info("Withdraw - STEP 04: Verify 'Transaction details of Withdrawal for Account account_id' message is displayed");
		verifyEquals(withdrawalPage.getResultMessage(), "Transaction details of Withdrawal for Account " + newAccountID);
		
		log.info("Withdraw - STEP 05: Verify Current Balance is correct");
		expectedBalance = newAccountInitialDeposit + depositAmount - withdrawAmount;
		verifyEquals(withdrawalPage.getCurrentBalance(), String.valueOf(expectedBalance));
	}
	
	@Test  
	public void TC_07_FundTransfer() {
		log.info("Fundtransfer - STEP 01: Create a new customer");
		withdrawalPage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		
		newCustomerPage.inputToCustomerNameTextbox(anotherCustomerName);
		newCustomerPage.selectFemaleGenderRadioButton();
		newCustomerPage.inputToDateOfBirthTextbox(anotherCustomerDateOfBirth);
		newCustomerPage.inputToAddressTextarea(anotherCustomerAddress);
		newCustomerPage.inputToCityTextbox(anotherCustomerCity);
		newCustomerPage.inputToStateTextbox(anotherCustomerState);
		newCustomerPage.inputToPinTextbox(anotherCustomerPIN);
		newCustomerPage.inputToTelephoneTextbox(anotherCustomerTelephone);
		newCustomerPage.inputToEmailTextbox(anotherCustomerEmail);
		newCustomerPage.inputToPasswordTextbox(anotherCustomerPassword);
		newCustomerPage.clickSubmitButton();
		
		anotherCustomerID = newCustomerPage.getCustomerID();
		
		log.info("Fundtransfer - STEP 02: Create a new account for the new customer");
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		newAccountPage.inputToCustomerIDTextbox(anotherCustomerID);
		newAccountPage.selectAccountType(anotherAccountAccType);
		newAccountPage.inputToInitialDepositTextbox(String.valueOf(anotherAccountInitialDeposit));
		newAccountPage.clickSubmitButton();
		
		anotherAccountID = newAccountPage.getAccountID();
		
		log.info("Fundtransfer - STEP 03: Open Fund Transfer page");
		newAccountPage.openMultiplePage(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
		
		log.info("Fundtransfer - STEP 04: Fill in Fund Transfer page");
		fundTransferPage.inputToPayerAccountNoTextbox(newAccountID);
		fundTransferPage.inputToPayeeAccountNoTextbox(anotherAccountID);
		fundTransferPage.inputToAmountTextbox(String.valueOf(fundTransferAmount));
		fundTransferPage.inputToDescriptionTextbox(fundTransferDescription);
		
		log.info("Fundtransfer - STEP 05: Click Submit button");
		fundTransferPage.clickSubmitButton();
		
		log.info("Fundtransfer - STEP 06: Verify 'Fund Transfer Details' message is displayed");
		verifyTrue(fundTransferPage.isFundTransferSuccessfulMessageDisplayed());
		
		log.info("Fundtransfer - STEP 07: Verify Transfer Amount is correct");
		verifyEquals(fundTransferPage.getTransferAmount(), String.valueOf(fundTransferAmount));
	}
	
	@Test  
	public void TC_08_BalanceEnquiry() {
		log.info("Balance Enquiry - STEP 01: Open Balance Enquiry page");
		fundTransferPage.openMultiplePage(driver, "Balance Enquiry");
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
		
		log.info("Balance Enquiry - STEP 02: Input account ID");
		balanceEnquiryPage.inputToAccountNoTextbox(newAccountID);
		
		log.info("Balance Enquiry - STEP 03: Click Submit button");
		balanceEnquiryPage.clickSubmitButton();
		
		log.info("Balance Enquiry - STEP 04: Verify 'Balance Details for Account account_id' message is displayed");
		verifyEquals(balanceEnquiryPage.getResultMessage(), "Balance Details for Account " + newAccountID);
		
		log.info("Balance Enquiry - STEP 05: Verify Balance Amount is correct");
		expectedBalance = newAccountInitialDeposit + depositAmount - withdrawAmount - fundTransferAmount;
		verifyEquals(balanceEnquiryPage.getBalance(), String.valueOf(expectedBalance));
	}
	
	@Test  
	public void TC_09_DeleteAccount() {
		log.info("Delete account - STEP 01: Open Delete Account page");
		balanceEnquiryPage.openMultiplePage(driver, "Delete Account");
		deleteAccountPage = PageGeneratorManager.getDeleteAccountpage(driver);
		
		log.info("Delete account - STEP 02: Input account ID");
		deleteAccountPage.inputToAccountNoTextbox(newAccountID);
		
		log.info("Delete account - STEP 03: click Submit button");
		deleteAccountPage.clickSubmitButton();
		
		log.info("Delete account - STEP 04: Accept confirmation alert");
		deleteAccountPage.acceptDeleteAccountConfirmationAlert();
				
		log.info("Delete account - STEP 05: Verify 'Account Deleted Sucessfully' alert message is displayed");
		verifyEquals(deleteAccountPage.getAlertMessage(), "Account Deleted Sucessfully");
		homePage =  deleteAccountPage.acceptAccountDeletedInformationAlert();
		
		log.info("Delete account - STEP 06: Open Edit Account page");
		homePage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
	
		log.info("Delete account - STEP 07: Input account ID");
		editAccountPage.inputToAccountNoTextbox(newAccountID);
		
		log.info("Delete account - STEP 08: Click Submit button");
		editAccountPage.clickSubmitButton();
		
		log.info("Delete account - STEP 09: Verify 'Account does not exist' alert message is displayed");
		verifyEquals(editAccountPage.getAlertMessage(), "Account does not exist");
		
		log.info("Delete account - STEP 10: Accept the information alert");
		editAccountPage.acceptAccountNotExistInformationAlert();
	}
	
	@Test  
	public void TC_10_DeleteCustomer() {
		log.info("Delete customer - STEP 01: Open Delete Customer page");
		editAccountPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		
		log.info("Delete customer - STEP 02: Input customer ID");
		deleteCustomerPage.inputToCustomerIDTextbox(newCustomerID);
		
		log.info("Delete customer - STEP 03: Click Submit button");
		deleteCustomerPage.clickSubmitButton();
		
		log.info("Delete customer - STEP 04: Accept the confirmation alert");
		deleteCustomerPage.acceptDeleteCustomerConfirmationAlert();
		
		log.info("Delete customer - STEP 05: Verify 'Customer deleleted successfully' alert message is displayed");
		verifyEquals(deleteCustomerPage.getAlertMessage(), "Customer deleted Successfully");
		
		log.info("Delete customer - STEP 06: Accept the information alert");
		homePage = deleteCustomerPage.acceptCustomerDeletedInformationAlert();
		
		log.info("Delete customer - STEP 07: Open Edit Customer page");
		homePage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		
		log.info("Delete customer - STEP 08: Input customer ID");
		editCustomerPage.inputToCustomerIDTextbox(newCustomerID);
		
		log.info("Delete customer - STEP 09: Click Submit button");
		editCustomerPage.clickAccSubmitButton();
		
		log.info("Delete customer - STEP 10: Verify 'Customer does not exist!!' alert message is displayed");
		verifyEquals(editCustomerPage.getAlertMessage(), "Customer does not exist!!");
		
		log.info("Delete customer - STEP 11: Accept the information alert");
		editCustomerPage.acceptCustomerNotExistInformationAlert();
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000000);
	}

	@AfterClass
	public void afterClass() {
		
		driver.quit();
	}

}
