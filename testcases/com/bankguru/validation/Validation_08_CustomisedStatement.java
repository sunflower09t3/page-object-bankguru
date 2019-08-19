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

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Customised Statement");
		customisedStatementPage = PageGeneratorManager.getCustomisedStatementPage(driver);

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("Validate Account No field with blank value - STEP 01: Do not input a value in Account No field and press Tab key");
		customisedStatementPage.pressTabToDynamicTextbox(driver, "accountno");
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Account Number must not be blank' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Account Number must not be blank");
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("Validate Account No field with character - STEP 01: Input character into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, "accountno", accountNoContainingCharacter);
		
		log.info("Validate Account No field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("Validate Account No field with special character - STEP 01: Input special character into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, "accountno", accountNoContainingSpecialCharacter);
		
		log.info("Validate Account No field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("Validate Account No field with space - STEP 01: Input a space into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, "accountno", accountNoContainingSpace);
		
		log.info("Validate Account No field with space at the beginning - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("Validate Account No field with space at the beginning - STEP 01: Input a space into Account No field");
		customisedStatementPage.inputToDynamicTextbox(driver, "accountno", accountNoBeginWithSpace);
		
		log.info("Validate Account No field with space at the beginning - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}
	
	@Test
	public void TC_06_MinimumTransactionValueMustNotContainCharacter() {
		log.info("Validate Minimum Transaction Value field with character - STEP 01: Input character into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, "amountlowerlimit", minimumTransactionValueContainingCharacter);
		
		log.info("Validate Minimum Transaction Value field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Minimum Transaction Value"), "Characters are not allowed");
	}

	@Test
	public void TC_07_MinimumTransactionValueNoMustNotContainSpecialCharacter() {
		log.info("Validate Minimum Transaction Value field with special character - STEP 01: Input special character into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, "amountlowerlimit", minimumTransactionValueContainingSpecialCharacter);
		
		log.info("Validate Minimum Transaction Value field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Minimum Transaction Value"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_08_MinimumTransactionValueMustNotContainSpace() {
		log.info("Validate Minimum Transaction Value field with space - STEP 01: Input a number which contains a space into Minimum Transaction Value field");
		customisedStatementPage.inputToDynamicTextbox(driver, "amountlowerlimit", minimumTransactionValueContainingSpace);
		
		log.info("Validate Minimum Transaction Value field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Minimum Transaction Value"), "Characters are not allowed");
	}

	@Test
	public void TC_09_MinimumTransactionValueoMustNotBeginWithSpace() {
		log.info("Validate Minimum Transaction Value field with space at the beginning - STEP 01: Input first character as a space");
		customisedStatementPage.inputToDynamicTextbox(driver, "amountlowerlimit", minimumTransactionValueBeginWithSpace);
		
		log.info("Validate Minimum Transaction Value field with space at the beginning - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Minimum Transaction Value"), "Characters are not allowed");
	}
	
	@Test
	public void TC_10_NumberOfTransactionMustNotContainCharacter() {
		log.info("Validate Number of Transaction field with character - STEP 01: Input character into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, "numtransaction", numOfTransactionContainingCharacter);
		
		log.info("Validate Number of Transaction field with blank value - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Number of Transaction"), "Characters are not allowed");
	}

	@Test
	public void TC_11_NumberOfTransactionValueNoMustNotContainSpecialCharacter() {
		log.info("Validate Number of Transaction field with special character - STEP 01: Input special character into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, "numtransaction", numOfTransactionContainingSpecialCharacter);
		
		log.info("Validate Number of Transaction field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Number of Transaction"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_12_NumberOfTransactionValueMustNotContainSpace() {
		log.info("Validate Number of Transaction field with space - STEP 01: Input a number which contains a space into Number of Transaction field");
		customisedStatementPage.inputToDynamicTextbox(driver, "numtransaction", numOfTransactionContainingSpace);
		
		log.info("Validate Number of Transaction field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Number of Transaction"), "Characters are not allowed");
	}

	@Test
	public void TC_13_NumberOfTransactionValueoMustNotBeginWithSpace() {
		log.info("Validate Number of Transaction field with space at the beginning - STEP 01: Input first character as a space");
		customisedStatementPage.inputToDynamicTextbox(driver, "numtransaction", numOfTransactionBeginWithSpace);
		
		log.info("Validate Number of Transaction field with space at the beginning - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(customisedStatementPage.getErrorMessageOfDynamicField(driver, "Number of Transaction"), "Characters are not allowed");
	}
	
	@AfterClass (alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
