package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.ChangePasswordPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_10_ChangePassword extends AbstractTest {
	WebDriver driver;
	String newPasswordWithoutNumber, newPasswordWithoutSpecialCharacter, newPasswordContainingPasswordString;
	String validNewPassword, confirmPasswordMismatch;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	ChangePasswordPageObject changePasswordPage;

	String userName, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Login credentials
		userName = "mngr213678";
		password = "Anajabu";
		
		// Data
		newPasswordWithoutNumber = "Guru!@#$";
		newPasswordWithoutSpecialCharacter = "Guru99";
		newPasswordContainingPasswordString = "Guru99!password";
		validNewPassword = "Guru99!@";
		confirmPasswordMismatch = "ConfirmPassword!=NewPassword";
		
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(userName);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Change Password");
		changePasswordPage = PageGeneratorManager.getChangePasswordPage(driver);
	}

	@Test
	public void TC_01_OldPasswordMustNotBeBlank() {
		changePasswordPage.inputNothingToOldPasswordTextboxAndPressTabKey();
		Assert.assertTrue(changePasswordPage.isOldPasswordMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_02_NewPasswordMustNotBeBlank() {
		changePasswordPage.inputNothingToNewPasswordTextboxAndPressTabKey();
		Assert.assertTrue(changePasswordPage.isNewPasswordMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_03_NewPasswordMustHaveAtLeastOneNumericValue() {
		changePasswordPage.inputToNewPasswordTextbox(newPasswordWithoutNumber);
		Assert.assertTrue(changePasswordPage.isNewPasswordMustHaveAtLeastOneNumericValueMessageDisplayed());
	}
	
	@Test
	public void TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter() {
		changePasswordPage.inputToNewPasswordTextbox(newPasswordWithoutSpecialCharacter);
		Assert.assertTrue(changePasswordPage.isNewPasswordMustHaveAtLeastOneSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_05_NewPasswordMustNotContainPasswordString() {
		changePasswordPage.inputToNewPasswordTextbox(newPasswordContainingPasswordString);
		Assert.assertTrue(changePasswordPage.isNewPasswordMustNotContainPasswordStringMessageDisplayed());
	}
	
	@Test
	public void TC_06_NewPasswordAndConfirmPasswordMustMatch() {
		changePasswordPage.inputToNewPasswordTextbox(validNewPassword);
		changePasswordPage.inputToConfirmPasswordTextbox(confirmPasswordMismatch);
		Assert.assertTrue(changePasswordPage.isPasswordNotMatchMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
