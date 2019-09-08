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

		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_USER_ID_FIELD, Common_01_RegisterToSystem.username);
		loginPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_PASSWORD_FIELD, Common_01_RegisterToSystem.password);
		homePage = loginPage.clickLoginButton();
	}

	@Test
	public void TC_01_OldPasswordMustNotBeBlank() {
		log.info("TC_01_OldPasswordMustNotBeBlank - STEP 01: Open Change Password form");
		homePage.openMultiplePage(driver, Constants.PAGE_NAME_OF_CHANGE_PASSWORD);
		changePasswordPage = PageGeneratorManager.getChangePasswordPage(driver);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CHANGE_PASSWORD));
		changePasswordPageURL = changePasswordPage.getChangePasswordPageURL();
		
		log.info("TC_01_OldPasswordMustNotBeBlank - STEP 02: Do not input a value in Old Password field and press Tab key");
		changePasswordPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_OLD_PASSWORD_FIELD);
		
		log.info("TC_01_OldPasswordMustNotBeBlank - STEP 03: Verify 'Old Password must not be blank' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_OLD_PASSWORD_FIELD), validationData.getOldPasswordBlankErrorMsg());
	}
	
	@Test
	public void TC_02_NewPasswordMustNotBeBlank() {
		log.info("TC_02_NewPasswordMustNotBeBlank - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CHANGE_PASSWORD));
		
		log.info("TC_02_NewPasswordMustNotBeBlank - STEP 02: Do not input a value in New Password field and press Tab key");
		changePasswordPage.pressTabToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_NEW_PASSWORD_FIELD);
		
		log.info("TC_02_NewPasswordMustNotBeBlank - STEP 03: Verify 'New Password must not be blank' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_NEW_PASSWORD_FIELD), validationData.getNewPasswordBlankErrorMsg());
	}
	
	@Test
	public void TC_03_NewPasswordMustHaveAtLeastOneNumericValue() {
		log.info("TC_03_NewPasswordMustHaveAtLeastOneNumericValue - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CHANGE_PASSWORD));
		
		log.info("TC_03_NewPasswordMustHaveAtLeastOneNumericValue - STEP 02: Input a password not containing any number into New Password field");
		changePasswordPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_NEW_PASSWORD_FIELD, validationData.getNewPasswordWithoutNumber());
		
		log.info("TC_03_NewPasswordMustHaveAtLeastOneNumericValue - STEP 03: Verify 'Enter at-least one numeric value' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_NEW_PASSWORD_FIELD), validationData.getNewPasswordWithoutNumberErrorMsg());
	}
	
	@Test
	public void TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter() {
		log.info("TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CHANGE_PASSWORD));
		
		log.info("TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter - STEP 02: Input a password not containing any special character into New Password field");
		changePasswordPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_NEW_PASSWORD_FIELD, validationData.getNewPasswordWithoutSpecialCharacter());
		
		log.info("TC_04_NewPasswordMustHaveAtLeastOneSpecialCharacter - STEP 03: Verify 'Enter at-least one special character' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_NEW_PASSWORD_FIELD), validationData.getNewPasswordWithoutSpecialCharacterErrorMsg());
	}
	
	@Test
	public void TC_05_NewPasswordMustNotContainPasswordString() {
		log.info("TC_05_NewPasswordMustNotContainPasswordString - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CHANGE_PASSWORD));
		
		log.info("TC_05_NewPasswordMustNotContainPasswordString - STEP 02: Input a password containing password string into New Password field");
		changePasswordPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_NEW_PASSWORD_FIELD, validationData.getNewPasswordContainPasswordString());
		
		log.info("TC_05_NewPasswordMustNotContainPasswordString - STEP 03: Verify 'Choose a difficult Password' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_NEW_PASSWORD_FIELD), validationData.getNewPasswordContainPasswordStringErrorMsg());
	}
	
	@Test
	public void TC_06_NewPasswordAndConfirmPasswordMustMatch() {
		log.info("TC_06_NewPasswordAndConfirmPasswordMustMatch - STEP 01: Open Change Password form");
		changePasswordPage.openURL(driver, changePasswordPageURL);
		verifyTrue(changePasswordPage.isPageTitleOrTableHeaderMessageDisplayed(driver, Constants.PAGE_TITLE_OF_CHANGE_PASSWORD));
		
		log.info("TC_06_NewPasswordAndConfirmPasswordMustMatch - STEP 02: Input valid password into New Password field");
		changePasswordPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_NEW_PASSWORD_FIELD, validationData.getValidNewPassword());
		
		log.info("TC_06_NewPasswordAndConfirmPasswordMustMatch - STEP 03: Input a different password into Confirm Password field");
		changePasswordPage.inputToDynamicTextbox(driver, Constants.NAME_ATTRIBUTE_OF_CONFIRM_PASSWORD_FIELD, validationData.getConfirmPasswordNotmatch());
		
		log.info("TC_06_NewPasswordAndConfirmPasswordMustMatch - STEP 04: Verify 'Passwords do not Match' message is displayed");
		verifyEquals(changePasswordPage.getErrorMessageOfDynamicField(driver, Constants.LABEL_OF_CONFIRM_PASSWORD_FIELD), validationData.getConfirmPasswordNotmatchErrorMsg());
	}

	@AfterClass (alwaysRun=true)
	public void tearDown() {
		closeBrowserAndDriver(driver);
	}

}
