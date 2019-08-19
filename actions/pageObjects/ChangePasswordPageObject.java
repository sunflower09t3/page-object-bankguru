package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.AbstractPage;

public class ChangePasswordPageObject extends AbstractPage {
	private WebDriver driver;

	public ChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
