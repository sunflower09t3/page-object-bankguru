package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.RegisterPageUI;
import commons.AbstractPage;

public class RegisterPageObject extends AbstractPage{
	
	WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailIDTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_ID_TEXTBOX, email);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDInfo() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_TEXT);
		return getTextElement(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordInfo() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getTextElement(driver, RegisterPageUI.PASSWORD_TEXT);
	}
	
	public void openLoginPageURL(String url) {
		openURL(driver, url);
	}

}
