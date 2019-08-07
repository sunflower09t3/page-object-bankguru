package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import commons.AbstractPage;

public class FundTransferPageObject extends AbstractPage {
	private WebDriver driver;

	public FundTransferPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToPayerAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public void inputToPayeeAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public void inputToAmountTextbox(String accountNo) {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_TEXTBOX);
		sendKeyToElement(driver, FundTransferPageUI.AMOUNT_TEXTBOX, accountNo);
	}

	public void inputToDescriptionTextbox(String description) {
		waitForElementVisible(driver, FundTransferPageUI.DESCRIPTION_TEXTBOX);
		sendKeyToElement(driver, FundTransferPageUI.DESCRIPTION_TEXTBOX, description);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, FundTransferPageUI.SUBMIT_BUTTON);
		clickToElement(driver, FundTransferPageUI.SUBMIT_BUTTON);
	}

	public String getTransferAmount() {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_TEXT_IN_TABLE);
		return getTextElement(driver, FundTransferPageUI.AMOUNT_TEXT_IN_TABLE);
	}

	public boolean isFundTransferSuccessfulMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.FUND_TRANSFER_DETAILS_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.FUND_TRANSFER_DETAILS_MESSAGE);
	}

	public void inputNothingToPayerAccountNoTextboxAndPressTabKey() {
		waitForElementVisible(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_TEXTBOX, "");
		sendKeyBoardToElement(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_TEXTBOX, Keys.TAB);
	}

	public boolean isPayerAccountNoMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isPayerAccountNoMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isPayerAccountNoMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.PAYER_ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public void inputNothingToPayeeAccountNoTextboxAndPressTabKey() {
		waitForElementVisible(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_TEXTBOX, "");
		sendKeyBoardToElement(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_TEXTBOX, Keys.TAB);
	}

	public boolean isPayeeAccountNoMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isPayeeAccountNoMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isPayeeAccountNoMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.PAYEE_ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public void inputNothingToAmountTextboxAndPressTabKey() {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_TEXTBOX);
		sendKeyToElement(driver, FundTransferPageUI.AMOUNT_TEXTBOX, "");
		sendKeyBoardToElement(driver, FundTransferPageUI.AMOUNT_TEXTBOX, Keys.TAB);
	}

	public boolean isAmountMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.AMOUNT_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isAmountMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.AMOUNT_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isAmountMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
		return isControlDisplayed(driver, FundTransferPageUI.AMOUNT_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
	}

	public void inputNothingToDescriptionTextboxAndPressTabKey() {
		waitForElementVisible(driver, FundTransferPageUI.DESCRIPTION_TEXTBOX);
		sendKeyToElement(driver, FundTransferPageUI.DESCRIPTION_TEXTBOX, "");
		sendKeyBoardToElement(driver, FundTransferPageUI.DESCRIPTION_TEXTBOX, Keys.TAB);
	}

	public boolean isDescriptionMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.DESCRIPTION_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, FundTransferPageUI.DESCRIPTION_MUST_NOT_BLANK_MESSAGE);
	}
}
