package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;

import bankguru.ValidationData;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MiniStatementPageObject;

public class Validation_07_MiniStatement extends AbstractTest {
	WebDriver driver;

	String miniStatementPageURL;

	LoginPageObject loginPage;
	HomePageObject homePage;
	MiniStatementPageObject miniStatementPage;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browserName) {
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("TC_01_AccountNoMustNotBeBlank - STEP 01: OPen Mini Statement form");
		homePage.openMultiplePage(driver, "Mini Statement");
		miniStatementPage = PageGeneratorManager.getMiniStatementPage(driver);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Mini Statement Form"));
		miniStatementPageURL = miniStatementPage.getMiniStatementPageURL();

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 02: Do not input a value in Account No field and press Tab key");
		miniStatementPage.pressTabToDynamicTextbox(driver, "accountno");

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 03: Verify 'Account Number must not be blank' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Account Number must not be blank");
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 01: OPen Mini Statement form");
		miniStatementPage.openURL(driver, miniStatementPageURL);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Mini Statement Form"));

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 02: Input character into Account No field");
		miniStatementPage.inputToDynamicTextbox(driver, "accountno", ValidationData.MiniStatement.ACCOUNT_NO_CONTAIN_CHARACTER);

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 01: OPen Mini Statement form");
		miniStatementPage.openURL(driver, miniStatementPageURL);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Mini Statement Form"));

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Account No field");
		miniStatementPage.inputToDynamicTextbox(driver, "accountno", ValidationData.MiniStatement.ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER);

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("TC_04_AccountNoMustNotContainSpace - STEP 01: OPen Mini Statement form");
		miniStatementPage.openURL(driver, miniStatementPageURL);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Mini Statement Form"));

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 02: Input a number which contains a space into Account No field");
		miniStatementPage.inputToDynamicTextbox(driver, "accountno", ValidationData.MiniStatement.ACCOUNT_NO_CONTAIN_SPACE);

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 01: OPen Mini Statement form");
		miniStatementPage.openURL(driver, miniStatementPageURL);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Mini Statement Form"));

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 02: Input first character as a space");
		miniStatementPage.inputToDynamicTextbox(driver, "accountno", ValidationData.MiniStatement.ACCOUNT_NO_BEGIN_WITH_SPACE);

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, "Account No"), "Characters are not allowed");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
