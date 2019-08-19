package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.WithdrawalPageUI;
import commons.AbstractPage;

public class WithdrawalPageObject extends AbstractPage{
	private WebDriver driver;

	public WithdrawalPageObject(WebDriver driver) {
		this.driver = driver;
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
