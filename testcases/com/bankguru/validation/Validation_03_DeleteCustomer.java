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
import commons.PageGeneratorManager;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_03_DeleteCustomer extends AbstractTest {
	WebDriver driver;
	String deleteCustomerPageURL;
	LoginPageObject loginPage;
	HomePageObject homePage;
	DeleteCustomerPageObject deleteCustomerPage;
	ValidationDataJson validationData;

	@Parameters({"browser", "validationData"})
	@BeforeClass
	public void setup(String browserName, String validationDataFilePath) throws JsonParseException, JsonMappingException, IOException {
		validationData = ValidationDataJson.get(validationDataFilePath);
		
		driver = openMultipleBrowser(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

	}

	@Test
	public void TC_01_CustomerIDMustNotBeBlank() {
		log.info("TC_01_CustomerIDMustNotBeBlank - STEP 01: Open Delete Customer page");
		homePage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Customer Form"));
		deleteCustomerPageURL = deleteCustomerPage.getDeleteCustomerPageURL();

		log.info("TC_01_CustomerIDMustNotBeBlank - STEP 02: Do not input a value in Customer ID field and press Tab key");
		deleteCustomerPage.pressTabToDynamicTextbox(driver, "cusid");

		log.info("TC_01_CustomerIDMustNotBeBlank - STEP 03: Verify 'Customer ID is required' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Customer ID is required");
	}

	@Test
	public void TC_02_CustomerIDMustNotContainCharacter() {
		log.info("TC_02_CustomerIDMustNotContainCharacter - STEP 01: Open Delete Customer page");
		deleteCustomerPage.openURL(driver, deleteCustomerPageURL);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Customer Form"));

		log.info("TC_02_CustomerIDMustNotContainCharacter - STEP 02: Input to Customer ID field");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", validationData.getCustomerIdContainCharacter());

		log.info("TC_02_CustomerIDMustNotContainCharacter - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Characters are not allowed");
	}

	@Test
	public void TC_03_CustomerIDMustNotContainSpecialCharacter() {
		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 01: Open Delete Customer page");
		deleteCustomerPage.openURL(driver, deleteCustomerPageURL);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Customer Form"));

		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 02: Input to Customer ID field");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", validationData.getCustomerIdContainSpecialCharacter());

		log.info("TC_03_CustomerIDMustNotContainSpecialCharacter - STEP 03: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_CustomerIDMustNotContainSpace() {
		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 01: Open Delete Customer page");
		deleteCustomerPage.openURL(driver, deleteCustomerPageURL);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Customer Form"));

		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 02: Input to Customer ID field");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", validationData.getCustomerIdContainSpace());

		log.info("TC_04_CustomerIDMustNotContainSpace - STEP 03: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Characters are not allowed");
	}

	@Test
	public void TC_05_CustomerIDMustNotBeginWithSpace() {
		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 01: Open Delete Customer page");
		deleteCustomerPage.openURL(driver, deleteCustomerPageURL);
		verifyTrue(deleteCustomerPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Delete Customer Form"));

		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 01: Input to Customer ID field");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", validationData.getCustomerIdBeginWithSpace());

		log.info("TC_05_CustomerIDMustNotBeginWithSpace - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "First character can not have space");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
