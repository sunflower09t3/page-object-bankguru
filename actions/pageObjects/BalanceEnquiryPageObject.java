package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.BalanceEnquiryPageUI;
import commons.AbstractPage;

public class BalanceEnquiryPageObject extends AbstractPage {
	private WebDriver driver;

	public BalanceEnquiryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getResultMessage() {
		waitForElementVisible(driver, BalanceEnquiryPageUI.BALANCE_DETAILS_MESSAGE);
		return getTextElement(driver, BalanceEnquiryPageUI.BALANCE_DETAILS_MESSAGE);
	}

	public String getBalance() {
		waitForElementVisible(driver, BalanceEnquiryPageUI.BALANCE_TEXT_IN_TABLE);
		return getTextElement(driver, BalanceEnquiryPageUI.BALANCE_TEXT_IN_TABLE);
	}

}
