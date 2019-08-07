package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.ChangePasswordPageUI;
import commons.AbstractPage;

public class ChangePasswordPageObject extends AbstractPage {
	private WebDriver driver;

	public ChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputNothingToOldPasswordTextboxAndPressTabKey() {
		waitForElementVisible(driver, ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX, "");
		sendKeyBoardToElement(driver, ChangePasswordPageUI.OLD_PASSWORD_TEXTBOX, Keys.TAB);
	}

	public boolean isOldPasswordMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, ChangePasswordPageUI.OLD_PASSWORD_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, ChangePasswordPageUI.OLD_PASSWORD_MUST_NOT_BLANK_MESSAGE);
	}

	public void inputNothingToNewPasswordTextboxAndPressTabKey() {
		waitForElementVisible(driver, ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX, "");
		sendKeyBoardToElement(driver, ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX, Keys.TAB);
	}

	public boolean isNewPasswordMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, ChangePasswordPageUI.NEW_PASSWORD_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, ChangePasswordPageUI.NEW_PASSWORD_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isNewPasswordMustHaveAtLeastOneNumericValueMessageDisplayed() {
		waitForElementVisible(driver, ChangePasswordPageUI.NEW_PASSWORD_MUST_HAVE_AT_LEAST_ONE_NUMERIC_VALUE);
		return isControlDisplayed(driver, ChangePasswordPageUI.NEW_PASSWORD_MUST_HAVE_AT_LEAST_ONE_NUMERIC_VALUE);
	}

	public void inputToNewPasswordTextbox(String newPassword) {
		waitForElementVisible(driver, ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, ChangePasswordPageUI.NEW_PASSWORD_TEXTBOX, newPassword);
	}

	public boolean isNewPasswordMustHaveAtLeastOneSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, ChangePasswordPageUI.NEW_PASSWORD_MUST_HAVE_AT_LEAST_ONE_SPECIAL_CHARACTER);
		return isControlDisplayed(driver, ChangePasswordPageUI.NEW_PASSWORD_MUST_HAVE_AT_LEAST_ONE_SPECIAL_CHARACTER);
	}

	public boolean isNewPasswordMustNotContainPasswordStringMessageDisplayed() {
		waitForElementVisible(driver, ChangePasswordPageUI.NEW_PASSWORD_MUST_NOT_CONTAIN_PASSWORD_STRING);
		return isControlDisplayed(driver, ChangePasswordPageUI.NEW_PASSWORD_MUST_NOT_CONTAIN_PASSWORD_STRING);
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, ChangePasswordPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, ChangePasswordPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	public boolean isPasswordNotMatchMessageDisplayed() {
		waitForElementVisible(driver, ChangePasswordPageUI.PASSWORD_NOT_MATCH_MESSAGE);
		return isControlDisplayed(driver, ChangePasswordPageUI.PASSWORD_NOT_MATCH_MESSAGE);
	}

}
