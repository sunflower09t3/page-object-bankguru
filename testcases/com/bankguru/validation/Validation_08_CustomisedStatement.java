package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.CustomisedStatementPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MiniStatementPageObject;

public class Validation_08_CustomisedStatement extends AbstractTest {
	WebDriver driver;
	String userName, password;
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

		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";
		
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

		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Customised Statement");
		customisedStatementPage = PageGeneratorManager.getCustomisedStatementPage(driver);

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		customisedStatementPage.inputNothingToAccountNoTextboxAndPressTabKey();
		Assert.assertTrue(customisedStatementPage.isAccountNoMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		customisedStatementPage.inputToAccountNoTextbox(accountNoContainingCharacter);
		Assert.assertTrue(customisedStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		customisedStatementPage.inputToAccountNoTextbox(accountNoContainingSpecialCharacter);
		Assert.assertTrue(customisedStatementPage.isAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		customisedStatementPage.inputToAccountNoTextbox(accountNoContainingSpace);
		Assert.assertTrue(customisedStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		customisedStatementPage.inputToAccountNoTextbox(accountNoBeginWithSpace);
		Assert.assertTrue(customisedStatementPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_07_MinimumTransactionValueMustNotContainCharacter() {
		customisedStatementPage.inputToMinimumTransactionValueTextbox(minimumTransactionValueContainingCharacter);
		Assert.assertTrue(customisedStatementPage.isMinimumTransactionValueMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_08_MinimumTransactionValueNoMustNotContainSpecialCharacter() {
		customisedStatementPage.inputToMinimumTransactionValueTextbox(minimumTransactionValueContainingSpecialCharacter);
		Assert.assertTrue(customisedStatementPage.isMinimumTransactionValueMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_09_MinimumTransactionValueMustNotContainSpace() {
		customisedStatementPage.inputToMinimumTransactionValueTextbox(minimumTransactionValueContainingSpace);
		Assert.assertTrue(customisedStatementPage.isMinimumTransactionValueMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_10_MinimumTransactionValueoMustNotBeginWithSpace() {
		customisedStatementPage.inputToMinimumTransactionValueTextbox(minimumTransactionValueBeginWithSpace);
		Assert.assertTrue(customisedStatementPage.isMinimumTransactionValueMustNotContainCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_12_NumberOfTransactionMustNotContainCharacter() {
		customisedStatementPage.inputToNumberOfTransactionTextbox(numOfTransactionContainingCharacter);
		Assert.assertTrue(customisedStatementPage.isNumberOfTransactionMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_13_MinimumTransactionValueNoMustNotContainSpecialCharacter() {
		customisedStatementPage.inputToNumberOfTransactionTextbox(numOfTransactionContainingSpecialCharacter);
		Assert.assertTrue(customisedStatementPage.isNumberOfTransactionMustNotContainSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_14_MinimumTransactionValueMustNotContainSpace() {
		customisedStatementPage.inputToNumberOfTransactionTextbox(numOfTransactionContainingSpace);
		Assert.assertTrue(customisedStatementPage.isNumberOfTransactionMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_15_MinimumTransactionValueoMustNotBeginWithSpace() {
		customisedStatementPage.inputToNumberOfTransactionTextbox(numOfTransactionBeginWithSpace);
		Assert.assertTrue(customisedStatementPage.isNumberOfTransactionMustNotContainCharacterMessageDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
