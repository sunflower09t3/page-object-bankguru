package bankguru;

public class ChangePasswordPageUI {
	public static final String OLD_PASSWORD_TEXTBOX = "//input[@name='oldpassword']";
	public static final String NEW_PASSWORD_TEXTBOX = "//input[@name='newpassword']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@name='confirmpassword']";
	public static final String OLD_PASSWORD_MUST_NOT_BLANK_MESSAGE = "//*[@id='message20' and text()='Old Password must not be blank']";
	public static final String NEW_PASSWORD_MUST_NOT_BLANK_MESSAGE = "//*[@id='message21' and text()='New Password must not be blank']";
	public static final String NEW_PASSWORD_MUST_HAVE_AT_LEAST_ONE_NUMERIC_VALUE = "//*[@id='message21' and text()='Enter at-least one numeric value']";
	public static final String NEW_PASSWORD_MUST_HAVE_AT_LEAST_ONE_SPECIAL_CHARACTER = "//*[@id='message21' and text()='Enter at-least one special character']";
	public static final String NEW_PASSWORD_MUST_NOT_CONTAIN_PASSWORD_STRING = "//*[@id='message21' and text()='Choose a difficult Password']";
	public static final String PASSWORD_NOT_MATCH_MESSAGE = "//*[@id='message22' and text()='Passwords do not Match']";

}
