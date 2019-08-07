package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.CustomisedStatementPageUI;
import commons.AbstractPage;

public class CustomisedStatementPageObject extends AbstractPage{
	private WebDriver driver;

	public CustomisedStatementPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputNothingToAccountNoTextboxAndPressTabKey() {
		waitForElementVisible(driver, CustomisedStatementPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, CustomisedStatementPageUI.ACCOUNT_NO_TEXTBOX, "");
		sendKeyBoardToElement(driver, CustomisedStatementPageUI.ACCOUNT_NO_TEXTBOX, Keys.TAB);
	}

	public boolean isAccountNoMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, CustomisedStatementPageUI.ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, CustomisedStatementPageUI.ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
	}

	public void inputToAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, CustomisedStatementPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, CustomisedStatementPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public boolean isAccountNoMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, CustomisedStatementPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, CustomisedStatementPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isAccountNoMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, CustomisedStatementPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, CustomisedStatementPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isMinimumTransactionValueMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, CustomisedStatementPageUI.MINIMUM_TRANSACTION_VALUE_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, CustomisedStatementPageUI.MINIMUM_TRANSACTION_VALUE_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isMinimumTransactionValueMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, CustomisedStatementPageUI.MINIMUM_TRANSACTION_VALUE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, CustomisedStatementPageUI.MINIMUM_TRANSACTION_VALUE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public void inputToMinimumTransactionValueTextbox(String minTransactionValue) {
		waitForElementVisible(driver, CustomisedStatementPageUI.MINIMUM_TRANSACTION_VALUE_TEXTBOX);
		sendKeyToElement(driver, CustomisedStatementPageUI.MINIMUM_TRANSACTION_VALUE_TEXTBOX, minTransactionValue);
	}

	public void inputToNumberOfTransactionTextbox(String numOfTransaction) {
		waitForElementVisible(driver, CustomisedStatementPageUI.NUMBER_OF_TRANSACTION_TEXTBOX);
		sendKeyToElement(driver, CustomisedStatementPageUI.NUMBER_OF_TRANSACTION_TEXTBOX, numOfTransaction);
	}

	public boolean isNumberOfTransactionMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, CustomisedStatementPageUI.NUMBER_OF_TRANSACTION_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, CustomisedStatementPageUI.NUMBER_OF_TRANSACTION_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isNumberOfTransactionMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, CustomisedStatementPageUI.NUMBER_OF_TRANSACTION_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
		return isControlDisplayed(driver, CustomisedStatementPageUI.NUMBER_OF_TRANSACTION_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
	}

	

}
