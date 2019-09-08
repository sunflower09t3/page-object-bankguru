package com.bankguru.validation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.commons.Common_01_RegisterToSystem;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import bankguru.ValidationDataJson;
import commons.AbstractTest;
import commons.Constants;
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
	ValidationDataJson validationData;

	@Parameters({"browser", "validationData"})
	@BeforeClass
	public void setup(String browserName, String validationDataFilePath) throws JsonParseException, JsonMappingException, IOException  {
		validationData = ValidationDataJson.get(validationDataFilePath);
		
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_USER_ID_FIELD, Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_AccountNoMustNotBeBlank() {
		log.info("TC_01_AccountNoMustNotBeBlank - STEP 01: OPen Mini Statement form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_MINI_STATEMENT);
		miniStatementPage = PageGeneratorManager.getMiniStatementPage(driver);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_MINI_STATEMENT));
		miniStatementPageURL = miniStatementPage.getMiniStatementPageURL();

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 02: Do not input a value in Account No field and press Tab key");
		miniStatementPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD);

		log.info("TC_01_AccountNoMustNotBeBlank - STEP 03: Verify 'Account Number must not be blank' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoBlankErrorMsg());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 01: OPen Mini Statement form");
		miniStatementPage.openURL(driver, miniStatementPageURL);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_MINI_STATEMENT));

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 02: Input character into Account No field");
		miniStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainCharacter());

		log.info("TC_02_AccountNoMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainCharacterErrorMsg());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 01: OPen Mini Statement form");
		miniStatementPage.openURL(driver, miniStatementPageURL);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_MINI_STATEMENT));

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 02: Input special character into Account No field");
		miniStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainSpecialCharacter());

		log.info("TC_03_AccountNoMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainSpecialCharacterErrorMsg());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		log.info("TC_04_AccountNoMustNotContainSpace - STEP 01: OPen Mini Statement form");
		miniStatementPage.openURL(driver, miniStatementPageURL);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_MINI_STATEMENT));

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 02: Input a number which contains a space into Account No field");
		miniStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoContainSpace());

		log.info("TC_04_AccountNoMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoContainSpaceErrorMsg());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 01: OPen Mini Statement form");
		miniStatementPage.openURL(driver, miniStatementPageURL);
		verifyTrue(miniStatementPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_MINI_STATEMENT));

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 02: Input first character as a space");
		miniStatementPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_ACC_NO_FIELD, validationData.getAccountNoBeginWithSpace());

		log.info("TC_05_AccountNoMustNotBeginWithSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(miniStatementPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_ACC_NO_FIELD), validationData.getAccountNoBeginWithSpaceErrorMsg());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
