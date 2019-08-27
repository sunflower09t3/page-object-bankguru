package bankguru;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidationDataJson {
	public static ValidationDataJson get(String dataFilePath) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(dataFilePath), ValidationDataJson.class);
	}
	
	
	@JsonProperty("customerNameContainNumber")
	private String customerNameContainNumber;
	
	@JsonProperty("customerNameContainSpecialCharacter")
	private String customerNameContainSpecialCharacter;

	@JsonProperty("customerNameBeginWithSpace")
	private String customerNameBeginWithSpace;

	@JsonProperty("addressBeginWithSpace")
	private String addressBeginWithSpace;

	@JsonProperty("cityContainNumber")
	private String cityContainNumber;

	@JsonProperty("cityContainSpecialCharacter")
	private String cityContainSpecialCharacter;

	@JsonProperty("cityBeginWithSpace")
	private String cityBeginWithSpace;

	@JsonProperty("stateContainNumber")
	private String stateContainNumber;

	@JsonProperty("stateContainSpecialCharacter")
	private String stateContainSpecialCharacter;

	@JsonProperty("stateBeginWithSpace")
	private String stateBeginWithSpace;

	@JsonProperty("pinContainCharacter")
	private String pinContainCharacter;

	@JsonProperty("pinWithLessThan6Digits")
	private String pinWithLessThan6Digits;

	@JsonProperty("pinContainSpecialCharacter")
	private String pinContainSpecialCharacter;

	@JsonProperty("pinBeginWithSpace")
	private String pinBeginWithSpace;

	@JsonProperty("pinContainSpace")
	private String pinContainSpace;

	@JsonProperty("telephoneBeginWithSpace")
	private String telephoneBeginWithSpace;

	@JsonProperty("telephoneContainSpace")
	private String telephoneContainSpace;

	@JsonProperty("telephoneContainSpecialCharacter")
	private String telephoneContainSpecialCharacter;

	@JsonProperty("invalidEmail")
	private String invalidEmail;

	@JsonProperty("emailBeginWithSpace")
	private String emailBeginWithSpace;

	@JsonProperty("customerIdContainCharacter")
	private String customerIdContainCharacter;

	@JsonProperty("customerIdContainSpecialCharacter")
	private String customerIdContainSpecialCharacter;

	@JsonProperty("customerIdContainSpace")
	private String customerIdContainSpace;

	@JsonProperty("customerIdBeginWithSpace")
	private String customerIdBeginWithSpace;

	@JsonProperty("initialDepositContainCharacter")
	private String initialDepositContainCharacter;

	@JsonProperty("initialDepositContainSpecialCharacter")
	private String initialDepositContainSpecialCharacter;

	@JsonProperty("initialDepositContainSpace")
	private String initialDepositContainSpace;

	@JsonProperty("initialDepositBeginWithSpace")
	private String initialDepositBeginWithSpace;

	@JsonProperty("accountNoContainCharacter")
	private String accountNoContainCharacter;

	@JsonProperty("accountNoContainSpecialCharacter")
	private String accountNoContainSpecialCharacter;

	@JsonProperty("accountNoContainSpace")
	private String accountNoContainSpace;

	@JsonProperty("accountNoBeginWithSpace")
	private String accountNoBeginWithSpace;

	@JsonProperty("minimumTransactionValueContainCharacter")
	private String minimumTransactionValueContainCharacter;

	@JsonProperty("minimumTransactionValueContainSpecialCharacter")
	private String minimumTransactionValueContainSpecialCharacter;

	@JsonProperty("minimumTransactionValueContainSpace")
	private String minimumTransactionValueContainSpace;

	@JsonProperty("minimumTransactionValueBeginWithSpace")
	private String minimumTransactionValueBeginWithSpace;

	@JsonProperty("numOfTransactionContainCharacter")
	private String numOfTransactionContainCharacter;

	@JsonProperty("numOfTransactionContainSpecialCharacter")
	private String numOfTransactionContainSpecialCharacter;

	@JsonProperty("numOfTransactionContainSpace")
	private String numOfTransactionContainSpace;

	@JsonProperty("numOfTransactionBeginWithSpace")
	private String numOfTransactionBeginWithSpace;

	@JsonProperty("payerAccountNoContainCharacter")
	private String payerAccountNoContainCharacter;

	@JsonProperty("payerAccountNoContainSpecialCharacter")
	private String payerAccountNoContainSpecialCharacter;

	@JsonProperty("payeeAccountNoContainCharacter")
	private String payeeAccountNoContainCharacter;

	@JsonProperty("payeeAccountNoContainSpecialCharacter")
	private String payeeAccountNoContainSpecialCharacter;

	@JsonProperty("fundtransferamountContainCharacter")
	private String fundtransferamountContainCharacter;

	@JsonProperty("fundtransferamountContainSpecialCharacter")
	private String fundtransferamountContainSpecialCharacter;

	@JsonProperty("newPasswordWithoutNumber")
	private String newPasswordWithoutNumber;

	@JsonProperty("newPasswordWithoutSpecialCharacter")
	private String newPasswordWithoutSpecialCharacter;

	@JsonProperty("newPasswordContainPasswordString")
	private String newPasswordContainPasswordString;

	@JsonProperty("validNewPassword")
	private String validNewPassword;

	@JsonProperty("confirmPasswordNotmatch")
	private String confirmPasswordNotmatch;
	
	public String getCustomerNameContainNumber() {
		return customerNameContainNumber;
	}
	public String getCustomerNameContainSpecialCharacter() {
		return customerNameContainSpecialCharacter;
	}
	public String getCustomerNameBeginWithSpace() {
		return customerNameBeginWithSpace;
	}
	public String getAddressBeginWithSpace() {
		return addressBeginWithSpace;
	}
	public String getCityContainNumber() {
		return cityContainNumber;
	}
	public String getCityContainSpecialCharacter() {
		return cityContainSpecialCharacter;
	}
	public String getCityBeginWithSpace() {
		return cityBeginWithSpace;
	}
	public String getStateContainNumber() {
		return stateContainNumber;
	}
	public String getStateContainSpecialCharacter() {
		return stateContainSpecialCharacter;
	}
	public String getStateBeginWithSpace() {
		return stateBeginWithSpace;
	}
	public String getPinContainCharacter() {
		return pinContainCharacter;
	}
	public String getPinWithLessThan6Digits() {
		return pinWithLessThan6Digits;
	}
	public String getPinContainSpecialCharacter() {
		return pinContainSpecialCharacter;
	}
	public String getPinBeginWithSpace() {
		return pinBeginWithSpace;
	}
	public String getPinContainSpace() {
		return pinContainSpace;
	}
	public String getTelephoneBeginWithSpace() {
		return telephoneBeginWithSpace;
	}
	public String getTelephoneContainSpace() {
		return telephoneContainSpace;
	}
	public String getTelephoneContainSpecialCharacter() {
		return telephoneContainSpecialCharacter;
	}
	public String getInvalidEmail() {
		return invalidEmail;
	}
	public String getEmailBeginWithSpace() {
		return emailBeginWithSpace;
	}
	public String getCustomerIdContainCharacter() {
		return customerIdContainCharacter;
	}
	public String getCustomerIdContainSpecialCharacter() {
		return customerIdContainSpecialCharacter;
	}
	public String getCustomerIdContainSpace() {
		return customerIdContainSpace;
	}
	public String getCustomerIdBeginWithSpace() {
		return customerIdBeginWithSpace;
	}
	public String getInitialDepositContainCharacter() {
		return initialDepositContainCharacter;
	}
	public String getInitialDepositContainSpecialCharacter() {
		return initialDepositContainSpecialCharacter;
	}
	public String getInitialDepositContainSpace() {
		return initialDepositContainSpace;
	}
	public String getInitialDepositBeginWithSpace() {
		return initialDepositBeginWithSpace;
	}
	public String getAccountNoContainCharacter() {
		return accountNoContainCharacter;
	}
	public String getAccountNoContainSpecialCharacter() {
		return accountNoContainSpecialCharacter;
	}
	public String getAccountNoContainSpace() {
		return accountNoContainSpace;
	}
	public String getAccountNoBeginWithSpace() {
		return accountNoBeginWithSpace;
	}
	public String getMinimumTransactionValueContainCharacter() {
		return minimumTransactionValueContainCharacter;
	}
	public String getMinimumTransactionValueContainSpecialCharacter() {
		return minimumTransactionValueContainSpecialCharacter;
	}
	public String getMinimumTransactionValueContainSpace() {
		return minimumTransactionValueContainSpace;
	}
	public String getMinimumTransactionValueBeginWithSpace() {
		return minimumTransactionValueBeginWithSpace;
	}
	public String getNumOfTransactionContainCharacter() {
		return numOfTransactionContainCharacter;
	}
	public String getNumOfTransactionContainSpecialCharacter() {
		return numOfTransactionContainSpecialCharacter;
	}
	public String getNumOfTransactionContainSpace() {
		return numOfTransactionContainSpace;
	}
	public String getNumOfTransactionBeginWithSpace() {
		return numOfTransactionBeginWithSpace;
	}
	public String getPayerAccountNoContainCharacter() {
		return payerAccountNoContainCharacter;
	}
	public String getPayerAccountNoContainSpecialCharacter() {
		return payerAccountNoContainSpecialCharacter;
	}
	public String getPayeeAccountNoContainCharacter() {
		return payeeAccountNoContainCharacter;
	}
	public String getPayeeAccountNoContainSpecialCharacter() {
		return payeeAccountNoContainSpecialCharacter;
	}
	public String getFundtransferamountContainCharacter() {
		return fundtransferamountContainCharacter;
	}
	public String getFundtransferamountContainSpecialCharacter() {
		return fundtransferamountContainSpecialCharacter;
	}
	public String getNewPasswordWithoutNumber() {
		return newPasswordWithoutNumber;
	}
	public String getNewPasswordWithoutSpecialCharacter() {
		return newPasswordWithoutSpecialCharacter;
	}
	public String getNewPasswordContainPasswordString() {
		return newPasswordContainPasswordString;
	}
	public String getValidNewPassword() {
		return validNewPassword;
	}
	public String getConfirmPasswordNotmatch() {
		return confirmPasswordNotmatch;
	}
	
}
