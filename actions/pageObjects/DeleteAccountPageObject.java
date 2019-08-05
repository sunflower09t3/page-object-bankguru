package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.BalanceEnquiryPageUI;
import bankguru.DeleteAccountPageUI;
import commons.AbstractPage;
import commons.PageGeneratorManager;

public class DeleteAccountPageObject extends AbstractPage{
	private WebDriver driver;

	public DeleteAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, DeleteAccountPageUI.SUBMIT_BUTTON);
		clickToElement(driver, DeleteAccountPageUI.SUBMIT_BUTTON);
	}

	public void acceptDeleteAccountConfirmationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}


	public String getAlertMessage() {
		waitForAlertPresent(driver);
		return getTextAlert(driver);
	}

	public HomePageObject acceptAccountDeletedInformationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
		return PageGeneratorManager.getHomePage(driver);
	}

}
