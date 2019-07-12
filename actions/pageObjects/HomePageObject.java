package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;

public class HomePageObject extends AbstractPage{
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isWelcomeMessageDisplayed(String expectedText) {
		String actualText = getTextElement(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		return actualText.equals(expectedText);
	}

	public boolean isUserIDDisplayed(String userID) {
		String actualText = getTextElement(driver, HomePageUI.USER_ID_TEXT);
		return actualText.contains(userID);
	}

	public void clickNewCustomerLink() {
		waitForElementVisible(driver, HomePageUI.NEW_CUSTOMER_LINK);
		clickToElement(driver, HomePageUI.NEW_CUSTOMER_LINK);
	}


}
