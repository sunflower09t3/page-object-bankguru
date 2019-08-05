package bankguru;

public class NewAccountPageUI {
	public static final String CUSTOMER_ID_TEXTBOX = "//input[@name='cusid']";
	public static final String ACCOUNT_TYPE_DROPDOWN = "//select[@name='selaccount']";
	public static final String INITIAL_DEPOSIT_TEXTBOX = "//input[@name='inideposit']";
	public static final String SUBMIT_BUTTON = "//input[@name='button2']";
	public static final String ACCOUNT_GENERATED_SUCCESSFUL_MESSAGE = "//p[@class='heading3' and text()='Account Generated Successfully!!!']";
	public static final String ACCOUNT_ID_TEXT_IN_TABLE = "//td[text()='Account ID']/following-sibling::td";
	public static final String CURRENT_AMOUNT_TEXT_IN_TABLE = "//td[text()='Current Amount']/following-sibling::td";
}
