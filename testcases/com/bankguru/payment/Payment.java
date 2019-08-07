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
		homePage.openMultiplePage(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
		
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
		newCustomerPage.clickSubmitButton();
		
		Assert.assertTrue(newCustomerPage.isCustomerRegisteredSuccessfulMessageDisplayed());
		
		newCustomerID = newCustomerPage.getCustomerID();
		System.out.println("newCustomerID: " + newCustomerID);
	}
	
	@Test 
	public void TC_02_EditCustomer() {
		newCustomerPage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		
		editCustomerPage.inputToCustomerIDTextbox(newCustomerID);
		editCustomerPage.clickAccSubmitButton();
		
		editCustomerPage.inputToAddressTextarea(editedCustomerAddress);
		editCustomerPage.inputToCityTextbox(editedCustomerCity);
		editCustomerPage.inputToStateTextbox(editedCustomerState);
		editCustomerPage.inputToPinTextbox(editedCustomerPIN);
		editCustomerPage.inputToTelephoneTextbox(editedCustomerTelephone);
		editCustomerPage.inputToEmailTextbox(editedCustomerEmail);
		editCustomerPage.clickSubmitButton();
		
		Assert.assertTrue(editCustomerPage.isCustomerUpdatedSuccessfulMessageDisplayed());
	}
	
	@Test 
	public void TC_03_CreateNewAccount() {
		editCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		newAccountPage.inputToCustomerIDTextbox(newCustomerID);
		newAccountPage.selectAccountType(newAccountAccType);
		newAccountPage.inputToInitialDepositTextbox(String.valueOf(newAccountInitialDeposit));
		newAccountPage.clickSubmitButton();
		
		Assert.assertTrue(newAccountPage.isAccountGeneratedSuccessfulMessageDisplayed());
		
		Assert.assertEquals(newAccountPage.getCurrentAmount(), String.valueOf(newAccountInitialDeposit));
		
		newAccountID = newAccountPage.getAccountID();
		System.out.println("newAccountID: " + newAccountID);
	}
	
	@Test  
	public void TC_04_EditAccoutType() {
		newAccountPage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
		
		editAccountPage.inputToAccountNoTextbox(newAccountID);
		editAccountPage.clickSubmitButton();
		
		editAccountPage.selectAccountType(editedAccountAccType);
		editAccountPage.clickSubmitButton();
		
		Assert.assertTrue(editAccountPage.isAccountDetailsUpdatedSuccessfulMessageDisplayed());
		Assert.assertEquals(editAccountPage.getAccountType(), editedAccountAccType);
	}
	
	@Test  
	public void TC_05_Deposit() {
		editAccountPage.openMultiplePage(driver, "Deposit");
		depositPage = PageGeneratorManager.getDepositPage(driver);
		
		depositPage.inputToAccountNoTextbox(newAccountID);
		depositPage.inputToAmountTextbox(String.valueOf(depositAmount));
		depositPage.inputToDescriptionTextbox(depositDescription);
		depositPage.clickSubmitButton();
		
		Assert.assertEquals(depositPage.getResultMessage(), "Transaction details of Deposit for Account " + newAccountID);
		
		expectedBalance = newAccountInitialDeposit + depositAmount;
		Assert.assertEquals(depositPage.getCurrentBalance(), String.valueOf(expectedBalance));
	}
	 
	@Test  
	public void TC_06_Withdraw() {
		depositPage.openMultiplePage(driver, "Withdrawal");
		withdrawalPage = PageGeneratorManager.getWithdrawlPage(driver);
		
		withdrawalPage.inputToAccountNoTextbox(newAccountID);
		withdrawalPage.inputToAmountTextbox(String.valueOf(withdrawAmount));
		withdrawalPage.inputToDescriptionTextbox(withdrawDescription);
		withdrawalPage.clickSubmitButton();
		
		Assert.assertEquals(withdrawalPage.getResultMessage(), "Transaction details of Withdrawal for Account " + newAccountID);
		
		expectedBalance = newAccountInitialDeposit + depositAmount - withdrawAmount;
		Assert.assertEquals(withdrawalPage.getCurrentBalance(), String.valueOf(expectedBalance));
	}
	
	@Test  
	public void TC_07_FundTransfer() {
		// Create another customer and its account 
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
		System.out.println("anotherCustomerID: " + anotherCustomerID);
		
		newCustomerPage.openMultiplePage(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		newAccountPage.inputToCustomerIDTextbox(anotherCustomerID);
		newAccountPage.selectAccountType(anotherAccountAccType);
		newAccountPage.inputToInitialDepositTextbox(String.valueOf(anotherAccountInitialDeposit));
		newAccountPage.clickSubmitButton();
		
		anotherAccountID = newAccountPage.getAccountID();
		System.out.println("anotherAccountID: " + anotherAccountID);
		
		// Transfer money from the current account to the newly created account
		newAccountPage.openMultiplePage(driver, "Fund Transfer");
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
		
		fundTransferPage.inputToPayerAccountNoTextbox(newAccountID);
		fundTransferPage.inputToPayeeAccountNoTextbox(anotherAccountID);
		fundTransferPage.inputToAmountTextbox(String.valueOf(fundTransferAmount));
		fundTransferPage.inputToDescriptionTextbox(fundTransferDescription);
		fundTransferPage.clickSubmitButton();
		
		Assert.assertTrue(fundTransferPage.isFundTransferSuccessfulMessageDisplayed());
		Assert.assertEquals(fundTransferPage.getTransferAmount(), String.valueOf(fundTransferAmount));
	}
	
	@Test  
	public void TC_08_BalanceEnquiry() {
		fundTransferPage.openMultiplePage(driver, "Balance Enquiry");
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
		
		balanceEnquiryPage.inputToAccountNoTextbox(newAccountID);
		balanceEnquiryPage.clickSubmitButton();
		
		Assert.assertEquals(balanceEnquiryPage.getResultMessage(), "Balance Details for Account " + newAccountID);
		
		expectedBalance = newAccountInitialDeposit + depositAmount - withdrawAmount - fundTransferAmount;
		Assert.assertEquals(balanceEnquiryPage.getBalance(), String.valueOf(expectedBalance));
	}
	
	@Test  
	public void TC_09_DeleteAccount() {
		balanceEnquiryPage.openMultiplePage(driver, "Delete Account");
		deleteAccountPage = PageGeneratorManager.getDeleteAccountpage(driver);
		
		deleteAccountPage.inputToAccountNoTextbox(newAccountID);
		deleteAccountPage.clickSubmitButton();
		deleteAccountPage.acceptDeleteAccountConfirmationAlert();
				
		Assert.assertEquals(deleteAccountPage.getAlertMessage(), "Account Deleted Sucessfully");
		homePage =  deleteAccountPage.acceptAccountDeletedInformationAlert();
		
		homePage.openMultiplePage(driver, "Edit Account");
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
	
		editAccountPage.inputToAccountNoTextbox(newAccountID);
		editAccountPage.clickSubmitButton();
		
		Assert.assertEquals(editAccountPage.getAlertMessage(), "Account does not exist");
		editAccountPage.acceptAccountNotExistInformationAlert();
	}
	
	@Test  
	public void TC_10_DeleteCustomer() {
		editAccountPage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		
		deleteCustomerPage.inputToCustomerIDTextbox(newCustomerID);
		deleteCustomerPage.clickSubmitButton();
		
		deleteCustomerPage.acceptDeleteCustomerConfirmationAlert();
		
		Assert.assertEquals(deleteCustomerPage.getAlertMessage(), "Customer deleted Successfully");
		
		homePage = deleteCustomerPage.acceptCustomerDeletedInformationAlert();
		homePage.openMultiplePage(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
		
		editCustomerPage.inputToCustomerIDTextbox(newCustomerID);
		editCustomerPage.clickAccSubmitButton();
		
		Assert.assertEquals(editCustomerPage.getAlertMessage(), "Customer does not exist!!");
		
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
