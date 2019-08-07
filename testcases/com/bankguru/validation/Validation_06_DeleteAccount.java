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
		deleteAccountPage.inputNothingToAccountNoTextboxAndPressTabKey();
		Assert.assertTrue(deleteAccountPage.isAccountNoMustNotBlankMessageDisplayed());
	}

	@Test
	public void TC_02_AccountNoMustNotContainCharacter() {
		deleteAccountPage.inputToAccountNoTextbox(accountNoContainingCharacter);
		Assert.assertTrue(deleteAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_03_AccountNoMustNotContainSpecialCharacter() {
		deleteAccountPage.inputToAccountNoTextbox(accountNoContainingSpecialCharacter);
		Assert.assertTrue(deleteAccountPage.isAccountNoMustNotContainSpecialCharacterMessageDisplayed());
	}

	@Test
	public void TC_04_AccountNoMustNotContainSpace() {
		deleteAccountPage.inputToAccountNoTextbox(accountNoContainingSpace);
		Assert.assertTrue(deleteAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}

	@Test
	public void TC_05_AccountNoMustNotBeginWithSpace() {
		deleteAccountPage.inputToAccountNoTextbox(accountNoBeginWithSpace);
		Assert.assertTrue(deleteAccountPage.isAccountNoMustNotContainCharacterMessageDisplayed());
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
