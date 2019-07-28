package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;

public class LoginPageFactory extends AbstractPage{
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//a[.='here']")
	private WebElement hereLink;
	
	@FindBy(how=How.XPATH, using="//*[@name='uid']")
	private WebElement userIDTextbox;
	
	@FindBy(how=How.XPATH, using="//*[@name='password']")
	private WebElement passwordTextbox;
	
	@FindBy(how=How.XPATH, using="//*[@name='btnLogin']")
	private WebElement loginButton;
	
	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickHereLink() {
		hereLink.click();
	}

	public String getLoginPageURL() {
		return driver.getCurrentUrl();
	}

	public void inputToUserIDTextbox(String userID) {
		userIDTextbox.sendKeys(userID);
	}

	public void inputToPasswordTextbox(String password) {
		passwordTextbox.sendKeys(password);
	}

	public HomePageObject clickLoginButton() {
		loginButton.click();
		return PageGeneratorManager.getHomePage(driver);
	}

}
