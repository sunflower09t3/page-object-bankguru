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
import pageObjects.ChangePasswordPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Validation_10_ChangePassword extends AbstractTest {
	WebDriver driver;
	
	String changePasswordPageURL;
	
	LoginPageObject loginPage;
	HomePageObject homePage;
	ChangePasswordPageObject changePasswordPage;
	
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
	public void TC_01_OldPasswordMustNotBeBlank() {
		log.info("TC_01_OldPasswordMustNotBeBlank - STEP 01: Open Change Password form");
		homePage.openMultiplePage(driver, "Change Password");
		changePasswordPage = PageGeneratorManager.getChangePasswordPage(driver);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Change Password"));
		changePasswordPageURL = changePasswordPage.getChangePasswordPageURL();
		
		log.info("TC_01_OldPasswordMustNotBeBlank - STEP 02: Do not input a value in Old Password field and press Tab key");
		changePasswordPage.pressTabToDynamicTextbox(driver, "oldpassword");
		
		log.info("TC_01_OldPasswordMustNotBeBlank - STEP 03: Verify 'Old Password must not be blank' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, "Old Password"), "Old Password must not be blank");
	}
	
	@Test
	public void TC_02_NewPasswordMustNotBeBlank() {
		log.info("TC_02_NewPasswordMustNotBeBlank - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Change Password"));
		
		log.info("TC_02_NewPasswordMustNotBeBlank - STEP 02: Do not input a value in New Password field and press Tab key");
		changePasswordPage.pressTabToDynamicTextbox(driver, "newpassword");
		
		log.info("TC_02_NewPasswordMustNotBeBlank - STEP 03: Verify 'New Password must not be blank' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, "New Password"), "New Password must not be blank");
	}
	
	@Test
	public void TC_03_NewPasswordMustHaveAtLeastOneNumericValue() {
		log.info("TC_03_NewPasswordMustHaveAtLeastOneNumericValue - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Change Password"));
		
		log.info("TC_03_NewPasswordMustHaveAtLeastOneNumericValue - STEP 02: Input a password not containing any number into New Password field");
		changePasswordPage.inputToDynamicTextbox(driver, "newpassword", validationData.getNewPasswordWithoutNumber());
		
		log.info("TC_03_NewPasswordMustHaveAtLeastOneNumericValue - STEP 03: Verify 'Enter at-least one numeric value' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, "New Password"), "Enter at-least one numeric value");
	}
	
	@Test
	public void TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter() {
		log.info("TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Change Password"));
		
		log.info("TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter - STEP 02: Input a password not containing any special character into New Password field");
		changePasswordPage.inputToDynamicTextbox(driver, "newpassword", validationData.getNewPasswordWithoutSpecialCharacter());
		
		log.info("TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter - STEP 03: Verify 'Enter at-least one special character' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, "New Password"), "Enter at-least one special character");
	}
	
	@Test
	public void TC_05_NewPasswordMustNotContainPasswordString() {
		log.info("TC_05_NewPasswordMustNotContainPasswordString - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Change Password"));
		
		log.info("TC_05_NewPasswordMustNotContainPasswordString - STEP 02: Input a password containing password string into New Password field");
		changePasswordPage.inputToDynamicTextbox(driver, "newpassword", validationData.getNewPasswordContainPasswordString());
		
		log.info("TC_05_NewPasswordMustNotContainPasswordString - STEP 03: Verify 'Choose a difficult Password' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, "New Password"), "Choose a difficult Password");
	}
	
	@Test
	public void TC_06_NewPasswordAndConfirmPasswordMustMatch() {
		log.info("TC_06_NewPasswordAndConfirmPasswordMustMatch - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, "Change Password"));
		
		log.info("TC_06_NewPasswordAndConfirmPasswordMustMatch - STEP 02: Input valid password into New Password field");
		changePasswordPage.inputToDynamicTextbox(driver, "newpassword", validationData.getValidNewPassword());
		
		log.info("TC_06_NewPasswordAndConfirmPasswordMustMatch - STEP 03: Input a different password into Confirm Password field");
		changePasswordPage.inputToDynamicTextbox(driver, "confirmpassword", validationData.getConfirmPasswordNotmatch());
		
		log.info("TC_06_NewPasswordAndConfirmPasswordMustMatch - STEP 04: Verify 'Passwords do not Match' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, "Confirm Password"), "Passwords do not Match");
	}

	@AfterClass (alwaysRun=true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
