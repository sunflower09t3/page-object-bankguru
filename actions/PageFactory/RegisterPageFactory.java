package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class RegisterPageFactory extends AbstractPage{
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@name='emailid']")
	private WebElement emailIDTextbox;
	
	@FindBy(how=How.XPATH, using="//*[@name='btnLogin']")
	private WebElement submitButton;
	
	@FindBy(how=How.XPATH, using="//td[contains(.,'User ID')]/following-sibling::td")
	private WebElement userIDText;
	
	@FindBy(how=How.XPATH, using="//td[contains(.,'Password')]/following-sibling::td")
	private WebElement passwordText;
	
	
	public RegisterPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputToEmailIDTextbox(String email) {
		emailIDTextbox.sendKeys(email);
	}

	public void clickSubmitButton() {
		submitButton.click();
	}

	public String getUserIDInfo() {
		return userIDText.getText();
	}

	public String getPasswordInfo() {
		return passwordText.getText();
	}
	
	public void openLoginPageURL(String url) {
		openURL(driver, url);	
	}
}
