package pageObjects;

import org.openqa.selenium.WebDriver;
import bankguru.FundTransferPageUI;
import commons.AbstractPage;

public class FundTransferPageObject extends AbstractPage {
	private WebDriver driver;

	public FundTransferPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTransferAmount() {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_TEXT_IN_TABLE);
		return getTextElement(driver, FundTransferPageUI.AMOUNT_TEXT_IN_TABLE);
	}
	
	public String getResultMessage() {
		waitForElementVisible(driver, FundTransferPageUI.FUND_TRANSFER_RESULT);
		return getTextElement(driver, FundTransferPageUI.FUND_TRANSFER_RESULT);
	}

}