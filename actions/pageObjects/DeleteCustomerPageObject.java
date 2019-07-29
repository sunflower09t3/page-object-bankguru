package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteCustomerPageObject extends AbstractPage{
	private WebDriver driver;

	public DeleteCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
