package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.DepositPageUI;
import commons.AbstractPage;

public class DepositPageObject extends AbstractPage{
	private WebDriver driver;

	public DepositPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, DepositPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, DepositPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public void inputToAmountTextbox(String amount) {
		waitForElementVisible(driver, DepositPageUI.AMOUNT_TEXTBOX);
		sendKeyToElement(driver, DepositPageUI.AMOUNT_TEXTBOX, amount);
	}

	public void inputToDescriptionTextbox(String description) {
		waitForElementVisible(driver, DepositPageUI.DESCRIPTION_TEXTBOX);
		sendKeyToElement(driver, DepositPageUI.DESCRIPTION_TEXTBOX, description);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, DepositPageUI.SUBMIT_BUTTON);
		clickToElement(driver, DepositPageUI.SUBMIT_BUTTON);
	}

	public String getResultMessage() {
		waitForElementVisible(driver, DepositPageUI.TRANSACTION_RESULT_MESSAGE);
		return getTextElement(driver, DepositPageUI.TRANSACTION_RESULT_MESSAGE);
	}

	public String getCurrentBalance() {
		waitForElementVisible(driver, DepositPageUI.CURRENT_BALANCE_TEXT_IN_TABLE);
		return getTextElement(driver, DepositPageUI.CURRENT_BALANCE_TEXT_IN_TABLE);
	}
}
