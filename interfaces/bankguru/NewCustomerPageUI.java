package bankguru;

public class NewCustomerPageUI {
	// Textbox
	public static final String CUSTOMER_NAME_TEXTBOX = "//input[@name='name']";
	public static final String CITY_TEXTBOX = "//input[@name='city']";
	public static final String STATE_TEXTBOX = "//input[@name='state']";
	public static final String PIN_TEXTBOX = "//input[@name='pinno']";
	public static final String TELEPHONE_TEXBOX = "//input[@name='telephoneno']";
	public static final String EMAIL_TEXTBOX = "//input[@name='emailid']";
	public static final String PASSWORD_TEXTBOX = "//input[@name='password']";
	public static final String DATE_OF_BIRTH_TEXTBOX = "//input[@name='dob']";
	
	// Textarea
	public static final String ADDRESS_TEXT_AREA = "//textarea[@name='addr']";
	
	// Button
	public static final String MALE_GENDER_RADIO_BUTTON = "//input[@name='rad1' and @value='m']";
	public static final String FEMALE_GENDER_RADIO_BUTTON = "//input[@name='rad1' and @value='f']";
	public static final String SUBMIT_BUTTON = "//input[@name='sub']";
	
	// Text
	public static final String ADD_CUSTOMER_RESULT = "//p[@class='heading3']";
	public static final String CUSTOMER_ID_TEXT_IN_TABLE = "//td[text()='Customer ID']/following-sibling::td";
}
