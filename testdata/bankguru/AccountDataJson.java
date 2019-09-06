package bankguru;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountDataJson {
	public static AccountDataJson get(String dataFilePath) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(dataFilePath), AccountDataJson.class);
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("firstAccount")
	public void unpackFirstAccount(Map<String, Object> firstAccount) {
		this.firstAccountType = (String) firstAccount.get("accountType");
		this.firstAccountInitialDeposit = (int) firstAccount.get("initialDeposit");
		this.firstAccountAddingResultMessage = (String) firstAccount.get("resultMessage");
		
		Map<String, Object> editedAccount = (Map<String, Object>) firstAccount.get("edit");
		this.firstAccountEditedType = (String) editedAccount.get("editedAccountType");
		this.firstAccountEditingResultMessage = (String) editedAccount.get("resultMessage");
		
		Map<String, Object> deposit = (Map<String, Object>) firstAccount.get("deposit");
		this.firstAccountDepositAmount = (int) deposit.get("amount");
		this.firstAccountDepositDescription = (String) deposit.get("description");
		this.firstAccountDepositResultMessage = (String) deposit.get("resultMessage");
		
		Map<String, Object> withdrawl = (Map<String, Object>) firstAccount.get("withdrawal");
		this.firstAccountWithdrawAmount= (int) withdrawl.get("amount");
		this.firstAccountWithdrawDescription = (String) withdrawl.get("description");
		this.firstAccountWithdrawResultMessage = (String) withdrawl.get("resultMessage");
		
		Map<String, Object> fundTransfer = (Map<String, Object>) firstAccount.get("fundTransfer");
		this.firstAccountTransferAmount = (int) fundTransfer.get("amount");
		this.firstAccountTransferDescription = (String) fundTransfer.get("description");
		this.firstAccountTransferResultMessage = (String) fundTransfer.get("resultMessage");
		
		Map<String, Object> balanceEnquiry = (Map<String, Object>) firstAccount.get("balanceEnquiry");
		this.firstAccountBalanceEnquiryResultMessage = (String) balanceEnquiry.get("resultMessage");
		
		Map<String, Object> deletedAccount = (Map<String, Object>) firstAccount.get("delete");
		this.firstAccountDeletingResultMessage = (String) deletedAccount.get("resultMessage");
	}
	
	@SuppressWarnings("unchecked")
	@JsonProperty("secondAccount")
	public void unPackSecondAccount(Map<String, Object> secondAccount) {
		this.secondAccountType = (String) secondAccount.get("accountType");
		this.secondAccountInitialDeposit = (int) secondAccount.get("initialDeposit");
		this.secondAccountAddingResultMessage = (String) secondAccount.get("resultMessage");
		
		Map<String, Object> balanceEnquiry = (Map<String, Object>) secondAccount.get("balanceEnquiry");
		this.secondAccountBalanceEnquiryResultMessage = (String) balanceEnquiry.get("resultMessage");
		
		Map<String, Object> deletedAccount = (Map<String, Object>) secondAccount.get("delete");
		this.secondAccountDeletingResultMessage = (String) deletedAccount.get("resultMessage");
	}
	
	@JsonProperty("thirdAccount")
	public void unPackThirdAccount(Map<String, Object> thirddAccount) {
		this.thirdAccountType = (String) thirddAccount.get("accountType");
		this.thirdAccountInitialDeposit = (int) thirddAccount.get("initialDeposit");
	}
	
	private String firstAccountType;
	
	private int firstAccountInitialDeposit;
	
	private String firstAccountAddingResultMessage;
	
	private String firstAccountEditedType;
	
	private String firstAccountEditingResultMessage;
	
	private int firstAccountDepositAmount;
	
	private String firstAccountDepositDescription;
	
	private String firstAccountDepositResultMessage;
	
	private int firstAccountWithdrawAmount;
	
	private String firstAccountWithdrawDescription;
	
	private String firstAccountWithdrawResultMessage;
	
	private int firstAccountTransferAmount;
	
	private String firstAccountTransferDescription;
	
	private String firstAccountTransferResultMessage;
	
	private String firstAccountBalanceEnquiryResultMessage;
	
	private String firstAccountDeletingResultMessage;
	
	private String secondAccountType;
	
	private int secondAccountInitialDeposit;
	
	private String secondAccountAddingResultMessage;
	
	private String secondAccountBalanceEnquiryResultMessage;
	
	private String secondAccountDeletingResultMessage;
	
	private String thirdAccountType;
	
	private int thirdAccountInitialDeposit;

	public String getFirstAccountType() {
		return firstAccountType;
	}

	public int getFirstAccountInitialDeposit() {
		return firstAccountInitialDeposit;
	}

	public String getFirstAccountAddingResultMessage() {
		return firstAccountAddingResultMessage;
	}

	public String getFirstAccountEditedType() {
		return firstAccountEditedType;
	}

	public String getFirstAccountEditingResultMessage() {
		return firstAccountEditingResultMessage;
	}

	public int getFirstAccountDepositAmount() {
		return firstAccountDepositAmount;
	}

	public String getFirstAccountDepositDescription() {
		return firstAccountDepositDescription;
	}

	public String getFirstAccountDepositResultMessage() {
		return firstAccountDepositResultMessage;
	}

	public int getFirstAccountWithdrawAmount() {
		return firstAccountWithdrawAmount;
	}

	public String getFirstAccountWithdrawDescription() {
		return firstAccountWithdrawDescription;
	}

	public String getFirstAccountWithdrawResultMessage() {
		return firstAccountWithdrawResultMessage;
	}

	public int getFirstAccountTransferAmount() {
		return firstAccountTransferAmount;
	}

	public String getFirstAccountTransferDescription() {
		return firstAccountTransferDescription;
	}

	public String getFirstAccountTransferResultMessage() {
		return firstAccountTransferResultMessage;
	}

	public String getFirstAccountBalanceEnquiryResultMessage() {
		return firstAccountBalanceEnquiryResultMessage;
	}

	public String getFirstAccountDeletingResultMessage() {
		return firstAccountDeletingResultMessage;
	}

	public String getSecondAccountType() {
		return secondAccountType;
	}

	public int getSecondAccountInitialDeposit() {
		return secondAccountInitialDeposit;
	}

	public String getSecondAccountAddingResultMessage() {
		return secondAccountAddingResultMessage;
	}

	public String getSecondAccountBalanceEnquiryResultMessage() {
		return secondAccountBalanceEnquiryResultMessage;
	}

	public String getSecondAccountDeletingResultMessage() {
		return secondAccountDeletingResultMessage;
	}

	public String getThirdAccountType() {
		return thirdAccountType;
	}

	public int getThirdAccountInitialDeposit() {
		return thirdAccountInitialDeposit;
	}

}
