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
		
		Map<String, Object> editedAccount = (Map<String, Object>) firstAccount.get("edit");
		this.firstAccountEditedType = (String) editedAccount.get("editedAccountType");
		
		Map<String, Object> deposit = (Map<String, Object>) firstAccount.get("deposit");
		this.firstAccountDepositAmount = (int) deposit.get("amount");
		this.firstAccountDepositDescription = (String) deposit.get("description");
		
		Map<String, Object> withdrawl = (Map<String, Object>) firstAccount.get("withdrawal");
		this.firstAccountWithdrawAmount= (int) withdrawl.get("amount");
		this.firstAccountWithdrawDescription = (String) withdrawl.get("description");
		
		Map<String, Object> fundTransfer = (Map<String, Object>) firstAccount.get("fundTransfer");
		this.firstAccountTransferAmount = (int) fundTransfer.get("amount");
		this.firstAccountTransferDescription = (String) fundTransfer.get("description");
	}
	
	@JsonProperty("secondAccount")
	public void unPackSecondAccount(Map<String, Object> secondAccount) {
		this.secondAccountType = (String) secondAccount.get("accountType");
		this.secondAccountInitialDeposit = (int) secondAccount.get("initialDeposit");
	}
	
	@JsonProperty("thirdAccount")
	public void unPackThirdAccount(Map<String, Object> thirddAccount) {
		this.thirdAccountType = (String) thirddAccount.get("accountType");
		this.thirdAccountInitialDeposit = (int) thirddAccount.get("initialDeposit");
	}
	
	private String firstAccountType;
	
	private int firstAccountInitialDeposit;
	
	private String firstAccountEditedType;
	
	private int firstAccountDepositAmount;
	
	private String firstAccountDepositDescription;
	
	private int firstAccountWithdrawAmount;
	
	private String firstAccountWithdrawDescription;
	
	private int firstAccountTransferAmount;
	
	private String firstAccountTransferDescription;
	
	private String secondAccountType;
	
	private int secondAccountInitialDeposit;
	
	private String thirdAccountType;
	
	private int thirdAccountInitialDeposit;

	public String getFirstAccountType() {
		return firstAccountType;
	}

	public int getFirstAccountInitialDeposit() {
		return firstAccountInitialDeposit;
	}

	public String getFirstAccountEditedType() {
		return firstAccountEditedType;
	}

	public int getFirstAccountDepositAmount() {
		return firstAccountDepositAmount;
	}

	public String getFirstAccountDepositDescription() {
		return firstAccountDepositDescription;
	}

	public int getFirstAccountWithdrawAmount() {
		return firstAccountWithdrawAmount;
	}

	public String getFirstAccountWithdrawDescription() {
		return firstAccountWithdrawDescription;
	}

	public int getFirstAccountTransferAmount() {
		return firstAccountTransferAmount;
	}

	public String getFirstAccountTransferDescription() {
		return firstAccountTransferDescription;
	}

	public String getSecondAccountType() {
		return secondAccountType;
	}

	public int getSecondAccountInitialDeposit() {
		return secondAccountInitialDeposit;
	}

	public String getThirdAccountType() {
		return thirdAccountType;
	}

	public int getThirdAccountInitialDeposit() {
		return thirdAccountInitialDeposit;
	}

}
