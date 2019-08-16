package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MiniStatementPageObject;

public class Validation_07_MiniStatement extends AbstractTest {
	WebDriver driver;
	String accountNoContainingCharacter, accountNoContainingSpecialCharacter;
	String accountNoContainingSpace, accountNoBeginWithSpace;

	LoginPageObject loginPage;
	HomePageObject homePage;
	MiniStatementPageObject miniStatementPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Data
		accountNoContainingCharacter = "123Acc";
		accountNoContainingSpecialCharacter = "123$%^";
		accountNoContainingSpace = "123 456";
		accountNoBeginWithSpace = " ";

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(Common_01_RegisterToSystem.username);
		loginPage.inputToPasswordTextbox(Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Mini Statement");
		miniStatementPage = PageGeneratorManager.getMiniStatementPage(driver);

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("Validate Account No field with blank value - STEP 01: Do not input a value in Account No field and press Tab key");
		miniStatementPage.inputNothingToAccountNoTextboxAndPressTabKey();
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Account Number must not be blank' message is displayed");
		verifyTrue(miniStatementPage.isAccountNoMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("Validate Account No field with character - STEP 01: Input character into Account No field");
		miniStatementPage.inputToAccountNoTextbox(accountNoContainingCharacter);
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(miniStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("Validate Account No field with special character - STEP 01: Input special character into Account No field");
		miniStatementPage.inputToAccountNoTextbox(accountNoContainingSpecialCharacter);
		
		log.info("Validate Account No field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(miniStatementPage.isAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("Validate Account No field with space - STEP 01: Input a number which contains a space into Account No field");
		miniStatementPage.inputToAccountNoTextbox(accountNoContainingSpace);
		
		log.info("Validate Account No field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(miniStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("Validate Account No field with space at the beginning - STEP 01: Input first character as a space");
		miniStatementPage.inputToAccountNoTextbox(accountNoBeginWithSpace);
		
		log.info("Validate Account No field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(miniStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
