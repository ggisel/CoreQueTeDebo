package ar.com.quetedebo.core;

public class Debt {
	private String memberPayment;
	private String addressPayment;
	private Float amount;
	private String description;

	public String getMemberPayment() {
		return memberPayment;
	}

	public void setMemberPayment(String memberPayment) {
		this.memberPayment = memberPayment;
	}

	public String getAddressPayment() {
		return addressPayment;
	}

	public void setAddressPayment(String addressPayment) {
		this.addressPayment = addressPayment;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
