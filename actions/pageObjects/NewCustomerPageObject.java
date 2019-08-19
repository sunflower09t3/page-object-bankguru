package pageObjects;

import org.openqa.selenium.WebDriver;
import bankguru.NewCustomerPageUI;
import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage {
	private WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getResultMessage() {
		waitForElementVisible(driver, NewCustomerPageUI.ADD_CUSTOMER_RESULT);
		return getTextElement(driver, NewCustomerPageUI.ADD_CUSTOMER_RESULT);
	}

	public String getCustomerID() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_ID_TEXT_IN_TABLE);
		return getTextElement(driver, NewCustomerPageUI.CUSTOMER_ID_TEXT_IN_TABLE);
	}

}
