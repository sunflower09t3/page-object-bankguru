package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.AbstractPage;

public class CustomisedStatementPageObject extends AbstractPage {
	private WebDriver driver;

	public CustomisedStatementPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCustomisedStatementPageURL() {
		return getCurrentPageURL(driver);
	}

}
