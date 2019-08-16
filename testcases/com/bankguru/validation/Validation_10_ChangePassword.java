package com.bankguru.validation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.bankguru.commons.Common_01_RegisterToSystem;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.ChangePasswordPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_10_ChangePassword extends AbstractTest {
	WebDriver driver;
	String newPasswordWithoutNumber, newPasswordWithoutSpecialCharacter, newPasswordContainingPasswordString;
	String validNewPassword, confirmPasswordMismatch;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	ChangePasswordPageObject changePasswordPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultipleBrowser(browserName);

		// Data
		newPasswordWithoutNumber = "Guru!@#$";
		newPasswordWithoutSpecialCharacter = "Guru99";
		newPasswordContainingPasswordString = "Guru99!password";
		validNewPassword = "Guru99!@";
		confirmPasswordMismatch = "ConfirmPassword!=NewPassword";
		
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToUserIDTextbox(Common_01_RegisterToSystem.username);
		loginPage.inputToPasswordTextbox(Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();

		homePage.openMultiplePage(driver, "Change Password");
		changePasswordPage = PageGeneratorManager.getChangePasswordPage(driver);
	}

	@Test
	public void TC_01_OldPasswordMustNotBeBlank() {
		log.info("Validate Old Password field with blank value - STEP 01: Do not input a value in Old Password field and press Tab key");
		changePasswordPage.inputNothingToOldPasswordTextboxAndPressTabKey();
		
		log.info("Validate Old Password field with blank value - STEP 02: Verify 'Old Password must not be blank' message is displayed");
		verifyTrue(changePasswordPage.isOldPasswordMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_02_NewPasswordMustNotBeBlank() {
		log.info("Validate New Password field with blank value - STEP 01: Do not input a value in New Password field and press Tab key");
		changePasswordPage.inputNothingToNewPasswordTextboxAndPressTabKey();
		
		log.info("Validate New Password field with blank value - STEP 02: Verify 'New Password must not be blank' message is displayed");
		verifyTrue(changePasswordPage.isNewPasswordMustNotBlankMessageDisplayed());
	}
	
	@Test
	public void TC_03_NewPasswordMustHaveAtLeastOneNumericValue() {
		log.info("Validate New Password field without number - STEP 01: Input a password not containing any number into New Password field");
		changePasswordPage.inputToNewPasswordTextbox(newPasswordWithoutNumber);
		
		log.info("Validate New Password field without number - STEP 02: Verify 'Enter at-least one numeric value' message is displayed");
		verifyTrue(changePasswordPage.isNewPasswordMustHaveAtLeastOneNumericValueMessageDisplayed());
	}
	
	@Test
	public void TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter() {
		log.info("Validate New Password field without special character - STEP 01: Input a password not containing any special character into New Password field");
		changePasswordPage.inputToNewPasswordTextbox(newPasswordWithoutSpecialCharacter);
		
		log.info("Validate New Password field without special character - STEP 02: Verify 'Enter at-least one special character' message is displayed");
		verifyTrue(changePasswordPage.isNewPasswordMustHaveAtLeastOneSpecialCharacterMessageDisplayed());
	}
	
	@Test
	public void TC_05_NewPasswordMustNotContainPasswordString() {
		log.info("Validate New Password field with password string - STEP 01: Input a password containing password string into New Password field");
		changePasswordPage.inputToNewPasswordTextbox(newPasswordContainingPasswordString);
		
		log.info("Validate New Password field without number - STEP 02: Verify 'Choose a difficult Password' message is displayed");
		verifyTrue(changePasswordPage.isNewPasswordMustNotContainPasswordStringMessageDisplayed());
	}
	
	@Test
	public void TC_06_NewPasswordAndConfirmPasswordMustMatch() {
		log.info("Password do not match - STEP 01: Input valid password into New Password field");
		changePasswordPage.inputToNewPasswordTextbox(validNewPassword);
		
		log.info("Password do not match - STEP 02: Input a different password into Confirm Password field");
		changePasswordPage.inputToConfirmPasswordTextbox(confirmPasswordMismatch);
		
		log.info("Password do not match - STEP 03: Verify 'Passwords do not Match' message is displayed");
		verifyTrue(changePasswordPage.isPasswordNotMatchMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
