package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.CustomisedStatementPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_08_CustomisedStatement extends AbstractTest {
	WebDriver driver;
	String accountNoContainingCharacter, accountNoContainingSpecialCharacter;
	String accountNoContainingSpace, accountNoBeginWithSpace;
	String minimumTransactionValueContainingCharacter, minimumTransactionValueContainingSpecialCharacter;
	String minimumTransactionValueContainingSpace, minimumTransactionValueBeginWithSpace;
	String numOfTransactionContainingCharacter, numOfTransactionContainingSpecialCharacter;
	String numOfTransactionContainingSpace, numOfTransactionBeginWithSpace;

	LoginPageObject loginPage;
	HomePageObject homePage;
	CustomisedStatementPageObject customisedStatementPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Data
		accountNoContainingCharacter = "123Acc";
		accountNoContainingSpecialCharacter = "123$%^";
		accountNoContainingSpace = "123 456";
		accountNoBeginWithSpace = " ";
		minimumTransactionValueContainingCharacter = "123Acc";
		minimumTransactionValueContainingSpecialCharacter = "123#$%";
		minimumTransactionValueContainingSpace = "123 456";
		minimumTransactionValueBeginWithSpace = " ";
		numOfTransactionContainingCharacter = "123Acc";
		numOfTransactionContainingSpecialCharacter = "123#$%";
		numOfTransactionContainingSpace = "123 456";
		numOfTransactionBeginWithSpace = " ";

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(Common_01_RegisterToSystem.username);
		loginPage.inputToPasswordTextbox(Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Customised Statement");
		customisedStatementPage = PageGeneratorManager.getCustomisedStatementPage(driver);

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("Validate Account No field with blank value - STEP 01: Do not input a value in Account No field and press Tab key");
		customisedStatementPage.inputNothingToAccountNoTextboxAndPressTabKey();
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Account Number must not be blank' message is displayed");
		verifyTrue(customisedStatementPage.isAccountNoMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("Validate Account No field with character - STEP 01: Input character into Account No field");
		customisedStatementPage.inputToAccountNoTextbox(accountNoContainingCharacter);
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("Validate Account No field with special character - STEP 01: Input special character into Account No field");
		customisedStatementPage.inputToAccountNoTextbox(accountNoContainingSpecialCharacter);
		
		log.info("Validate Account No field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		customisedStatementPage.inputToAccountNoTextbox(accountNoContainingSpace);
		verifyTrue(customisedStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("Validate Account No field with space - STEP 01: Input a number which contains a space into Account No field");
		customisedStatementPage.inputToAccountNoTextbox(accountNoBeginWithSpace);
		
		log.info("Validate Account No field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_07_MinimumTransactionValueMustNotContainCharacter() {
		log.info("Validate Minimum Transaction Value field with character - STEP 01: Input character into Minimum Transaction Value field");
		customisedStatementPage.inputToMinimumTransactionValueTextbox(minimumTransactionValueContainingCharacter);
		
		log.info("Validate Minimum Transaction Value field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isMinimumTransactionValueMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_08_MinimumTransactionValueNoMustNotContainSpecialCharacter() {
		log.info("Validate Minimum Transaction Value field with special character - STEP 01: Input special character into Minimum Transaction Value field");
		customisedStatementPage.inputToMinimumTransactionValueTextbox(minimumTransactionValueContainingSpecialCharacter);
		
		log.info("Validate Minimum Transaction Value field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isMinimumTransactionValueMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_09_MinimumTransactionValueMustNotContainSpace() {
		log.info("Validate Minimum Transaction Value field with space - STEP 01: Input a number which contains a space into Minimum Transaction Value field");
		customisedStatementPage.inputToMinimumTransactionValueTextbox(minimumTransactionValueContainingSpace);
		
		log.info("Validate Minimum Transaction Value field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isMinimumTransactionValueMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_10_MinimumTransactionValueoMustNotBeginWithSpace() {
		log.info("Validate Minimum Transaction Value field with space at the beginning - STEP 01: Input first character as a space");
		customisedStatementPage.inputToMinimumTransactionValueTextbox(minimumTransactionValueBeginWithSpace);
		
		log.info("Validate Minimum Transaction Value field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(customisedStatementPage.isMinimumTransactionValueMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_12_NumberOfTransactionMustNotContainCharacter() {
		log.info("Validate Number of Transaction field with character - STEP 01: Input character into Number of Transaction field");
		customisedStatementPage.inputToNumberOfTransactionTextbox(numOfTransactionContainingCharacter);
		
		log.info("Validate Number of Transaction field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isNumberOfTransactionMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_13_MinimumTransactionValueNoMustNotContainSpecialCharacter() {
		log.info("Validate Number of Transaction field with special character - STEP 01: Input special character into Number of Transaction field");
		customisedStatementPage.inputToNumberOfTransactionTextbox(numOfTransactionContainingSpecialCharacter);
		
		log.info("Validate Number of Transaction field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isNumberOfTransactionMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_14_MinimumTransactionValueMustNotContainSpace() {
		log.info("Validate Number of Transaction field with space - STEP 01: Input a number which contains a space into Number of Transaction field");
		customisedStatementPage.inputToNumberOfTransactionTextbox(numOfTransactionContainingSpace);
		
		log.info("Validate Number of Transaction field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyTrue(customisedStatementPage.isNumberOfTransactionMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_15_MinimumTransactionValueoMustNotBeginWithSpace() {
		log.info("Validate Number of Transaction field with space at the beginning - STEP 01: Input first character as a space");
		customisedStatementPage.inputToNumberOfTransactionTextbox(numOfTransactionBeginWithSpace);
		
		log.info("Validate Number of Transaction field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyTrue(customisedStatementPage.isNumberOfTransactionMustNotContainCharacterMessageDisplayed());
	}
	
	@AfterClass (alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
