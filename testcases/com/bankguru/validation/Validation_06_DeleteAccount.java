package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.DeleteAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_06_DeleteAccount extends AbstractTest {
	WebDriver driver;
	String userName, password;
	String accountNoContainingCharacter, accountNoContainingSpecialCharacter;
	String accountNoContainingSpace, accountNoBeginWithSpace;

	LoginPageObject loginPage;
	HomePageObject homePage;
	DeleteAccountPageObject deleteAccountPage;
	
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

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Delete Account");
		deleteAccountPage = PageGeneratorManager.getDeleteAccountpage(driver);

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("Validate Account No field with blank value - STEP 01: Do not input a value in Account No field and press Tab key");
		deleteAccountPage.inputNothingToAccountNoTextboxAndPressTabKey();
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Account Number must not be blank' message is displayed");
		verifyTrue(deleteAccountPage.isAccountNoMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("Validate Account No field with character - STEP 01: Input character into Account No field");
		deleteAccountPage.inputToAccountNoTextbox(accountNoContainingCharacter);
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(deleteAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("Validate Account No field with special character - STEP 01: Input special character into Account No field");
		deleteAccountPage.inputToAccountNoTextbox(accountNoContainingSpecialCharacter);
		
		log.info("Validate Account No field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(deleteAccountPage.isAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("Validate Account No field with space - STEP 01: Input a number which contains a space into Account No field");
		deleteAccountPage.inputToAccountNoTextbox(accountNoContainingSpace);
		
		log.info("Validate Account No field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(deleteAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("Validate Account No field with space at the beginning - STEP 01: Input first character as a space");
		deleteAccountPage.inputToAccountNoTextbox(accountNoBeginWithSpace);
		
		log.info("Validate Account No field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(deleteAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
