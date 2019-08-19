package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.bankguru.commons.Common_01_RegisterToSystem;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_03_DeleteCustomer extends AbstractTest {
	WebDriver driver;
	String customerIDContainingCharacter, customerIDContainingSpecialCharacter, customerIDContainingSpace,
			customerIDBeginWithSpace;

	LoginPageObject loginPage;
	HomePageObject homePage;
	DeleteCustomerPageObject deleteCustomerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Data
		customerIDContainingCharacter = "123abc";
		customerIDContainingSpecialCharacter = "123$%^";
		customerIDContainingSpace = "123 45";
		customerIDBeginWithSpace = " 123";

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToDynamicTextbox(driver, "uid", Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, "password", Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
	}

	@Test
	public void TC_01_CustomerIDMustNotBeBlank() {
		log.info(
				"Validate Customer ID field with blank value - STEP 01: Do not input a value in Customer ID field and press Tab key");
		deleteCustomerPage.pressTabToDynamicTextbox(driver, "cusid");

		log.info(
				"Validate Customer ID field with blank value - STEP 02: Verify 'Customer ID is required' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Customer ID is required");
	}

	@Test
	public void TC_02_CustomerIDMustNotContainCharacter() {
		log.info("Validate Customer ID field with character - STEP 01: Input character into Customer ID field");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", customerIDContainingCharacter);

		log.info(
				"Validate Customer ID field with character - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Characters are not allowed");
	}

	@Test
	public void TC_03_CustomerIDMustNotContainSpecialCharacter() {
		log.info(
				"Validate Customer ID field with special character - STEP 01: Input special character into Customer ID field");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", customerIDContainingSpecialCharacter);

		log.info(
				"Validate Customer ID field with special character - STEP 02: Verify 'Special characters are not allowed' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_CustomerIDMustNotContainSpace() {
		log.info(
				"Validate Customer ID field with space - STEP 01: Input customer ID which contains a space into Customer ID field");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", customerIDContainingSpace);

		log.info(
				"Validate Customer ID field with space - STEP 02: Verify 'Characters are not allowed' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "Characters are not allowed");
	}

	@Test
	public void TC_05_CustomerIDMustNotBeginWithSpace() {
		log.info("Validate Customer ID field with space at the beginning - STEP 01: Input first character as a space");
		deleteCustomerPage.inputToDynamicTextbox(driver, "cusid", customerIDBeginWithSpace);

		log.info(
				"Validate Customer ID field with space at the beginning - STEP 02: Verify 'First character can not have space' message is displayed");
		verifyEquals(deleteCustomerPage.getErrorMessageOfDynamicField(driver, "Customer ID"), "First character can not have space");
	}

	@AfterClass (alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
