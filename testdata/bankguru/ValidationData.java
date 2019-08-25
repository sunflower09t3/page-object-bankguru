package bankguru;

public class ValidationData {
	public class NewCustomer{
		public static final String CUSTOMER_NAME_WITH_NUMBER = "myname123";
		public static final String CUSTOMER_NAME_WITH_SPECIAL_CHARACTER = "myname!@$";
		public static final String CUSTOMER_NAME_BEGIN_WITH_SPACE = " ";
		public static final String ADDRESS_BEGIN_WITH_SPACE = " ";
		public static final String CITY_WITH_NUMBER = "city123";
		public static final String CITY_WITH_SPECIAL_CHARACTER = "city@#%";
		public static final String CITY_BEGIN_WITH_SPACE = " ";
		public static final String STATE_WITH_NUMBER = "state123";
		public static final String STATE_WITH_SPECIAL_CHARACTER = "state*&";
		public static final String STATE_BEGIN_WITH_SPACE = " ";
		public static final String PIN_WITH_CHARACTER = "123pin";
		public static final String PIN_WITH_LESS_THAN_6_DIGITS = "123";
		public static final String PIN_WITH_SPECIAL_CHARACTER = "123$";
		public static final String PIN_BEGIN_WITH_SPACE = " ";
		public static final String PINT_WITH_SPACE = "123 45";
		public static final String TELEPHONE_BEGIN_WITH_SPACE = " ";
		public static final String TELEPHONE_WITH_SPACE = "123 123";
		public static final String TELEPHONE_WITH_SPECIAL_CHARACTER = "123@#$";
		public static final String INVALID_EMAIL = "myemail@";
		public static final String EMAIL_BEGIN_WITH_SPACE = " ";
	}

	public class EditCustomer{
		public static final String CUSTOMER_ID_WITH_CHARACTER = "123acc";
		public static final String CUSTOMER_ID_WITH_SPECIAL_CHARACTER = "123$%^";
		public static final String CITY_WITH_NUMBER = "city123";
		public static final String CITY_WITH_SPECIAL_CHARACTER = "city@#%";
		public static final String STATE_WITH_SPECIAL_CHARACTER = "state#$%";
		public static final String STATE_WITH_NUMBER = "state123";
		public static final String PIN_WITH_CHARACTER = "123abc";
		public static final String PIN_WITH_LESS_THAN_6_DIGITS = "123";
		public static final String PIN_WITH_MORE_THAN_6_DIGITS = "123456789";
		public static final String PIN_WITH_SPECIAL_CHARACTER = "123%$@";
		public static final String TELEPHONE_WITH_SPECIAL_CHARACTER = "123456789$%";
		public static final String INVALID_EMAIL = "tuongvi@";
	}
	
	public class DeleteCustomer{
		public static final String CUSTOMER_ID_CONTAIN_CHARACTER = "123abc";
		public static final String CUSTOMER_ID_CONTAIN_SPECIAL_CHARACTER = "123$%^";
		public static final String CUSTOMER_ID_CONTAIN_SPACE = "123 45";
		public static final String CUSTOMER_ID_BEGIN_WITH_SPACE = " 123";
	}
	
	public class NewAccount{
		public static final String CUSTOMER_ID_CONTAIN_CHARACTER = "123abc";
		public static final String CUSTOMER_ID_CONTAIN_SPECIAL_CHARACTER = "123$%^";
		public static final String CUSTOMER_ID_CONTAIN_SPACE = "123 45";
		public static final String CUSTOMER_ID_BEGIN_WITH_SPACE = " 123";
		public static final String INITIAL_DEPOSIT_CONTAIN_CHARACTER = "10000Acc";
		public static final String INITIAL_DEPOSIT_CONTAIN_SPECIAL_CHARACTER = "10000#$%";
		public static final String INITIAL_DEPOSIT_CONTAIN_SPACE = "100 00";
		public static final String INITIAL_DEPOSIT_BEGIN_WITH_SPACE = " 10000";
	}
	
	public class EditAccount{
		public static final String ACCOUNT_NO_CONTAIN_CHARACTER = "123Acc";
		public static final String ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER = "123$%^";
		public static final String ACCOUNT_NO_CONTAIN_SPACE = "123 456";
		public static final String ACCOUNT_NO_BEGIN_WITH_SPACE = " ";
	}
	
	public class DeleteAccount {
		public static final String ACCOUNT_NO_CONTAIN_CHARACTER = "123Acc";
		public static final String ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER = "123$%^";
		public static final String ACCOUNT_NO_CONTAIN_SPACE = "123 456";
		public static final String ACCOUNT_NO_BEGIN_WITH_SPACE = " ";
	}
	
	public class MiniStatement{
		public static final String ACCOUNT_NO_CONTAIN_CHARACTER = "123Acc";
		public static final String ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER = "123$%^";
		public static final String ACCOUNT_NO_CONTAIN_SPACE = "123 456";
		public static final String ACCOUNT_NO_BEGIN_WITH_SPACE = " ";
	}
	
	public class CustomisedStatement{
		public static final String ACCOUNT_NO_CONTAIN_CHARACTER = "123Acc";
		public static final String ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER = "123$%^";
		public static final String ACCOUNT_NO_CONTAIN_SPACE = "123 456";
		public static final String ACCOUNT_NO_BEGIN_WITH_SPACE = " ";
		public static final String MINIMUM_TRANSACTION_VALUE_CONTAIN_CHARACTER = "123Acc";
		public static final String MINIMUM_TRANSACTION_VALUE_CONTAIN_SPECIAL_CHARACTER = "123#$%";
		public static final String MINIMUM_TRANSACTION_VALUE_CONTAIN_SPACE = "123 456";
		public static final String MINIMUM_TRANSACTION_VALUE_BEGIN_WITH_SPACE = " ";
		public static final String NUM_OFT_RANSACTION_CONTAIN_CHARACTER = "123Acc";
		public static final String NUM_OF_TRANSACTION_CONTAIN_SPECIAL_CHARACTER = "123#$%";
		public static final String NUM_OF_TRANSACTION_CONTAIN_SPACE = "123 456";
		public static final String NUM_OF_TRANSACTION_BEGIN_WITH_SPACE = " ";

	}
	
	public class FundTransfer{
		public static final String PAYER_ACCOUNT_NO_CONTAIN_CHARACTER = "123payer";
		public static final String PAYER_ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER = "123@#$";
		public static final String PAYEE_ACCOUNT_NO_CONTAIN_CHARACTER = "123payee";
		public static final String PAYEE_ACCOUNT_NO_CONTAIN_SPECIAL_CHARACTER = "123&*(";
		public static final String AMOUNT_CONTAIN_CHARACTER = "123amount";
		public static final String AMOUNT_CONTAIN_SPECIAL_CHARACTER = "123$";
	}
	
	public class ChangePassword{
		public static final String NEW_PASSWORD_WITHOUT_NUMBER = "Guru!@#$";
		public static final String NEW_PASSWORD_WITHOUT_SPECIAL_CHARACTER = "Guru99";
		public static final String NEW_PASSWORD_CONTAIN_PASSWORD_STRING = "Guru99!password";
		public static final String VALID_NEW_PASSWORD = "Guru99!@";
		public static final String CONFIRM_PASSWORD_MISMATCH = "ConfirmPassword!=NewPassword";
	}
}
