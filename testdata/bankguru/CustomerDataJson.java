package bankguru;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDataJson {
	public static CustomerDataJson get(String dataFilePath)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(dataFilePath), CustomerDataJson.class);
	}

	@SuppressWarnings("unchecked")
	@JsonProperty("firstCustomer")
	private void unpackFirstCustomer(Map<String, Object> firstCustomer) {
		this.firstCustomerName = (String) firstCustomer.get("customerName");
		this.firstCustomerGender = (String) firstCustomer.get("gender");
		this.firstCustomerDateOfBirth = (String) firstCustomer.get("dateOfBirth");
		this.firstCustomerAddress = (String) firstCustomer.get("address");
		this.firstCustomerState = (String) firstCustomer.get("state");
		this.firstCustomerCity = (String) firstCustomer.get("city");
		this.firstCustomerPin = (String) firstCustomer.get("pin");
		this.firstCustomerTelephone = (String) firstCustomer.get("telephone");
		this.firstCustomerEmail = (String) firstCustomer.get("email");
		this.firstCustomerPassword = (String)firstCustomer.get("password");
		
		Map<String, Object> editedCustomer = (Map<String, Object>) firstCustomer.get("edit");
		this.firstCustomerEditedAddress = (String) editedCustomer.get("address");
		this.firstCustomerEditedCity = (String) editedCustomer.get("city");
		this.firstCustomerEditedState= (String) editedCustomer.get("state");
		this.firstCustomerEditedPin = (String) editedCustomer.get("pin");
		this.firstCustomerEditedTelephone = (String) editedCustomer.get("telephone");
		this.firstCustomerEditedEmail = (String) editedCustomer.get("email");
	}

	@JsonProperty("secondCustomer")
	private void unpackSecondCustomer(Map<String, Object> secondCustomer) {
		this.secondCustomerName = (String) secondCustomer.get("customerName");
		this.secondCustomerGender = (String) secondCustomer.get("gender");
		this.secondCustomerDateOfBirth = (String) secondCustomer.get("dateOfBirth");
		this.secondCustomerAddress = (String) secondCustomer.get("address");
		this.secondCustomerState = (String) secondCustomer.get("state");
		this.secondCustomerCity = (String) secondCustomer.get("city");
		this.secondCustomerPin = (String) secondCustomer.get("pin");
		this.secondCustomerTelephone = (String) secondCustomer.get("telephone");
		this.secondCustomerEmail = (String) secondCustomer.get("email");
		this.secondCustomerPassword = (String) secondCustomer.get("password");
	}

	@JsonProperty("thirdCustomer")
	private void unpackThirdCustomer(Map<String, Object> thirdCustomer) {
		this.thirdCustomerName = (String) thirdCustomer.get("customerName");
		this.thirdCustomerGender = (String) thirdCustomer.get("gender");
		this.thirdCustomerDateOfBirth = (String) thirdCustomer.get("dateOfBirth");
		this.thirdCustomerAddress = (String) thirdCustomer.get("address");
		this.thirdCustomerState = (String) thirdCustomer.get("state");
		this.thirdCustomerCity = (String) thirdCustomer.get("city");
		this.thirdCustomerPin = (String) thirdCustomer.get("pin");
		this.thirdCustomerTelephone = (String) thirdCustomer.get("telephone");
		this.thirdCustomerEmail = (String) thirdCustomer.get("email");
		this.thirdCustomerPassword = (String) thirdCustomer.get("password");
	}

	@JsonProperty("forthCustomer")
	public void unpackForthCutomer(Map<String, Object> forthCustomer) {
		this.forthCustomerName = (String) forthCustomer.get("customerName");
		this.forthCustomerGender = (String) forthCustomer.get("gender");
		this.forthCustomerDateOfBirth = (String) forthCustomer.get("dateOfBirth");
		this.forthCustomerAddress = (String) forthCustomer.get("address");
		this.forthCustomerState = (String) forthCustomer.get("state");
		this.forthCustomerCity = (String) forthCustomer.get("city");
		this.forthCustomerPin = (String) forthCustomer.get("pin");
		this.forthCustomerTelephone = (String) forthCustomer.get("telephone");
		this.forthCustomerEmail = (String) forthCustomer.get("email");
		this.forthCustomerPassword = (String) forthCustomer.get("password");
	}

	private String firstCustomerName;

	private String firstCustomerGender;

	private String firstCustomerDateOfBirth;

	private String firstCustomerAddress;

	private String firstCustomerState;

	private String firstCustomerCity;

	private String firstCustomerPin;

	private String firstCustomerTelephone;

	private String firstCustomerEmail;

	private String firstCustomerPassword;

	private String firstCustomerEditedAddress;

	private String firstCustomerEditedCity;

	private String firstCustomerEditedState;

	private String firstCustomerEditedPin;

	private String firstCustomerEditedTelephone;

	private String firstCustomerEditedEmail;

	private String secondCustomerName;

	private String secondCustomerGender;

	private String secondCustomerDateOfBirth;

	private String secondCustomerAddress;

	private String secondCustomerState;

	private String secondCustomerCity;

	private String secondCustomerPin;

	private String secondCustomerTelephone;

	private String secondCustomerEmail;

	private String secondCustomerPassword;

	private String thirdCustomerName;

	private String thirdCustomerGender;

	private String thirdCustomerDateOfBirth;

	private String thirdCustomerAddress;

	private String thirdCustomerState;

	private String thirdCustomerCity;

	private String thirdCustomerPin;

	private String thirdCustomerTelephone;

	private String thirdCustomerEmail;

	private String thirdCustomerPassword;

	private String forthCustomerName;

	private String forthCustomerGender;

	private String forthCustomerDateOfBirth;

	private String forthCustomerAddress;

	private String forthCustomerState;

	private String forthCustomerCity;

	private String forthCustomerPin;

	private String forthCustomerTelephone;

	private String forthCustomerEmail;

	private String forthCustomerPassword;

	public String getFirstCustomerName() {
		return firstCustomerName;
	}

	public String getFirstCustomerGender() {
		return firstCustomerGender;
	}

	public String getFirstCustomerDateOfBirth() {
		return firstCustomerDateOfBirth;
	}

	public String getFirstCustomerAddress() {
		return firstCustomerAddress;
	}

	public String getFirstCustomerState() {
		return firstCustomerState;
	}

	public String getFirstCustomerCity() {
		return firstCustomerCity;
	}

	public String getFirstCustomerPin() {
		return firstCustomerPin;
	}

	public String getFirstCustomerTelephone() {
		return firstCustomerTelephone;
	}

	public String getFirstCustomerEmail() {
		return firstCustomerEmail;
	}

	public String getFirstCustomerPassword() {
		return firstCustomerPassword;
	}

	public String getFirstCustomerEditedAddress() {
		return firstCustomerEditedAddress;
	}

	public String getFirstCustomerEditedCity() {
		return firstCustomerEditedCity;
	}

	public String getFirstCustomerEditedState() {
		return firstCustomerEditedState;
	}

	public String getFirstCustomerEditedPin() {
		return firstCustomerEditedPin;
	}

	public String getFirstCustomerEditedTelephone() {
		return firstCustomerEditedTelephone;
	}

	public String getFirstCustomerEditedEmail() {
		return firstCustomerEditedEmail;
	}

	public String getSecondCustomerName() {
		return secondCustomerName;
	}

	public String getSecondCustomerGender() {
		return secondCustomerGender;
	}

	public String getSecondCustomerDateOfBirth() {
		return secondCustomerDateOfBirth;
	}

	public String getSecondCustomerAddress() {
		return secondCustomerAddress;
	}

	public String getSecondCustomerState() {
		return secondCustomerState;
	}

	public String getSecondCustomerCity() {
		return secondCustomerCity;
	}

	public String getSecondCustomerPin() {
		return secondCustomerPin;
	}

	public String getSecondCustomerTelephone() {
		return secondCustomerTelephone;
	}

	public String getSecondCustomerEmail() {
		return secondCustomerEmail;
	}

	public String getSecondCustomerPassword() {
		return secondCustomerPassword;
	}

	public String getThirdCustomerName() {
		return thirdCustomerName;
	}

	public String getThirdCustomerGender() {
		return thirdCustomerGender;
	}

	public String getThirdCustomerDateOfBirth() {
		return thirdCustomerDateOfBirth;
	}

	public String getThirdCustomerAddress() {
		return thirdCustomerAddress;
	}

	public String getThirdCustomerState() {
		return thirdCustomerState;
	}

	public String getThirdCustomerCity() {
		return thirdCustomerCity;
	}

	public String getThirdCustomerPin() {
		return thirdCustomerPin;
	}

	public String getThirdCustomerTelephone() {
		return thirdCustomerTelephone;
	}

	public String getThirdCustomerEmail() {
		return thirdCustomerEmail;
	}

	public String getThirdCustomerPassword() {
		return thirdCustomerPassword;
	}

	public String getForthCustomerName() {
		return forthCustomerName;
	}

	public String getForthCustomerGender() {
		return forthCustomerGender;
	}

	public String getForthCustomerDateOfBirth() {
		return forthCustomerDateOfBirth;
	}

	public String getForthCustomerAddress() {
		return forthCustomerAddress;
	}

	public String getForthCustomerState() {
		return forthCustomerState;
	}

	public String getForthCustomerCity() {
		return forthCustomerCity;
	}

	public String getForthCustomerPin() {
		return forthCustomerPin;
	}

	public String getForthCustomerTelephone() {
		return forthCustomerTelephone;
	}

	public String getForthCustomerEmail() {
		return forthCustomerEmail;
	}

	public String getForthCustomerPassword() {
		return forthCustomerPassword;
	}

}
