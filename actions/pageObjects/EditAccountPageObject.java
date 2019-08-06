package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.EditAccountPageUI;
import bankguru.EditCustomerPageUI;
import commons.AbstractPage;

public class EditAccountPageObject extends AbstractPage{
	private WebDriver driver;

	public EditAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, EditAccountPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, EditAccountPageUI.SUBMIT_BUTTON);
		clickToElement(driver, EditAccountPageUI.SUBMIT_BUTTON);
	}

	public void selectAccountType(String accountType) {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
		selectItemInHtmlDropdownByVisibleTex(driver, EditAccountPageUI.ACCOUNT_TYPE_DROPDOWN, accountType);
	}

	public boolean isAccountDetailsUpdatedSuccessfulMessageDisplayed() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_DETAILS_UPDATED_SUCCESSFUL_MESSAGE);
		return isControlDisplayed(driver, EditAccountPageUI.ACCOUNT_DETAILS_UPDATED_SUCCESSFUL_MESSAGE);
	}

	public String getAccountType() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_TYPE_TEXT_IN_TABLE);
		return getTextElement(driver, EditAccountPageUI.ACCOUNT_TYPE_TEXT_IN_TABLE);
	}

	public String getAlertMessage() {
		waitForAlertPresent(driver);
		return getTextAlert(driver);
	}

	public void acceptAccountNotExistInformationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}

	public void inputNothingToAccountNoTextboxAndPressTabKey() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, EditAccountPageUI.ACCOUNT_NO_TEXTBOX, "");
		sendKeyBoardToElement(driver, EditAccountPageUI.ACCOUNT_NO_TEXTBOX, Keys.TAB);
	}

	public boolean isAccountNoMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, EditAccountPageUI.ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isAccountNoMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, EditAccountPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isAccountNoMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
		return isControlDisplayed(driver, EditAccountPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
	}

	public boolean isEditAccountEntryFormDisplayed() {
		waitForElementVisible(driver, EditAccountPageUI.EDIT_ACCOUNT_ENTRY_FORM_TEXT);
		return isControlDisplayed(driver, EditAccountPageUI.EDIT_ACCOUNT_ENTRY_FORM_TEXT);
	}

}
