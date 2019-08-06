package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.BalanceEnquiryPageUI;
import bankguru.DeleteAccountPageUI;
import commons.AbstractPage;
import commons.PageGeneratorManager;

public class DeleteAccountPageObject extends AbstractPage{
	private WebDriver driver;

	public DeleteAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, DeleteAccountPageUI.SUBMIT_BUTTON);
		clickToElement(driver, DeleteAccountPageUI.SUBMIT_BUTTON);
	}

	public void acceptDeleteAccountConfirmationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}


	public String getAlertMessage() {
		waitForAlertPresent(driver);
		return getTextAlert(driver);
	}

	public HomePageObject acceptAccountDeletedInformationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
		return PageGeneratorManager.getHomePage(driver);
	}

	public void inputNothingToAccountNoTextboxAndPressTabKey() {
		waitForElementVisible(driver, DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX, "");
		sendKeyBoardToElement(driver, DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX, Keys.TAB);
	}

	public boolean isAccountNoMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, DeleteAccountPageUI.ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, DeleteAccountPageUI.ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isAccountNoMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, DeleteAccountPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, DeleteAccountPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isAccountNoMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, DeleteAccountPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
		return isControlDisplayed(driver, DeleteAccountPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
	}

}
