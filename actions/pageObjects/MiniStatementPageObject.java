package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class MiniStatementPageObject extends AbstractPage{
	private WebDriver driver;

	public MiniStatementPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
