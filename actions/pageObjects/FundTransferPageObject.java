package pageObjects;

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
}
