package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.BalanceEnquiryPageObject;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomisedStatementPageObject;
import pageObjects.DeleteAccountPageObject;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.DepositPageObject;
import pageObjects.EditAccountPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.LogoutPageObject;
import pageObjects.MiniStatementPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.WithDrawalPageObject;

public class PageGeneratorManager {

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}
	
	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
		return new DeleteCustomerPageObject(driver);
	}
	
	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}
	
	public static EditAccountPageObject getEditAccountPage(WebDriver driver) {
		return new EditAccountPageObject(driver);
	}
	
	public static DeleteAccountPageObject getDeleteAccountpage(WebDriver driver) {
		return new DeleteAccountPageObject(driver);
	}
	
	public static DepositPageObject getDepositPage(WebDriver driver) {
		return  new DepositPageObject(driver);
	}
	
	public static WithDrawalPageObject getWithdrawlPage(WebDriver driver) {
		return new WithDrawalPageObject(driver);
	}
	
	public static FundTransferPageObject getFunTransferPage(WebDriver driver) {
		return new FundTransferPageObject(driver);
	}
	
	public static ChangePasswordPageObject getChangePasswordPage(WebDriver driver) {
		return new ChangePasswordPageObject(driver);
	}
	
	public static BalanceEnquiryPageObject getBalanceEnquiryPage(WebDriver driver) {
		return new BalanceEnquiryPageObject(driver);
	}
	
	public static MiniStatementPageObject getMiniStatementPage(WebDriver driver) {
		return new MiniStatementPageObject(driver);
	}
	
	public static CustomisedStatementPageObject getCustomisedStatementPage(WebDriver driver) {
		return new CustomisedStatementPageObject(driver);
	}
	
	public static LogoutPageObject getLogoutPage(WebDriver driver) {
		return new LogoutPageObject(driver);
	}
}
