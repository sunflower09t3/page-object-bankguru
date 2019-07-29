package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class LogoutPageObject extends AbstractPage{
	private WebDriver driver;

	public LogoutPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
