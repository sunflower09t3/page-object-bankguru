package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.MiniStatementPageUI;
import commons.AbstractPage;

public class MiniStatementPageObject extends AbstractPage{
	private WebDriver driver;

	public MiniStatementPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputNothingToAccountNoTextboxAndPressTabKey() {
		waitForElementVisible(driver, MiniStatementPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, MiniStatementPageUI.ACCOUNT_NO_TEXTBOX, "");
		sendKeyBoardToElement(driver, MiniStatementPageUI.ACCOUNT_NO_TEXTBOX, Keys.TAB);
	}

	public boolean isAccountNoMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, MiniStatementPageUI.ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, MiniStatementPageUI.ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isAccountNoMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, MiniStatementPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER);
		return isControlDisplayed(driver, MiniStatementPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER);
	}

	public boolean isAccountNoMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, MiniStatementPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
		return isControlDisplayed(driver, MiniStatementPageUI.ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
	}

	public void inputToAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, MiniStatementPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, MiniStatementPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
	}
}
