package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.WithdrawalPageUI;
import commons.AbstractPage;

public class WithdrawalPageObject extends AbstractPage{
	private WebDriver driver;

	public WithdrawalPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, WithdrawalPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, WithdrawalPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public void inputToAmountTextbox(String amount) {
		waitForElementVisible(driver, WithdrawalPageUI.AMOUNT_TEXTBOX);
		sendKeyToElement(driver, WithdrawalPageUI.AMOUNT_TEXTBOX, amount);
	}

	public void inputToDescriptionTextbox(String description) {
		waitForElementVisible(driver, WithdrawalPageUI.DESCRIPTION_TEXTBOX);
		sendKeyToElement(driver, WithdrawalPageUI.DESCRIPTION_TEXTBOX, description);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, WithdrawalPageUI.SUBMIT_TEXTBOX);
		clickToElement(driver, WithdrawalPageUI.SUBMIT_TEXTBOX);
	}

	public String getResultMessage() {
		waitForElementVisible(driver, WithdrawalPageUI.TRANSACTION_RESULT_MESSSAGE);
		return getTextElement(driver, WithdrawalPageUI.TRANSACTION_RESULT_MESSSAGE);
	}

	public String getCurrentBalance() {
		waitForElementVisible(driver, WithdrawalPageUI.CURRENT_BALANCE_TEXT_IN_TABLE);
		return getTextElement(driver, WithdrawalPageUI.CURRENT_BALANCE_TEXT_IN_TABLE);
	}
}
