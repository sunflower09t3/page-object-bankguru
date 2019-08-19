package pageObjects;

import org.openqa.selenium.WebDriver;
import bankguru.NewAccountPageUI;
import commons.AbstractPage;

public class NewAccountPageObject extends AbstractPage{
	private WebDriver driver;

	public NewAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectAccountType(String accountType) {
		waitForElementVisible(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
		selectItemInHtmlDropdownByVisibleTex(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN, accountType);
	}

	public String getResultMessage() {
		waitForElementVisible(driver, NewAccountPageUI.ADD_ACCOUNT_RESULT);
		return getTextElement(driver, NewAccountPageUI.ADD_ACCOUNT_RESULT);
	}

	public String getAccountID() {
		waitForElementVisible(driver, NewAccountPageUI.ACCOUNT_ID_TEXT_IN_TABLE);
		return getTextElement(driver, NewAccountPageUI.ACCOUNT_ID_TEXT_IN_TABLE);
	}

	public String getCurrentAmount() {
		waitForElementVisible(driver, NewAccountPageUI.CURRENT_AMOUNT_TEXT_IN_TABLE);
		return getTextElement(driver, NewAccountPageUI.CURRENT_AMOUNT_TEXT_IN_TABLE);
	}

}