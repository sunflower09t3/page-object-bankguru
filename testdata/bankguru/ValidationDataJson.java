package bankguru;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidationDataJson {
	public static ValidationDataJson get(String dataFilePath) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(dataFilePath), ValidationDataJson.class);
	}

	@SuppressWarnings("unchecked")
	@JsonProperty("customerName")
	private void unpackCustomerName(Map<String, Object> customerNameMap) {
		Map<String, Object> blankNameMap = (Map<String, Object>) customerNameMap.get("blank");
		this.customerNameBlankErrorMsg = (String) blankNameMap.get("errorMessage");
		
		Map<String, Object> nameContainNumberMap = (Map<String, Object>) customerNameMap.get("containNumber");
		this.customerNameContainNumber = (String) nameContainNumberMap.get("input");
		this.customerNameContainNumberErrorMsg = (String) nameContainNumberMap.get("errorMessage");
		
		Map<String, Object> nameContainSpecialCharacterMap = (Map<String, Object>) customerNameMap.get("containSpecialCharacter");
		this.customerNameContainSpecialCharacter = (String) nameContainSpecialCharacterMap.get("input");
		this.customerNameContainSpecialCharacterErrorMsg = (String) nameContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> nameBeginWithSpaceMap = (Map<String, Object>) customerNameMap.get("beginWithSpace");
		this.customerNameBeginWithSpace = (String) nameBeginWithSpaceMap.get("input");
		this.customerNameBeginWithSpaceErrorMsg = (String) nameBeginWithSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("address")
	private void unpackAddress(Map<String, Object> addressMap) {
		Map<String, Object> blankAddressMap = (Map<String, Object>) addressMap.get("blank");
		this.addressBlankErrorMsg = (String) blankAddressMap.get("errorMessage");
		
		Map<String, Object> addressBeginWithSpaceMap = (Map<String, Object>) addressMap.get("beginWithSpace");
		this.addressBeginWithSpace = (String) addressBeginWithSpaceMap.get("input");
		this.addressBeginWithSpaceErrorMsg = (String) addressBeginWithSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("city")
	private void unpackCity(Map<String, Object> cityMap) {
		Map<String, Object> blankCityMap = (Map<String, Object>) cityMap.get("blank");
		this.cityBlankErrorMsg = (String) blankCityMap.get("errorMessage");
		
		Map<String, Object> cityContainNumberMap = (Map<String, Object>) cityMap.get("containNumber");
		this.cityContainNumber = (String) cityContainNumberMap.get("input");
		this.cityContainNumberErrorMsg = (String) cityContainNumberMap.get("errorMessage");
		
		Map<String, Object> cityContainSpecialCharacterMap = (Map<String, Object>) cityMap.get("containSpecialCharacter");
		this.cityContainSpecialCharacter = (String) cityContainSpecialCharacterMap.get("input");
		this.cityContainSpecialCharacterErrorMsg = (String) cityContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> cityBeginWithSpaceMap = (Map<String, Object>) cityMap.get("beginWithSpace");
		this.cityBeginWithSpace = (String) cityBeginWithSpaceMap.get("input");
		this.cityBeginWithSpaceErrorMsg = (String) cityBeginWithSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("state")
	private void unpackState(Map<String, Object> stateMap) {
		Map<String, Object> blankStateMap = (Map<String, Object>) stateMap.get("blank");
		this.stateBlankErrorMsg = (String) blankStateMap.get("errorMessage");
		
		Map<String, Object> stateContainNumberMap = (Map<String, Object>) stateMap.get("containNumber");
		this.stateContainNumber = (String) stateContainNumberMap.get("input");
		this.stateContainNumberErrorMsg = (String) stateContainNumberMap.get("errorMessage");
		
		Map<String, Object> stateContainSpecialCharacterMap = (Map<String, Object>) stateMap.get("containSpecialCharacter");
		this.stateContainSpecialCharacter = (String) stateContainSpecialCharacterMap.get("input");
		this.stateContainSpecialCharacterErrorMsg = (String) stateContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> stateBeginWithSpaceMap = (Map<String, Object>) stateMap.get("beginWithSpace");
		this.stateBeginWithSpace = (String) stateBeginWithSpaceMap.get("input");
		this.stateBeginWithSpaceErrorMsg = (String) stateBeginWithSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("pin")
	private void unpackPin(Map<String, Object> pinMap) {
		Map<String, Object> blankPinMap = (Map<String, Object>) pinMap.get("blank");
		this.pinBlankErrorMsg = (String) blankPinMap.get("errorMessage");
		
		Map<String, Object> pinContainCharacterMap = (Map<String, Object>) pinMap.get("containCharacter");
		this.pinContainCharacter = (String) pinContainCharacterMap.get("input");
		this.pinContainCharacterErrorMsg = (String) pinContainCharacterMap.get("errorMessage");
		
		Map<String, Object> pinLessThan6DigitsMap = (Map<String, Object>) pinMap.get("lessThan6Digits");
		this.pinWithLessThan6Digits = (String) pinLessThan6DigitsMap.get("input");
		this.pinWithLessThan6DigitsErrorMsg = (String) pinLessThan6DigitsMap.get("errorMessage");
		
		Map<String, Object> pinContainSpecialCharacterMap = (Map<String, Object>) pinMap.get("containSpecialCharacter");
		this.pinContainSpecialCharacter = (String) pinContainSpecialCharacterMap.get("input");
		this.pinContainSpecialCharacterErrorMsg = (String) pinContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> pinBeginWithSpaceMap = (Map<String, Object>) pinMap.get("beginWithSpace");
		this.pinBeginWithSpace = (String) pinBeginWithSpaceMap.get("input");
		this.pinBeginWithSpaceErrorMsg = (String) pinBeginWithSpaceMap.get("errorMessage");
		
		Map<String, Object> pinContainSpaceMap = (Map<String, Object>) pinMap.get("containSpace");
		this.pinContainSpace = (String) pinContainSpaceMap.get("input");
		this.pinContainSpaceErrorMsg = (String) pinContainSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("telephone")
	private void unpackTelephone(Map<String, Object> telephoneMap) {
		Map<String, Object> blankTelephoneMap = (Map<String, Object>) telephoneMap.get("blank");
		this.telephoneBlankErrorMsg = (String) blankTelephoneMap.get("errorMessage");
		
		Map<String, Object> telephoneContainSpecialCharacterMap = (Map<String, Object>) telephoneMap.get("containSpecialCharacter");
		this.telephoneContainSpecialCharacter = (String) telephoneContainSpecialCharacterMap.get("input");
		this.telephoneContainSpecialCharacterErrorMsg = (String) telephoneContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> telephoneBeginWithSpaceMap = (Map<String, Object>) telephoneMap.get("beginWithSpace");
		this.telephoneBeginWithSpace = (String) telephoneBeginWithSpaceMap.get("input");
		this.telephoneBeginWithSpaceErrorMsg = (String) telephoneBeginWithSpaceMap.get("errorMessage");
		
		Map<String, Object> telephoneContainSpaceMap = (Map<String, Object>) telephoneMap.get("containSpace");
		this.telephoneContainSpace = (String) telephoneContainSpaceMap.get("input");
		this.telephoneContainSpaceErrorMsg = (String) telephoneContainSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("email")
	private void unpackEmail(Map<String, Object> emailMap) {
		Map<String, Object> blankEmailMap = (Map<String, Object>) emailMap.get("blank");
		this.emailBlankErrorMsg = (String) blankEmailMap.get("errorMessage");
		
		Map<String, Object> invalidEmailMap = (Map<String, Object>) emailMap.get("invalid");
		this.invalidEmail = (String) invalidEmailMap.get("input");
		this.invalidEmailErrorMsg = (String) invalidEmailMap.get("errorMessage");
		
		Map<String, Object> emailBeginWithSpaceMap = (Map<String, Object>) emailMap.get("beginWithSpace");
		this.emailBeginWithSpace = (String) emailBeginWithSpaceMap.get("input");
		this.emailBeginWithSpaceErrorMsg = (String) emailBeginWithSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("customerID")
	private void unpackCustomerID(Map<String, Object> customerIDMap) {
		Map<String, Object> blankCustomerIDMap = (Map<String, Object>) customerIDMap.get("blank");
		this.customerIdBlankErrorMsg = (String) blankCustomerIDMap.get("errorMessage");
		
		Map<String, Object> customerIDContainCharacterMap = (Map<String, Object>) customerIDMap.get("containCharacter");
		this.customerIdContainCharacter = (String) customerIDContainCharacterMap.get("input");
		this.customerIdContainCharacterErrorMsg = (String) customerIDContainCharacterMap.get("errorMessage");
		
		Map<String, Object> customerIDContainSpecialCharacterMap = (Map<String, Object>) customerIDMap.get("containSpecialCharacter");
		this.customerIdContainSpecialCharacter = (String) customerIDContainSpecialCharacterMap.get("input");
		this.customerIdContainSpecialCharacterErrorMsg = (String) customerIDContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> customerIDBeginWithSpaceMap = (Map<String, Object>) customerIDMap.get("beginWithSpace");
		this.customerIdBeginWithSpace = (String) customerIDBeginWithSpaceMap.get("input");
		this.customerIdBeginWithSpaceErrorMsg = (String) customerIDBeginWithSpaceMap.get("errorMessage");
		
		Map<String, Object> customerIDContainSpaceMap = (Map<String, Object>) customerIDMap.get("containSpace");
		this.customerIdContainSpace = (String) customerIDContainSpaceMap.get("input");
		this.customerIdContainSpaceErrorMsg = (String) customerIDContainSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("initialDeposit")
	private void unpackInitialDeposit(Map<String, Object> initialDepositMap) {
		Map<String, Object> blankInitialDepositIDMap = (Map<String, Object>) initialDepositMap.get("blank");
		this.initialDepositBlankErrorMsg = (String) blankInitialDepositIDMap.get("errorMessage");
		
		Map<String, Object> initialDepositContainCharacterMap = (Map<String, Object>) initialDepositMap.get("containCharacter");
		this.initialDepositContainCharacter = (String) initialDepositContainCharacterMap.get("input");
		this.initialDepositContainCharacterErrorMsg = (String) initialDepositContainCharacterMap.get("errorMessage");
		
		Map<String, Object> initialDepositContainSpecialCharacterMap = (Map<String, Object>) initialDepositMap.get("containSpecialCharacter");
		this.initialDepositContainSpecialCharacter = (String) initialDepositContainSpecialCharacterMap.get("input");
		this.initialDepositContainSpecialCharacterErrorMsg = (String) initialDepositContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> initialDepositBeginWithSpaceMap = (Map<String, Object>) initialDepositMap.get("beginWithSpace");
		this.initialDepositBeginWithSpace = (String) initialDepositBeginWithSpaceMap.get("input");
		this.initialDepositBeginWithSpaceErrorMsg = (String) initialDepositBeginWithSpaceMap.get("errorMessage");
		
		Map<String, Object> initialDepositContainSpaceMap = (Map<String, Object>) initialDepositMap.get("containSpace");
		this.initialDepositContainSpace = (String) initialDepositContainSpaceMap.get("input");
		this.initialDepositContainSpaceErrorMsg = (String) initialDepositContainSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("accountNo")
	private void unpackAccountNo(Map<String, Object> accountNoMap) {
		Map<String, Object> blankAccountNoMap = (Map<String, Object>) accountNoMap.get("blank");
		this.accountNoBlankErrorMsg = (String) blankAccountNoMap.get("errorMessage");
		
		Map<String, Object> accountNoContainCharacterMap = (Map<String, Object>) accountNoMap.get("containCharacter");
		this.accountNoContainCharacter = (String) accountNoContainCharacterMap.get("input");
		this.accountNoContainCharacterErrorMsg = (String) accountNoContainCharacterMap.get("errorMessage");
		
		Map<String, Object> accountNoContainSpecialCharacterMap = (Map<String, Object>) accountNoMap.get("containSpecialCharacter");
		this.accountNoContainSpecialCharacter = (String) accountNoContainSpecialCharacterMap.get("input");
		this.accountNoContainSpecialCharacterErrorMsg = (String) accountNoContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> accountNoBeginWithSpaceMap = (Map<String, Object>) accountNoMap.get("beginWithSpace");
		this.accountNoBeginWithSpace = (String) accountNoBeginWithSpaceMap.get("input");
		this.accountNoBeginWithSpaceErrorMsg = (String) accountNoBeginWithSpaceMap.get("errorMessage");
		
		Map<String, Object> accountNoContainSpaceMap = (Map<String, Object>) accountNoMap.get("containSpace");
		this.accountNoContainSpace = (String) accountNoContainSpaceMap.get("input");
		this.accountNoContainSpaceErrorMsg = (String) accountNoContainSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("minimumTransaction")
	private void unpackMinimumTransaction(Map<String, Object> minimumTransactionMap) {
		Map<String, Object> minimumTransactionContainCharacterMap = (Map<String, Object>) minimumTransactionMap.get("containCharacter");
		this.minimumTransactionValueContainCharacter = (String) minimumTransactionContainCharacterMap.get("input");
		this.minimumTransactionValueContainCharacterErrorMsg = (String) minimumTransactionContainCharacterMap.get("errorMessage");
		
		Map<String, Object> minimumTransactionContainSpecialCharacterMap = (Map<String, Object>) minimumTransactionMap.get("containSpecialCharacter");
		this.minimumTransactionValueContainSpecialCharacter = (String) minimumTransactionContainSpecialCharacterMap.get("input");
		this.minimumTransactionValueContainSpecialCharacterErrorMsg = (String) minimumTransactionContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> minimumTransactionBeginWithSpaceMap = (Map<String, Object>) minimumTransactionMap.get("beginWithSpace");
		this.minimumTransactionValueBeginWithSpace = (String) minimumTransactionBeginWithSpaceMap.get("input");
		this.minimumTransactionValueBeginWithSpaceErrorMsg = (String) minimumTransactionBeginWithSpaceMap.get("errorMessage");
		
		Map<String, Object> minimumTransactionContainSpaceMap = (Map<String, Object>) minimumTransactionMap.get("containSpace");
		this.minimumTransactionValueContainSpace = (String) minimumTransactionContainSpaceMap.get("input");
		this.minimumTransactionValueContainSpaceErrorMsg = (String) minimumTransactionContainSpaceMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("numberOfTransaction")
	private void unpackNumberOfTransaction(Map<String, Object> numberOfTransactionMap) {
		Map<String, Object> numberOfTransactionContainCharacterMap = (Map<String, Object>) numberOfTransactionMap.get("containCharacter");
		this.numOfTransactionContainCharacter = (String) numberOfTransactionContainCharacterMap.get("input");
		this.numOfTransactionContainCharacterErrorMsg = (String) numberOfTransactionContainCharacterMap.get("errorMessage");
		
		Map<String, Object> numberOfTransactionContainSpecialCharacterMap = (Map<String, Object>) numberOfTransactionMap.get("containSpecialCharacter");
		this.numOfTransactionContainSpecialCharacter = (String) numberOfTransactionContainSpecialCharacterMap.get("input");
		this.numOfTransactionContainSpecialCharacterErrorMsg = (String) numberOfTransactionContainSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> numberOfTransactionBeginWithSpaceMap = (Map<String, Object>) numberOfTransactionMap.get("beginWithSpace");
		this.numOfTransactionBeginWithSpace = (String) numberOfTransactionBeginWithSpaceMap.get("input");
		this.numOfTransactionBeginWithSpaceErrorMsg = (String) numberOfTransactionBeginWithSpaceMap.get("errorMessage");
		
		Map<String, Object> numberOfTransactionContainSpaceMap = (Map<String, Object>) numberOfTransactionMap.get("containSpace");
		this.numOfTransactionContainSpace = (String) numberOfTransactionContainSpaceMap.get("input");
		this.numOfTransactionContainSpaceErrorMsg = (String) numberOfTransactionContainSpaceMap.get("errorMessage");
	}
	
	
	@SuppressWarnings("unchecked")
	@JsonProperty("payerAccountNo")
	private void unpackPayerAccountNo(Map<String, Object> payerAccountNoMap) {
		Map<String, Object> blankPayerAccountNoMap = (Map<String, Object>) payerAccountNoMap.get("blank");
		this.payerAccountNoBlankErrorMsg = (String) blankPayerAccountNoMap.get("errorMessage");
		
		Map<String, Object> payerAccountNoContainCharacterMap = (Map<String, Object>) payerAccountNoMap.get("containCharacter");
		this.payerAccountNoContainCharacter = (String) payerAccountNoContainCharacterMap.get("input");
		this.payerAccountNoContainCharacterErrorMsg = (String) payerAccountNoContainCharacterMap.get("errorMessage");
		
		Map<String, Object> payerAccountNoContainSpecialCharacterMap = (Map<String, Object>) payerAccountNoMap.get("containSpecialCharacter");
		this.payerAccountNoContainSpecialCharacter = (String) payerAccountNoContainSpecialCharacterMap.get("input");
		this.payerAccountNoContainSpecialCharacterErrorMsg = (String) payerAccountNoContainSpecialCharacterMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("payeeAccountNo")
	private void unpackPayeeAccountNo(Map<String, Object> payeeAccountNoMap) {
		Map<String, Object> blankPayeeAccountNoMap = (Map<String, Object>) payeeAccountNoMap.get("blank");
		this.payeeAccountNoBlankErrorMsg = (String) blankPayeeAccountNoMap.get("errorMessage");
		
		Map<String, Object> payeeAccountNoContainCharacterMap = (Map<String, Object>) payeeAccountNoMap.get("containCharacter");
		this.payeeAccountNoContainCharacter = (String) payeeAccountNoContainCharacterMap.get("input");
		this.payeeAccountNoContainCharacterErrorMsg = (String) payeeAccountNoContainCharacterMap.get("errorMessage");
		
		Map<String, Object> payeeAccountNoContainSpecialCharacterMap = (Map<String, Object>) payeeAccountNoMap.get("containSpecialCharacter");
		this.payeeAccountNoContainSpecialCharacter = (String) payeeAccountNoContainSpecialCharacterMap.get("input");
		this.payeeAccountNoContainSpecialCharacterErrorMsg = (String) payeeAccountNoContainSpecialCharacterMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("fundTransferAmount")
	private void unpackFundTransferAmount(Map<String, Object> fundTransferAmountMap) {
		Map<String, Object> blankTransferAmountMap = (Map<String, Object>) fundTransferAmountMap.get("blank");
		this.fundTransferAmountBlankErrorMsg = (String) blankTransferAmountMap.get("errorMessage");
		
		Map<String, Object> transferAmountContainCharacterMap = (Map<String, Object>) fundTransferAmountMap.get("containCharacter");
		this.fundTransferAmountContainCharacter = (String) transferAmountContainCharacterMap.get("input");
		this.fundTransferAmountContainCharacterErrorMsg = (String) transferAmountContainCharacterMap.get("errorMessage");
		
		Map<String, Object> transferAmountContainSpecialCharacterMap = (Map<String, Object>) fundTransferAmountMap.get("containSpecialCharacter");
		this.fundTransferAmountContainSpecialCharacter = (String) transferAmountContainSpecialCharacterMap.get("input");
		this.fundTransferAmountContainSpecialCharacterErrorMsg = (String) transferAmountContainSpecialCharacterMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("fundTransferDescription")
	private void unpackFundTransferDescription(Map<String, Object> fundTransferDescriptionMap) {
		Map<String, Object> blankTransferAmountMap = (Map<String, Object>) fundTransferDescriptionMap.get("blank");
		this.fundTransferDescriptionBlankErrorMsg = (String) blankTransferAmountMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("oldPassword")
	private void unpackOldPassword(Map<String, Object> oldPasswordMap) {
		Map<String, Object> blankOldPasswordMap = (Map<String, Object>) oldPasswordMap.get("blank");
		this.oldPasswordBlankErrorMsg = (String) blankOldPasswordMap.get("errorMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("newPassowrd")
	private void unpackNewPassowrd(Map<String, Object> newPassowrdMap) {
		Map<String, Object> blankNewPasswordMap = (Map<String, Object>) newPassowrdMap.get("blank");
		this.newPasswordBlankErrorMsg = (String) blankNewPasswordMap.get("errorMessage");
		
		Map<String, Object> newPasswordWithoutNumberMap = (Map<String, Object>) newPassowrdMap.get("withoutNumber");
		this.newPasswordWithoutNumber = (String) newPasswordWithoutNumberMap.get("input");
		this.newPasswordWithoutNumberErrorMsg = (String) newPasswordWithoutNumberMap.get("errorMessage");
		
		Map<String, Object> newPasswordWithoutSpecialCharacterMap = (Map<String, Object>) newPassowrdMap.get("withoutSpecialCharacter");
		this.newPasswordWithoutSpecialCharacter = (String) newPasswordWithoutSpecialCharacterMap.get("input");
		this.newPasswordWithoutSpecialCharacterErrorMsg = (String) newPasswordWithoutSpecialCharacterMap.get("errorMessage");
		
		Map<String, Object> newPasswordContainPasswordStringMap = (Map<String, Object>) newPassowrdMap.get("containPasswordString");
		this.newPasswordContainPasswordString = (String) newPasswordContainPasswordStringMap.get("input");
		this.newPasswordContainPasswordStringErrorMsg = (String) newPasswordContainPasswordStringMap.get("errorMessage");
		
		Map<String, Object> validNewPasswordMap = (Map<String, Object>) newPassowrdMap.get("valid");
		this.validNewPassword = (String) validNewPasswordMap.get("input");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("confirmPassword")
	private void unpackConfirmPassword(Map<String, Object> confirmPasswordMap) {
		Map<String, Object> confirmPasswordNotMatchMap = (Map<String, Object>) confirmPasswordMap.get("notMatchOldPassword");
		this.confirmPasswordNotmatch = (String) confirmPasswordNotMatchMap.get("input");
		this.confirmPasswordNotmatchErrorMsg = (String) confirmPasswordNotMatchMap.get("errorMessage");
	}
	
	private String customerNameBlankErrorMsg;
	private String customerNameContainNumber;
	private String customerNameContainNumberErrorMsg;
	private String customerNameContainSpecialCharacter;
	private String customerNameContainSpecialCharacterErrorMsg;
	private String customerNameBeginWithSpace;
	private String customerNameBeginWithSpaceErrorMsg;
	private String addressBlankErrorMsg;
	private String addressBeginWithSpace;
	private String addressBeginWithSpaceErrorMsg;
	private String cityBlankErrorMsg;
	private String cityContainNumber;
	private String cityContainNumberErrorMsg;
	private String cityContainSpecialCharacter;
	private String cityContainSpecialCharacterErrorMsg;
	private String cityBeginWithSpace;
	private String cityBeginWithSpaceErrorMsg;
	private String stateBlankErrorMsg;
	private String stateContainNumber;
	private String stateContainNumberErrorMsg;
	private String stateContainSpecialCharacter;
	private String stateContainSpecialCharacterErrorMsg;
	private String stateBeginWithSpace;
	private String stateBeginWithSpaceErrorMsg;
	private String pinBlankErrorMsg;
	private String pinContainCharacter;
	private String pinContainCharacterErrorMsg;
	private String pinWithLessThan6Digits;
	private String pinWithLessThan6DigitsErrorMsg;
	private String pinContainSpecialCharacter;
	private String pinContainSpecialCharacterErrorMsg;
	private String pinBeginWithSpace;
	private String pinBeginWithSpaceErrorMsg;
	private String pinContainSpace;
	private String pinContainSpaceErrorMsg;
	private String telephoneBlankErrorMsg;
	private String telephoneBeginWithSpace;
	private String telephoneBeginWithSpaceErrorMsg;
	private String telephoneContainSpace;
	private String telephoneContainSpaceErrorMsg;
	private String telephoneContainSpecialCharacter;
	private String telephoneContainSpecialCharacterErrorMsg;
	private String emailBlankErrorMsg;
	private String invalidEmail;
	private String invalidEmailErrorMsg;
	private String emailBeginWithSpace;
	private String emailBeginWithSpaceErrorMsg;
	private String customerIdBlankErrorMsg;
	private String customerIdContainCharacter;
	private String customerIdContainCharacterErrorMsg;
	private String customerIdContainSpecialCharacter;
	private String customerIdContainSpecialCharacterErrorMsg;
	private String customerIdContainSpace;
	private String customerIdContainSpaceErrorMsg;
	private String customerIdBeginWithSpace;
	private String customerIdBeginWithSpaceErrorMsg;
	private String initialDepositBlankErrorMsg;
	private String initialDepositContainCharacter;
	private String initialDepositContainCharacterErrorMsg;
	private String initialDepositContainSpecialCharacter;
	private String initialDepositContainSpecialCharacterErrorMsg;
	private String initialDepositContainSpace;
	private String initialDepositContainSpaceErrorMsg;
	private String initialDepositBeginWithSpace;
	private String initialDepositBeginWithSpaceErrorMsg;
	private String accountNoBlankErrorMsg;
	private String accountNoContainCharacter;
	private String accountNoContainCharacterErrorMsg;
	private String accountNoContainSpecialCharacter;
	private String accountNoContainSpecialCharacterErrorMsg;
	private String accountNoContainSpace;
	private String accountNoContainSpaceErrorMsg;
	private String accountNoBeginWithSpace;
	private String accountNoBeginWithSpaceErrorMsg;
	private String minimumTransactionValueContainCharacter;
	private String minimumTransactionValueContainCharacterErrorMsg;
	private String minimumTransactionValueContainSpecialCharacter;
	private String minimumTransactionValueContainSpecialCharacterErrorMsg;
	private String minimumTransactionValueContainSpace;
	private String minimumTransactionValueContainSpaceErrorMsg;
	private String minimumTransactionValueBeginWithSpace;
	private String minimumTransactionValueBeginWithSpaceErrorMsg;
	private String numOfTransactionContainCharacter;
	private String numOfTransactionContainCharacterErrorMsg;
	private String numOfTransactionContainSpecialCharacter;
	private String numOfTransactionContainSpecialCharacterErrorMsg;
	private String numOfTransactionContainSpace;
	private String numOfTransactionContainSpaceErrorMsg;
	private String numOfTransactionBeginWithSpace;
	private String numOfTransactionBeginWithSpaceErrorMsg;
	private String payerAccountNoBlankErrorMsg;
	private String payerAccountNoContainCharacter;
	private String payerAccountNoContainCharacterErrorMsg;
	private String payerAccountNoContainSpecialCharacter;
	private String payerAccountNoContainSpecialCharacterErrorMsg;
	private String payeeAccountNoBlankErrorMsg;
	private String payeeAccountNoContainCharacter;
	private String payeeAccountNoContainCharacterErrorMsg;
	private String payeeAccountNoContainSpecialCharacter;
	private String payeeAccountNoContainSpecialCharacterErrorMsg;
	private String fundTransferAmountBlankErrorMsg;
	private String fundTransferAmountContainCharacter;
	private String fundTransferAmountContainCharacterErrorMsg;
	private String fundTransferAmountContainSpecialCharacter;
	private String fundTransferAmountContainSpecialCharacterErrorMsg;
	private String fundTransferDescriptionBlankErrorMsg;
	private String oldPasswordBlankErrorMsg;
	private String newPasswordBlankErrorMsg;
	private String newPasswordWithoutNumber;
	private String newPasswordWithoutNumberErrorMsg;
	private String newPasswordWithoutSpecialCharacter;
	private String newPasswordWithoutSpecialCharacterErrorMsg;
	private String newPasswordContainPasswordString;
	private String newPasswordContainPasswordStringErrorMsg;
	private String validNewPassword;
	private String confirmPasswordNotmatch;
	private String confirmPasswordNotmatchErrorMsg;

	public String getCustomerNameBlankErrorMsg() {
		return customerNameBlankErrorMsg;
	}

	public String getCustomerNameContainNumber() {
		return customerNameContainNumber;
	}

	public String getCustomerNameContainNumberErrorMsg() {
		return customerNameContainNumberErrorMsg;
	}

	public String getCustomerNameContainSpecialCharacter() {
		return customerNameContainSpecialCharacter;
	}

	public String getCustomerNameContainSpecialCharacterErrorMsg() {
		return customerNameContainSpecialCharacterErrorMsg;
	}

	public String getCustomerNameBeginWithSpace() {
		return customerNameBeginWithSpace;
	}

	public String getCustomerNameBeginWithSpaceErrorMsg() {
		return customerNameBeginWithSpaceErrorMsg;
	}

	public String getAddressBlankErrorMsg() {
		return addressBlankErrorMsg;
	}

	public String getAddressBeginWithSpace() {
		return addressBeginWithSpace;
	}

	public String getAddressBeginWithSpaceErrorMsg() {
		return addressBeginWithSpaceErrorMsg;
	}

	public String getCityBlankErrorMsg() {
		return cityBlankErrorMsg;
	}

	public String getCityContainNumber() {
		return cityContainNumber;
	}

	public String getCityContainNumberErrorMsg() {
		return cityContainNumberErrorMsg;
	}

	public String getCityContainSpecialCharacter() {
		return cityContainSpecialCharacter;
	}

	public String getCityContainSpecialCharacterErrorMsg() {
		return cityContainSpecialCharacterErrorMsg;
	}

	public String getCityBeginWithSpace() {
		return cityBeginWithSpace;
	}

	public String getCityBeginWithSpaceErrorMsg() {
		return cityBeginWithSpaceErrorMsg;
	}

	public String getStateBlankErrorMsg() {
		return stateBlankErrorMsg;
	}

	public String getStateContainNumber() {
		return stateContainNumber;
	}

	public String getStateContainNumberErrorMsg() {
		return stateContainNumberErrorMsg;
	}

	public String getStateContainSpecialCharacter() {
		return stateContainSpecialCharacter;
	}

	public String getStateContainSpecialCharacterErrorMsg() {
		return stateContainSpecialCharacterErrorMsg;
	}

	public String getStateBeginWithSpace() {
		return stateBeginWithSpace;
	}

	public String getStateBeginWithSpaceErrorMsg() {
		return stateBeginWithSpaceErrorMsg;
	}

	public String getPinBlankErrorMsg() {
		return pinBlankErrorMsg;
	}

	public String getPinContainCharacter() {
		return pinContainCharacter;
	}

	public String getPinContainCharacterErrorMsg() {
		return pinContainCharacterErrorMsg;
	}

	public String getPinWithLessThan6Digits() {
		return pinWithLessThan6Digits;
	}

	public String getPinWithLessThan6DigitsErrorMsg() {
		return pinWithLessThan6DigitsErrorMsg;
	}

	public String getPinContainSpecialCharacter() {
		return pinContainSpecialCharacter;
	}

	public String getPinContainSpecialCharacterErrorMsg() {
		return pinContainSpecialCharacterErrorMsg;
	}

	public String getPinBeginWithSpace() {
		return pinBeginWithSpace;
	}

	public String getPinBeginWithSpaceErrorMsg() {
		return pinBeginWithSpaceErrorMsg;
	}

	public String getPinContainSpace() {
		return pinContainSpace;
	}

	public String getPinContainSpaceErrorMsg() {
		return pinContainSpaceErrorMsg;
	}

	public String getTelephoneBlankErrorMsg() {
		return telephoneBlankErrorMsg;
	}

	public String getTelephoneBeginWithSpace() {
		return telephoneBeginWithSpace;
	}

	public String getTelephoneBeginWithSpaceErrorMsg() {
		return telephoneBeginWithSpaceErrorMsg;
	}

	public String getTelephoneContainSpace() {
		return telephoneContainSpace;
	}

	public String getTelephoneContainSpaceErrorMsg() {
		return telephoneContainSpaceErrorMsg;
	}

	public String getTelephoneContainSpecialCharacter() {
		return telephoneContainSpecialCharacter;
	}

	public String getTelephoneContainSpecialCharacterErrorMsg() {
		return telephoneContainSpecialCharacterErrorMsg;
	}

	public String getEmailBlankErrorMsg() {
		return emailBlankErrorMsg;
	}

	public String getInvalidEmail() {
		return invalidEmail;
	}

	public String getInvalidEmailErrorMsg() {
		return invalidEmailErrorMsg;
	}

	public String getEmailBeginWithSpace() {
		return emailBeginWithSpace;
	}

	public String getEmailBeginWithSpaceErrorMsg() {
		return emailBeginWithSpaceErrorMsg;
	}

	public String getCustomerIdBlankErrorMsg() {
		return customerIdBlankErrorMsg;
	}

	public String getCustomerIdContainCharacter() {
		return customerIdContainCharacter;
	}

	public String getCustomerIdContainCharacterErrorMsg() {
		return customerIdContainCharacterErrorMsg;
	}

	public String getCustomerIdContainSpecialCharacter() {
		return customerIdContainSpecialCharacter;
	}

	public String getCustomerIdContainSpecialCharacterErrorMsg() {
		return customerIdContainSpecialCharacterErrorMsg;
	}

	public String getCustomerIdContainSpace() {
		return customerIdContainSpace;
	}

	public String getCustomerIdContainSpaceErrorMsg() {
		return customerIdContainSpaceErrorMsg;
	}

	public String getCustomerIdBeginWithSpace() {
		return customerIdBeginWithSpace;
	}

	public String getCustomerIdBeginWithSpaceErrorMsg() {
		return customerIdBeginWithSpaceErrorMsg;
	}

	public String getInitialDepositBlankErrorMsg() {
		return initialDepositBlankErrorMsg;
	}

	public String getInitialDepositContainCharacter() {
		return initialDepositContainCharacter;
	}

	public String getInitialDepositContainCharacterErrorMsg() {
		return initialDepositContainCharacterErrorMsg;
	}

	public String getInitialDepositContainSpecialCharacter() {
		return initialDepositContainSpecialCharacter;
	}

	public String getInitialDepositContainSpecialCharacterErrorMsg() {
		return initialDepositContainSpecialCharacterErrorMsg;
	}

	public String getInitialDepositContainSpace() {
		return initialDepositContainSpace;
	}

	public String getInitialDepositContainSpaceErrorMsg() {
		return initialDepositContainSpaceErrorMsg;
	}

	public String getInitialDepositBeginWithSpace() {
		return initialDepositBeginWithSpace;
	}

	public String getInitialDepositBeginWithSpaceErrorMsg() {
		return initialDepositBeginWithSpaceErrorMsg;
	}

	public String getAccountNoBlankErrorMsg() {
		return accountNoBlankErrorMsg;
	}

	public String getAccountNoContainCharacter() {
		return accountNoContainCharacter;
	}

	public String getAccountNoContainCharacterErrorMsg() {
		return accountNoContainCharacterErrorMsg;
	}

	public String getAccountNoContainSpecialCharacter() {
		return accountNoContainSpecialCharacter;
	}

	public String getAccountNoContainSpecialCharacterErrorMsg() {
		return accountNoContainSpecialCharacterErrorMsg;
	}

	public String getAccountNoContainSpace() {
		return accountNoContainSpace;
	}

	public String getAccountNoContainSpaceErrorMsg() {
		return accountNoContainSpaceErrorMsg;
	}

	public String getAccountNoBeginWithSpace() {
		return accountNoBeginWithSpace;
	}

	public String getAccountNoBeginWithSpaceErrorMsg() {
		return accountNoBeginWithSpaceErrorMsg;
	}

	public String getMinimumTransactionValueContainCharacter() {
		return minimumTransactionValueContainCharacter;
	}

	public String getMinimumTransactionValueContainCharacterErrorMsg() {
		return minimumTransactionValueContainCharacterErrorMsg;
	}

	public String getMinimumTransactionValueContainSpecialCharacter() {
		return minimumTransactionValueContainSpecialCharacter;
	}

	public String getMinimumTransactionValueContainSpecialCharacterErrorMsg() {
		return minimumTransactionValueContainSpecialCharacterErrorMsg;
	}

	public String getMinimumTransactionValueContainSpace() {
		return minimumTransactionValueContainSpace;
	}

	public String getMinimumTransactionValueContainSpaceErrorMsg() {
		return minimumTransactionValueContainSpaceErrorMsg;
	}

	public String getMinimumTransactionValueBeginWithSpace() {
		return minimumTransactionValueBeginWithSpace;
	}

	public String getMinimumTransactionValueBeginWithSpaceErrorMsg() {
		return minimumTransactionValueBeginWithSpaceErrorMsg;
	}

	public String getNumOfTransactionContainCharacter() {
		return numOfTransactionContainCharacter;
	}

	public String getNumOfTransactionContainCharacterErrorMsg() {
		return numOfTransactionContainCharacterErrorMsg;
	}

	public String getNumOfTransactionContainSpecialCharacter() {
		return numOfTransactionContainSpecialCharacter;
	}

	public String getNumOfTransactionContainSpecialCharacterErrorMsg() {
		return numOfTransactionContainSpecialCharacterErrorMsg;
	}

	public String getNumOfTransactionContainSpace() {
		return numOfTransactionContainSpace;
	}

	public String getNumOfTransactionContainSpaceErrorMsg() {
		return numOfTransactionContainSpaceErrorMsg;
	}

	public String getNumOfTransactionBeginWithSpace() {
		return numOfTransactionBeginWithSpace;
	}

	public String getNumOfTransactionBeginWithSpaceErrorMsg() {
		return numOfTransactionBeginWithSpaceErrorMsg;
	}

	public String getPayerAccountNoBlankErrorMsg() {
		return payerAccountNoBlankErrorMsg;
	}

	public String getPayerAccountNoContainCharacter() {
		return payerAccountNoContainCharacter;
	}

	public String getPayerAccountNoContainCharacterErrorMsg() {
		return payerAccountNoContainCharacterErrorMsg;
	}

	public String getPayerAccountNoContainSpecialCharacter() {
		return payerAccountNoContainSpecialCharacter;
	}

	public String getPayerAccountNoContainSpecialCharacterErrorMsg() {
		return payerAccountNoContainSpecialCharacterErrorMsg;
	}

	public String getPayeeAccountNoBlankErrorMsg() {
		return payeeAccountNoBlankErrorMsg;
	}

	public String getPayeeAccountNoContainCharacter() {
		return payeeAccountNoContainCharacter;
	}

	public String getPayeeAccountNoContainCharacterErrorMsg() {
		return payeeAccountNoContainCharacterErrorMsg;
	}

	public String getPayeeAccountNoContainSpecialCharacter() {
		return payeeAccountNoContainSpecialCharacter;
	}

	public String getPayeeAccountNoContainSpecialCharacterErrorMsg() {
		return payeeAccountNoContainSpecialCharacterErrorMsg;
	}

	public String getFundTransferAmountBlankErrorMsg() {
		return fundTransferAmountBlankErrorMsg;
	}

	public String getFundTransferAmountContainCharacter() {
		return fundTransferAmountContainCharacter;
	}

	public String getFundTransferAmountContainCharacterErrorMsg() {
		return fundTransferAmountContainCharacterErrorMsg;
	}

	public String getFundTransferAmountContainSpecialCharacter() {
		return fundTransferAmountContainSpecialCharacter;
	}

	public String getFundTransferAmountContainSpecialCharacterErrorMsg() {
		return fundTransferAmountContainSpecialCharacterErrorMsg;
	}
	
	public String getFundTransferDescriptionBlankErrorMsg() {
		return fundTransferDescriptionBlankErrorMsg;
	}

	public String getOldPasswordBlankErrorMsg() {
		return oldPasswordBlankErrorMsg;
	}

	public String getNewPasswordBlankErrorMsg() {
		return newPasswordBlankErrorMsg;
	}

	public String getNewPasswordWithoutNumber() {
		return newPasswordWithoutNumber;
	}

	public String getNewPasswordWithoutNumberErrorMsg() {
		return newPasswordWithoutNumberErrorMsg;
	}

	public String getNewPasswordWithoutSpecialCharacter() {
		return newPasswordWithoutSpecialCharacter;
	}

	public String getNewPasswordWithoutSpecialCharacterErrorMsg() {
		return newPasswordWithoutSpecialCharacterErrorMsg;
	}

	public String getNewPasswordContainPasswordString() {
		return newPasswordContainPasswordString;
	}

	public String getNewPasswordContainPasswordStringErrorMsg() {
		return newPasswordContainPasswordStringErrorMsg;
	}

	public String getValidNewPassword() {
		return validNewPassword;
	}

	public String getConfirmPasswordNotmatch() {
		return confirmPasswordNotmatch;
	}

	public String getConfirmPasswordNotmatchErrorMsg() {
		return confirmPasswordNotmatchErrorMsg;
	}

}
