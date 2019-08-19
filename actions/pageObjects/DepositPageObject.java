package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.DepositPageUI;
import commons.AbstractPage;

public class DepositPageObject extends AbstractPage{
	private WebDriver driver;

	public DepositPageObject(WebDriver driver) {
		this.driver = driver;
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
