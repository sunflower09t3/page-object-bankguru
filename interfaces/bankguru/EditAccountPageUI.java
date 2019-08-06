package bankguru;

public class EditAccountPageUI {
	public static final String ACCOUNT_NO_TEXTBOX = "//input[@name='accountno']";
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	public static final String ACCOUNT_TYPE_DROPDOWN = "//select[@name='a_type']";
	public static final String ACCOUNT_DETAILS_UPDATED_SUCCESSFUL_MESSAGE = "//p[@class='heading3' and text()='Account details updated Successfully!!!']";
	public static final String ACCOUNT_TYPE_TEXT_IN_TABLE = "//td[text()='Account Type']/following-sibling::td";
	public static final String ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE = "//*[@id='message2' and text()='Account Number must not be blank']";
	public static final String ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message2' and text()='Characters are not allowed']";
	public static final String ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER = "//*[@id='message2' and text()='Special characters are not allowed']";
	public static final String EDIT_ACCOUNT_ENTRY_FORM_TEXT = "//p[@class='heading3' and text()='Edit Account Entry Form']";
}
